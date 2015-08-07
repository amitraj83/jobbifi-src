<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

                    <section id="test-table-section" class="white-container">
                        <h3 class="">
                            Test List
                            <span class="pull-right"><button onclick="showCreateTest();" class="btn btn-default"
                                                             type="button">Create Test
                            </button></span>
                        </h3>
                        <div id="test-table-section-msg"></div>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Diff Level</th>
                                <th>Skills</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="testtable">
                            <tr>
                                <td colspan='5'>Loading...</td>
                            </tr>
                            </tbody>
                        </table>
                    </section>
                    <section id="create-test-section" style="display:none;">
                        <div id="testmsg"></div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Create Test</h3>
                            </div>
                            <div class="panel-body">
                                <form action="<c:url value='/admin/createtest.do'/>" method="post"
                                      class="form form-horizontal">
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Title</label>

                                        <div class="col-md-9">
                                            <input class="form-control" type="text" id="title" name="title"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Difficulty Level</label>

                                        <div class="col-md-9">
                                            <select class="form-control" id="difficultyLevel" name="difficultyLevel">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Description</label>

                                        <div class="col-md-9">
                                            <textarea class="form-control" row="5" type="text" id="description"
                                                      name="description"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">No Of Questions</label>

                                        <div class="col-md-9">
                                            <input class="form-control" type="text" id="noOfQuestions"
                                                   name="noOfQuestions"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Duration (minutes)</label>

                                        <div class="col-md-9">
                                            <input class="form-control" type="text" id="duration" name="duration"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label">Skills</label>

                                        <div class="col-md-9">
                                            <input class="form-control" type="text" id="skills" name="skills"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label"></label>

                                        <div class="col-md-9">
                                            <button type="button" class="btn btn-default" onclick="createTest();">Create
                                                Test
                                            </button>
                                            <button type="button" class="btn btn-default" onclick="showTestTable();">
                                                Back
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="' + "<c:url value='/js/jquery-1.11.0.js'/>" + '"><\/script>')</script>
<script src="<c:url value='/template/js/jquery.ba-outside-events.min.js' />"></script>
<script src="<c:url value='/template/js/jquery.responsive-tabs.js' />"></script>
<script src="<c:url value='/template/js/jquery-ui-1.10.4.custom.min.js' />"></script>
<script src="<c:url value='/template/js/jquery.inview.min.js' />"></script>
<script src="<c:url value='/template/js/script.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap.min.js' />"></script>
<script type="text/javascript">
    $(function () {
        loadTestList();
    });

    // should be paginated
    function loadTestList() {
        var data = "";
        $.ajax({
            url: '<c:url value="/admin/gettests.do"/>',
            type: 'GET',
            dataType: 'json',
            data: data,
            success: function (response) {
                if (typeof response !== null && response.result) {
                    showTestList(response.result);
                }
            },
            error: function (jqxhr) {
                console.log("error");
            }
        });
    }

    function showTestList(result) {
        var html = "";
        if (result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                html += '<tr><td>' + result[i].title + '</td>' +
                        '<td>' + result[i].description + '</td>' +
                        '<td>' + result[i].difficultyLevel + '</td>';

                var skills = new Array(); // "";
                for (var j = 0; j < result[i].skills.length; j++) {
                    skills.push(result[i].skills[j]);
                }
                var base = '<c:url value="/admin/testquestion.do?tid=" />';
                html += '<td>' + skills.join(",") + '</td>' +
                        '<td><a class="btn btn-default btn-sm" href="' + base + result[i].id + '">Add Question</a>&nbsp;' +
                        '<button type="button" onclick="publishTest(\'' + result[i].id + '\')" class="btn btn-success btn-sm" >Publish</buttton></td></tr>';
            }
            $("#testtable").html(html);
        } else {
            html = "<tr><td colspan='5'>No test found.</td></tr>";
            $("#testtable").html(html);
        }
    }

    function createTest() {

        var title = $("#title").val();
        var description = $("#description").val();
        var difficultyLevel = $("#difficultyLevel").val();
        var skills = $("#skills").val();
        var duration = $("#duration").val();
        var noOfQuestions = $("#noOfQuestions").val();
        var data = "title=" + title + "&description=" + description + "&difficultyLevel=" + difficultyLevel +
                "&skills=" + skills + "&noOfQuestions=" + noOfQuestions + "&duration=" + duration;

        $.ajax({
            url: '<c:url value="/admin/createtest.do"/>',
            type: 'post',
            dataType: 'json',
            data: data,
            success: function (response) {
                if (response.result && response.result == 1) {
                    $("#title").val("");
                    $("#description").val("");
                    $("#difficultyLevel").val("1");
                    $("#skills").val("");
                    $("#duration").val("");
                    $("#noOfQuestions").val("");

                    $("#testmsg").html("<div class='alert alert-success'>Test created successfully.</div>");
                    setTimeout(function () {
                        $("#testmsg").html("");
                    }, 3000);

                    loadTestList();

                } else if (response.result && response.result == 0) {
                    $("#testmsg").html("<div class='alert alert-danger'>Error occured while creating Test.</div>");
                    setTimeout(function () {
                        $("#testmsg").html("");
                    }, 3000);
                }
            },
            error: function (jqXhr, status) {
                $("#testmsg").html("<div class='alert alert-danger'>Error occured while processing request.</div>");
                setTimeout(function () {
                    $("#testmsg").html("");
                }, 3000);
            }
        });
        return false;
    }

    function showCreateTest() {
        $("#test-table-section").hide();
        $("#create-test-section").show();
    }

    function showTestTable() {
        $("#test-table-section").show();
        $("#create-test-section").hide();
    }

    function publishTest(tid) {
        var data = "tid=" + tid;
        $.ajax({
            url: '<c:url value="/admin/publishtest.do"/>',
            type: 'get',
            dataType: 'json',
            data: data,
            success: function (response) {

                if (response.error) {
                    $("#test-table-section-msg").html("<div class='alert alert-warning'>" + response.error + ".</div>");
                    setTimeout(function () {
                        $("#test-table-section-msg").html("");
                    }, 3000);
                } else {

                    if (response.result && response.result == 1) {
                        $("#test-table-section-msg").html("<div class='alert alert-success'>Test published successfully.</div>");
                        setTimeout(function () {
                            $("#test-table-section-msg").html("");
                        }, 3000);
                        loadTestList();

                    } else if (response.result && response.result == 0) {
                        $("#test-table-section-msg").html("<div class='alert alert-danger'>Error occured while publishing Test.</div>");
                        setTimeout(function () {
                            $("#test-table-section-msg").html("");
                        }, 3000);
                    }
                }
            },
            error: function (jqXhr, status) {
                $("#testmsg").html("<div class='alert alert-danger'>Error occured while processing request.</div>");
                setTimeout(function () {
                    $("#testmsg").html("");
                }, 3000);
            }
        });
    }
</script>
</body>
</html>