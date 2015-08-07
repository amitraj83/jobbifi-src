<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mock Interview</title>
    <link href='https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic|Roboto+Condensed:400,700'
          rel='stylesheet'
          type='text/css'>
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='/template/css/bootstrap.css' />">
    <link rel="stylesheet" href="<c:url value='/template/css/font-awesome.min.css' />">
    <link rel="stylesheet" href="<c:url value='/template/css/flexslider.css' />">
    <link rel="stylesheet" href="<c:url value='/template/css/style.css' />">
    <link rel="stylesheet" href="<c:url value='/template/css/custom.css' />">
    <link rel="stylesheet" href="<c:url value='/template/css/responsive.css' />">

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
<jsp:include page="admin-header.jsp"/>
<div id="main-wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-3 page-sidebar">
                    <jsp:include page="admin-sidebar.jsp"/>
                </div>
                <div class="col-md-9 page-content">

                    <section>
                        <h3>${test.title}</h3>
                    </section>

                    <section id="testquestionsection" class="white-container">
                        <h3 class="">
                            Questions
                            <span class="pull-right"><button onclick="showAddQuestionSection();" class="btn btn-default"
                                                             type="button">Add Question
                            </button></span>
                        </h3>
                        <div id="testquestionsectionmsg"></div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Question</th>
                                <th>Type</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="testquestiontable">
                            <tr>
                                <td colspan='3'>Loading...</td>
                            </tr>
                            </tbody>
                        </table>
                    </section>

                    <section id="addquestionsection" style="display:none;">
                        <div id="questionmsg"></div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title" style="display:inline-block">Add Question</h3>
                                <a class="pull-right" href="javascript:void(0)" onclick="showQuestionListSection();">Show
                                    Question List</a>
                            </div>
                            <div class="panel-body">
                                <form action="<c:url value='/admin/testquestion.do'/>" method="post"
                                      class="form form-horizontal">
                                    <input type="hidden" name="testId" value="${tid}" id="testId"/>

                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Question</label>

                                        <div class="col-md-9">
                                            <textarea class="form-control" type="text" id="question"
                                                      name="question"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Question Type</label>

                                        <div class="col-md-9">
                                            <select class="form-control" id="questionType" name="questionType">
                                                <option value="RADIOBOX">Single Answer</option>
                                                <option value="CHECKBOX">Multiple Choice</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Answer 1</label>

                                        <div class="col-md-9">
                                            <textarea class="form-control" type="text" id="answer1"
                                                      name="answer1"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Answer 2</label>

                                        <div class="col-md-9">
                                            <textarea class="form-control" type="text" id="answer2"
                                                      name="answer2"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Answer 3</label>

                                        <div class="col-md-9">
                                            <textarea class="form-control" type="text" id="answer3"
                                                      name="answer3"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Answer 4</label>

                                        <div class="col-md-9">
                                            <textarea class="form-control" type="text" id="answer4"
                                                      name="answer4"></textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Correct Answer</label>

                                        <div class="col-md-9">
                                            <div id="ANS_RADIO">
                                                1.&nbsp;&nbsp;<input class="radio radio-inline" type="radio"
                                                                     name="correctAnswerRadio" value="1"/>
                                                &nbsp;&nbsp;
                                                2.&nbsp;&nbsp;<input class="radio radio-inline" type="radio"
                                                                     name="correctAnswerRadio" value="2"/>
                                                &nbsp;&nbsp;
                                                3.&nbsp;&nbsp;<input class="radio radio-inline" type="radio"
                                                                     name="correctAnswerRadio" value="3"/>
                                                &nbsp;&nbsp;
                                                4.&nbsp;&nbsp;<input class="radio radio-inline" type="radio"
                                                                     name="correctAnswerRadio" value="4"/>
                                            </div>
                                            <div id="ANS_CHECKBOX" style="display:none">
                                                1&nbsp;&nbsp;<input class="checkbox checkbox-inline" type="checkbox"
                                                                    name="correctAnswer" value="1"/>
                                                &nbsp;&nbsp;
                                                2&nbsp;<input class="checkbox checkbox-inline" type="checkbox"
                                                              name="correctAnswer" value="2"/>
                                                &nbsp;&nbsp;
                                                3&nbsp;<input class="checkbox checkbox-inline" type="checkbox"
                                                              name="correctAnswer" value="3"/>
                                                &nbsp;&nbsp;
                                                4&nbsp;<input class="checkbox checkbox-inline" type="checkbox"
                                                              name="correctAnswer" value="4"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label"></label>

                                        <div class="col-md-9">
                                            <button type="button" class="btn btn-default" onclick="addQuestion();">Save
                                                Question
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Preview</h3>
                            </div>
                            <div class="panel-body">
                                <form class="form form-horizontal">
                                    <div id="previewDiv"></div>
                                </form>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="' + "<c:url value='/js/jquery-1.11.0.js'/>" + '"><\/script>')</script>
