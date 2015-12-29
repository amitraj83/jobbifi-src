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
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
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
          if(status == 16){
        	  Map<AttributeType, String> params = new HashMap<AttributeType, String>();
        	  params.put(AttributeType.USER_NAME, username);
        	  params.put(AttributeType.INTERVIEW_INTERVIEWEE, i.getInterviewee());
        	  params.put(AttributeType.INTERVIEW_INTERVIEWEE, i.getInterviewee());
        	  params.put(AttributeType.INTERVIEW_TITLE,i.getTitle());
        	  params.put(AttributeType.INTERVIEW_URL,
        	            (String) data.get("baseURL") + "/interviewdetail.do?iid=" + iid);
        	  params.put(AttributeType.ESCROW_AMOUNT, String.valueOf(amount));
        	  
        	  Services.getInstance().getEmailService().sendMail(Mailer.EmailType.ESCROW_DEPOSITE,
                      params, DataStoreRegistry.getInstance()
                      .getInterviewerDataStore().getUserEmail(username));
        	  
        	  //send mail to interviewee
        	  Map<AttributeType, String> param = new HashMap<AttributeType, String>();
        	  param.put(AttributeType.USER_NAME, i.getInterviewee());
        	  param.put(AttributeType.ADVISOR, username);
        	  param.put(AttributeType.INTERVIEW_TITLE,i.getTitle());
        	  param.put(AttributeType.INTERVIEW_URL,
        	            (String) data.get("baseURL") + "/interviewdetail.do?iid=" + iid);
        	  param.put(AttributeType.ESCROW_AMOUNT, String.valueOf(amount));
        	  
        	  Services.getInstance().getEmailService().sendMail(Mailer.EmailType.NEW_ESCROW,
                      param, DataStoreRegistry.getInstance()
                      .getInterviewerDataStore().getUserEmail(i.getInterviewee()));
          }

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
