<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Login</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<c:if test="${not empty error}">
    <div class="errorblock">
        Your login attempt was not successful, try again.<br/> Caused :
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
</c:if>


<div class="login_form_section">

    <p class="login_logo_text" style="margin:5px 0 0 50px;"><a href="#"><img src="images/linkedin.png"/></a></p>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

    <form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
        <p><input type="text" class="login_textbox" name='j_username' placeholder="someone@example.com"/></p>

        <p><input type="password" class="login_textbox" name='j_password' placeholder="Password"/></p>

        <p><input type="submit" name="submit" class="login_submitbutton" value="Submit"/></p>
    </form>

    <p style="margin:10px 0 0 50px; color:#757675; font-family:Arial, Helvetica, sans-serif; font-size:14px;">Don't have
        an account? <a href="register.html"><span style="color:#67b971;">Sign up now</span></a></p>
</div>

<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
</body>
</html>