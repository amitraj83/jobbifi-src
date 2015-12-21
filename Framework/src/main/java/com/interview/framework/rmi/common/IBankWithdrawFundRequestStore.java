package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.BankWithdrawFundRequest;

public interface IBankWithdrawFundRequestStore extends Remote {

  String NAME = "bankWithdrawFundRequestStore";

  public ObjectId saveBankWithdrawFundRequest(BankWithdrawFundRequest bankWithdrawFundRequest)
      throws RemoteException;

}
