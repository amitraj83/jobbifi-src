<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Profile</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>


<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-9 profile-page">
                    <div class="row">
                        <div class="col-md-4">
                            <img class="img-thumbnail profile_img" src="<c:url value='/resources/images/face.jpg'/>"
                                 id="profile_userpic"/>
                        </div>
                        <div class="col-md-8">
                            <div class="pull-right">
                                <center>
                                    <h1 class="rating-num" style="margin-top: 0px;margin-bottom: 0px;">
                                        <span id="profile_rating">4.0</span>
                                    </h1>
                                    <input type="number" id="userRating"/>

                                    <div>
                                        <span class="glyphicon glyphicon-user"></span>&nbsp;<span
                                            id="reviewcount">1,050,008</span> Reviews
                                    </div>
                                </center>
                            </div>
                            <strong>
                                <h1 style="margin-top:0px;text-transform:capitalize;font-weight:bold;">
                                    <div id="profile_username"></div>
                                    <small style="font-size:46%;margin-top:5px;">Software Engineer at Goldman Sachs
                                    </small>
                                </h1>
                            </strong>

                            <br>

                            <div id="user-balance-div"></div>
                            <div class="profile-country">Country <span id="profile_country"></span></div>
                            <div id="usershortcv"></div>


                        </div>

                    </div>
                    <hr/>
                    <div class="row">

                        <div class="col-md-12">
                            <i class="fa fa-2x fa-suitcase"></i> &nbsp;<span
                                style="font-size:24px;">Work Experience</span>

                            <div id="workexperience"></div>


                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <i class="fa fa-2x fa-graduation-cap"></i> &nbsp;<span
                                style="font-size:26px;">Education</span>

                            <div id="education">
                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>University</th>
                                        <th>Degree</th>
                                        <th>Major</th>
                                        <th>Year</th>
                                    </tr>
                                    </thead>
                                    <tbody id="userprofileeducation"></tbody>
                                </table>
                            </div>
                            <hr/>

                        </div>
                    </div>


                    <div class="row">
                        <div class="col-md-12">
                            <i class="fa fa-2x fa-tags"></i> &nbsp;<span style="font-size:28px;">Skills</span>

                            <div id="skills"></div>
                            <hr/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <i class="fa fa-2x fa-comments"></i> &nbsp;<span style="font-size:28px;">Reviews</span>

                            <div class="tab-pane" id="reviews"></div>
                            <hr/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div id="interviewoffered">

                            </div>
                        </div>
                    </div>

                </div>


                <div class="col-md-3"></div>
            </div>


        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>

