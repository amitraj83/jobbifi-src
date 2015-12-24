package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.DisputeMessage;
import com.interview.framework.rmi.common.IDisputeMessageDataStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class DisputeMessageDataStore extends UnicastRemoteObject
    implements IDisputeMessageDataStore {


  /**
   * 
   */
  private static final long serialVersionUID = -3336287847882180604L;

  protected DisputeMessageDataStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  @Override
  public ObjectId insertMessage(DisputeMessage disputeMessage) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject dbObject = new BasicDBObject();
    ObjectId _id = new ObjectId();
    dbObject.put("_id", _id);
    dbObject.put(DATASTORES.DISPUTE_MESSAGE.DISPUTEID, disputeMessage.getDisputeId());
    dbObject.put(DATASTORES.DISPUTE_MESSAGE.MESSAGEBY, disputeMessage.getMessageBy());
    dbObject.put(DATASTORES.DISPUTE_MESSAGE.MESSAGE, disputeMessage.getMessage());
    dbObject.put(DATASTORES.DISPUTE_MESSAGE.FID, disputeMessage.getFid());
    dbObject.put(DATASTORES.DISPUTE_MESSAGE.TIME, disputeMessage.getTime());

    WriteResult wr = collection.save(dbObject);
    CommandResult cr = wr.getLastError();
    if (cr.ok())
      return _id;
    else
      return null;
  }

  @Override
  public List<DisputeMessage> getMessages(String disputeId) throws RemoteException {

    List<DisputeMessage> list = new ArrayList<DisputeMessage>();
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put(DATASTORES.DISPUTE_MESSAGE.DISPUTEID, disputeId);

    DBCursor cursor =
        collection.find(query).sort(new BasicDBObject(DATASTORES.DISPUTE_MESSAGE.TIME, 1));
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      DisputeMessage msg = new DisputeMessage();
      msg.setDisputeId(disputeId);
      msg.setFid(row.get(DATASTORES.DISPUTE_MESSAGE.FID) == null ? ""
          : row.get(DATASTORES.DISPUTE_MESSAGE.FID).toString());
      msg.setId(row.get("_id").toString());
      msg.setMessage(row.get(DATASTORES.DISPUTE_MESSAGE.MESSAGE).toString());
      msg.setMessageBy(row.get(DATASTORES.DISPUTE_MESSAGE.MESSAGEBY).toString());
      msg.setTime(new Long(row.get(DATASTORES.DISPUTE_MESSAGE.TIME).toString()));
      list.add(msg);
    }
    return list;
  }

}
