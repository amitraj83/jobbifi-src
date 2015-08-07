package com.interview.notification;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.Notification;
import com.interview.rmi.DataStoreRegistry;

@Service
public class AwardInterviewNotificationProcessor extends NotificationProcessor {

  public AwardInterviewNotificationProcessor() {
    register(VARIABLES.NOTIFICATION.TYPE.AWARD_INTERVIEW, this);
  }

  @Override
  public void process(Object document) {
    try {
      String iid = (String) document;

      Interview interview;

      interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);

      Set<String> recepients = new HashSet<String>();
      recepients.add(interview.getInterviewee());

      List<Bid> bids =
          DataStoreRegistry.getInstance().getBidStore().getBidsReceivedForInterview(iid);
      for (Bid bid : bids) {
        recepients.add(bid.getBidder());
      }

      Notification notification = new Notification();
      Map<Object, Object> nData = new HashMap<Object, Object>();
      nData.put(VARIABLES.IID, interview.getId());
      notification.setContent(nData);
      notification.setCreatedBy(interview.getInterviewee());
      notification.setEntryDate(new Date().getTime());
      notification.setType(VARIABLES.NOTIFICATION.TYPE.AWARD_INTERVIEW);

      for (String recepient : recepients) {
        notification.setRecepientUser(recepient);
        DataStoreRegistry.getInstance().getNotificationStore().save(notification);
      }
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  /*
   * @Override public Map<Object, Object> createContentMap(DBObject row) { Map<Object, Object>
   * contentMap = new HashMap<Object, Object>(); try{ BasicDBObject basicDBObject = (BasicDBObject)
   * row.get(VARIABLES.NOTIFICATION.DATASTORE.CONTENT); ObjectId iid = new ObjectId(
   * basicDBObject.get(VARIABLES.IID).toString()); Interview interview =
   * Services.getInstance().getInterviewDataStore().getInterview(iid); Bid bid =
   * Services.getInstance().getInterviewDataStore().getAcceptedBidForInterview (iid);
   * contentMap.put(DATASTORES.INTERVIEW.TITLE, interview.getTitle()); contentMap.put(VARIABLES.IID,
   * interview.getId()); contentMap.put(DATASTORES.INTERVIEW.INTERVIEWEE,
   * interview.getInterviewee()); contentMap.put(DATASTORES.INTERVIEW.INTERVIEWER,
   * interview.getInterviewer()); contentMap.put(VARIABLES.Bid.PRICE, bid.getPrice()); } catch
   * (RemoteException e) { // TODO Auto-generated catch block e.printStackTrace(); } return
   * contentMap; }
   */
}
