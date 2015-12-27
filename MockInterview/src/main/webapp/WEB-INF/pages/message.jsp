<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Message</title>
</head>
<body>

<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <div class="list-group list-default" id="sidenav">
                        <a href="javascript:void(0);" id="nav-allmessage" class="list-group-item active">All
                            Messages</a>
                        <a href="<c:url value='newmessage.do'/>" id="nav-newmessage" class="list-group-item">New
                            Message</a>
                    </div>
                </div>
                <div class="col-md-9">
                    <h1 style="margin-top:0px" id="heading">Messages</h1>
                    <div id="message"></div>
                    <hr/>
                    <section id="msgSection">
                        <div class="clearfix white-container" id="msgdetail"></div>
                    </section>
                    <section id="subMsgSection" style="display: none;">
                        <div class="clearfix white-container" id="submsgdetail"></div>
                    </section>
                    <section id="chatMessage" style="display: none;">
                        <div class="clearfix white-container" id="chatMessagedetail"></div>
                    </section>
                    <ul class="pagination pull-right" id="message_pagination">
                    </ul>


                </div>

            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>

<script type="text/javascript">
    var id = "${jobid}";
    var messageto = "${job.interviewer}";
    var msgfrom = "${username}";
    var length = "";
    $(function () {

        $("#date").html(prettyDate(new Date(Number(${job.dt}))));
        $("#postedByRating").rating({clearButton: "", showCaption: false});

        $(document).on("click", ".transpage", function (event) {
            event.preventDefault();
            loadMessages($(this).text());
        });

        $(document).on('click', '#nav-allmessage', function () {
            loadMessages(1);
            $("#chatMessagedetail").html('');
            $("#heading").html('Messages');
            $("#message").html('');
            $("#chatMessage").hide();
            $("#nav-allmessage").addClass("active");            
        });
        loadMessages();
    });

    var loadMessages = function (pagenum) {
        if (pagenum == null || pagenum == undefined) {
            pagenum = 1;
        }
        var param = "pagenum=" + pagenum;
        $.ajax({
            type: 'GET',
            url: BASE_URL + 'getmessage.do',
            data: param,
            async: false
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            var jobs = resData.MESSAGE_LIST;
            var jobsHtml = "";

            $("#message_pagination").html("");
            if (Number(resData.MESSAGE_LIST_COUNT) > 10) {
                var pagination = "";
                var totalpages = Number(resData.MESSAGE_LIST_COUNT) / 10;
                for (var i = 0; i < totalpages; i++) {
                    if (pagenum == null || pagenum == undefined && i == 0) {
                        pagination = pagination + '<li class="active"><a class="transpage" href="javascript:void(0)">' + (i + 1) + '</a></li>';
                    } else if (pagenum == i + 1) {
                        pagination = pagination + '<li class="active"><a class="transpage" href="javascript:void(0)">' + (i + 1) + '</a></li>';
                    } else {
                        pagination = pagination + '<li><a class="transpage" href="javascript:void(0)">' + (i + 1) + '</a></li>';
                    }
                }
                $("#message_pagination").html(pagination);
            }


            if (null != jobs && jobs.length > 0) {
                length = Number(jobs.length);
                jobsHtml = '<table class="table table-striped"><thead><tr><th>With</th><th>Message</th><th>Date</th></tr></thead><tbody>';
                for (var i = 0; i < jobs.length; i++) {
                	var messageWith = null;
                    var to = jobs[i].to;
                    var from = jobs[i].from;
                    var message = jobs[i].message;
                    var labelNew = "";
                    if (LOGIN_USER == to) {
                        to = "Me";
                        messageWith = from;
                        if (jobs[i].status == "UNREAD") {
                            labelNew = "&nbsp;&nbsp;<label class='label label-success'>New</label>";
                            message='<strong>'+message+'</strong>';
                        }
                    } 
					if (LOGIN_USER == from) {
                        from = "Me";
                        messageWith = to; 
                    }
					var from1="'"+jobs[i].from+"'";
					var to1="'"+jobs[i].to+"'";
                    jobsHtml += '<tr style="cursor:pointer;" onclick="getChat('+from1+','+to1+')"><td>' + messageWith +labelNew + '</td><td>' + message + '</td><td>' + prettyDate(new Date(jobs[i].creationDate)) + '</td></tr>';
                }
                jobsHtml += '</tbody></table>';
            } else {
                jobsHtml = "No message found.";
            }
            $("#msgdetail").html(jobsHtml);
            $("#subMsgSection").hide();
            $("#msgSection").show();
            loadNewMessageCount();
        });
    }

    function loadSubMessage(id) {

        $.ajax({
            type: 'GET',
            url: BASE_URL + 'getsubmessage.do',
            data: "parentMessageId=" + id,
            async: false
        }).done(function (res) {

            var resData = jQuery.parseJSON(res);
            var jobs = resData.SUB_MESSAGE_LIST;
            var msgHtml = "";

            if (null != jobs && jobs.length > 0) {
                length = Number(jobs.length);

                for (var i = 0; i < jobs.length; i++) {

                    var classIn = "";
                    if (i == (jobs.length - 1)) {
                        classIn = " ";
                    }

                    var to = jobs[i].to;
                    var from = jobs[i].from;
                    var labelNew = "";
                    if (LOGIN_USER == to) {
                        to = "Me";
                        if (jobs[i].status == "UNREAD") {
                            labelNew = "&nbsp;&nbsp;<label class='label label-success'>New</label>";
                        }
                    }
                    if (LOGIN_USER == from) {
                        from = "Me";
                    }

                    msgHtml += '<div class="panel panel-default panel-message">' +
                            '<div class="panel-heading">' +
                            '<h4 class="panel-title">' +
                            '<a onclick = "changestatus(\'' + jobs[i].id + '\',\'' + jobs[i].status + '\',\'' + jobs[i].to + '\')" data-toggle="collapse" data-parent="#accordion" href="#subcollapse' + i + '">' +
                            ' To: '
                            + to + ' From: ' + from + labelNew + ' </a>' +
                            '<span class="pull-right">' + prettyDate(new Date(jobs[i].creationDate)) + '</span>' +
                            '</h4>' +
                            '</div>' +
                            '<div id="subcollapse' + i + '" class="panel-collapse collapse ' + classIn + ' ">' +
                            '<div class="panel-body">' +
                            '<p>' + jobs[i].message + '</p>' +
                            '</div>' +
                            '</div>' +
                            '</div>';

                    if (i == (jobs.length - 1)) {
                        msgHtml += '<div class="clearfix mt20">' +
                                '<label class="col-md-2">To</label>' +
                                '<div class="col-md-10 form-control-static">' + jobs[i].to + '</div>' +
                                '<form class="form form-horizontal" id="submessageForm">' +
                                '<div class="form-group">' +
                                '<label class="col-md-2">Message:</label>' +
                                '<div class="col-md-10">' +
                                '<textarea name="message" id="inputmessage" rows="5" cols="80" class="form-control"></textarea>	' +
                                '</div>' +
                                '</div>' +
                                '<input type="hidden" name="jobid" value="' + jobs[i].refId + '"/>' +
                                '<input type="hidden" name="jobtitle" value="' + jobs[i].title + '"/>';
                        if (jobs[i].parentMessageId == "") {
                            msgHtml += '<input type="hidden" name="parentMessageId" id="parentMessageId" value="' + jobs[i].id + '"/>';
                        }
                        else {
                            msgHtml += '<input type="hidden" name="parentMessageId" id="parentMessageId" value="' + jobs[i].parentMessageId + '"/>';
                        }
                        msgHtml += '<input type="hidden" name="type" id="type" value="REPLY"/>' +
                                '<input type="hidden" name="from" value="' + jobs[i].from + '"/>' +
                                '<input type="hidden" name="to" value="' + jobs[i].to + '"/>' +
                                '<input type="hidden" name="refentity" value="' + jobs[i].refEntity + '"/>' +
                                '<div class="form-group">' +
                                '<div class="col-md-offset-2 col-md-8">' +
                                '<button type="button" class="btn btn-default" id="sendmessage">Send</button>' +
                                '&nbsp;<button type="button" onclick="loadMessages()" class="btn btn-default" >Back</button>' +
                                '</div>' +
                                '</div>' +
                                '</div>';
                    }
                }
            }

            $("#submsgdetail").html(msgHtml);
            $("#subMsgSection").show();
            $("#msgSection").hide();
            $("#message_pagination").hide();

        });
    }

    function changestatus(id, status, to) {
        if (status == "UNREAD" && LOGIN_USER == to) {
            $.ajax({
                type: 'GET',
                url: BASE_URL + 'changestatus.do',
                data: "id=" + id,
                async: false
            }).done(function (res) {

            });
        }
    }

    function submitForm() {
        $.ajax({
            type: 'POST',
            url: BASE_URL + 'jobmessage.do',
            data: $("#jobDetailContactForm").serialize(),
            success: function (res) {
                var jobsHtml = '<div class="panel panel-default">' +
                        '<div class="panel-heading">' +
                        '<h4 class="panel-title">' +
                        '<a data-toggle="collapse" data-parent="#accordion" href="#collapse' + length + '"><i class="fa fa-plus"></i> ' + msgfrom + '</a>' +
                        '<span class="pull-right">' + prettyDate(new Date()) + '</span>' +
                        '</h4>' +
                        '</div>' +
                        '<div id="collapse' + length + '" class="panel-collapse collapse">' +
                        '<div class="panel-body">' +
                        '<p>' + $("#inputmessage").val() + '</p>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                $("#msgdetail").append(jobsHtml)
                length++;
                console.log(res);
            },
            error: function (status) {

            }
        });
    }

    $(document).on('click', '#sendmessage', function () {
        $.ajax({
            type: 'GET',
            url: BASE_URL + 'sendmessage.do',
            data: $("#submessageForm").serialize(),
            async: false
        }).done(function (res) {
            showSuccess("Message sent successfully.");
            message("Message sent successfully.","success");
            $("#inputmessage").val("");
        });
    });
    
    function getChat(from,to){
        var user = '';
        var toUser = '';
        if(LOGIN_USER==from){
            user=to;
        }
        if(LOGIN_USER==to){
            user=from;
        }
        $.ajax({
            type: 'GET',
            url: BASE_URL + 'getmessagebetween.do',
            data: "user=" + user,
            async: false
        }).done(function (res) {
            //$("#message").html("Between : "+from+" vs "+to);
            var resData = jQuery.parseJSON(res);
            var messages = resData.MESSAGE_LIST;
            var data = '';
            for (var i = messages.length-1; i >= 0; i--) {
                
                if (messages[i].from === LOGIN_USER) {
                    CSS_Class = "panel-warning";
                } else {
                    CSS_Class = "panel-danger";
                }

                var messagetrail = '<div class="panel ' + CSS_Class + '">' +
                        '<div class="panel-heading">' +
                        '  <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp' + messages[i].from +
                        '</span><span class="pull-right "><h5 style="margin-top:1px">' +
                        prettyDate(new Date(Number(messages[i].lastReplyToDate))) + '</h5></span></h3>' +
                        '</div>' +
                        '<div class="panel-body">' + messages[i].message +
                        '</div></div>';
                data+=messagetrail;                
            }

            data+='<div id="yourmesssages"></div><div id="chatForm"></div>';
            var messagetrail1 = '<div class="panel panel-warning">' +
            '<div class="panel-heading">' +
            '  <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp' +
            LOGIN_USER + '</span><span class="pull-right"></span></h3>' +
            '</div>' +
            '<div class="panel-body" style="padding:5px;">' +
            '<form class="form form-horizontal" id="newmessageForm">'+
            '<textarea class="form-control input-sm" style="margin:0 0 10px" id="send_message" name="message"></textarea>' +
            '<input type="hidden" name="to" value="'+user+'"/>'+
            '<input type="hidden" name="jobid" value=""/>'+
            '<input type="hidden" name="jobtitle" value=""/>'+
            '<input type="hidden" name="parentMessageId" value=""/>'+
            '<input type="hidden" name="refentity" value=""/>'+
            '<div class="row">' +
            '	<div class="col-md-5 pull-right">'+
            '<img id="loader" style="display: none;" alt="Processing..." src=" /resources/img/loading.gif ">'+
            '<button type="button" onclick="submitForm();" id="sendMessage" class="btn btn-info pull-right">Send Message</button></div>' +
            '</form>'
            '</div>' +
            '</div>' +
            '</div>';
            
            
            $("#chatMessagedetail").html(data+messagetrail1);
            $("#heading").html(user);
            $("#chatMessage").show();
            $("#msgSection").hide();
            $("#nav-allmessage").removeClass("active");
            loadNewMessageCount();
        });
    }
    function submitForm() {
        $("#loader").show();
        $.ajax({
            type: 'POST',
            url: BASE_URL + 'sendnewmessage.do',
            data: $("#newmessageForm").serialize(),
            async: false
        }).done(function (res) {
            $("#contactmeModal").modal("hide");
            showSuccess("Your message has been sent.");
            var temp = '<div class="panel panel-warning">' +
            '<div class="panel-heading">' +
            '  <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp' + LOGIN_USER +
            '</span><span class="pull-right "><h5 style="margin-top:1px">' +
            prettyDate(new Date()) + '</h5></span></h3>' +
            '</div>' +
            '<div class="panel-body">' + $("#send_message").val() +
            '</div></div>';
            $( temp ).insertBefore( "#yourmesssages" );
            $("#send_message").val("");
        }).always(function (jqXHR, textStatus) {
            $("#loader").hide();
        });
    }
</script>
</body>
</html>
