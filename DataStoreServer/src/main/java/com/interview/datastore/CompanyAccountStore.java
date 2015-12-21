package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.CompanyAccount;
import com.interview.framework.rmi.common.ICompanyAccountStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class CompanyAccountStore extends UnicastRemoteObject implements ICompanyAccountStore {

  /**
   * 
   */
  private static final long serialVersionUID = 2929875881033321724L;

  protected CompanyAccountStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  @Override
  public String insertTransaction(CompanyAccount account) throws RemoteException {

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    ObjectId _id = new ObjectId();

    DBObject row = new BasicDBObject();
    row.put(DATASTORES.COMPANY_ACCOUNT.AMOUNT, account.getAmount());
    row.put(DATASTORES.COMPANY_ACCOUNT.DEBIT_OR_CREDIT, account.getDebitOrCredit());
    row.put(DATASTORES.COMPANY_ACCOUNT.ID, _id);
    row.put(DATASTORES.COMPANY_ACCOUNT.INITIATOR, account.getInitiator());
    row.put(DATASTORES.COMPANY_ACCOUNT.INTERVIEW_ID, account.getInterviewId());
    row.put(DATASTORES.COMPANY_ACCOUNT.PURPOSE, account.getPurpose());
    row.put(DATASTORES.COMPANY_ACCOUNT.STATUS, account.getStatus());
    row.put(DATASTORES.COMPANY_ACCOUNT.TIME, account.getTime());

    CommandResult cr = collection.save(row).getCachedLastError();
    if (cr.ok())
      return _id.toString();
    else
      return null;
  }

  @Override
  public boolean cancelTransaction(String id) throws RemoteException {
    ObjectId _id = new ObjectId(id);

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject query = new BasicDBObject();
    query.put("_id", _id);

    DBObject row = collection.findOne(query);
    BasicDBObject updateDoc =
        new BasicDBObject("$set", new BasicDBObject(DATASTORES.COMPANY_ACCOUNT.STATUS,
            DATASTORES.COMPANY_ACCOUNT.TSTATUS.CANCELLED));
    CommandResult cr = collection.update(query, updateDoc).getCachedLastError();
    return cr.ok();
  }

  @Override
  public boolean deleteTransaction(String id) throws RemoteException {
    ObjectId _id = new ObjectId(id);
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject query = new BasicDBObject();
    query.put("_id", _id);

    DBObject row = collection.findOne(query);
    CommandResult cr = collection.remove(query).getCachedLastError();
    return cr.ok();

  }

}
