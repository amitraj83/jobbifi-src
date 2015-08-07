package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.ResetPasswordEntity;

public interface IResetPasswordDataStore extends Remote {

  String NAME = "resetpassword";

  public ObjectId insert(ResetPasswordEntity entity) throws RemoteException;

  public ResetPasswordEntity getResetPasswordEntity(ObjectId _id) throws RemoteException;

  public void delete(ObjectId _id) throws RemoteException;

  public boolean setExprired(ObjectId _id, boolean option) throws RemoteException;


}
