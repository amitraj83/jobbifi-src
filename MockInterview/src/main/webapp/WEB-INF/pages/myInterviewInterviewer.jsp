<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>All Interview</title>
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
                <div class="clearfix white-container">
                    <h3 class="title" id="interview">All Interviews</h3>
                    <hr>
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Bid Amount</th>
                                <th>Budget</th>
                                <th>Status</th>
                                <th>Date</th>
                            </tr>
                            </thead>
                            <tbody id="interviewee_allbody"></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">
    var status = "${status}";
    var budget = "${budget}";
    $(function () {
        loadAllInterview();
        $("#interview").html(status + " INTERVIEW");
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
            url: BASE_URL + "/getinterviewlist.do",
            data: param,
            async: false
        }).done(function (res) {

            var resData = jQuery.parseJSON(res);
            var myinterview = resData.ibid;

            if (Number(resData.INTERVIEWS_WHERE_I_BID_COUNT) < 10) {
                $(".pagination").html("");
            }
            else {
                var pagination = "";
                var totalpages = Number(resData.INTERVIEWS_WHERE_I_BID_COUNT) / 10;
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
            $("#interviewee_allbody").html("");

            for (var i = 0; i < myinterview.length; i++) {
                if (status == 'ALL' || status == myinterview[i].statusString) {
                    var html = '<tr onclick="interviewdetail(\'' + myinterview[i].id + '\',\'' + myinterview[i].price + '\');" style="cursor: pointer;" iid="' + myinterview[i].id + '">' +
                                    '            <td> ' + myinterview[i].title + ' </td>' +
                                    '            <td> ' + myinterview[i].price + ' </td>' +
                                    '            <td> ' + myinterview[i].budget + ' </td>' +
                                    '            <td> ' + myinterview[i].statusString + ' </td>' +
                                    '            <td> ' + prettyDate(new Date(myinterview[i].dt)) + ' </td>' +
                                    '        </tr>'
                            ;
                    $("#interviewee_allbody").append(html);
                }
            }

        });
    }

    $(document).on("click", ".transpage", function (event) {
        event.preventDefault();
        loadAllInterview($(this).text());
    });

    function interviewdetail(iid, price) {
        window.location.href = BASE_URL + "interviewdetail_Interviewer.do?iid=" + iid + "&budget=" + price;
    }

    function getBidCount() {
        var count = 0;
        return count;
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