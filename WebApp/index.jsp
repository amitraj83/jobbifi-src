<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Mock Interviews</title>
    <link href='http://fonts.googleapis.com/css?family=Muli' rel='stylesheet' type='text/css'>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/modern-business.css" rel="stylesheet">
    <link href="libraries/font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet/less" type="text/css" href="css/ohover.less"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/map.js"></script>
    <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/library.js"></script>
    <script type="text/javascript" src='js/strophe.js'></script>
    <script type="text/javascript" src="js/chat.js"></script>
    <script type="text/javascript" src="js/sha1.js"></script>
    <script type="text/javascript" src="js/frontcommon.js"></script>
    <script src="js/less.js" type="text/javascript"></script>

    <script>
        $('.carousel').carousel({
            interval: 5000 //changes the speed
        });

        $(function () {
            $("#searchJobBtn").click(function () {
                var searchKey = $("#searchKey").val();
                if (searchKey == "") {
                    alert("Please enter the search term");
                    return;
                }
                $("#searchJobResult").html('');
                searchJobs(searchKey, 1, 10);
            });

            $("#searchKey").on("keypress", function (e) {
                if (e.which == 13) {
                    var searchKey = $("#searchKey").val();
                    if (searchKey == "") {
                        alert("Please enter the search term");
                        return;
                    }
                    $("#searchJobResult").html('');
                    searchJobs(searchKey, 1, 10);
                    return false;
                }
            });

            searchJobs('""', 1, 5, true);
        });

        function searchJobs(searchKey, currentPage, rows, pageload) {

            var isPageload = pageload || false;
            if (!isPageload) {
                window.top.location = BASE_URL + "jobs.html?searchKey=" + searchKey;
                return false;
            }


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
                        var oneresult =
                                '<li class="list-group-item">' +
                                '<div class="row">' +
                                '<div style="padding-right:4px" class="col-md-2"><div style="margin-bottom:1px;padding:2px;width:60px;" class="well">' +
                                '<center><a target="_blank" href="' + BASE_URL + 'userprofile.html?name=' + jsonDocList[i].interviewer + '">' +
                                '<img alt="" src="' + jsonDocList[i].profilepic + '" class="img-responsive img-hover"></a></center></div></div>' +
                                '<div style="padding-left:1px" class="col-md-6">' +
                                ' <h4 style="margin-top: 2px;"><a target="_blank" href="' + joburl + '">' + jsonDocList[i].title + '</a><br>' +
                                '<small>' + jsonDocList[i].companyname + '</small></h4></div>' +
                                '<div style="margin-top: 10px;" class="col-md-4"><i class="fa fa-map-marker"></i> ' + jsonDocList[i].location + '</div>' +
                                '</div>' +
                                '</li> ';
                        allResultsView = allResultsView + oneresult;
                        resultsFound = true;
                    });
                }

                if (resultsFound != true) {
                    allResultsView = "<li id='noresult' ><div class='alert alert-info'>No Results Found</div><li>";
                } else {
                    var totalRecords = jsonResult.NUM_OF_RESULTS;
                    var totalPages = Math.ceil(totalRecords / PAGE_SIZE);
                    if (totalPages > 1 && totalPages > currentPage) {
                        $("#loadMoreJob").html("<button type='button' onclick='searchJobs("
                                + searchKey + "," + (currentPage + 1) + "," + rows + ");' class='btn btn-info'>+ Load More Jobs</button>");
                    } else {
                        $("#loadMoreJob").hide();
                    }
                }
                $("#searchJobResult").append(allResultsView);
            });
        }
    </script>

    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>

    <style>
        .white {
            color: white;
        }

        .btn-lg {
            font-size: 38px;
            line-height: 1.33;
            border-radius: 6px;
        }

        .box > .icon {
            text-align: center;
            position: relative;
        }

        .box > .icon > .image {
            position: relative;
            z-index: 2;
            margin: auto;
            width: 160px;
            height: 160px;
            border: 7px solid #2ea4ec;
            line-height: 160px;
            border-radius: 50%;
            background: #63B76C;
            vertical-align: middle;
        }

        .box > .icon:hover > .image {
            border: 4px solid white;
        }

        .box > .icon > .image > i {
            font-size: 40px !important;
            color: #fff !important;
        }

        .box > .icon:hover > .image > i {
            color: white !important;
        }

        .d_testimonial {
            background-image: url("images/testmonial_bg.png");
            height: 513px;
            width: 100%;
            padding-top: 10px;
        }

        .box > .icon > .info {
            /* margin-top: -24px;
             background: rgba(0, 0, 0, 0.04);
             border: 1px solid #e0e0e0;*/
            padding: 15px 0 10px 0;
        }

        .box > .icon > .info > h3.title {
            color: #222;
            font-weight: 500;
            font-size: 30px;
            text-transform: uppercase;

        }

        .box > .icon > .info > p {
            color: #666;
            line-height: 1.5em;
            margin: 10px;
            font-size: 19px;
        }

        .box > .icon:hover > .info > h3.title, .box > .icon:hover > .info > p, .box > .icon:hover > .info > .more > a {
            color: #222;
        }

        .box > .icon > .info > .more a {
            color: #222;
            line-height: 12px;
            text-transform: uppercase;
            text-decoration: none;
        }

        .box > .icon:hover > .info > .more > a {
            color: #000;
            padding: 6px 8px;
            border-bottom: 4px solid black;
        }

        .box .space {
            height: 30px;
        }
    </style>

    <style>

        .box-content {
            display: inline-block;
            width: 200px;
        }

        .bottom {
            border-bottom: 1px solid #ccc;
        }

        .right {
            border-right: 1px solid #ccc;
        }

        .greenclass {
            background-color: #5cb85c;
        }

        .login-or {
            position: relative;
            font-size: 18px;
            color: #aaa;
            margin-top: 10px;
            margin-bottom: 10px;
            padding-top: 10px;
            padding-bottom: 10px;
        }

        .span-or {
            display: block;
            position: absolute;
            left: 50%;
            top: -2px;
            margin-left: -25px;
            background-color: #fff;
            width: 50px;
            text-align: center;
        }

        .hr-or {
            background-color: #cdcdcd;
            height: 1px;
            margin-top: 0px !important;
            margin-bottom: 0px !important;
        }

        .carousel {
            margin-bottom: 0;
            padding: 0 40px 30px 40px;
        }

        /* The controlsy */
        .carousel-control {
            left: -12px;
            height: 40px;
            width: 40px;
            background: none repeat scroll 0 0 #222222;
            border: 4px solid #FFFFFF;
            border-radius: 23px 23px 23px 23px;
            margin-top: 40px;
        }

        .carousel-control.right {
            right: -12px;
        }

        /* The indicators */
        .carousel-indicators {
            right: 50%;
            top: auto;
            bottom: -10px;
            margin-right: -19px;
        }

        /* The colour of the indicators */
        .carousel-indicators li {
            background: #cecece;
        }

        .carousel-indicators .active {
            background: #428bca;
        }

        /***********************/

        /****************/
        .btn-2 {
            background: #2aadde;
            color: #fff;
            font-size: 18px;
            padding: 6px 50px;
            font-family: 'Lato', sans-serif;

        }

        .btn-2:hover {
            background: #1d8eb8;
            color: #fff;
        }

        .btn-2:active {
            color: #fff;
        }

        /**************/

        .btn-3 {
            background: #f43a3b;
            color: #fff;
            font-size: 18px;
            padding: 6px 14px;
            font-family: 'Lato', sans-serif;

        }

        .btn-3:hover {
            color: #fff;
            background: #fb1617;
        }

        .btn-3:active {
            color: #fff;
        }

        .d_empoly {
            background-image: url("images/empoly_bg.png");
            height: 620px;
            width: 100%;
            padding-top: 10px;
        }

        /**********font work***********/

        h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6, p {
            font-family: 'Lato', sans-serif !important;
        }

        /********job section head*********/

        .title-lines {
            display: block;
            height: auto;
            overflow: hidden;
            position: relative;
            text-align: center;
            width: 100%;
            margin: 0px 0px;
        }

        .title-lines h4 {
            font-weight: 800;
            font-size: 24px;
        }

        .title-lines h1, .title-lines h2, .title-lines h3, .title-lines h4, .title-lines h5, .title-lines h6 {
            display: inline-block;
            margin-bottom: 16px;
            padding: 0 11px;
            position: relative;
            text-transform: uppercase;
        }

        .title-lines h1:before, .title-lines h1:after, .title-lines h2:before, .title-lines h2:after, .title-lines h3:before, .title-lines h3:after, .title-lines h4:before, .title-lines h4:after, .title-lines h5:before, .title-lines h5:after, .title-lines h6:before, .title-lines h6:after {
            background: url("../img/title-bg.png") repeat scroll 0 0 rgba(0, 0, 0, 0);
            content: "";
            display: block;
            height: 9px;
            margin-top: -3px;
            position: absolute;
            top: 50%;
            vertical-align: middle;
            width: 1000px;
        }

        .title-lines h1:before, .title-lines h2:before, .title-lines h3:before, .title-lines h4:before, .title-lines h5:before, .title-lines h6:before {
            left: 100%;
        }

        .title-lines h1:after, .title-lines h2:after, .title-lines h3:after, .title-lines h4:after, .title-lines h5:after, .title-lines h6:after {
            right: 100%;
        }

    </style>

