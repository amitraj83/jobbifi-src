package com.interview.request.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.Interviewer;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class InterviewerRegistrationHandler extends RequestHandler {

  @Autowired
  private Properties myProps;
  
  private static final Logger logger = Logger.getLogger(InterviewerRegistrationHandler.class);

  public InterviewerRegistrationHandler() {
    addHandler(this, REQUEST_TYPES.INTERVIEWER_REGISTRATION);
  }

  @Override
  public Map<String, Object> handleRequest(final Map<Object, Object> reqMap) {
	  
    Map<String, Object> map = new HashMap<String, Object>();    
    int success_status = -1;
    
    try {
    	
    	final Interviewer interviewer = (Interviewer) reqMap.get("user");
    	interviewer.setUserSocialNetwork(Interviewer.SOCIALNETWORKS.DIRECT);
    	interviewer.setChatPass(Services.getInstance().getPasswordGenerator().generatePassword());
    	success_status =
          DataStoreRegistry.getInstance().getInterviewerDataStore().insertInterviewer(interviewer);

    	
	      if(success_status == 2) {
	    	  map.put("response", "2");
	    	  return map;
	        
	      } else {
	    	  // TODO: we do not need the chat functionality
	    	  // remove the code
	    	  /*
	        XMPPConnection connection = new XMPPBOSHConnection(false, "bosh.metajack.im", 5280, "xmpp-httpbind",
	                				"162.243.74.91");
	        connection.connect();
	        connection.login("admin", "amitraj");
	        logger.info("Connected");        
	        AccountManager accountManager = AccountManager.getInstance(connection);
	        accountManager.createAccount(interviewer.getUsername(), interviewer.getChatPass());
	        logger.info("User created : " + interviewer.getUsername() + " / " + interviewer.getChatPass());    	
	    	connection.disconnect();
	    	*/
	    	  	    	  
	    	  
              Map<String, Object> model = new HashMap<String, Object>();
              model.put("username", interviewer.getUsername());              
	    	  Services.getInstance().getEmailService().sendMail(interviewer.getEmail(), 
	    			  myProps.getProperty("mail.register.subject"), "registration.ftl",model);
	    	  
	      }
      
    } catch (Exception e) {
    	logger.error("Exception : INTERVIEWER REGISTRATION : ", e);
    	map.put("response", "-1");
    	return map;
    }
    
    map.put("response", success_status);
    return map;
  }
}