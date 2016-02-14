<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Jobbifi</title>
<link rel="stylesheet"
	href="<c:url value='/resources/css/ohover.css' />">
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
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="images/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>
	<%@ include file="/WEB-INF/pages/common/header.jsp"%>
	<div id="carousel-wrapper"
		style="background-image: url(images/home/home-page.jpg); background-size: cover;">
		<div id="home-carousel" class="carousel slide" data-ride="carousel">
			<div class="container">
				<div class="carousel-arrows">
					<a class="home-carousel-left" href="#home-carousel"
						data-slide="prev"><i class="fa fa-angle-left"></i></a> <a
						class="home-carousel-right" href="#home-carousel"
						data-slide="next"><i class="fa fa-angle-right"></i></a>
				</div>
			</div>
			<div class="carousel-inner">
				<div class="item active">
					<div class="carousel-caption container text-center">
						<h2 class="text-center">
							FIND YOUR ADVISOR<br /> <span id="search-form-title-changer">FOR YOUR CV, REFERRALS AND MOCK INTERVIEWS</span>&nbsp;
						</h2>
						<br />

						<div id="home-search-form" class="text-center">
                        <input id="front-search-key" type="text" class="form-control"
								aria-label="Search term" placeholder="Skills, company name or keywords"	>    
                        <!-- <input name="" type="text" class="form-control state-input" placeholder="Location">-->
                         <!--<select id="front-search-dropdown" class="selectpicker"
								data-width="170px" data-size='auto'>
								<option selected>Jobs</option>
								<option>Advisors</option>
								<option>Mock Interviews</option>
                        </select> --> 
                      <!-- <div id="front-search-dropdown" class="wrapper-dropdown-3" tabindex="1">
						<span>Search Type</span>
						<ul class="dropdown">
							<li><a href="#"><i class="icon-envelope icon-large"></i>Jobs</a></li>
							<li><a href="#"><i class="icon-truck icon-large"></i>Advisors</a></li>
							<li><a href="#"><i class="icon-plane icon-large"></i>Mock Interviews</a></li>
						</ul>
						</div> -->
                        <button id="front-search-button"
                            class="form-control btn btn-default" type="button"><i class="fa fa-search"></i> Search
                        </button>
						</div>
						<!-- /input-group -->
					</div>
				</div>
			</div>
			<!-- <div class="brand-promotion">
				<div class="container">
					<div class="media row">
						<div class="col-sm-4">
							<div class="brand-content wow fadeIn" data-wow-duration="700ms"
								data-wow-delay="300ms">
								<img class="pull-left img-responsive"
									src="images/home/brand1.png" alt="" />

								<div class="media-body">
									<h2>Boost your career</h2>

									<p>Looking for a job change but unsure of how to achieve
										your goals?</p>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="brand-content wow fadeIn" data-wow-duration="700ms"
								data-wow-delay="400ms">
								<img class="pull-left img-responsive"
									src="images/home/brand2.png" alt="" />

								<div class="media-body">
									<h2>Conquer that job</h2>

									<p>Get visibility to top employers across the world</p>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="brand-content wow fadeIn" data-wow-duration="700ms"
								data-wow-delay="500ms">
								<img class="pull-left img-responsive"
									src="images/home/brand3.png" alt="" />

								<div class="media-body">
									<h2>Stand out</h2>
									<p>Promote your skills and professional experience</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div> -->
			<!--/#brand-promotion-->
		</div>
		<!--/#home-carousel-->
	</div>
	<!--/#carousel-wrapper-->
	<br />
	<br />
	<div id="candidate-workflow">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="row text-center section-title">
						<div class="col-sm-8 col-sm-offset-2">
							<br /> <br />
							<h3 class="wow fadeInDown" data-wow-duration="700ms"
								data-wow-delay="300ms">How it works for Candidates</h3>
							<p class="wow fadeInUp" data-wow-duration="700ms"
								data-wow-delay="300ms" style="margin-top:15px;">We've made it amazingly simple for
								job seekers to manage their hunt for that dream job.</p>
						</div>
					</div>
					<ul class="timeline">
						<li>
							<div class="timeline-image">
								<img class="img-circle img-responsive"
									src="images/candidate-workflow/job_search.png" alt="">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>Find an Advisor</h4>
									
								</div>
								<div class="timeline-body">
									<p class="text-muted">Giving your career or upcoming interview an
										always needed push has traditionally been an ordeal. With
										Jobbifi's advisor search and recommendation engine, getting in
										touch with a company insider, career guidance professional or even
										advisors in your target company is a breeze.</p>
								</div>
							</div>
							<div class="line"></div>
						</li>
						<li class="timeline-inverted">
							<div class="timeline-image">
								<img class="img-circle img-responsive"
									src="images/candidate-workflow/advisor.png" alt="">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>Avail Advisor Support</h4>
									
								</div>
								<div class="timeline-body">
									<p class="text-muted">Ask your advisor to help you prepare a strong CV, referrals and mock interviews. An advisor is the only person who can guide in a right and targeted way to land in your dream job. Advisors can push your CVs to the right place with right recommendations. </p>
								</div>
							</div>
							<div class="line"></div>
						</li>
						<li>
							<div class="timeline-image">
								<img class="img-circle img-responsive"
									src="images/candidate-workflow/interview.png" alt="">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>Mock Interviews</h4>
									<h4 class="subheading"></h4>
								</div>
								<div class="timeline-body">
									<p class="text-muted">Ask your advisors to take your mock interviews to help you prepare for our next upcoming job interview. Advisors can tell you perfectly what a company is looking into the candidates. How to behave and answer in real job interviews. They can help you to analyze your weaknesses, which you can overcome with right guidance.</p>
								</div>
							</div>
							<div class="line"></div>
						</li>
						<li class="timeline-inverted">
							<div class="timeline-image">
								<img class="img-circle img-responsive"
									src="images/candidate-workflow/hire.png" alt="">
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4>Get hired!</h4>
									<h4 class="subheading"></h4>
								</div>
								<div class="timeline-body">
									<p class="text-muted">With right advices from your advisors, you can perform better in your upcoming job interviews and increase your chances to get hired. </p>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<br />
	<br />
	<br />
	<br />
	<!--/#portfolio-->
	<div id="promo-one" class="parallax-section">
		<div class="parallax-content">
			<div class="container text-center wow zoomIn"
				data-wow-duration="700ms" data-wow-delay="300ms">
				<h1>If I hadn't had mentors, I wouldn't be here today. I'm a
					product of great mentoring, great coaching... Coaches or mentors
					are very important. They could be anyone - your husband, other
					family members, or your boss.</h1>

				<h2>
					Indra Nooyi <span>CEO, PepsiCo</span>
				</h2>
			</div>
		</div>
	</div>
	<!--/parallax-section-->
	<br />
	
	<div id="about-us">
		<div class="container">
			<div class="row text-center section-title">
				<div class="col-sm-8 col-sm-offset-2">
					<h3 class="wow fadeInDown" data-wow-duration="700ms"
						data-wow-delay="300ms">Advisior</h3>
					<p class="wow fadeInUp" data-wow-duration="700ms"
						data-wow-delay="300ms" style="margin-top:15px;">As an advisor, you can contribute to your fellow colleagues and students from your college towards their next career move.</p>
				</div>
			</div>
			<div class="row" style="margin-bottom:80px">
				<div class="col-md-6"><img src="images/advisor/help.jpg" style="width:465px;height:auto;"></div>
				<div class="col-md-6"><h2 style="margin-top:1px">Talk to Job Seekers</h2>

					<p>
						Jobbifi provides a great place where career advisors can help job seeking candidates in the prepration of their upcoming job interviews. Job seeking candidates are sometimes confused and stressed. Advisors' little help will greatly support job seekers to fulfil their dreams. As an advisor, you will feel immense pleasure and proud in yourself after helping such candidate. These candidate may be from the same college where you graduated. In other terms, you are supporting your college as well.
					</p>
				</div>
			</div>
			<div class="row" style="margin-bottom:180px">
				
				<div class="col-md-6"><h2 style="margin-top:1px">Contribute to the society</h2>

					<p>
						It is a chance to help and support the community and society from where you are coming. Today, an advisor is a well established and experienced person in his life. However, he owes to the college, community and other. Just giving an hour or two from your busy life, will be highly helpful to your fellow job seekers.
					</p>
				</div>
				<div class="col-md-6"><img src="images/services/request.jpeg" style="width:465px;height:auto;"></div>
			</div>
			
		</div>
	</div>
	<!--/about-us-->
	
	<!--/#clients-->
	<div id="fun-fact" class="text-center parallax-section">
		<div class="parallax-content">
			<div class="container">
				<div class="text-center wow zoomIn" data-wow-duration="700ms"
					data-wow-delay="300ms">
					<h1>SOME STATS</h1>

					<p>Some important numbers that describe the potential of our
						resources.</p>
				</div>
				<div class="row funs">
					<div class="col-xs-4 wow zoomIn" data-wow-duration="700ms"
						data-wow-delay="500ms">
						<i class="fa fa-group"></i>

						<h2>Jobs</h2>

						<h3 class="timer" id="jobscount">1245</h3>
					</div>
					<div class="col-xs-4 wow zoomIn" data-wow-duration="700ms"
						data-wow-delay="700ms">
						<i class="fa fa-gift"></i>
						<h2>Candidates</h2>
						<h3 class="timer" id="candidatescount">986</h3>
					</div>
					<div class="col-xs-4 wow zoomIn" data-wow-duration="700ms"
						data-wow-delay="900ms">
						<i class="fa fa-trophy"></i>

						<h3>Advisors/Employers</h3>
						<h3 class="timer" id="employerscount">23</h3>
					</div>					
				</div>
			</div>
		</div>
	</div>
	<!--/parallax-section-->
	<!--
	<div id="pricing-tables" class="padding-top padding-bottom white-section">
		<div class="container">
			<div class="row text-center section-title">
				<div class="col-sm-6 col-sm-offset-3">
					<h3 class="wow fadeInDown" data-wow-duration="700ms"
						data-wow-delay="300ms">Employers</h3>
					<p class="wow fadeInUp" data-wow-duration="700ms"
						data-wow-delay="300ms">We offer unbeatable packages for the
						employers to post jobs and to find the right candidate</p>
				</div>
			</div>
			<div class="pricing-table">
				<center>
					<div class="row">
						<div class="col-md-1"></div>
						<div class="col-sm-3">
							<div class="single-table wow zoomIn" data-wow-duration="700ms"
								data-wow-delay="300ms">
								<h2>Silver</h2>

								<p class="price">
									<span>Free</span> &nbsp; monthly
								</p>
								<ul>
									<li>100 CV Access</li>
									<li>10 Job postings</li>
									<li>Hiring Management</li>
									<li>Limited Support</li>
								</ul>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="single-table featured-table wow zoomIn"
								data-wow-duration="700ms" data-wow-delay="400ms">
								<h2>Gold</h2>

								<p class="price">
									<span class="dollar-icon"><i class="fa fa-inr fa-lg"
										style="vertical-align: -31%; padding-right: 5px;"></i></span><span>3000</span>
									monthly
								</p>
								<ul>
									<li>500 CV Access</li>
									<li>50 Job postings</li>
									<li>Hiring Management</li>
									<li>Dedicated Support</li>
								</ul>

							</div>
						</div>
						<div class="col-sm-3">
							<div class="single-table wow zoomIn" data-wow-duration="700ms"
								data-wow-delay="500ms">
								<h2>Platinum</h2>

								<p class="price">
									<span class="dollar-icon"><i class="fa fa-inr fa-lg"
										style="vertical-align: -31%; padding-right: 5px;"></i></span><span>5000</span>
									monthly
								</p>
								<ul>
									<li>Unlimited CV Access</li>
									<li>Unlimited Job postings</li>
									<li>Hiring Management</li>
									<li>Dedicated Support</li>
								</ul>

							</div>
						</div>
						<div class="col-md-1"></div>
					</div>
				</center>
			</div>
		</div>
	</div> -->
    	<div id="contact-us" class="padding-top padding-bottom">
		<div class="container">
			<!-- <div class="wow fadeInUp" data-wow-duration="700ms" data-wow-delay="300ms">
            <img class="img-responsive" src="images/monitor.png" alt="">
        </div> -->
			<div class="row text-center section-title">
				<div class="col-sm-6 col-sm-offset-3">
					<h3 class="wow fadeInDown" data-wow-duration="700ms"
						data-wow-delay="300ms">Contact Us</h3>
					<p class="wow fadeInUp" data-wow-duration="700ms"
						data-wow-delay="300ms"></p>
				</div>
			</div>
			<div class="contact-content">
				<div class="row">
					<div class="col-sm-4 wow zoomIn" data-wow-duration="700ms"
						data-wow-delay="300ms">
						<h2>Visit Our Office</h2>
						<address>
							<p>
								<i class="fa fa-map-marker"></i> #117/346, Block-O
							</p>

							<p style="padding-left: 20px;">Geeta Nagar, Kanpur - 208025</p>

							<p style="padding-left: 20px;">UP, India</p>
						</address>
					</div>
					<div class="col-sm-4 wow zoomIn" data-wow-duration="700ms"
						data-wow-delay="400ms">
						<h2>Talk to us</h2>

						<div class="business-time">
							<p>
								<i class="fa fa-phone"></i> Phone: +91-9036928424
							</p>

							<p>
								<i class="fa fa-envelope"></i> Email: support@jobbifi.com
							</p>

							<!-- <p><i class="fa fa-clock-o"></i> Sun. <span>Closed</span></p> -->
						</div>
					</div>
					<div class="col-sm-4 wow zoomIn" data-wow-duration="700ms"
						data-wow-delay="500ms">
						<h2>Have a query?</h2>

						<form id="contact-form" class="contact-form" name="contact-form"
							method="post" action="/support.do">
							<div class="row">
								<div class="form-group col-sm-6 name-field">
									<input type="text" name="name" class="form-control"
										required="required" placeholder="Name">
								</div>
								<div class="form-group col-sm-6 email-field">
									<input type="email" name="email" class="form-control"
										required="required" placeholder="Email Id">
								</div>
								<div class="form-group col-sm-12">
									<textarea name="message" id="message" required="required"
										class="form-control" rows="8" placeholder="Your Text"></textarea>
								</div>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-default">Submit</button>
							</div>
							<div class="alert alert-success" style="display: none;"
								role="alert" id="contact-form-submitted-alert">Thanks for
								your query. We'll get back to you soon!</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/#contact-us-->   
    
	<div id="gmap"></div>
	<!--/#gmap-->
	<%@ include file="/WEB-INF/pages/common/footer.jsp"%>
	<%@ include file="/WEB-INF/pages/common/js.jsp"%>
	<script type="text/javascript">
	
		$(document).ready(function() {


			$.ajax({
					type : "GET",
					url : "/stats.do",
					data : "",
					success : function(data) {
						var jresponse = jQuery.parseJSON(data);
            			var jobscount = jresponse.jobs;
			            var candidatescount = jresponse.candidates;
			            var employerscount = jresponse.employers;
			            
			            $("#jobscount").html(jobscount);
						$("#candidatescount").html(candidatescount);
						$("#employerscount").html(employerscount);

					}
				});



			$("#contact-form").submit(function(e) {
				var url = "/support.do";
				$.ajax({
					type : "POST",
					url : url,
					data : $("#contact-form").serialize(),
					success : function(data) {
						alert(data);
					}
				});
				e.preventDefault();
				$("#contact-form")[0].reset();
				$("#contact-form-submitted-alert").show();			
			});
		});

		$(document)
				.ready(
						function() {
							$(".btn-signup").on("click", function() {
								$('#myModal').modal('show');
								$('#loginbox').hide();
								$('#signupbox').show();
							});
							$("#front-search-button")
									.on(
											"click",
											function() {
												var urlToAppend = '';
												switch ("Advisors") {
												case "Jobs":
													urlToAppend = 'jobs.do?searchKey='
															+ $
																	.trim($(
																			"#front-search-key")
																			.val());
													break;
												case "Advisors":
													urlToAppend = 'advisors.do?searchKey='
															+ $
																	.trim($(
																			"#front-search-key")
																			.val());
													break;

												case "Mock Interviews":
													urlToAppend = 'mocks.do?searchKey='
															+ $
																	.trim($(
																			"#front-search-key")
																			.val());
													break;
												}
												window.location = BASE_URL
														+ urlToAppend;
											})

							$('#services div.portfolio-content').on(
									'click',
									function(event) {
										console.log("click: " + event.target);
										window.location = BASE_URL
												+ $(this).attr('data-target');
									});

							$("#front-search-key").on("keypress", function(e) {
								if (e.which == 13) {
									$("#front-search-button").trigger("click");
								}
							});

							//var items = [ "PERFECT CAREER ADVISOR",
							//		"NEXT AWESOME RECRUIT", "DREAM JOB" ], $text = $('#search-form-title-changer'), delay = 40000000; //seconds
							var items = [ "FOR YOUR CV, REFERRALS AND MOCK INTERVIEWS"], $text = $('#search-form-title-changer'), delay = 40000000; //seconds

							function loop(delay) {
								$.each(items, function(i, elm) {
									$text.delay(delay * 1E3).fadeOut();
									$text.queue(function() {
										$text.html(items[i]);
										$text.dequeue();
									});
									$text.fadeIn();
									$text.queue(function() {
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
		$(function() {
			var map;
			map = new GMaps({
				el : '#gmap',
				lat : 45.995447,
				lng : -73.5697587,
				scrollwheel : false,
				zoom : 10,
				zoomControl : true,
				panControl : false,
				streetViewControl : false,
				mapTypeControl : false,
				overviewMapControl : false,
				clickable : false
			});

			var image = '';

			map.addMarker({
				lat : 45.995447,
				lng : -73.5697587,
				icon : image,
				animation : google.maps.Animation.DROP,
				verticalAlign : 'bottom',
				horizontalAlign : 'center',
				backgroundColor : '#d3cfcf',
			});

			var styles = [ {
				"featureType" : "road",
				"stylers" : [ {
					"color" : "#005b96"
				} ]
			}, {
				"featureType" : "water",
				"stylers" : [ {
					"color" : "#99b3cc"
				} ]
			}, {
				"featureType" : "landscape",
				"stylers" : [ {
					"color" : "#ffffff"
				} ]
			}, {
				"elementType" : "labels.text.fill",
				"stylers" : [ {
					"color" : "#d3cfcf"
				} ]
			}, {
				"featureType" : "poi",
				"stylers" : [ {
					"color" : "#0a446a"
				} ]
			}, {
				"elementType" : "labels.text",
				"stylers" : [ {
					"saturation" : 1
				}, {
					"weight" : 0.1
				}, {
					"color" : "#000000"
				} ]
			} ];

			map.addStyle({
				styledMapName : "Styled Map",
				styles : styles,
				mapTypeId : "map_style"
			});

			map.setStyle("map_style");
		}());

		function activeHome() {
			$('.navbar-collapse li.scroll').removeClass('active').eq(0)
					.addClass('active');
		}
	</script>
    
    
    		<script type="text/javascript">
			
			function DropDown(el) {
				this.dd = el;
				this.placeholder = this.dd.children('span');
				this.opts = this.dd.find('ul.dropdown > li');
				this.val = '';
				this.index = -1;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;

					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						return false;
					});

					obj.opts.on('click',function(){
						var opt = $(this);
						obj.val = opt.text();
						obj.index = opt.index();
						obj.placeholder.text(obj.val);
					});
				},
				getValue : function() {
					return this.val;
				},
				getIndex : function() {
					return this.index;
				}
			}

			$(function() {

				var dd = new DropDown( $('#front-search-dropdown') );

				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-3').removeClass('active');
				});

			});

		</script>
    
</body>

</html>
