package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.FIRST_REQUESTS;
import com.interview.framework.VARIABLES;
import com.interview.framework.rmi.common.IRequestDataStore;
import com.interview.services.Services;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class RequestsDataStore extends UnicastRemoteObject implements IRequestDataStore {

  /**
   * 
   */
  private static final long serialVersionUID = -1697158246990554825L;

  protected RequestsDataStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  public Map<String, Object> registerRequest(String interviewerUsername, String intervieweeUsername)
      throws RemoteException {
    Map<String, Object> resMap = new HashMap<String, Object>();
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(FIRST_REQUESTS.DBCOLLECTION);
    DBObject dbObject = new BasicDBObject();
    dbObject.put(FIRST_REQUESTS.INTERVIEWER, interviewerUsername);
    dbObject.put(FIRST_REQUESTS.INTERVIEWEE, intervieweeUsername);
    Date currDate = new Date();
    dbObject.put(FIRST_REQUESTS.DATE_AND_TIME, currDate);
    dbObject.put(FIRST_REQUESTS.STATUS, FIRST_REQUESTS.STATUSES.PENDING.ordinal());
    ObjectId objectId = new ObjectId();
    dbObject.put(FIRST_REQUESTS.ID, objectId);
    dbObject.put(FIRST_REQUESTS.UPDATE_DATE, "");
    WriteResult wr = collection.insert(dbObject);
    CommandResult cr = wr.getLastError();
    if (cr.ok()) {
      // resMap.put("response", "OK");
      // resMap.put("date", currDate.toString());

      Map<String, String> map = new HashMap<String, String>();
      map.put(FIRST_REQUESTS.INTERVIEWER, interviewerUsername);
      map.put(FIRST_REQUESTS.DATE_AND_TIME, currDate.getTime() + "");
      map.put(FIRST_REQUESTS.STATUS, FIRST_REQUESTS.STATUSES.PENDING.ordinal() + "");
      // map.put(FIRST_REQUESTS.ID, iRequest.get(FIRST_REQUESTS.ID).toString());
      resMap.put(objectId.toString(), map);
    } else
      resMap.put("error", cr.getErrorMessage());

    return resMap;
  }

  public Map<String, Object> retrieveAllFirstRequestsForUsername(String username)
      throws RemoteException {


    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(FIRST_REQUESTS.DBCOLLECTION);
    DBObject query = new BasicDBObject();
    query.put(FIRST_REQUESTS.INTERVIEWEE, username);

    DBCursor cursor = collection.find(query);
    Map<String, Object> requestsSent = new HashMap<String, Object>();
    while (cursor.hasNext()) {
      DBObject iRequest = cursor.next();
      Map<String, String> map = new HashMap<String, String>();
      map.put(FIRST_REQUESTS.INTERVIEWER, iRequest.get(FIRST_REQUESTS.INTERVIEWER).toString());
      map.put(FIRST_REQUESTS.DATE_AND_TIME,
          ((Date) iRequest.get(FIRST_REQUESTS.DATE_AND_TIME)).getTime() + "");
      map.put(FIRST_REQUESTS.STATUS, iRequest.get(FIRST_REQUESTS.STATUS).toString());
      map.put(FIRST_REQUESTS.USER_ONLINE, "0");
      requestsSent.put(iRequest.get(FIRST_REQUESTS.ID).toString(), map);
    }

    query = new BasicDBObject();
    query.put(FIRST_REQUESTS.INTERVIEWER, username);

    cursor = collection.find(query);
    Map<String, Object> requestsReceived = new HashMap<String, Object>();
    while (cursor.hasNext()) {
      DBObject iRequest = cursor.next();
      Map<String, String> map = new HashMap<String, String>();
      map.put(FIRST_REQUESTS.INTERVIEWEE, iRequest.get(FIRST_REQUESTS.INTERVIEWEE).toString());
      map.put(FIRST_REQUESTS.DATE_AND_TIME,
          ((Date) iRequest.get(FIRST_REQUESTS.DATE_AND_TIME)).getTime() + "");
      map.put(FIRST_REQUESTS.STATUS, iRequest.get(FIRST_REQUESTS.STATUS).toString());
      map.put(FIRST_REQUESTS.USER_ONLINE, "0");
      requestsReceived.put(iRequest.get(FIRST_REQUESTS.ID).toString(), map);
    }

    Map<String, Object> resMap = new HashMap<String, Object>();
    resMap.put(VARIABLES.REQ_SENT, requestsSent);
    resMap.put(VARIABLES.REQ_RECEIVED, requestsReceived);

    System.out.println("Retrieve: " + resMap.toString());
    return resMap;
  }

  public int updateStatus(ObjectId id, FIRST_REQUESTS.STATUSES status) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(FIRST_REQUESTS.DBCOLLECTION);
    DBObject query = new BasicDBObject();
    query.put(FIRST_REQUESTS.ID, id);
    BasicDBObject uo = new BasicDBObject();
    uo.append("$set", new BasicDBObject().append(FIRST_REQUESTS.STATUS, status.ordinal())
        .append(FIRST_REQUESTS.UPDATE_DATE, new Date().toString()));
    WriteResult result = collection.update(query, uo);
    CommandResult cr = result.getLastError();
    if (cr.ok()) {
      return 1;
    } else
      return -1;
  }

  public int updateStatus(String id, FIRST_REQUESTS.STATUSES status) throws RemoteException {
    ObjectId oid = new ObjectId(id);
    if (oid != null) {
      return updateStatus(oid, status);
    } else
      return -1;
  }

  public List<String> getAcceptedFriends(String user) throws RemoteException {

    List<String> friendList = new ArrayList<String>();
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(FIRST_REQUESTS.DBCOLLECTION);
    DBObject query1 = new BasicDBObject(FIRST_REQUESTS.INTERVIEWEE, user);
    DBObject query2 = new BasicDBObject(FIRST_REQUESTS.INTERVIEWER, user);

    BasicDBList orList = new BasicDBList();
    orList.add(query1);
    orList.add(query2);

    BasicDBObject clause1 = new BasicDBObject("$or", orList);
    clause1.append(FIRST_REQUESTS.STATUS, FIRST_REQUESTS.STATUSES.ACCEPTED.ordinal());

    DBCursor cursor = collection.find(clause1);
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      if (row.get(FIRST_REQUESTS.INTERVIEWEE).equals(user))
        friendList.add((String) row.get(FIRST_REQUESTS.INTERVIEWER));
      else if (row.get(FIRST_REQUESTS.INTERVIEWER).equals(user))
        friendList.add((String) row.get(FIRST_REQUESTS.INTERVIEWEE));
    }
    return friendList;
  }

  public Map<String, String> getUsersOfRequestId(String rid) throws RemoteException {
    Map<String, String> users = new HashMap<String, String>();
    ObjectId id = new ObjectId(rid);
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(FIRST_REQUESTS.DBCOLLECTION);
    DBObject query = new BasicDBObject().append(FIRST_REQUESTS.ID, id);
    DBObject row = collection.findOne(query);
    users.put(FIRST_REQUESTS.INTERVIEWEE, (String) row.get(FIRST_REQUESTS.INTERVIEWEE));
    users.put(FIRST_REQUESTS.INTERVIEWER, (String) row.get(FIRST_REQUESTS.INTERVIEWER));
    return users;

  }


}
