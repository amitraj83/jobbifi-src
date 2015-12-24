package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;

@Service
public class AvailableUsersHandler extends RequestHandler {

  public AvailableUsersHandler() {
    addHandler(this, REQUEST_TYPES.AVAILABLE_USERS);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      String username = data.get(USER.USERNAME).toString();

      DataStoreRegistry.getInstance().getInterviewerDataStore().getType(username);
      // As interviewer
      List<Bid> bids = DataStoreRegistry.getInstance().getBidStore().getBidsIPosted(username);
      for (Bid bid : bids) {
        String iid = bid.getIid();
        Interview interview =
            DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
        if (interview != null)
          resMap.put(interview.getInterviewee(), "0");
      }

      // As interviewee
      List<Interview> interviews = DataStoreRegistry.getInstance().getInterviewDataStore()
          .getInterviews(username, DATASTORES.INTERVIEW.INTERVIEWEE);
      for (Interview interview : interviews) {
        List<String> bidIDs = DataStoreRegistry.getInstance().getBidStore()
            .getAllBidIDsForInterview(interview.getId());
        for (String bidid : bidIDs) {
          String bidder =
              DataStoreRegistry.getInstance().getBidStore().getBid(new ObjectId(bidid)).getBidder();
          resMap.put(bidder, "0");
        }

      }
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
