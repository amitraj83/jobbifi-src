package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.types.ObjectId;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.VARIABLES;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class DeleteInterviewHandler extends RequestHandler {

  public DeleteInterviewHandler() {
    addHandler(this, REQUEST_TYPES.DELETE_INTERVIEW);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    ObjectId iid = new ObjectId(data.get("_id").toString());
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      int code = Services.getInstance().getDeleteInterviewService().deleteInterview(iid);

      resMap.put("code", code);
      resMap.put("message", RETURN_VALUES.getResponseMessage(code));
      
      if(code == 0){
    	  Map<String, String> param = new HashMap<String, String>(); 
          //param.put("username", (String) data.get(VARIABLES.POST_INTERVIEW.INTERVIEWEE));
     	 	param.put("url", (String) data.get("baseURL")+"/interviewdetail.do?iid="+(String)data.get(VARIABLES.IID));
     	 	param.put("companylogo", "");
     	 List<String> users = DataStoreRegistry.getInstance().getBidStore().getBidderForInterview((String)data.get(VARIABLES.IID));
     	 List<String> receivers = new ArrayList<String>();
     	 if(users != null){
     		 Map<String,String> getEmailListFromUsersList =  DataStoreRegistry.getInstance().getInterviewerDataStore().getEmailListFromUsersList(users);
     		 for (Entry<String, String> obj : getEmailListFromUsersList.entrySet()) {
     			 receivers.add(obj.getValue());
 			}
     		 
     		 Services.getInstance().getEmailService().sendMailChannelOnEvent("51", 
     				 param, receivers, "A mock interview you applied to was closed ! "+(String) data.get(VARIABLES.POST_INTERVIEW.TITLE));
     		 
     	 }
      }
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
