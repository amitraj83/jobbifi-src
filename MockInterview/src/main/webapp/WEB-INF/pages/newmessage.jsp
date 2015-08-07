<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>New Message</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-3 page-sidebar">
                    <div class="list-group list-default" id="sidenav">
                        <a href="<c:url value='message.do'/>" id="nav-allmessage" class="list-group-item">All
                            Messages</a>
                        <a href="javascript:void(0);" id="nav-newmessage" class="list-group-item active">New Message</a>
                    </div>
                </div>
                <div class="col-md-9 page-content">
                    <h1 style="margin-top:0px">Send Message</h1>
                    <hr/>
                    <section id="newMsgSection">
                        <div class="clearfix white-container">
                            <form class="form form-horizontal" id="newmessageForm" name="messageform">
                                <div class="form-group">
                                    <label class="col-md-2 ">To:</label>

                                    <div class="col-md-8">
                                        <select class="form-control" name="to" id="contact_list">
                                            <option value="">Select a Friend from list</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2">Message:</label>

                                    <div class="col-md-8">
                                        <textarea name="message" id="inputmessage" rows="5" cols="80"
                                                  class="form-control"></textarea>
                                    </div>
                                    <input type="hidden" name="jobid" value=""/>
                                    <input type="hidden" name="jobtitle" value=""/>
                                    <input type="hidden" name="parentMessageId" value=""/>
                                    <input type="hidden" name="refentity" value=""/>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-2 col-md-8">
                                        <button type="submit" id="newmessage" class="btn btn-default">Send</button>
                                        <img id="newmessageloader" style="display: none;" alt="Processing..."
                                             src="<c:url value="/resources/img/loading.gif" />">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </section>
                </div>

            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>

<script type="text/javascript">
    $(function () {
        loadcontactlist();
        $("#newmessageForm").validate({
            rules: {
                to: {required: true},
                message: {required: true}
            },
            submitHandler: function (form) {
                submitForm();
                return;
            }
        });
    });

    function loadcontactlist() {

        $.ajax({
            type: 'GET',
            url: BASE_URL + 'getcontactlist.do',
            async: false
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            var contctlist = resData.CONTACT_LIST;
            var contctlistHtml = "";
            for (var i = 0; i < contctlist.length; i++) {
                $("#contact_list").append('<option value="' + contctlist[i].contact + '">' + contctlist[i].contact + '</option>');
            }
        });
    }

    function submitForm() {

        $("#newmessageloader").show();
        var to = $("#contact_list option:selected").val();
        var message = $("#inputmessage").val();

        var jobid = "";
        var jobtitle = "";
        var parentMessageId = "";
        var refentity = "";

        $.ajax({
            type: 'POST',
            url: BASE_URL + 'sendnewmessage.do',
            data: "to=" + to + "&message=" + message + "&jobid=" + jobid + "&jobtitle=" + jobtitle +
            "&parentMessageId=" + parentMessageId + "&refentity=" + refentity,
            async: false
        }).done(function (res) {

            showSuccess("Message sent successfully.");
            $("#contact_list").val("");
            $("#inputmessage").val("");
        }).always(function (jqXHR, textStatus) {
            $("#newmessageloader").hide();
        });
    }
</script>
</body>
</html>