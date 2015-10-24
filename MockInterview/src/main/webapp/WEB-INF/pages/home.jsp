<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <title>Jobbifi</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/ohover.css' />">
    <!--CSS-->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/font.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="images/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<div id="carousel-wrapper" style="background-image: url(images/home/home.jpg); background-size: cover;">
    <div id="home-carousel" class="carousel slide" data-ride="carousel">
        <div class="container">
            <div class="carousel-arrows">
                <a class="home-carousel-left" href="#home-carousel" data-slide="prev"><i
                        class="fa fa-angle-left"></i></a>
                <a class="home-carousel-right" href="#home-carousel" data-slide="next"><i class="fa fa-angle-right"></i></a>
            </div>
        </div>
        <div class="carousel-inner">
            <div class="item active">
                <div class="carousel-caption container text-center">
                    <h2 class="text-center">FIND YOUR<br/><span id="search-form-title-changer">DREAM JOB</span>&nbsp;
                    </h2>
                    <br/>

                    <div id="home-search-form" class="text-center">
                        <select id="front-search-dropdown" class="selectpicker" data-width="161px" data-size='auto'>
                            <option selected>Jobs</option>
                            <option>Advisors</option>
                            <option>Mock Interviews</option>
                            <!--<option>Mock interviews</option>-->
                        </select>
                        <input id="front-search-key" type="text" class="form-control" aria-label="Search term"
                               placeholder="Skills, company name or keywords">
                        <button id="front-search-button" class="form-control btn btn-default" type="button">Search
                        </button>
                    </div>
                    <!-- /input-group -->
                </div>
            </div>
        </div>
        <div class="brand-promotion">
            <div class="container">
                <div class="media row">
                    <div class="col-sm-4">
                        <div class="brand-content wow fadeIn" data-wow-duration="700ms" data-wow-delay="300ms">
                            <img class="pull-left img-responsive" src="images/home/brand1.png" alt=""/>

                            <div class="media-body">
                                <h2>Boost your career</h2>

                                <p>Looking for a job change but unsure of how to achieve your goals?</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="brand-content wow fadeIn" data-wow-duration="700ms" data-wow-delay="400ms">
                            <img class="pull-left img-responsive" src="images/home/brand2.png" alt=""/>

                            <div class="media-body">
                                <h2>Conquer that job</h2>

                                <p>Get visibility to top employers across the world</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="brand-content wow fadeIn" data-wow-duration="700ms" data-wow-delay="500ms">
                            <img class="pull-left img-responsive" src="images/home/brand3.png" alt=""/>

                            <div class="media-body">
                                <h2>Stand out</h2>

                                <p>Promote your skills and professional experience</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/#brand-promotion-->
    </div>
    <!--/#home-carousel-->
