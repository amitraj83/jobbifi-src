<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="modal fade" id="videoModal" tabindex="-1" role="dialog" aria-labelledby="videoModal" aria-hidden="true">
	<div class="modal-dialog" style="width: 50%;">
		<div class="modal-content" style="background: #00395E;">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true" style="color: white;">&times;</span>
							</button>
				<div style="margin-left: 9px;">
					<iframe width="99%" height="450"  src="https://www.youtube.com/embed/n-SO8aAGInQ" frameborder="0" allowfullscreen></iframe>
					
					
				</div>
			</div>
		</div>
	</div>
</div>
			


<div class="modal fade bs-example-modal-sm" id="myModal" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog login-modal">
		<div class="modal-content">
			<div class="modal-body text-center">
				<div id="loginbox">
                    <div class="panel panel-info" style="border-color: #00395E;">
                    <div class="panel-heading" style="color: #FFFFFF;background-color: #00395E;border-color: #00395E;">
                        <div class="panel-title text-center" style="font-size: 22px;">Welcome to Jobbifi
                        <div class="pull-right">
                        <button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true" style="color: white;">&times;</span>
							</button>
                        </div>
                        </div>
                    </div>     
                    <div style="padding-top:30px" class="panel-body">
                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                             <form id="loginform" role="form" name="f"
									action="j_spring_security_check" method="POST">
									<div class="form-group email-field">
										<input type="email" class="form-control text-center"
											required="required" placeholder="someone@example.com"
											id="j_username" name='j_username'>
									</div>
									<div class="form-group password-field">
										<input type="password" class="form-control text-center"
											required="required" placeholder="Password" id="j_password"
											name='j_password'>
									</div>
									<div class="form-group">
										<button type="submit" id="login-submit-button"
											class="btn btn-success btn-block lowercase" style="width: 370px;">Login</button>
										<img id="loginbtnloader" style="display: none;"
											alt="Processing..."
											src="<c:url value=" /resources/img/loading.gif " />" />
									</div>
									<input type="hidden" id="callback" />
								</form>
                             
                             
                             <div style="display: none;" id="login-alert"
								class="alert alert-danger col-md-12"></div>
                                
                                
                             
                             <div class="row login-form-content-footer">
							<div class="col-sm-6 col-md-6 text-left">
								<a href="#" class="small muted"
									onClick="$('#loginbox').hide(); $('#forgotpasswordbox').show()">Forgot
									password?</a>
							</div>
							<div class="col-sm-6 col-md-6 text-right">
								<a href="#" class="small muted"
									onClick="$('#loginbox').hide(); $('#signupbox').show()">Sign up now</a>
							</div>
						</div>   
                                
                                
                             
                             
                        </div>                     
                    </div>


				</div>

				<div id="forgotpasswordbox" style="display: none;" class="mainbox">
                
                
                <div class="panel panel-info" style="border-color: #00395E;">
                    <div class="panel-heading" style="color: #FFFFFF;background-color: #00395E;border-color: #00395E;">
                        <div class="panel-title text-center" style="font-size: 22px;">Forgot your password?
                        <div class="pull-right">
                        <button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
                        </div>
                    </div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body">
                           <form accept-charset="UTF-8" id="login-recordar" method="post">
								<fieldset>
									<p class="clearfix">
										Email address you use to log in to your account.<br /> We'll
										send you an email with instructions to choose a new password.
									</p>

									<div class="form-group">
										<input
											id="forgotpass_email" class="form-control text-center"
											placeholder="Email" name="email" type="email" />
									</div>
									<label id="forgotpass_email-error" class="error"
										for="forgotpass_email" style="display: inline-block;"></label>
									<br />
									<button type="submit" class="btn btn-success btn-block lowercase">Continue</button>
									<img id="passwordbtnloader" style="display: none;"
										alt="Processing..."
										src="<c:url value="/resources/img/loading.gif" />">
								</fieldset>
							</form>
                                         
                          
                          <div class="login-form-content-footer">
                            <div class="text-center">
                                <a href="#" class="small muted"
                                    onClick="$('#forgotpasswordbox').hide(); $('#loginbox').show()">or
                                    sign in</a>
                                </div>
                            </div>
                          
                            
                            
                        </div>                     
                    </div>
                
				</div>
				<div id="signupbox" style="display: none;" >
                
                <div class="panel panel-info" style="border-color: #00395E;">
                    <div class="panel-heading" style="color: #FFFFFF;background-color: #00395E;border-color: #00395E;">
                        <div class="panel-title text-center" style="font-size: 22px;">Signup with Jobbifi
                        <div class="pull-right">
                        <button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true" style="color: white;">&times;</span>
							</button>
                        </div>
                    </div>
                    
                    </div>     

                    <div style="padding-top:30px" class="panel-body">
                           
                       	<form id="signupform" class="form-horizontal">
									<div class="form-group row">
										<div class="col-sm-12 col-md-12">
											 <label class="radio-inline"> 
												<input
												name="usertype" checked="checked" id="intervieweeId"
												value="INTERVIEWEE" type="radio"/> 
												Job Seeker
											</label> 
											<label class="radio-inline"> 
												<input name="usertype"
												id="interviewerId" value="INTERVIEWER" type="radio"/>
												Career Advisor
											</label>
											<!--<label class="radio-inline"> 
												<input name="usertype"
												id="employerId" value="EMPLOYER" type="radio"/>
												Employer
											</label> -->

										</div>
									</div>
									<div id="userSignUpForm" >
										<div class="form-group row">
											<div class="col-sm-12 col-md-12">
												<input type="text" class="form-control text-center" id="email"
													name="email" placeholder="Email Address">
											</div>
										</div>
										<div class="form-group row">
											<div class="col-sm-12 col-md-12">
												<input type="text" class="form-control text-center" id="username"
													name="username" placeholder="Choose a username">
											</div>
										</div>
										<div class="form-group row">
											<div class="col-sm-12 col-md-12">
												<input type="password" class="form-control text-center" id="password"
													name="password" placeholder="Password">
											</div>
										</div>
										
										<div class="form-group row">
											<div class="col-md-12">
												<button type="submit" class="btn btn-success btn-block lowercase">Sign Up</button>
													<img id="signupbtnloader" style="display: none;"
													alt="Processing..."
													src="<c:url value=" /resources/img/loading.gif " />">
												
											</div>
										</div>
									</div>
								</form>
                        <div class="login-form-content-footer">
							<div class="text-center">
								<a href="#" class="small muted"
									onClick="$('#signupbox').hide(); $('#loginbox').show()">Already
									have an account?</a>
							</div>
						</div>      



                        </div>                     
                    </div>
                
				</div>
			</div>
		</div>
	</div>
