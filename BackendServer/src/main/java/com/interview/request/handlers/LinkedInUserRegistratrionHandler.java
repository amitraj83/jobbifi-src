package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.Interviewer;
import com.interview.rmi.DataStoreRegistry;

@Service
public class LinkedInUserRegistratrionHandler extends RequestHandler {

  public LinkedInUserRegistratrionHandler() {
    addHandler(this, REQUEST_TYPES.LINKEDIN_USER_REG);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    Interviewer interviewer = (Interviewer) data.get("user");
    try {
      interviewer.setUserSocialNetwork(Interviewer.SOCIALNETWORKS.LINKEDIN);
      int result =
          DataStoreRegistry.getInstance().getInterviewerDataStore().insertInterviewer(interviewer);
      resMap.put("result", result);

    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return resMap;
  }
}
