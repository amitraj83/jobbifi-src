package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.types.ObjectId;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class AwardInterviewHandler extends RequestHandler {

  public AwardInterviewHandler() {
    addHandler(this, REQUEST_TYPES.AWARD_INTERVIEW);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      String iid = data.get("iid").toString();
      String bid_id = data.get("bid").toString();

      int status = Services.getInstance().getBidAcceptService().acceptBid(new ObjectId(iid),
          new ObjectId(bid_id));

      resMap.put("response", RETURN_VALUES.getResponseMessage(status));

      if (status == 12) {
        Bid bid = DataStoreRegistry.getInstance().getBidStore().getBid(new ObjectId(bid_id));
        Interview interview =
            DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(new ObjectId(iid));
        Map<AttributeType, String> params = new HashMap<AttributeType, String>();
        params.put(AttributeType.INTERVIEW_URL,
            (String) data.get("baseURL") + "/interviewdetail.do?iid=" + iid);
        params.put(AttributeType.INTERVIEW_TITLE, interview.getTitle());
        List<String> users =
            DataStoreRegistry.getInstance().getBidStore().getBidderForInterview(iid);
        List<String> receivers = new ArrayList<String>();
        if (users != null) {
          Map<String, String> getEmailListFromUsersList = DataStoreRegistry.getInstance()
              .getInterviewerDataStore().getEmailListFromUsersList(users);
          params.put(AttributeType.BID_PRICE, bid.getPrice());
          for (Entry<String, String> userEmails : getEmailListFromUsersList.entrySet()) {
            if (bid.getBidder().equals(userEmails.getKey())) {
              Services.getInstance().getEmailService().sendMail(
                  Mailer.EmailType.AWARD_INTERVIEW_SUCCESS, params, userEmails.getValue());
              // Services.getInstance().getEmailService().sendMailChannelOnEvent("80",
              // param, new ArrayList<String>(Arrays.asList(obj.getKey())), " Congratulations! You
              // won ! "+interview.getTitle());
            } else {
              receivers.add(userEmails.getValue());
            }
          }
          for (String recipient : receivers) {
            Services.getInstance().getEmailService()
                .sendMail(Mailer.EmailType.AWARD_INTERVIEW_FAILURE, params, recipient);
          }
          // Services.getInstance().getEmailService().sendMailChannelOnEvent("81", params,
          // receivers,
          // "Proposal Ended ! " + interview.getTitle());
        }
      }

      Services.getInstance().getNotificationService().processNotification(iid,
          VARIABLES.NOTIFICATION.TYPE.AWARD_INTERVIEW);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
