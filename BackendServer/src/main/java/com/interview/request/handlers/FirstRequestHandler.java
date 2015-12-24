package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.framework.rmi.common.IRequestDataStore;
import com.interview.rmi.DataStoreRegistry;

public class FirstRequestHandler extends RequestHandler {

  public FirstRequestHandler() {
    addHandler(this, REQUEST_TYPES.FIRST_REQUEST);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      String interviewerUsername = (String) data.get(VARIABLES.INTERVIEWER);
      String intervieweeUsername = (String) data.get(VARIABLES.INTERVIEWEE);
      IRequestDataStore requestDSService = DataStoreRegistry.getInstance().getRequestsDataStore();
      resMap = requestDSService.registerRequest(interviewerUsername, intervieweeUsername);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
