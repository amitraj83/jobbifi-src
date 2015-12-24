package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Rating;
import com.interview.rmi.DataStoreRegistry;

public class UserInfoHandler extends RequestHandler {

  public UserInfoHandler() {
    addHandler(this, REQUEST_TYPES.USER_INFO);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    if (data.get(REQUEST_TYPES.SUB_REQ) != null
        && data.get(REQUEST_TYPES.SUB_REQ).equals("USERS_PICS")) {
      String[] userslist = (String[]) data.get("userslist");
      for (String username : userslist) {
        if (!username.trim().equals("")) {
          try {
            Map<String, Object> userMap =
                DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(username);
            if (userMap.get(USER.PROFILE_PIC) != null)
              resMap.put(username, userMap.get(USER.PROFILE_PIC).toString());
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        }
      }
    } else if (data.get(REQUEST_TYPES.SUB_REQ) != null
        && data.get(REQUEST_TYPES.SUB_REQ).equals("USER_EXTERNAL_INFO")) {
      String id = (String) data.get(USER.USERNAME);
      try {
        resMap = DataStoreRegistry.getInstance().getInterviewerDataStore().getUserExternalInfo(id);
        resMap.put("avgRating", DataStoreRegistry.getInstance().getRatingStore().getAvgRating(id));
        resMap.put("reviewCount",
            DataStoreRegistry.getInstance().getRatingStore().getReviewsCount(id));
        Map<String, Integer> individualRating = getIndividualRating(id);

        resMap.put("individualratings", individualRating);

      } catch (RemoteException e) {
        e.printStackTrace();
      }
    } else {
      String id = "";
      if (data.get(USER.EMAIL) != null) {
        id = (String) data.get(USER.EMAIL);
      } else if (data.get(USER.USERNAME) != null) {
        id = (String) data.get(USER.USERNAME);
      }

      try {
        resMap = DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(id);
        Map<String, Integer> individualRating = getIndividualRating(id);
        resMap.put("individualratings", individualRating);
        resMap.put("reviewCount", DataStoreRegistry.getInstance().getRatingStore()
            .getReviewsCount(resMap.get(USER.USERNAME).toString()));
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return resMap;
  }

  private Map<String, Integer> getIndividualRating(String id) throws RemoteException {
    List<Rating> allRatings = DataStoreRegistry.getInstance().getRatingStore().getAllRatings(id);
    int rate1 = 0;
    int rate2 = 0;
    int rate3 = 0;
    int rate4 = 0;
    int rate5 = 0;

    for (Rating rating : allRatings) {
      if (rating.getAverage() > 4)
        rate5++;
      else if (rating.getAverage() > 3)
        rate4++;
      else if (rating.getAverage() > 2)
        rate3++;
      else if (rating.getAverage() > 1)
        rate2++;
      else if (rating.getAverage() > 0)
        rate1++;
    }
    Map<String, Integer> individualRating = new HashMap<String, Integer>();
    individualRating.put("rate1", rate1);
    individualRating.put("rate2", rate2);
    individualRating.put("rate3", rate3);
    individualRating.put("rate4", rate4);
    individualRating.put("rate5", rate5);
    return individualRating;
  }
}
