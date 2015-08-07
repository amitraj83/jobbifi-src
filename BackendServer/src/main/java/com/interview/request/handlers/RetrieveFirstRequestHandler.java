package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.rmi.common.IRequestDataStore;
import com.interview.rmi.DataStoreRegistry;

public class RetrieveFirstRequestHandler extends RequestHandler {

  public RetrieveFirstRequestHandler() {
    addHandler(this, REQUEST_TYPES.RETRIEVE_FIRST_REQUEST);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> firstRequests = null;
    try {
      IRequestDataStore requestDSService = DataStoreRegistry.getInstance().getRequestsDataStore();
      firstRequests =
          requestDSService.retrieveAllFirstRequestsForUsername((String) data.get(USER.USERNAME));
      firstRequests.put(VARIABLES.ACCEPTED_FRIENDS,
          requestDSService.getAcceptedFriends((String) data.get(USER.USERNAME)));
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return firstRequests;
  }

}
