package com.interview.request.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Notification;
import com.interview.rmi.DataStoreRegistry;

@Service
public class GetWallHandler extends RequestHandler {

  public GetWallHandler() {
    addHandler(this, REQUEST_TYPES.GET_WALL);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    String user = data.get(USER.USERNAME).toString();
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      List<Notification> list =
          DataStoreRegistry.getInstance().getNotificationStore().getAllLatestNotification(user, 10);

      resMap.put("response", list);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
