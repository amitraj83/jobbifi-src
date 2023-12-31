<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Interviews</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div id="wrapper">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <%@ include file="/WEB-INF/pages/myInterviewSidebar.jsp" %>
            </div>

            <div class="col-md-9">
            	<div class="row">
            		<div id="message"></div>
            	</div>
                <div class="row">
						<div id="allInterview" class="col-md-12"
							style="margin-top: 0px; margin-bottom: 10px;">
							<h1>All Interview </h1>
						</div>
						<div id="openInterview" class="col-md-12"
							style="margin-top: 0px; margin-bottom: 10px;">
							<h1>Open Interviews</h1>
						</div>

						<div id="currentInterview" class="col-md-12"
							style="margin-top: 0px; margin-bottom: 10px;">
							<h1>Current Interviews</h1>
						</div>
						<div id="completedInterview" class="col-md-12"
							style="margin-top: 0px; margin-bottom: 10px;">
							<h1>Completed Interviews</h1>
						</div>

					</div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Bids</th>
                                <th>Status</th>
                                <th>Date</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                            </thead>
                            <tbody id="interviewTable"></tbody>
                        </table>
                        <ul class="pagination pull-right" id="interview_pagination">
                    </div>
                </div>
                </ul>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">
    var status = "${status}";
    
    if(status == "COMPLETED"){
    	$("#allInterview").hide();
    	$("#openInterview").hide();
    	$("#currentInterview").hide();
    	$("#completedInterview").show();
    	
    }else if(status == "OPEN"){
    
    	$("#allInterview").hide();
    	$("#openInterview").show();
    	$("#currentInterview").hide();
    	$("#completedInterview").hide();
    
    }else if(status == "IN PROGRESS"){
    	
    	$("#allInterview").hide();
    	$("#openInterview").hide();
    	$("#currentInterview").show();
    	$("#completedInterview").hide();

    }else {
    
    	$("#allInterview").show();
    	$("#openInterview").hide();
    	$("#currentInterview").hide();
    	$("#completedInterview").hide();

    }
    
    $(function () {
        loadAllInterview();
        $("#interview").html(status + " INTERVIEW");
        selectNavigation();
    });

    var loadAllInterview = function (pagenum) {
        if (pagenum == null || pagenum == undefined) {
            pagenum = 1;
        }
        if (status == null || status == undefined) {
            status = "ALL";
        }

        var param = "pagenum=" + pagenum + "&status=" + status;
        $.ajax({
            type: "GET",
            url: BASE_URL + "getinterviewlist.do",
            data: param,
            async: false
        }).done(function (res) {

            var resData = jQuery.parseJSON(res);
            var myinterview = resData.MY_INTERVIEW_AS_INTERVIEWEE;
            var count = Number(resData.MY_INTERVIEW_AS_INTERVIEWEE_COUNT);

            if (Number(resData.MY_INTERVIEW_AS_INTERVIEWEE_COUNT) < 10) {
                $(".pagination").html("");
            } else {
                var pagination = "";
                var totalpages = Number(resData.MY_INTERVIEW_AS_INTERVIEWEE_COUNT) / 10;
                for (var i = 0; i < totalpages; i++) {
                    if (pagenum == null || pagenum == undefined && i == 0)
                        pagination = pagination + '<li class="active" style="background:#428bca,color:white"><a class="transpage" href="#">' + (i + 1) + '</a></li>';
                    else if (pagenum == i + 1)
                        pagination = pagination + '<li class="active"><a class="transpage" style="background:#428bca,color:white" href="#">' + (i + 1) + '</a></li>';
                    else
                        pagination = pagination + '<li ><a class="transpage" href="#">' + (i + 1) + '</a></li>';
                }
                $("#interview_pagination").html(pagination);
            }

            $("#interviewTable").html("");
            if (count > 0) {
                for (var i = 0; i < myinterview.length; i++) {
                	var html = '<tr>' +
                    '            <td onclick="interviewdetail(\'' + myinterview[i].id + '\');" style="cursor: pointer;"> ' + myinterview[i].title + ' </td>' +
                    '            <td> ' + myinterview[i].bidcount + ' </td>' +
                    '            <td> ' + myinterview[i].statusString + ' </td>' +
                    '            <td> ' + prettyDate(new Date(myinterview[i].dt)) + ' </td>' ;

					if( myinterview[i].statusString === "OPEN"){
						html +='<td onclick="updateInterview(\'' + myinterview[i].id + '\');" style="cursor: pointer;"> <button type="button" class="btn btn-warning">Edit</button> </td> ' +
                    		'<td onclick="deleteInterview(\'' + myinterview[i].id + '\');" style="cursor: pointer;"> <button type="button" class="btn btn-danger">Delete</button> </td> '+	
                    		' </tr>';
                   	}else{
                   		html +='<td>-</td><td>-</td></tr>'
                   	}
                    $("#interviewTable").append(html);
                }
            } else {
                var noresult = "<tr><td colspan='4'>No interview found.</td></tr>"
                $("#interviewTable").append(noresult);
            }

        });
    }
//"/deleteinterview.do"

    $(document).on("click", ".transpage", function (event) {
        event.preventDefault();
        loadAllInterview($(this).text());
    });

    function interviewdetail(iid) {
        window.location.href = BASE_URL + "interviewdetail.do?iid=" + iid;
    }

    function deleteInterview(iid){
    	var param = "iid=" + iid;
    	$.ajax({
            type: "GET",
            url: BASE_URL + "deleteinterview.do",
            data: param,
            async: false
        }).done(function (res) {
        	loadAllInterview();
        	if(JSON.parse(res).code == 0){
        		 message(JSON.parse(res).message,"success")
        	}else{
        		message("Something went wrong, Please try again !!!","danger");
        	}
        });
    
    }
    
    function updateInterview(iid){
    	window.location.href = BASE_URL + "updateinterviewdetail.do?iid=" + iid;
    }
    
    function selectNavigation() {
        if (status == "ALL") {
            $("#allinterview").addClass("active");
        } else if (status == "OPEN") {
            $("#openinterview").addClass("active");
        } else if (status == "CURRENT") {
            $("#currentinterview").addClass("active");
        } else if (status == "COMPLETED") {
            $("#completedinterview").addClass("active");
        }
    }
</script>
</body>
</html>
