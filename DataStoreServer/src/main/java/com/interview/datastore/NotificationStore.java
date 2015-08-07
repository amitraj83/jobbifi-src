package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Notification;
import com.interview.framework.rmi.common.INotificationStore;
import com.interview.notificationprocessor.NotificationProcessor;
import com.interview.services.Services;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

// @Service("notificationStore")
// @DependsOn("mongoService")
// @DependsOn("rmiserver")
public class NotificationStore extends UnicastRemoteObject implements INotificationStore {

  private int defaultMaxNotification = 30;
  private final boolean UNREAD = true;
  private final boolean ALL = false;

  protected NotificationStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  public ObjectId save(Notification notif) throws RemoteException {
    DBCollection COLLECTION =
        Services.getInstance().getBaseDataStore().db
            .getCollection(VARIABLES.NOTIFICATION.DATASTORE.COLLECTION);
    ObjectId _id = new ObjectId();
    DBObject notifObject = new BasicDBObject();
    notifObject.put(VARIABLES.NOTIFICATION.DATASTORE.CONTENT, notif.getContent());
    notifObject.put(VARIABLES.NOTIFICATION.DATASTORE.CREATEDBY, notif.getCreatedBy());
    notifObject.put(VARIABLES.NOTIFICATION.DATASTORE.ENTRY_DATE, notif.getEntryDate());
    // notifObject.put(VARIABLES.NOTIFICATION.DATASTORE.INTERVIEWID, notif.getInterviewId());
    notifObject.put(VARIABLES.NOTIFICATION.DATASTORE.NOTIFICATIONID, _id);
    notifObject.put(VARIABLES.NOTIFICATION.DATASTORE.RECEPIENTS_USER, notif.getRecepientUser());
    notifObject.put(VARIABLES.NOTIFICATION.DATASTORE.TYPE, notif.getType());
    notifObject.put(VARIABLES.NOTIFICATION.DATASTORE.HAS_READ, 0);

    WriteResult wr = COLLECTION.save(notifObject);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok()) {
      return _id;
    } else
      return null;

  }

  public List<Notification> getUnReadNotification(String user, int max) throws RemoteException {
    return getNotifications(user, max, UNREAD);
  }

  public List<Notification> getAllLatestNotification(String user, int max) throws RemoteException {
    return getNotifications(user, max, ALL);
  }

  private List<Notification> getNotifications(String user, int max, boolean unread)
      throws RemoteException {

    DBCollection COLLECTION =
        Services.getInstance().getBaseDataStore().db
            .getCollection(VARIABLES.NOTIFICATION.DATASTORE.COLLECTION);

    BasicDBObject query = new BasicDBObject(VARIABLES.NOTIFICATION.DATASTORE.RECEPIENTS_USER, user);
    if (unread)
      query.append(VARIABLES.NOTIFICATION.DATASTORE.HAS_READ, 0);

    // new BasicDBObject("$in", user));
    DBCursor cursor =
        COLLECTION.find(query)
            .sort(new BasicDBObject(VARIABLES.NOTIFICATION.DATASTORE.ENTRY_DATE, -1)).limit(max);
    List<Notification> list = new ArrayList<Notification>();
    List<ObjectId> nIds = new ArrayList<ObjectId>();
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      Notification notification = new Notification();

      Map<Object, Object> contentMap = makeNotificationContentMap(row);

      notification.setContent(contentMap);
      notification.setCreatedBy(row.get(VARIABLES.NOTIFICATION.DATASTORE.CREATEDBY).toString());
      notification.setEntryDate((Long) row.get(VARIABLES.NOTIFICATION.DATASTORE.ENTRY_DATE));
      notification.setNotificationId(row.get(VARIABLES.NOTIFICATION.DATASTORE.NOTIFICATIONID)
          .toString());
      notification.setRecepientUser(row.get(VARIABLES.NOTIFICATION.DATASTORE.RECEPIENTS_USER)
          .toString());
      notification.setType(row.get(VARIABLES.NOTIFICATION.DATASTORE.TYPE).toString());
      list.add(notification);
      nIds.add(new ObjectId(row.get(VARIABLES.NOTIFICATION.DATASTORE.NOTIFICATIONID).toString()));
    }
    markNotificationsAsRead(nIds);
    return list;
  }

  private Map<Object, Object> makeNotificationContentMap(DBObject row) {
    String type = row.get(VARIABLES.NOTIFICATION.DATASTORE.TYPE).toString();
    NotificationProcessor notification = NotificationProcessor.getInstance(type);
    return notification.createContentMap(row);

  }

  private void markNotificationsAsRead(List<ObjectId> nIds) {
    DBCollection COLLECTION =
        Services.getInstance().getBaseDataStore().db
            .getCollection(VARIABLES.NOTIFICATION.DATASTORE.COLLECTION);

    for (ObjectId nId : nIds) {
      DBObject query = new BasicDBObject(VARIABLES.NOTIFICATION.DATASTORE.NOTIFICATIONID, nId);
      DBObject updateDoc =
          new BasicDBObject("$set", new BasicDBObject(VARIABLES.NOTIFICATION.DATASTORE.HAS_READ, 1));
      COLLECTION.update(query, updateDoc);
    }
  }

  private List<String> getRecepientsList(BasicDBList listDBRecepients) throws RemoteException {
    List<String> recepients = new ArrayList<String>();
    Iterator<Object> it = listDBRecepients.iterator();
    while (it.hasNext()) {
      recepients.add(it.next().toString());
    }
    return recepients;
  }

  public Map<String, List<Notification>> getNotifications(List<String> users)
      throws RemoteException {
    Map<String, List<Notification>> fullList = new HashMap<String, List<Notification>>();

    for (String user : users) {
      List<Notification> list = getNotifications(user, defaultMaxNotification, UNREAD);
      fullList.put(user, list);
    }
    return fullList;
  }



}
