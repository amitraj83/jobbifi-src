package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.ResetPasswordEntity;
import com.interview.framework.rmi.common.IResetPasswordDataStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class ResetPasswordDataStore extends UnicastRemoteObject implements IResetPasswordDataStore {

  private static final long serialVersionUID = 1L;

  protected ResetPasswordDataStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  public ObjectId insert(ResetPasswordEntity entity) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db
        .getCollection(DATASTORES.RESET_PASSWORD_ENTITY.COLLECTION);

    DBObject doc = new BasicDBObject();
    ObjectId _id = new ObjectId();
    doc.put(DATASTORES.RESET_PASSWORD_ENTITY.ID, _id);
    doc.put(DATASTORES.RESET_PASSWORD_ENTITY.USERNAME, entity.getUsername());
    doc.put(DATASTORES.RESET_PASSWORD_ENTITY.SECRETKEY, entity.getSecretKey());
    doc.put(DATASTORES.RESET_PASSWORD_ENTITY.SECTOKEN, entity.getSectoken());
    doc.put(DATASTORES.RESET_PASSWORD_ENTITY.EXPIRED, entity.isExpired());
    doc.put(DATASTORES.RESET_PASSWORD_ENTITY.IPADDRESS, entity.getIpAddress());
    doc.put(DATASTORES.RESET_PASSWORD_ENTITY.TIME, entity.getDt());

    WriteResult wr = collection.save(doc);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok())
      return _id;
    else
      return null;
  }


  public ResetPasswordEntity getResetPasswordEntity(ObjectId _id) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db
        .getCollection(DATASTORES.RESET_PASSWORD_ENTITY.COLLECTION);
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.RESET_PASSWORD_ENTITY.ID, _id);
    DBObject row = collection.findOne(query);
    ResetPasswordEntity entity = new ResetPasswordEntity();
    entity.setDt(new Long(row.get(DATASTORES.RESET_PASSWORD_ENTITY.TIME).toString()));
    entity.setExpired((Boolean) row.get(DATASTORES.RESET_PASSWORD_ENTITY.EXPIRED));
    entity.setId(_id.toString());
    entity.setSecretKey(row.get(DATASTORES.RESET_PASSWORD_ENTITY.SECRETKEY).toString());
    entity.setSectoken(row.get(DATASTORES.RESET_PASSWORD_ENTITY.SECTOKEN).toString());
    entity.setUsername(row.get(DATASTORES.RESET_PASSWORD_ENTITY.USERNAME).toString());
    return entity;
  }

  public void delete(ObjectId _id) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db
        .getCollection(DATASTORES.RESET_PASSWORD_ENTITY.COLLECTION);
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.RESET_PASSWORD_ENTITY.ID, _id);
    collection.remove(query);
  }

  public boolean setExprired(ObjectId _id, boolean option) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db
        .getCollection(DATASTORES.RESET_PASSWORD_ENTITY.COLLECTION);
    DBObject query = new BasicDBObject(DATASTORES.RESET_PASSWORD_ENTITY.ID, _id);
    DBObject updateDoc = new BasicDBObject("$set",
        new BasicDBObject(DATASTORES.RESET_PASSWORD_ENTITY.EXPIRED, option));
    WriteResult wr = collection.update(query, updateDoc);
    CommandResult cr = wr.getCachedLastError();
    return cr.ok();
  }
}
