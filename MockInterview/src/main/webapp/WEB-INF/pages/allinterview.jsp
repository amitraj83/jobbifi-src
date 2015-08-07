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
                <div class="list-group list-default" style="width:100%">
                    <a href="javascript:void(0);" id="interviewee_allinterview" class="list-group-item active">All
                        Interview</a>
                    <a href="<c:url value='publishinterview.do'/>" id="interviewee_publishinterview"
                       class="list-group-item">Publish Interviews</a>
                    <a href="<c:url value='openinterview.do'/>" id="interviewee_open" class="list-group-item">Open
                        Interviews</a>
                    <a href="<c:url value='currentinterview.do'/>" id="interviewee_current" class="list-group-item">Current
                        Interviews</a>
                    <a href="<c:url value='completedinterview.do'/>" id="interviewee_completed" class="list-group-item">Completed
                        Interviews</a>
                    <a href="javascript:void(0);" id="interviewee_dispute" class="list-group-item">Disputes</a>
                </div>
            </div>

            <div class="col-md-9 page-content">
                <div class="clearfix white-container">
                    <h3>All Interviews</h3>
                    <hr>
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Bids</th>
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
    $(function () {
        loadAllInterview();
    });

    function loadAllInterview() {
        $.ajax({
            type: "GET",
            url: BASE_URL + "/getinterviewlist.do",
            async: false
        }).done(function (res) {

            var resData = jQuery.parseJSON(res);
            var myinterview = resData.MY_INTERVIEW_AS_INTERVIEWEE;

            for (var i = 0; i < myinterview.length; i++) {
                var html = '<tr style="cursor: pointer;" class="interviewee_interview" iid="' + myinterview[i].id + '">' +
                                '            <td> ' + myinterview[i].title + ' </td>' +
                                '            <td> ' + resData.ibid.length + ' </td>' +
                                '            <td> ' + getInterviewStatusLiteral(myinterview[i].status) + ' </td>' +
                                '            <td> ' + prettyDate(new Date(myinterview[i].dt)) + ' </td>' +
                                '        </tr>'
                        ;
                $("#interviewee_allbody").append(html);
            }

        });
    }

    function getInterviewStatusLiteral(status) {
        if (status == 0) {
            return "OPEN";
        }
        else if (status == 1) {
            return "IN PROGRESS";
        }
        else if (status == 2) {
            return "IN PROGRESS";
        }
        else if (status == 3) {
            return "IN PROGRESS";
        }
        else if (status == 4) {
            return "APPROVAL PENDING";
        }
        else if (status == 5) {
            return "COMPLETED";
        }
        else if (status == 6) {
            return "CANCELLED";
        }
        else if (status == 7) {
            return "REPOSTED";
        }
        else if (status == 8) {
            return "USER RATED";
        }
        else if (status == 9) {
            return "DISPUTE";
        }
    }

    function getBidCount() {
        var count = 0;
        return count;
    }
</script>
</body>
</html>