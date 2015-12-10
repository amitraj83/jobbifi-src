package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Escrow;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class EscrowHandler extends RequestHandler {

  public EscrowHandler() {
    addHandler(this, REQUEST_TYPES.ESCROW_DEPOSIT);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> res = null;
    ObjectId iid = new ObjectId(data.get("_id").toString());
    if (data.get(REQUEST_TYPES.SUB_REQ).equals("DEPOSIT")) {
      res = new HashMap<String, Object>();
      try {
        double amount = new Double(data.get(DATASTORES.INTERVIEW.ESCROW_BALANCE).toString());
        String username = data.get(USER.USERNAME).toString();
        Interview i = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);

        double totalEscrow = 0;
        List<Escrow> escrowList =
            DataStoreRegistry.getInstance().getEscrowDataStore().getEscrowEntry(i.getId());
        for (Escrow escrow : escrowList) {
          totalEscrow += escrow.getAmount();
        }

        Bid acceptedBid =
            DataStoreRegistry.getInstance().getBidStore().getAcceptedBidForInterview(i.getId());

        Double totalPrice = new Double(acceptedBid.getPrice());
        int status = -1;
        if (totalEscrow + amount <= totalPrice)
          status = Services.getInstance().getEscrowDeposit().escrowDeposit(username, iid, amount);

        if (totalEscrow < totalPrice) {
          i = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
          double eb = i.getEb();

          String message = RETURN_VALUES.getResponseMessage(status);

          double bal = new Double(DataStoreRegistry.getInstance().getInterviewerDataStore()
              .getUserInfo(i.getInterviewee()).get(USER.BALANCE).toString());
          Services.getInstance().getNotificationService().processNotification(i,
              VARIABLES.NOTIFICATION.TYPE.ESCROW_NOTIFICATION);

          res.put("message", message);
          res.put("code", status);
          res.put("eb", eb);
          res.put("status", i.getStatus());
          res.put("bal", bal);
          res.put("escrowlist",
              DataStoreRegistry.getInstance().getEscrowDataStore().getEscrowEntry(iid.toString()));
        } else {
          res.put("message", "Full amount has already been deposited");
        }
      } catch (RemoteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    } else if (data.get(REQUEST_TYPES.SUB_REQ).equals("ESCROWLIST")) {
      res = new HashMap<String, Object>();
      try {
        Interview i = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
        if (data.get(USER.USERNAME).equals(i.getInterviewee())
            || data.get(USER.USERNAME).equals(i.getInterviewer())) {
          res.put("escrowlist",
              DataStoreRegistry.getInstance().getEscrowDataStore().getEscrowEntry(iid.toString()));
        } else
          res.put("errow", "You are not allowed for this operation");
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return res;
  }

}
