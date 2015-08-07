package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.interview.framework.pojo.UserTransaction;

public interface IUserTransaction extends Remote {

  String NAME = "usertransaction";

  public String save(UserTransaction userTransaction) throws RemoteException;

  public List<String> getTransactionIDs(String username) throws RemoteException;

  public List<UserTransaction> getUserTransactions(String username) throws RemoteException;

  public boolean deleteUserTransaction(String _id) throws RemoteException;
}
