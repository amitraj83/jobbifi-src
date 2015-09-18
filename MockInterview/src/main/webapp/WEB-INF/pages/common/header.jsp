<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<div class="modal fade bs-example-modal-sm" id="myModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog login-modal" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-body text-center">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <div id="loginbox" class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <h3 class="text-center">Log in to Jobbifi</h3>

                        <div>
                            <div style="display: none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            <form id="loginform" class="form-horizontal" role="form" name='f'
                                  action="j_spring_security_check" method='POST'>
                                <input type="hidden" id="callback"/>

                                <div class="input-group" id="login-username"><span class="input-group-addon"><span
                                        class="fa fa-user" aria-hidden="true"></span></span>
                                    <input type="text" class="form-control" id="j_username" name='j_username'
                                           placeholder="someone@example.com"/>
                                </div>
                                <div class="input-group" id="login-password"><span class="input-group-addon"><i
                                        class="fa fa-lock"></i></span>
                                    <input type="password" class="form-control" id="j_password" name='j_password'
                                           placeholder="Password"/>
                                </div>
                                <div class="form-group" id="login-submit-button">
                                    <div class="controls">
                                        <button type="submit" name="submit" class="btn btn-default">Login</button>
                                        <img id="loginbtnloader" style="display: none;" alt="Processing..."
                                             src="<c:url value=" /resources/img/loading.gif " />"/>
                                    </div>
                                </div>
                                <!-- FORGOT PASSWORD -->
                                <button class="btn btn-link" 
                                        onClick="$('#loginbox').hide(); $('#forgotpasswordbox').show()">Forgot password?
                                </button>
                                <!-- SIGN UP -->
                                <div class="form-group" id="login-sign-up-button">
                                    <div class="control"> Don't have an account? <a class=""
                                                                                    onClick="$('#loginbox').hide(); $('#signupbox').show()">Sign
                                        up here.</a></div>
                                </div>
                            </form>
                        </div>
                        <div class="clearfix">
                            <p class="text-muted text-small sign-in-separator">
                                or
                            </p>
                            <button onclick="linkedinLogin();" class="btn btn-default btn-linkedin"> Login with Linked<i
                                    class="fa fa-linkedin"></i></button>
                        </div>
                        <br/>
                    </div>
                </div>
                <div id="forgotpasswordbox" style="display: none;" class="mainbox">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="panel-title">Forgot your password?</div>
                            <div style="float: right; font-size: 80%; position: relative; top: -10px"><a
                                    href="javascript:void(0);" id="acceso"
                                    onclick="$('#forgotpasswordbox').hide(); $('#loginbox').show()"> Account Access</a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <form accept-charset="UTF-8" id="login-recordar" method="post">
                                <fieldset>
                                    <p class="clearfix"> Email address you use to log in to your account
                                        <br> We'll send you an email with instructions to choose a new password. </p>

                                    <div class="form-group input-group"><span class="input-group-addon"> @ </span>
                                        <input id="forgotpass_email" class="form-control" placeholder="Email"
                                               name="email" type="email">
                                    </div>
                                    <button type="button" class="btn btn-default" onclick="sendForgotPasswordMail();">
                                        Continue
                                    </button>
                                    <img id="passwordbtnloader" style="display: none;" alt="Processing..."
                                         src="<c:url value=" /resources/img/loading.gif " />"></fieldset>
                            </form>
                        </div>
                    </div>
                </div>
                <!--- Sign up box -->
                <div id="signupbox" style="display: none;" class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <h3 class="text-center">Sign up with Jobbifi</h3>

                        <div class="clearfix">
                            <br/>
                            <form id="signupform" class="form-horizontal">
                            	<div class="form-group row">
                                    <label class="col-md-4 control-label">User type</label>
                                    <div class="col-md-8">
                                    	<label class="pull-left radio-inline">
                                                <input name="usertype"  checked="checked" value="INTERVIEWEE" type="radio"> Interviewee                                            
                                        </label>                                    	                                       
                                        <label class="radio-inline">
                                               <input name="usertype" value="INTERVIEWER" type="radio"> Interviewer                                            
                                        </label>                                                                                                                   
                                    </div>
                                </div>
                                
                                <div id="intervieweeSignUpForm">
	                                <div class="form-group row">
	                                    <label for="email" class="col-md-4 control-label">Email</label>
	
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" id="email" name="email"
	                                               placeholder="Email Address">
	                                    </div>
	                                </div>
	                                <div class="form-group row">
	                                    <label for="firstname" class="col-md-4 control-label">Username</label>
	
	                                    <div class="col-md-8">
	                                        <input type="text" class="form-control" id="username" name="username"
	                                               placeholder="Choose a username">
	                                    </div>
	                                </div>
	                                <div class="form-group row">
	                                    <label for="lastname" class="col-md-4 control-label">Password</label>
	
	                                    <div class="col-md-8">
	                                        <input type="password" class="form-control" id="password" name="password"
	                                               placeholder="Password">
	                                    </div>
	                                </div>
	                                <div class="form-group row">
	                                    <label for="password" class="col-md-4 control-label">Repeat Password
	                                    </label>
	
	                                    <div class="col-md-8">
	                                        <input type="password" class="form-control" id="confirmpassword"
	                                               name="confirmpassword" placeholder="Confirm Password">
	                                    </div>
	                                </div>
	                                
	                                <div class="form-group row">
	                                    <div class="col-md-12">
	                                        <button type="submit" class="btn btn-default">Sign Up</button>
	                                        <img id="signupbtnloader" style="display: none;" alt="Processing..."
	                                             src="<c:url value=" /resources/img/loading.gif " />">
	                                    </div>
	                                </div>
	                                
	                                <p class="text-muted text-small sign-in-separator">
	                                        or
	                                </p>
                                </div>
                                
                                <div class="clearfix">                                
                                    <button onclick="linkedinLogin();" class="btn btn-default btn-linkedin">Sign up
                                        using Linked<i class="fa fa-linkedin"></i></button>
                                </div>
                                <br/>
                                Already have an account? <a id="signinlink" href="javascript:void(0)"
                                                            onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign
                                in here.</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- Page Loader -->
