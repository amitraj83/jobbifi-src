var chatConnected = false;
var friendsprofilepics = "";
var CHAT_FRIENDS_LI = new Map;
var onlineStatus = '<img src="images/green.png" width="15px" height="15px" />';
var offlineStatus = '<img src="images/red.png" width="15px" height="15px" />';

function on_message(message) {
    var jid = Strophe.getBareJidFromJid($(message).attr('from'));
    var body = $(message).find("html > body");
    var composing = $(message).find('composing');
    if (composing.length > 0) {
        $("#typingplace").html(Strophe.getNodeFromJid(jid) + " is typing...");
    }

    if (body.length === 0) {
        body = $(message).find('body');
        if (body.length > 0) {
            body = body.text()
        } else {
            body = null;
        }
    } else {
        body = body.contents();
        var span = $("<span></span>");
        body.each(function () {
            if (document.importNode) {
                $(document.importNode(this, true)).appendTo(span);
            } else {
                // IE workaround
                span.append(this.xml);
            }
        });
    }

    if (body) {
        var chatmessage = getChatMessageWithHTML(friendsprofilepics[Strophe.getNodeFromJid(jid)],
            Strophe.getNodeFromJid(jid),
            new Date().getTime(), body);

        $("#mCSB_1_container").append(chatmessage);
        $("#scrollarea").mCustomScrollbar("scrollTo", "bottom");
        $("#typingplace").html("");
    }
    return true;
}

var getChatMessageWithHTML = function (pic, from, time, message) {
    var cm = '<li class="left "><span class="chat-img pull-left">' +
        '<img src="' + pic + '" alt="User Avatar" width="40px" height="40px"/>' +
        '</span>' +
        '<div class="chat-body clearfix">' +
        '   <div class="header">' +
        '        <strong class="primary-font">' + from + '</strong> <small class="pull-right text-muted">' +
        '            <span class="glyphicon glyphicon-time"></span>' + prettyDate(new Date(Number(time))) + '</small>' +
        '    </div>' +
        '    <p>' + message + '</p>' +
        '</div>' +
        '</li>';
    return cm;
}

function on_presence(presence) {
    var ptype = $(presence).attr('type');
    var from = $(presence).attr('from');
    var fromJID = "";
    if (from.indexOf("/") > -1) {
        fromJID = from.split("/")[0];
    } else {
        fromJID = from;
    }

    if (ptype == 'subscribe') {
        var iq = $iq({type: 'get'}).c('query', {xmlns: 'jabber:iq:roster'});
        connection.sendIQ(iq, function (iqq) {
            var exist = false;
            $(iqq).find('item').each(function () {
                var jid = $(this).attr('jid');
                if (jid.indexOf("/") > -1)
                    jid = jid.split("/")[0];
                if (jid == fromJID)
                    exist = true;

            });
            if (exist == false) {
                connection.send($pres({to: fromJID, "type": "subscribe"}));
            }
        });
        connection.send($pres({to: from, "type": "subscribed"}));
    }

    if (user.getUserName() != fromJID.split("@")[0]) {
        if (ptype === 'unavailable') {
            CHAT_FRIENDS_MAP.put(fromJID.split("@")[0], offlineStatus);
        } else {
            var show = $(presence).find("show").text();
            if (show === "" || show === "chat") {
                CHAT_FRIENDS_MAP.put(fromJID.split("@")[0], onlineStatus);
            } else {
                CHAT_FRIENDS_MAP.put(fromJID.split("@")[0], onlineStatus);
            }
        }
        $("#" + fromJID.split("@")[0] + "_status_img").html(CHAT_FRIENDS_MAP.get(fromJID.split("@")[0]));
    }
    return true;
}

