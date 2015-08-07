<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mock Interviews</title>
    <link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic|Roboto+Condensed:400,700'
          rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="template/css/bootstrap.css">
    <link rel="stylesheet" href="template/css/font-awesome.min.css">
    <link rel="stylesheet" href="template/css/flexslider.css">
    <link rel="stylesheet" href="template/css/style.css">
    <link rel="stylesheet" href="template/css/custom.css">
    <link rel="stylesheet" href="template/css/responsive.css">
    <!--[if IE 9]>
    <script src="template/js/media.match.min.js"></script>
    <![endif]-->

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div id="main-wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1 page-content">
                    <section id="exam">
                        <div style="overflow:hidden">
                            <h3><span class="pull-right" id="remainingTime"></span></h3>
                        </div>
                        <div id="questionDiv">Loading...</div>
                        <div id="buttonDiv" style="display:none; margin-top:30px;">
                            <button id="btnprev" onclick="showPreviousQuestion()" class="btn btn-primary" type="button">
                                Previous
                            </button>
                            <button onclick="submitAnswer()" class="btn btn-success" type="button">Submit</button>
                            <button id="btnnext" onclick="showNextQuestion()" class="btn btn-primary" type="button">
                                Next
                            </button>
                        </div>
                        <hr/>
                        <div>
                            <button id="btnend" class="pull-right btn btn-danger" onclick="endTest();">End Test</button>
                        </div>
                    </section>
                    <section id="score" style="display:none">
                        <h3>Your test submitted successfully.</h3>
                        Your Score : <span id="scorespan"></span>
                    </section>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/jquery-1.11.0.min.js"><\/script>')</script>
