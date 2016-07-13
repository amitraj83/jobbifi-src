<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Job Seekers Package - Gold</title>

<style type="text/css">
  #navigation.otherview>div.main-nav {
      top: -64px;
  }
  #timeline .timeline:before {
    left: 5%;
  }
  #timeline .timeline>li>.timeline-badge {
    left: 5%; 
  }
  #timeline .timeline>li>.timeline-panel {
    left: -14%;
  }
  #wrapper {
     margin-top: 0px; 
  } 
  #timeline .timeline>li>.timeline-panel {
    width: 85%;
  }

  
.box {
    border-radius: 3px;
    box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    padding: 10px 25px;
    text-align: right;
    display: block;
    margin-top: 60px;
}
.box-icon {
    background-color: #57a544;
    border-radius: 50%;
    display: none;
    height: 75px;
    margin: 0 auto;
    width: 75px;
    margin-top: -61px;
}
.box-icon span {
    color: #fff;
    display: table-cell;
    text-align: center;
    vertical-align: middle;
}
.info h4 {
    font-size: 26px;
    letter-spacing: 2px;
    text-transform: uppercase;
}
.info > p {
    color: #717171;
    font-size: 16px;
    padding-top: 10px;
    text-align: justify;
}
.info > a {
    background-color: #03a9f4;
    border-radius: 2px;
    box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    color: #fff;
    transition: all 0.5s ease 0s;
}
.info > a:hover {
    background-color: #0288d1;
    box-shadow: 0 2px 3px 0 rgba(0, 0, 0, 0.16), 0 2px 5px 0 rgba(0, 0, 0, 0.12);
    color: #fff;
    transition: all 0.5s ease 0s;
} 	
  
</style>
<script>
	window.onload = function() {
		var d = new Date().getTime();
		document.getElementById("tid").value = d;
		document.getElementById("tid2").value = d;
		document.getElementById("orderid1").value = "Gold-"+d;
		document.getElementById("orderid2").value = "Gold-"+d;
	};
</script>

</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>


<div id="carousel-wrapper" style="background-image: url(images/jobscreen.png); background-size: cover;">
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
					<div class="carousel-caption container text-center" >
						<h2 class="text-center">
							WORK WITH AMAZING PEOPLE <br /> <span id="search-form-title-changer">JOIN US AT JOBBIFI</span>&nbsp;
						</h2>
						<br />

					</div>
						<!-- /input-group -->
				</div>
			</div>
		</div>	
</div>
<br>

<div class="container">
	
	<h2>Current Openings at Jobbifi</h2>
	<hr/>
	<div class="row" style="background:white;">
	<div >
            <table class="table " style="font-size: medium;">
                  <thead>
                  <tr>
                      <th>Job Title</th>
                      <th>Department</th>
                      <th>Location</th>
                      <th>Status</th> 
                      <th></th>                                         
                  </tr>
              </thead>   
              <tbody >
                <tr>
                    <td style="vertical-align:middle;">Front End Developer</td>
                    <td style="vertical-align:middle;">Product Development</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Software Engineer(Full Stack)</td>
                    <td style="vertical-align:middle;">Product Development</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Android Developer</td>
                    <td style="vertical-align:middle;">Product Development</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Android Developer</td>
                    <td style="vertical-align:middle;">Product Development</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Work from Home</td>
                    <td style="vertical-align:middle;"><span class="label label-danger">Part Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">UX Designer</td>
                    <td style="vertical-align:middle;">Product Development</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Project Manager</td>
                    <td style="vertical-align:middle;">Product Development</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">SEO Specialist</td>
                    <td style="vertical-align:middle;">Social Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Blog Writer</td>
                    <td style="vertical-align:middle;">Social Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Work From Home</td>
                    <td style="vertical-align:middle;"><span class="label label-danger">Part Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Marketing Campaign Specialist</td>
                    <td style="vertical-align:middle;">Social Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Marketing Media Specialist</td>
                    <td style="vertical-align:middle;">Social Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Data Entry Associate</td>
                    <td style="vertical-align:middle;">Social Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Work From Home</td>
                    <td style="vertical-align:middle;"><span class="label label-danger">Part Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
               <tr>
                    <td style="vertical-align:middle;">LinkedIn Specialist</td>
                    <td style="vertical-align:middle;">Social Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Work From Home</td>
                    <td style="vertical-align:middle;"><span class="label label-danger">Part Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
               	<tr>
                    <td style="vertical-align:middle;">Talent Acquisition Specialist</td>
                    <td style="vertical-align:middle;">Human Resource</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">HR Manager</td>
                    <td style="vertical-align:middle;">Human Resource</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">HR Intern</td>
                    <td style="vertical-align:middle;">Human Resource</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Business Development Executive</td>
                    <td style="vertical-align:middle;">Sales & Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Lead Generation Specialist</td>
                    <td style="vertical-align:middle;">Sales & Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Work From Home</td>
                    <td style="vertical-align:middle;"><span class="label label-danger">Part Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">B2B Marketing Executive</td>
                    <td style="vertical-align:middle;">Sales & Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Bangalore</td>
                    <td style="vertical-align:middle;"><span class="label label-success">Full Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Tele-Marketing Specialist</td>
                    <td style="vertical-align:middle;">Sales & Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Work From Home</td>
                    <td style="vertical-align:middle;"><span class="label label-danger">Part Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                <tr>
                    <td style="vertical-align:middle;">Email-Marketing Specialist</td>
                    <td style="vertical-align:middle;">Sales & Marketing</td>
                    <td style="vertical-align:middle;"><i class="fa fa-map-marker" aria-hidden="true"></i> &nbsp; Work From Home</td>
                    <td style="vertical-align:middle;"><span class="label label-danger">Part Time</span>
                    </td> 
                    <td style="vertical-align:middle;"><button type="button" class="btn btn-link">Apply</button></td>                                      
                </tr>
                
                

              </tbody>
            </table>
    </div>
	</div>
</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>

</body>
</html>