</div>
<!--/#carousel-wrapper-->
<div id="services" class="padding-top padding-bottom off-white">
    <div class="container">
        <div class="row text-center section-title">
            <div class="col-sm-6 col-sm-offset-3">
                <h3 class="wow fadeInDown" data-wow-duration="700ms" data-wow-delay="300ms">Our Services</h3>
                <hr class="title-border">
                <p class="wow fadeInUp" data-wow-duration="700ms" data-wow-delay="300ms">The Jobbifi ecosystem provides
                    tools to job seekers, advisors and employers to accomplish their goals.</p>
            </div>
        </div>
    </div>
    <div class="portfolio-wrapper wow fadeInUp" data-wow-duration="700ms" data-wow-delay="300ms">
        <ul class="filter text-center">
            <li><a class="active" href="#" data-filter="*">All</a></li>
            <li><a href="#" data-filter=".seekers">Job seekers</a></li>
            <li><a href="#" data-filter=".advisors">Advisors</a></li>
            <li><a href="#" data-filter=".employers">Employers</a></li>
            <!--
    <li><a href="#" data-filter=".web">Web</a></li>
    <li><a href="#" data-filter=".package">Package</a></li>
    -->
        </ul>
        <!--/#portfolio-filter-->
        <ul class="portfolio-items">
            <li class="seekers">
                <div class="portfolio-content" data-target="jobs.do">
                    <img class="img-responsive" src="images/services/jobsearch.jpeg" alt="">

                    <div class="overlay">
                        <a class="folio-detail" href="images/services/js1.jpg" data-gallery="prettyPhoto"><i
                                class="fa fa-camera"></i></a>

                        <h2>Find a Job</h2>

                        <p>Our intelligent search help you reach your career goals and become visible to employers.</p>
                        <a class="folio-link" href="jobs.do"><i class="fa fa-long-arrow-right link-forward"></i></a>
                    </div>
                </div>
            </li>
            <li class="seekers">
                <div class="portfolio-content" data-target="advisors.do">
                    <img class="img-responsive" src="images/services/findadvisor.jpeg" alt="">

                    <div class="overlay">
                        <a class="folio-detail" href="images/portfolio/port-full2.jpg" data-gallery="prettyPhoto"><i
                                class="fa fa-camera"></i></a>

                        <h2>Find an advisor</h2>

                        <p>Network with professionals from top companies with extensive experience in their fields.</p>
                        <a class="folio-link" href="advisors.do"><i class="fa fa-long-arrow-right link-forward"></i></a>
                    </div>
                </div>
            </li>
            <li class="employers">
                <div class="portfolio-content" data-target="post_job.html">
                    <img class="img-responsive" src="images/services/postjob.jpeg" alt="">

                    <div class="overlay">
                        <a class="folio-detail" href="images/portfolio/port-full4.jpg" data-gallery="prettyPhoto"><i
                                class="fa fa-camera"></i></a>

                        <h2>Post jobs</h2>

                        <p>Post your jobs and find candidates suitable for your job opening</p>
                        <a class="folio-link" href="#"><i class="fa fa-long-arrow-right"></i></a>
                    </div>
                </div>
            </li>
            <li class="seekers advisors employers">
                <div class="portfolio-content" data-target="message.html">
                    <img class="img-responsive" src="images/services/chat.jpg" alt="">

                    <div class="overlay">
                        <a class="folio-detail" href="images/portfolio/port-full3.jpg" data-gallery="prettyPhoto"><i
                                class="fa fa-camera"></i></a>

                        <h2>Realtime chat</h2>

                        <p>Job seekers, advisors and employers are encouraged to interact with each other via our
                            realtime chat network.</p>
                        <a class="folio-link" href="#"><i class="fa fa-long-arrow-right"></i></a>
                    </div>
                </div>
            </li>
            <li class="seekers advisors">
                <div class="portfolio-content" data-target="cv.html">
                    <img class="img-responsive" src="images/services/resume.jpeg" alt="">

                    <div class="overlay">
                        <a class="folio-detail" href="images/portfolio/port-full5.jpg" data-gallery="prettyPhoto"><i
                                class="fa fa-camera"></i></a>

                        <h2>Resume services</h2>

                        <p>Get expert advise on building and improving your CV to maximise your chances.</p>
                        <a class="folio-link" href="#"><i class="fa fa-long-arrow-right"></i></a>
                    </div>
                </div>
            </li>
            <li class="employers">
                <div class="portfolio-content" data-target="candidate_search.html">
                    <img class="img-responsive" src="images/services/searchcandidate.jpeg" alt="">

                    <div class="overlay">
                        <a class="folio-detail" href="images/portfolio/port-full6.jpg" data-gallery="prettyPhoto"><i
                                class="fa fa-camera"></i></a>

                        <h2>Candidate search</h2>

                        <p>Our intelligent search and recommendation engine gets you the perfect candidate.</p>
                        <a class="folio-link" href="#"><i class="fa fa-long-arrow-right"></i></a>
                    </div>
                </div>
            </li>
            <li class="advisors">
                <div class="portfolio-content" data-target="mocks.do">
                    <img class="img-responsive" src="images/services/mock.jpg" alt="">

                    <div class="overlay">
                        <a class="folio-detail" href="images/portfolio/port-full7.jpg" data-gallery="prettyPhoto"><i
                                class="fa fa-camera"></i></a>

                        <h2>Find mock interviews</h2>

                        <p>Find and bid to conduct mock interviews matched with your profile</p>
                        <a class="folio-link" href="mocks.do"><i class="fa fa-long-arrow-right link-forward"></i></a>
                    </div>
                </div>
            </li>
            <li class="seekers">
                <div class="portfolio-content" data-target="request_mock.html">
                    <img class="img-responsive" src="images/services/request.jpeg" alt="">

                    <div class="overlay">
                        <a class="folio-detail" href="images/portfolio/port-full8.jpg" data-gallery="prettyPhoto"><i
                                class="fa fa-camera"></i></a>

                        <h2>Request a Mock</h2>

                        <p>Publish your interview requirements and invite our experts to test your skills</p>
                        <a class="folio-link" href="#"><i class="fa fa-long-arrow-right"></i></a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
