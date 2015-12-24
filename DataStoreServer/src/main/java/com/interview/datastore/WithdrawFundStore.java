package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Transactions;
import com.interview.framework.rmi.common.IwithdrawfundStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class WithdrawFundStore extends UnicastRemoteObject implements IwithdrawfundStore {
  private static final long serialVersionUID = 1L;

  public WithdrawFundStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  public void insertWithdrawFund(Transactions transactions) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db
        .getCollection(DATASTORES.WITHDRAW_FUND.COLLECTION);
    DBObject dbObject = new BasicDBObject();
    dbObject.put(DATASTORES.WITHDRAW_FUND.USERNAME, transactions.getOwner());
    dbObject.put(DATASTORES.WITHDRAW_FUND.AMOUNT, transactions.getAmount());
    dbObject.put(DATASTORES.WITHDRAW_FUND.PAYPAL_ADDRESS, transactions.getPaypal_address());
    dbObject.put(DATASTORES.WITHDRAW_FUND.REQUESTED_DATE, new Date().getTime());
    dbObject.put(DATASTORES.WITHDRAW_FUND.STATUS, transactions.getStatus());
    dbObject.put(DATASTORES.WITHDRAW_FUND.PROCESSED_DATE, "0");
    WriteResult wr = collection.save(dbObject);
  }
}
