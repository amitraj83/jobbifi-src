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


<div id="carousel-wrapper" style="background-image: url(images/packages.jpg); background-size: cover;">
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
							OUR SERVICES SUIT EVERYONE<br /> <span id="search-form-title-changer">BUY OUR PACKAGES FOR NEXT STEP IN YOUR CAREER</span>&nbsp;
						</h2>
						<br />

					</div>
						<!-- /input-group -->
				</div>
			</div>
		</div>	
</div>
<div id="wrapper">



<div id="timeline" class="container">
    <div class="page-header">
    
    <div class="row">
    
	<div class="panel panel-default panel-table">
              <div class="panel-heading" style="color: #fff;background-color: #4cae4c;">
                <div class="row">
                  <div class="col col-xs-6">
                    <h1 class="panel-title" style="    margin-top: 4px;font-size: 32px;">Gold Package | <i class="fa fa-inr" aria-hidden="true"></i>&nbsp;3000</span></h1>
                  </div>
                  <div class="col col-xs-6 text-right">
                    
		    <sec:authorize access="!isAuthenticated()">  
		      <button type="button" class="btn btn-primary pull-right" onclick="javascript:showLoginBox();" style="margin-top: 0;">Login to Buy</button>
		      </sec:authorize>
				
		      <sec:authorize access="isAuthenticated()">
		      
		        <form method="post" name="customerData" action="ccavRequestHandler.jsp">
			
				<input type="hidden" name="tid" id="tid" readonly style="display:none"/>
			
			
				<input type="hidden" name="merchant_id" id="merchant_id" value="101257" style="display:none"/> 
			
			
				<input type="hidden" name="order_id" id="orderid1" style="display:none"/>
			
			
				<input type="hidden" name="currency" value="INR" style="display:none" />
			
			
				<input type="hidden" name="amount" value="3000.00" style="display:none" />
			
			
				<input type="hidden" name="redirect_url"
					value="https://www.jobbifi.com/ccavResponseHandler.jsp" style="display:none" />
				
			
			
				<input type="hidden" name="cancel_url"
					value="https://www.jobbifi.com/ccavResponseHandler.jsp" style="display:none" />
				
			
			
				<input type="hidden" name="language" id="language" value="EN" style="display:none" />
			
			
				<input type="hidden" name="billing_name" value="" style="display:none" />
			
			
				<input type="hidden" name="billing_address"
					value="" style="display:none" style="display:none" />
			
			
				<input type="hidden" name="billing_city" value="" style="display:none" />
			
			
				<input type="hidden" name="billing_state" value="" style="display:none" />
			
			
				<input type="hidden" name="billing_zip" value="" style="display:none" />
			
			
				<input type="hidden" name="billing_country" value="India" style="display:none" />
				
			
			
				<input type="hidden" name="billing_tel" value="" style="display:none" />
				
			
			
				<input type="hidden" name="billing_email"
					value="" style="display:none" />
			
			
				<input type="hidden" name="delivery_name" value="" style="display:none" />
				
			
			
				<input type="hidden" name="delivery_address"
					value="" style="display:none" />
			
			
				<input type="hidden" name="delivery_city" value="" style="display:none" />
				
			
			
				<input type="hidden" name="delivery_state" value="" style="display:none" />
				
			
			
				<input type="hidden" name="delivery_zip" value="" style="display:none" />
			
			
				<input type="hidden" name="delivery_country" value="India" style="display:none" />
				
			
			
				<input type="hidden" name="delivery_tel" value="" style="display:none" />
				
			
			
				<input type="hidden" name="merchant_param1"
					value="Jobbifi - Gold Package" style="display:none" />
			
			
				<input type="hidden" name="merchant_param2"
					value="additional Info." style="display:none" />
			
			
				<input type="hidden" name="merchant_param3"
					value="additional Info." style="display:none" />
			
			
				<input type="hidden" name="merchant_param4"
					value="additional Info." style="display:none" />
			
			
				<input type="hidden" name="merchant_param5"
					value="additional Info." style="display:none" />
			
			
				<!-- <select name="integration_type" style="display:none">
						<option value="iframe_normal">iframe_normal</option>
				</select>
							 -->
			
				<input type="hidden" name="promo_code" value=""style="display:none" />
			
			
				<input type="hidden" name="customer_identifier" value=""style="display:none" />
			
			
				<button type="submit" class="btn btn-primary" style="margin-top: 0;">Buy Now</button>
			
				</table>
			</form>
		      
		      
		      </sec:authorize>
		    
		    
                  </div>
                </div>
              </div>
              <div class="panel-body">
                
		<ul class="timeline">
			<li class="timeline-inverted">
			  <div class="timeline-badge warning"><i class="glyphicon glyphicon-credit-card"></i></div>
			  <div class="timeline-panel">
			    <div class="timeline-heading">
			      <h4 class="timeline-title">Resume Writing</h4>
			    </div>
			    <div class="timeline-body">
		    <p>Many people are not found by recruiters because their resume does not reflect the right match with the target job. Our industry experts with more than 10 years of experience will write your resume that highlights your skills, strength and competencies in a way that your resume cannot be ignored by recruiters. </p>
		     <p><i class="fa fa-hand-o-right"></i>&nbsp;Our expert will contact you to understand your skill set.</p>
		     <p><i class="fa fa-hand-o-right"></i>&nbsp;We prepare your resume up to our expectations.</p>
		     <p><i class="fa fa-hand-o-right"></i>&nbsp;We showcase your resume with your skills and strength according to industry standards.</p>
		     
			    </div>
			  </div>
			</li>
			<li class="timeline-inverted">
			  <div class="timeline-badge danger"><i class="glyphicon glyphicon-credit-card"></i></div>
			  <div class="timeline-panel">
			    <div class="timeline-heading">
			      <h4 class="timeline-title">2000 Resume flash</h4>
			    </div>
			    <div class="timeline-body">
				<p>More recruiters see your resume, more chances to get a call back from recruiters. We send your resume to 2000 recruiters in top companies. Once, our network of recruiters receive your resume, your chances of interview call is increased. </p>
				<p>You must have experience in :</p>
				 <p><i class="fa fa-hand-o-right"></i>&nbsp;We send your resume to 1000 recruiters.</p>
				 <p><i class="fa fa-hand-o-right"></i>&nbsp;Our network of recruiters are notified about your skills.</p>
				 <p><i class="fa fa-hand-o-right"></i>&nbsp;We make sure to increase your chances of interaction with recruiters.</p>
				 
			    </div>
			  </div>
			</li>
			<!-- <li class="timeline-inverted">
			  <div class="timeline-badge warning"><i class="glyphicon glyphicon-credit-card"></i></div>
			  <div class="timeline-panel">
			    <div class="timeline-heading">
			      <h4 class="timeline-title">10 Referrals</h4>
			    </div>
			    <div class="timeline-body">
					    <p>Referral is one of the best way to get hired. We will find 10 right professionals in top companies who can forward/refer your resume in their companies. </p>
					     <p><i class="fa fa-hand-o-right"></i>&nbsp;Your resume is referred to the recruiters.</p>
					     <p><i class="fa fa-hand-o-right"></i>&nbsp;Your referer assures the recruiter that you are a suitable candidate for the job.</p>
					     <p><i class="fa fa-hand-o-right"></i>&nbsp;Your referrer may help you to prepare for the job.</p>
					     
			    </div>
			  </div>
			</li> -->
			<li class="timeline-inverted">
			  <div class="timeline-badge danger"><i class="glyphicon glyphicon-credit-card"></i></div>
			  <div class="timeline-panel">
			    <div class="timeline-heading">
			      <h4 class="timeline-title">100 Job applications</h4>
			    </div>
			    <div class="timeline-body">
		    <p>We understand that finding jobs and job applications are tough and time consuming processes. We made it more simpler. Jobbifi can find relevant jobs for you and also apply on your behalf - you dont need to do anything. We can apply almost 100 jobs for you. So, you dont need to invest time in applying jobs. The result would be that you will get recruiters call. We expect that out of 100 jobs that we apply, you will receive at least 50 call backs. Out of 50 call backs, at least 20 interviews will be scheduled.</p>
		     <p><i class="fa fa-hand-o-right"></i>&nbsp;Our experts will show the right for you </p>
		     <p><i class="fa fa-hand-o-right"></i>&nbsp;On your approval, we will apply jobs for you.</p>
		     <p><i class="fa fa-hand-o-right"></i>&nbsp;More job applications, more the chances for you to get a job.</p>
		     
			    </div>
			  </div>
			</li>
			<li class="timeline-inverted">
			  <div class="timeline-badge warning"><i class="glyphicon glyphicon-credit-card"></i></div>
			  <div class="timeline-panel">
			    <div class="timeline-heading">
			      <h4 class="timeline-title">3 Mock Interviews</h4>
			    </div>
			    <div class="timeline-body">
		    <p>The biggest reason of a failed interview is that the candidate does not know what the interviewer is looking into the candidate. Many times a candidate thinks that I answered all question correctly, still the inerviewer reject him/her. It is because you failed to show what the interviewer was looking for. If you have an interview in company X, we will try to find out a company insider from company X or someone who worked in company X to take your mock interview. Your mock interview will help you to focus on the skills that a company is looking into you.</p>
		     <p><i class="fa fa-hand-o-right"></i>&nbsp;3 mock interview sessions</p>
		     <p><i class="fa fa-hand-o-right"></i>&nbsp;We will find your mock interview advisors from your target company.</p>
		     <p><i class="fa fa-hand-o-right"></i>&nbsp;Mock interview is the best way to increase your chances to get hired in your dream company.</p>
		     
			    </div>
			  </div>
			</li>
		    </ul>
            
              </div>
              <div class="panel-footer">
                <div class="row">
                  
		  <div class="pull-right">
		  
				<sec:authorize access="!isAuthenticated()">  
		        	<button type="button" class="btn btn-primary pull-right" onclick="javascript:showLoginBox();" style="margin-top: 0;">Login to Buy</button>
		        </sec:authorize>
				
		        <sec:authorize access="isAuthenticated()">
		      
		        <form method="post" name="customerData" action="ccavRequestHandler.jsp">
			
				<input type="hidden" name="tid" id="tid2" readonly style="display:none"/>
			
			
				<input type="hidden" name="merchant_id" id="merchant_id" value="101257" style="display:none"/> 
			
			
				<input type="hidden" name="order_id" id="orderid2" style="display:none"/>
			
			
				<input type="hidden" name="currency" value="INR" style="display:none" />
			
			
				<input type="hidden" name="amount" value="3000.00" style="display:none" />
			
			
				<input type="hidden" name="redirect_url"
					value="https://www.jobbifi.com/ccavResponseHandler.jsp" style="display:none" />
				
			
			
				<input type="hidden" name="cancel_url"
					value="https://www.jobbifi.com/ccavResponseHandler.jsp" style="display:none" />
				
			
			
				<input type="hidden" name="language" id="language" value="EN" style="display:none" />
			
			
				<input type="hidden" name="billing_name" value="" style="display:none" />
			
			
				<input type="hidden" name="billing_address"
					value="" style="display:none" style="display:none" />
			
			
				<input type="hidden" name="billing_city" value="" style="display:none" />
			
			
				<input type="hidden" name="billing_state" value="" style="display:none" />
			
			
				<input type="hidden" name="billing_zip" value="" style="display:none" />
			
			
				<input type="hidden" name="billing_country" value="India" style="display:none" />
				
			
			
				<input type="hidden" name="billing_tel" value="" style="display:none" />
				
			
			
				<input type="hidden" name="billing_email"
					value="" style="display:none" />
			
			
				<input type="hidden" name="delivery_name" value="" style="display:none" />
				
			
			
				<input type="hidden" name="delivery_address"
					value="" style="display:none" />
			
			
				<input type="hidden" name="delivery_city" value="" style="display:none" />
				
			
			
				<input type="hidden" name="delivery_state" value="" style="display:none" />
				
			
			
				<input type="hidden" name="delivery_zip" value="" style="display:none" />
			
			
				<input type="hidden" name="delivery_country" value="India" style="display:none" />
				
			
			
				<input type="hidden" name="delivery_tel" value="" style="display:none" />
				
			
			
				<input type="hidden" name="merchant_param1"
					value="Jobbifi - Gold Package" style="display:none" />
			
			
				<input type="hidden" name="merchant_param2"
					value="additional Info." style="display:none" />
			
			
				<input type="hidden" name="merchant_param3"
					value="additional Info." style="display:none" />
			
			
				<input type="hidden" name="merchant_param4"
					value="additional Info." style="display:none" />
			
			
				<input type="hidden" name="merchant_param5"
					value="additional Info." style="display:none" />
			
			
				<!-- <select name="integration_type" style="display:none">
						<option value="iframe_normal">iframe_normal</option>
				</select>
							 -->
			
				<input type="hidden" name="promo_code" value=""style="display:none" />
			
			
				<input type="hidden" name="customer_identifier" value=""style="display:none" />
			
			
				<button type="submit" class="btn btn-primary" style="margin-top: 0;">Buy Now</button>
			
				</table>
			</form>
		      
		      
		      </sec:authorize>
		  
		  
		  
		  </div>
		  
                </div>
              </div>
        </div>
	
	<br><br>
	
	<div class="panel panel-success">
	  <div class="panel-heading">
	    <h3 class="panel-title "><center style="font-size: 26px;">Didn't like the above package, try other two packages</center></h3>
	  </div>
	  <div class="panel-body">
	    <div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
		    <div class="box">
			<div class="box-icon">
			    <i class="fa fa-gg" aria-hidden="true"></i>
			</div>
			<div class="info">
			    <h4 class="text-center">Silver</h4>
			    <p>The best package for freshers and to test our services. Try this package to make sure that we only work for you to get you a job. It gives you the best chance to accelerate your job hunt and analyze your strength and weaknesses.</p>
			    <a href="silver.jsp" class="btn"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Checkout</a>
			</div>
		    </div>
		</div>
		
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
		    <div class="box">
			<div class="box-icon">
			    <span class="fa fa-2x fa-css3" ></span>
			</div>
			<div class="info">
			    <h4 class="text-center">Platinum</h4>
			    <p>This package provides you the ultimate service which drastically increases your chances to land in your dream job. You will have unlimited CV iterations, many many CV flashes, many job applications and surprisingly enough mock interview and personal consultations.</p>
			    <a href="platinum.jsp" class="btn"><i class="fa fa-external-link" aria-hidden="true"></i>&nbsp;Checkout</a>
			</div>
		    </div>
		</div>
		</div>
	  </div>
	</div>
    
    
    </div>
    
    
        
		
    </div>
    
</div>


</div>


<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>

</body>
</html>
