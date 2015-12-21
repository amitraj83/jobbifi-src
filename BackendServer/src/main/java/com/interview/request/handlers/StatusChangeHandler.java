package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.BID_STATUS;
import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.rmi.DataStoreRegistry;

public class StatusChangeHandler extends RequestHandler {

  public StatusChangeHandler() {
    addHandler(this, REQUEST_TYPES.STATUS_CHANGE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      String iid = data.get(VARIABLES.Bid.INTERVIEW_ID).toString();
      String bid = data.get(VARIABLES.Bid.BID_ID).toString();
      int bid_status = new Integer(data.get(VARIABLES.Bid.STATUS).toString());

      if (bid_status == BID_STATUS.PENDING) {
        DataStoreRegistry.getInstance().getBidStore().updateBidStatus(new ObjectId(bid),
            bid_status);
      } else if (bid_status == BID_STATUS.ACCEPT) {
        DataStoreRegistry.getInstance().getBidStore().updateBidStatus(new ObjectId(bid),
            bid_status);
        List<String> bidList =
            DataStoreRegistry.getInstance().getBidStore().getAllBidIDsForInterview(iid);
        bidList.remove(bid);
        for (String bid_id : bidList) {
          if (bid_id != bid)
            DataStoreRegistry.getInstance().getBidStore().updateBidStatus(new ObjectId(bid_id),
                BID_STATUS.REJECT);
        }
        DataStoreRegistry.getInstance().getInterviewDataStore()
            .updateInterviewStatus(new ObjectId(iid), INTERVIEW_STATUS.IN_PROGRESS);
      } else if (bid_status == BID_STATUS.REJECT) {
        DataStoreRegistry.getInstance().getBidStore().updateBidStatus(new ObjectId(bid),
            bid_status);
      }

      resMap.put("status", 1);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