<script type="text/javascript">
    username = "${name}";
    $(function () {
        getProfileDetails();
    });

    function getProfileDetails() {
        $.ajax({
            url: BASE_URL + "getProfileDetails.do?username=" + username,
            type: 'GET',
            async: false,
            success: function (res) {
                var user = jQuery.parseJSON(res);
                if (user.type == "INTERVIEWER") {
                    $("#profiletabs")
                            .append('<li><a href="#interviewoffered" role="tab" data-toggle="tab">' +
                            '<i class="fa fa-book"></i> &nbsp;Jobs Offered</a></li>');

                    showJobsOffered();
                }

                $("#reviewcount").html(user.reviewCount);
                $("#profile_username").html(user.username);
                var country = (user.country != "") ? user.country : "NA";
                $("#profile_country").html(country);
                $("#profile_userpic").attr({'src': BASE_URL + user.profilepic});

                $(".custom-rating").val(user.avgRating);
                $(".custom-rating").rating({
                    'min': 0,
                    'max': 5,
                    'step': 1,
                    'readonly': true,
                    'showClear': false,
                    'showCaption': false
                });

                $("#profile_rating").html(parseFloat(user.avgRating).toFixed(2));
                $("#userRating").val(user.avgRating).rating({
                    'min': 0,
                    'max': 5,
                    'step': 1,
                    'readonly': true,
                    'showClear': false,
                    'showCaption': false
                });

                var cv = $.trim(user.cv);
                if (cv.length > 280) {
                    cv = cv.substring(0, 280);
                    cv = cv + '...&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">Show More </a>';
                }
                $("#usershortcv").html(cv);
                console.log("Loggedin user:" + user.username);
                console.log("showing user: " + username);
                if (user.username == username) {
                    $("#user-balance-div").html('<div class="profile-balance">Balance' +
                            '<span id="profile_balance"></span></div>');
                }
                var workexp = "";
                var allpositions = user.positions;
                if (allpositions.length > 0) {
                    for (var i = 0; i < allpositions.length; i++) {
                        workexp = workexp + '<span><h5><b>'
                                + allpositions[i].title + ' - '
                                + allpositions[i].companyName + ' ('
                                + allpositions[i].startYear + '-'
                                + allpositions[i].endYear
                                + ')</b></h5></span>'
                                + allpositions[i].description + '<hr>';
                    }
                    $("#workexperience").html(workexp);
                } else {
                    $("#workexperience").html("<div>Work experience information not available.</div>");
                }

                var alleducations = user.educations;
                if (alleducations.length) {
                    for (var i = 0; i < alleducations.length; i++) {
                        var education = '<tr> ' + '<td>'
                                + alleducations[i].schoolname + '</td>'
                                + '<td>' + alleducations[i].degree
                                + '</td>' + '<td>'
                                + alleducations[i].fieldOfStudy + '</td>'
                                + '<td>' + alleducations[i].startYear + '-'
                                + alleducations[i].endYear + '</td>'
                                + '</tr>';
                        $("#userprofileeducation").append(education);
                    }
                } else {
                    $("#userprofileeducation").append("<tr><td colspan='4'>Education information not available.</td></tr>");
                }

                var allskills = user.skilllist;
                if (allskills.length > 0) {
                    for (var i = 0; i < allskills.length; i++) {
                        var per = Math.round((allskills[i].skillYear / 15) * 100);
                        if (per > 100) {
                            per = 100;
                        }

                        var askill = '<div class="row">'
                                + '<div class="col-md-3">' + allskills[i].skill + '</div>'
                                + '<div class="col-md-6 ">'
                                + '<div class="progress">'
                                + '	<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="'
                                + per + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + per + '%">'
                                + '<span class="sr-only">' + per + '% </span>' + '</div>'
                                + '</div>'
                                + '</div>'
                                + '<div class="col-md-3">' + allskills[i].skillYear + '	years</div></div>';
                        $("#skills").append(askill);
                    }
                } else {
                    $("#skills").html("<div>Skills information not available.</div>");
                }

                var allReviews = user.allreviews;
                if (allReviews.length > 0) {
                    for (var i = 0; i < allReviews.length; i++) {
                        console.log(allReviews[i]);
                        var areview = '<div class="panel panel-default">'
                                + '<div class="panel-heading">'
                                + '<div class="row">'
                                + '<div class="col-md-8"><span class="panel-title">'
                                + allReviews[i].ititle + '</span> </div>'
                                + '<div class="col-md-4"><span style="pull-right">'
                                + ' (' + allReviews[i].average + '/5)'
                                + '	</span></div></div></div>'
                                + '<div class="panel-body">' + '<i>' + allReviews[i].message + '</i>'
                                + '<div class="row">'
                                + '<div class="col-md-8"></div>'
                                + '<div class="col-md-4"><span style="pull-right"><i> by '
                                + getUserProfileLink(allReviews[i].ratedby) + ' on <small>'
                                + prettyDate(new Date(Number(allReviews[i].dt))) + '</small></span></div>'
                                + '  </div>' + '  </div>' + '</div>';
                        $("#reviews").append(areview);
                    }
                } else {
                    $("#reviews").html("<div>No reviews available.</div>");
                }
            }
        });
    }

    /* show jobs offered */
    function showJobsOffered() {
        $.ajax({
            url: "<c:url value='getJobsOffered.do'/>",
            type: 'GET',
            async: false,
            success: function (res) {
                var resData = jQuery.parseJSON(res);
                var jobs = resData.jobs;
                var jobsHtml = "";
                if (null != jobs && jobs.length > 0) {
                    for (var i = 0; i < jobs.length; i++) {
                        jobsHtml += '<div class="panel panel-default">' +
                                '<div class="panel-heading">' +
                                '<h4 class="panel-title">' +
                                '<a data-toggle="collapse" data-parent="#accordion" href="#collapse' + i + '"><i class="fa fa-plus"></i> ' + jobs[i].title + '</a>' +
                                '<span class="pull-right">' + prettyDate(new Date(jobs[i].dt)) + '</span>' +
                                '</h4>' +
                                '</div>' +
                                '<div id="collapse' + i + '" class="panel-collapse collapse">' +
                                '<div class="panel-body">' +
                                '<p>' + jobs[i].description + '</p>' +
                                '</div>' +
                                '</div>' +
                                '</div>';
                    }
                } else {
                    jobsHtml = "No jobs posted.";
                }
                $("#jobslist").html(jobsHtml);
            }
        });
    }
    ;

    function getUserProfileLink(user) {
        return "<a target='_blank' href='" + BASE_URL + "userprofile.do?name=" + user + "'>" + user + "</a>";
    }
</script>
</body>
</html>