</head>
<body>

<!-- Page Content -->
<div class="container">

    <div style="padding:8px 7px 0; background:#f4f4f4;">
        <div class="row">
            <div class="col-md-6 col-xs-12">
                <div style="width: 100%;float: left;position:relative;max-height:230px;overflow: hidden; box-shadow: 0px 0px 0px 1px #178ACC; border: 2px solid #ededed; border-style: dashed; margin-bottom: 10px;">
                    <img src="images/register_now.png" style="width: 100%;"/>

                    <!--<div style="position: absolute;bottom: 10px;right: 10px;"><a href="#" class="btn btn btn-info" style="letter-spacing: 0.3px;">Find a Job</a> or <a href="">Post a Job</a></div>-->

                    <!--<div style="position: absolute;bottom: 10px;right: 10px;"><a href="#"><button class="btn_02 btn-2 btn-2a">Register Now</button></a> <br/> or <a href="#" style="text-shadow:1px 2px 3px #999; color:#fff; "> Post a Job</a></div>-->

                    <div style="position: absolute;bottom: 10px;right: 10px;"><a href="#">
                        <button class=" btn btn-2">Find a Job</button>
                    </a> <br/> <span style="float:right; color:#fff;">or <a href="#"
                                                                            style="text-shadow:1px 2px 3px #999; color:#fff; ">
                        Post a Job</a></span></div>

                </div>
            </div>

            <div class="col-md-6 col-xs-12">
                <div style="width: 100%;float: left;position:relative;max-height:230px;overflow: hidden;box-shadow: 0px 0px 0px 1px #178ACC; border: 2px solid #ededed; border-style: dashed;margin-bottom: 10px;">
                    <img src="images/post_job.png" style="width: 100%;"/>

                    <!--<div style="position: absolute;bottom: 10px;right: 10px;"><a href="">Beome an Advisor</a> or <a href="#" class="btn btn-warning" style="letter-spacing: 0.3px;">Find Career Advisor</a></div>-->

                    <!-- <div style="position: absolute;bottom: 10px; left:10px;"> <a href="#"><button class="btn_02 btn-3 btn-3a">Post a Job</button></a><br/> or <a href="#" style="text-shadow: 1px 2px 3px #999; color:#265fff;"> Beome an Advisor</a> </div>-->

                    <div style="position: absolute;bottom: 10px; left:10px;"><a href="#">
                        <button class="btn btn-3">Find Career advisor</button>
                    </a><br/> <span style="color:#000;">or <a href="#"
                                                              style="text-shadow: 1px 2px 3px #999; color:#000;"> Become
                        an Advisor</a></span></div>

                </div>

            </div>
        </div>
    </div>
    <div class="clearfix"></div>


    <div class="well" style="margin-top:15px; box-shadow:none; padding:10px; margin-bottom:11px; background:#f9f8f8;">
        <div class="">
            <div class="row">

                <!---------------------------->
                <div class="col-sm-1" style="padding-right:0px;">

                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria-expanded="true">

                            <span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Jobs</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Advisors</a></li>
                            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">mocks</a></li>
                        </ul>
                    </div>

                </div>

                <!------------------------>
                <div class="col-sm-5" style="padding-right:0px; padding-left:0px;"><input id="searchKey" type="text"
                                                                                          class="form-control"
                                                                                          style=" margin-right:10px;"
                                                                                          placeholder="Search Jobs"/>
                </div>
                <div class="col-sm-1" style="text-align:center; line-height:31px; color:#777; padding:0px;"> in</div>
                <div class="col-sm-3" style="padding-left:0px;"><input id="searchLocation" type="text"
                                                                       class="form-control" style=" margin-right:10px;"
                                                                       placeholder="Location"/></div>

                <!--<select style="width:26%; margin-right:10px;" class="form-control">
                <option>Select category</option>
                <option>Select category</option>
                <option>Select category</option>
                </select>-->
                <div class="col-sm-2" style="text-align:center;">
                  <span class="input-group-btn">
                    <button id="searchJobBtn" class="btn btn-info" style=" border-radius:5px; padding:6px 42px;"
                            type="button">Search
                    </button>
                  </span>
                </div>
            </div>
        </div>
    </div>

    <!--------------------------------------------->


    <!--------------------------------------------->
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-8">
                    <!--<h2><center>Recent Jobs</center></h2>-->

                    <div class="title-lines" style="margin-top:10px;">
                        <h4>Recent Jobs</h4>
                    </div>

                    <ul class="list-group" style="width:100%; " id="searchJobResult">
                    </ul>
                    <center id="loadMoreJob"></center>
                </div>


                <div class="col-md-4">
                    <!--<h2><center>Featured Job</center></h2>-->

                    <div class="title-lines">
                        <h3>Featured Jobs</h3>
                    </div>

                    <ul class="list-group" style="width:100%; ">
                        <li class="list-group-item">
                            <img src="images/logo_sonnet.jpg" alt="Image" style="width:260px;height:100px;">
                            <h4> Design Engineer <br>
                                <small>Sonnet Research Labs</small>
                            </h4>
                            <i class="fa fa-map-marker"></i> San Francisco, USA<br><br>

                            <p>The Sonnet Labs Universal Shopping Experience team (USX) is seeking a uniquely innovative
                                and highly motivated Design Technologist (Prototyper) to bring</p>

                            <p class="pull-right"><a href="#">Show more</a></p>
                            <br>
                        </li>

                    </ul>

                </div>
            </div>
        </div>
    </div>


    <div class="col-md-12">
        <br>
        <hr>
        <br>
    </div>
