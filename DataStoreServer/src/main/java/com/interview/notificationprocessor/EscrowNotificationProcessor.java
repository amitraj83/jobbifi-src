package com.interview.notificationprocessor;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Interview;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class EscrowNotificationProcessor extends NotificationProcessor {

  public EscrowNotificationProcessor() {
    register(VARIABLES.NOTIFICATION.TYPE.ESCROW_NOTIFICATION, this);
  }

  @Override
  public Map<Object, Object> createContentMap(DBObject row) {

    Map<Object, Object> contentMap = new HashMap<Object, Object>();
    try {
      BasicDBObject basicDBObject =
          (BasicDBObject) row.get(VARIABLES.NOTIFICATION.DATASTORE.CONTENT);
      ObjectId iid = new ObjectId(basicDBObject.get(VARIABLES.IID).toString());

      Interview interview = Services.getInstance().getInterviewDataStore().getInterview(iid);

      contentMap.put(DATASTORES.INTERVIEW.INTERVIEWEE, interview.getInterviewee());
      contentMap.put(DATASTORES.INTERVIEW.TITLE, interview.getTitle());
      contentMap.put(DATASTORES.INTERVIEW.ESCROW_BALANCE, interview.getEb());
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return contentMap;
  }
  /*
   * @Override public void process(Object document) { try{ Interview interview =
   * (Interview)document;
   * 
   * Notification notification = new Notification(); Map<Object, Object> nMap = new HashMap<Object,
   * Object>(); nMap.put(VARIABLES.IID, interview.getId()); notification.setContent(nMap);
   * notification.setCreatedBy(interview.getInterviewee()); notification.setEntryDate(new
   * Date().getTime()); notification.setType(VARIABLES.NOTIFICATION.TYPE.ESCROW_NOTIFICATION);
   * 
   * notification.setRecepientUser(interview.getInterviewee());
   * Services.getInstance().getNotificationStore().save(notification);
   * 
   * notification.setRecepientUser(interview.getInterviewer());
   * Services.getInstance().getNotificationStore().save(notification); } catch (Exception e) { //
   * TODO Auto-generated catch block e.printStackTrace(); }
   * 
   * 
   * }
   */


}
