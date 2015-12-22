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
                    <div class="alert alert-success" id="successJobApply" name = "successJobApply"> Your application received.</div>
                        <div class="row pull-right">
                            <div class="col-md-6 jobdetail-userpic">
                                <a target="_blank"
                                   href="<c:url value='/userprofile.do?name=${job.interviewer}'/>">

                                    <img class="img-responsive img-hover img-thumbnail" src="${profilepic}"
                                         alt="User Pic"
                                         v style="height:100px;"/>
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
						<span class="job-location">
						<i class="fa fa-map-marker"></i>&nbsp; ${job.location} | Industry : ${job.industry} | Exp. : ${job.experience} yr | Salary : ${job.salary}

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

					<c:if test="${not empty job.companyDescription or not empty job.companyVideo}">
						<h3>Company Description</h3>
						<p>${job.companyDescription}</p>						
						<c:if test="${not empty job.companyVideo}">
							<div id="youTubeVideo"></div>						 	
						 </c:if> 
					</c:if>
                    <hr/>
                </div>
                <div class="col-md-3">


                </div>
            </div>


            <div class="row">
                <div class="col-md-9 page-content">                    
                    <div class="clearfix white-container" id="msgdetail">
					
                     <sec:authorize access="isAuthenticated()">                
	                	<c:choose>
	                		<c:when test="${not empty jobApplication}">
	                			<div class="alert alert-success">You have already sent the application.</div>
	                			
	                			<div class="panel panel-default">
	                				<div class="panel-heading"><h4 class="panel-title">Job Application Details<span class="pull-right">05:21 PM</span></h4></div>
	                				<div class="panel-body">
	                					<p>${jobApplication.coverLetter}</p>
	                					<c:if test="${not empty uploadedFile}">
	                						<p><a href="${uploadedFile.URL}" target="_blank">${uploadedFile.originalFileName}</a></p>
	                					</c:if>
	               					</div>
	            				</div>
	                		</c:when>
	                		<c:otherwise>
	                			<div class="panel panel-default" id="jobApplicationPanel">
	                			<span id ="errorDoc" name = "errorDoc"><code>Please select Resume.</code><br /></span>
	                			<span id="errorTCover" name = "errorTCover"> <code>Please enter details in Cover letter.</code></span>
	                			<span id="coverlettererror" name = "errorTCover"> <code>Cover letter must be alteast 100 characters long.</code></span>
			                        <span id="coverTextSize" name = "coverTextSize"><code> Cover letter is to small,At least  contains more than 250 words. </code></span> 
									<span id="errorFileType" name = "errorFileType" ><code>Please select proper file (.doc,docx,pdf will be accepted).</code></span>
									
			                        <div class="panel-heading">
			                            <h1 class="panel-title" style="font-size:18px;">Apply for this job</h1>
			                        </div>
			                        <div class="panel-body">
			                            <div class="clearfix white-container">
			                                <form class="form form-horizontal" id="jobApplicationForm">
			                                	<input type="hidden" name="jobId" value="${jobid}">                                	
			                                    <div class="form-group">
			                                        <label class="col-md-2">Cover Letter</label>
			                                        <div class="col-md-10">
			                                            <textarea name="coverLetter" id="coverLetter" rows="5" cols="80"
			                                                      class="form-control"></textarea>
			                                        </div>                                        
			                                    </div>
			                                    <div class="form-group">
			                                        <label class="col-md-2">Resume</label>
			                                        <div class="col-md-8">
			                                            <div class="form-inline">
			                                                <div class="form-group" style="padding-left:16px;">
			                                                    <input id="jobapplicationdoc" type="file" name="file"
								                                       data-url="<c:url value='/aauth/fileupload.do?type=jobapplicationdoc&jobid=${jobid}' />" multiple style="opacity:0;
														      		filter:alpha(opacity: 0);" class="pull-left hide">
								                                <button id="attachfile" type="button" class="btn btn-sm btn-info">Upload Resume</button>
								                                <img id="fileloader" style="display: none;" alt="Processing..." src="<c:url value="/resources/img/loading.gif" />">				
								                                <div id="selectedfile" style="clear:both"></div>
								                                <input type="hidden" name="cvFileId" id="jobapplicationid">
			                                                </div>
			                                            </div>
			                                        </div>
			
			
			                                    </div>
			                                    <div class="form-group">
			                                        <div class="col-md-offset-2 col-md-10">
			                                            <div id="applyexception"></div><button type="submit" class="btn btn-default pull-right">Apply</button>
			                                        </div>
			                                    </div>
			                                </form>
			                            </div>
			
			                        </div>
			                    </div>
	                		</c:otherwise>
	                	</c:choose>
	                    
	                </sec:authorize> 	
                </div>                
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
	var jobapplicationdocVal = '';

	$("#successJobApply").hide();
	/*Get uploaded file name for checking file ext and selected or not.   */
   $('#jobapplicationdoc').bind('change', function() {
	 jobapplicationdocVal = $('#jobapplicationdoc').val(); 
	});

	$('#errorDoc').hide();
	$('#errorTCover').hide();
	$("#coverlettererror").hide();
	
	$("#errorFileType").hide();
	$("#coverTextSize").hide();
	
    var id = "${jobid}";    
    $(function () {
    	/* file upload */
        $('#jobapplicationdoc').fileupload({
            dataType: 'json',
            maxChunkSize: 20000000,
            done: function (e, data) {
                var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
                if (jsonResponse && jsonResponse._id) {
                    var html = "<a target='_blank' href='" + BASE_URL + jsonResponse.url + "' >" + jsonResponse.originalfn + "</a><br/>";
                    $("#selectedfile").html(html);
                    $("#jobapplicationid").val(jsonResponse._id);
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
            $('#jobapplicationdoc').trigger('click');
        });
    	
        $("#nav_jobs").addClass("active");
        $("#date").html(prettyDate(new Date(Number(${job.dt}))));
        $("#postedByRating").rating({clearButton: "", showCaption: false});
        $("#jobApplicationForm").validate({
            rule: {
                message: {required: true}
            },
            submitHandler: function (form) {
                submitForm();
                return false;
            }
        });
    });

    function submitForm() {    	    	
        var  coverLetterVal = $('#coverLetter').val(); 

        

		if(jobapplicationdocVal==''){ 
        	$('#errorDoc').show();
    	}else{
    		$('#errorDoc').hide();
    	} 
    	
		if(coverLetterVal==''){ 
        	$('#errorTCover').show();
    	}else{
    		$('#errorTCover').hide();
    	} 
        
		if(coverLetterVal.length > 0 && coverLetterVal.length < 100)
        {
        	$("#coverlettererror").show();
        }
        else
        	$("#coverlettererror").hide();


		if(coverLetterVal != '' &&  jobapplicationdocVal != ''){
			//check for the file extension.
			var ext = jobapplicationdocVal.split('.').pop().toLowerCase();
			
			if($.inArray(ext, ['doc','docx','pdf']) == -1) {
			     $("#errorFileType").show();
			}else{
				$("#errorFileType").hide();
			}	
			
			//check the cover letter size .
			var coverLetterTxtSize = coverLetterVal.length;
			
			if(coverLetterTxtSize < 250 ){
			   $("#coverTextSize").show();
			}else{
				$("#coverTextSize").hide();
			}
			
			
			if(coverLetterTxtSize > 250 && $.inArray(ext, ['doc','docx','pdf']) != -1 ){
			$.ajax({
	            type: 'POST',
	            url: BASE_URL + 'jobapplication/save.do',
	            data: $("#jobApplicationForm").serialize(),
	            success: function (res) {
	            	var json = jQuery.parseJSON(res);
	            	$("#successJobApply").show();
	            	if(json.status == 1) {
	            		$("#jobApplicationPanel").hide();
	                    var jobsHtml = '<div class="panel panel-default">' +
	                            '<div class="panel-heading">' +
	                            '<h4 class="panel-title">Job Application' +
	                            '<span class="pull-right">' + prettyDate(new Date()) + '</span>' +
	                            '</h4>' +
	                            '</div>' +
	                            '<div class="panel-body">' +
	                            '<p>' + $("#coverLetter").val() + '</p>' +
	                            $("#selectedfile").html() +
	                            '</div>' +
	                            '</div>';
	                    $("#msgdetail").html(jobsHtml);	
	                    
	                    $("#selectedfile").html("");
	                    $("#jobapplicationid").val("");
	                	
	                    
	            	} else {
	            		$("#msgdetail").html("<div class='alert alert-error'>Error occured while processing request.</div>");
	            	}           	                
	            },
	            error: function (status) {
	            	console.log(status);
	            }
	        });	
		}
		}
    }    
    
    <c:if test="${not empty job.companyVideo}">
    	var v = gupurl("v", '${job.companyVideo}');    
    	var html = '<iframe width="420" height="315" src="http://www.youtube.com/embed/'+v+'"></iframe>';
    	$("#youTubeVideo").html(html);
   	</c:if>
</script>
</body>
</html>