<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Advisors</title>
</head>

<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<sec:authorize access="isAuthenticated()" var="isAuthenticated"></sec:authorize>
<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-md-12" style="padding-left:2px">
                            <h2>Search Advisors</h2>
							<div id="searchJobMessage"></div>
                            <div class="input-group">
                                <input id="searchKey" type="text" class="form-control" placeholder="Search Advisors">
                                            <span class="input-group-btn">
                                    <button id="searchAdvisorBtn" class="btn btn-default" type="button">Search</button>
                                </span>
                            </div>
                        </div>
                    </div>                    
                    <div class="row">
                        <div id="searchAdvisorResult" class="col-md-12"></div>
                    </div>
                    <div class="row">
                        <div id="paginationdiv"></div>
                    </div>
                </div>
                
                <div class="col-md-4 page-sidebar">
                	<div class="clearfix" style="padding-left: 30px; border-left: 1px solid #ddd;">
                	

                        <div>
                        <center>
                            <h3>What's Next?</h3>    
                            <div class="stepwizard" style="height:160px;">
                            <div class="stepwizard-row">
                                <div class="stepwizard-step first-button">
                                    <button type="button" class="btn btn-success btn-square">Select your Advisor</button>
                                </div>
                            </div>
                            <div class="stepwizard-row">   
                                <div class="stepwizard-step second-button">
                                    <button type="button" class="btn btn-info btn-square">Ask Advisor for Referrals</button>
                                </div>
                            </div>
                            <div class="stepwizard-row">   
                                <div class="stepwizard-step third-button">
                                    <button type="button" class="btn btn-info btn-square">Mock interview with Advisor</button>
                                </div>
                            </div>

                            <div class="stepwizard-row">   
                                <div class="stepwizard-step">
                                    <button type="button" class="btn btn-warning btn-square">Succeed in real interview!</button>
                                </div>
                            </div>
                            
                        </center>
                        </div>




                    
                		<div style="display:none; border: 5px solid rgb(255, 158, 40); padding: 0px 15px 10px;">
                			<h3>Need to talk with advisor ?</h3>
                			<p>Post your Mock interview and invite advisor to train you and for job referrals</p>
  							<div style="text-align:center">
  								<a onclick="showPostAMockScreen()" class="btn btn-success">Post a Mock</a>
                				<!--a href="/publishinterview.do" class="btn btn-success">Post a Mock</a-->
               				</div>
                		</div>
                	
                	   <div style="display:none;">
						<h3 style="font-weight: bold;margin: 30px 0 15px;">Latest Jobs</h3>
						<div id="latestjoblist"></div>                
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
</div>
<!-- Contact Me Modal -->
<div class="modal fade bs-example-modal-sm" id="contactmeModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog login-modal" style="width: 450px;">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #0072bc;padding: 8px;color: white;">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="myModalLabel" style="margin-left: 23px;">Message </h4>
            </div>
            <div class="modal-body">
                <div class="row" style="padding: 15px;">
                    <div class="col-md-12">
                        <div id="contactme" class="mainbox col-md-12">
                            <form class="form form-horizontal" id="newmessageForm">
                                <input type="hidden" name="jobid" value=""/>
                                <input type="hidden" name="jobtitle" value=""/>
                                <input type="hidden" name="parentMessageId" value=""/>
                                <input type="hidden" name="refentity" value=""/>
                                <input type="hidden" name="to"/>

                                <div class="form-group">
                                    
                                    <div class="col-md-12">
                                        <textarea name="message" id="inputmessage" rows="5" cols="80"
                                                  class="form-control" style="font-size: 11px;height: 200px;resize: vertical; ">

                                        </textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <button type="submit" id="newmessage" class="btn btn-success" style="width:100%">Send Message</button>
                                        <img id="contactMeloader" style="display: none;" alt="Processing..."
                                             src="<c:url value=" /resources/img/loading.gif " />">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">

	function searchJobMessage() {
	    $("#searchJobMessage").html("<div class='alert alert-danger'> Please enter what are you looking for? </div>");
	    setTimeout(function () {
	        $("#searchJobMessage").html("");
	    }, 3600 * 2);
	}
	
    $(document).ready(function () {

    	if(${isAuthenticated} == false){
    		$(".scroll.active").removeClass("active");
    	}else{
	    	$("#nav_advisors").addClass("active");
    	}
        var searchKey = getParameterByName("searchKey");
        if (searchKey != "") {
            $("#searchKey").val(searchKey);
            searchAdvisors(searchKey, 1);
        } else {
            searchAdvisors(null, 1);
        }
        
        //loadLatestJob(searchKey);
        
        $("#searchAdvisorBtn").click(function (e) {
        	var searchKey = $("#searchKey").val();
       	    if (searchKey == "") {
                 //showWarning("Please enter the search term you want to search.");
                 searchJobMessage();
                  return;
            }
            searchAdvisors(searchKey, 1);
        });

        $("#searchKey").on("keypress", function (e) {
            search(e);
        });
        
        $("#newmessageForm").validate({
            rules: {
                message: {
                    required: true
                },
            },
            submitHandler: function (form) {
                submitForm();
                return false;
            }
        });
    });

    function search(e) {
        if (e.which == 13) {
            var searchKey = $("#searchKey").val();
            if (searchKey == "") {
                //showWarning("Please enter the search term you want to search.");
                searchJobMessage();
                return;
            }
            searchAdvisors(searchKey, 1);
            return false;
        }
    }
    
    function showPostAMockScreen(){
    	if (LOGIN_USER == null) {
            showLoginBox();
            return;
        }else{
        	window.location = "/publishinterview.do";
        }
    }
    

    var PAGE_SIZE = 10;
    function searchAdvisors(searchKey, currentPage) {

        $("#searchAdvisorResult").html("");
        if (searchKey == null || searchKey=="") {
            $.ajax({
                type: "POST",
                url: BASE_URL + "gettopadvisor.do",
                data: "noOfResult=5",
            }).done(function (res) {
                var jsonResult = jQuery.parseJSON(res); 
                var json = jsonResult.advisorList;
        
                    for(var i =0; i < json.length; i++)    
                    {   
                        var j = json[i];
                        var skills=jQuery.parseJSON(j.skills); //.split(",");
                        var positions = [];
                        for(var j=0; j < json[i].positions.length; j++){
                            var icon =  (j == 0) ? true : false;
                            positions.push({ position:json[i].positions[j].title ,
                                company : json[i].positions[j].companyName, icon:icon});
                        }
                        
                        var educations = [];
                        for(var j=0; j < json[i].educations.length; j++){
                            var icon =  (j == 0) ? true : false;
                            educations.push({ education :json[i].educations[j].degree,
                                field : json[i].educations[j].fieldOfStudy,
                                school : json[i].educations[j].schoolname,icon:icon});
                        }
                        
                        var description = json[i].cv;
                        var descLess = description.substring(0, 170);
                        if (description.length > 170) {
                            descLess += "...";
                        }

                        var visibleName = json[i].username.replace(/\b[a-z]/g, function(letter) {
                                            return letter.toUpperCase();
                                        })  

                        var searchItem =
                                    '<div class="row white-bg">' +
                                    '<div class="col-md-2 text-center" style="padding:0px;">' +
                                    '<a target="_blank" href="userprofile.do?name={{ username }}" style="font-weight: bold;">' +
                                    /*'<img class="img-thumb" alt="" src="' + BASE_URL + job.profilepic + '">' +*/
                                    '<img class="img-thumb circle-pic" alt="" src="{{ image }}">' +
                                    '</a>' +
                                    '<a onclick="showContactMeScreen(\'{{ username }}\');" href="#"><i class="fa fa-envelope-o"></i> <span style=" style="color: #0072bc; !important">Contact me</span></a>'+
                                    /*'<button onclick="showContactMeScreen(\'{{ username }}\');" class="btn btn-default blue-button btn-xs" type="button"><i class="fa fa-envelope-o"></i> Contact me</button>' +*/
                                    '</div>' +
                                    '<div class="col-md-10">' +
                                    '<div class="row" style="padding:4px;font-size: 20px;">' +
                                    '<div class="col-md-12">' +
                                    '<span class="job_search_result_title"><a target="_blank" href="userprofile.do?name={{ username }}">'+visibleName+'</a></span>' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="row" style="padding:4px; font-size: 12px;">' +
                                    '<div class="col-md-12">' +
                                    '<span class="job_search_result_location"><i class="fa fa-map-marker"></i> {{ country }}</span>' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="row" style="padding:4px;font-size: 12px;">' +
                                    '<div class="col-md-12">' +
                                    '<table class="advisor-result-table">' +
                                    '{{ #positions }}' +
                                    '<tr>' +
                                    '<td><i {{#icon}}class="fa fa-suitcase"{{/icon}} style="margin-right:15px"></i></td>' +
                                    '<td>{{ position }} at {{company}}</td>' +
                                    '</tr>' +
                                    '{{ /positions }}' +
                                    '<tr>' +
                                    '{{ #educations }}' +
                                    '<td><i {{ #icon }} class="fa fa-graduation-cap"{{ /icon }} style="margin-right:15px"></i></td>' +
                                    '<td>{{ education }}, {{ field}} , {{school}}</td>' +
                                    '</tr>' +
                                    '{{ /educations }}' +
                                    '</table>' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="row" style="padding:4px;font-size: 12px;">' +
                                    '<div class="col-md-12">' +
                                    '<span class="job_search_result_description"><dl class="dl-horizontal"><dd>'+descLess+' </dd></dl></span>' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="row"  style="padding:4px;font-size: 12px;"><div class="col-md-12">' +
                                    '<div class="advisor-title"> {{ #skills }}<span class="label label-info">{{ . }}</span>&nbsp; {{ /skills }}</div>' +
                                    '</div></div>' +
                                    '</div>' +
                                    '</div>';

                                  
                        templateData = {
                            'username': json[i].username,
                            'country': json[i].country,
                            'rating': parseFloat(json[i].avgRating).toFixed(1),
                            'positions': positions,
                            'skills': skills,
                            'rate' : json[i].rate,
                            'educations': educations,
                            'image': BASE_URL + json[i].profilepic
                            /*'image': BASE_URL + "images/face.jpg",*/
                        }
                        
                        searchItem = Mustache.to_html(searchItem, templateData);
                        $("#searchAdvisorResult").append(searchItem);
                        
                        $("input.userRating").rating({
                            'min': 0,
                            'max': 5,
                            'step': 1,
                            'readonly': true,
                            'showClear': false,
                            'showCaption': false
                        });
                        
                    }
                    //);
                
                var totalRecords = jresponse.NUM_OF_RESULTS;
                var html = getPaginationHTMLForAdvisor(totalRecords, currentPage, searchKey, "INTERVIEWER");
                $("#paginationdiv").html(html);
                $('html, body').animate({ scrollTop: 0 }, 'slow');
                
            });
        }
        else{
        $(".pagination").hide();
        var start = ((currentPage - 1) * 10);
        var searchparam = "searchkey=" + encodeURIComponent(searchKey) + "&start=" + start; 
            $.ajax({
                type: "POST",
                url: BASE_URL + "search.do",
                data: searchparam
                //async: false
            }).done(function (msg) 
            {
            	
                var jresponse = jQuery.parseJSON(msg);
                var json = jresponse.data;
                
                if(json.length == 0)
                {
                    $("#searchAdvisorResult").html("<div class='alert alert-info'>No results found.</div>");
                    $(".pagination").hide();
                }                    
                else
                {   
                    for(var i =0; i < json.length; i++)    
                    {	
                        var j = json[i];
                        var skills=j.skills;
                    	var positions = [];
    	            	for(var j=0; j < json[i].positions.length; j++){
    	            		var icon =  (j == 0) ? true : false;
    	            		positions.push({ position:json[i].positions[j].title ,
    	            			company : json[i].positions[j].companyName, icon:icon});
    	            	}
    	            	
    	            	var educations = [];
    	            	for(var j=0; j < json[i].educations.length; j++){
    	            		var icon =  (j == 0) ? true : false;
    	            		educations.push({ education :json[i].educations[j].degree,
    	            			field : json[i].educations[j].fieldOfStudy,
    	            			school : json[i].educations[j].schoolname,icon:icon});
    	            	}
    	            	
    	                var description = json[i].cv;
                        var descLess = description.substring(0, 170);
                        if (description.length > 170) {
                            descLess += "...";
                        }

                        var searchItem =
                                    '<div class="row white-bg">' +
                                    '<div class="col-md-2 text-center" style="padding:0px;">' +
                                    '<a target="_blank" href="userprofile.do?name={{ username }}" style="font-weight: bold;">' +
                                    /*'<img class="img-thumb" alt="" src="' + BASE_URL + job.profilepic + '">' +*/
                                    '<img class="img-thumb circle-pic" alt="" src="{{ image }}">' +
                                    '</a>' +
                                    '<a onclick="showContactMeScreen(\''+json[i].username+'\');" href="#"><i class="fa fa-envelope-o"></i> <span style=" style="color: #0072bc; !important">Contact me</span></a>'+
                                    /*'<button onclick="showContactMeScreen(\'{{ username }}\');" class="btn btn-default blue-button btn-xs" type="button"><i class="fa fa-envelope-o"></i> Contact me</button>' +*/
                                    '</div>' +
                                    '<div class="col-md-10">' +
                                    '<div class="row" style="padding:4px;font-size: 20px;">' +
                                    '<div class="col-md-12">' +
                                    '<span class="job_search_result_title"><a style="font-weight: bold;" target="_blank" href="userprofile.do?name='+json[i].username+'">{{ username }}</a></span>' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="row" style="padding:4px; font-size: 12px;">' +
                                    '<div class="col-md-12">' +
                                    '<span class="job_search_result_location"><i class="fa fa-map-marker"></i> {{ country }}</span>' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="row" style="padding:4px;font-size: 12px;">' +
                                    '<div class="col-md-12">' +
                                    '<table class="advisor-result-table">' +
                                    '{{ #positions }}' +
                                    '<tr>' +
                                    '<td><i {{#icon}}class="fa fa-suitcase"{{/icon}} style="margin-right:15px"></i></td>' +
                                    '<td>{{ position }} at {{company}}</td>' +
                                    '</tr>' +
                                    '{{ /positions }}' +
                                    '<tr>' +
                                    '{{ #educations }}' +
                                    '<td><i {{ #icon }} class="fa fa-graduation-cap"{{ /icon }} style="margin-right:15px"></i></td>' +
                                    '<td>{{ education }}, {{ field}} , {{school}}</td>' +
                                    '</tr>' +
                                    '{{ /educations }}' +
                                    '</table>' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="row" style="padding:4px;font-size: 12px;">' +
                                    '<div class="col-md-12">' +
                                    '<span class="job_search_result_description"><dl class="dl-horizontal"><dd>'+descLess+' </dd></dl></span>' +
                                    '</div>' +
                                    '</div>' +
                                    '<div class="row"  style="padding:4px;font-size: 12px;"><div class="col-md-12">' +
                                    '<div class="advisor-title"> {{ #skills }}<span class="label label-info">{{ . }}</span> {{ /skills }}</div>' +
                                    '</div></div>' +
                                    '</div>' +
                                    '</div>';

                         var thisUser = json[i].username.replace(/\b[a-z]/g, function(letter) {
                                            return letter.toUpperCase();
                                        })           
                        templateData = {
    	                    'username': thisUser,
    	                    'country': json[i].country,
    	                    'rating': parseFloat(json[i].avgRating).toFixed(1),
    	                    'positions': positions,
    	                    'skills': skills,
    	                    'rate' : json[i].rate,
                            'educations': educations,
    	                    'image': BASE_URL + json[i].profilepic,
                            /*'image': BASE_URL + "images/face.jpg",*/
    	                }
    	                
    	                searchItem = Mustache.to_html(searchItem, templateData);
    	                $("#searchAdvisorResult").append(searchItem);
    	                
    	                $("input.userRating").rating({
    	                    'min': 0,
    	                    'max': 5,
    	                    'step': 1,
    	                    'readonly': true,
    	                    'showClear': false,
    	                    'showCaption': false
    	                });
    	                
    	            }
                    //);
                } 
                
                var totalRecords = jresponse.NUM_OF_RESULTS;
                var html = getPaginationHTMLForAdvisor(totalRecords, currentPage, searchKey, "INTERVIEWER");
                $("#paginationdiv").html(html);
                $('html, body').animate({ scrollTop: 0 }, 'slow');
            });

        }
    }

    function getPaginationHTMLForAdvisor(totalRecords, currentPage, searchKey, searchFor) {
        var totalPages = Math.ceil(totalRecords / PAGE_SIZE);
        var html = "";
        if(searchKey=="''"){
            searchKey="";
        }
        if (totalPages > 1) {
            html += "<ul class='pagination pull-right'>"
            for (var i = 0; i < totalPages; i++) {
                if (currentPage == (i + 1)) {
                    html = html + "<li class='active'><a href='javascript:void(0)'>" + (i + 1) + "</a></li>";
                } else {
                    html = html + "<li class=''><a href='javascript:searchAdvisors(\"" + searchKey + "\"," + (i + 1) + ")'>" + (i + 1) + "</a></li>";
                }
            }
            html += "</ul>"
        }
        return html;
    }
    
    var jobItem = '<div class="latest-job">'+
	  '<a class="job-title" target="_blank" href=" '+BASE_URL+'jobdetail.do?jid={{id}}" title="{{title}}" >{{ title }}</a>'+
	  '<p class="job-skill"> {{ #skills }} <span class="label label-info">{{ . }}</span> {{ /skills }} </p>'+
	  '<p class="job-company">{{companyname}}</p>'+
	  '<p class="job-location">{{location}}</p>' +
	  '</div><hr class="job-line">';
    
    function loadLatestJob(searchKey){    	
    	searchKey =  (searchKey == null || searchKey == "") ? "''" : searchKey;
    	$.ajax({
            type: "GET",
            url: BASE_URL + "searchjobs.do",
            data: "searchkey=" + searchKey + "&start=0&rows=5",
        }).done(function (res) {

        	var jsonResult = jQuery.parseJSON(res);
			var jsonDocList = jsonResult.JSON_DOC_LIST;                        
			if (null != jsonDocList && Object.keys(jsonDocList).length > 0) {
                $.each(jsonDocList, function (i) {                	                	
                	var templateData = {
                		'id' : jsonDocList[i].id, 
                		'title' : jsonDocList[i].title,
                		'skills' : jsonDocList[i].skills,
                		'companyname' : jsonDocList[i].companyname,
                		'location' : jsonDocList[i].location
                	};
                	var html = Mustache.to_html(jobItem, templateData);
                	$("#latestjoblist").append(html);
                	
                });
            }
        });
    }
    function showContactMeScreen(interviewer) {
        if (LOGIN_USER == null) {
            showInfo("You need to login to contact " + interviewer);
            showLoginBox();
            return;
        }

        $('input:hidden[name="to"]').val(interviewer);
        $("#contactmeModal").modal("show");
        $("#myModalLabel").html("Message to "+interviewer);
        $("#inputmessage").val("Dear "+interviewer+",\r\n\r\nI found you as the  best advisor for me. Can you please help me to"+
            "\r\n1. Prepare my CV\r\n2. CV Referrals\r\n3. Mock Interviews.\r\n\r\nLooking forward for your reply.\r\n\r\nRegards,\r\n"+LOGIN_USER);
    }
    function submitForm() {
        $("#contactMeloader").show();
        $.ajax({
            type: 'POST',
            url: BASE_URL + 'sendnewmessage.do',
            data: $("#newmessageForm").serialize(),
            async: false
        }).done(function (res) {
            $("#contactmeModal").modal("hide");
            showSuccess("Your message has been sent.");
            $("#inputmessage").val("");
        }).always(function (jqXHR, textStatus) {
            $("#contactMeloader").hide();
        });
    }
</script>
</body>
</html>