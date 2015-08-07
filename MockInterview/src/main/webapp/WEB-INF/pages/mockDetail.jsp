<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Mock Interview</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div id="wrapper">
    <div id="page-content">
        <div class="container">
            <div class="col-md-8 page-content">
                <div class="clearfix white-container">
                    <h2 style="padding-right: 90px;" class="title"> ${title}</h2>
                    <!-- check this -->
                    <c:choose>
                        <c:when test="${statusString !='OPEN'}">
                            <h4 class="interview-status">${status_string}</h4>
                        </c:when>
                    </c:choose>
                    <p>
                        <i class="fa fa-map-marker"></i>&nbsp;
                        ${experience} yr exp. | $${budget} | Posted on <span id="date"></span>
                    </p>

                    <p>
                        Skills:
                        <c:forEach var="skill" items="${skills}">
                        <span class="label label-info">${skill}</span> &nbsp;
                        </c:forEach>
                    <hr>
                    <h3>Job Description</h3>

                    <p>${description}</p>
                </div>


                <sec:authorize access="isAuthenticated()">

                    <c:choose>
                        <c:when test="${bid != null}">
                            <div class="clearfix white-container" id="bidContainer">
                                <h2 class="title">Your Bid Details:</h2>

                                <p>
                                    <b>Amount ($) :</b> ${bid.price} <br/>
                                    <b>Message :</b> ${bid.msg}
                                </p>
                            </div>
                        </c:when>
                        <c:otherwise>

                            <c:choose>
                                <c:when test="${statusString =='OPEN'}">
                                    <div class="clearfix white-container" id="bidContainer">
                                        <h4 class="title">Place a Bid : </h4>

                                        <form class="form form-horizontal" id="bidForm">
                                            <input type="hidden" name="iid" value="${iid}"/>
                                            <input type="hidden" name="bidfid" value=""/>

                                            <div class="form-group">
                                                <label class="col-md-2">Amount ($)</label>

                                                <div class="col-md-8">
                                                    <input class="form-control" id="price" type="text" placeholder="0"
                                                           name="price">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2">Message</label>

                                                <div class="col-md-8">
                                                    <textarea name=msg rows="5" id="msg" cols="80"
                                                              class="form-control"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-offset-2 col-md-8">
                                                    <button type="submit" class="btn btn-default">Submit</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </c:when>
                            </c:choose>

                        </c:otherwise>
                    </c:choose>
                </sec:authorize>
            </div>
            <div class="col-md-4 page-sidebar">
                <div class="widget sidebar-widget white-container ">
                    <div class="row">

                        <div class="col-md-6">
                            <a href="<c:url value='/userprofile.do?name=${interviewee}' />" target="_blank">
                                <img class="img-responsive img-hover" src="<c:url value=''/>${profilepic}"
                                     alt="${interviewee}" style="height:100px;">
                            </a>
                        </div>
                        <div class="col-md-6" style="padding:0px 0px 0px 2px">
                            Posted By<br>
								<span>
									<a target="_blank"
                                       href="<c:url value='/userprofile.do?name=${interviewee}' />">${interviewee}</a>
								</span>
                            <input type="number" class="user-rating hide" value="0"/>
                            <button onclick="contactMe('${interviewee}');" class="btn btn-default"
                                    style='margin:5px 0;'>Contact Me
                            </button>
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

    var IID = '${iid}';
    var interviewee = '${interviewee}';

    $(function () {
        $("#nav_mocks").addClass("active");
        showUserRating();
        $("#bidForm").validate({
            rules: {
                price: {number: true, required: true},
                message: {required: true}
            },
            submitHandler: function (form) {
                placeBid();
                return false;
            }
        });

        $("#date").html(prettyDate(new Date(Number(${dated}))));
    });

    function placeBid() {
        $.ajax({
            type: "GET",
            url: "<c:url value='/makebid.do'/>",
            data: $("#bidForm").serialize(),
        }).done(function (msg) {
            var json = jQuery.parseJSON(msg);
            if (json.success == 1) {
                showSuccess("Your bid placed successfully.");
                var html = '<h2 class="title">Your Bid Details:</h2>' +
                        '<p>' +
                        '<b>Amount ($) :</b> ' + $("#price").val() + ' <br/>' +
                        '<b>Message :</b> ' + $("#msg").val() +
                        '</p>';

                $("#bidContainer").html(html);

            } else {
                showError("Request failed to bid on the Mock.");
            }
            $("#price").val('');
            $("#msg").val('');
        });

        $.ajax({
            type: 'POST',
            url: BASE_URL + 'addcontactlist.do',
            data: "to=" + interviewee
        });
    }

    function showUserRating() {
        $.ajax({
            type: 'GET',
            dataType: 'JSON',
            url: BASE_URL + 'userrating.do?username=${interviewee}',
            success: function (data) {
                $(".user-rating").val(data.rating);
                $(".user-rating").rating({
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

    function contactMe(interviewee) {
        if (LOGIN_USER == null) {
            showLoginBox();
            showInfo("Please login to contact to User.");
            $("#callback").val("showMockMessageBox");
            return;
        }

        showMockMessageBox();
    }

    function showMockMessageBox() {
        console.log("show message box");
    }
</script>
</body>
</html>