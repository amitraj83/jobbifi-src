package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Escrow;
import com.interview.framework.rmi.common.IEscrowDataStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class EscrowDataStore extends UnicastRemoteObject implements IEscrowDataStore {


  /**
   * 
   */
  private static final long serialVersionUID = 4860283829096742430L;

  protected EscrowDataStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  @Override
  public ObjectId createEscrowEntry(Escrow escrow) throws RemoteException {

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject dbObject = new BasicDBObject();
    ObjectId _id = new ObjectId();
    dbObject.put("_id", _id);
    dbObject.put(DATASTORES.ESCROW.VISIBLE_ID, escrow.getVisibleId());
    dbObject.put(DATASTORES.ESCROW.IID, escrow.getIid());
    dbObject.put(DATASTORES.ESCROW.AMOUNT, escrow.getAmount());
    dbObject.put(DATASTORES.ESCROW.STATUS, escrow.getStatus());
    dbObject.put(DATASTORES.ESCROW.DATE, escrow.getDate());


    WriteResult wr = collection.save(dbObject);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok()) {

      return _id;
    } else
      return null;
  }

  @Override
  public List<Escrow> getEscrowEntry(String iid) throws RemoteException {

    List<Escrow> escrows = new ArrayList<Escrow>();
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.ESCROW.IID, iid);

    DBCursor cursor = collection.find(query);
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      Escrow escrow = new Escrow();
      escrow.setAmount(new Double(row.get(DATASTORES.ESCROW.AMOUNT).toString()));
      escrow.setDate(new Long(row.get(DATASTORES.ESCROW.DATE).toString()));
      escrow.setId(row.get(DATASTORES.ESCROW.OBJECT_ID).toString());
      escrow.setIid(row.get(DATASTORES.ESCROW.IID).toString());
      escrow.setStatus(new Integer(row.get(DATASTORES.ESCROW.STATUS).toString()));
      escrow.setVisibleId(row.get(DATASTORES.ESCROW.VISIBLE_ID).toString());
      escrows.add(escrow);
    }
    return escrows;
  }

  @Override
  public boolean releaseStatusUpdate(String escrowId) throws RemoteException {

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.ESCROW.OBJECT_ID, new ObjectId(escrowId));
    BasicDBObject updateDoc = new BasicDBObject("$set",
        new BasicDBObject(DATASTORES.ESCROW.STATUS, DATASTORES.ESCROW.STATUSES.RELEASED));
    WriteResult wr = collection.update(query, updateDoc);
    if (wr.getCachedLastError().ok())
      return true;
    else
      return false;
  }

  @Override
  public double getReleasedEscrowForInterview(String iid) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    BasicDBObject andQuery = new BasicDBObject();
    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
    obj.add(new BasicDBObject(DATASTORES.ESCROW.IID, iid));
    obj.add(new BasicDBObject(DATASTORES.ESCROW.STATUS, DATASTORES.ESCROW.STATUSES.RELEASED));
    andQuery.put("$and", obj);


    double totalReleased = 0;

    DBCursor cursor = collection.find(andQuery);
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      totalReleased += new Double(row.get(DATASTORES.ESCROW.AMOUNT).toString());
    }

    return totalReleased;
  }



}
