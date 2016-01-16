<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Publish Interview</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div id="wrapper">
    <div class="container">
        <div class="row">
            <div class="col-md-3 page-sidebar">
                <%@ include file="/WEB-INF/pages/myInterviewSidebar.jsp" %>
            </div>

            <div class="col-md-9 page-content">

                <div id="interviewee_publishresponse"></div>

                <div class="clearfix white-container">
                    <div id="message"></div>
                    <h1 class="title">Publish Interview</h1>
                    <hr>

                    <form id="publishInterviewForm" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Interview Title</label>

                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="title" placeholder="Title">
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
                                <input type="text" class="form-control" placeholder="Skills Required" name="skills"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Description</label>

                            <div class="col-sm-9">
                                <textarea class="form-control" rows="5" name="description" id="mock-description"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Budget</label>

                            <div class="col-sm-9">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-inr"></i></span>

                                    <input type="text" class="form-control" name="budget"
                                           placeholder="Price you want to pay">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Experience Level</label>

                            <div class="col-sm-9">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="experience"
                                           placeholder="Experience of interviewer">
                                    <span class="input-group-addon">years</span>
                                </div>
                            </div>
                        </div>
                        <!--<div class="form-group">
                            <label class="col-sm-3 control-label">Upload Document</label>

                            <div class="col-sm-9">
                                <input id="postinterviewdoc" type="file" name="file"
                                       data-url="<c:url value='/aauth/fileupload.do?type=interviewdoc' />" multiple
                                       style="opacity:0;  filter:alpha(opacity: 0);"
                                       data-sequential-uploads="true" class="pull-left hide">
                                <button type="button" class="btn btn-sm btn-info pull-left" id="attachfile">Attach a
                                    file
                                </button>
                                <img id="fileloader" style="display: none;" alt="Processing..."
                                     src="<c:url value="/resources/img/loading.gif" />">

                                <div style="clear: both" id="selectedfile"></div>
                                <input type="hidden" name="ifile" id="interviewdocid">
                            </div>
                        </div>-->
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" class="btn btn-default">Publish Interview</button>
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
        $("#publishinterview").addClass("active");
        $('#mock-description').ckeditor();

        $("#publishInterviewForm").validate({
            rules: {
                title: {required: true},
                industry: {required: true},
                skills: {required: true},
                budget: {required: true, number: true},
                experience: {required: true, number: true},
                description: {required: true},
            },messages: {
            	title: "Interview title is required.",
            	industry: "Industry is required.",
            	skills: "Skill is required.",
            	budget: "Budget is required.",
            	experience: "Experience is required.",
            	description: "Description is required.",
            },
            submitHandler: function (form) {
                submitForm();
                return false;
            },
            errorPlacement: function (error, element) {
                if (element.attr("name") == "budget" || element.attr("name") == "experience") {
                    error.insertAfter(element.parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });

        /* file upload */
        $('#postinterviewdoc').fileupload({
            dataType: 'json',
            maxChunkSize: 20000000,
            done: function (e, data) {
                var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
                if (jsonResponse && jsonResponse._id) {
                    var html = "<a target='_blank' href='" + BASE_URL + jsonResponse.url + "' >" + jsonResponse.originalfn + "</a><br/>";
                    $("#selectedfile").html(html);
                    $("#interviewdocid").val(jsonResponse._id);
                } else {
                    message("Unable to upload the file.","danger");
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
            $('#postinterviewdoc').trigger('click');
        });

    });

    function submitForm() {
        $("#submitloader").show();
        $.ajax({
            type: "POST",
            url: "<c:url value='postinterview.do'/>",
            data: $("#publishInterviewForm").serialize(),
            async: false
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            if (resData.status == 1) {
                message("Your interview is posted successfully.","success");
                $('#publishInterviewForm').trigger("reset");
                $("#selectedfile").html("");
            } else {
                message("Your interview was not posted. Please Try Again.","danger");
            }
        }).always(function (jqXHR, textStatus) {
            $("#submitloader").hide();
        });
    }
    
</script>
</body>
</html>
