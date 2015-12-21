package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.UploadedFile;
import com.interview.rmi.DataStoreRegistry;

public class GetMyBidHandler extends RequestHandler {

  public GetMyBidHandler() {
    addHandler(this, REQUEST_TYPES.GET_MY_BIDS);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    try {

      String username = (String) data.get(USER.USERNAME);
      String SUB_REQ = null;
      if (data.get(REQUEST_TYPES.SUB_REQ) != null) {
        SUB_REQ = data.get(REQUEST_TYPES.SUB_REQ).toString();
      }

      if (SUB_REQ != null && SUB_REQ.equals(REQUEST_TYPES.GET_ALL_BIDS)) {

        int pageNumber = new Integer(data.get("pagenum").toString());
        List<Object> listOfBids = new ArrayList<Object>();
        List<Bid> posted =
            DataStoreRegistry.getInstance().getBidStore().getBidsIPosted(username, pageNumber);
        if (posted != null) {
          for (Bid bid : posted) {
            // TODO : change
            Interview interview =
                DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(bid.getIid());
            interview.setPrice(bid.getPrice());
            interview.setDt(new Date((Long) bid.getDate()).getTime());
            interview.setStatus(bid.getStatus());
            listOfBids.add(interview);
          }
          resMap.put("ALL_BID_PLACED", listOfBids);
          resMap.put("ALL_BID_PLACED_count",
              DataStoreRegistry.getInstance().getBidStore().getBidsIPostedCount(username));
        }

      } else if (SUB_REQ != null && SUB_REQ.equals(REQUEST_TYPES.GET_AWARDED_BIDS)) {

        int pageNumber = new Integer(data.get("pagenum").toString());
        List<Object> listOfBids = new ArrayList<Object>();
        List<Interview> listofInterview = DataStoreRegistry.getInstance().getInterviewDataStore()
            .getAwardedInterview(username, pageNumber);
        for (Interview interview : listofInterview) {
          Bid bid = DataStoreRegistry.getInstance().getBidStore()
              .getBidByBidderAndInterview(username, interview.getId());
          interview.setPrice(bid.getPrice());
          interview.setDt(new Date((Long) bid.getDate()).getTime());
          listOfBids.add(interview);
        }
        resMap.put("AWARDED_BID", listOfBids);
        resMap.put("AWARDED_BID_count", DataStoreRegistry.getInstance().getInterviewDataStore()
            .getTotalAwardedInterviewCount(username));

      } else {
        List<Bid> posted = DataStoreRegistry.getInstance().getBidStore().getBidsIPosted(username);
        if (posted != null) {
          for (Bid bid : posted) {
            bid.setReputation(
                DataStoreRegistry.getInstance().getRatingStore().getReviewsCount(username));
          }
        }
        resMap.put(VARIABLES.BIDS_POSTED, (posted));

        List<Map<String, Object>> listOfBids = new ArrayList<Map<String, Object>>();
        List<Interview> listOfMyPostedInterviews = DataStoreRegistry.getInstance()
            .getInterviewDataStore().getInterviews(username, DATASTORES.INTERVIEW.INTERVIEWEE);

        for (Interview interview : listOfMyPostedInterviews) {
          List<Bid> received = DataStoreRegistry.getInstance().getBidStore()
              .getBidsReceivedForInterview(interview.getId());

          for (Bid bid : received) {
            Map<String, Object> bidMap = new HashMap<String, Object>();
            bidMap.put(VARIABLES.Bid.BID_ID, bid.getId());
            bidMap.put(VARIABLES.Bid.BIDDER, bid.getBidder());
            bidMap.put(VARIABLES.Bid.DATE, bid.getDate());
            bidMap.put(VARIABLES.Bid.INTERVIEW_ID, bid.getIid());
            bidMap.put(VARIABLES.Bid.MSG, bid.getMsg());
            bidMap.put(VARIABLES.Bid.PRICE, bid.getPrice());
            bidMap.put(VARIABLES.Bid.STATUS, bid.getStatus());
            bidMap.put(VARIABLES.Bid.STATUS_STRING, bid.getStatusString());
            bidMap.put(VARIABLES.BIDDERS_RATING,
                DataStoreRegistry.getInstance().getRatingStore().getAvgRating(bid.getBidder()));
            if (bid.getAttachmentID() != null && !bid.getAttachmentID().equals("")) {
              UploadedFile file = DataStoreRegistry.getInstance().getUploadedFileDataStore()
                  .getUploadedFile(new ObjectId(bid.getAttachmentID()));
              bidMap.put(VARIABLES.BID_ATTACHMENT_URL, file.getURL());
              bidMap.put(VARIABLES.BID_ATTACHMENT_NAME, file.getOriginalFileName());
            }
            bidMap.put("bidderreviewcount",
                DataStoreRegistry.getInstance().getRatingStore().getReviewsCount(bid.getBidder()));
            listOfBids.add(bidMap);
          }
        }

        resMap.put(VARIABLES.BIDS_RECEIVED, (listOfBids));
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return resMap;
  }

}