</div>






























<!--

<div class="modal fade bs-example-modal-sm" id="mysignupModal" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog login-modal">
		<div class="modal-content">
			<div class="modal-body text-center">
				

				
				<div id="signupbox"  >
                
                <div class="panel panel-info" style="border-color: #00395E;">
                    <div class="panel-heading" style="color: #FFFFFF;background-color: #00395E;border-color: #00395E;">
                        <div class="panel-title text-center" style="font-size: 22px;">Signup with Jobbifi
                        <div class="pull-right">
                        <button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true" style="color: white;">&times;</span>
							</button>
                        </div>
                    </div>
                    
                    </div>     

                    <div style="padding-top:30px" class="panel-body">
                           
                       	<form id="signupform" class="form-horizontal">
									<div class="form-group row">
										<div class="col-sm-12 col-md-12">
											 <label class="radio-inline"> 
												<input
												name="usertype" checked="checked" id="intervieweeId"
												value="INTERVIEWEE" type="radio"/> 
												Job Seeker
											</label> 
											<label class="radio-inline"> 
												<input name="usertype"
												id="interviewerId" value="INTERVIEWER" type="radio"/>
												Career Advisor
											</label>
											

										</div>
									</div>
									<div id="userSignUpForm" >
										<div class="form-group row">
											<div class="col-sm-12 col-md-12">
												<input type="text" class="form-control text-center" id="email"
													name="email" placeholder="Email Address">
											</div>
										</div>
										<div class="form-group row">
											<div class="col-sm-12 col-md-12">
												<input type="text" class="form-control text-center" id="username"
													name="username" placeholder="Choose a username">
											</div>
										</div>
										<div class="form-group row">
											<div class="col-sm-12 col-md-12">
												<input type="password" class="form-control text-center" id="password"
													name="password" placeholder="Password">
											</div>
										</div>
										
										<div class="form-group row">
											<div class="col-md-12">
												<button type="submit" class="btn btn-success btn-block lowercase">Sign Up</button>
													<img id="signupbtnloader" style="display: none;"
													alt="Processing..."
													src="<c:url value=" /resources/img/loading.gif " />">
												
											</div>
										</div>
									</div>
								</form>
                           



                        </div>                     
                    </div>
                
				</div>
			</div>
		</div>
	</div>
