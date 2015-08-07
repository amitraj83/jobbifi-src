<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- No cache header -->
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Mock Interviews</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/modern-business.css" rel="stylesheet">
    <link href="libraries/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="js/jquery-1.11.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/sha1.js"></script>
    <script src="js/library.js"></script>
    <script src="js/frontcommon.js"></script>
    <script>
        var loc = document.location + "";
        var BASE_URL = loc.substring(0, loc.lastIndexOf("/")) + "/";

        function restPassword() {
            var changepass1 = $("#changepass1").val();
            var changepass2 = $("#changepass2").val();
            if (changepass1 == changepass2) {
                var encryptedPass = CryptoJS.SHA1(changepass2);
                $.ajax({
                    type: "POST",
                    url: BASE_URL + "resetpassword.do",
                    data: "password=" + encryptedPass + "&authinstance=" + $("#changepass_authinstance").val() + "&authid=" + $("#changepass_authid").val() + "&authtoken=" + $("#changepass_authtoken").val()
                }).done(function (msg) {
                    var resData = jQuery.parseJSON(msg);
                    if (resData.status == 0) {
                        $("#changepasserror").html("<div class='alert alert-warning'>The link has been expired. To change password, click <a href=\"#forgotpassword\">here</a></div>");
                        $("#changepasswordform").hide();
                    } else if (resData.status == 1) {
                        $("#changepasserror").html("<div class='alert alert-success'>Your password has been changed succesfully. Click <a href=\"index.jsp\">here</a> to login.</div>");
                        $("#changepasswordform").hide();
                    } else if (resData.status == 2) {
                        $("#changepasserror").html("<div class='alert alert-error'>The system has encountered some problem. Please try again.</div>");
                    }
                });
            } else {
                $("#changepasserror").html("The two passwords are not same.");
            }
        }
    </script>
</head>
<body>
<div class="container" style="margin-top:40px">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <h3 style="margin:0px">Change Password</h3>
            <hr>
            <div class="row">
                <div class="col-md-12">
                    <div class="" id="changepasserror"></div>
                    <div class="panel panel-default" id="changepasswordform">
                        <div class="panel-body">
                            <form class="form-horizontal" role="form">
                                <input type="hidden" id="changepass_authinstance" value="${authinstance}"/>
                                <input type="hidden" id="changepass_authid" value="${authid}"/>
                                <input type="hidden" id="changepass_authtoken" value="${authtoken}"/>

                                <div class="form-group">
                                    <label class="col-md-3 control-label">New password</label>

                                    <div class="col-md-9">
                                        <input class="form-control" type="password" id="changepass1"
                                               placeholder="New password"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Confirm new password</label>

                                    <div class="col-md-9">
                                        <input class="form-control" type="password" id="changepass2"
                                               placeholder="Confirm new password"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <center>
                            <button type="button" onclick="restPassword();" class="btn btn-success">Change Password
                            </button>
                        </center>
                        <br/>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<br/>
</body>
</html>