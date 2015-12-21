package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.DisposableBean;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class BidHandler extends RequestHandler implements DisposableBean {
  private static final Logger logger = Logger.getLogger(BidHandler.class);

  public BidHandler() {
    addHandler(this, REQUEST_TYPES.BID_REQ);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();

    Object subReq = data.get(REQUEST_TYPES.SUB_REQ);
    if (null != subReq && subReq.toString().equals(REQUEST_TYPES.BID_SUB_REQ.MAKE_BID)) {

      try {
        Bid bid = new Bid();
        bid.setBidder((String) data.get(VARIABLES.Bid.BIDDER));
        bid.setIid((String) data.get(VARIABLES.Bid.INTERVIEW_ID));
        bid.setMsg((String) data.get(VARIABLES.Bid.MSG));
        bid.setPrice((String) data.get(VARIABLES.Bid.PRICE));
        bid.setDate(new Date().getTime());
        bid.setStatus(0);
        bid.setAttachmentID(data.get(VARIABLES.Bid.BID_FID).toString());

        ObjectId id = DataStoreRegistry.getInstance().getBidStore().saveBid(bid);
        if (id != null) {
          resMap.put("success", 1);
          resMap.put(VARIABLES.Bid.BID_ID, id.toString());
          resMap.put(VARIABLES.Bid.BIDDER, bid.getBidder());
          resMap.put(VARIABLES.Bid.INTERVIEW_ID, bid.getIid());
          resMap.put(VARIABLES.Bid.MSG, bid.getMsg());
          resMap.put(VARIABLES.Bid.PRICE, bid.getPrice());
          resMap.put(VARIABLES.Bid.STATUS, bid.getStatus());
          resMap.put(VARIABLES.Bid.DATE, bid.getDate());
          bid.setId(id.toString());

          Services.getInstance().getNotificationService().processNotification(bid,
              VARIABLES.NOTIFICATION.TYPE.NEW_BID_NOTIFICATION);
          Map<AttributeType, String> model = new HashMap<AttributeType, String>();
          model.put(AttributeType.BID_BIDDER, bid.getBidder());
          model.put(AttributeType.BID_MESSAGE, bid.getMsg());
          model.put(AttributeType.BID_PRICE, bid.getPrice());

          DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
          model.put(AttributeType.BID_DATE, dateFormat.format(new Date(bid.getDate())));
          Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore()
              .getInterview(data.get(VARIABLES.Bid.INTERVIEW_ID).toString());

          Map<String, Object> userMap = DataStoreRegistry.getInstance().getInterviewerDataStore()
              .getUserInfo(interview.getInterviewee());

          // send mail to interviewee about bid
          Map<AttributeType, String> param = new HashMap<AttributeType, String>();
          param.put(AttributeType.INTERVIEW_INTERVIEWEE, interview.getInterviewee());
          param.put(AttributeType.INTERVIEW_TITLE, interview.getTitle());
          param.put(AttributeType.INTERVIEW_URL, data.get("baseURL").toString()
              + "/interviewdetail.do?iid=" + data.get(VARIABLES.Bid.INTERVIEW_ID).toString());
          int highestBidAmount = DataStoreRegistry.getInstance().getBidStore()
              .getMaxBidOfInterview(data.get(VARIABLES.Bid.INTERVIEW_ID).toString());
          param.put(AttributeType.BID_PRICE_HIGHEST, String.valueOf(highestBidAmount));
          String email = DataStoreRegistry.getInstance().getInterviewerDataStore()
              .getUserEmail(interview.getInterviewee());
          List<String> resEmail = new ArrayList<String>();
          resEmail.add(email);
          Services.getInstance().getEmailService().sendMail(Mailer.EmailType.BID_PLACED_INTERVIEWEE,
              model, userMap.get(USER.EMAIL).toString());
              // Services.getInstance().getEmailService().sendMailChannelOnEvent("6", param,
              // resEmail,
              // "You received a new bid for " + interview.getTitle());

          // send mail to bidder
          Map<AttributeType, String> param1 = new HashMap<AttributeType, String>();
          param1.put(AttributeType.BID_BIDDER, bid.getBidder());
          param1.put(AttributeType.BID_PRICE, bid.getPrice());
          param1.put(AttributeType.INTERVIEW_TITLE, interview.getTitle());
          param1.put(AttributeType.INTERVIEW_URL, data.get("baseURL").toString()
              + "/interviewdetail.do?iid=" + data.get(VARIABLES.Bid.INTERVIEW_ID).toString());
          param1.put(AttributeType.BID_PRICE_HIGHEST, String.valueOf(highestBidAmount));
          String email1 = DataStoreRegistry.getInstance().getInterviewerDataStore()
              .getUserEmail(bid.getBidder());
          Services.getInstance().getEmailService().sendMail(Mailer.EmailType.BID_PLACED_INTERVIEWER,
              model, email1);

          // Services.getInstance().getEmailService().sendMailChannelOnEvent("7", param, resEmail1,
          // "Your bid has been placed successfully for " + interview.getTitle());
        }
      } catch (Exception e) {
        logger.error(" Exception MAKE BID: ", e);
      }

    } else if (null != subReq
        && subReq.toString().equals(REQUEST_TYPES.BID_SUB_REQ.GET_BID_BY_BIDDER_AND_INTERVIEW)) {
      String bidder = (String) data.get(VARIABLES.Bid.BIDDER);
      String iid = (String) data.get(VARIABLES.Bid.INTERVIEW_ID);
      Bid bid = null;
      try {
        bid = DataStoreRegistry.getInstance().getBidStore().getBidByBidderAndInterview(bidder, iid);
      } catch (RemoteException e) {
        logger.error("Exception GET BID : ", e);
      }
      resMap.put("bid", bid);
    }

    return resMap;
  }

  public void destroy() {
    logger.debug("Destroying Bidhander Bean.......");
  }
}

