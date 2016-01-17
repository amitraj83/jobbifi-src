package com.interview.request.handlers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Interviewer;
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service
public class InterviewerRegistrationHandler extends RequestHandler {
  private static final Logger logger = Logger.getLogger(InterviewerRegistrationHandler.class);

  public InterviewerRegistrationHandler() {
    addHandler(this, REQUEST_TYPES.INTERVIEWER_REGISTRATION);
  }

  @Override
  public Map<String, Object> handleRequest(final Map<Object, Object> reqMap) {
    Map<String, Object> map = new HashMap<String, Object>();
    String SUB_REQ = null;
    Object obj = reqMap.get(REQUEST_TYPES.SUB_REQ);
    if(obj!=null){
    	SUB_REQ = (String)reqMap.get(REQUEST_TYPES.SUB_REQ);
    }
    int success_status = -1;
    if(null != SUB_REQ && REQUEST_TYPES.INTERVIEWER_REGISTRATION_SUB_REQ.EMAIL_VERIFICATION.equals(SUB_REQ)){
    	try{
    		
    		String encodedUserName = String.valueOf(reqMap.get("authid"));
    		String authToken = String.valueOf(reqMap.get("authtoken"));
    		
    		byte[] encodedUserNamebytes =
    	            Services.getInstance().getConversionService().hexStringToByteArray(encodedUserName);
	        String username =
	            Services.getInstance().getDecryptionService().decrypt(encodedUserNamebytes, "KJH9876JHDGF8976KJHGJ897");

	        if(DataStoreRegistry.getInstance().getInterviewerDataStore().isUserNameExist(username)){
	        	Map<String, Object> info = DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(username);
	        	String hash = String.valueOf(info.get(USER.EMAILHASH));
	        	if(authToken.equalsIgnoreCase(hash)){
	        		boolean result = DataStoreRegistry.getInstance().getInterviewerDataStore().setActiveAfterEmailVrification(hash);
	        		if(result){
	        			//map.put("response", "1");
	        			success_status = 1;
	        		}else{
	        			success_status = -1;
	        		}
	        	}
	        }
    		
    	}catch (Exception e) {
  	      logger.error("Exception : INTERVIEWER REGISTRATION : ", e);
  	      map.put("response", "-1");
  	      return map;
  	    }
    }else{
	    try {
	      final Interviewer interviewer = (Interviewer) reqMap.get("user");
	      interviewer.setUserSocialNetwork(Interviewer.SOCIALNETWORKS.DIRECT);
	      interviewer.setChatPass(Services.getInstance().getPasswordGenerator().generatePassword());
	      
	      String accountActivationToken = Services.getInstance().getSecurityTokenGenerator().getToken();
	      interviewer.setEmailHash(accountActivationToken);
	      
	      success_status =
	          DataStoreRegistry.getInstance().getInterviewerDataStore().insertInterviewer(interviewer);
	
	      if (success_status == 2) {
	        map.put("response", "2");
	        return map;
	      } else if (success_status == 1) {
	        Map<AttributeType, String> params = new HashMap<AttributeType, String>();
	        params.put(AttributeType.USER_NAME, interviewer.getUsername());
	        params.put(AttributeType.USER_TYPE, "INTERVIEWER");
	        
	        
	        
	        String secretKey = Services.getInstance().getSecurityTokenGenerator().getToken();
	        byte[] encodedUserNamebytes =
	                Services.getInstance().getEncryptionService().encrypt(interviewer.getUsername(), "KJH9876JHDGF8976KJHGJ897");

	        String encodedUserName =
	                Services.getInstance().getConversionService().bytesToHex(encodedUserNamebytes);
	        
	        String verificationURL = "www.jobbifi.com" 
	        		+ "/emailverification.do?authid=" + encodedUserName+"&authtoken="+interviewer.getEmailHash();
	        
	        params.put(AttributeType.EMAIL_VARIFICATION_URL, verificationURL);
	        System.out.println("Activation url for "+interviewer.getUsername()+" : "+verificationURL);
	        Services.getInstance().getEmailService().sendMail(Mailer.EmailType.NEW_REGISTRATION, params,
	            interviewer.getEmail());
	        // Services.getInstance().getEmailService().sendMailChannelOnEvent("1", param, recList,
	        // "mail.resetpasswordlink.subject");
	      }
	    } catch (Exception e) {
	      logger.error("Exception : INTERVIEWER REGISTRATION : ", e);
	      map.put("response", "-1");
	      return map;
	    }
    }
    map.put("response", success_status);
    return map;
  }
}
