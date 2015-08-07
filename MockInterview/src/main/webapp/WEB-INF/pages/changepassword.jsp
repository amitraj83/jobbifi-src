<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Post Interview</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-3 page-sidebar">
                    <div class="list-group list-default">
                        <a href="<c:url value='/profilesetting.do'/>" class="list-group-item">Update Profile</a>
                        <a href="<c:url value='/changepassword.do'/>" class="list-group-item active">Change Password</a>
                    </div>
                </div>
                <div class="col-sm-9 page-content">
                    <div class="clearfix white-container">
                        <h3 style="margin:0px">Change Password</h3>
                        <hr/>
                        <form class="form form-horizontal" id="changePasswordForm">
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Current Password</label>

                                <div class="col-lg-9">
                                    <input type="password" class="form-control" name="currentPassword"
                                           id="currentPassword"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">New Password</label>

                                <div class="col-lg-9">
                                    <input type="password" class="form-control" name="newPassword" id="newPassword"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Confirm New Password</label>

                                <div class="col-lg-9">
                                    <input type="password" id="confirmNewPassword" name="confirmNewPassword"
                                           class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"></label>

                                <div class="col-lg-9">
                                    <button class="btn btn-success" type="submit">Update Password</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">
    $(function () {
        $("#changePasswordForm").validate({
            rules: {
                currentPassword: {required: true, minlength: 8},
                newPassword: {required: true, minlength: 8, nowhitespace: true},
                confirmNewPassword: {required: true, minlength: 8, equalTo: "#newPassword"}
            },
            submitHandler: function (form) {
                changePassword();
                return false;
            }
        });
    });

    function changePassword() {
        var data = "currentPassword=" + CryptoJS.SHA1($("#currentPassword").val()) +
                "&newPassword=" + CryptoJS.SHA1($("#newPassword").val());
        $.ajax({
            url: BASE_URL + "changepassword.do",
            data: data,
            type: 'POST',
            success: function (response) {
                var resData = jQuery.parseJSON(response);
                $("#currentPassword").val('');
                $("#newPassword").val('');
                $("#confirmNewPassword").val('');
            },
            error: function () {
                console.log("Error occured while processing the request");
            }
        });
        return false;
    }
</script>
</body>
</html>