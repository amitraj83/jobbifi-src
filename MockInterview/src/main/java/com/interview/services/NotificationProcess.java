package com.interview.services;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class NotificationProcess {

  /* @Scheduled(fixedDelay = 5000) */
  public void retrieveNotifications() {

    Map<Object, Object> req = new HashMap<Object, Object>();
    req.put("users", Services.getInstance().getUserSessionManager().getOnlineUsers());

    /*
     * Map<String, List<Notification>> res =
     * (Map)Services.getInstance().getRequestHandlerService().handleRequest(req,
     * REQUEST_TYPES.RETRIEVE_NOTIFICATIONS);
     * 
     * Iterator<String> it = res.keySet().iterator(); while(it.hasNext()){ String user = it.next();
     * List<Notification> list = res.get(user); for (Notification notification : list) { String data
     * =
     * Services.getInstance().getJSONUtilityService().getJSONStringOfMap(notification.getContent());
     * List<String> listRecepients = new ArrayList<String>();
     * listRecepients.add(notification.getRecepientUser()); new Update(user, notification.getType(),
     * data , listRecepients); } }
     */



    System.out.println("This is from Scheduler " + new Date());

  }



}
