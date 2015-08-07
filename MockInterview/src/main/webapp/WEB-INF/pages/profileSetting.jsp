<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Profile Setting</title>
    <style type="text/css">

    </style>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">

                <div class="col-md-3">
                    <div class="list-group list-default">
                        <a href="<c:url value='/profilesetting.do'/>" class="list-group-item active">Update Profile</a>
                        <a href="<c:url value='/changepassword.do'/>" class="list-group-item">Change Password</a>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Your Profile</h2>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Update Your Profile Picture</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 text-center">
                                            <img src="http://placehold.it/150" id="update_img" class="avatar img-square"
                                                 alt="avatar" width="150px" height="150px">
                                            <input id="uploadphoto" type="file" name="file"
                                                   data-url="aauth/fileupload.do?type=profilepicupdate"
                                                   style="opacity:0;  filter:alpha(opacity: 0);">
                                            <button type="button" id="uploadphotobtn" class="btn btn-sm btn-default">
                                                Upload Photo
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">General Information</h3>
                                </div>
                                <div class="panel-body">
                                    <form id="editgeneralinfo" class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label">Rate(per hour)</label>

                                            <div class="col-lg-9">
                                                <div class="input-group">
                                                    <span class="input-group-addon">$</span>
                                                    <input type="text" id="perhourrate" name="perhourrate"
                                                           class="form-control">
                                                </div>


                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label">Present Country</label>

                                            <div class="col-lg-9">
                                                <select class="form-control" id="updatecountries"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label">Skills</label>

                                            <div class="col-lg-9">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <ul class="nav nav-pills" id="update_skills"></ul>
                                                        <br/>
                                                    </div>
                                                    <div class="col-md-12 form-inline">
                                                        <label for="update_skill">Skill name</label>
                                                        <input id="update_skill" class="form-control" type="text"
                                                               value="" placeholder="Skill">
                                                        <label for="update_skillexp">Experience</label>
                                                        <select id="update_skillexp" class="form-control">
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
                                                        <button type="button" class="btn btn-success "
                                                                id="update_addskill">Add
                                                        </button>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label">Short CV</label>

                                            <div class="col-lg-9">
                                                <textarea rows="3" class="form-control" id="update_shortcv"></textarea>
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
                                    <form class="form-horizontal" role="form" id="update_allpositions"></form>
                                    <a href="javascript:void(0)" class="btn btn-default pull-right"
                                       id="update_addmoreposition">Add More Position</a>
                                </div>
                            </div>

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Update Your Education Details</h3>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal" role="form" id="update_alleducations"></form>
                                    <a href="javascript:void(0)" class="btn btn-default pull-right"
                                       id="update_addmoreeducation">Add More Education</a>
                                </div>
                            </div>
                            <div id="buttonPanel" style="text-align:center; ">
                                <button type="submit" class="btn btn-success" id="edit_profile_button">Update Details
                                </button>
                                <button type="button" class="btn btn-danger">Cancel</button>
                            </div>
                            <br/>
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
    var username = '<sec:authentication property="principal.username" />';

    var positionHTML = '<div class="form-group" id="positionDiv">' +
            '  <label class="col-md-12">Position</label>' +
            '  <div class="col-md-12">' +
            '      <div class="row form-group">' +
            '          <label class="col-md-2 control-label">Title</label>' +
            '          <div class="col-md-4"><input name="title" class="form-control" type="text" value="$title$"></div>' +
            '          <label class="col-md-2 control-label">Company</label>' +
            '          <div class="col-md-4"><input name="companyName" class="form-control" type="text" value="$cn$"></div>' +
            '     </div>  ' +
            '     <div class="row form-group">' +
            '          <label class="col-md-2 control-label">Start</label>' +
            '          <div class="col-md-4"><input name="startyear" class="form-control" type="text" value="$sy$"></div>' +
            '          <label class="col-md-2 control-label">End</label>' +
            '          <div class="col-md-4"><input name="endyear" class="form-control" type="text" value="$ey$"></div>' +
            '      </div>  ' +
            '     <div class="row form-group">' +
            '          <label class="col-md-2 control-label">Description</label>' +
            '          <div class="col-md-10"><textarea name="description" rows="3" class="form-control">$desc$</textarea></div>' +
            '      </div>  ' +
            '  </div>' +
            '</div>';

    var educationHTML = ' <div class="form-group" id="edudiv">' +
            '<label class="col-md-12">Education</label>' +
            '<div class="col-md-12">' +
            '      <div class="row form-group">' +
            '           <label class="col-md-2 control-label">Degree</label>' +
            '           <div class="col-md-4"><input name="degree" class="form-control" type="text" value="$degree$"></div>' +
            '           <label class="col-md-2 control-label">Major</label>' +
            '           <div class="col-md-4"><input name="major" class="form-control" type="text" value="$major$"></div>' +
            '     </div>' +
            '      <div class="row form-group">' +
            '          <label class="col-md-2 control-label">University</label>' +
            '          <div class="col-md-10"><input name="university" class="form-control" type="text" value="$uni$"> </div>' +
            '     </div>' +
            '      <div class="row form-group">' +
            '           <label class="col-md-2 control-label">Start</label>' +
            '           <div class="col-md-4"><input name="startyear" class="form-control" type="text" value="$sy$"></div>' +
            '           <label class="col-md-2 control-label">End</label>' +
            '           <div class="col-md-4"><input name="endyear" class="form-control" type="text" value="$ey$"></div>' +
            '     </div>' +
            '  </div>' +
            '</div>';

    $(function () {

        // photo upload
        $("#uploadphotobtn").click(function () {
            $("#uploadphoto").trigger('click');
        });
        $('#uploadphoto').attr({'data-url': "aauth/fileupload.do?type=profilepicupdate&interviewer="});
        $('#uploadphoto').fileupload({
            dataType: 'json',
            maxChunkSize: 20000000,
            done: function (e, data) {
                var jsonResponse = jQuery.parseJSON(data.jqXHR.responseText);
                if (null != jsonResponse) {
                    $("#update_img").attr({src: BASE_URL + jsonResponse.path});
                }
            },
            add: function (e, data) {
                data.submit();
            }
        });

// btn click event
        $('#update_addskill').click(function () {
            editSkillSet($("#update_skill").val(), $("#update_skillexp").val());
        });

        $('#update_addmoreeducation').click(function (event) {
            var screen = educationHTML.split("$degree$").join("")
                    .split("$major$").join("")
                    .split("$uni$").join("")
                    .split("$sy$").join("")
                    .split("$ey$").join("");
            $("#update_alleducations").append(screen);
        });

        $('#update_addmoreposition').click(function (event) {
            var screen = positionHTML.split("$title$").join("")
                    .split("$cn$").join("")
                    .split("$sy$").join("")
                    .split("$ey$").join("")
                    .split("$desc$").join("");
            $("#update_allpositions").append(screen);
        });

        $.ajax({
            url: BASE_URL + "getProfileDetails.do?username=" + username,
            type: 'GET',
            async: false,
            success: function (res) {

                var user = jQuery.parseJSON(res);

                $("#update_img").attr("src", BASE_URL + user.profilepic);
                $("#updatecountries").html(allCountriesOption);
                $("#perhourrate").val(user.rate);

                var allskills = user.skilllist;
                for (var i = 0; i < allskills.length; i++) {
                    editSkillSet(allskills[i].skill, allskills[i].skillYear);
                }

                $("#updatecountries").val(user.country);
                $("#update_shortcv").val(user.cv);

                /* position */
                var allpositions = user.positions;
                for (var i = 0; i < allpositions.length; i++) {
                    var screen = positionHTML.split("$title$").join(allpositions[i].title)
                            .split("$cn$").join(allpositions[i].companyName)
                            .split("$sy$").join(allpositions[i].startYear)
                            .split("$ey$").join(allpositions[i].endYear)
                            .split("$desc$").join(allpositions[i].description);
                    $("#update_allpositions").append(screen);
                }

                /* education */
                var alleducations = user.educations;
                for (var i = 0; i < alleducations.length; i++) {
                    var screen = educationHTML.split("$degree$").join(alleducations[i].degree)
                            .split("$major$").join(alleducations[i].fieldOfStudy)
                            .split("$uni$").join(alleducations[i].schoolname)
                            .split("$sy$").join(alleducations[i].startYear)
                            .split("$ey$").join(alleducations[i].endYear);
                    $("#update_alleducations").append(screen);
                }

            }
        });
    });


    // Validate profile
    $(function () {

        $("#editgeneralinfo").validate({
            rules: {
                perhourrate: {required: true},
            },
            submitHandler: function (form) {
                submitForm();
            }
        });

    });

    var editSkillSet = function (skill, year) {
        var askill = '<li>' +
                '<div class="alert alert-success alert-dismissible" role="alert">' +
                '<button type="button" class="close" data-dismiss="alert" style="position:static">&nbsp;<span aria-hidden="true">&times;</span>' +
                '<span class="sr-only">Delete</span>' +
                '</button>' +
                '<a href="#">' +
                '<span id="sk">' + skill + '</span> <span class="badge" id="sky">' + year + '</span></a>' +
                '</div>' +
                '</li>';
        $("#update_skills").append(askill);
    }

    //edit profile
    $('#edit_profile_button').click(function () {
        var param = "";
        var country = $("#updatecountries").val();
        param += "country=" + country;

        var skills = new Array();
        var skill_li = $("#update_skills").find("li > div.alert");
        for (var i = 0; i < skill_li.length; i++) {
            var li = skill_li[i];
            var skill = {};
            skill.skill = $(li).find("#sk").text();
            skill.skillYear = $(li).find("#sky").text();
            skills.push(skill);
        }
        param += "&skills=" + JSON.stringify(skills);
        param += "&rate=" + $("#perhourrate").val();
        param += "&cv=" + $("#update_shortcv").val();

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
        }
        param += "&educations=" + JSON.stringify(educations);

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
        }
        param += "&positions=" + JSON.stringify(positions);

        $.ajax({
            type: "GET",
            url: "<c:url value='updateprofile.do'/>",
            data: param
        }).done(function (msg) {
            var resData = jQuery.parseJSON(msg);
            if (resData.status == 1) {

                $("#buttonPanel").prepend("<span class='label label-success'>Your profile has been updated successfully.</span>");


            } else {
                alert("Your profile could't update, please try again.");
            }
        });

    });

    var allCountriesOption = '<option value="">Country...</option>'
            + '<option value="Afganistan">Afghanistan</option>'
            + '<option value="Albania">Albania</option>'
            + '<option value="Algeria">Algeria</option>'
            + '<option value="American Samoa">American Samoa</option>'
            + '<option value="Andorra">Andorra</option>'
            + '<option value="Angola">Angola</option>'
            + '<option value="Anguilla">Anguilla</option>'
            + '<option value="Antigua &amp; Barbuda">Antigua &amp; Barbuda</option>'
            + '<option value="Argentina">Argentina</option>'
            + '<option value="Armenia">Armenia</option>'
            + '<option value="Aruba">Aruba</option>'
            + '<option value="Australia">Australia</option>'
            + '<option value="Austria">Austria</option>'
            + '<option value="Azerbaijan">Azerbaijan</option>'
            + '<option value="Bahamas">Bahamas</option>'
            + '<option value="Bahrain">Bahrain</option>'
            + '<option value="Bangladesh">Bangladesh</option>'
            + '<option value="Barbados">Barbados</option>'
            + '<option value="Belarus">Belarus</option>'
            + '<option value="Belgium">Belgium</option>'
            + '<option value="Belize">Belize</option>'
            + '<option value="Benin">Benin</option>'
            + '<option value="Bermuda">Bermuda</option>'
            + '<option value="Bhutan">Bhutan</option>'
            + '<option value="Bolivia">Bolivia</option>'
            + '<option value="Bonaire">Bonaire</option>'
            + '<option value="Bosnia &amp; Herzegovina">Bosnia &amp; Herzegovina</option>'
            + '<option value="Botswana">Botswana</option>'
            + '<option value="Brazil">Brazil</option>'
            + '<option value="British Indian Ocean Ter">British Indian Ocean Ter</option>'
            + '<option value="Brunei">Brunei</option>'
            + '<option value="Bulgaria">Bulgaria</option>'
            + '<option value="Burkina Faso">Burkina Faso</option>'
            + '<option value="Burundi">Burundi</option>'
            + '<option value="Cambodia">Cambodia</option>'
            + '<option value="Cameroon">Cameroon</option>'
            + '<option value="Canada">Canada</option>'
            + '<option value="Canary Islands">Canary Islands</option>'
            + '<option value="Cape Verde">Cape Verde</option>'
            + '<option value="Cayman Islands">Cayman Islands</option>'
            + '<option value="Central African Republic">Central African Republic</option>'
            + '<option value="Chad">Chad</option>'
            + '<option value="Channel Islands">Channel Islands</option>'
            + '<option value="Chile">Chile</option>'
            + '<option value="China">China</option>'
            + '<option value="Christmas Island">Christmas Island</option>'
            + '<option value="Cocos Island">Cocos Island</option>'
            + '<option value="Colombia">Colombia</option>'
            + '<option value="Comoros">Comoros</option>'
            + '<option value="Congo">Congo</option>'
            + '<option value="Cook Islands">Cook Islands</option>'
            + '<option value="Costa Rica">Costa Rica</option>'
            + '<option value="Cote DIvoire">Cote DIvoire</option>'
            + '<option value="Croatia">Croatia</option>'
            + '<option value="Cuba">Cuba</option>'
            + '<option value="Curaco">Curacao</option>'
            + '<option value="Cyprus">Cyprus</option>'
            + '<option value="Czech Republic">Czech Republic</option>'
            + '<option value="Denmark">Denmark</option>'
            + '<option value="Djibouti">Djibouti</option>'
            + '<option value="Dominica">Dominica</option>'
            + '<option value="Dominican Republic">Dominican Republic</option>'
            + '<option value="East Timor">East Timor</option>'
            + '<option value="Ecuador">Ecuador</option>'
            + '<option value="Egypt">Egypt</option>'
            + '<option value="El Salvador">El Salvador</option>'
            + '<option value="Equatorial Guinea">Equatorial Guinea</option>'
            + '<option value="Eritrea">Eritrea</option>'
            + '<option value="Estonia">Estonia</option>'
            + '<option value="Ethiopia">Ethiopia</option>'
            + '<option value="Falkland Islands">Falkland Islands</option>'
            + '<option value="Faroe Islands">Faroe Islands</option>'
            + '<option value="Fiji">Fiji</option>'
            + '<option value="Finland">Finland</option>'
            + '<option value="France">France</option>'
            + '<option value="French Guiana">French Guiana</option>'
            + '<option value="French Polynesia">French Polynesia</option>'
            + '<option value="French Southern Ter">French Southern Ter</option>'
            + '<option value="Gabon">Gabon</option>'
            + '<option value="Gambia">Gambia</option>'
            + '<option value="Georgia">Georgia</option>'
            + '<option value="Germany">Germany</option>'
            + '<option value="Ghana">Ghana</option>'
            + '<option value="Gibraltar">Gibraltar</option>'
            + '<option value="Great Britain">Great Britain</option>'
            + '<option value="Greece">Greece</option>'
            + '<option value="Greenland">Greenland</option>'
            + '<option value="Grenada">Grenada</option>'
            + '<option value="Guadeloupe">Guadeloupe</option>'
            + '<option value="Guam">Guam</option>'
            + '<option value="Guatemala">Guatemala</option>'
            + '<option value="Guinea">Guinea</option>'
            + '<option value="Guyana">Guyana</option>'
            + '<option value="Haiti">Haiti</option>'
            + '<option value="Hawaii">Hawaii</option>'
            + '<option value="Honduras">Honduras</option>'
            + '<option value="Hong Kong">Hong Kong</option>'
            + '<option value="Hungary">Hungary</option>'
            + '<option value="Iceland">Iceland</option>'
            + '<option value="India">India</option>'
            + '<option value="Indonesia">Indonesia</option>'
            + '<option value="Iran">Iran</option>'
            + '<option value="Iraq">Iraq</option>'
            + '<option value="Ireland">Ireland</option>'
            + '<option value="Isle of Man">Isle of Man</option>'
            + '<option value="Israel">Israel</option>'
            + '<option value="Italy">Italy</option>'
            + '<option value="Jamaica">Jamaica</option>'
            + '<option value="Japan">Japan</option>'
            + '<option value="Jordan">Jordan</option>'
            + '<option value="Kazakhstan">Kazakhstan</option>'
            + '<option value="Kenya">Kenya</option>'
            + '<option value="Kiribati">Kiribati</option>'
            + '<option value="Korea North">Korea North</option>'
            + '<option value="Korea Sout">Korea South</option>'
            + '<option value="Kuwait">Kuwait</option>'
            + '<option value="Kyrgyzstan">Kyrgyzstan</option>'
            + '<option value="Laos">Laos</option>'
            + '<option value="Latvia">Latvia</option>'
            + '<option value="Lebanon">Lebanon</option>'
            + '<option value="Lesotho">Lesotho</option>'
            + '<option value="Liberia">Liberia</option>'
            + '<option value="Libya">Libya</option>'
            + '<option value="Liechtenstein">Liechtenstein</option>'
            + '<option value="Lithuania">Lithuania</option>'
            + '<option value="Luxembourg">Luxembourg</option>'
            + '<option value="Macau">Macau</option>'
            + '<option value="Macedonia">Macedonia</option>'
            + '<option value="Madagascar">Madagascar</option>'
            + '<option value="Malaysia">Malaysia</option>'
            + '<option value="Malawi">Malawi</option>'
            + '<option value="Maldives">Maldives</option>'
            + '<option value="Mali">Mali</option>'
            + '<option value="Malta">Malta</option>'
            + '<option value="Marshall Islands">Marshall Islands</option>'
            + '<option value="Martinique">Martinique</option>'
            + '<option value="Mauritania">Mauritania</option>'
            + '<option value="Mauritius">Mauritius</option>'
            + '<option value="Mayotte">Mayotte</option>'
            + '<option value="Mexico">Mexico</option>'
            + '<option value="Midway Islands">Midway Islands</option>'
            + '<option value="Moldova">Moldova</option>'
            + '<option value="Monaco">Monaco</option>'
            + '<option value="Mongolia">Mongolia</option>'
            + '<option value="Montserrat">Montserrat</option>'
            + '<option value="Morocco">Morocco</option>'
            + '<option value="Mozambique">Mozambique</option>'
            + '<option value="Myanmar">Myanmar</option>'
            + '<option value="Nambia">Nambia</option>'
            + '<option value="Nauru">Nauru</option>'
            + '<option value="Nepal">Nepal</option>'
            + '<option value="Netherland Antilles">Netherland Antilles</option>'
            + '<option value="Netherlands">Netherlands (Holland, Europe)</option>'
            + '<option value="Nevis">Nevis</option>'
            + '<option value="New Caledonia">New Caledonia</option>'
            + '<option value="New Zealand">New Zealand</option>'
            + '<option value="Nicaragua">Nicaragua</option>'
            + '<option value="Niger">Niger</option>'
            + '<option value="Nigeria">Nigeria</option>'
            + '<option value="Niue">Niue</option>'
            + '<option value="Norfolk Island">Norfolk Island</option>'
            + '<option value="Norway">Norway</option>'
            + '<option value="Oman">Oman</option>'
            + '<option value="Pakistan">Pakistan</option>'
            + '<option value="Palau Island">Palau Island</option>'
            + '<option value="Palestine">Palestine</option>'
            + '<option value="Panama">Panama</option>'
            + '<option value="Papua New Guinea">Papua New Guinea</option>'
            + '<option value="Paraguay">Paraguay</option>'
            + '<option value="Peru">Peru</option>'
            + '<option value="Phillipines">Philippines</option>'
            + '<option value="Pitcairn Island">Pitcairn Island</option>'
            + '<option value="Poland">Poland</option>'
            + '<option value="Portugal">Portugal</option>'
            + '<option value="Puerto Rico">Puerto Rico</option>'
            + '<option value="Qatar">Qatar</option>'
            + '<option value="Republic of Montenegro">Republic of Montenegro</option>'
            + '<option value="Republic of Serbia">Republic of Serbia</option>'
            + '<option value="Reunion">Reunion</option>'
            + '<option value="Romania">Romania</option>'
            + '<option value="Russia">Russia</option>'
            + '<option value="Rwanda">Rwanda</option>'
            + '<option value="St Barthelemy">St Barthelemy</option>'
            + '<option value="St Eustatius">St Eustatius</option>'
            + '<option value="St Helena">St Helena</option>'
            + '<option value="St Kitts-Nevis">St Kitts-Nevis</option>'
            + '<option value="St Lucia">St Lucia</option>'
            + '<option value="St Maarten">St Maarten</option>'
            + '<option value="St Pierre &amp; Miquelon">St Pierre &amp; Miquelon</option>'
            + '<option value="St Vincent &amp; Grenadines">St Vincent &amp; Grenadines</option>'
            + '<option value="Saipan">Saipan</option>'
            + '<option value="Samoa">Samoa</option>'
            + '<option value="Samoa American">Samoa American</option>'
            + '<option value="San Marino">San Marino</option>'
            + '<option value="Sao Tome & Principe">Sao Tome &amp; Principe</option>'
            + '<option value="Saudi Arabia">Saudi Arabia</option>'
            + '<option value="Senegal">Senegal</option>'
            + '<option value="Seychelles">Seychelles</option>'
            + '<option value="Sierra Leone">Sierra Leone</option>'
            + '<option value="Singapore">Singapore</option>'
            + '<option value="Slovakia">Slovakia</option>'
            + '<option value="Slovenia">Slovenia</option>'
            + '<option value="Solomon Islands">Solomon Islands</option>'
            + '<option value="Somalia">Somalia</option>'
            + '<option value="South Africa">South Africa</option>'
            + '<option value="Spain">Spain</option>'
            + '<option value="Sri Lanka">Sri Lanka</option>'
            + '<option value="Sudan">Sudan</option>'
            + '<option value="Suriname">Suriname</option>'
            + '<option value="Swaziland">Swaziland</option>'
            + '<option value="Sweden">Sweden</option>'
            + '<option value="Switzerland">Switzerland</option>'
            + '<option value="Syria">Syria</option>'
            + '<option value="Tahiti">Tahiti</option>'
            + '<option value="Taiwan">Taiwan</option>'
            + '<option value="Tajikistan">Tajikistan</option>'
            + '<option value="Tanzania">Tanzania</option>'
            + '<option value="Thailand">Thailand</option>'
            + '<option value="Togo">Togo</option>'
            + '<option value="Tokelau">Tokelau</option>'
            + '<option value="Tonga">Tonga</option>'
            + '<option value="Trinidad &amp; Tobago">Trinidad &amp; Tobago</option>'
            + '<option value="Tunisia">Tunisia</option>'
            + '<option value="Turkey">Turkey</option>'
            + '<option value="Turkmenistan">Turkmenistan</option>'
            + '<option value="Turks &amp; Caicos Is">Turks &amp; Caicos Is</option>'
            + '<option value="Tuvalu">Tuvalu</option>'
            + '<option value="Uganda">Uganda</option>'
            + '<option value="Ukraine">Ukraine</option>'
            + '<option value="United Arab Erimates">United Arab Emirates</option>'
            + '<option value="United Kingdom">United Kingdom</option>'
            + '<option value="United States of America">United States of America</option>'
            + '<option value="Uraguay">Uruguay</option>'
            + '<option value="Uzbekistan">Uzbekistan</option>'
            + '<option value="Vanuatu">Vanuatu</option>'
            + '<option value="Vatican City State">Vatican City State</option>'
            + '<option value="Venezuela">Venezuela</option>'
            + '<option value="Vietnam">Vietnam</option>'
            + '<option value="Virgin Islands (Brit)">Virgin Islands (Brit)</option>'
            + '<option value="Virgin Islands (USA)">Virgin Islands (USA)</option>'
            + '<option value="Wake Island">Wake Island</option>'
            + '<option value="Wallis &amp; Futana Is">Wallis &amp; Futana Is</option>'
            + '<option value="Yemen">Yemen</option>'
            + '<option value="Zaire">Zaire</option>'
            + '<option value="Zambia">Zambia</option>'
            + '<option value="Zimbabwe">Zimbabwe</option>';


</script>
</body>
</html>
