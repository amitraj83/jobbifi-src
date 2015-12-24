<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Disputes</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div id="wrapper">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <%@ include file="/WEB-INF/pages/myInterviewSidebar.jsp" %>
            </div>

            <div class="col-md-9" id="interviewee_maincontent">
                <div class="row">
                    <div class="col-md-12" id="interviewee_maincontent">
                        <div class="row">
                            <h1>Interview Disputes</h1>
                            <div id="message"></div>
                        </div>
                    </div>

                    <div class="responsive-tabs">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="active"><a href="#currentdisputes" role="tab" data-toggle="tab">Current
                                Disputes</a></li>
                            <li><a href="#createdispute" role="tab" data-toggle="tab">Create Dispute</a></li>
                            <li><a href="#closeddisputes" role="tab" data-toggle="tab">Closed Disputes</a></li>
                        </ul>

                        <div class="tab-content">

                            <!-- Dispute Tab -->
                            <div class="tab-pane fade in" id="createdispute">
                            	<br/>
                                <form class="form form-horizontal" name="disputeform" id="disputeform">
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Select Interview</label>

                                        <div class="col-md-8">
                                            <select class="form-control" name="disputeInterview"
                                                    id="disputable_interviews">
                                                <option value="">Select an interview for your dispute</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Upload Document</label>

                                        <div class="col-md-8">
                                            <input class="hide" id="disputedoc" type="file" name="file"
                                                   data-url="aauth/fileupload.do?type=disputedocument" multiple
                                                   style="opacity:0;  filter:alpha(opacity: 0);"
                                                   data-sequential-uploads="true">

                                            <div>
                                                <button type="button" class="btn btn-sm btn-default" id="attachifile">
                                                    Attach a file
                                                </button>
                                                <img id="fileloader" style="display: none;" alt="Processing..."
                                                     src="<c:url value="/resources/img/loading.gif" />">
                                            </div>
                                            <div id="selectedifile"></div>
                                            <input type="hidden" id="disputedocid" name="disputefiles[]"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label">Your Message:</label>

                                        <div class="col-md-8">
                                            <textarea name="disputeMessage" id="disputemessage" class="form-control"
                                                      rows="7" cols="100"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-offset-4 col-md-8">
                                            <button type="submit" class="btn btn-default" id="submitdispute">Create
                                                Dispute
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!-- Dispute Tab -->

                            <!-- Current Dispute L<i class="fa fa-inr"></i>ist -->
                            <div class="tab-pane fade in active" id="currentdisputes">
                                <br/>
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>Dispute Id</th>
                                        <th>Interview</th>
                                        <th>Dispute with</th>
                                        <th>Amount</th>
                                        <th>Result</th>
                                    </tr>
                                    </thead>
                                    <tbody id="currentdisputestable"></tbody>
                                </table>
                            </div>
                            <!-- Dispute List -->

                            <!-- Close Dispute List -->
                            <div class="tab-pane fade in" id="closeddisputes">
                                <br/>
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>Dispute Id</th>
                                        <th>Interview</th>
                                        <th>Dispute with</th>
                                        <th>Amount</th>
                                        <th>Result</th>
                                        <th>Date Closed</th>
                                    </tr>
                                    </thead>
                                    <tbody id="closeddisputestable"></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">

    var status = "${status}";
    var USER_ROLE = "${userRole}";
    var closedispute;
    var result;

    $(function () {
        $("#nav-dispute").addClass("active");
        loadDisputeList();

        $("#disputeform").validate({
            rules: {
                disputeInterview: {required: true},
                disputeMessage: {required: true}
            },
            submitHandler: function (form) {
                submitDisputeForm();
                return;
            }
        });

        /* document upload */
        $("#attachifile").click(function () {
            $("#disputedoc").trigger('click');
        });

        $('#disputedoc').fileupload({
            dataType: 'json',
            maxChunkSize: 20000000,
            done: function (e, data) {
                var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
                if (jsonResponse && jsonResponse._id) {
                    var html = "<a target='_blank' href='" + BASE_URL + jsonResponse.url + "' >" + jsonResponse.originalfn + "</a><br/>";
                    $("#selectedifile").html(html);
                    $("#disputedocid").val(jsonResponse._id);
                } else {
                    //showError("Unable to upload the file.");
                	message("Unable to upload the file.","danger");
                }
                $("#fileloader").hide();
            },
            add: function (e, data) {
                data.submit();
                $("#fileloader").show();
            }
        });
    });

    function loadDisputeList() {
        $.ajax({
            type: "GET",
            url: BASE_URL + "getDisputeList.do",
            async: false<i class="fa fa-inr"></i>
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            if (resData.disputeList.length > 0) {
                for (var i = 0; i < resData.disputeList.length; i++) {
                    var html = '<tr class="disputerow" did="' + resData.disputeList[i].id + '">' +
                            '<td><a href="javascript:void(0)"># ' + resData.disputeList[i].visibleID + '</a></td>' +
                            '<td>' + resData.disputeList[i].title + '</td>' +
                            '<td>' + resData.disputeList[i].with + '</td>' +
                            '<td><i class="fa fa-inr"></i>' + resData.disputeList[i].amount + '</td>' +
                            '<td>' + resData.disputeList[i].result + '</td>' +
                            '</tr>';
                    $("#currentdisputestable").append(html);
                }
            } else {
                var noresult = "<tr><td colspan='5'>No dispute found.</td></tr>";
                $("#currentdisputestable").append(noresult);
            }
        });

        /* disputable mock list */
        $.ajax({
            type: "GET",
            url: BASE_URL + "getdisputableList.do",
            async: false
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            for (var i = 0; i < resData.disputable_list.length; i++) {
                $("#disputable_interviews").append('<option data-eb="' + resData.disputable_list[i].eb
                        + '" value="' + resData.disputable_list[i].id + '">' + resData.disputable_list[i].title + '</option>');
            }
        });

        /* closed dispute list */
        $.ajax({
            type: "GET",
            url: BASE_URL + "closeddisputelist.do"
        }).done(function (msg) {
            var resData = jQuery.parseJSON(msg);
            if (resData.closed_dispute_List.length > 0) {
                for (var i = 0; i < resData.closed_dispute_List.length; i++) {
                    if (resData.closed_dispute_List[i].closedBy == "") {
                        result = resData.closed_dispute_List[i].result
                    } else if (resData.closed_dispute_List[i].closedBy == LOGIN_USER) {
                        result = "LOST";
                    } else {
                        result = "WIN";
                    }

                    var html = '<tr class="disputerow" did="' + resData.closed_dispute_List[i].id + '"> ' +
                                    '            <td><a href="javascript:void(0)"># ' + resData.closed_dispute_List[i].visibleID + '</a> </td>' +
                                    '            <td> ' + resData.closed_dispute_List[i].title + ' </td>' +
                                    '            <td> ' + resData.closed_dispute_List[i].with + ' </td>' +
                                    '            <td><i class="fa fa-inr"></i> ' + resData.closed_dispute_List[i].amount + ' </td>' +
                                    '            <td> ' + result + '</td>' +
                                    '            <td> ' + prettyDate(new Date(resData.closed_dispute_List[i].timeclosed)) + ' </td>' +
                                    '        </tr>'
                            ;
                    $("#closeddisputestable").append(html);
                }
            } else {
                var noresult = "<tr><td colspan='6'>No dispute found.</td></tr>";
                $("#closeddisputestable").append(noresult);
            }
        });
    }

    /* submit dispute */
    function submitDisputeForm() {

        var iid = $("#disputable_interviews option:selected").val();
        var eb = $("#disputable_interviews option:selected").attr("data-eb");
        var disputefileid = $("#disputedocid").val();
        var disputeMessage = $("#disputemessage").val();

        $.ajax({
            type: "POST",
            url: BASE_URL + "createdispute.do",
            data: "iid=" + iid + "&fileid=" + disputefileid + "&msg=" + disputeMessage + "&amount=" + eb
        }).done(function (msg) {<i class="fa fa-inr"></i>
            var jsonResponse = jQuery.parseJSON(msg);
            if (jsonResponse.status == "1") {
                $("#currentdisputestable").append(
                        '<tr class="disputerow" did="' + jsonResponse.id + '"> ' +
                        '<td><a href="javascript:void(0)"># ' + jsonResponse.vid + '</a></td>' +
                        '<td>' + jsonResponse.interview + '</td>' +
                        '<td>' + jsonResponse.disputewith + '</td>' +
                        '<td><i class="fa fa-inr"></i>' + jsonResponse.amount + '</td>' +
                        '<td>PENDING</td>' +
                        '<td>-</td>' +
                        '</tr>');

                message("You have successfully created the dispute.","success")
            } else if (jsonResponse.status == "2") {
                //showWarning("The interview is already in the dispute status.");
                message("The interview is already in the dispute status.","warning");
            } else {
                //showError("Sorry, You cannot raise the dispute at the moment.");
                message("Sorry, You cannot raise the dispute at the moment.","danger");
            }

            $("#disputemessage").val("");
            $("#disputable_interviews").val('');
            $("#selectedifile").html("");
            $("#interviewdocid").val("");
        });
    }


    /* dispute detail window */
    $(document).on("click", ".disputerow", function () {

        $(".interviewee_tabs").hide();

        var did = $(this).attr("did");
        var dwith = $(this).children().eq(2).text();
        var damount = $(this).children().eq(3).text();

        $.ajax({
            type: "GET",
            url: BASE_URL + "retrievedispute.do",
            data: "did=" + did
        }).done(function (msg) {

            var jsonResponse = jQuery.parseJSON(msg);
            var status = jsonResponse.disputeStatus;
            var iid = jsonResponse.iid;
            var actionbutton = "";
            var closedispute = "";
            var dresult = "";

            if (status == "OPEN") {
                if (USER_ROLE == "INTERVIEWER") {
                    closedispute = "interviewerclosingdispute";
                } else {
                    closedispute = "intervieweeclosingdispute";
                }
                dresult = "PENDING";
                actionbutton = "Close and Release Funds";
            } else {
                dresult = jsonResponse.disputeResult;
            }

            var screen = getAction(jsonResponse, actionbutton, closedispute, dresult, damount, did, status);
            $("#interviewee_maincontent").html(screen);
            var CSS_Class = "";
            if (status != "OPEN") {
                $("#interviewerclosingdispute").hide();
            }

            $.each(jsonResponse.list, function (id, data) {

                var fid = data.fid;
                var attachment = "";
                if ((fid != null) && (fid != "")) {
                    attachment = '<br><a target="_blank" href="' + jsonResponse[fid].url + '">' + jsonResponse[fid].fn + '</a>';
                }

                if (jsonResponse.username === data.messageBy) {
                    CSS_Class = "panel-warning";
                } else {
                    CSS_Class = "panel-danger";
                }
                var messagetrail = '<div class="panel ' + CSS_Class + '">' +
                        '<div class="panel-heading">' +
                        '  <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp' + data.messageBy +
                        '</span><span class="pull-right "><h5 style="margin-top:1px">' +
                        prettyDate(new Date(Number(data.time))) + '</h5></span></h3>' +
                        '</div>' +
                        '<div class="panel-body">' + data.message + attachment +
                        '</div></div>';

                $("#messagetrail").append(messagetrail);
            });

            $("#messagetrail").append('<div id="yourmesssages"></div><div id="disputemessageform"></div>');
            var messagetrail = '<div class="panel panel-warning">' +
                    '<div class="panel-heading">' +
                    '  <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp' +
                    jsonResponse.createdby + '</span><span class="pull-right"></span></h3>' +
                    '</div>' +
                    '<div class="panel-body" style="padding:5px;">' +
                    '<textarea class="form-control input-sm" style="margin:0 0 10px" id="dispute_message"></textarea>' +
                    '<div class="row">' +
                    '	<div class="col-md-7"><div class="form-group">' +
                    '<input id="disputemessagedoc" type="file" name="file" data-url="aauth/fileupload.do?type=disputedocument" ' +
                    'multiple style="opacity:0; filter:alpha(opacity: 0);display:none">' +
                    '<div><button id="disputemessagebtn" type="button" class="btn btn-sm btn-default">Upload</button></div>' +
                    '<div id="selecteddisputemessagefile"></div><input type="hidden" id="disputemessagedocid">' +
                    '</div></div>' +
                    '	<div class="col-md-5"><button type="submit" did="' + did + '" id="senddisputemessage" class="btn btn-info pull-right">Send Message</button></div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';

            if (status == "OPEN") {

                $("#disputemessageform").html(messagetrail);
                $('#disputemessagebtn').click(function () {
                    $("#disputemessagedoc").trigger('click');
                });

                $('#disputemessagedoc').fileupload("destroy");
                $('#disputemessagedoc').fileupload({
                    dataType: 'json',
                    maxChunkSize: 20000000,
                    done: function (e, data) {
                        var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
                        $("#selecteddisputemessagefile").html(jsonResponse.originalfn)
                        $("#disputemessagedocid").val(jsonResponse._id);
                    },
                });

            } else {

                $("#intervieweeclosingdispute").hide();
                var closingnote = '<div class="panel panel-danger">' +
                        '	  <div class="panel-heading">' +
                        '    <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp;Admin</span>' +
                        '<span class="pull-right "><h5 style="margin-top:1px">' +
                        prettyDate(new Date(jsonResponse.timeclosed)) + '</h5></span></h3>' +
                        '  </div>' +
                        '  <div class="panel-body">' +
                        '  This dispute has been resolved and closed.' +
                        '  </div>' +
                        '</div>';
                $("#disputemessageform").html(closingnote);
            }
        });
        $("#interviewee_maincontent").html($("#a_dispute").html());
    });

    function getAction(jsonResponse, actionbutton, closedispute, dresult, damount, did, status) {
        var _dispute = '<div id="a_dispute" class="interviewee_tabs white-container">' +
                '<h1 class="title" style="text-transform:capitalize;">' + jsonResponse.title + '</h1>' +
                '<h4>Dispute between: <span style="text-transform:capitalize">' + jsonResponse.createdby + '</span> vs <span style="text-transform:capitalize">' + jsonResponse.disputewith + '</span>&nbsp;&nbsp;(<span style="color:green;" id="headingdstatus">' + status + '</span>)</h4>' +
                '<div class="row">' +
                '<div class="col-md-8" id="messagetrail"></div>' +
                '<div class="col-md-4">' +
                '<div class="panel panel-primary">' +
                '<div class="panel-heading" >';
        if (actionbutton != "") {
            _dispute = _dispute + '<h3 class="panel-title">Dispute Amount: <i class="fa fa-inr"></i>' + damount +
                    '<br><br>Result: <span id="disputesidebosresult">' + dresult + '</span><br><br><button did="' + did +
                    '" id="' + closedispute + '" type="button" class="btn btn-success" >Close and Release Funds</button></h3>';
        } else {
            _dispute = _dispute + '<h3 class="panel-title">Dispute Amount : <i class="fa fa-inr"></i>' + damount + '<br><br>Result: <span id="disputesidebosresult">' + dresult + '</span></h3>';
        }
        _dispute = _dispute + '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>';
        return _dispute;
    }

    /* send dispute message for the current dispute */
    $(document).on('click', '#senddisputemessage', function () {
        var did = $(this).attr("did");
        var fid = $("#disputemessagedocid").val();
        var msg = $("#dispute_message").val().replace(/\n/g, '<br />');
        $.ajax({
            type: "GET",
            url: BASE_URL + "senddisputemessage.do",
            data: "did=" + did + "&fid=" + fid + "&msg=" + msg
        }).done(function (msg) {
            var jsonResponse = jQuery.parseJSON(msg);

            if (jsonResponse.status == "1") {
                var fid = jsonResponse.message.fid;
                var attachment = "";

                if ((fid != null) && (fid != "")) {
                    attachment = '<br><a href="' + jsonResponse[fid].url + '">' + jsonResponse[fid].fn + '</a>';
                }

                var messagetrail = '<div class="panel panel-warning">' +
                        '<div class="panel-heading" >' +
                        '<h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp' + jsonResponse.message.messageBy + '</span><span class="pull-right "><h5 style="margin-top:1px">' + prettyDate(new Date(Number(jsonResponse.message.time))) + '</h5></span></h3>' +
                        '</div>' +
                        '<div class="panel-body">' + jsonResponse.message.message + attachment +
                        '</div></div>';

                $("#dispute_message").val("");
                $("#selecteddisputemessagefile").html("");
                $("#disputemessagedocid").val("");
                $("#yourmesssages").append(messagetrail);
            }
        });
    });


    $(document).on('click', '#interviewerclosingdispute', function () {
        if (confirm("Your will loose your dispute and cannot be reverted. Are you sure?") == true) {
            var did = $("#interviewerclosingdispute").attr("did");
            $.ajax({
                type: "GET",
                url: BASE_URL + "interviewerclosingdispute.do",
                data: "did=" + did
            }).done(function (msg) {
                var jsonResponse = jQuery.parseJSON(msg);
                $("#disputetrail").html(jsonResponse.message);
                $("#dmsgbox").html("");
            });
        }
    });


    $(document).on('click', '#intervieweeclosingdispute', function () {
        var element = $(this);
        var answer = confirm("Your will loose your dispute funds and cannot be reverted. Are you sure?")
        if (answer) {
            var did = $(this).attr("did");
            $.ajax({
                type: "GET",
                url: BASE_URL + "intervieweeclosingdispute.do",
                data: "did=" + did
            }).done(function (msg) {
                var jsonResponse = jQuery.parseJSON(msg);
                var closingnote = '<div class="panel panel-danger">' +
                        '	  <div class="panel-heading">' +
                        '    <h3 class="panel-title"><span><i class="fa fa-user"></i>&nbsp;Admin</span><span class="pull-right "><h5 style="margin-top:1px">' + prettyDate(new Date()) + '</h5></span></h3>' +
                        '  </div>' +
                        '  <div class="panel-body">' +
                        '  This dispute has been resolved and closed.' +
                        '  </div>' +
                        '</div>';
                $("#disputemessageform").html(closingnote);
                $("#headingdstatus").html("CLOSED");
                $("#disputesidebosresult").html("LOST");
                $(element).hide();
            });
        }
    });
    
    $(document).ready(function () {
    	$(".acc-link").hide();
    });
</script>
</body>
</html>
