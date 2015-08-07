package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.RETURN_VALUES;
import com.interview.services.Services;

public class DeleteInterviewHandler extends RequestHandler {

  public DeleteInterviewHandler() {
    addHandler(this, REQUEST_TYPES.DELETE_INTERVIEW);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    ObjectId iid = new ObjectId(data.get("_id").toString());
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      int code = Services.getInstance().getDeleteInterviewService().deleteInterview(iid);

      resMap.put("code", code);
      resMap.put("message", RETURN_VALUES.getResponseMessage(code));
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