</div>

<!--------------------->

<!--<div class="col-md-12" style="background: #178ACC;border-radius: 4px;-moz-border-radius: 4px;-webkit-border-radius: 4px;-ms-border-radius: 4px;-o-border-radius: 4px;">
    <center>

        <h2 style="color: #FFFFFF;">What people say about us !<br><small style="color: #FFFFFF;">What employers and candidates say about us for helping in their requirements. </small></h2>

        <div class="row">
            <div class="col-xs-12 col-sm-6 col-lg-4">
                <div class="box">
                    <div class="icon">
                        <div class="image"><img src="http://placehold.it/100x100" alt="Image" style="max-width:100%;vertical-align: top;" class="img-circle"></div>
                        <div class="info">
                            <h3 class="title" style="color:#FFFFFF">Alan</h3>
                            <p style="color:#FFFFFF">
                                <em>I am incredibly pleased with Jobbifi's mock interview service. It really helped me to find the person who can guide me for right direction in my career.</em>
                            </p>

                        </div>
                    </div>
                    <div class="space"></div>
                </div>
            </div>



            <div class="col-xs-12 col-sm-6 col-lg-4">
                <div class="box">
                    <div class="icon">
                        <div class="image" style="border-color: #2e6da4;"><img src="http://placehold.it/100x100" alt="Image" style="max-width:100%;vertical-align: top;" class="img-circle"></div>
                        <div class="info" style="border-color:#2e6da4;">
                            <h3 class="title" style="color:#FFFFFF">Alan</h3>
                            <p style="color:#FFFFFF">
                                <em>I am incredibly pleased with Jobbifi's mock interview service. It really helped me to find the person who can guide me for right direction in my career.</em>
                            </p>

                        </div>
                    </div>
                    <div class="space"></div>
                </div>
            </div>


            <div class="col-xs-12 col-sm-6 col-lg-4">
                <div class="box">
                    <div class="icon">
                        <div class="image"><img src="http://placehold.it/100x100" alt="Image" style="max-width:100%;vertical-align: top;" class="img-circle"></div>
                        <div class="info">
                            <h3 class="title" style="color:#FFFFFF">Alan</h3>
                            <p style="color:#FFFFFF">
                                <em>I am incredibly pleased with Jobbifi's mock interview service. It really helped me to find the person who can guide me for right direction in my career.</em>
                            </p>

                        </div>
                    </div>
                    <div class="space"></div>
                </div>
            </div>

 </div>


    </center>