<script src="template/js/jquery.ba-outside-events.min.js"></script>
<script src="template/js/jquery.responsive-tabs.js"></script>
<script src="template/js/jquery-ui-1.10.4.custom.min.js"></script>
<script src="template/js/jquery.inview.min.js"></script>
<script src="template/js/script.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/map.js"></script>
<script type="text/javascript" src="js/dateformat.js"></script>
<script type="text/javascript" src="js/sha1.js"></script>
<script type="text/javascript" src="js/library.js"></script>
<script type="text/javascript" src="js/frontcommon.js"></script>
<script type="text/javascript" src='js/strophe.js'></script>
<script type="text/javascript" src="js/chat.js"></script>
<script>
    var TEST_ID = "${UserTest__.testId}";
    var CURRENT_QUESTION_NO = ${UserTest__.currentQNo};
    var TOTAL_QUESTIONS = ${UserTest__.totalQuestions};
    var DURATION = ${UserTest__.remaingingTimeInSeconds};

    function getQuestion(qno) {

        $("#buttonDiv").hide();
        var data = "qno=" + qno;
        $.ajax({
            url: '<c:url value="/getTQuestion.do"/>',
            type: 'GET',
            dataType: 'json',
            data: data,
            success: function (response) {
                showQuestion(response);
                $("#buttonDiv").show();
            }, error: function () {
                $("#buttonDiv").show();
            }
        });
    }

    function showQuestion(result) {
        var q = result.question;
        var question = q.question;
        var questionType = q.questionType;
        var html = "<p><input type='hidden' id='qid' value='" + q.id + "'>"
                + "<input type='hidden' id='questionType' value='" + q.questionType + "'>"
                + q.question + "</p>";
        if (questionType == "CHECKBOX") {
            html += '<div class="form-group"><label class="checkbox"><input name="answers" value="1" type="checkbox"/>' + q.answer1 + '</label></div>'
                    + '<div class="form-group"><label class="checkbox"><input name="answers" value="2" type="checkbox"/>' + q.answer2 + '</label></div>'
                    + '<div class="form-group"><label class="checkbox"><input name="answers" value="3" type="checkbox"/>' + q.answer3 + '</label></div>'
                    + '<div class="form-group"><label class="checkbox"><input name="answers" value="4" type="checkbox"/>' + q.answer4 + '</label></div>';
        } else {
            html += '<div class="form-group"><label class="radio"><input name="answers" value="1" type="radio"/>' + q.answer1 + '</label></div>'
                    + '<div class="form-group"><label class="radio"><input name="answers" value="2" type="radio"/>' + q.answer2 + '</label></div>'
                    + '<div class="form-group"><label class="radio"><input name="answers" value="3" type="radio"/>' + q.answer3 + '</label></div>'
                    + '<div class="form-group"><label class="radio"><input name="answers" value="4" type="radio"/>' + q.answer4 + '</label></div>';
        }

        $("#questionDiv").html(html);

        var ans = result.subans;
        for (var i = 0; i < ans.length; i++) {
            $("input[name='answers'][value='" + ans[i] + "']").attr({'checked': 'checked'});
        }

        $("#btnprev").hide();
        $("#btnnext").hide();
        if (CURRENT_QUESTION_NO > 1) {
            $("#btnprev").show();
        }
        if (CURRENT_QUESTION_NO < TOTAL_QUESTIONS) {
            $("#btnnext").show();
        }

    }

    function submitAnswer() {

        $("#buttonDiv").hide();
        CURRENT_QUESTION_NO = CURRENT_QUESTION_NO + 1;
        var data = "qno=" + CURRENT_QUESTION_NO + "&qid=" + $("#qid").val();
        var correctAnswer = new Array();
        var questionType = $("#questionType").val();
        if (questionType == "RADIOBOX") {
            var car = $("input[type='radio'][name='answers']:checked").val();
            correctAnswer.push(car);
        } else {
            var cacb = $("input[type='checkbox'][name='answers']:checked");
            for (var i = 0; i < cacb.length; i++) {
                correctAnswer.push(cacb[i].value);
            }
        }
        data += "&sans=" + correctAnswer.join();

        $.ajax({
            url: '<c:url value="submitanswer.do"/>',
            type: 'POST',
            dataType: 'json',
            data: data,
            success: function (response) {
                showQuestion(response);
                $("#buttonDiv").show();
            },
            error: function (jqXhr) {
                $("#buttonDiv").show();
            }
        });
    }

    function showNextQuestion() {
        if (CURRENT_QUESTION_NO < TOTAL_QUESTIONS) {
            CURRENT_QUESTION_NO = CURRENT_QUESTION_NO + 1;
            getQuestion(CURRENT_QUESTION_NO);
        }
    }

    function showPreviousQuestion() {
        if (CURRENT_QUESTION_NO > 1) {
            CURRENT_QUESTION_NO = CURRENT_QUESTION_NO - 1;
            getQuestion(CURRENT_QUESTION_NO);
        }
    }

    function submitTest() {
        $.ajax({
            url: '<c:url value="/submittest.do"/>',
            type: 'POST',
            dataType: 'json',
            success: function (response) {
                $("#exam").hide();
                $("#scorespan").html(response.score);
                $("#score").show();

            }, error: function () {
                alert("Error occured while processing request. Please try again later.");
            }
        });
    }

    function endTest() {
        if (confirm("Are you sure?")) {
            $("#buttonDiv").hide();
            $("#btnend").attr({'disabled': 'disabled'});
            submitTest();
        }
    }

    $(function () {
        getQuestion(CURRENT_QUESTION_NO);
        initializeExamTimer(DURATION);
    });

    var hour = 0;
    var mins = 0;
    var secs = 0;

    function initializeExamTimer(remainingTimeInSeconds) {
        hour = Math.floor(Math.max(remainingTimeInSeconds / (60 * 60), 0));
        var secsLeft = remainingTimeInSeconds % (60 * 60);
        mins = Math.floor(Math.max(secsLeft / 60, 0));
        secsLeft = secsLeft % 60;
        secs = secsLeft;
        displayRemainingTime();
        setTimeout("updateRemainingTime()", 1000);
    }

    function updateRemainingTime() {
        var examFinished = false;
        if (secs > 0) secs--;
        else {
            if (mins > 0) {
                secs = 59;
                mins--;
            }
            else {
                if (hour > 0) {
                    secs = 59;
                    mins = 59;
                    hour--;
                }
                else {
                    hour = min = sec = 0;
                    examTimedOut();
                    examFinished = true;
                }
            }
        }
        displayRemainingTime();
        if (!examFinished) setTimeout("updateRemainingTime()", 1000);
    }

    function displayRemainingTime() {
        var hourStr = hour < 10 ? "0" + hour : hour;
        var minsStr = mins < 10 ? "0" + mins : mins;
        var secsStr = secs < 10 ? "0" + secs : secs;
        var time = hourStr + ":" + minsStr + ":" + secsStr;
        var remainingTimeSpan = document.getElementById('remainingTime');
        remainingTimeSpan.innerHTML = time;
    }

    function examTimedOut() {
        submitTest();
    }
</script>
</body>
</html>
