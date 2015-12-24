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
public class NewInterviewNotificationProcessor extends NotificationProcessor {

  public NewInterviewNotificationProcessor() {
    register(VARIABLES.NOTIFICATION.TYPE.NEW_INTERVIEW_NOTIFICATION, this);
  }

  /*
   * @Override public void process(Object document) { try{ Interview interview =
   * (Interview)document; List<String> companies =
   * Services.getInstance().getCompanyServices().getRelatedCompaniesForSkills
   * (interview.getSkills()); List<String> receivers =
   * Services.getInstance().getInterviewerDataStore().getMatchingUsersList( interview.getSkills(),
   * companies, null); receivers.remove(interview.getInterviewee()); for (String receiver :
   * receivers) {
   * 
   * Notification notification = new Notification(); Map<Object, Object> nData = new HashMap<Object,
   * Object>(); nData.put(VARIABLES.IID, interview.getId()); notification.setContent(nData);
   * notification.setCreatedBy(interview.getInterviewee());
   * notification.setEntryDate(interview.getDt()); notification.setRecepientUser(receiver);
   * notification.setType(VARIABLES.NOTIFICATION.TYPE.NEW_INTERVIEW_NOTIFICATION);
   * Services.getInstance().getNotificationStore().save(notification); } } catch (RemoteException e)
   * { // TODO Auto-generated catch block e.printStackTrace(); } }
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
      contentMap.put(DATASTORES.INTERVIEW.SKILLS, interview.getSkills());
      contentMap.put(VARIABLES.IID, interview.getId());
      contentMap.put(DATASTORES.INTERVIEW.DATE, interview.getDt());
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return contentMap;
  }



  /*
   * @Override public void save(Interview interview) {
   * 
   * 
   * 
   * }
   */

}
