package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Transaction;

public interface ITransactionStore extends Remote {

  String NAME = "transaction";

  public ObjectId saveTrasaction(Transaction trans) throws RemoteException;

  public Transaction getTransaction(ObjectId tid) throws RemoteException;

  public int updateTransaction(String transactionid, double balance, String details)
      throws RemoteException;

  public boolean updateTransactionDetails(String transactionid, String details)
      throws RemoteException;

  public List<Transaction> getTransactionsList(String user, long fromDate, long toDate, int pageNum)
      throws RemoteException;

  public boolean deleteTransaction(String tid) throws RemoteException;

  public int getTotalRecord(String user, long fromDate, long toDate) throws RemoteException;
}
