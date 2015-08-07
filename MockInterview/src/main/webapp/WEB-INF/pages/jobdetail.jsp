<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Job Detail</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div id="wrapper">
    <div id="page-content">
        <div class="container">


            <div class="row">
                <div class="col-md-9">
                    <div>
                        <div class="row pull-right">
                            <div class="col-md-6 jobdetail-userpic">
                                <a target="_blank"
                                   href="<c:url value='/userprofile.do?name=${job.interviewer}'/>">

                                    <img class="img-responsive img-hover img-thumbnail" src="${profilepic}"
                                         alt="User Pic" style="height:100px;"/>
                                </a>
                            </div>
                            <div class="col-md-6 jobdetail-postedby">
                                Posted By
								<span style="padding-top:5px;">
									
										<a href="<c:url value='/userprofile.do?name=${job.interviewer}'/>"
                                           target="_blank" style="color:#0072bc">${job.interviewer}</a>
									
								</span>

                                <div>
                                    <i class="fa fa-clock-o"></i><span id="date"></span>
                                    <!--<input data-showCaption="false" data-showClear="false" data-size="xs"
								    data-readonly="true" data-min="0" data-max="5" data-step="0.5" value="${rating}" 
								    id="postedByRating" type="number" class="rating" />-->
                                </div>

                            </div>
                        </div>
                        <h1 class="title"> ${job.title} <br>
                            <small> ${job.companyName}</small>
                        </h1>

                    </div>
                    <div>
						<span class="job-location"><i class="fa fa-map-marker"></i>&nbsp; 
								${job.location}
						</span>
						<span>
							
						</span>
                    </div>

                    <div style="padding-top: 15px;">
                        <p>
                            Skills:
                            <c:forEach var="skill" items="${job.skills}">
                                <span class="label label-info">${skill}</span> &nbsp;
                            </c:forEach>
                        </p>
                        <hr>
                    </div>
                    <h3>Job Description</h3>

                    <p>${job.description}</p>

                    <p>orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been
                        the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley
                        of type and scrambled it to make a type specimen book. It has survived not only five centuries,
                        but also the leap into electronic typesetting, remaining essentially unchanged. It was
                        popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,
                        and more recently with desktop publishing software like Aldus PageMaker including versions of
                        Lorem Ipsum.</p>

                    <hr/>
                </div>
                <div class="col-md-3">


                </div>

            </div>


            <div class="row">
                <div class="col-md-9 page-content">
                    <div class="clearfix white-container">
                        <!--<p>
								 |  ${job.experience} yr exp. | $${job.salary} | Posted 
								
							</p>
							-->

                    </div>

                    <div class=="clearfix white-container
                    " id="msgdetail">

                </div>
                <sec:authorize access="isAuthenticated()">

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h1 class="panel-title" style="font-size:18px;">Apply for this job</h1>
                        </div>
                        <div class="panel-body">


                            <div class="clearfix white-container">

                                <form class="form form-horizontal" id="jobDetailContactForm">

                                    <div class="form-group">
                                        <label class="col-md-2">Cover Letter</label>

                                        <div class="col-md-10">
                                            <textarea name="message" id="inputmessage" rows="5" cols="80"
                                                      class="form-control"></textarea>
                                        </div>
                                        <input type="hidden" name="jobid" value="${jobid}"/>
                                        <input type="hidden" name="jobtitle" value="${job.title}"/>
                                        <input type="hidden" name="to" value="${job.interviewer}"/>
                                        <input type="hidden" name="parentMessageId" id="parentMessageId" value=""/>
                                        <input type="hidden" name="type" id="type" value=""/>

                                    </div>
                                    <!--  Implement upload logic from here
                                    -->
                                    <div class="form-group">
                                        <label class="col-md-2">Resume</label>

                                        <div class="col-md-8">
                                            <div class="form-inline">
                                                <div class="form-group" style="padding-left:16px;">
                                                    <input type="file" name="files[]" id="js-upload-files" multiple>
                                                </div>
                                            </div>
                                        </div>


                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-offset-2 col-md-10">
                                            <button type="submit" class="btn btn-default pull-right">Send</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <div class="pull-right">
                        <button type="submit" class="btn btn-default">Apply</button>
                    </div>
                </sec:authorize>

            </div>
            <div class="col-md-4 page-sidebar">
                <div class="widget sidebar-widget white-container ">
                    <!-- <div class="row">
								<div class="col-md-6">
									<a href="<c:url value='/userprofile.do?name=${job.interviewer}'/>" target="_blank"><img class="img-responsive img-hover" src="${profilepic}" alt="User Pic" style="height:100px;"></a>
								</div>
								<div class="col-md-6" style="padding:0px 0px 0px 2px">
									Posted By<br><span><a target="_blank" href="<c:url value='/userprofile.do?name=${job.interviewer}'/>">${job.interviewer}</a></span><br>
									<span>
									    <input data-showCaption="false" data-showClear="false" data-size="xs" data-readonly="true" data-min="0" data-max="5" data-step="0.5" value="${rating}" id="postedByRating" type="number" class="rating" >
									</span>									
								</div>								
							</div> -->
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">

    var id = "${jobid}";
    var messageto = "${job.interviewer}";
    var msgfrom = "${username}";
    var length = "";

    $(function () {

        $("#nav_jobs").addClass("active");
        $("#date").html(prettyDate(new Date(Number(${job.dt}))));
        $("#postedByRating").rating({clearButton: "", showCaption: false});
        $("#jobDetailContactForm").validate({
            rule: {
                message: {required: true}
            },
            submitHandler: function (form) {
                submitForm();
                return false;
            }
        });

        loadMessages();
    });

    function loadMessages() {
        var jobsHtml = "";
        $.ajax({
            type: 'GET',
            url: BASE_URL + 'getjobmessage.do',
            data: "iid=" + id + "&messageto=" + messageto,
            async: false
        }).done(function (res) {
            $("#type").val("REPLY");

            var resData = jQuery.parseJSON(res);
            var jobs = resData.JOB_LIST;
            if (null != jobs && jobs.length > 0) {
                length = Number(jobs.length);
                $("#parentMessageId").val(jobs[0].id);
                for (var i = 0; i < jobs.length; i++) {
                    jobsHtml += '<div class="panel panel-default">' +
                            '<div class="panel-heading">' +
                            '<h4 class="panel-title">' +
                            '<a data-toggle="collapse" data-parent="#accordion" href="#collapse' + i +
                            '"><i class="fa fa-plus" ></i> ' + jobs[i].from + '</a>' +
                            '<span class="pull-right">' + prettyDate(new Date(jobs[i].creationDate)) + '</span>' +
                            '</h4>' +
                            '</div>' +
                            '<div id="collapse' + i + '" class="panel-collapse collapse">' +
                            '<div class="panel-body">' +
                            '<p>' + jobs[i].message + '</p>' +
                            '</div>' +
                            '</div>' +
                            '</div>';
                }
            } else {
                length = 0;
                $("#type").val("ORIGINAL");
            }
        });

        $("#msgdetail").html(jobsHtml);
    }


    function submitForm() {
        $.ajax({
            type: 'POST',
            url: BASE_URL + 'jobmessage.do',
            data: $("#jobDetailContactForm").serialize(),
            success: function (res) {
                var jobsHtml = '<div class="panel panel-default">' +
                        '<div class="panel-heading">' +
                        '<h4 class="panel-title">' +
                        '<a data-toggle="collapse" data-parent="#accordion" href="#collapse' + length + '"><i class="fa fa-plus"></i> ' + msgfrom + '</a>' +
                        '<span class="pull-right">' + prettyDate(new Date()) + '</span>' +
                        '</h4>' +
                        '</div>' +
                        '<div id="collapse' + length + '" class="panel-collapse collapse">' +
                        '<div class="panel-body">' +
                        '<p>' + $("#inputmessage").val() + '</p>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                $("#msgdetail").append(jobsHtml)
                length++;
                console.log(res);
            },
            error: function (status) {

            }
        });

        $.ajax({
            type: 'POST',
            url: BASE_URL + 'addcontactlist.do',
            data: $("#jobDetailContactForm").serialize()
        });
    }
</script>
</body>
</html>