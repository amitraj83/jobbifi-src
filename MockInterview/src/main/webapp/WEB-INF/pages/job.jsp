<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Jobs</title>
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
                            <h2>Search Jobs</h2>

                            <div class="input-group">
                                <input id="searchKey" type="text" class="form-control"
                                       placeholder="I am looking for a...">
                                            <span class="input-group-btn">
									<button id="searchJobBtn" class="btn btn-default" type="button">Search</button>
									</span>
                            </div>
                            <div class="col-md-offset-3 col-md-9" id="searchJobMessage"></div>
                        </div>
                    </div>
                    <div class="row" style="padding-top:20px;">
                        <div id="searchJobResult" class="col-md-12"></div>
                    </div>
                </div>
                <div class="col-md-4 page-sidebar">
                    <aside>
                        <div class="widget sidebar-widget white-container social-widget">
                            <h5 class="widget-title">Share Us</h5>

                            <div class="widget-content">
                                <div class="row row-p5">
                                    <div class="col-xs-6 col-md-3 share-box facebook">
                                        <div class="count">86</div>
                                        <a href="#">Facebook</a>
                                    </div>
                                    <div class="col-xs-6 col-md-3 share-box twitter">
                                        <div class="count">2.2k</div>
                                        <a href="#">Twitter</a>
                                    </div>
                                    <div class="col-xs-6 col-md-3 share-box google">
                                        <div class="count">324</div>
                                        <a href="#">Google +</a>
                                    </div>
                                    <div class="col-xs-6 col-md-3 share-box linkedin">
                                        <div class="count">1.5k</div>
                                        <a href="#">LinkedIn</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="widget sidebar-widget text-center">
                            <a href="#"><img alt="" src="img/content/sidebar-ad.png"></a>
                        </div>
                        <div class="white-container">
                            <div class="widget sidebar-widget">
                                <h5 class="widget-title">5 Tips To Pass Your Interview!</h5>

                                <div class="widget-content">
                                    <div class="fitvidsjs">
                                        <div class="fluid-width-video-wrapper" style="padding-top: 50%;">
                                            <iframe frameborder="0" allowfullscreen=""
                                                    src="//www.youtube.com/embed/A6XUVjK9W4o?rel=0&amp;controls=2&amp;showinfo=0"
                                                    id="fitvid544396"></iframe>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="widget sidebar-widget">
                                <h5 class="widget-title">The Poll</h5>

                                <div class="widget-content">
                                    <p>Are you satisfied with your current employer?</p>

                                    <div class="radio">
                                        <label>
                                            <input type="radio" checked="" value="" name="sidebarpoll"> Definitely Yes
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" value="" name="sidebarpoll"> Rather Yes
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" value="" name="sidebarpoll"> I'm not sure
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" value="" name="sidebarpoll"> Rather Not
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" value="" name="sidebarpoll"> Not at all
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </aside>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Contact Me Modal -->
<div class="modal fade bs-example-modal-sm" id="contactmeModal" role="dialog" aria-hidden="true">
    <div class="modal-dialog login-modal" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">Send Message</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div id="contactme" class="mainbox col-md-12">
                            <form class="form form-horizontal" id="newmessageForm">
                                <input type="hidden" name="jobid" value=""/>
                                <input type="hidden" name="jobtitle" value=""/>
                                <input type="hidden" name="parentMessageId" value=""/>
                                <input type="hidden" name="refentity" value=""/>
                                <input type="hidden" name="to"/>

                                <div class="form-group">
                                    <label class="col-md-2">Message</label>

                                    <div class="col-md-8">
                                        <textarea name="message" id="inputmessage" rows="5" cols="80"
                                                  class="form-control"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-2 col-md-8">
                                        <button type="submit" id="newmessage" class="btn btn-default">Send</button>
                                        <img id="contactMeloader" style="display: none;" alt="Processing..."
                                             src="<c:url value=" /resources/img/loading.gif " />">
                                    </div>
                                </div>
                            </form>
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
    function searchJobMessage() {
        $("#searchJobMessage").html("<label class='clearfix error'>Please enter what are you looking for?<label>");
        setTimeout(function () {
            $("#searchJobMessage").html("");
        }, 3600 * 2);
    }

    $(document).ready(function () {

        $("#nav_jobs").addClass("active");
        $("#searchJobBtn").click(function () {
            var searchKey = $("#searchKey").val();
            if (searchKey == "") {
                searchJobMessage();
                return;
            }
            searchJobs(searchKey, 1, 10);
        });

        $("#searchKey").on("keypress", function (e) {
            if (e.which == 13) {
                var searchKey = $("#searchKey").val();
                if (searchKey == "") {
                    searchJobMessage()
                    return;
                }
                searchJobs(searchKey, 1, 10);
                return false;
            }
        });

        var searchKey = getParameterByName("searchKey");
        if (searchKey != "") {
            $("#searchKey").val(searchKey);
            searchJobs(searchKey, 1, 10);
        } else {
            searchJobs(null, 1, 10);
        }

        searchFeaturedJobs("", 1, 10);


        $("#newmessageForm").validate({
            rules: {
                message: {
                    required: true
                },
            },
            submitHandler: function (form) {
                submitForm();
                return false;
            }
        });
    });

    function searchJobs(searchKey, currentPage, rows) {

        if (searchKey == null) {
            searchKey = "''";
        }
        var start = ((currentPage - 1) * 10);
        $.ajax({
            type: "GET",
            url: BASE_URL + "searchjobs.do",
            data: "searchkey=" + searchKey + "&start=" + start + "&rows=" + rows,
        }).done(function (res) {
            var jsonResult = jQuery.parseJSON(res);
            var jsonDocList = jsonResult.JSON_DOC_LIST;
            var resultsFound = false;
            var allResultsView = "";
            if (null != jsonDocList && Object.keys(jsonDocList).length > 0) {
                $.each(jsonDocList, function (i) {
                    var joburl = BASE_URL + "jobdetail.do?jid=" + jsonDocList[i].id;
                    var oneresult = generateJobItem(jsonDocList[i]);
                    allResultsView = allResultsView + oneresult;
                    resultsFound = true;
                });
            }

            if (resultsFound == true) {
                var totalRecords = jsonResult.NUM_OF_RESULTS;
                var pagination = getPaginationHTML(totalRecords, currentPage, searchKey);
                allResultsView = allResultsView + pagination;
            } else {
                allResultsView = "<div class='alert alert-info'>No Results Found</div>";
            }
            $("#searchJobResult").html(allResultsView);
            $('html, body').animate({
                scrollTop: 0
            }, 'slow');
        });
    }

    function generateJobItem(job) {
        var joburl = BASE_URL + "jobdetail.do?jid=" + job.id;
        var skills = job.skills;
        var skillsHTML = "";
        for (var i = 0; i < skills.length; i++) {
            skillsHTML += '<span class="job_search_result_skill">' + skills[i] + '</span>';
            if (i != skills.length - 1) {
                skillsHTML += ' | '
            }
        }

        var description = job.description;
        var descLess = description.substring(0, 180);
        if (description.length > 180) {
            descLess += "...";
        }

        var d = new Date(Number(job.dt));
        var html =
                '<div class="row">' +
                '<div class="col-md-2 text-center" style="padding:0px;">' +
                '<a target="_blank" href="' + BASE_URL + 'userprofile.do?name=' + job.interviewer + '">' +
                '<img class="img-thumb" alt="" src="' + BASE_URL + job.profilepic + '">' +
                '<span class="job_search_result_employer_img">' + job.interviewer + '</span>' +
                '</a>' +
                '<button onclick="showContactMeScreen(\'' + job.interviewer + '\');" class="btn btn-default btn-xs" type="button"><i class="fa fa-envelope-o"></i> Contact me</button>' +
                '</div>' +
                '<div class="col-md-10">' +
                '<div class="row" style="padding:4px;">' +
                '<div class="col-md-12">' +
                '<span class="job_search_result_title"><a target="_blank" href="' + joburl + '">' + job.title + '</a></span>' +
                '<span class="job_search_result_date pull-right">' + prettyDate(d) + '</span>' +
                '<span class="job_search_result_employer">' + job.companyname + '</span><br />' +
                '</div>' +
                '</div>' +
                '<div class="row" style="padding:4px;">' +
                '<div class="col-md-12">' +
                '<span class="job_search_result_location"><i class="fa fa-map-marker"></i> ' +
                job.location + '</span>' +
                '<span class="job_search_result_skills"><i class="fa fa-briefcase"></i> ' + job.experience + ' yrs</span>' +
                '<span class="job_search_result_salary"><i class="fa fa-inr"></i> ' + job.salary + '</span>' +

                '</div>' +
                '</div>' +
                '<div class="row"  style="padding:4px;"><div class="col-md-12">' +
                '<span class="job_search_result_skill_header"><dl class="dl-horizontal"><dt>Skills</dt><dd>' +
                skillsHTML +
                '</dd></dl></span></div></div>' +
                '<div class="row" style="padding:4px;">' +
                '<div class="col-md-12">' +
                '<span class="job_search_result_description"><dl class="dl-horizontal"><dt>Description</dt><dd>' + descLess + '</dd></dl></span>' +
                '<a class="job_search_result_description_read_more pull-right" href="' + joburl + '">Read more</a>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div><hr />';
        return html;
    }

    function getPaginationHTML(totalRecords, currentPage, searchKey) {
        var PAGE_SIZE = 10;
        var totalPages = Math.ceil(totalRecords / PAGE_SIZE);
        var html = "";
        if (totalPages > 1) {
            html += "<ul class='pagination pull-right'>"
            for (var i = 0; i < totalPages; i++) {
                if (currentPage == (i + 1)) {
                    html = html + "<li class='active'><a href='javascript:void(0)'>" + (i + 1) + "</a></li>";
                } else {
                    html = html + "<li class=''><a href='javascript:searchJobs(\"" + searchKey + "\"," + (i + 1) + ")'>" + (i + 1) + "</a></li>";
                }
            }
            html += "</ul>"
        }
        return html;
    }

    function showContactMeScreen(interviewer) {
        if (LOGIN_USER == null) {
            showInfo("You need to login to contact " + interviewer);
            showLoginBox();
            return;
        }

        $('input:hidden[name="to"]').val(interviewer);
        $("#contactmeModal").modal("show");
    }

    function submitForm() {
        $("#contactMeloader").show();
        $.ajax({
            type: 'POST',
            url: BASE_URL + 'sendnewmessage.do',
            data: $("#newmessageForm").serialize(),
            async: false
        }).done(function (res) {
            $("#contactmeModal").modal("hide");
            showSuccess("Your message has been sent.");
            $("#inputmessage").val("");
        }).always(function (jqXHR, textStatus) {
            $("#contactMeloader").hide();
        });
    }

    function searchFeaturedJobs(searchKey, currentPage, rows) {
        var PAGE_SIZE = rows;
        var start = ((currentPage - 1) * PAGE_SIZE);
        if (searchKey == "") {
            searchKey = '""';
        }
        $.ajax({
            type: "GET",
            url: BASE_URL + "searchjobs.do",
            data: "searchkey=" + searchKey + "&start=" + start + "&rows=" + rows,
        }).done(function (res) {
            var jsonResult = jQuery.parseJSON(res);
            var jsonDocList = jsonResult.JSON_DOC_LIST;
            var resultsFound = false;
            var allResultsView = "";
            if (null != jsonDocList && Object.keys(jsonDocList).length > 0) {
                $.each(jsonDocList, function (i) {
                    var joburl = BASE_URL + "jobdetail.do?jid=" + jsonDocList[i].id;
                    var oneresult = '<li class=" col-md-6">' +
                            '<div class="jobs-item with-thumb">' +
                            '<div class="thumb">' +
                            '<a target="_blank" href="' + BASE_URL + 'userprofile.do?name=' + jsonDocList[i].interviewer + '">' +
                            '<img src="' + jsonDocList[i].profilepic + '" title="' + jsonDocList[i].interviewer + '" ></a>' +
                            '<br/><a title="' + jsonDocList[i].interviewer + '" target="_blank" href="' + BASE_URL + 'userprofile.do?name=' + jsonDocList[i].interviewer + '">' + jsonDocList[i].interviewer + '</a></div>' +
                            '<div class="clearfix visible-xs"></div>' +
                            '<h6 class="title"><a target="_blank" href="' + joburl + '">' + jsonDocList[i].title + '</a></h6>' +
                            '<span class="location">' + jsonDocList[i].companyname + '</span>' +
                            '</div>	' +
                            '</li>';
                    allResultsView = allResultsView + oneresult;
                });
            }
            $("ul#featuredjob").html(allResultsView);
        });
    }
</script>
</body>

</html>
