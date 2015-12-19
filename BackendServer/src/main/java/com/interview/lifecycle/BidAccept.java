package com.interview.lifecycle;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.BID_STATUS;
import com.interview.framework.DATASTORES;
import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;

public class BidAccept {

  public int acceptBid(ObjectId iid, ObjectId bid_id) throws RemoteException {

    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.PENDING) {

      Bid bid = DataStoreRegistry.getInstance().getBidStore().getBid(bid_id);
      String interviewer = bid.getBidder();
      // assign interview
      // change interview status
      Map<String, Object> changes = new HashMap<String, Object>();
      changes.put(DATASTORES.INTERVIEW.INTERVIEWER, interviewer);
      changes.put(DATASTORES.INTERVIEW.STATUS, INTERVIEW_STATUS.IN_PROGRESS);
      DataStoreRegistry.getInstance().getInterviewDataStore().updateInterview(iid, changes);

      // change bid status
      DataStoreRegistry.getInstance().getBidStore().updateBidStatus(bid_id, BID_STATUS.ACCEPT);

      List<Bid> bids =
          DataStoreRegistry.getInstance().getBidStore().getBidsReceivedForInterview(iid.toString());

      for (Bid b : bids) {
        if (!b.getId().equals(bid_id.toString()))
          DataStoreRegistry.getInstance().getBidStore().updateBidStatus(new ObjectId(b.getId()),
              BID_STATUS.REJECT);
      }

      return RETURN_VALUES.ACCEPT_BID_SUCCESS;
    } else
      return RETURN_VALUES.ACCEPT_BID_FAIL;

  }

}
