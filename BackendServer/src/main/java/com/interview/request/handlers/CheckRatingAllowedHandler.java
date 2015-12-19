package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Rating;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class CheckRatingAllowedHandler extends RequestHandler {

  public CheckRatingAllowedHandler() {
    addHandler(this, REQUEST_TYPES.CHECK_RATING_ALLOWED);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    String ratedBy = data.get(DATASTORES.RATING.RATEDBY).toString();
    String rateFor = data.get("rateFor").toString();
    ObjectId iid = new ObjectId(data.get(DATASTORES.RATING.IID).toString());
    try {
      boolean res = Services.getInstance().getRatingService().isRatingAllowed(ratedBy, (iid));
      if (!res) {
        Rating r = DataStoreRegistry.getInstance().getRatingStore().getRating(ratedBy, iid);
        if (r != null) {
          resMap.put("rate1", r.getRate1());
          resMap.put("rate2", r.getRate2());
          resMap.put("rate3", r.getRate3());
          resMap.put("rate4", r.getRate4());
          resMap.put("message", r.getMessage());
          resMap.put("ratingavailable", true);
        } else
          resMap.put("ratingavailable", false);
      }
      resMap.put("currAvg", DataStoreRegistry.getInstance().getInterviewerDataStore()
          .getUserInfo(rateFor).get(USER.RATING));

      resMap.put("result", res);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return resMap;
  }

}
