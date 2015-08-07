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

                            <div class="profile-balance">Balance <span id="profile_balance"></span></div>
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
    var username = '<sec:authentication property="principal.username" />';
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
                    showJobsOffered(user);
                }

                $("#reviewcount").html(user.reviewCount);
                $("#profile_balance").html(Number(user.balance).toFixed(2));
                $("#profile_username").html(user.username);

                $("#profile_userpic").attr({'src': BASE_URL + user.profilepic});
                var country = (user.country != "") ? user.country : "NA";
                $("#profile_country").html(country);

                $("#userRating").val(user.avgRating).rating({
                    'min': 0,
                    'max': 5,
                    'step': 1,
                    'readonly': true,
                    'showClear': false,
                    'showCaption': false
                });

                $("#profile_rating").html(parseFloat(user.avgRating).toFixed(2));

                if (Number(user.reviewCount) > 0) {
                    var individualratingsCount = user.individualratings;
                    if (Number(individualratingsCount.rate5) > 0) {
                        $("#howmany5").attr("style", 'width: ' + (Number(individualratingsCount.rate5) / Number(user.reviewCount) * 100) + '%');
                        $("#howmanyvalue5").html((Number(individualratingsCount.rate5) / Number(user.reviewCount) * 100) + "%");
                    }
                    if (Number(individualratingsCount.rate4) > 0) {
                        $("#howmany4").attr("style", 'width: ' + (Number(individualratingsCount.rate4) / Number(user.reviewCount) * 100) + '%');
                        $("#howmanyvalue4").html((Number(individualratingsCount.rate4) / Number(user.reviewCount) * 100) + "%");
                    }
                    if (Number(individualratingsCount.rate3) > 0) {
                        $("#howmany3").attr("style", 'width: ' + (Number(individualratingsCount.rate3) / Number(user.reviewCount) * 100) + '%');
                        $("#howmanyvalue3").html((Number(individualratingsCount.rate3) / Number(user.reviewCount) * 100) + "%");
                    }
                    if (Number(individualratingsCount.rate2) > 0) {
                        $("#howmany2").attr("style", 'width: ' + (Number(individualratingsCount.rate2) / Number(user.reviewCount) * 100) + '%');
                        $("#howmanyvalue2").html((Number(individualratingsCount.rate2) / Number(user.reviewCount) * 100) + "%");
                    }
                    if (Number(individualratingsCount.rate1) > 0) {
                        $("#howmany1").attr("style", 'width: ' + (Number(individualratingsCount.rate1) / Number(user.reviewCount) * 100) + '%');
                        $("#howmanyvalue1").html((Number(individualratingsCount.rate1) / Number(user.reviewCount) * 100) + "%");
                    }
                }

                var cv = $.trim(user.cv);
                if (cv.length > 280) {
                    cv = cv.substring(0, 280);
                    cv = cv + '...&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">Show More </a>';
                }
                $("#usershortcv").html(cv);

                var workexp = "";
                var allpositions = user.positions;
                if (allpositions.length > 0) {
                    for (var i = 0; i < allpositions.length; i++) {
                        workexp = workexp + '<span><h4 style="padding-top:15px;"><b>'
                                + allpositions[i].title + ' - '
                                + allpositions[i].companyName + ' ('
                                + allpositions[i].startYear + '-'
                                + allpositions[i].endYear
                                + ')</b></h4></span>'
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
                                + '<div class="col-md-3" style="text-transform:capitalize">' + allskills[i].skill + '</div>'
                                + '<div class="col-md-6 ">'
                                + '		<div class="progress">'
                                + ' 		<div class="progress-bar progress-bar-success" '
                                + '		role="progressbar" aria-valuenow="' + per
                                + '" aria-valuemin="0" aria-valuemax="100" style="width: ' + per + '%">'
                                + '<span class="sr-only">' + per
                                + '% </span>' + '</div>' + '	</div>' + '</div>'
                                + '<div class="col-md-3">' + allskills[i].skillYear + ' years</div></div>';
                        $("#skills").append(askill);
                    }
                } else {
                    $("#skills").html("<div>Skills information not available.</div>");
                }

                var allReviews = user.allreviews;
                if (allReviews.length > 0) {
                    for (var i = 0; i < allReviews.length; i++) {
                        var areview = '<div class="panel panel-default">'
                                + '<div class="panel-heading">'
                                + '<div class="row">'
                                + '<div class="col-md-8"><span class="panel-title">'
                                + allReviews[i].ititle + '</span> </div>'
                                + '<div class="col-md-4"><span style="pull-right">'
                                + ' <span style="display:inline-block;"><input type="number" id="userRating-' + i + '" /> </span> '
                                + ' <span>(' + allReviews[i].average + '/5)</span>'
                                + '	</span></div></div></div>'
                                + '<div class="panel-body">' + '<i>' + allReviews[i].message + '</i>'
                                + '<div class="row">'
                                + '<div class="col-md-8"></div>'
                                + '<div class="col-md-4"><span style="pull-right"><i> by '
                                + getUserProfileLink(allReviews[i].ratedby) + ' on <small>'
                                + prettyDate(new Date(Number(allReviews[i].dt))) + '</small></span></div>'
                                + '  </div>' + '  </div>' + '</div>';
                        $("#reviews").append(areview);
                        $("#userRating-" + i + "").val(allReviews[i].average).rating({
                            'min': 0,
                            'max': 5,
                            'step': 1,
                            'readonly': true,
                            'showClear': false,
                            'showCaption': false
                        });

                    }
                } else {
                    $("#reviews").html("<div>No reviews available.</div>");
                }
            }
        });
    }

    /* show jobs offered */
    function showJobsOffered(user) {

        $.ajax({

            url: "<c:url value='getJobsOffered.do'/>",
            type: 'GET',
            async: false,
            success: function (res) {
                var resData = jQuery.parseJSON(res);
                var jobs = resData.jobs;
                var jobsHtml = '<div class="job-posted-header"><i class="fa fa-2x fa-newspaper-o"></i> ' +
                        '&nbsp;<span style="font-size:28px;">Jobs Posted</span>' +
                        '<div class="clearfix pull-right">' +
                        '<a class="btn btn-default" href="<c:url value="/postjob.do"/>">' +
                        '    Post a Job</a>' +
                        '</div></div> ';
                //'<div class="panel-group" id="jobslist"></div>';
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
                    jobsHtml = "You did not posted any jobs yet. Use post job button to post a job to get desire candidate.";
                }
                $("#interviewoffered").html(jobsHtml);
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
