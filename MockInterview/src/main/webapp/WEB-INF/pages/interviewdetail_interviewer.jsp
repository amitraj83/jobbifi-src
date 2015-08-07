<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Mock Interview Detail </title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-3 page-sidebar">
                    <%@ include file="/WEB-INF/pages/myInterviewSidebar.jsp" %>
                </div>

                <div class="col-md-9 page-content">

                    <div class="clearfix white-container">
                        <div class="row">
                            <div class="col-md-8">
                                <h3 class="title">
                                    <a style="padding-right: 90px;" href="javascript:void(0);"
                                       id="interviewee_interview_title">${title}</a>
                                </h3>
                                <h4 class="interview-status">${status_string}</h4>

                                <p><i class="fa fa-clock-o"></i> Posted on <span id="date"></span></p>
                                <hr>
                                <p><b>Skills Required:</b>
                                    <c:forEach var="skill" items="${skills}">
                                        <label class="label label-info">${skill}</label>
                                    </c:forEach>
                                </p>

                                <p><b>Description</b>:${description}</p>

                                <p>${ifile}</p>
                            </div>
                            <div class="col-md-4">
                                <div class="well well-default">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <img class="img-responsive img-hover" src="$postedbypic$" width="150px"
                                                 height="150px"/>
                                        </div>
                                        <div class="col-md-6">Posted By<br><span>${interviewee}</span></div>
                                        <input class="custom-rating" type="number" id="userRating"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <section class="clearfix">
                        <div class="responsive-tabs">
                            <ul id="itab" class="nav nav-tabs" role="tablist">
                                <li class="active"><a href="#escrowdeposit" role="tab" data-toggle="tab">Escrow
                                    Desposit</a></li>
                                <li id="tabRating"><a href="#rateinterviewer" role="tab" data-toggle="tab">Rate
                                    Interviewer</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="escrowdeposit">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="well" style="padding:10px;text-align:center"><b>Total Escrow</b>

                                                <p>

                                                <div id="escrow_screen_totalescrow"></div>
                                                </p></div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="well" style="padding:10px;text-align:center"><b>Total
                                                Released</b>

                                                <p>

                                                <div id="escrow_screen_totalreleased"></div>
                                                </p></div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="well" style="padding:10px;text-align:center"><b>Total Budget</b>

                                                <p>

                                                <div id="escrow_screen_totalbudget"></div>
                                                </p></div>
                                        </div>
                                    </div>

                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>Escrow id</th>
                                            <th>Escrow Amount</th>
                                            <th>Action</th>
                                            <th>Date</th>
                                        </tr>
                                        </thead>
                                        <tbody id="interviewee_interview_allescrows"></tbody>
                                    </table>
                                </div>

                                <div class="tab-pane fade in" id="rateinterviewer">
                                    <div class="row" style="margin-top:10px;">
                                        <div class="col-md-12">

                                            <div class="row" id="post-review-box">
                                                <div class="col-md-12">
                                                    <form accept-charset="UTF-8" method="post"
                                                          class="form form-horizontal" id="ratingForm">

                                                        <p>Let us know about your experience:</p>

                                                        <div class="form-group">
                                                            <label class="col-md-12">Was Technical Support
                                                                Helpful?</label>

                                                            <div class="col-md-12">
                                                                <input type="number" id="r1" value="0"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-12">Was Interview Realistic?</label>

                                                            <div class="col-md-12">
                                                                <input type="number" id="r2" value="0">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-12">Was Interview Professional?</label>

                                                            <div class="col-md-12">
                                                                <input type="number" id="r3" value="0"/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-12">Would you recommend him?</label>

                                                            <div class="col-md-12">
                                                                <input type="number" id="r4" value="0"/>
                                                            </div>
                                                        </div>

                                                        <div class="clearfix" id="reviewSection">
                                                            <p>Your review:</p>

                                                            <div class="form-group">
                                                                <div class="col-md-12">
                                                                    <textarea class="form-control animated" cols="50"
                                                                              id="new-review" name="comment"
                                                                              placeholder="Enter your review here..."
                                                                              rows="5"></textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="clearfix">
                                                            <button id="submitrating" class="btn btn-success btn-lg"
                                                                    type="submit">Save
                                                            </button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>

                </div>
            </div>

        </div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">
    var interview_id = "${iid}";
    var ecsrowbalance = "0";
    var budget = "${budget}";
    var ratingOption = {'min': 0, 'max': 5, 'step': 1, 'showClear': false, 'showCaption': false};
    var ratingOptionDisable = {
        'min': 0,
        'max': 5,
        'step': 1,
        'readonly': true,
        'showClear': false,
        'showCaption': false
    };

    $(function () {
        loadEscrowList();
        showUserRating();
        checkRatingAllowed();
        $("#date").html(prettyDate(new Date(Number(${dt}))));
    });

    function showUserRating() {
        $.ajax({
            type: 'GET',
            dataType: 'JSON',
            url: BASE_URL + 'userrating.do?username=${interviewee}',
            success: function (data) {
                $(".custom-rating").val(data.rating);
                $(".custom-rating").rating({
                    'min': 0,
                    'max': 5,
                    'step': 1,
                    'readonly': true,
                    'showClear': false,
                    'showCaption': false
                });
            }
        });
    }

    function loadEscrowList() {
        var allrows = "";
        $.ajax({
            type: "POST",
            url: BASE_URL + "escrowlist.do",
            data: "iid=" + interview_id,
            async: false
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            var totalReleased = 0;
            if (resData.escrowlist.length > 0) {
                for (var i = 0; i < resData.escrowlist.length; i++) {
                    var statusButton = "";
                    if (resData.escrowlist[i].status == "0") {
                        ecsrowbalance = Number(resData.escrowlist[i].amount) + Number(ecsrowbalance);
                        statusButton = '<span  iid="' + resData.escrowlist[i].iid + '" escid="' + resData.escrowlist[i].id + '" esc_amount="'
                                + resData.escrowlist[i].amount + '" class="label label-warning">Not Released</button>';
                    } else {
                        statusButton = '<span class="label label-success">Released</span>';
                        totalReleased = totalReleased + Number(resData.escrowlist[i].amount);
                    }

                    allrows = allrows + '<tr>' +
                            '<td class="col-md-3">' + resData.escrowlist[i].visibleId + '</td>' +
                            '<td class="col-md-3">$' + resData.escrowlist[i].amount + '</td>' +
                            '<td class="col-md-3">' + statusButton + '</td>' +
                            '<td class="col-md-3">' + prettyDate(new Date(Number(resData.escrowlist[i].date))) + '</td>' +
                            '</tr>';
                }
            } else {
                allrows = "<tr><td colspan='4'>No escrow found.</td></tr>";
            }

            $("#interviewee_interview_allescrows").append(allrows);
            $("#escrow_screen_totalescrow").html("$" + ecsrowbalance);
            $("#escrow_screen_totalreleased").html("$" + totalReleased);
            $("#escrow_screen_totalbudget").html("$" + budget);
        });
    }

    function checkRatingAllowed() {
        var param = "iid=" + interview_id + "&rateFor='${interviewee}'";
        $.ajax({
            type: "GET",
            url: BASE_URL + "ratingallowed.do",
            data: param,
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            if (resData.result == false && resData.ratingavailable == false) {
                $("#r1").rating(ratingOption);
                $("#r2").rating(ratingOption);
                $("#r3").rating(ratingOption);
                $("#r4").rating(ratingOption);
                $("#tabRating").hide();
            } else if (resData.result == false && resData.ratingavailable) {
                $("#r1").val(resData.rate1).rating(ratingOptionDisable);
                $("#r2").val(resData.rate2).rating(ratingOptionDisable);
                $("#r3").val(resData.rate3).rating(ratingOptionDisable);
                $("#r4").val(resData.rate4).rating(ratingOptionDisable);
                $("#submitrating").hide();
                $("#reviewSection").html("<p>" + resData.message + "</p>");
            } else {
                $("#r1").rating(ratingOption);
                $("#r2").rating(ratingOption);
                $("#r3").rating(ratingOption);
                $("#r4").rating(ratingOption);

                $("#ratingForm").validate({
                    rules: {
                        comment: {required: true}
                    },
                    submitHandler: function (form) {
                        submitRating();
                        return;
                    }
                });
            }
        });
    }

    $(document).on('click', "#submitrating", function () {
        var iid = $(this).attr("alt");
        var param = "";
        param = param + "1=" + $("#r1").rating().val() + "&";
        param = param + "2=" + $("#r2").rating().val() + "&";
        param = param + "3=" + $("#r3").rating().val() + "&";
        param = param + "4=" + $("#r4").rating().val();
        param = param + "&msg=" + $("#new-review").val() + "&iid=" + interview_id + "&rateFor='${interviewee }'";

        $.ajax({
            type: "GET",
            url: BASE_URL + "rate.do",
            data: param
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            if (resData.code == 23) {
                $("#rateinterviewer").prepend('<div class="alert alert-success" role="alert">Thank you. Your ratings have been saved.</div>');
            } else {
                $("#rateinterviewer").prepend('<div class="alert alert-danger" role="alert">Sorry. Try again later.</div>');
            }
        });
        return;
    });
</script>
</body>
</html>