<!--/#portfolio-->
<div id="promo-one" class="parallax-section">
    <div class="parallax-content">
        <div class="container text-center wow zoomIn" data-wow-duration="700ms" data-wow-delay="300ms">
            <h1>If I hadn't had mentors, I wouldn't be here today. I'm a product of great mentoring, great coaching...
                Coaches or mentors are very important. They could be anyone - your husband, other family members, or
                your boss.</h1>

            <h2>Indra Nooyi <span>CEO, PepsiCo</span></h2>
        </div>
    </div>
</div>
<!--/parallax-section-->
<div id="about-us">
    <div class="container">
        <div class="row text-center section-title">
            <div class="col-sm-8 col-sm-offset-2">
                <h3 class="wow fadeInDown" data-wow-duration="700ms" data-wow-delay="300ms">About Us</h3>
                <hr class="title-border">
                <p class="wow fadeInUp" data-wow-duration="700ms" data-wow-delay="300ms">We are career experts. Our network of job seekers, career advisors and employer helps you to find the right person who can help you to grow in your career path. We connect job seeking candidates with the right career advisor who can help you to prepare for your upcoming interview and do the job referrals. Our intelligent search engine helps employers to find the right candidate for their requirements. </p>
            </div>
        </div>
        <div class="row about-content">
            <div class="col-sm-4 wow zoomIn" data-wow-duration="700ms" data-wow-delay="300ms">
                <h2>Our Vision</h2>

                <p>Our vision is to break the communication gap between fresh job seeking candidates and experienced professionals who potentially can be career advisors or recruiters in the top companies.</p>
            </div>
            <div class="col-sm-4 wow zoomIn" data-wow-duration="700ms" data-wow-delay="400ms">
                <h2>Our History</h2>

                <p>History 
We are backed-up by several talented career advisors who desire to train job seeking candidates for their upcoming dream job interview. </p>
            </div>
            <div class="col-sm-4 wow zoomIn" data-wow-duration="700ms" data-wow-delay="500ms">
                <h2>Our Values</h2>

                <p>We are driven by our core value that a job seeking candidate is a precious talent. We are committed to connect such candidates with the right persons and help them grow in their career.
</p>
            </div>
        </div>
        <!--/about-content
        <div class="our-team padding-bottom wow zoomIn" data-wow-duration="700ms" data-wow-delay="300ms">
            <h2 class="text-center heading">Meet Our Team</h2>

            <div id="team-carousel" class="carousel slide" data-ride="carousel">
                <a class="team-carousel-left" href="#team-carousel" data-slide="prev"><i
                        class="fa fa- fa-chevron-left"></i></a>
                <a class="team-carousel-right" href="#team-carousel" data-slide="next"><i
                        class="fa fa- fa-chevron-right"></i></a>

                <div class="carousel-inner">
                    <div class="item active">
                        <div class="row">
                            <div class="col-sm-3">
                                <div class="team-member">
                                    <div class="member-image">
                                        <img class="img-responsive" src="images/about-us/member1.jpg" alt="Team member">
                                    </div>
                                    <div class="overlay">
                                        <h4>Sayandeep Purkayasth</h4>

                                        <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy
                                            eirmod. Praesent faucibus</p>
                                        <ul class="social-icons">
                                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="team-member">
                                    <div class="member-image">
                                        <img class="img-responsive" src="images/about-us/member2.jpg" alt="Team member">
                                    </div>
                                    <div class="overlay">
                                        <h4>Vivek Saini</h4>

                                        <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy
                                            eirmod. Praesent faucibus</p>
                                        <ul class="social-icons">
                                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="team-member">
                                    <div class="member-image">
                                        <img class="img-responsive" src="images/about-us/member3.jpg" alt="Team member">
                                    </div>
                                    <div class="overlay">
                                        <h4>Pushkar Murkute</h4>

                                        <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy
                                            eirmod. Praesent faucibus</p>
                                        <ul class="social-icons">
                                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="team-member">
                                    <div class="member-image">
                                        <img class="img-responsive" src="images/about-us/member4.jpg" alt="Team member">
                                    </div>
                                    <div class="overlay">
                                        <h4>Manisha Singh</h4>

                                        <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy
                                            eirmod. Praesent faucibus</p>
                                        <ul class="social-icons">
                                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>-->
        <!--/our-team-->
    </div>
