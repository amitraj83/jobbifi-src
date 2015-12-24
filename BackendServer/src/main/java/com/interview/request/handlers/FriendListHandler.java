package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.DataStoreRegistry;

public class FriendListHandler extends RequestHandler {

  public FriendListHandler() {
    addHandler(this, REQUEST_TYPES.FRIENDLIST_TO_UPDATE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      String author = (String) data.get("user");

      List<String> friends =
          DataStoreRegistry.getInstance().getRequestsDataStore().getAcceptedFriends(author);

      resMap.put("FL", friends);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