<div class="preloader">
    <div id="loaderImage"></div>
</div>
<header id="navigation" >
    <div class="navbar main-nav" role="banner">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">
                    <h1><img class="img-responsive" src="images/logo.png" alt="logo"></h1>
                </a>
            </div>
            <div class="top-bar">
                    <span>
                    <a href="mailto:support@jobbifi.com"><i class="fa fa-envelope"></i>support@jobbifi.com
                    </a>
                    </span>
                <!-- <span><i class="fa fa-phone"></i> (123) 456-7890</span> --></div>
            <nav class="collapse navbar-collapse navbar-right">
                <ul class="nav navbar-nav">
                    <!-- IF USER IS NOT AUTHENTICATED -->
                    <sec:authorize access="!isAuthenticated()">
                        <li class="scroll active"><a href="#navigation">Home</a></li>
                        <li class="scroll"><a class="scroll" href="#services">Services</a></li>
                        <li class="scroll"><a class="scroll" href="#about-us">About us</a></li>
                        <li class="scroll"><a class="scroll" href="#pricing-tables">For Employers</a></li>
                        <li class="scroll"><a class="scroll" href="#contact-us">Contact</a></li>
                        <li class="scroll"><a href="#navigation" data-toggle="modal" data-target="#myModal">Login</a>
                        </li>
                        <!-- <li class="scroll"><a href="#pricing-tables">Pricing Table</a></li> -->
                    </sec:authorize>
                    <!-- IF USER IS AN INTERVIEWEE -->
                    <sec:authorize access="hasRole('ROLE_INTERVIEWEE')">
                        <li id="nav_jobs"><a title="Jobs" href="<c:url value='/jobs.do'/>">Jobs</a></li>
                        <li><a title="Advisors" href="<c:url value='/advisors.do'/>">Advisors</a></li>
                        <li><a title="My Interviews" href="<c:url value='/myinterview.do'/>">My Mocks</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_INTERVIEWER')">                    
                    	<li id="nav_advisors"><a title="Advisors" href="<c:url value='/mocks.do'/>">Advisors</a></li>
                        <li><a title="Employer" href="<c:url value='/jobapplications.do'/>">Employer</a></li>
                    </sec:authorize>
                    <!-- ANY LOGGED IN USER -->
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <li><a title="Finance" href="<c:url value='/finance.do'/>">Finance</a></li>
                        <li><a title="Message" href="<c:url value='/message.do'/>">Messages <span
                                id="messageCount"></span></a></li>
                        <li id="user-dropdown-menu" class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-user"></i>
                                <sec:authentication property="principal.username"/>
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a title="Profile" href="<c:url value='/profile.do'/>"><i class="fa fa-user"></i>
                                    Profile</a></li>
                                <li><a title="Settings" href="<c:url value='/profilesetting.do'/>"><i
                                        class="fa fa-cog"></i> Settings</a></li>
                                <li>
                                    <a href="javascript:void(0)" onclick="logout();" id="signout"><span
                                            class="fa fa-sign-out"></span> Sign out</a>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>
                </ul>
            </nav>
            <div class="search">
                <form role="form"><i class="fa fa-search"></i>

                    <div class="field-toggle">
                        <input type="text" class="search-form" autocomplete="off" placeholder="Search">
                    </div>
                </form>
            </div>
        </div>
    </div>
</header>



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
