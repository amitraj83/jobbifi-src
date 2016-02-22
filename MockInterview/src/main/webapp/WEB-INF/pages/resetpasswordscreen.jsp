<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="/WEB-INF/pages/common/css.jsp"%>
<title>Change Password</title>
</head>
<body>
	<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	<div id="wrapper">
		<div id="page-content">

			<div class="container">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<h3 style="margin: 0px">Change Password</h3>
						<hr>
						<div class="row">
							<div class="col-md-12">
								<c:choose>
									<c:when test="${status ne '1'}">
										<div class='alert alert-warning'>The link has been expired. To change password, click <a href='javascript:void(0)' style='color: #0072bc;' onclick='forgotPasswordLink();'>here</a></div>
									</c:when>
									<c:otherwise>
										<div class="" id="changepasserror"></div>
										<div class="panel panel-default" id="changepasswordform">
											<div class="panel-body">
												<form class="form-horizontal" role="form">
													<input type="hidden" id="changepass_authinstance"
														value="${authinstance}" /> <input type="hidden"
														id="changepass_authid" value="${authid}" /> <input
														type="hidden" id="changepass_authtoken"
														value="${authtoken}" />

													<div class="form-group">
														<label class="col-md-3 control-label">New password</label>

														<div class="col-md-9">
															<input class="form-control" type="password"
																id="changepass1" placeholder="New password" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 control-label">Confirm new
															password</label>

														<div class="col-md-9">
															<input class="form-control" type="password"
																id="changepass2" placeholder="Confirm new password" />
														</div>
													</div>
												</form>
											</div>
											<center>
												<button type="button" onclick="restPassword();"
													class="btn btn-success">Change Password</button>
											</center>
											<br />
										</div>
									</c:otherwise>
								</c:choose>

							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<br />

	<%@ include file="/WEB-INF/pages/common/footer.jsp"%>
	<%@ include file="/WEB-INF/pages/common/js.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#footer").css("position", "fixed");
			$("#footer").addClass("navbar-fixed-bottom");
		});
		var loc = document.location + "";
		var BASE_URL = loc.substring(0, loc.lastIndexOf("/")) + "/";

		function restPassword() {
			var changepass1 = $("#changepass1").val();
			var changepass2 = $("#changepass2").val();
			if (changepass1 == changepass2) {
				if(changepass1.length >= 8){
				var encryptedPass = CryptoJS.SHA1(changepass2);
				$
						.ajax(
								{
									type : "POST",
									url : BASE_URL + "resetpassword.do",
									data : "password="
											+ encryptedPass
											+ "&authinstance="
											+ $("#changepass_authinstance")
													.val() + "&authid="
											+ $("#changepass_authid").val()
											+ "&authtoken="
											+ $("#changepass_authtoken").val()
								})
						.done(
								function(msg) {
									var resData = jQuery.parseJSON(msg);
									if (resData.status == 0) {
										$("#changepasserror")
												.html(
														"<div class='alert alert-warning'>The link has been expired. To change password, click <a href=\"javascript:void(0)\" style='color: #0072bc;' onclick='forgotPasswordLink();'>here</a></div>");
										$("#changepasswordform").hide();
									} else if (resData.status == 1) {
										$("#changepasserror")
												.html(
														"<div class='alert alert-success'>Your password has been changed successfully. Click <a \"javascript:void(0)\" style='color: #0072bc;' onclick='loginLink();'>here</a> to login.</div>");
										$("#changepasswordform").hide();
									} else if (resData.status == 2) {
										$("#changepasserror")
												.html(
														"<div class='alert alert-error'>The system has encountered some problem. Please try again.</div>");
									}
								});
				}else{
					$("#changepasserror").html("<div class='alert alert-error'>Please enter at least 8 characters.</div>");
				}
			} else {
				$("#changepasserror").html("The two passwords are not same.");
			}
		}
		function forgotPasswordLink(){
			$('#myModal').modal('show');
			$('#loginbox').hide();
			$('#forgotpasswordbox').show()
		}
		function loginLink(){
			window.location = BASE_URL;
		}
	</script>
</body>
</html>