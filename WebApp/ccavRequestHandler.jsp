<%@ include file="/WEB-INF/pages/common/taglib.jsp" %>
<!DOCTYPE html>
<%@ page import = "java.io.*,java.util.*,com.ccavenue.security.*" %>
<html>
<head>
    <%@ include file="/WEB-INF/pages/common/css.jsp" %>
    <title>Job Seekers Package - Silver</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>	
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
</style>

</head>
<body>
<%@ include file="/WEB-INF/pages/common/header.jsp" %>

<div id="carousel-wrapper" style="background-image: url(images/co-bg.jpg); background-size: cover;">
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
					<div class="carousel-caption container text-center" style="top:100px;">
						<h2 class="text-center">
							YOUR CAREER IS OUR BUSINESS<br /> <span id="search-form-title-changer">JUST ENTER YOUR CARD DETAILS TO BUY SILVER PACKAGE</span>&nbsp;
						</h2>
						<br />

					</div>
						<!-- /input-group -->
				</div>
			</div>
		</div>	
</div>


<div id="wrapper" style="position: absolute;top: 25%;left: 38%;">

	<%
	 String merchantId = request.getParameter("merchant_id");   
	 String accessCode = "AVFR65DF66AW11RFWA";	// Put in the Access Code provided by CCAVENUES
	 String workingKey = "E70DC793E16ED214E4DAC189B85DB7BC";    // Put in the Working Key provided by CCAVENUES								 
	                                                            
	 Enumeration enumeration=request.getParameterNames();
	 String ccaRequest="", pname="", pvalue="";
	 while(enumeration.hasMoreElements()) {
	      pname = ""+enumeration.nextElement();
	      pvalue = request.getParameter(pname);
	      ccaRequest = ccaRequest + pname + "=" + pvalue + "&";
	 }
	 AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
	 String encRequest = aesUtil.encrypt(ccaRequest);
	%>
	<center>
		<br><br>
      	<!-- width required mininmum 482px -->
       	<iframe  width="482" height="500" scrolling="No" frameborder="0"  id="paymentFrame" src="https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction&merchant_id=<%= merchantId %>&encRequest=<%=  encRequest %>&access_code=<%= accessCode %>">
	  	</iframe>
	</center>
</div>	
	<script type="text/javascript">
    	$(document).ready(function(){
    		$('iframe#paymentFrame').load(function() {
				 window.addEventListener('message', function(e) {
			    	 $("#paymentFrame").css("height",e.data['newHeight']+'px'); 	 
			 	 }, false);
			 }); 
    	});
	</script>
<br><br><br><br>
<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<%@ include file="/WEB-INF/pages/common/js.jsp" %>
<script type="text/javascript">

	window.onload = function() {
		var d = new Date().getTime();
		document.getElementById("tid").value = d;
	};



</script>
</body>
</html>
