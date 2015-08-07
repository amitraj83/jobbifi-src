<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- No cache header -->
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Mock Interviews</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/modern-business.css" rel="stylesheet">
    <link href="libraries/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="js/jquery-1.11.0.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/sha1.js"></script>
    <script src="js/library.js"></script>
    <script src="js/jquery.numeric.js"></script>
    <script type='text/javascript' src='js/jquery-fileupload/vendor/jquery.ui.widget.js'></script>
    <script type='text/javascript' src='js/jquery-fileupload/jquery.iframe-transport.js'></script>
    <script type='text/javascript' src='js/jquery-fileupload/jquery.fileupload.js'></script>
    <script>
        var loc = document.location + "";
        var BASE_URL = loc.substring(0, loc.lastIndexOf("/")) + "/";
        var linkedInDetails = ${IN_REG};
        var linkedInUsername = '${IN_USERNAME}';

        var editSkillSet = function (skill, year) {
            var askill = '<li>' +
                    ' <div class="alert alert-info alert-dismissible" role="alert" style="margin:0px;padding:7px;">' +
                    '<button type="button" class="close" data-dismiss="alert" style="position:static">&nbsp;<span aria-hidden="true">&times;</span>' +
                    '<span class="sr-only">Delete</span>' +
                    '</button>' +
                    '<a href="#">' +
                    '<span class="badge pull-right" id="sky">' + year + '</span><span id="sk">' + skill + '</span></a>' +
                    '</div>' +
                    '</li>';
            $("#update_skills").append(askill);
        }

        var positionHTML = '<div class="form-group" id="positionDiv">' +
                '  <label class="col-lg-3 control-label">Position:</label>' +
                '  <div class="col-lg-9">' +
                '      <div class="row">' +
                '          <div class="col-md-1" style="padding:8px 0px 0px 15px;">Title:</div>' +
                '          <div class="col-md-4" ><input name="title" class="form-control" type="text" value="$title$"></div>' +
                '          <div class="col-md-2" style="padding:8px 0px 0px 1px;">Company:</div>' +
                '          <div class="col-md-4" style="padding:0px 0px 0px 5px;"><input name="companyName" class="form-control" type="text" value="$cn$"></div>' +
                '     </div>  ' +
                '      <div class="row">' +
                '          <div class="col-md-1" style="padding:8px 0px 0px 15px;">Start:</div>' +
                '          <div class="col-md-4" ><input name="startyear" class="form-control" type="text" value="$sy$"></div>' +
                '          <div class="col-md-2" style="padding:8px 0px 0px 1px;">End:</div>' +
                '          <div class="col-md-4" style="padding:0px 0px 0px 5px;"><input name="endyear" class="form-control" type="text" value="$ey$"></div>' +
                '      </div>  ' +
                '      <textarea name="description" rows="3" class="form-control">$desc$</textarea>' +
                '  </div>' +
                '</div>';


        var educationHTML = ' <div class="form-group" id="edudiv"><label class="col-lg-3 control-label">Education:</label>' +
                '<div class="col-lg-9">' +
                '   <table style="padding:5px">' +
                '     <tr>' +
                '           <td>Degree:</td>' +
                '           <td><input name="degree" class="form-control" type="text" value="$degree$"></td>' +
                '           <td>Major:</td>' +
                '           <td><input name="major" class="form-control" type="text" value="$major$"></td>' +
                '     </tr>' +
                '     <tr>' +
                '          <td>University:</td>' +
                '           <td colspan="3"><input name="university" class="form-control" type="text" value="$uni$"> </td>' +
                '     </tr>' +
                '     <tr>' +
                '           <td>Start:</td>' +
                '           <td><input name="startyear" class="form-control" type="text" value="$sy$"></td>' +
                '           <td>End:</td>' +
                '           <td><input name="endyear" class="form-control" type="text" value="$ey$"></td>' +
                '     </tr>' +
                '     ' +
                '   </table>' +
                '  </div>' +
                '</div>';

        $(document).ready(function () {

            $("#updatecountries").html(allCountriesOption);
            $("#perhourrate").val('0');
            $("#in_email").val(linkedInDetails.emailAddress);
            $("#update_shortcv").val(linkedInDetails.summary);
            $("#in_gen_un").val(linkedInUsername);
            if (linkedInDetails.pictureUrl != null) {
                $("#update_img").attr({src: linkedInDetails.pictureUrl});
            }

            var allskills = linkedInDetails.skillsList;
            for (var i = 0; i < allskills.length; i++) {
                var skillYear = (null == allskills[i].expYear || "" == allskills[i].expYear) ? 1 : allskills[i].expYear;
                editSkillSet(allskills[i].skillname, skillYear);
            }

            $('#update_addskill').click(function () {
                editSkillSet($("#update_skill").val(), $("#update_skillexp").val());
            });

            var allpositions = linkedInDetails.positionsList;
            for (var i = 0; i < allpositions.length; i++) {
                var screen = positionHTML.split("$title$").join((allpositions[i].title == null) ? "" : allpositions[i].title)
                        .split("$cn$").join(allpositions[i].companyName)
                        .split("$sy$").join(allpositions[i].startYear)
                        .split("$ey$").join(allpositions[i].endYear)
                        .split("$desc$").join(allpositions[i].description);
                $("#update_allpositions").append(screen);
            }

            $(document).on('click', '#update_addmoreposition', function (event) {
                event.preventDefault();
                var screen = positionHTML.split("$title$").join("")
                        .split("$cn$").join("")
                        .split("$sy$").join("")
                        .split("$ey$").join("")
                        .split("$desc$").join("");
                $("#update_allpositions").append(screen);
            });

            var alleducations = linkedInDetails.educationList;
            for (var i = 0; i < alleducations.length; i++) {
                var sy = (alleducations[i].startYear == null || alleducations[i].startYear == 0) ? "" : alleducations[i].startYear;
                var ey = (alleducations[i].endYear == null || alleducations[i].endYear == 0) ? "" : alleducations[i].endYear;
                var screen = educationHTML.split("$degree$").join(alleducations[i].degree)
                        .split("$major$").join(alleducations[i].fieldOfStudy)
                        .split("$uni$").join(alleducations[i].schoolname)
                        .split("$sy$").join(sy)
                        .split("$ey$").join(ey);
                $("#update_alleducations").append(screen);
            }

            $(document).on('click', '#update_addmoreeducation', function (event) {
                event.preventDefault();
                var screen = educationHTML.split("$degree$").join("")
                        .split("$major$").join("")
                        .split("$uni$").join("")
                        .split("$sy$").join("")
                        .split("$ey$").join("");
                $("#update_alleducations").append(screen);
            });

            $("#perhourrate").numeric();
            $('input[type="radio"][name="usertype"]').on('change', function (e) {
                var role = $(this).val();
                if (role == 'INTERVIEWER') {
                    $("#in_user_role_rate").show();
                } else {
                    $("#in_user_role_rate").hide();
                }
            });

            $(document).on('click', '#inuserregButton', function () {
                $("#in_reg_error").html("");
                var generateUserName = $("#in_gen_un").val().trim();
                if (generateUserName == "") {
                    $("#in_reg_error").html("Please provide a username");
                    return;
                }
                if (generateUserName.length > 10) {
                    $("#in_reg_error").html("Username size must be less than 10 characters");
                    return;
                }

                var inUserRole = $('input[type="radio"][name="usertype"]:checked').val();
                if (inUserRole == 0) {
                    $("#in_reg_error").html("Please select your role");
                    return;
                }

                if (inUserRole == 'INTERVIEWER') {
                    var hourlyRate = $("#perhourrate").val().trim();
                    if (hourlyRate == "" || hourlyRate == "0") {
                        $("#in_reg_error").html("If your role is Interviewer, you must provide your hourly rate.");
                        return;
                    }
                    if (!isFinite(String(hourlyRate))) {
                        $("#in_reg_error").html("Please provide numerical value as your hourly rate.");
                        return;
                    }
                }

                var country = $("#updatecountries").val();
                if (country == "") {
                    $("#in_reg_error").html("Please select the country.");
                    return;
                }
                var shortcv = $("#update_shortcv").val();

                var skills = new Array();
                var skill_li = $("#update_skills").find("li > div.alert");
                for (var i = 0; i < skill_li.length; i++) {
                    var li = skill_li[i];
                    var skill = {};
                    skill.skill = $(li).find("#sk").text();
                    skill.skillYear = $(li).find("#sky").text();
                    skills.push(skill);
                }

                var edudiv = $("#update_alleducations").children("#edudiv");
                var educations = new Array();
                for (var i = 0; i < edudiv.length; i++) {
                    var adiv = edudiv[i];
                    var inputs = $(adiv).find("input");
                    var edu = {};
                    edu.degree = $(inputs[0]).val();
                    edu.fieldOfStudy = $(inputs[1]).val();
                    edu.schoolname = $(inputs[2]).val();
                    edu.startYear = $(inputs[3]).val();
                    edu.endYear = $(inputs[4]).val();
                    educations.push(edu);

                    if (edu.degree.trim() == "") {
                        $("#in_reg_error").html("Please provide degree of education " + (i + 1) + ".");
                        return;
                    }
                    if (edu.fieldOfStudy.trim() == "") {
                        $("#in_reg_error").html("Please provide field of study in education " + (i + 1) + ".");
                        return;
                    }
                    if (edu.startYear.trim() == "") {
                        $("#in_reg_error").html("Please provide start year of education " + (i + 1) + ".");
                        return;
                    }
                    if (edu.endYear.trim() == "") {
                        $("#in_reg_error").html("Please provide end year of education " + (i + 1) + ".");
                        return;
                    }
                    if (edu.schoolname.trim() == "") {
                        $("#in_reg_error").html("Please provide school name of education " + (i + 1) + ".");
                        return;
                    }
                }
                var educationParam = "educations=" + JSON.stringify(educations);

                var positiondivs = $("#update_allpositions").children("#positionDiv");
                var positions = new Array();
                for (var i = 0; i < positiondivs.length; i++) {
                    var adiv = positiondivs[i];
                    var position = {};
                    position.title = $(adiv).find("input[name='title']").val();
                    position.companyName = $(adiv).find("input[name='companyName']").val();
                    position.startYear = $(adiv).find("input[name='startyear']").val();
                    position.endYear = $(adiv).find("input[name='endyear']").val();
                    position.description = $(adiv).find("textarea[name='description']").val();
                    positions.push(position);

                    if (position.title.trim() == "") {
                        $("#in_reg_error").html("Please provide title of position " + (i + 1) + ".");
                        return;
                    }
                    if (position.companyName.trim() == "") {
                        $("#in_reg_error").html("Please provide company name in position " + (i + 1) + ".");
                        return;
                    }
                    if (position.startYear.trim() == "") {
                        $("#in_reg_error").html("Please provide start year of position " + (i + 1) + ".");
                        return;
                    }
                    if (position.endYear.trim() == "") {
                        $("#in_reg_error").html("Please provide end year of position " + (i + 1) + ".");
                        return;
                    }
                }
                var positionParam = "positions=" + JSON.stringify(positions);
                var industry = (linkedInDetails.industry == null) ? "" : linkedInDetails.industry;
                var rate = (inUserRole == 'INTERVIEWER' ? $("#perhourrate").val().trim() : "");
                var profilepic = $("#update_img").attr("src");
                var param = "username=" + generateUserName +
                        "&useremail=" + $("#in_email").val().trim() +
                        "&type=" + inUserRole +
                        "&rate=" + rate +
                        "&cv=" + shortcv +
                        "&industry=" + industry +
                        "&skills=" + JSON.stringify(skills) +
                        "&country=" + country +
                        "&" + educationParam +
                        "&" + positionParam +
                        "&profilepic=" + profilepic;

                $.ajax({
                    type: "POST",
                    url: BASE_URL + "linkedinuserregister.do",
                    data: param
                }).done(function (msg) {
                    var jsonResponse = jQuery.parseJSON(msg);
                    if (jsonResponse.result == "1") {
                        alert("You have been successfully registered");
                        window.location.href = BASE_URL;
                    } else if (jsonResponse.result == "2") {
                        alert("User already exist. ");
                    }
                });
            });

            // cancel registration
            $(document).on('click', '#inuserregCancel', function () {
                $.ajax({
                    type: "POST",
                    url: BASE_URL + "linkedinuserregistercancel.do",
                }).done(function (msg) {
                    if (msg == "DONE") {
                        window.location.href = BASE_URL + 'index.jsp';
                    }
                });
            });

            $("#profilepicbtn").click(function () {
                $("#profilepicdoc").trigger('click');
            });

            $('#profilepicdoc').fileupload({
                dataType: 'json',
                maxChunkSize: 20000000,
                done: function (e, data) {
                    var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
                    $("#update_img").attr({src: jsonResponse.profilepic});
                },
                add: function (e, data) {
                    data.submit();
                }
            });

        });
    </script>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="javascript:void(0)">Mock Interview</a>
        </div>
    </div>
