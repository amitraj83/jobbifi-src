<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Job Applications</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div id="wrapper">
    <div id="page-content">
        <div class="container">        
            <div class="row">
	            <div class="col-md-3">
	                <%@ include file="/WEB-INF/pages/employerSidebar.jsp" %>
	            </div>
	
	            <div class="col-md-9">	                
	                <div class="job-posted-header"><i class="fa fa-2x fa-newspaper-o"></i> &nbsp;<span style="font-size:28px;">Jobs Posted</span></div>
	            	<div id="joblist"></div>	            	
	            </div>	            
	        </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>

<script type="text/javascript">
$(function () {
	$("#sidenav_jobapplications").addClass("active");
	showJobsOffered();
});

    /* show jobs offered */
function showJobsOffered() {
    $.ajax({
        url: "<c:url value='getJobsOffered.do'/>",
        type: 'GET',
        async: false,
        success: function (res) {
            var resData = jQuery.parseJSON(res);
            var jobs = resData.jobs;
            var jobsHtml = '';
            if (null != jobs && jobs.length > 0) {
                for (var i = 0; i < jobs.length; i++) {
                    jobsHtml += '<div class="panel panel-default">' +
                            '<div class="panel-heading">' +
                            '<h4 class="panel-title">' +
                            '<a data-toggle="collapse" data-parent="#accordion" href="#collapse' + i + '"><i class="fa fa-plus"></i> ' + jobs[i].title + '</a>' +
                            '<span class="pull-right">' + prettyDate(new Date(jobs[i].dt)) + '</span>' +
                            '<a href="javascript:void()" class="pull-right" onclick="updateJob(\''+ jobs[i].id +'\');" style="cursor: pointer;margin-right: 10px;color: #0072bc;">Edit</a>'+
                            '</h4>' +
                            '</div>' +
                            '<div id="collapse' + i + '" class="panel-collapse collapse">' +
                            '<div class="panel-body">' +
                            '<p>' + jobs[i].description + '</p>' +
                            getApplicantHtml(jobs[i]) +
                            '</div>' +
                            '</div>' +
                            '</div>';
                }
            } else {
                jobsHtml = "You did not posted any jobs yet. Use post job button to post a job to get desire candidate.";
            }
            
            $("#joblist").html(jobsHtml);            
            $(".user-rating").rating({'min':0,'max':5,'step':1,'readonly':true,'showClear': false, 'showCaption': false});
        }
    });
}
    
function updateJob(jid){
	window.location.href = BASE_URL + "editjob.do?jid=" + jid;
}

function getUserProfileLink(user) {
   return "<a target='_blank' href='" + BASE_URL + "userprofile.do?name=" + user + "'>" + user + "</a>";
}

function getApplicantHtml(job){

	var html = '<div class="table-responsive">'+
               '<table class="table table-hover">'+
               '<thead>'+
               '<tr>'+
               '<th>Applicant</th>'+
               '<th>Cover Letter</th>'+
               '<th>Resume</th>'+
               '<th>Date</th>'+
               '<th style="width:15%">Status</th>'+
               '</tr>'+
               '</thead>';
                     
    var applications = job.jobApplications;
    if(applications.length > 0) {
    	for(var i =0; i < applications.length; i++){
    		html += "<tr>" +
    				"<td style='width:25%'>"+
    				"<div class='row'>" +
    				"<div class='col-md-4'><img style='height:70px;width:60px' src='"+applications[i].profilePic +"' title='"+
    				applications[i].applicantId + "' ></div>" +
    				"<div class='col-md-8'>"+
    					getUserProfileLink(applications[i].applicantId)+ "<br/>" +
    					"<input type='number' class='user-rating' value='"+applications[i].rating+"'/>" +
    					"<div><span id='reviewcount'> "+applications[i].reviewCount+"</span> Reviews</div>"+
    				"</div>" +		     				
    				"</div>" +	
					"</td>"+
    				"<td>"+applications[i].coverLetter+"</td>";
    		if(applications[i].cvFileId != ""){
    			html += "<td><a target='_blank' href='"+BASE_URL+applications[i].uploadedFile.url+"'>"+applications[i].uploadedFile.originalFileName+"</a></td>";
    		} else {
    			html += "<td>NA</td>";
    		}		
    				
    				
    		html +=	"<td>"+ prettyDate(new Date(applications[i].dt)) +"</td>"+
    				"<td>"+ getStatusSelectBox(applications[i].status,applications[i].id)+"</td>"+
    				"</tr>";
    	}
    
    } else {
    	html += "<tr><td colspan'5'>No applicant found.</td></tr>";
    }
                            
    html += '</table>' +
            '</div>';
    return html;
}

function getStatusSelectBox(status, applicationId){
	var statuses = ["Applied", "Shortlisted", "Interviewed", "Recruited"];
	var selectHtml = "<select class='form-control' data-appid='"+applicationId+"' onchange='changeApplicationStatus(this)'>";
	for(var i =0; i < statuses.length; i++){		
		if(statuses[i] == status){
			selectHtml += "<option selected='selected' value='"+statuses[i]+"'>"+statuses[i]+"</option>";
		} else {
			selectHtml += "<option value='"+statuses[i]+"'>"+statuses[i]+"</option>";
		}
		
	}
	selectHtml += "</select>";
	return selectHtml;
}

function changeApplicationStatus(select){	
	var status = $(select).val();
	var id = $(select).attr("data-appid");
	$.ajax({
        url: "<c:url value='jobapplication/updatestatus.do'/>",
        type: 'POST',
        async: false,
        data : "id=" + id + "&status=" + status,
        success: function (res) {
        	var resData = jQuery.parseJSON(res);
        	if(resData.status == 1){
        		showSuccess("Application status updated successfully.");
        	} else {
        		showError("Error occured while updating the application status.");
        	}
       	}
    });		       
}
</script>
</body>
</html>
