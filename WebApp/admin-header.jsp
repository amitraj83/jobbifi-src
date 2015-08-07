<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value='/'/>">Mock Interview</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="menu-logout"><a href="javascript:void(0)" onclick="logout();">Logout</a></li>
        </ul>
    </div>
</nav>
<script type="text/javascript">
    function logout() {
        $.ajax({
            type: "GET",
            url: "<c:url value='/logout.do'/>",
        }).done(function (msg) {
            window.location.href = "<c:url value='/'/>";
        }).error(function (msg) {
            console.log("error occured while logout");
        });
    }
</script>