</div>-->

<div class="d_testimonial">

    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>

        <div class="carousel-inner" role="listbox">
            <div class="item active">

                <div class="col-md-12"
                     style=" border-radius: 4px;-moz-border-radius: 4px;-webkit-border-radius: 4px;-ms-border-radius: 4px;-o-border-radius: 4px;">

                    <div class="container">

                        <center>

                            <h2 style="color: #FFFFFF;">What people say about us !<br>
                                <small style="color: #FFFFFF;">What employers and candidates say about us for helping in
                                    their requirements.
                                </small>
                            </h2>

                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="box">
                                        <div class="icon">
                                            <div class="image"><img src="images/testimonial_01.png" alt="Image"
                                                                    style="max-width:100%;vertical-align: top;"
                                                                    class="img-circle"></div>
                                            <div class="info">

                                                <p style="color:#FFFFFF">
                                                    I am incredibly pleased with Jobbifi's mock interview service. It
                                                    really helped me to find the person who can guide me for right
                                                    direction in my career.
                                                </p>

                                                <h3 class="title" style="color:#FFFFFF">Alan</h3>

                                            </div>
                                        </div>
                                        <div class="space"></div>
                                    </div>
                                </div>


                            </div>


                        </center>
                    </div>
                </div>
            </div>

            <div class="item">

                <div class="col-md-12"
                     style=" border-radius: 4px;-moz-border-radius: 4px;-webkit-border-radius: 4px;-ms-border-radius: 4px;-o-border-radius: 4px;">

                    <div class="container">

                        <center>

                            <h2 style="color: #FFFFFF;">What people say about us !<br>
                                <small style="color: #FFFFFF;">What employers and candidates say about us for helping in
                                    their requirements.
                                </small>
                            </h2>

                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="box">
                                        <div class="icon">
                                            <div class="image"><img src="images/testimonial_01.png" alt="Image"
                                                                    style="max-width:100%;vertical-align: top;"
                                                                    class="img-circle"></div>
                                            <div class="info">

                                                <p style="color:#FFFFFF">
                                                    I am incredibly pleased with Jobbifi's mock interview service. It
                                                    really helped me to find the person who can guide me for right
                                                    direction in my career.
                                                </p>

                                                <h3 class="title" style="color:#FFFFFF">Alan</h3>

                                            </div>
                                        </div>
                                        <div class="space"></div>
                                    </div>
                                </div>


                            </div>


                        </center>
                    </div>
                </div>
            </div>

            <div class="item">

                <div class="col-md-12"
                     style=" border-radius: 4px;-moz-border-radius: 4px;-webkit-border-radius: 4px;-ms-border-radius: 4px;-o-border-radius: 4px;">

                    <div class="container">

                        <center>

                            <h2 style="color: #FFFFFF;">What people say about us !<br>
                                <small style="color: #FFFFFF;">What employers and candidates say about us for helping in
                                    their requirements.
                                </small>
                            </h2>

                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="box">
                                        <div class="icon">
                                            <div class="image"><img src="images/testimonial_01.png" alt="Image"
                                                                    style="max-width:100%;vertical-align: top;"
                                                                    class="img-circle"></div>
                                            <div class="info">

                                                <p style="color:#FFFFFF">
                                                    I am incredibly pleased with Jobbifi's mock interview service. It
                                                    really helped me to find the person who can guide me for right
                                                    direction in my career.
                                                </p>

                                                <h3 class="title" style="color:#FFFFFF">Alan</h3>

                                            </div>
                                        </div>
                                        <div class="space"></div>
                                    </div>
                                </div>


                            </div>


                        </center>
                    </div>
                </div>
            </div>

        </div>


    </div>