</div>
<!--/about-us-->
<div id="clients" class="padding-top padding-bottom">
    <div class="container">
        <div class="row text-center section-title">
            <div class="col-sm-6 col-sm-offset-3">
                <h3 class="wow fadeInDown" data-wow-duration="700ms" data-wow-delay="300ms">Trending Employers</h3>
                <hr class="title-border">
                <p class="wow fadeInUp" data-wow-duration="700ms" data-wow-delay="300ms">Schedule job interviews with top employers and get trained by our career advisors to increase your chances to succeed in that dream job interview.</p>
            </div>
        </div>
        <div id="clients-carousel" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner wow zoomIn" data-wow-duration="700ms" data-wow-delay="300ms">
                <div class="item active">
                    <ul>
                        <li>
                            <a class="img-responsive" href="#"><img src="images/client/client1.png" alt=""/></a>
                        </li>
                        <li>
                            <a class="img-responsive" href="#"><img src="images/client/client2.png" alt=""/></a>
                        </li>
                        <li>
                            <a class="img-responsive" href="#"><img src="images/client/client3.png" alt=""/></a>
                        </li>
                        <li>
                            <a class="img-responsive" href="#"><img src="images/client/client4.png" alt=""/></a>
                        </li>
                    </ul>
                </div>
                <div class="item">
                    <ul>
                        <li>
                            <a class="img-responsive" href="#"><img src="images/client/client1.png" alt=""/></a>
                        </li>
                        <li>
                            <a class="img-responsive" href="#"><img src="images/client/client2.png" alt=""/></a>
                        </li>
                        <li>
                            <a class="img-responsive" href="#"><img src="images/client/client3.png" alt=""/></a>
                        </li>
                        <li>
                            <a class="img-responsive" href="#"><img src="images/client/client4.png" alt=""/></a>
                        </li>
                    </ul>
                </div>
            </div>
            <a class="client-left" href="#clients-carousel" data-slide="prev"><i class="fa fa-angle-left"></i></a>
            <a class="client-right" href="#clients-carousel" data-slide="next"><i class="fa fa-angle-right"></i></a>
        </div>
    </div>
</div>
<!--/#clients-->
<div id="fun-fact" class="text-center parallax-section">
    <div class="parallax-content">
        <div class="container">
            <div class="text-center wow zoomIn" data-wow-duration="700ms" data-wow-delay="300ms">
                <h1>SOME STATS</h1>

                <p>Some important numbers that describe the potential of our resources.</p>
            </div>
            <div class="row funs">
                <div class="col-xs-3 wow zoomIn" data-wow-duration="700ms" data-wow-delay="500ms">
                    <i class="fa fa-group"></i>

                    <h2>Jobs</h2>

                    <h3 class="timer">1245</h3>
                </div>
                <div class="col-xs-3 wow zoomIn" data-wow-duration="700ms" data-wow-delay="700ms">
                    <i class="fa fa-gift"></i>

                    <h2>Candidates </h2>

                    <h3 class="timer">986</h3>
                </div>
                <div class="col-xs-3 wow zoomIn" data-wow-duration="700ms" data-wow-delay="900ms">
                    <i class="fa fa-trophy"></i>

                    <h3>Advisors</h3>

                    <h3 class="timer">23</h3>
                </div>
                <div class="col-xs-3 wow zoomIn" data-wow-duration="700ms" data-wow-delay="900ms">
                    <i class="fa fa-trophy"></i>

                    <h3>Employers</h3>

                    <h3 class="timer">23</h3>
                </div>
            </div>
        </div>
    </div>
