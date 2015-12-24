package com.interview.notificationprocessor;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class NewBidNotificationProcessor extends NotificationProcessor {

  public NewBidNotificationProcessor() {
    register(VARIABLES.NOTIFICATION.TYPE.NEW_BID_NOTIFICATION, this);
  }

  /*
   * @Override public void process(Object document) { try{ Bid bid = (Bid)document;
   * 
   * Notification notification = new Notification();
   * 
   * Map<Object, Object> nData = new HashMap<Object, Object>(); nData.put(VARIABLES.BID_ID,
   * bid.getId()); nData.put(VARIABLES.IID, bid.getIid());
   * 
   * notification.setContent(nData); notification.setCreatedBy(bid.getBidder());
   * notification.setEntryDate(bid.getDate()); Set<String> recepients = new HashSet<String>();
   * recepients.add(bid.getBidder()); Interview interview =
   * Services.getInstance().getInterviewDataStore().getInterview(new ObjectId(bid.getIid()));
   * recepients.add(interview.getInterviewee()); List<Bid> bids =
   * Services.getInstance().getBidStore().getBidsReceivedForInterview(bid.getIid()); for (Bid b :
   * bids) { recepients.add(b.getBidder()); }
   * notification.setType(VARIABLES.NOTIFICATION.TYPE.NEW_BID_NOTIFICATION);
   * 
   * for (String user: recepients) { notification.setRecepientUser(user);
   * Services.getInstance().getNotificationStore().save(notification); }
   * 
   * } catch (RemoteException e) { // TODO Auto-generated catch block e.printStackTrace(); }
   * 
   * }
   */
  @Override
  public Map<Object, Object> createContentMap(DBObject row) {
    Map<Object, Object> contentMap = new HashMap<Object, Object>();
    try {
      BasicDBObject basicDBObject =
          (BasicDBObject) row.get(VARIABLES.NOTIFICATION.DATASTORE.CONTENT);
      ObjectId iid = new ObjectId(basicDBObject.get(VARIABLES.IID).toString());
      ObjectId bidid = new ObjectId(basicDBObject.get(VARIABLES.BID_ID).toString());
      Interview interview = Services.getInstance().getInterviewDataStore().getInterview(iid);
      Bid bid = Services.getInstance().getBidStore().getBid(bidid);

      contentMap.put(DATASTORES.INTERVIEW.TITLE, interview.getTitle());
      contentMap.put(VARIABLES.IID, interview.getId());
      contentMap.put(VARIABLES.BID_ID, bidid.toString());
      contentMap.put(VARIABLES.Bid.BIDDER, bid.getBidder());
      contentMap.put(VARIABLES.Bid.DATE, bid.getDate());
      contentMap.put(VARIABLES.Bid.PRICE, bid.getPrice());
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return contentMap;
  }



}