</div>







-->




































<!-- Page Loader -->
<div class="preloader">
	<div id="loaderImage"></div>
</div>
<header id="navigation">
	<div class="navbar main-nav  " role="banner">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">
					<h1>
						<img class="img-responsive" src="images/logo.png" alt="logo">
					</h1>
				</a>
			</div>
			<nav class="collapse navbar-collapse navbar-right">
				<ul class="nav navbar-nav">
					<!-- IF USER IS NOT AUTHENTICATED -->
					<sec:authorize access="!isAuthenticated()">
						<!--<li class="scroll"><a id="headersignuplink" href="#navigation" data-toggle="modal"
							data-target="#mysignupModal">Sign Up</a></li>	-->
						<li class="scroll"><a href="#navigation" data-toggle="modal"
							data-target="#myModal">Login</a></li>
						<li class="scroll"><a class="scroll" href="#about-us">Advisor</a></li>
						<li class="scroll"><a class="scroll" href="#candidate-workflow">Candidate</a></li>
						<li class="scroll"><a id="headerwhatthis" href="#navigation" data-toggle="modal"	data-target="#videoModal" data-theVideo="https://www.youtube.com/embed/sGbxmsDFVnE">What's this?</a></li>
			

			
			
						<!--<li class="scroll active"><a href="#navigation"
							onclick="activeHome()">Home</a></li>-->
						<!--<li class="scroll"><a class="scroll" href="#pricing-tables">Employers</a></li>-->
						<!-- <li class="scroll"><a href="#pricing-tables">Pricing Table</a></li> -->
					</sec:authorize>
					<!-- IF USER IS AN INTERVIEWEE -->
					<sec:authorize access="hasRole('ROLE_INTERVIEWER')">
						<!-- <li id="nav_advisors"><a title="Advisors"
							 href="<c:url value='/mocks.do'/>">Advisors</a></li> -->
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_EMPLOYER')">
						<li><a title="Employer"
							href="<c:url value='/jobapplications.do'/>">Employer</a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a title="Admin"
							href="<c:url value='/admin.do'/>">Admin</a></li>
						<li id="user-dropdown-menu" class="dropdown" >
						<a
							class="dropdown-toggle" data-toggle="dropdown" style="cursor:pointer;"> <i
								class="fa fa-user"></i> <sec:authentication
									property="principal.username" /> <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a title="Profile" href="<c:url value='/profile.do'/>"><i
										class="fa fa-user"></i> Profile</a></li>
								<li><a title="Settings"
									href="<c:url value='/profilesetting.do'/>"><i
										class="fa fa-cog"></i> Settings</a></li>
								<li><a href="javascript:void(0)" onclick="logout();"
									id="signout"><span class="fa fa-sign-out"></span> Sign out</a>
								</li>
							</ul></li>
					</sec:authorize>
					<!-- ANY LOGGED IN USER -->
					<sec:authorize access="hasRole('ROLE_USER')">
						<!--<li><a title="Finance" href="<c:url value='/finance.do'/>">Finance</a></li>-->
						<li id="user-dropdown-menu" class="dropdown">
						<a
							class="dropdown-toggle" data-toggle="dropdown" style="cursor:pointer;"> <i
								class="fa fa-user"></i> <sec:authentication
									property="principal.username" /> <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a title="Profile" href="<c:url value='/profile.do'/>"><i
										class="fa fa-user"></i> Profile</a></li>
								<li><a title="Settings"
									href="<c:url value='/profilesetting.do'/>"><i
										class="fa fa-cog"></i> Settings</a></li>
								<li><a href="javascript:void(0)" onclick="logout();"
									id="signout"><span class="fa fa-sign-out"></span> Sign out</a>
								</li>
							</ul>
						</li>
						<li><a title="Message" href="<c:url value='/message.do'/>">Messages
								<span id="messageCount"></span>
						</a></li>
						
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_INTERVIEWEE')">
						<!--<li id="nav_jobs"><a title="Jobs"
							href="<c:url value='/jobs.do'/>">Jobs</a></li> -->
						<li id="nav_advisors"><a title="Advisors"
							href="<c:url value='/advisors.do'/>">Find Advisors</a></li>
						<!-- <li id="nav_mocks"><a title="My Interviews"
							href="<c:url value='/myinterview.do'/>">My Mocks</a></li> -->
					</sec:authorize>
					
				</ul>
			</nav>
			<div class="search">
				<form role="form">
					<i class="fa fa-search"></i>
					<div class="field-toggle">
						<input type="text" class="search-form" autocomplete="off"
							placeholder="Search">
					</div>
				</form>
			</div>
		</div>
	</div>