<script src="<c:url value='/template/js/jquery.ba-outside-events.min.js' />"></script>
<script src="<c:url value='/template/js/jquery.responsive-tabs.js' />"></script>
<script src="<c:url value='/template/js/jquery-ui-1.10.4.custom.min.js' />"></script>
<script src="<c:url value='/template/js/jquery.inview.min.js' />"></script>
<script src="<c:url value='/template/js/script.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap.min.js' />"></script>
<script type="text/javascript" src="<c:url value='libraries/ckeditor/ckeditor.js' />"></script>
<script type="text/javascript" src="<c:url value='libraries/ckeditor/adapters/jquery.js' />"></script>
<script type="text/javascript">
    var TEST_ID = '${tid}';
    var RELOAD_QUESTION_LIST = false;
    function checkQuestionType() {
        var questionType = $("#questionType").val();
        if (questionType == "RADIOBOX") {
            $("#ANS_RADIO").show();
            $("#ANS_CHECKBOX").hide();
        } else if (questionType == "CHECKBOX") {
            $("#ANS_RADIO").hide();
            $("#ANS_CHECKBOX").show();
        }
    }

    function addQuestion() {
        var data = "testId=" + $("#testId").val() + "&question=" + $("#question").val()
                + "&answer1=" + $("#answer1").val() + "&answer2=" + $("#answer2").val()
                + "&answer3=" + $("#answer3").val() + "&answer4=" + $("#answer4").val()
                + "&questionType=" + $("#questionType").val();

        var correctAnswer = new Array();
        var questionType = $("#questionType").val();
        if (questionType == "RADIOBOX") {
            var car = $("input[type='radio'][name='correctAnswerRadio']:checked").val();
            correctAnswer.push(car);
        } else {
            var cacb = $("input[type='checkbox'][name='correctAnswer']:checked");
            for (var i = 0; i < cacb.length; i++) {
                correctAnswer.push(cacb[i].value);
            }
        }
        data += "&correctAnswers=" + correctAnswer.join();

        $.ajax({
            url: '<c:url value="/admin/testquestion.do"/>',
            type: 'POST',
            dataType: 'json',
            data: data,
            success: function (response) {
                console.log(response);

                if (response && response.result == 1) {
                    $("#question").val("");
                    $("#answer1").val("");
                    $("#answer2").val("");
                    $("#answer3").val("");
                    $("#answer4").val("");
                    $("#questionType").val("RADIOBOX");
                    RELOAD_QUESTION_LIST = true;

                    $("#questionmsg").html("<div class='alert alert-success'>Question added Successfully.</div>");
                    setTimeout(function () {
                        $("#questionmsg").html("");
                    }, 3000);

                } else if (response && response.result == 1) {
                    $("#questionmsg").html("<div class='alert alert-success'>Unable to add Question.</div>");
                    setTimeout(function () {
                        $("#questionmsg").html("");
                    }, 3000);
                }

            },
            error: function (jqXhr) {
                $("#questionmsg").html("<div class='alert alert-danger'>Error occured while processing request.</div>");
                setTimeout(function () {
                    $("#questionmsg").html("");
                }, 3000);
            }
        });
    }

    function previewQuestion() {
        var question = $("#question").val();
        var questionType = $("#questionType").val();
        var answer1 = $("#answer1").val();
        var answer2 = $("#answer2").val();
        var answer3 = $("#answer3").val();
        var answer4 = $("#answer4").val();
        var qtHtml = "<input name='pqra' class='radio radio-inline col-md-1' type='radio'/>";
        if (questionType == "CHECKBOX") {
            qtHtml = "<input class='checkbox checkbox-inline col-md-1' type='checkbox'/>";
        }

        if (question.trim() == "") {
            $("#previewDiv").html("");
        } else {
            var html = "<p>" + question + "</p>"
                    + '<div class="form-group">' + qtHtml + '<div>' + answer1 + '</div></div>'
                    + '<div class="form-group">' + qtHtml + '<div>' + answer2 + '</div></div>'
                    + '<div class="form-group">' + qtHtml + '<div>' + answer3 + '</div></div>'
                    + '<div class="form-group">' + qtHtml + '<div>' + answer4 + '</div></div>';
            $("#previewDiv").html(html);
        }
        setTimeout(function () {
            previewQuestion();
        }, 5000);
    }

    $(function () {
        $("#questionType").bind("change", function (event) {
            checkQuestionType();
        });
        setTimeout(function () {
            previewQuestion();
        }, 5000);
        showQuestionList(TEST_ID, 1);
        $("#question").ckeditor();
    });

    function showQuestionList(testId, pageNo) {
        var pagesize = 10;
        var data = "tid=" + testId + "&pagesize=" + pagesize + "&pageno=" + pageNo;
        $.ajax({
            url: '<c:url value="/admin/gettestquestion.do"/>',
            data: data,
            dataType: 'json',
            type: 'GET',
            success: function (response) {
                console.log(response);
                var result = response.result;
                var count = response.count;
                var html = "";
                if (result.length > 0) {
                    for (var i = 0; i < result.length; i++) {
                        html += '<tr><td>' + result[i].question + '</td>' +
                                '<td>' + result[i].questionType + '</td>' +
                                '<td><button class="btn btn-danger btn-sm" type="button" onclick="removeQuestion(\''
                                + result[i].id + '\')">Remove</button></td></tr>';
                    }

                    html += getPaginationForQuestion(count, pageNo, testId, pagesize);
                    $("#testquestiontable").html(html);

                } else {
                    html = "<tr><td colspan='3'>No question found.</td></tr>";
                    $("#testquestiontable").html(html);
                }

            }, error: function (jqXhr) {

            }
        });
    }

    function showAddQuestionSection() {
        $("#addquestionsection").slideDown();
        $("#testquestionsection").hide(100);
    }

    function showQuestionListSection() {

        if (RELOAD_QUESTION_LIST) {
            showQuestionList(TEST_ID, 1);
            RELOAD_QUESTION_LIST = false;
        }

        $("#addquestionsection").slideUp();
        $("#testquestionsection").show(500);
    }

    function getPaginationForQuestion(totalRecords, currentPage, testId, pageSize) {
        var totalPages = Math.ceil(totalRecords / pageSize);
        var html = "";
        if (totalPages >= 1) {
            html += "<tr><td colspan='3'><ul class='pagination pull-right'>"
            for (var i = 0; i < totalPages; i++) {
                if (currentPage == (i + 1)) {
                    html = html + "<li class='active'><a href='javascript:void(0)'>" + (i + 1) + "</a></li>";
                } else {
                    html = html + "<li class=''><a href='javascript:showQuestionList(\"" + testId + "\"," + (i + 1) + ")'>" + (i + 1) + "</a></li>";
                }
            }
            html += "</td></tr></ul>"
        }
        return html;
    }

    function removeQuestion(qid) {
        var data = "qid=" + qid;
        $.ajax({
            url: '<c:url value="/admin/deletequestion.do"/>',
            data: data,
            dataType: 'json',
            type: 'GET',
            success: function (response) {
                var result = response.result;
                if (result == 1) {
                    $("#testquestionsectionmsg").html("<div class='alert alert-success'>Question deleted Successfully.</div>");
                    setTimeout(function () {
                        $("#testquestionsectionmsg").html("");
                    }, 3000);
                    showQuestionList(TEST_ID, 1);
                } else {
                    $("#testquestionsectionmsg").html("<div class='alert alert-danger'>Opration Failed.</div>");
                    setTimeout(function () {
                        $("#testquestionsectionmsg").html("");
                    }, 3000);
                }

            }, error: function (jqXhr) {

            }
        });
    }
</script>
</body>
</html>