<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Advisors</title>
</head>

<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-md-12">
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
                        <div id="pagination"></div>
                    </div>
                </div>
                
                <div class="col-md-4 page-sidebar">
                	<div class="clearfix" style="padding-left: 30px; border-left: 1px solid #ddd;">
                	
                		<div style="border: 5px solid rgb(255, 158, 40); padding: 0px 15px 10px;">
                			<h3>Need to talk with advisor ?</h3>
                			<p>Post your Mock interview and invite advisor to train you and for job referrals</p>
  							<div style="text-align:center">
  								<a onclick="showPostAMockScreen()" class="btn btn-success">Post a Mock</a>
                				<!--a href="/publishinterview.do" class="btn btn-success">Post a Mock</a-->
               				</div>
                		</div>
                	
                	
						<h3 style="font-weight: bold;margin: 30px 0 15px;">Latest Jobs</h3>
						<div id="latestjoblist"></div>                
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

        var searchKey = getParameterByName("searchKey");
        if (searchKey != "") {
            $("#searchKey").val(searchKey);
            searchAdvisors(searchKey, 1);
        } else {
            searchAdvisors(null, 1);
        }
        
        loadLatestJob(searchKey);
        
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
        if (searchKey == null) {
            searchKey = "''";
        }

        var start = ((currentPage - 1) * 10);
        $.ajax({
            type: "POST",
            url: BASE_URL + "search.do",
            data: "searchkey=" + searchKey + "&start=" + start,
            //async: false
        }).done(function (msg) {
        	
            var jresponse = jQuery.parseJSON(msg);
            var json = jresponse.JSON_DOC_LIST;
            
            if(jresponse.NUM_OF_RESULTS > 0) {            
	            $.each(json, function (i) {	
	            	
	            	var skills = json[i].skills.replace(/\[/,'').replace(/\]/,'').split(",");
	            	var positions = [];
	            	for(var j=0; j < json[i].additional.positions.length; j++){
	            		var icon =  (j == 0) ? true : false;
	            		positions.push({ position:json[i].additional.positions[j].title ,
	            			company : json[i].additional.positions[j].companyName, icon:icon});
	            	}
	            	
	            	var educations = [];
	            	for(var j=0; j < json[i].additional.educations.length; j++){
	            		var icon =  (j == 0) ? true : false;
	            		educations.push({ education :json[i].additional.educations[j].degree,
	            			field : json[i].additional.educations[j].fieldOfStudy,
	            			school : json[i].additional.educations[j].schoolname,icon:icon});
	            	}
	            	
	                var searchItem =
	                        '<div class="row">' +
	                        '<div class="col-md-3">' +
	                        '<img src="{{ image }}" style="width:100%;height:150px;" alt="..." class="img-thumbnail">' +
	                        '<center><input type="number" value="{{rating}}" class="userRating" /> <span>( {{ rating }} / 5)</span></center>' +
	                        '</div>' +
	                        '<div class="col-md-9">' +
	                        '<div class="advisor-name"><a target="_blank" href="userprofile.do?name={{ username }}">{{ username }}</a></div>' +
	                        '<div class="advisor-title"><strong>Skills</strong> {{ #skills }}<span class="label label-default">{{ . }}</span> {{ /skills }}</div>' +
	                        '<div><span class="advisor-rate"><strong>Rate:&nbsp;&nbsp;</strong><i class="fa fa-inr"></i> {{rate}} /hr</span><span></div>' +
	
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
	                        '</div><hr />';
	
	                templateData = {
	                    'username': json[i].username,
	                    'country': json[i].country,
	                    'rating': parseFloat(json[i].additional.rating).toFixed(1),
	                    'positions': positions,
	                    'skills': skills,
	                    'rate' : json[i].rate,
	                    'educations': educations,
	                    'image': BASE_URL + json[i].additional.profilepic,
	                }
	                
	                console.log(templateData);
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
	                
	            });
            } else {
            	$("#searchAdvisorResult").html("<div class='alert alert-info'>No results found.</div>");
                $(".pagination").hide();
            }

            var totalRecords = jresponse.NUM_OF_RESULTS;
            var html = getPaginationHTMLForAdvisor(totalRecords, currentPage, searchKey, "INTERVIEWER");
            $("#paginationdiv").html(html);
            $('html, body').animate({ scrollTop: 0 }, 'slow');
        });
    }

    function getPaginationHTMLForAdvisor(totalRecords, currentPage, searchKey, searchFor) {
        var totalPages = Math.ceil(totalRecords / PAGE_SIZE);
        var html = "";
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
</script>
</body>
</html>