package com.interview.notificationprocessor;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.Rating;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class RatingNotificationProcessor extends NotificationProcessor {

  public RatingNotificationProcessor() {
    register(VARIABLES.NOTIFICATION.TYPE.RATING_NOTIFICATION, this);
  }

  @Override
  public Map<Object, Object> createContentMap(DBObject row) {

    Map<Object, Object> contentMap = new HashMap<Object, Object>();
    try {
      BasicDBObject basicDBObject =
          (BasicDBObject) row.get(VARIABLES.NOTIFICATION.DATASTORE.CONTENT);
      ObjectId rid = new ObjectId(basicDBObject.get(DATASTORES.RATING.ID).toString());

      Rating rating = Services.getInstance().getRatingStore().getRating(rid);

      Interview interview =
          Services.getInstance().getInterviewDataStore().getInterview(rating.getIid());

      contentMap.put(DATASTORES.RATING.USERNAME, rating.getUsername());
      contentMap.put(DATASTORES.RATING.AVERAGE, rating.getAverage());
      contentMap.put("title", interview.getTitle());
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return contentMap;
  }
  /*
   * @Override public void process(Object document) { try{ Rating rating = (Rating)document;
   * Notification notification = new Notification();
   * 
   * Map<Object, Object> nMap = new HashMap<Object, Object>(); nMap.put(DATASTORES.RATING.ID,
   * rating.getId()); notification.setContent(nMap); notification.setCreatedBy(rating.getRatedBy());
   * notification.setEntryDate(rating.getTime());
   * notification.setType(VARIABLES.NOTIFICATION.TYPE.RATING_NOTIFICATION);
   * 
   * notification.setRecepientUser(rating.getRatedBy());
   * Services.getInstance().getNotificationStore().save(notification);
   * 
   * notification.setRecepientUser(rating.getUsername());
   * Services.getInstance().getNotificationStore().save(notification); } catch (Exception e) { //
   * TODO Auto-generated catch block e.printStackTrace(); } }
   */
}