</div>
<!--/parallax-section-->
<div id="pricing-tables" class="padding-top padding-bottom">
    <div class="container">
        <div class="row text-center section-title">
            <div class="col-sm-6 col-sm-offset-3">
                <h3 class="wow fadeInDown" data-wow-duration="700ms" data-wow-delay="300ms">Pricing</h3>
                <hr class="title-border">
                <p class="wow fadeInUp" data-wow-duration="700ms" data-wow-delay="300ms">We offer unbeatable packages for the employers to post jobs and to find the right candidate</p>
            </div>
        </div>
        <div class="pricing-table">
            <div class="row">
                <div class="col-sm-3">
                    <div class="single-table wow zoomIn" data-wow-duration="700ms" data-wow-delay="300ms">
                        <h2>Job Post - Basic</h2>

                        <p class="price"><span class="dollar-icon"><i class="fa fa-inr fa-lg" style="vertical-align: -31%;padding-right: 5px;"></i></span><span>0</span> monthly</p>
                        <ul>
                            <li>10 posting</li>
                            <li>Response Management</li>
                            <li>Valid for first 3 months</li>
                            <li>24/7 Support</li>
                        </ul>
                        <a href="#" class="btn-signup">Sign-up</a>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="single-table featured-table wow zoomIn" data-wow-duration="700ms"
                         data-wow-delay="400ms">
                        <h2>Job Post - Full</h2>

                        <p class="price"><span class="dollar-icon"><i class="fa fa-inr fa-lg" style="vertical-align: -31%;padding-right: 5px;"></i></span><span>3500</span> monthly</p>
                        <ul>
                            <li>50 job posting</li>
                            <li>Response Management</li>
                            <li>Hiring Management</li>
                            <li>24/7 Support</li>
                        </ul>
                        <a href="#" class="btn-signup">Sign-up</a>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="single-table wow zoomIn" data-wow-duration="700ms" data-wow-delay="500ms">
                        <h2>CV Search - Basic</h2>

                        <p class="price"><span class="dollar-icon"><i class="fa fa-inr fa-lg" style="vertical-align: -31%;padding-right: 5px;"></i></span><span>0</span> monthly</p>
                        <ul>
                            <li>200 CV Access</li>
                            <li>Free Communication</li>
                            <li>Valid for first 3 months</li>
                            <li>24/7 Support</li>
                        </ul>
                        <a href="#" class="btn-signup">Sign-up</a>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="single-table featured-table  wow zoomIn" data-wow-duration="700ms" data-wow-delay="600ms">
                        <h2>CV Search - Full</h2>

                        <p class="price"><span class="dollar-icon"><i class="fa fa-inr fa-lg" style="vertical-align: -31%;padding-right: 5px;"></i></span><span>4000</span> monthly</p>
                        <ul>
                            <li>Unlimited CV Access</li>
                            <li>Free Communication</li>
                            <li>Hiring Management</li>
                            <li>24/7 Support</li>
                        </ul>
                        <a href="#" class="btn-signup">Sign-up</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--/#pricing-tables-->
<!--
<div id="twitter" class="text-center parallax-section">
<div class="parallax-content">
<div class="container">
    <div class="text-center wow zoomIn" data-wow-duration="700ms" data-wow-delay="300ms">
        <i class="fa fa-twitter"></i>
        <h1>PASSION LEADS TO DESIGN, DESIGN LEADS TO PERFORMANCE, PERFORMANCE LEADS TO SUCCESS!</h1>
        <p>@<a href="#">ThemeRegion</a>- August 13th, 2014</p>
    </div>
</div>
</div>
</div><!--/parallax-section-->
<div id="contact-us" class="padding-top padding-bottom">
    <div class="container">
        <!-- <div class="wow fadeInUp" data-wow-duration="700ms" data-wow-delay="300ms">
            <img class="img-responsive" src="images/monitor.png" alt="">
        </div> -->
        <div class="row text-center section-title">
            <div class="col-sm-6 col-sm-offset-3">
                <h3 class="wow fadeInDown" data-wow-duration="700ms" data-wow-delay="300ms">Contact Us</h3>
                <hr class="title-border">
                <p class="wow fadeInUp" data-wow-duration="700ms" data-wow-delay="300ms"></p>
            </div>
        </div>
        <div class="contact-content">
            <div class="row">
                <div class="col-sm-4 wow zoomIn" data-wow-duration="700ms" data-wow-delay="300ms">
                    <h2>Visit Our Office</h2>
                    <address>
                        <p><i class="fa fa-map-marker"></i> #20,13th Cross,4th Main</p>

                        <p style="padding-left: 20px;">Sampangiramnagar,Near BSNL Office</p>

                        <p style="padding-left: 20px;">Bangalore 5600 027</p>
                    </address>
                </div>
                <div class="col-sm-4 wow zoomIn" data-wow-duration="700ms" data-wow-delay="400ms">
                    <h2>Talk to us</h2>

                    <div class="business-time">
                        <p><i class="fa fa-phone"></i> Phone : +91-9036928424</p>

                        <p><i class="fa fa-envelope"></i> Email : support@jobbifi.com</p>

                        <!-- <p><i class="fa fa-clock-o"></i> Sun. <span>Closed</span></p> -->
                    </div>
                </div>
                <div class="col-sm-4 wow zoomIn" data-wow-duration="700ms" data-wow-delay="500ms">
                    <h2>Have a query ?</h2>

                    <form id="contact-form" class="contact-form" name="contact-form" method="post"
                          action="send-mail.php">
                        <div class="row">
                            <div class="form-group col-sm-6 name-field">
                                <input type="text" name="name" class="form-control" required="required"
                                       placeholder="Name">
                            </div>
                            <div class="form-group col-sm-6 email-field">
                                <input type="email" name="email" class="form-control" required="required"
                                       placeholder="Email Id">
                            </div>
                            <div class="form-group col-sm-12">
                                <textarea name="message" id="message" required="required" class="form-control" rows="8"
                                          placeholder="Your Text"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-default">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!--/#contact-us-->
