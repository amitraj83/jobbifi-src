package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.DataStoreRegistry;

public class SearchAdditionalDataHandler extends RequestHandler {

  public SearchAdditionalDataHandler() {
    addHandler(this, REQUEST_TYPES.SEARCH_ADDITIONAL_DATA);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> users) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      resMap = DataStoreRegistry.getInstance().getInterviewerDataStore().getAdditionalData(users);

    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return resMap;
  }
}
