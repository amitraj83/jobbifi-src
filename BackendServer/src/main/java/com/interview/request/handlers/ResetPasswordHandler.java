package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.ResetPasswordEntity;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service
public class ResetPasswordHandler extends RequestHandler {

  @Autowired
  private Properties myProps;
	
  public ResetPasswordHandler() {
    addHandler(this, REQUEST_TYPES.RESET_PASSWORD);
  }

  private static final Logger logger = Logger.getLogger(ResetPasswordHandler.class);
  
  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
    	
    	int subrequest = new Integer(data.get(REQUEST_TYPES.SUB_REQ_RESET_PASSWORD).toString());
    	if (subrequest == 1) {
	        // Email the reset password link
	        String useremail = data.get(USER.EMAIL).toString();	        
	        String username =
	              (String) DataStoreRegistry.getInstance().getInterviewerDataStore()
	                  .getUserInfo(useremail).get(USER.USERNAME);
	        String url = Services.getInstance().getGenerateResetPasswordURLService().generateURL(username, useremail);
	           
	        Map<String, Object> model = new HashMap<String, Object>(); 
            model.put("useremail", useremail);
            model.put("username", username);
            model.put("url", url);
	    	Services.getInstance().getEmailService().sendMail(useremail, 
	    			  myProps.getProperty("mail.resetpasswordlink.subject"), "resetpassword.ftl",model);
	          
	        resMap.put("RESULT", "SUCCESS");  	       	

    	} else if (subrequest == 2) {
	        // validate link and show password reset screen
	        String encodedUserName = data.get(VARIABLES.RESET_PASS.AUTH_ID).toString();
	        String authInstanceObjectId = data.get(VARIABLES.RESET_PASS.AUTH_INSTANCE).toString();
	        String securityToken = data.get(VARIABLES.RESET_PASS.AUTH_TOKEN).toString();
	
	        ResetPasswordEntity entity =
	            DataStoreRegistry.getInstance().getResetPasswordDataStore()
	                .getResetPasswordEntity(new ObjectId(authInstanceObjectId));
	
	        String secretKey = entity.getSecretKey();
	        byte[] encodedUserNamebytes =
	            Services.getInstance().getConversionService().hexStringToByteArray(encodedUserName);
	        String username =
	            Services.getInstance().getDecryptionService().decrypt(encodedUserNamebytes, secretKey);
	
	        long currTime = new Date().getTime();
	        long entryTime = entity.getDt();
	        long diffInSec = (currTime - entryTime) / 1000;
	        if (diffInSec > 3600) {
	        	resMap.put("response", "0");
	        } else {
	          if ((username.equals(entity.getUsername()))
	              && (authInstanceObjectId.equals(entity.getId()))
	              && (securityToken.equals(entity.getSectoken()))) {
	            resMap.put("response", "1");
	          } else
	            resMap.put("response", "2");
	        }
	        
      } else if (subrequest == 3) {
        // Change the password
        String encodedUserName = data.get(VARIABLES.RESET_PASS.AUTH_ID).toString();
        String authInstanceObjectId = data.get(VARIABLES.RESET_PASS.AUTH_INSTANCE).toString();
        String securityToken = data.get(VARIABLES.RESET_PASS.AUTH_TOKEN).toString();
        String encryptedPass = data.get(USER.PASSWORD).toString();

        ResetPasswordEntity entity =
            DataStoreRegistry.getInstance().getResetPasswordDataStore()
                .getResetPasswordEntity(new ObjectId(authInstanceObjectId));

        String secretKey = entity.getSecretKey();
        byte[] encodedUserNamebytes =
            Services.getInstance().getConversionService().hexStringToByteArray(encodedUserName);
        String username =
            Services.getInstance().getDecryptionService().decrypt(encodedUserNamebytes, secretKey);

        long currTime = new Date().getTime();
        long entryTime = entity.getDt();
        long diffInSec = (currTime - entryTime) / 1000;
        if (diffInSec > 3600) {
          resMap.put("status", "0");
        } else {
          if ((username.equals(entity.getUsername()))
              && (authInstanceObjectId.equals(entity.getId()))
              && (securityToken.equals(entity.getSectoken()))) {

            if (DataStoreRegistry.getInstance().getInterviewerDataStore()
                .setPasswordForUserName(username, encryptedPass)) {
              DataStoreRegistry.getInstance().getResetPasswordDataStore()
                  .setExprired(new ObjectId(entity.getId()), true);

              resMap.put("status", "1");
            } else
              resMap.put("status", "2");
          }
        }

      }
    } catch (RemoteException e) {
    	logger.error("Remote Exception : ", e);      
    } catch (Exception e) {
    	logger.error("Exception : ", e);
    }
    return resMap;
  }

}
