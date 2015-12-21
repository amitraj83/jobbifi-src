package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.interview.framework.pojo.CompanyAccount;

public interface ICompanyAccountStore extends Remote {

  String NAME = "companyaccount";

  public String insertTransaction(CompanyAccount account) throws RemoteException;

  public boolean cancelTransaction(String _id) throws RemoteException;

  public boolean deleteTransaction(String _id) throws RemoteException;

}
