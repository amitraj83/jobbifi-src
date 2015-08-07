<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mock Interviews</title>
    <link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic|Roboto+Condensed:400,700'
          rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="template/css/bootstrap.css">
    <link rel="stylesheet" href="template/css/font-awesome.min.css">
    <link rel="stylesheet" href="template/css/flexslider.css">
    <link rel="stylesheet" href="template/css/style.css">
    <link rel="stylesheet" href="template/css/custom.css">
    <link rel="stylesheet" href="template/css/responsive.css">
    <!--[if IE 9]>
    <script src="template/js/media.match.min.js"></script>
    <![endif]-->

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div id="website_header"></div>

<div id="main-wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-8 page-content">
                    <h3>${test.title}</h3>

                    <p>
                        <c:forEach var="skill" items="${test.skills}">
                            <span class="label label-info">${skill}</span> &nbsp;
                        </c:forEach>
                    </p>

                    <p>${test.description}</p>

                    <p>This test is having ${test.noOfQuestions} multiple choice questions and should take less
                        than ${test.duration} minutes to complete.</p>

                    <p><a class="btn btn-success" href='<c:url value="/starttest.do"/>?tid=${test.id}'>Start Test</a>
                    </p>
                </div>
                <div class="col-md-4 page-sidebar">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Test Contents</h3>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li>test content</li>
                                <li>test content</li>
                                <li>test content</li>
                                <li>test content</li>
                                <li>test content</li>
                                <li>test content</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/jquery-1.11.0.min.js"><\/script>')</script>
<script src="template/js/jquery.ba-outside-events.min.js"></script>
<script src="template/js/jquery.responsive-tabs.js"></script>
<script src="template/js/jquery-ui-1.10.4.custom.min.js"></script>
<script src="template/js/jquery.inview.min.js"></script>
<script src="template/js/script.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/map.js"></script>
<script type="text/javascript" src="js/dateformat.js"></script>
<script type="text/javascript" src="js/sha1.js"></script>
<script type="text/javascript" src="js/library.js"></script>
<script type="text/javascript" src="js/frontcommon.js"></script>
<script type="text/javascript" src='js/strophe.js'></script>
<script type="text/javascript" src="js/chat.js"></script>
<script>
    var loc = document.location + "";
    var BASE_URL = loc.substring(0, loc.lastIndexOf("/")) + "/";
</script>
</body>
</html>
