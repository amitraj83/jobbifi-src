package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.interview.framework.CHAT;
import com.interview.framework.chat.ChatMessage;
import com.interview.framework.rmi.common.IChatStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class ChatStore extends UnicastRemoteObject implements IChatStore {

  /**
   * 
   */
  private static final long serialVersionUID = -3933224087003969415L;

  protected ChatStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  public int saveChatMessage(ChatMessage message) throws RemoteException {

    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(CHAT.COLLECTION_NAME);
    DBObject dbObject = new BasicDBObject();
    dbObject.put(CHAT.RID, message.getRid());
    dbObject.put(CHAT.FROM, message.getFrom());
    dbObject.put(CHAT.TO, message.getTo());
    dbObject.put(CHAT.MESSAGE, message.getMessage());
    dbObject.put(CHAT.TIME, message.getTime());
    dbObject.put(CHAT.TYPE, message.getType());
    WriteResult wr = collection.save(dbObject);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok())
      return 1;
    else
      return -1;
  }

  @Override
  public List<ChatMessage> getChatHistory(String user, String otheruser, int max)
      throws RemoteException {

    List<ChatMessage> list = new ArrayList<ChatMessage>();
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(CHAT.COLLECTION_NAME);
    // from user to otheruser
    BasicDBObject case1 = new BasicDBObject();
    List<BasicDBObject> case1list = new ArrayList<BasicDBObject>();
    case1list.add(new BasicDBObject(CHAT.FROM, user));
    case1list.add(new BasicDBObject(CHAT.TO, otheruser));
    case1.put("$and", case1list);

    // OR
    // from otheruser to user
    BasicDBObject case2 = new BasicDBObject();
    List<BasicDBObject> case2list = new ArrayList<BasicDBObject>();
    case2list.add(new BasicDBObject(CHAT.TO, user));
    case2list.add(new BasicDBObject(CHAT.FROM, otheruser));
    case2.put("$and", case2list);

    BasicDBObject ORQuery = new BasicDBObject();
    List<BasicDBObject> orlist = new ArrayList<BasicDBObject>();
    orlist.add(case1);
    orlist.add(case2);
    ORQuery.put("$or", orlist);

    // DBCursor cursor = collection.find(andQuery);
    DBCursor cursor = collection.find(ORQuery).sort(new BasicDBObject(CHAT.TIME, 1)).limit(max);
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      ChatMessage message = new ChatMessage();
      // message.setRid((String)row.get(CHAT.RID));
      message.setFrom((String) row.get(CHAT.FROM));
      message.setTo((String) row.get(CHAT.TO));
      message.setMessage((String) row.get(CHAT.MESSAGE));
      message.setTime((Date) row.get(CHAT.TIME));
      message.setType((String) row.get(CHAT.TYPE));
      list.add(message);
    }
    return list;
  }

  public long getOfflineCount(String username) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(CHAT.COLLECTION_NAME);
    long count = collection.count(
        new BasicDBObject(CHAT.TO, username).append(CHAT.TYPE, CHAT.Type.OFFLINE.toString()));
    return count;
  }
}
