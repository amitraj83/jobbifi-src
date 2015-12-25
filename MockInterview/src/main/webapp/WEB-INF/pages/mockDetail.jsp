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
                
                <div class="col-md-9 page-content">

                    <div class="clearfix white-container">
                        <div class="row">
                            <div class="col-md-9">
                                <div class="row">
                                <div class="col-md-9">
                                <h3 class="title">
                                    <a style="padding-right: 90px;" href="javascript:void(0);"
                                       id="interviewee_interview_title">${title}</a>
                                </h3>
                                </div>
                                <div class="col-md-3" style="margin-top:5px;">
                                    <h3><span class="label label-success">${status_string}</span></h3> <!--<h4 class="interview-status">${status_string}</h4> -->
                                </div>
                                </div>
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
                            <div class="col-md-3">
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


                   

                </div>


                <div class="col-md-3 page-sidebar">
                    <div class="clearfix" style="padding-left: 30px; border-left: 1px solid #ddd;">
                       
                       <div>
                        <center>
                            <h3>What's Next?</h3>    
                            <div class="stepwizard" style="height:160px;">
                            <div class="stepwizard-row">
                                <div class="stepwizard-step">
                                    <button type="button" class="btn btn-info btn-square">Provide mock interview</button>
                                </div>
                            </div>
                            <div class="stepwizard-row">   
                                <div class="stepwizard-step">
                                    <button type="button" class="btn btn-success btn-square">Refer candidate in your company</button>
                                </div>
                            </div>
                            <div class="stepwizard-row">   
                                <div class="stepwizard-step">
                                    <button type="button" class="btn btn-warning btn-square">Get paid while supporting community !</button>
                                </div>
                            </div>
                            
                        </center>
                        </div>

                       
                       <hr/>
                        <div style="border: 5px solid rgb(255, 158, 40); padding: 0px 15px 10px;">
                            <h3>Looking for a right candidate ?</h3>
                            <p>Post your job and let millions of candidates find your job and you select the right candidate for your job.</p>
                            <div style="text-align:center">
                            
                                <a onclick="showPostJobScreen()" class="btn btn-success">Post a Job</a>
                                <!--<a href="/publishinterview.do" class="btn btn-success">Post a Mock</a>-->
                            </div>
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


    function showPostJobScreen(){
        window.location = BASE_URL+"postjob.do";
    }

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