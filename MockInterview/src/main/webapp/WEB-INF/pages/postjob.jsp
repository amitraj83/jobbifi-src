<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Post Your Job</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>


<div id="wrapper">
    <div class="container">
        <div class="row">
            <div class="col-md-3 page-sidebar">
                <%@ include file="/WEB-INF/pages/employerSidebar.jsp" %>
            </div>
            <div class="col-md-9 page-content">
                <div class="white-container">
                    <h1 class="title">Post Your Job</h1>
                    <hr>
                    <form class="form-horizontal" role="form" id="postJobForm">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Job Title</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="title" placeholder="Job Title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Company Name</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="companyname" placeholder="Company Name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Job Location</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="location" placeholder="Job Location">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Industry</label>

                            <div class="col-sm-9">
                                <select class="form-control" name="industry">
                                    <option value="">Select Industry</option>
                                    <option value="Software Development">Software Development</option>
                                    <option value="Finance">Finance</option>
                                    <option value="Business Development">Business Development</option>
                                    <option value="Technology">Technology</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Skills Required</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="skills" placeholder="Skills Required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Job Description</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" id="job-description" name="description" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Company Video</label>
                            <div class="col-sm-9">
                                <input type="text" id="companyvideo" class="form-control" name="companyvideo" placeholder="Only youtube video">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Company Description</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" name="companydescription" rows="5"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Experience</label>

                            <div class="col-sm-9">
                                <div class="input-group">
                                    <input type="text" placeholder="Experience required for job" name="experience"
                                           class="form-control">
                                    <span class="input-group-addon">Years</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Salary</label>

                            <div class="col-sm-9">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-inr"></i></span>
                                    <input type="text" placeholder="Job Salary" name="salary" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Apply Url</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="applyurl" placeholder="Apply URL">
                            </div>
                        </div>
                       
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" class="btn btn-default">Post Job</button>
                                <img id="submitloader" style="display: none;" alt="Processing..."
                                     src="<c:url value="/resources/img/loading.gif" />">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>

<script type="text/javascript">
    $(function () {
        $("#sidenav_postjob").addClass("active");
        $("#job-description").ckeditor();

        $("#postJobForm").validate({
            rules: {
                title: {required: true},
                companyname: {required: true},
                location: {required: true},
                industry: {required: true},
                description: {required: true},
                salary: {required: true, number: true},
                skills: {required: true},
                experience: {required: true, number: true}
            },
            errorPlacement: function (error, element) {
                if (element.attr("name") == "salary" || element.attr("name") == "experience") {
                    error.insertAfter(element.parent());
                } else {
                    error.insertAfter(element);
                }
            },
            submitHandler: function (form) {
                submitPostJobForm();
                return;
            }
        });

        /* file upload */
        $('#postjobdoc').fileupload({
            dataType: 'json',
            maxChunkSize: 20000000,
            done: function (e, data) {
                var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
                if (jsonResponse && jsonResponse._id) {
                    var html = "<a target='_blank' href='" + BASE_URL + jsonResponse.url + "' >" + jsonResponse.originalfn + "</a><br/>";
                    $("#selectedfile").html(html);
                    $("#jobdocid").val(jsonResponse._id);
                } else {
                    showError("Unable to upload the file.");
                }
                $("#fileloader").hide();
            },
            add: function (e, data) {
                data.submit();
                $("#fileloader").show();
            }
        });

        /* trigger file upload on button */
        $("#attachfile").click(function () {
            $('#postjobdoc').trigger('click');
        });
    });

    function submitPostJobForm() {

        $("#submitloader").show();
        $.ajax({
            type: "POST",
            url: "<c:url value='/postjob.do'/>",
            data: $("#postJobForm").serialize(),
            async: false
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            if (resData.status == 1) {
                showSuccess("Your job is posted successfully.");
                $('#postJobForm').trigger("reset");
                $("#selectedfile").html("");

            } else {
                showError("Your job was not posted. Please try again.");
            }
        }).always(function (jqXHR, textStatus) {
            $("#submitloader").hide();
        });
    };
</script>
</body>
</html>