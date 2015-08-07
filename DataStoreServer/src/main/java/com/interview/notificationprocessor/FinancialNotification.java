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
public class FinancialNotification extends NotificationProcessor {

  public FinancialNotification() {
    register(VARIABLES.NOTIFICATION.TYPE.FINANCIAL_NOTIFICATION, this);
  }

  /*
   * @Override public void process(Object document) { try{ Interview interview =
   * (Interview)document;
   * 
   * Notification notification = new Notification();
   * 
   * Map<Object, Object> nData = new HashMap<Object, Object>(); nData.put(VARIABLES.IID,
   * interview.getId()); //nData.put(DATASTORES.INTERVIEW.INTERVIEWER, interview.getInterviewer());
   * //nData.put(DATASTORES.INTERVIEW.TITLE, interview.getTitle());
   * 
   * notification.setContent(nData); notification.setCreatedBy(interview.getInterviewee());
   * notification.setEntryDate(new Date().getTime());
   * 
   * Set<String> recepients = new HashSet<String>(); recepients.add(interview.getInterviewee());
   * recepients.add(interview.getInterviewer());
   * notification.setType(VARIABLES.NOTIFICATION.TYPE.FINANCIAL_NOTIFICATION);
   * 
   * for (String user: recepients) { notification.setRecepientUser(user);
   * Services.getInstance().getNotificationStore().save(notification); } } catch (Exception e) { //
   * TODO Auto-generated catch block e.printStackTrace(); }
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
      Interview interview = Services.getInstance().getInterviewDataStore().getInterview(iid);

      contentMap.put(DATASTORES.INTERVIEW.TITLE, interview.getTitle());
      contentMap.put(DATASTORES.INTERVIEW.INTERVIEWEE, interview.getInterviewee());
      contentMap.put(DATASTORES.INTERVIEW.INTERVIEWER, interview.getInterviewer());
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return contentMap;
  }



}