$(document).on('keypress', "#chatbox", function (ev) {
    if (ev.which == 13) {
        var message = $msg({
            to: $.trim($(".cfli.selected").find("a").text()) + "@" + XMPPService,
            type: "chat"
        }).c("body").t($("#chatbox").get(0).value).up().c('active', {xmlns: "http://jabber.org/protocol/chatstates"});
        ;
        connection.sendIQ(message);
        $(this).parent().data('composing', false);
        var chatmessage = getChatMessageWithHTML(user.getProfilePic(), user.getUserName(), new Date().getTime(), $("#chatbox").val());
        $("#mCSB_1_container").append(chatmessage);
        saveChatMessage($("#chatbox").val(), user.getUserName(), $.trim($(".cfli.selected").find("a").text()));
        $("#chatbox").val("");
        $("#scrollarea").mCustomScrollbar("scrollTo", "bottom");

    } else {
        var composing = $(this).parent().data('composing');
        if (!composing) {
            console.log($.trim($(".cfli.selected").find("a").text()) + "@" + XMPPService);
            var notify = $msg({
                to: $.trim($(".cfli.selected").find("a").text()) + "@" + XMPPService,
                "type": "chat"
            }).c('composing', {xmlns: "http://jabber.org/protocol/chatstates"});
            connection.send(notify);
            $(this).parent().data('composing', true);
        }
    }
});

$(document).on('click', ".cfli", function () {
    $(".cfli").removeClass("selected");
    $(this).addClass("selected");
    $("#chatbox").attr("disabled", false);
    $("#chatbox").attr("placeholder", "Type your message here...");
    var otherparty = $(this).find("a").text();
    var otherpartypic = $(this).find("img").attr("src");
    $("#chattingwith").html(otherparty);
    $.ajax({
        type: "GET",
        url: BASE_URL + "chathistory.do",
        data: "user=" + user.getUserName() + "&otheruser=" + otherparty
    }).done(function (msg) {
        var jsonResponse = jQuery.parseJSON(msg);
        var fullChatHistory = "";
        for (var mi = 0; mi < jsonResponse.data.length; mi++) {

            var pic = jsonResponse.data[mi].from == user.getUserName() ? user.getProfilePic() : otherpartypic;
            var achatmessage = getChatMessageWithHTML(pic,
                jsonResponse.data[mi].from, jsonResponse.data[mi].time, jsonResponse.data[mi].message);
            fullChatHistory += achatmessage;
        }

        $("#mCSB_1_container").html(fullChatHistory);
        $("#scrollarea").mCustomScrollbar("scrollTo", "bottom");
    });
});

function checkWhetherUserIsOnline(username) {
    var img = CHAT_FRIENDS_MAP.get(username);
    if (img.indexOf("green") != -1) {
        return true;
    } else {
        return false;
    }
}

function populatesFrieldList() {
    if (chatConnected == false) {
        setTimeout(populatesFrieldList, 500);
        return;
    }

    var getParamList = "";
    for (var i = 0; i++ < CHAT_FRIENDS_MAP.size; CHAT_FRIENDS_MAP.next()) {
        getParamList += CHAT_FRIENDS_MAP.key() + "$$";
    }

    $.ajax({
        type: "GET",
        url: BASE_URL + "usersdataforinbox.do",
        data: "userslist=" + getParamList
    }).done(function (msg) {
        var jsonResponse = jQuery.parseJSON(msg);
        friendsprofilepics = jsonResponse.picmap;
        var currFriendList = "";
        for (var i = 0; i++ < CHAT_FRIENDS_MAP.size; CHAT_FRIENDS_MAP.next()) {
            var friend = CHAT_FRIENDS_MAP.key();
            var status = CHAT_FRIENDS_MAP.value();
            var friendItem = '<div class="list-group-item contactitem cfli" >' +
                '<div class="row">' +
                '<div class="col-md-4">' +
                '<img src="' + jsonResponse.picmap[friend] + '" alt="Scott Stevens" class="img-responsive" />' +
                '</div>' +
                '<div class="col-md-7" >' +
                '<div class="row"><a href="#" style="margin-left:3px;">' + friend + '<span id="' + friend + '_status_img" class="pull-left">' + status + '</span></a></div>' +
                '<div class="row"><small>Bye</small></div>' +
                '</div>' +
                '</div>' +
                '</div>';
            currFriendList = currFriendList + friendItem;
            CHAT_FRIENDS_LI.put(friend, friendItem);
        }
        $("#inboxcontactlist").html(currFriendList);
    });
}


