package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import com.interview.framework.FIRST_REQUESTS;
import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.DataStoreRegistry;

public class ProcessFirstRequestHandler extends RequestHandler {

  public ProcessFirstRequestHandler() {
    addHandler(this, REQUEST_TYPES.PROCESS_FIRST_REQUEST);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    // System.out.println("IN Process First Request Handler");
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      int status = DataStoreRegistry.getInstance().getRequestsDataStore().updateStatus(
          data.get(FIRST_REQUESTS.ID).toString(),
          FIRST_REQUESTS.STATUSES.valueOf(data.get(FIRST_REQUESTS.STATUS) + ""));
      resMap.put("result", status);
      resMap.putAll(DataStoreRegistry.getInstance().getRequestsDataStore()
          .getUsersOfRequestId(data.get(FIRST_REQUESTS.ID).toString()));
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
