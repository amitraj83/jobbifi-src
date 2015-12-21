package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class ReleaseFundsHandler extends RequestHandler {

  public ReleaseFundsHandler() {
    addHandler(this, REQUEST_TYPES.RELEASE_FUNDS);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> res = new HashMap<String, Object>();
    try {
      ObjectId iid = new ObjectId(data.get("iid").toString());
      double amount = new Double(data.get(VARIABLES.AMOUNT).toString());
      String escrowID = data.get(DATASTORES.ESCROW.OBJECT_ID).toString();
      int code = Services.getInstance().getReleaseFundsService().releaseFunds(iid, amount);
      if (code == RETURN_VALUES.RELEASE_FUNDS_SUCCESS) {
        Interview i = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
        double eb = i.getEb();

        int status = i.getStatus();

        double bal = new Double(DataStoreRegistry.getInstance().getInterviewerDataStore()
            .getUserInfo(i.getInterviewee()).get(USER.BALANCE).toString());
        DataStoreRegistry.getInstance().getEscrowDataStore().releaseStatusUpdate(escrowID);
        String message = RETURN_VALUES.getResponseMessage(code);

        res.put("message", message);
        res.put("status", status);
        res.put("eb", eb);
        res.put("code", code);
        res.put("bal", bal);
        Services.getInstance().getNotificationService().processNotification(i,
            VARIABLES.NOTIFICATION.TYPE.FINANCIAL_NOTIFICATION);

        double totalReleasedFunds = DataStoreRegistry.getInstance().getEscrowDataStore()
            .getReleasedEscrowForInterview(i.getId());

        Bid acceptedBid =
            DataStoreRegistry.getInstance().getBidStore().getAcceptedBidForInterview(i.getId());

        Double totalPrice = new Double(acceptedBid.getPrice());
        if (totalReleasedFunds >= totalPrice) {
          DataStoreRegistry.getInstance().getInterviewDataStore()
              .updateInterviewStatus(new ObjectId(i.getId()), INTERVIEW_STATUS.COMPLETED);
          Interview intv =
              DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
          res.put("status", intv.getStatus());
        }
        res.put("totalreleased", totalReleasedFunds);
      } else {
        String message = RETURN_VALUES.getResponseMessage(code);
        res.put("message", message);
      }
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return res;
  }

}