<div id="gmap">
</div>
<!--/#gmap-->
<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {

    	
    	$(".btn-signup").on("click",function(){
    		$('#myModal').modal('show');
    		$('#loginbox').hide(); 
    		$('#signupbox').show();    		
    	});

        $("#front-search-button").on("click", function () {
            var urlToAppend = '';
            switch ($("#front-search-dropdown").next().find("button").attr("title")) {
                case "Jobs":
                    urlToAppend = 'jobs.do?searchKey=' + $.trim($("#front-search-key").val());
                    break;
                case "Advisors":
                    urlToAppend = 'advisors.do?searchKey=' + $.trim($("#front-search-key").val());
                    break;

                case "Mock Interviews":
                    urlToAppend = 'mocks.do?searchKey=' + $.trim($("#front-search-key").val());
                    break;


            }
            window.location = BASE_URL + urlToAppend;
        })

        $('#services div.portfolio-content').on('click', function (event) {
            console.log("click: " + event.target);
            window.location = BASE_URL + $(this).attr('data-target');
        });

        $("#front-search-key").on("keypress", function (e) {
        	if (e.which == 13) {
        		$( "#front-search-button" ).trigger( "click" );
			}
        });
        
        var items = ["PERFECT CAREER ADVISOR", "NEXT AWESOME RECRUIT", "DREAM JOB"],
                $text = $('#search-form-title-changer'),
                delay = 4; //seconds

        function loop(delay) {
            $.each(items, function (i, elm) {
                $text.delay(delay * 1E3).fadeOut();
                $text.queue(function () {
                    $text.html(items[i]);
                    $text.dequeue();
                });
                $text.fadeIn();
                $text.queue(function () {
                    if (i == items.length - 1) {
                        loop(delay);
                    }
                    $text.dequeue();
                });
            });
        }

        loop(delay);

    });
    //Google Map Customization
    $(function () {

        var map;

        map = new GMaps({
            el: '#gmap',
            lat: 45.995447,
            lng: -73.5697587,
            scrollwheel: false,
            zoom: 10,
            zoomControl: true,
            panControl: false,
            streetViewControl: false,
            mapTypeControl: false,
            overviewMapControl: false,
            clickable: false
        });

        var image = '';
        map.addMarker({
            lat: 45.995447,
            lng: -73.5697587,
            icon: image,
            animation: google.maps.Animation.DROP,
            verticalAlign: 'bottom',
            horizontalAlign: 'center',
            backgroundColor: '#d3cfcf',
        });


        var styles = [

            {
                "featureType": "road",
                "stylers": [{
                    "color": "#005b96"
                }]
            }, {
                "featureType": "water",
                "stylers": [{
                    "color": "#99b3cc"
                }]
            }, {
                "featureType": "landscape",
                "stylers": [{
                    "color": "#ffffff"
                }]
            }, {
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#d3cfcf"
                }]
            }, {
                "featureType": "poi",
                "stylers": [{
                    "color": "#0a446a"
                }]
            }, {
                "elementType": "labels.text",
                "stylers": [{
                    "saturation": 1
                }, {
                    "weight": 0.1
                }, {
                    "color": "#000000"
                }]
            }

        ];

        map.addStyle({
            styledMapName: "Styled Map",
            styles: styles,
            mapTypeId: "map_style"
        });

        map.setStyle("map_style");
    }());
</script>
</body>

</html>
