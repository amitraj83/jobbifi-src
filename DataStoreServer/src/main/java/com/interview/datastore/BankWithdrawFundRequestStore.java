package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.BankWithdrawFundRequest;
import com.interview.framework.rmi.common.IBankWithdrawFundRequestStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class BankWithdrawFundRequestStore extends UnicastRemoteObject
    implements IBankWithdrawFundRequestStore {

  private static final long serialVersionUID = 1L;

  protected BankWithdrawFundRequestStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  @Override
  public ObjectId saveBankWithdrawFundRequest(BankWithdrawFundRequest bankWithdrawFundRequest)
      throws RemoteException {

    DBCollection collection = Services.getInstance().getBaseDataStore().db
        .getCollection(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.DBCollection);

    DBObject dbObject = new BasicDBObject();
    ObjectId _id = new ObjectId();
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.ID, _id);
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.USERNAME,
        bankWithdrawFundRequest.getUsername());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.AMOUNT,
        bankWithdrawFundRequest.getAmount().toPlainString());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.ACCOUNT_NAME,
        bankWithdrawFundRequest.getAccountName());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.ACCOUNT_NUMBER,
        bankWithdrawFundRequest.getAccountNumber());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.ACCOUNT_TYPE,
        bankWithdrawFundRequest.getAccountType());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.IFSC_CODE,
        bankWithdrawFundRequest.getIfscCode());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.BANK_NAME,
        bankWithdrawFundRequest.getBankName());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.BANK_BRANCH,
        bankWithdrawFundRequest.getBankBranch());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.BANK_CITY,
        bankWithdrawFundRequest.getBankCity());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.STATUS, bankWithdrawFundRequest.getStatus());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.REQUESTED_DATE,
        bankWithdrawFundRequest.getRequestDate());
    dbObject.put(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.PROCESSED_DATE,
        bankWithdrawFundRequest.getProcessedDate());

    WriteResult wr = collection.save(dbObject);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok()) {
      return _id;
    } else {
      return null;
    }
  }
}
