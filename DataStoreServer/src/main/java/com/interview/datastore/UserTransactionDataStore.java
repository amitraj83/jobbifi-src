package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.UserTransaction;
import com.interview.framework.rmi.common.IUserTransaction;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserTransactionDataStore extends UnicastRemoteObject implements IUserTransaction {

  /**
   * 
   */
  private static final long serialVersionUID = 1101341669643225217L;

  protected UserTransactionDataStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  @Override
  public List<String> getTransactionIDs(String username) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    List<String> list = new ArrayList<String>();
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.USER_TRANSACTION.USERNAME, username);
    DBCursor cursor =
        collection.find(query).sort(new BasicDBObject(DATASTORES.USER_TRANSACTION.TIME, -1));
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      list.add(row.get(DATASTORES.USER_TRANSACTION.TRANSACTION_ID).toString());
    }

    return list;
  }

  @Override
  public List<UserTransaction> getUserTransactions(String username) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    List<UserTransaction> list = new ArrayList<UserTransaction>();
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.USER_TRANSACTION.USERNAME, username);
    DBCursor cursor =
        collection.find(query).sort(new BasicDBObject(DATASTORES.USER_TRANSACTION.TIME, -1));
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      UserTransaction ut = new UserTransaction();
      ut.set_id(row.get(DATASTORES.USER_TRANSACTION.ID).toString());
      if (row.get(DATASTORES.USER_TRANSACTION.ARTIFACT) != null)
        ut.setArtifact(row.get(DATASTORES.USER_TRANSACTION.ARTIFACT).toString());
      if (row.get(DATASTORES.USER_TRANSACTION.ARTIFACT_ID) != null)
        ut.setArtifactid(row.get(DATASTORES.USER_TRANSACTION.ARTIFACT_ID).toString());
      if (row.get(DATASTORES.USER_TRANSACTION.TIME) != null)
        ut.setTime(new Long(row.get(DATASTORES.USER_TRANSACTION.TIME).toString()));
      if (row.get(DATASTORES.USER_TRANSACTION.TRANSACTION_ID) != null)
        ut.setTid(row.get(DATASTORES.USER_TRANSACTION.TRANSACTION_ID).toString());
      if (row.get(DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE) != null)
        ut.setPurpose(row.get(DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE).toString());
      if (row.get(DATASTORES.USER_TRANSACTION.USERNAME) != null)
        ut.setUsername(row.get(DATASTORES.USER_TRANSACTION.USERNAME).toString());

      list.add(ut);
    }

    return list;
  }

  @Override
  public String save(UserTransaction userTransaction) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    ObjectId _id = new ObjectId();
    DBObject row = new BasicDBObject();
    row.put(DATASTORES.USER_TRANSACTION.ID, _id);
    row.put(DATASTORES.USER_TRANSACTION.USERNAME, userTransaction.getUsername());
    row.put(DATASTORES.USER_TRANSACTION.TRANSACTION_ID, userTransaction.getTid());
    row.put(DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE, userTransaction.getPurpose());
    row.put(DATASTORES.USER_TRANSACTION.ARTIFACT, userTransaction.getArtifact());
    row.put(DATASTORES.USER_TRANSACTION.ARTIFACT_ID, userTransaction.getArtifactid());
    row.put(DATASTORES.USER_TRANSACTION.TIME, userTransaction.getTime());
    CommandResult cr = collection.save(row).getCachedLastError();
    if (cr.ok())
      return _id.toString();
    else
      return null;
  }

  @Override
  public boolean deleteUserTransaction(String id) throws RemoteException {
    ObjectId _id = new ObjectId(id);
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put("_id", _id);

    DBObject row = collection.findOne(query);
    CommandResult cr = collection.remove(row).getCachedLastError();
    return cr.ok();
  }



}
