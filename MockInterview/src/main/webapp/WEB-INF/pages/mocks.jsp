<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Mocks Interviews</title>
</head>

<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<sec:authorize access="isAuthenticated()" var="isAuthenticated"></sec:authorize>
<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="row">
            	<div id="leftmenu" class="col-md-3"> 
            		<%@ include file="/WEB-INF/pages/myInterviewSidebar.jsp" %>
            	</div> 
                <div id="centersearch" class="col-md-6">
                    <div class="row">
                        <div class="col-md-12">
                            <sec:authorize access="hasRole('ROLE_INTERVIEWEE')" var="isInterviewee"></sec:authorize>
                            <h2>Search Mock Interviews</h2>
							<div id="searchMockMessage"></div>
                            <div class="input-group">
                                <input id="searchKey" type="text" class="form-control" placeholder="Enter skills"/>
                                            <span class="input-group-btn">
                                    <button id="searchMockBtn" class="btn btn-default" type="button">Search</button>
                                </span>
                            </div>
                        </div>
                        
                    </div>
                    <div class="row" style="margin-top: 30px;">
                        <div id="searchMockResult" class="col-md-12"><center><i class="fa fa-spinner fa-spin"></i></center></div>
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
                                        <button type="button" class="btn btn-success btn-square">Support a candidate</button>
                                    </div>
                                </div>
                                <div class="stepwizard-row">   
                                    <div class="stepwizard-step">
                                        <button type="button" class="btn btn-info btn-square">Provide a mock interview</button>
                                    </div>
                                </div>
                                <div class="stepwizard-row">   
                                    <div class="stepwizard-step">
                                        <button type="button" class="btn btn-warning btn-square">Get paid and increase rating!</button>
                                    </div>
                                </div>
                                
                            </center>
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
    function searchMockMessage() {
        $("#searchMockMessage").html("<div class='alert alert-danger'> Please enter what are you looking for? </div>");
        setTimeout(function () {
            $("#searchMockMessage").html("");
        }, 3600 * 2);
    }

    $(function () {
    	if(${isAuthenticated} == false){
    		$(".scroll.active").removeClass("active");
            $("#leftmenu").remove();
            $("#centersearch").removeClass("col-md-6");
            $("#centersearch").addClass("col-md-9");

    	}
        $("#sidenav_searchMock").addClass("active");        
        $("#searchMockBtn").click(function (e) {
            var searchKey = $("#searchKey").val();
            if (searchKey == "") {
                searchMockMessage();
                return;
            }
            searchMocks(searchKey, 1);
        });
        
        $("#searchKey").on("keypress", function (e) {
            search(e);
        });
        var searchKey = getParameterByName("searchKey");
        if (searchKey != null) {
            $("#searchKey").val(searchKey);
            searchMocks(searchKey, 1);
        } else {
            searchMocks(null, 1);
        }

    });

    function search(e) {
        if (e.which == 13) {
            var searchKey = $("#searchKey").val();
            if (searchKey == "") {
                searchMockMessage();
                return;
            }
            searchMocks(searchKey, 1);
            return false;
        }
    }

    var TotalRecords = 0;

    function searchMocks(searchKey, currentPage) {
        var searchterm = searchKey;
        if (null == searchKey || "null" == searchKey) {
            searchKey = "''";
        }

        var start = ((currentPage - 1) * 10);
        $.ajax({
            type: "GET",
            url: BASE_URL + "interviewsearch.do",
            data: "searchkey=" + searchKey + "&start=" + start,
        }).done(function (msg) {
            var wholeJSON = jQuery.parseJSON(msg);
            var json = wholeJSON.JSON_DOC_LIST;
            var resultsFound = false;
            var allResultsView = "";
            for (var i = 0; i < Object.keys(json).length; i++) {
                
                var profilepic = "resources/images/face.png";
                if ("" != wholeJSON.pics[json[i].interviewee]) {
                    profilepic = wholeJSON.pics[json[i].interviewee];
                }

                var attachment = "";
                var bidHtml = "";
                if(${isInterviewee} == false){
                bidHtml = "<button class='btn btn-xs btn-default' onclick='showBidPopUp(\"" + json[i].id + "\", this, \"" + json[i].interviewee + "\")'><i class='fa fa-share-square' style='color:white'></i> Support me</button>";
                }
                if (LOGIN_USER != null) {

                    if (null != wholeJSON.iidFiles[json[i].id] && "" != wholeJSON.iidFiles[json[i].id]) {
                        attachment = '<br/><b>Attachment:</b>&nbsp;<a href="' + BASE_URL + wholeJSON.iidFiles[json[i].id].url + '">' + wholeJSON.iidFiles[json[i].id].original_name + '</a>';
                    }

                    var bid = wholeJSON.iidBids[json[i].id];
                    if (bid != null) {
                        /*bidHtml = "<button class='btn btn-xs btn-default'> <i class='fa fa-lg fa-info-circle' style='color:white'></i> Bid placed</button>";*/
                        bidHtml = '<span class="label label-success">Bid Placed</span>';
                    }

                }

                var d = new Date(json[i].dt);
                var skills = json[i].skills;
                var skillsHTML = "";
                if (skills != null) {
                    for (var j = 0; j < skills.length; j++) {
                        skillsHTML += '<span class="label label-info">' + skills[j] + '</span>&nbsp;';
                    }
                }

                var description = json[i].description;
                var mockTemplate =
                        '<div class="row">' +
                        '<div class="col-md-3 text-center">' +
                        '<a target="_blank" href="{{ BASE_URL }}userprofile.do?name={{ interviewee }}">' +
                        '<img alt="{{ interviewee }}" src="{{ BASE_URL }}{{ image }}" style="width:90%;height:144px;"><br />' +
                        '<span class="mock_interviewee">{{ interviewee }}</span></a>' +
                        '<div id="bidb_{{ id }}" >{{{ bid }}}</div>' +
                        '</div>' +
                        '<div class="col-md-9">' +
                        '<div class="row">' +
                        '<div class="col-md-12">' +
                        '<span class="mock_title" ><a target="_blank" href="{{ BASE_URL }}mockdetail.do?iid={{ id }}">{{ title }}</a></span><span class="pull-right">' +
                        '<i class="fa fa-calendar"></i>{{ datePosted }}</span>' +
                            // '<span class="label label-warning pull-right">{{ status }}</span>'+
                        '</div></div>' +
                        '<div class="row">' +
                        '<div class="col-md-12">' +
                        '<div><i class="fa fa-tags"></i><strong class="grey-heading">Skills:</strong> {{{ skills }}}</div>' +
                        '<div><span class="mock-budget "><i class="fa fa-credit-card"></i><strong class="grey-heading">Budget:</strong> <span style="color:#454545;">$ {{ budget }}</span></span>' +
                        '<span><i class="fa fa-briefcase"></i><strong  class="grey-heading">Experience:</strong> <span style="color:#454545;">{{{ experience }}} yr</span></span></div>' +
                        '</div>' +
                        '</div>' +
                        '<div class="row"><div class="col-md-12">' +
                        '<strong class="grey-heading">Description:</strong> ' +
                        '<span style="color:#454545;">{{ description }}</span></div></div>' +
                        '</div>' +
                        '</div><hr />';

                var mockData = {
                    'id': json[i].id,
                    'budget': wholeJSON[json[i].id ].budget, // json[i].budget,
                    'experience': wholeJSON[json[i].id ].experience, // json[i].experience,
                    'title': json[i].title,
                    'interviewee': json[i].interviewee,
                    /*'image': profilepic,*/
                    'image': "images/face.jpg",
                    'skills': skillsHTML,
                    'BASE_URL': BASE_URL,
                    'datePosted': prettyDate(new Date(Number(json[i].dt))),
                    'description': getDescriptionLess(description, 155),
                    'attachment': attachment,
                    'bid': bidHtml

                };
                var oneresult = Mustache.to_html(mockTemplate, mockData);

                allResultsView = allResultsView + oneresult;
                resultsFound = true;
            }

            if (resultsFound == true) {
                var totalRecords = wholeJSON.NUM_OF_RESULTS;
                var pagination = getPaginationHTMLForMock(totalRecords, currentPage, searchterm);
                allResultsView = allResultsView + pagination;
                $("#searchMockResult").html(allResultsView);
            } else {
                $("#searchMockResult").html("<div class='alert alert-info'>No Results Found.</div>");
            }


            /*$(document).on('click', ".mock_title", function(event){
                alert("Clicked");
                alert($(this).attr("iid"));
            });*/

            $("#maincontainer").html(screen);
            $('html, body').animate({
                scrollTop: 0
            }, 'slow');
        });
    }


    function showMockDetails (iid) {
        alert("IID "+iid);
        $.ajax({
            type: "GET",
            url: "<c:url value='/mockdetail.do'/>",
            data: "iid="+iid,
        }).done(function (msg) {
            alert("Yes show details"+msg);
        });
    }

    function getPaginationHTMLForMock(totalRecords, currentPage, searchKey) {
        var PAGE_SIZE = 10;
        var totalPages = Math.ceil(totalRecords / PAGE_SIZE);
        var html = "";
        if (totalPages >= 1) {
            html += "<ul class='pagination pull-right'>"
            for (var i = 0; i < totalPages; i++) {
                if (currentPage == (i + 1)) {
                    html = html + "<li class='active'><a href='javascript:void(0)'>" + (i + 1) + "</a></li>";
                } else {
                    html = html + "<li class=''><a href='javascript:searchMocks(\"" + searchKey + "\"," + (i + 1) + ")'>" + (i + 1) + "</a></li>";
                }
            }
            html += "</ul>"
        }
        return html;
    }

    function getInterviewStatusLiteral(status) {
        if (status == 0) {
            return "OPEN";
        } else if (status == 1) {
            return "IN PROGRESS";
        } else if (status == 2) {
            return "IN PROGRESS";
        } else if (status == 3) {
            return "IN PROGRESS";
        } else if (status == 4) {
            return "APPROVAL PENDING";
        } else if (status == 5) {
            return "COMPLETED";
        } else if (status == 6) {
            return "CANCELLED";
        } else if (status == 7) {
            return "REPOSTED";
        } else if (status == 8) {
            return "USER RATED";
        } else if (status == 9) {
            return "DISPUTE";
        }
    }

    function showBidPopUp(iid, btn, interviewee) {

        if (LOGIN_USER == null) {
            showLoginBox();
            showInfo("Please login to place bid on the Mock.");
            return;
        }
        $(".popover").popover("hide");
        $(btn).popover("destroy");
        var html = "<form style='padding:15px;' class='form form-horizontal' id='pop_" + iid + "'>" +
                "<input name='iid' type='hidden' value='" + iid + "' />" +
                "<input name='bidfid' type='hidden' value='' />" +
                "<div class='form-group'><input class='form-control input-sm' type='text' name='price' placeholder='Bid Amount' /></div>" +
                "<div class='form-group'><textarea class='form-control input-sm' name='msg' placeholder='Message'></textarea></div>" +
                // "<button id='placebid_"+iid+"'  onclick='placeBid(\"" + iid + "\",\"" + interviewee + "\")' class='btn btn-default' type='button'>Bid</button>" +
            "<button id='placebid_"+iid+"' iid='"+iid+"' interviewee='"+interviewee+"'  class='btn btn-default' type='button'>Bid</button>" +
                "</form>";
        var options = {
            title: 'Place Bid <a class="close" style="float:right" onclick="hideBidPopUp()">&times;</a>',
            html: true,
            content: html,
            container: 'body'
        };
        $(btn).popover(options);
        $(btn).popover("show");

        $("#placebid_"+iid).one("click", function(){
            console.log("clicked  --   "+$(this).attr("iid")+"   --  "+$(this).attr("interviewee"));
            var iid = $(this).attr("iid");
            var interviewee = $(this).attr("interviewee");

            $.ajax({
                type: "GET",
                url: "<c:url value='/makebid.do'/>",
                data: $("#pop_" + iid).serialize(),
            }).done(function (msg) {
                var json = jQuery.parseJSON(msg);
                if (json.success == 1) {
                    showSuccess("Your bid placed successfully.");
                    /*$("#bidb_" + iid).html("<button class='btn btn-sm btn-default'><i class='fa fa-gavel'></i> $" +$("#pop_"+iid + " input[name='price']").val() + "</button>");*/
                    $("#bidb_" + iid).html('<span class="label label-success">Bid Placed</span>');
                } else {
                    showError("Request failed to bid on the Mock.");
                }
                hideBidPopUp(iid);
            });

        addToContactList(interviewee);

        });

    }

    function hideBidPopUp() {
        $(".popover").popover("hide");
    }

    function placeBid() {
        
    }

    function addToContactList(interviewee) {
        $.ajax({
            type: 'POST',
            url: BASE_URL + 'addcontactlist.do',
            data: "to=" + interviewee
        });
    }
</script>
</body>

</html>