</div>
<!---------------------->

<div class="container">

    <!--<div class="col-md-12">
    <br><hr><br>
    </div>-->

    <%--<div class="col-md-12">
        <center>

            <h2>Companies we have helped <br><small>Companies we helped to find their suitable talent. </small></h2>


<div class="row">
<div class="col-md-12">
        <div id="Carousel" class="carousel slide">

        <ol class="carousel-indicators">
            <li data-target="#Carousel" data-slide-to="0" class="active"></li>
            <li data-target="#Carousel" data-slide-to="1"></li>
            <li data-target="#Carousel" data-slide-to="2"></li>
        </ol>

        <!-- Carousel items -->
        <div class="carousel-inner">

        <div class="item active">
            <div class="row">
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
            </div><!--.row-->
        </div><!--.item-->

        <div class="item">
            <div class="row">
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
            </div><!--.row-->
        </div><!--.item-->

        <div class="item">
           <div class="row">
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
              <div class="col-md-3"><a href="#" class="thumbnail"><img src="http://placehold.it/180x100" alt="Image" style="max-width:100%;"></a></div>
            </div><!--.row-->
        </div><!--.item-->

        </div><!--.carousel-inner-->
          <a data-slide="prev" href="#Carousel" class="left carousel-control">&#8592;</a>
          <a data-slide="next" href="#Carousel" class="right carousel-control">&#8594;</a>
        </div><!--.Carousel-->

