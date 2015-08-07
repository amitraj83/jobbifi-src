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
                    <div class="col-md-12" style="margin-top:0px;margin-bottom:10px;">
                        <h1>All Interviews</h1>
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
                    var html = '<tr onclick="interviewdetail(\'' + myinterview[i].id + '\');" style="cursor: pointer;">' +
                                    '            <td> ' + myinterview[i].title + ' </td>' +
                                    '            <td> ' + myinterview[i].bidcount + ' </td>' +
                                    '            <td> ' + myinterview[i].statusString + ' </td>' +
                                    '            <td> ' + prettyDate(new Date(myinterview[i].dt)) + ' </td>' +
                                    '        </tr>'
                            ;
                    $("#interviewTable").append(html);
                }
            } else {
                var noresult = "<tr><td colspan='4'>No interview found.</td></tr>"
                $("#interviewTable").append(noresult);
            }

        });
    }


    $(document).on("click", ".transpage", function (event) {
        event.preventDefault();
        loadAllInterview($(this).text());
    });

    function interviewdetail(iid) {
        window.location.href = BASE_URL + "interviewdetail.do?iid=" + iid;
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
