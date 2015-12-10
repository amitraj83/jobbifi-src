package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.VARIABLES;
import com.interview.services.Services;

public class AwardInterviewHandler extends RequestHandler {

  public AwardInterviewHandler() {
    addHandler(this, REQUEST_TYPES.AWARD_INTERVIEW);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      String iid = data.get("iid").toString();
      String bid_id = data.get("bid").toString();

      int status = Services.getInstance().getBidAcceptService().acceptBid(new ObjectId(iid),
          new ObjectId(bid_id));

      resMap.put("response", RETURN_VALUES.getResponseMessage(status));

      Services.getInstance().getNotificationService().processNotification(iid,
          VARIABLES.NOTIFICATION.TYPE.AWARD_INTERVIEW);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