function saveChatMessage(chatmessage, from, to) {
    var currD = new Date();
    var type = "OFFLINE";
    if (checkWhetherUserIsOnline(to)) {
        type = "ONLINE";
    }
    $.ajax({
        type: "GET",
        url: BASE_URL + "savechatmessage.do",
        data: "from=" + from + "&to=" + to + "&time=" + currD.getTime() + "&message=" + chatmessage + "&type=" + type
    }).done(function (msg) {
        console.log(msg);
    });
}

function on_roster(iq) {
    var query = $(iq).find('query');
    friendlist = new Array();
    $(iq).find('item').each(function () {
        var jid = $(this).attr('jid');
        CHAT_FRIENDS_MAP.put(jid.split("@")[0], offlineStatus);
    });
    connection.addHandler(on_presence, null, "presence");
    connection.send($pres());
    chatConnected = true;
    return true;
}

function onConnect(status) {
    if (status == Strophe.Status.CONNECTING) {
        console.log('Strophe is connecting.');
    } else if (status == Strophe.Status.CONNFAIL) {
        console.log('Strophe failed to connect.');
    } else if (status == Strophe.Status.DISCONNECTING) {
        console.log('Strophe is disconnecting.');
    } else if (status == Strophe.Status.DISCONNECTED) {
        console.log('Strophe is disconnected.');
        console.log("Disconnected:" + connection.jid);
    } else if (status == Strophe.Status.CONNECTED) {
        console.log('Strophe is connected.');
        var iq = $iq({type: 'get'}).c('query', {xmlns: 'jabber:iq:roster'});
        connection.sendIQ(iq, on_roster);
        connection.addHandler(on_message, null, "message", "chat");
    }
}

function signInOnChat() {
    connection = new Strophe.Connection(BOSH_SERVICE);
    connection.connect(user.getUserName() + '@' + XMPPService, user.getChatPass(), onConnect);
}

function signOutOnChat() {
    if (connection != null) {
        connection.options.sync = true;
        connection.flush();
        connection.disconnect();
    }
}

function showMessageBox() {

    $("#scrollarea").mCustomScrollbar("scrollTo", "bottom");
    populatesFrieldList();
    $("#chatbox").attr("disabled", true);

    $(document).on("keyup", "#contactsearch", function (ev) {
        /*var currFriendList = "";
         for(var i = 0; i++ < CHAT_FRIENDS_MAP.size; CHAT_FRIENDS_MAP.next()){
         var friend = CHAT_FRIENDS_MAP.key();
         var status = CHAT_FRIENDS_MAP.value();
         if( friend.match($(this).val()) != null)
         {
         var friendItem = '<div class="list-group-item contactitem cfli" >'+
         '<div class="row">'+
         '<div class="col-md-4">'+
         '<img src="'+friendsprofilepics[friend]+'" alt="Scott Stevens" class="img-responsive" />'+
         '</div>'+
         '<div class="col-md-7" >'+
         '<div class="row"><a href="#">'+friend+' <span id="'+friend+'_status_img" class="pull-right">'+status+'</span></a></div>'+
         '<div class="row"><small>Bye</small></div>'+
         '</div>'+
         '</div>'+
         '</div>';
         currFriendList = currFriendList+ friendItem;
         CHAT_FRIENDS_LI.put(friend,friendItem);
         }
         }
         $("#inboxcontactlist").html(currFriendList);*/

        $("#inboxcontactlist").html("");
        var searchLiList = "";
        for (var i = 0; i++ < CHAT_FRIENDS_LI.size; CHAT_FRIENDS_LI.next()) {
            var friend = CHAT_FRIENDS_LI.key();
            var li = CHAT_FRIENDS_LI.value();
            if (friend.match($(this).val()) != null)
                searchLiList += li;
        }
        $("#inboxcontactlist").html(searchLiList);
    });
}