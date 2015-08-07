<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Mock Interviews</title>
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/css/modern-business.css' />" rel="stylesheet">
    <link href="<c:url value='/libraries/font-awesome-4.1.0/css/font-awesome.min.css' />" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/map.js"></script>
    <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/dateformat.js"></script>
    <script type="text/javascript" src="js/library.js"></script>
    <script type="text/javascript" src="js/frontcommon.js"></script>
    <script type="text/javascript" src='js/strophe.js'></script>
    <script type="text/javascript" src="js/chat.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#date").html(prettyDate(new Date(Number(${job.dt}))));
            $("#postedByRating").html(getStarView(${rating}));
        });
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <h2> ${job.title} <br>
                <small> ${job.companyName}</small>
            </h2>
            <p>
                <i class="fa fa-map-marker"></i>&nbsp;
                ${job.location} | ${job.experience} yr exp. | $${job.salary} | Posted on <span id="date"></span>

            </p>

            <p>
                Skills:
                <c:forEach var="skill" items="${job.skills}">
                <span class="label label-info">${skill}</span> &nbsp;
                </c:forEach>
            <hr>
            <h3>Job Description</h3>

            <p>${job.description}</p>

        </div>
        <div class="col-md-4">
            <div class="well" style="margin:25px">
                <div class="row">
                    <div class="col-md-6">
                        <a href="userprofile.html?name=${job.interviewer}" target="_blank"><img
                                class="img-responsive img-hover" src="${profilepic}" alt="User Pic"
                                style="height:100px;"></a>
                    </div>
                    <div class="col-md-6" style="padding:0px 0px 0px 2px">
                        Posted By<br><span><a target="_blank"
                                              href="userprofile.html?name=${job.interviewer}">${job.interviewer}</a></span><br>
                        <span id="postedByRating"></span>
                        <button onclick="showContactMeScreen('${job.interviewer}')" type="button"
                                class="btn btn-primary btn-sm" style="margin-top:2px;">Contact Me !
                        </button>
                    </div>

                </div>
            </div>
            <div style="margin:25px">
                <h3>Suggested Advisors</h3>

                <div class="row">
                    <div class="col-md-3"><img class="img-responsive img-hover" src="jobpage_files/50x50.gif" alt="">
                    </div>
                    <div class="col-md-9" style="padding:0px 0px 0px 2px"><span><a href="#">amitraj</a></span>
                        <br>
                        <small><a href="#">java</a>, <a href="#">php</a>, <a href="#">xml</a></small>
                    </div>
                    <br>
                    <i class="fa fa-star" style="color: orange;"></i><i class="fa fa-star" style="color: orange;"></i><i
                        class="fa fa-star" style="color: orange;"></i><i class="fa fa-star-half-o"
                                                                         style="color: orange;"></i><i
                        class="fa fa-star-o" style="color: orange;"></i>&nbsp;
                    <small>(34 reviews)</small>
                </div>
                <hr style="margin-top: 5px;margin-bottom: 5px;">
                <div class="row">
                    <div class="col-md-3"><img class="img-responsive img-hover" src="jobpage_files/50x50.gif" alt="">
                    </div>
                    <div class="col-md-9" style="padding:0px 0px 0px 2px"><span><a href="#">amitraj</a></span>
                        <br>
                        <small><a href="#">java</a>, <a href="#">php</a>, <a href="#">xml</a></small>
                    </div>
                    <br>
                    <i class="fa fa-star" style="color: orange;"></i><i class="fa fa-star" style="color: orange;"></i><i
                        class="fa fa-star" style="color: orange;"></i><i class="fa fa-star-half-o"
                                                                         style="color: orange;"></i><i
                        class="fa fa-star-o" style="color: orange;"></i>&nbsp;
                    <small>(34 reviews)</small>
                </div>
                <hr style="margin-top: 5px;margin-bottom: 5px;">
                <div class="row">
                    <div class="col-md-3"><img class="img-responsive img-hover" src="jobpage_files/50x50.gif" alt="">
                    </div>
                    <div class="col-md-9" style="padding:0px 0px 0px 2px"><span><a href="#">amitraj</a></span>
                        <br>
                        <small><a href="#">java</a>, <a href="#">php</a>, <a href="#">xml</a></small>
                    </div>
                    <br>
                    <i class="fa fa-star" style="color: orange;"></i><i class="fa fa-star" style="color: orange;"></i><i
                        class="fa fa-star" style="color: orange;"></i><i class="fa fa-star-half-o"
                                                                         style="color: orange;"></i><i
                        class="fa fa-star-o" style="color: orange;"></i>&nbsp;
                    <small>(34 reviews)</small>
                </div>
                <hr style="margin-top: 5px;margin-bottom: 5px;">
                <div class="row">
                    <div class="col-md-3"><img class="img-responsive img-hover" src="jobpage_files/50x50.gif" alt="">
                    </div>
                    <div class="col-md-9" style="padding:0px 0px 0px 2px"><span><a href="#">amitraj</a></span>
                        <br>
                        <small><a href="#">java</a>, <a href="#">php</a>, <a href="#">xml</a></small>
                    </div>
                    <br>
                    <i class="fa fa-star" style="color: orange;"></i><i class="fa fa-star" style="color: orange;"></i><i
                        class="fa fa-star" style="color: orange;"></i><i class="fa fa-star-half-o"
                                                                         style="color: orange;"></i><i
                        class="fa fa-star-o" style="color: orange;"></i>&nbsp;
                    <small>(34 reviews)</small>
                </div>
                <hr style="margin-top: 5px;margin-bottom: 5px;">
                <div class="row">
                    <div class="col-md-3"><img class="img-responsive img-hover" src="jobpage_files/50x50.gif" alt="">
                    </div>
                    <div class="col-md-9" style="padding:0px 0px 0px 2px"><span><a href="#">amitraj</a></span>
                        <br>
                        <small><a href="#">java</a>, <a href="#">php</a>, <a href="#">xml</a></small>
                    </div>
                    <br>
                    <i class="fa fa-star" style="color: orange;"></i><i class="fa fa-star" style="color: orange;"></i><i
                        class="fa fa-star" style="color: orange;"></i><i class="fa fa-star-half-o"
                                                                         style="color: orange;"></i><i
                        class="fa fa-star-o" style="color: orange;"></i>&nbsp;
                    <small>(34 reviews)</small>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>