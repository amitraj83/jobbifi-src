package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.Rating;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class RatingHandler extends RequestHandler {

  public RatingHandler() {
    addHandler(this, REQUEST_TYPES.RATING);
  }

  private static final Logger logger = Logger.getLogger(RatingHandler.class);

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();

    try {
      Object subReq = data.get(REQUEST_TYPES.SUB_REQ);

      if (null != subReq && subReq.toString().equals(REQUEST_TYPES.RATING_SUB_REQ.RATE_USER)) {
        String author = data.get("ratedBy").toString();
        int rate1 = new Integer(data.get("1").toString());
        int rate2 = new Integer(data.get("2").toString());
        int rate3 = new Integer(data.get("3").toString());
        int rate4 = new Integer(data.get("4").toString());
        String message = data.get("msg").toString();
        ObjectId iid = new ObjectId(data.get("iid").toString());

        String user = "";
        Interview i = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
        if (i.getInterviewee().equals(author)) {
          user = i.getInterviewer();
        } else {
          user = i.getInterviewee();
        }

        double average = (rate1 + rate2 + rate3 + rate4) / 4;

        Rating rating = new Rating();
        rating.setUsername(user);
        rating.setAverage(average);
        rating.setIid(iid);
        rating.setMessage(message);
        rating.setRate1(rate1);
        rating.setRate2(rate2);
        rating.setRate3(rate3);
        rating.setRate4(rate4);
        rating.setRatedBy(author);
        rating.setTime(new Date().getTime());
        ObjectId rid = Services.getInstance().getRatingService().doRating(iid, user, rating);
        if (rid != null) {
          rating.setId(rid.toString());
          Services.getInstance().getNotificationService().processNotification(rating,
              VARIABLES.NOTIFICATION.TYPE.RATING_NOTIFICATION);

          resMap.put("status",
              RETURN_VALUES.getResponseMessage(RETURN_VALUES.RATING_SAVED_SUCCESS));
          resMap.put("code", RETURN_VALUES.RATING_SAVED_SUCCESS);
        } else {
          resMap.put("status",
              RETURN_VALUES.getResponseMessage(RETURN_VALUES.RATING_SAVED_FAILURE));
          resMap.put("code", RETURN_VALUES.RATING_SAVED_FAILURE);
        }

      } else if (null != subReq
          && subReq.toString().equals(REQUEST_TYPES.RATING_SUB_REQ.GET_USER_RATING)) {
        String username = data.get(USER.USERNAME).toString();
        double rating = DataStoreRegistry.getInstance().getRatingStore().getAvgRating(username);
        resMap.put(USER.RATING, rating);
      }


    } catch (RemoteException e) {
      logger.error("Remote Exception : ", e);
    }
    return resMap;
  }

}