</header>
<div id="msg"></div>


<!--/#navigation-->
<!-- <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation"><div class="container"><div class="navbar-header"><button type="button" class="navbar-toggle" data-toggle="collapse"data-target="#bs-example-navbar-collapse-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><a class="navbar-brand" href="<c:url
        value='/'/>" id="logo"><img src="<c:url
        value='/resources/images/site/jobbifi-logo1.png'/>" class="img-responsive"></a></div><div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1"><ul class="nav navbar-nav navbar-right"><sec:authorize
        access="!isAuthenticated()"><li id="nav_jobs"><a title="Jobs" href="<c:url
        value='/jobs.do'/>">Jobs</a></li><li id="nav_mocks"><a title="Mocks" href="<c:url
        value='/mocks.do'/>">Mocks</a></li><li><a title="Advisors" href="<c:url
        value='/advisors.do'/>">Advisors</a></li><li><a title="Employers" href="#">Employers</a></li></sec:authorize><sec:authorize
        access="hasRole('ROLE_INTERVIEWEE')"><li id="nav_jobs"><a title="Jobs" href="<c:url
        value='/jobs.do'/>">Jobs</a></li><li><a title="Advisors" href="<c:url
        value='/advisors.do'/>">Advisors</a></li><li><a title="My Interviews" href="<c:url
        value='/myinterview.do'/>">My Interviews</a></li></sec:authorize><sec:authorize
        access="hasRole('ROLE_INTERVIEWER')"><li id="nav_mocks"><a title="Mocks" href="<c:url
        value='/mocks.do'/>">Mocks</a></li><li><a title="Employers" href="<c:url
        value='/employer.do'/>">Employers</a></li><li><a title="My Interviews" href="<c:url
        value='/awardedbid.do'/>">My Interviews</a></li></sec:authorize><sec:authorize
        access="!isAuthenticated()"><li class="menu-login"><a href="javascript:showLoginBox();" id="loginscreen">Login</a></li></sec:authorize><sec:authorize
        access="hasRole('ROLE_USER')"><li><a title="Finance" href="<c:url
        value='/finance.do'/>">Finance</a></li><li><a title="Message" href="<c:url
        value='/message.do'/>">Messages <span id="messageCount"></span></a></li><li class="dropdown"><a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"><sec:authentication
        property="principal.username"/><b class="caret"></b></span></a><ul class="dropdown-menu"><li><a title="Profile" href="<c:url
        value='/profile.do'/>"><i class="fa fa-user"></i> Profile</a></li><li><a title="Settings" href="<c:url
        value='/profilesetting.do'/>"><i class="fa fa-cog"></i> Settings</a></li><li> A <a href="javascript:void(0)" onclick="logout();" id="signout"><span class="glyphicon glyphicon-off"></span> Sign out</a></li></ul></li></sec:authorize></ul></div></div></nav> -->
