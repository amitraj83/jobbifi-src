<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>My Interviews</title>
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
                    <h1 class="title">My Interviews</h1>

                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Budget</th>
                                <th>Status</th>
                                <th>Bid Amount</th>
                                <th>Bid Date</th>
                            </tr>
                            </thead>
                            <tbody id="bid_allbody"></tbody>
                        </table>
                    </div>
                    <ul class="pagination pull-right" id="bid_pagination"></ul>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">
    $(function () {
        loadAllInterview();
        $("#myinterview").addClass("active");
    });

    var loadAllInterview = function (pagenum) {
        if (pagenum == null || pagenum == undefined) {
            pagenum = 1;
        }
        var param = "pagenum=" + pagenum;
        $.ajax({
            type: "GET",
            url: BASE_URL + "getawardedbidlist.do",
            data: param,
            async: false
        }).done(function (res) {

            var resData = jQuery.parseJSON(res);
            var myinterview = resData.AWARDED_BID;

            $("#bid_pagination").html("");
            if (Number(resData.AWARDED_BID_count) > 10) {
                var pagination = "";
                var totalpages = Number(resData.AWARDED_BID_count) / 10;
                for (var i = 0; i < totalpages; i++) {
                    if (pagenum == null || pagenum == undefined && i == 0) {
                        pagination = pagination + '<li class="active"><a class="transpage" href="javascript:void(0)">' + (i + 1) + '</a></li>';
                    } else if (pagenum == i + 1) {
                        pagination = pagination + '<li class="active"><a class="transpage" href="javascript:void(0)">' + (i + 1) + '</a></li>';
                    } else {
                        pagination = pagination + '<li><a class="transpage" href="javascript:void(0)">' + (i + 1) + '</a></li>';
                    }
                }
                $("#bid_pagination").html(pagination);
            }

            $("#bid_allbody").html("");
            if (myinterview.length > 0) {
                for (var i = 0; i < myinterview.length; i++) {
                    var html = '<tr onclick="interviewdetail(\'' + myinterview[i].id + '\');" style="cursor: pointer;">' +
                            '<td>' + myinterview[i].title + '</td>' +
                            '<td><i class="fa fa-inr"></i>' + myinterview[i].budget + '</td>' +
                            '<td>' + myinterview[i].statusString + '</td>' +
                            '<td><i class="fa fa-inr"></i>' + myinterview[i].price + '</td>' +
                            '<td>' + prettyDate(new Date(myinterview[i].dt)) + '</td>' +
                            '</tr>';
                    $("#bid_allbody").append(html);
                }
            } else {
                var noresult = "<tr><td colspan='5'>No interview found.</td></tr>";
                $("#bid_allbody").append(noresult);
            }

        });
    }

    $(document).on("click", ".transpage", function (event) {
        event.preventDefault();
        loadAllInterview($(this).text());
    });

    function interviewdetail(iid) {
        window.location.href = BASE_URL + "interviewdetail_Interviewer.do?iid=" + iid;
    }
</script>
</body>
</html>