</div>
</div>

        </center>
    </div>--%>


    <!--
    <div class="col-md-12">
    <br><hr><br>
    </div>
    -->
    <div class="col-md-12" style="">
        <BR>
        <center><h2>FOR JOB SEEKERS</h2></center>
        <BR>
        <!--
        <div class="row">
        <div class="col-md-8">

        <h2>How it works for Employers and candidates</h2>
        <p style="color:gray">

              This website does not provide only job search service rather also allows candidates to connect with the potential employers and experienced person who can guide candidates in progressing their career. Basically, candidates can search jobs and also ask the related persons to conduct a mock interview, career consulation and CV preparation services.

         </p>

         <p style="color:gray">
              For employers, it is two way benefit. They can search the potential candidates for their companies as well as they can prepare candidates to earn for their provided services.


         </p>

        </div>


        <div class="col-md-4">
          <div class="embed-responsive embed-responsive-4by3">
            <iframe class="embed-responsive-item" src="//www.youtube.com/embed/95lhtBc53f8" frameborder="0" allowfullscreen></iframe>

          </div>
        </div>  -->


        <!-- <div style="text-align:center;"><img src="images/candidate_01.png" alt="Image" /></div>     -->


        <div class="block_for_effect">


            <div class="example">
                <!-- From left and right -->
                <div class="row">

                    <div class="row-2" style="margin:1%">
                        <!-- colored -->
                        <div class="item-hover circle effect13 from_left_and_right"><a href="#">
                            <div class="img"><img src="images/jobs/job-2.jpg" alt="img"></div>
                            <div class="info">
                                <div class="info-back">
                                    <h3>Job Search</h3>

                                    <p>Search Jobs and apply.</p>
                                </div>
                            </div>
                        </a></div>
                        <!-- end colored -->
                    </div>

                    <div class="row-2" style="margin:1%">
                        <!-- colored -->
                        <div class="item-hover circle effect13 from_left_and_right"><a href="#">
                            <div class="img"><img src="images/mock-pics/mock-6.jpg" alt="img"></div>
                            <div class="info">
                                <div class="info-back">
                                    <h3>Mock Interviews</h3>

                                    <p>Practice mock interviews to succeed</p>
                                </div>
                            </div>
                        </a></div>
                        <!-- end colored -->
                    </div>

                    <div class="row-2" style="margin:1%">
                        <!-- colored -->
                        <div class="item-hover circle effect13 from_left_and_right"><a href="#">
                            <div class="img"><img src="images/advisor/advisor-1.jpg" alt="img"></div>
                            <div class="info">
                                <div class="info-back">
                                    <h3>Search Advisors</h3>

                                    <p>Find and talk to experienced advisors for career consultation</p>
                                </div>
                            </div>
                        </a></div>
                        <!-- end colored -->
                    </div>

                    <div class="row-2" style="margin:1%">
                        <!-- colored -->
                        <div class="item-hover circle effect13 from_left_and_right"><a href="#">
                            <div class="img"><img src="images/career/career-2.jpg" alt="img"></div>
                            <div class="info">
                                <div class="info-back">
                                    <h3>Career Services</h3>

                                    <p>CV forward, professional networks</p>
                                </div>
                            </div>
                        </a></div>
                        <!-- end colored -->
                    </div>

                </div>
                <!-- end From left and right -->
                <!-- Top to bottom -->


                <!-- end Bottom to top -->
            </div>


        </div>


        <!-- </div> -->


    </div>


    <div class="col-md-12">
        <br>
        <hr>
        <br>
    </div>


    <%--<div class="col-md-12">

    <center>
        <h2>Plans &amp; Pricing <br> <small>With our plans, employers can post and find suitable candidates with suitable price</small></h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h4 class="text-center">FREE Package</h4>
                        </div>
                        <div class="panel-body text-center">
                            <p class="lead">
                                <strong>$0 / month</strong>
                            </p>
                        </div>
                        <ul class="list-group list-group-flush text-center" style="width:230px;">
                            <li class="list-group-item">
                                Unlimited Job Posting
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Unlimited CV Contacts
                                <span class="glyphicon glyphicon-remove pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Mock Fees Waiver
                                <span class="glyphicon glyphicon-remove pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Customized Support
                                <span class="glyphicon glyphicon-remove pull-right"></span>
                            </li>
                        </ul>
                        <div class="panel-footer">
                            <a class="btn btn-sm btn-block btn-info">Purchase</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <h4 class="text-center">Standard commercial package</h4>
                        </div>
                        <div class="panel-body text-center">
                            <p class="lead">
                                <strong>$10 / month</strong>
                            </p>
                        </div>
                        <ul class="list-group list-group-flush text-center"  style="width:230px;">
                            <li class="list-group-item">
                                Unlimited Job Posting
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Unlimited CV Contacts
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Mock Fees Waiver
                                <span class="glyphicon glyphicon-remove pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Customized Support
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                        </ul>
                        <div class="panel-footer">
                            <a class="btn btn-sm btn-block btn-warning">Purchase</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4 class="text-center">Premium commercial package</h4>
                        </div>
                        <div class="panel-body text-center">
                            <p class="lead">
                                <strong>$20 / month</strong>
                            </p>
                        </div>
                        <ul class="list-group list-group-flush text-center"  style="width:230px;">
                            <li class="list-group-item">
                               Unlimited Job Posting
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Unlimited CV Contacts
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Mock Fees Waiver
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Customized Support
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                        </ul>
                        <div class="panel-footer">
                            <a class="btn btn-sm btn-block btn-primary">Purchase</a>
                        </div>
                    </div>
                </div>
            </div>

            </center>
    </div>--%>


    <!--  <div class="col-md-12">
           <div class="well">
              <div class="row">
                  <div class="col-md-8">
                      <p>With our flexible plans, you can post as many jobs as you want. We guarantee you can find the best candidate for your job. Moreover, candidates can also contact you to seek you help in getting the job through your career services.</p>
                  </div>
                  <div class="col-md-4">
                       <a class="btn btn-lg btn-block btn-success"> Post a Job</a>
                  </div>
              </div>
          </div>
      </div>--->

    <!-- <div class="col-md-12">
     <br><hr><br>
     </div>-->

    <%--<div class="col-md-12">

    <center>
        <h2>CV Services Plans &amp; Pricing <br> <small>With our plans, candidates can apply and find suitable professionals for their career plan.</small></h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4 class="text-center">FREE Package</h4>
                        </div>
                        <div class="panel-body text-center">
                            <p class="lead">
                                <strong>$0 / month</strong>
                            </p>
                        </div>
                        <ul class="list-group list-group-flush text-center" style="width:230px;">
                            <li class="list-group-item">
                                Unlimited Job Posting
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Unlimited CV Contacts
                                <span class="glyphicon glyphicon-remove pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Mock Fees Waiver
                                <span class="glyphicon glyphicon-remove pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Customized Support
                                <span class="glyphicon glyphicon-remove pull-right"></span>
                            </li>
                        </ul>
                        <div class="panel-footer">
                            <a class="btn btn-sm btn-block btn-primary">Purchase</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <h4 class="text-center">Student package</h4>
                        </div>
                        <div class="panel-body text-center">
                            <p class="lead">
                                <strong>$10 / month</strong>
                            </p>
                        </div>
                        <ul class="list-group list-group-flush text-center"  style="width:230px;">
                            <li class="list-group-item">
                                Unlimited Job Posting
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Unlimited CV Contacts
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Mock Fees Waiver
                                <span class="glyphicon glyphicon-remove pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Customized Support
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                        </ul>
                        <div class="panel-footer">
                            <a class="btn btn-sm btn-block btn-warning">Purchase</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h4 class="text-center">Premium package</h4>
                        </div>
                        <div class="panel-body text-center">
                            <p class="lead">
                                <strong>$20 / month</strong>
                            </p>
                        </div>
                        <ul class="list-group list-group-flush text-center"  style="width:230px;">
                            <li class="list-group-item">
                               Unlimited Job Posting
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Unlimited CV Contacts
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Mock Fees Waiver
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                            <li class="list-group-item">
                                Customized Support
                                <span class="glyphicon glyphicon-ok pull-right"></span>
                            </li>
                        </ul>
                        <div class="panel-footer">
                            <a class="btn btn-sm btn-block btn-info">Purchase</a>
                        </div>
                    </div>
                </div>
            </div>

            </center>
    </div>--%>

    <!--
                <div class="col-md-12">
                <br><hr><br>
                </div>-->

    <%--<div class="col-md-12">
         <div class="well">
            <div class="row">
                <div class="col-md-8">
                    <p>Candidates, If you are having a problem regarding your career plan or stuck in a job, search a suitable professional as your advisor and ask to take mock interview, CV preparation or career consulation. You can post your requirement as a mock absolutely free.</p>
                </div>
                <div class="col-md-4">
                     <a class="btn btn-lg btn-block btn-info"> Post a Mock</a>
                </div>
            </div>
        </div>
    </div>--%>


    <!-- <div class="col-md-12">
     <br><hr><br>
     </div>-->


    <!--   <div class="col-md-12">
       <center>
           <h2>Jobbifi Site State <br><small>Here we list our site stats and how many people we have helped for their career. </small></h2>
           <div class="row">
             <div class="col-md-2 box-content right"><h4> 289756 <br><small>Jobs posted</small></h4></div>

             <div class="col-md-2 box-content right"><h4> 10789 <br><small>Employers Registered</small></h4></div>
             <div class="col-md-2 box-content right"><h4> 90789 <br><small>Candidates Registered</small></h4></div>
             <div class="col-md-2 box-content right"><h4> 1789 <br><small>Mocks Posted</small></h4></div>
             <div class="col-md-2 box-content "><h4> 551789 <br><small>Members</small></h4></div>
           </div>

<br><br><br><br>
       </center>
       </div>-->


</div>


<div class="d_empoly">
    <div class="container">
        <div style="text-align:center; margin-top:45px;"><img src="images/empoly_01.png" alt="Image"/></div>
    </div>
</div>


</div>
<!-- /.container -->

<!-------------------------------------------read more modal box------------------>
<div class="modal fade bs-example-modal-sm in" id="post_job_read_more" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="">
    <div class="modal-dialog" style="width:400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">�</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title text-center" id="myModalLabel">Post Job</h4>

            </div>
            <div class="modal-body">

                <p class="show_post_job_content text-left"></p>


            </div>
        </div>
    </div>
</div>

<div class="modal fade bs-example-modal-sm in" id="how_work_read_more" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="">
    <div class="modal-dialog" style="width:400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">�</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title text-center" id="myModalLabel">How it works</h4>

            </div>
            <div class="modal-body">

                <p class="show_how_work_content text-left"></p>


            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        var posttext = $('#post_job_content').text();
        $('#post_job_read_more .show_post_job_content').text(posttext);
        var howworktext = $('#how_work_content').text();
        $('#how_work_read_more .show_how_work_content').text(howworktext);

    });


</script>

<!-----------end read more modal box-------------->


</body>

</html>
