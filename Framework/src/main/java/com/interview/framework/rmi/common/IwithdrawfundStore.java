package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.interview.framework.pojo.Transactions;


public interface IwithdrawfundStore extends Remote {

  String NAME = "withdrawfundstore";

  public void insertWithdrawFund(Transactions transactions) throws RemoteException;
}
