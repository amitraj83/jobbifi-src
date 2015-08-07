<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // Create cookies for first and last names.
    //Cookie firstName = new Cookie("username",request.getParameter("username"));

    //response.addCookie( firstName );
    response.sendRedirect("welcome.do");
%>
<script>
    window.location = "/welcome.do";
</script>
