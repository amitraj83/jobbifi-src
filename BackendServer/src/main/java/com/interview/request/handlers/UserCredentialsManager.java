package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.rmi.DataStoreRegistry;

public class UserCredentialsManager extends RequestHandler {

  public UserCredentialsManager() {
    addHandler(this, REQUEST_TYPES.USER_CREDENTIALS);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    String emailId = (String) data.get(USER.EMAIL);
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      resMap =
          DataStoreRegistry.getInstance().getInterviewerDataStore().getUserCredentials(emailId);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return resMap;
  }
}