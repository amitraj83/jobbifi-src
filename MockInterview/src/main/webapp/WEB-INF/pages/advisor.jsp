<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Advisors</title>
</head>

<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-md-12">
                            <h2>Search Advisors</h2>

                            <div class="input-group">
                                <input id="searchKey" type="text" class="form-control" placeholder="Search Advisors">
                                            <span class="input-group-btn">
                                    <button id="searchAdvisorBtn" class="btn btn-default" type="button">Search</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 text-center" id="searchAdvisorMessage"></div>
                    </div>
                    <div class="row">
                        <div id="searchAdvisorResult" class="col-md-12"></div>
                    </div>
                    <div class="row">
                        <div id="pagination"></div>
                    </div>
                </div>
                <!-- end main content -->
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {

        var searchKey = getParameterByName("searchKey");
        if (searchKey != null) {
            $("#searchKey").val(searchKey);
            searchAdvisors(searchKey, 1);
        } else {
            searchAdvisors(null, 1);
        }
        $("#searchAdvisorBtn").click(function (e) {
            search(e);
        });

        $("#searchKey").on("keypress", function (e) {
            search(e);
        });


    });

    function search(e) {
        if (e.which == 13) {
            var searchKey = $("#searchKey").val();
            if (searchKey == "") {
                showWarning("Please enter the search term you want to search.");
                return;
            }
            searchAdvisors(searchKey, 1);
            return false;
        }
    }

    var PAGE_SIZE = 10;


    function searchAdvisors(searchKey, currentPage) {

        $("#searchAdvisorResult").html("");
        if (searchKey == null) {
            searchKey = "''";
        }

        var start = ((currentPage - 1) * 10);
        $.ajax({
            type: "POST",
            url: BASE_URL + "search.do",
            data: "searchkey=" + searchKey + "&start=" + start,
            async: false
        }).done(function (msg) {
            var jresponse = jQuery.parseJSON(msg);
            var json = jresponse.JSON_DOC_LIST;
            var resultsFound = false;
            $.each(json, function (i) {

                var mySkills = '';

                /*var skillsJson = json[i].skills.replace(/\[/,'').replace(/\]/,'').split(",");
                 for(var j=0;j<(skillsJson.length < 5 ? skillsJson.length : 5);j++){
                 mySkills = mySkills + skillsJson[j]+",";
                 }
                 if(mySkills.length > 0) mySkills = mySkills.substring(0, mySkills.length-1);*/
                var myEducation = '';
                var schoolJson = '';
                var degreeJson = '';
                var fieldofstudyJson = '';
                if (json[i].school != null) {
                    schoolJson = json[i].school.replace(/\[/, '').replace(/\]/, '').split(",");
                    if (json[i].degree != null)
                        degreeJson = json[i].degree.replace(/\[/, '').replace(/\]/, '').split(",");
                    if (json[i].fieldofstudy != null)
                        fieldofstudyJson = json[i].fieldofstudy.replace(/\[/, '').replace(/\]/, '').split(",");

                    myEducation = '<i class="fa fa-graduation-cap"></i><span class="ml5">';

                    for (var j = 0; j < (schoolJson.length < 3 ? schoolJson.length : 2); j++) {
                        myEducation = myEducation + '<span class="ml5"><b>' + schoolJson[j] + "</b> " + degreeJson[j] + ' - ' + fieldofstudyJson[j] + ' </span>';
                    }
                    myEducation += "</span>";
                }

                var myPositions = '<span class="ml5">';
                var compJson = '';
                var posJson = '';
                if (json[i].position != null) {
                    posJson = json[i].position.replace(/\[/, '').replace(/\]/, '').split(",");
                    if (json[i].companies = null)
                        compJson = json[i].companies.replace(/\[/, '').replace(/\]/, '').split(",");
                    for (var j = 0; j < (compJson.length < 3 ? compJson.length : 2); j++) {
                        myPositions = myPositions + '<span><b>' + compJson[j] + '</b>, ' + posJson[j] + '</span><br/>';
                    }
                    myPositions += "</span>";
                }
                var status = "";

                var searchItem =
                        '<div class="row">' +
                        '<div class="col-md-3">' +
                        '<img src="{{ image }}" style="width:100%;height:150px;" alt="..." class="img-thumbnail">' +
                        '<center><input type="number" class="userRating" /> <span>{{ rating }}</span></center>' +
                        '</div>' +
                        '<div class="col-md-9">' +
                        '<div class="advisor-name"><a href="userprofile.do?name={{ username }}">{{ username }}</a></div>' +
                        '<div class="advisor-title"><strong>Skills</strong> {{ #skills }}<span class="label label-default">{{ . }}</span> {{ /skills }}</div>' +
                        '<div><span class="advisor-rate"><strong>Rate:&nbsp;&nbsp;</strong><i class="fa fa-inr"></i> 3000/hr</span><span><strong>Experience:</strong> 10 years</span></div>' +

                        '<table class="advisor-result-table">' +
                        '{{ #positions }}' +
                        '<tr>' +
                        '<td><i {{#icon}}class="fa fa-suitcase"{{/icon}} style="margin-right:15px"></i></td>' +
                        '<td>{{ position }}</td>' +
                        '</tr>' +
                        '{{ /positions }}' +
                        '<tr>' +
                        '{{ #educations }}' +
                        '<td><i {{ #icon }} class="fa fa-graduation-cap"{{ /icon }} style="margin-right:15px"></i></td>' +
                        '<td>{{ education }}</td>' +
                        '</tr>' +
                        '{{ /educations }}' +
                        '</table>' +
                        '</div>' +
                        '</div><hr />';

                templateData = {
                    'username': json[i].username,
                    'country': json[i].country,
                    'rating': "(3/5)",
                    'positions': [{
                        "position": "Software Engineer at Microsoft",
                        "icon": true
                    }, {
                        "position": "Software Engineer at Google"
                    }],
                    'skills': [
                        "Java",
                        "PHP",
                        "XML"
                    ],
                    'educations': [{
                        "education": "B-Tech, Computer Science, IIT Kanpur",
                        "icon": true
                    }, {
                        "education": "M-Tech, Computer Science, IIT Kanpur"
                    }, {
                        "education": "Ph.D., Computer Science, Trinity College Dublin"
                    }],
                    'image': "images/face.jpg",
                }
                console.log(templateData);
                searchItem = Mustache.to_html(searchItem, templateData);


                // '<div class="candidates-item">'
                // +'<div class="thumb"><img src="'+profilepic+'" alt=""></div>'
                // +'<h3 class="title">'+json[i].username+'</h3>'
                // +'<ul class="top-btns">'
                // +'<li><i class="fa fa-map-marker"></i> '+json[i].country+'</li>'
                // +'</ul>'
                // +'<div class="content"><p class="skills"><b>Skills</b> '+mySkills+'</p>'
                // +'<p class="position"><i class="fa fa-suitcase"></i> '+myPositions+'</p>'
                // +'<p class="education">'+myEducation+'</p>'
                // +'</div></div>';


                $("#searchAdvisorResult").append(searchItem);
                $("input.userRating").val(3).rating({
                    'min': 0,
                    'max': 5,
                    'step': 1,
                    'readonly': true,
                    'showClear': false,
                    'showCaption': false
                });
                resultsFound = true;
            });

            if (resultsFound == false) {
                $("#searchAdvisorResult").html("<div class='alert alert-info'>No results found.</div>");
                $(".pagination").hide();
            }

            var totalRecords = jresponse.NUM_OF_RESULTS;
            var html = getPaginationHTMLForAdvisor(totalRecords, currentPage, searchKey, "INTERVIEWER");
            $("#paginationdiv").html(html);
            $('html, body').animate({
                scrollTop: 0
            }, 'slow');
        });
    }

    function getPaginationHTMLForAdvisor(totalRecords, currentPage, searchKey, searchFor) {
        var totalPages = Math.ceil(totalRecords / PAGE_SIZE);
        var html = "";
        if (totalPages > 1) {
            html += "<ul class='pagination pull-right'>"
            for (var i = 0; i < totalPages; i++) {
                if (currentPage == (i + 1)) {
                    html = html + "<li class='active'><a href='javascript:void(0)'>" + (i + 1) + "</a></li>";
                } else {
                    html = html + "<li class=''><a href='javascript:searchAdvisors(\"" + searchKey + "\"," + (i + 1) + ")'>" + (i + 1) + "</a></li>";
                }
            }
            html += "</ul>"
        }
        return html;
    }
</script>
</body>

</html>
