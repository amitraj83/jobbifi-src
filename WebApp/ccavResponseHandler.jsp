<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.io.*,java.util.*,com.ccavenue.security.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Response Handler</title>
</head>
<body>
	<%
		String workingKey = "E70DC793E16ED214E4DAC189B85DB7BC";		//32 Bit Alphanumeric Working Key should be entered here so that data can be decrypted.
		String encResp= request.getParameter("encResp");
		AesCryptUtil aesUtil=new AesCryptUtil(workingKey);
		String decResp = aesUtil.decrypt(encResp);
		StringTokenizer tokenizer = new StringTokenizer(decResp, "&");
		Hashtable hs=new Hashtable();
		String pair=null, pname=null, pvalue=null;
		while (tokenizer.hasMoreTokens()) {
			pair = (String)tokenizer.nextToken();
			if(pair!=null) {
				StringTokenizer strTok=new StringTokenizer(pair, "=");
				pname=""; pvalue="";
				if(strTok.hasMoreTokens()) {
					pname=(String)strTok.nextToken();
					if(strTok.hasMoreTokens())
						pvalue=(String)strTok.nextToken();
					hs.put(pname, pvalue);
				}
			}
		}
	%>
	<center>
		<font size="4" color="blue"><b>Response Page</b></font>
				<%
				String status = String.valueOf(hs.get("order_status"));
				if(status == "Success"){
					%> <p>Your order was successful. Please check your email for the order confirmation.</p><br> Continue to <a href="https://www.jobbifi.com">Jobbifi</a><%
				}
				else{
					%> <p>Your order was not successful. Please check your email for the order failure.</p><br> Continue to <a href="https://www.jobbifi.com">Jobbifi</a><%
				}

				%>
				
		
	</center>
</body>
</html>