</nav>

<div class="container" style="margin-top:40px">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <h3 style="margin:0px">Your Profile Details</h3>
            <hr>
            <div class="row">
                <div class="col-md-12">
                    <div id="in_reg_error"></div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Profile Picture</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-3">
                                    <img src="images/face.jpg" id="update_img" class="avatar img-square" alt="avatar"
                                         width="150px" height="150px">
                                </div>
                                <div class="col-md-5">
                                    <br><br><br><br><br>
                                    <input id="profilepicdoc" type="file" name="file" data-url="pauth/fileupload.do"
                                           multiple style="opacity:0;  filter:alpha(opacity: 0);">

                                    <div>
                                        <button id="profilepicbtn" type="button" class="btn btn-sm btn-default"> Upload
                                            Photo
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">General Information </h3>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Email</label>

                                    <div class="col-md-9">
                                        <input class="form-control" type="text" id="in_email"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">Username</label>

                                    <div class="col-md-9">
                                        <input class="form-control" type="text" id="in_gen_un" placeholder="username"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="usertype" class="col-md-3 control-label">User Type</label>

                                    <div class="col-md-9">
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="usertype" checked="checked"
                                                       value="INTERVIEWER"> Interviewer
                                            </label>
                                        </div>
                                        <div class="radio">
                                            <label>
                                                <input type="radio" name="usertype" value="INTERVIEWEE"> Interviewee
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">General Information</h3>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form">
                                <div class="form-group" id="in_user_role_rate">
                                    <label class="col-lg-3 control-label">Rate(per hour):</label>

                                    <div class="col-lg-9">
                                        <div class="input-group">
                                            <span class="input-group-addon">$</span>
                                            <input type="text" id="perhourrate" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">Present Country:</label>

                                    <div class="col-lg-9">
                                        <select class="form-control" id="updatecountries"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">Skills:</label>

                                    <div class="col-lg-9">
                                        <div class="row">
                                            <div class="col-md-2" style="padding:8px 0px 0px 15px;">Skill Name:</div>
                                            <div class="col-md-4" style="padding:2px"><input id="update_skill"
                                                                                             style="width:170px;"
                                                                                             class="form-control"
                                                                                             type="text" value=""
                                                                                             placeholder="Skill"></div>
                                            <div class="col-md-2" style="padding:8px 0px 0px 10px;">Experience:</div>
                                            <div class="col-md-2" style="padding:2px 0px 0px 5px;"><select
                                                    id="update_skillexp" style="width:90px;" class="form-control">
                                                <option value="0">0 years</option>
                                                <option value="1">1 years</option>
                                                <option value="2">2 years</option>
                                                <option value="3">3 years</option>
                                                <option value="4">4 years</option>
                                                <option value="5">5 years</option>
                                                <option value="6">6 years</option>
                                                <option value="7">7 years</option>
                                                <option value="8">8 years</option>
                                                <option value="9">9 years</option>
                                                <option value="10">10 years</option>
                                                <option value="11">11 years</option>
                                                <option value="12">12 years</option>
                                                <option value="13">13 years</option>
                                                <option value="14">14 years</option>
                                                <option value="15">15 years</option>
                                                <option value="16">16 years</option>
                                                <option value="17">17 years</option>
                                                <option value="18">18 years</option>
                                                <option value="19">19 years</option>
                                                <option value="20">20 years</option>
                                            </select>
                                            </div>
                                            <div class="col-md-2" style="padding:2px">
                                                <button type="button" class="btn btn-success " id="update_addskill">
                                                    Add
                                                </button>
                                            </div>
                                        </div>
                                        <ul class="nav nav-pills" id="update_skills">

                                        </ul>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label">Short CV:</label>

                                    <div class="col-lg-9">
                                        <textarea rows="3" class="form-control" id="update_shortcv"
                                                  placeholder="Enter cv"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>


                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Update Your Professional Details</h3>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" id="update_allpositions">

                            </form>
                            <a href="#" class="pull-right" id="update_addmoreposition">Add More Position</a>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Update Your Education Details</h3>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form" id="update_alleducations">


                            </form>
                            <a href="#" class="pull-right" id="update_addmoreeducation">Add More Education</a>
                        </div>
                    </div>
                    <center>
                        <button type="button" class="btn btn-success" id="inuserregButton">Register</button>
                        &nbsp;&nbsp;&nbsp;
                        <button type="button" class="btn btn-danger" id="inuserregCancel">Cancel</button>
                    </center>
                </div>
            </div>

            <hr>

        </div>
    </div>
</div>
</body>
</html>