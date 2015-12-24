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
                            <div class="col-md-12">
                                <div id="interview_status" class="interview-status pull-right label <c:choose>
                                    <c:when test="${status_string=='OPEN'}">label-success</c:when><c:otherwise>label-warning</c:otherwise></c:choose>"
                                     style="height:40px; width:150px;font-size:18px;padding-top:10px;">${status_string}</div>
                                <!--<div id="interview_status" class="interview-status pull-right alert <c:choose>
                                    <c:when test="${status_string=='OPEN'}">alert-success</c:when><c:otherwise>alert-warning</c:otherwise></c:choose>">${status_string}</div>-->
                                <h1 style="margin-top:0px;text-transform:capitalize;"><a href="javascript:void(0);"
                                                                                         id="interviewee_interview_title">${title}</a>
                                </h1>

                                <p><i class="fa fa-clock-o"></i> Posted on <span id="date"></span></p>
                                <hr/>
                                <p><b>Skills:</b>
                                    <c:forEach var="skill" items="${skills}">
                                        <label class="label label-info">${skill}</label>
                                    </c:forEach>
                                </p>

                                <p><b>Description:</b>${description}</p>

                                <p style="padding-bottom:20px;"><b>Attachments:</b>${ifile}</p>
                            </div>
                        </div>
                    </div>


                    <section class="clearfix">
                        <div class="responsive-tabs">
                            <ul id="itab" class="nav nav-tabs" role="tablist">
                                <li class="active"><a href="#bidsreceived" role="tab" data-toggle="tab">Bids
                                    Received</a></li>
                                <li><a href="#escrowdeposit" role="tab" data-toggle="tab">Escrow Desposit</a></li>
                                <li id="tabRating"><a href="#rateinterviewer" role="tab" data-toggle="tab">Rate
                                    Interviewer</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="bidsreceived">
                                    <br/>
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>Bidder</th>
                                            <th>Message</th>
                                            <th>Budget</th>
                                            <th>Date</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>
                                        <tbody id="interviewee_interview_allbids"></tbody>
                                    </table>
                                </div>

                                <div class="tab-pane fade in" id="escrowdeposit">
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="well" style="padding:10px;text-align:center"><b>Total Escrow</b>

                                                <div id="escrow_screen_totalescrow"></div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="well" style="padding:10px;text-align:center"><b>Total
                                                Released</b>

                                                <div id="escrow_screen_totalreleased"></div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="well" style="padding:10px;text-align:center"><b>Total Budget</b>

                                                <div id="escrow_screen_totalbudget"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="input-group">
                                                        <span class="input-group-addon"><i class="fa fa-inr"></i></span>
                                                        <input id="interviewee_interview_escrowamount" type="text"
                                                               class="form-control">
                                                    </div>
                                                </div>
                                                <div id="currentinterviewdata" iid="" interviewer="00"></div>
                                                <div class="col-md-4">
                                                    <button id="createescrowbutton" type="button"
                                                            class="btn btn-default">Create Escrow
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="well" style="padding:10px;text-align:center">
                                                <b>Account Balance</b>

                                                <div id="accountBalance"><i class="fa fa-inr"></i>${User_Balance}</div>
                                            </div>
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
                                	<br/>
                                    <div class="row" style="margin-top:10px;">
                                        <div class="col-md-12">

                                            <div class="row" id="post-review-box">
                                                <div class="col-md-12">
                                                    <form accept-charset="UTF-8" method="post"
                                                          class="form form-horizontal" id="ratingForm">

                                                        <p>Let us know about your experience</p>
                                                        <div id="message"></div>
												
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
                                                            <p>Your review</p>

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
    var theBID = "";
    var interviewer = "";
    var ecsrowbalance = "0";
    var budget = "0";
    var userbalance = "${User_Balance}";
    var bidreviewcount = "";
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
        $("#date").html(prettyDate(new Date(Number(${dt}))));
        initPage();
    });

    function initPage() {
        loadAllBid();
        loadEscrowList();
        checkRatingAllowed();
    }

    var bidPlaced = false;

    /* load all bid */
    function loadAllBid() {
        $.ajax({
            type: "GET",
            url: BASE_URL + "allbid.do",
            async: false
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            var allbid = resData.BIDS_RECEIVED;

            if (allbid.length > 0) {
                for (var i = 0; i < allbid.length; i++) {
                    if (interview_id == allbid[i].iid) {
                        bidPlaced = true;
                        var action = '<button onclick="awardInterview(this,\'' + allbid[i].price + '\')" iid="' + allbid[i].iid + '" bidid="' + allbid[i].id
                                + '" type="button" class="btn btn-success btn-xs awardinterview">Award</button>';
                        if (allbid[i].status == 2) {
                            action = '<span class="label label-warning">Rejected</span>';
                        }
                        if (allbid[i].status == 1) {
                            action = '<span class="label label-success">Accepted</span>';
                            theBID = allbid[i].iid;
                            interviewer = allbid[i].bidder;
                            budget = Number(allbid[i].price);
                        }

                        var bidRow = '<tr>' +
                                '<td class="col-md-4"><div class="row"><div class="col-md-6">' +
                                '<img src="images/face.jpg" class="img-responsive img-hover" width="100px" height="100px">' +
                                '</div>' +
                                '<div class="col-md-6">' + allbid[i].bidder + '<br> ' +
                                '<div id="interviewerRating">' +
                                '<input class="rating-display" type="number" value="' + allbid[i].reputation + '"/></div>' +
                                allbid[i].bidderreviewcount + ' Reviews</div></div></td>' +
                                '<td class="col-md-4">' + allbid[i].msg + '</td>' +
                                '<td class="col-md-2"><i class="fa fa-inr"></i>' + allbid[i].price + '</td>' +
                                '<td class="col-md-3">' + prettyDate(new Date(Number(allbid[i].dt))) + '</td>' +
                                '<td class="col-md-3">' + action + '</td>' +
                                '</tr>';
                        var html = bidRow + action;
                        $("#interviewee_interview_allbids").append(html);
                    }
                }
            }
            $('.rating-display').rating(ratingOptionDisable);
            if (!bidPlaced) {
                var html = "<tr><td colspan='5'>No bid found</td></tr>";
                $("#interviewee_interview_allbids").html(html);
            }
        });
    }

    /* load escrow list */
    function loadEscrowList() {
        $.ajax({
            type: "POST",
            url: BASE_URL + "escrowlist.do",
            data: "iid=" + interview_id,
            async: false
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            var totalReleased = 0;
            var allrows = "";
            if (resData.escrowlist.length > 0) {
                for (var i = 0; i < resData.escrowlist.length; i++) {
                    var statusButton = "";
                    if (resData.escrowlist[i].status == "0") {
                        ecsrowbalance = Number(resData.escrowlist[i].amount) + Number(ecsrowbalance);
                        statusButton = '<button onclick="releaseFund(this);" iid="' + resData.escrowlist[i].iid + '" escid="'
                                + resData.escrowlist[i].id + '" esc_amount="' + resData.escrowlist[i].amount
                                + '" type="button" class="btn btn-info btn-xs releasefundsbutton">Release</button>';
                    } else {
                        statusButton = '<span class="label label-success">Released</span>';
                        totalReleased = totalReleased + Number(resData.escrowlist[i].amount);
                    }

                    allrows = allrows + '<tr>' +
                            '<td class="col-md-3">' + resData.escrowlist[i].visibleId + '</td>' +
                            '<td class="col-md-3"><i class="fa fa-inr"></i>' + resData.escrowlist[i].amount + '</td>' +
                            '<td class="col-md-3">' + statusButton + '</td>' +
                            '<td class="col-md-3">' + prettyDate(new Date(Number(resData.escrowlist[i].date))) + '</td>' +
                            '</tr>';
                }
            } else {
                allrows = "<tr><td colspan='4'>No escrow found.</td></tr>";
            }

            $("#interviewee_interview_allescrows").append(allrows);
            $("#escrow_screen_totalescrow").html("<i class=\"fa fa-inr\"></i>" + ecsrowbalance);
            $("#escrow_screen_totalreleased").html("<i class=\"fa fa-inr\"></i>" + totalReleased);
            $("#escrow_screen_totalbudget").html("<i class=\"fa fa-inr\"></i>" + budget);
        });
    }

    /* check rating allowed */
    function checkRatingAllowed() {
        var param = "iid=" + interview_id + "&rateFor=" + interviewer;
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

    $("#createescrowbutton").one("click", function () {

        var escrowamount = $("#interviewee_interview_escrowamount").val();
        if (theBID != null && theBID != undefined) {

            var bal = userbalance;
            if (( Number(ecsrowbalance) + Number(escrowamount) > Number(budget))) {
                showError("Escrow more than total budget not allowed.");
                return;
            }

            if (Number(escrowamount) < Number(bal)) {
                var param = "iid=" + interview_id + "&interviewer=" + interviewer + "&amount=" + escrowamount;
                $.ajax({
                    type: "POST",
                    url: BASE_URL + "escrowdeposit.do",
                    data: param
                }).done(function (res) {
                    var resData = jQuery.parseJSON(res);
                    if (resData.code == 16) {
                        var allrows = "";
                        var totalReleased = 0;
                        for (var i = 0; i < resData.escrowlist.length; i++) {
                            var statusButton = "";
                            if (resData.escrowlist[i].status == "0") {
                                statusButton = '<button onclick="releaseFund(this);" iid="' + resData.escrowlist[i].iid
                                        + '" escid="' + resData.escrowlist[i].id + '" esc_amount="' + resData.escrowlist[i].amount
                                        + '" type="button" class="btn btn-info btn-xs releasefundsbutton">Release</button>';
                            } else {
                                statusButton = '<span class="label label-success">Released</span>';
                                totalReleased = totalReleased + Number(resData.escrowlist[i].amount);
                            }
                            allrows = allrows + '<tr>' +
                                    '<td class="col-md-3">' + resData.escrowlist[i].visibleId + '</td>' +
                                    '<td class="col-md-3"><i class="fa fa-inr"></i>' + resData.escrowlist[i].amount + '</td>' +
                                    '<td class="col-md-3">' + statusButton + '</td>' +
                                    '<td class="col-md-3">' + prettyDate(new Date(Number(resData.escrowlist[i].date))) + '</td>' +
                                    '</tr>';
                        }

                        $("#interviewee_interview_allescrows").html(allrows);
                        $("#escrow_screen_totalescrow").html("<i class=\"fa fa-inr\"></i>" + resData.eb);
                        $("#escrow_screen_totalreleased").html("<i class=\"fa fa-inr\"></i>" + totalReleased);
                        $("#accountBalance").html("<i class=\"fa fa-inr\"></i>" + resData.bal);
                        $("#interviewee_interview_escrowamount").html("");

                    } else {
                        showError("Escrow deposit failed : " + resData.message);
                    }

                    $("#interviewee_interview_escrowamount").html("");
                });

            } else {
                showError("You do not have enough balance to create escrow.");
            }
        }
    });

    function submitRating() {
        var param = "";
        param = param + "1=" + $("#r1").rating().val() + "&";
        param = param + "2=" + $("#r2").rating().val() + "&";
        param = param + "3=" + $("#r3").rating().val() + "&";
        param = param + "4=" + $("#r4").rating().val();
        param = param + "&msg=" + $("#new-review").val() + "&iid=" + interview_id + "&rateFor=" + interviewer;

        $.ajax({
            type: "GET",
            url: BASE_URL + "rate.do",
            data: param
        }).done(function (res) {
            var resData = jQuery.parseJSON(res);
            if (resData.code == 23) {
                message("Thank you. Your ratings have been saved.","success");
                $("#submitrating").hide();
                $("#reviewSection").html("<p>" + $("#new-review").val() + "</p>");

            } else {
                message("Unable to saved rating. Please try again later.","danger");
            }
        });
    }

    function releaseFund(btn) {
        var message = "This money can not be reverted back. Do you really want to release the funds ?";
        bootbox.confirm(message, function (result) {
            if (result) {
                var iid = $(btn).attr('iid');
                var param = "iid=" + iid + "&interviewer=" + interviewer +
                        "&amount=" + $(btn).attr('esc_amount') + "&escid=" + $(btn).attr('escid');
                var element = $(btn);
                $.ajax({
                    type: "POST",
                    url: BASE_URL + "releasefunds.do",
                    data: param
                }).done(function (res) {
                    var resData = jQuery.parseJSON(res);
                    if (resData.code == 19) {
                        $(element).parent().html('<span class="label label-success">Released</span>');
                        $("#escrow_screen_totalescrow").html("<i class=\"fa fa-inr\"></i>" + resData.eb);
                        $("#escrow_screen_totalreleased").html("<i class=\"fa fa-inr\"></i>" + resData.totalreleased);
                        showSuccess(resData.message);
                    } else {
                        showError(resData.message);
                    }
                });
            }
        });
    }

    function awardInterview(btn, price) {
        var iid = $(btn).attr('iid');
        var bidid = $(btn).attr("bidid");
        var thiselement = $(btn);
        theBID = iid;
        budget = Number(price);
        var message = "Are you sure you want to award this Interview ?";
        bootbox.confirm(message, function (result) {
            if (result) {
                var param = "iid=" + iid + "&bid=" + bidid;
                $.ajax({
                    type: "GET",
                    url: BASE_URL + "awardinterview.do",
                    data: param
                }).done(function (res) {

                    $(thiselement).parent().html('<span class="label label-success">Accepted</span>');
                    $(btn).parent().html('<span class="label label-warning">Rejected</span>');

                    var resData = jQuery.parseJSON(res);
                    $("#interview_status").html('IN PROGRESS');
                    $("#escrow_screen_totalbudget").html('<i class="fa fa-inr"></i>' + price);
                });
            }
        });
    }
    
    $(document).ready(function () {
    	$(".acc-link").hide();
    });
</script>
</body>
</html>
