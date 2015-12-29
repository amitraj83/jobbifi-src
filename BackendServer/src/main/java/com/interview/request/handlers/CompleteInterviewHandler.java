package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.pojo.Interview;
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class CompleteInterviewHandler extends RequestHandler {

  public CompleteInterviewHandler() {
    addHandler(this, REQUEST_TYPES.COMPLETE_INTERVIEW);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    ObjectId _id = new ObjectId(data.get("_id").toString());
    try {
      int code = Services.getInstance().getCompleteInterviewService().completeInterview(_id);

      resMap.put("code", code);
      resMap.put("message", RETURN_VALUES.getResponseMessage(code));

      if (code == RETURN_VALUES.COMPLETE_INTERVIEW_SUCCESS) {
        resMap.put("status", INTERVIEW_STATUS.COMPLETED);
        Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(_id);
        //interviewee
        Map<AttributeType, String> param = new HashMap<AttributeType, String>();
        param.put(AttributeType.USER_NAME, interview.getInterviewee());
	    param.put(AttributeType.INTERVIEW_TITLE,interview.getTitle());
	    param.put(AttributeType.INTERVIEW_URL,
	    		(String) data.get("baseURL") + "/interviewdetail.do?iid=" + interview.getId());
	  	  
	  	Services.getInstance().getEmailService().sendMail(Mailer.EmailType.MOCK_INTERVIEW_COMPLETED_INTERVIEWEE,
	  			param, DataStoreRegistry.getInstance()
	  			.getInterviewerDataStore().getUserEmail(interview.getInterviewee()));
	  	//interviewer
	  	param.put(AttributeType.USER_NAME, interview.getInterviewer());
	    param.put(AttributeType.INTERVIEW_TITLE,interview.getTitle());
	    param.put(AttributeType.INTERVIEW_URL,
	    		(String) data.get("baseURL") + "/interviewdetail.do?iid=" + interview.getId());
	  	  
	  	Services.getInstance().getEmailService().sendMail(Mailer.EmailType.MOCK_INTERVIEW_COMPLETED_INTERVIEWER,
	  			param, DataStoreRegistry.getInstance()
	  			.getInterviewerDataStore().getUserEmail(interview.getInterviewee()));
      }
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
