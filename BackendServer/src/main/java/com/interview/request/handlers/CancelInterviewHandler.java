package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.RETURN_VALUES;
import com.interview.services.Services;

public class CancelInterviewHandler extends RequestHandler {

  public CancelInterviewHandler() {
    addHandler(this, REQUEST_TYPES.CANCEL_INTERVIEW);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      ObjectId _id = new ObjectId(data.get("_id").toString());

      int code = Services.getInstance().getCancelInterviewService().cancelInterview(_id);
      resMap.put("code", code);
      resMap.put("message", RETURN_VALUES.getResponseMessage(code));
      if (code == RETURN_VALUES.CANCEL_INTERVIEW_SUCCESS) {
        resMap.put("istatus", INTERVIEW_STATUS.CANCELLED);
      }

    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return resMap;
  }

}
