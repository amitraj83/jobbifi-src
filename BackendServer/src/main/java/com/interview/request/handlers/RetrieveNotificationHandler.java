package com.interview.request.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.DataStoreRegistry;

@Service
public class RetrieveNotificationHandler extends RequestHandler {

  public RetrieveNotificationHandler() {
    addHandler(this, REQUEST_TYPES.RETRIEVE_NOTIFICATIONS);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    List<String> onlineUsers = (List<String>) data.get("users");
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      map =
          (Map) DataStoreRegistry.getInstance().getNotificationStore()
              .getNotifications(onlineUsers);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return map;
  }

}
