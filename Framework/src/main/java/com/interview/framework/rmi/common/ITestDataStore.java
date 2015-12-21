package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Test;

public interface ITestDataStore extends Remote {

  public static final String NAME = "testStore";

  public ObjectId saveTest(Test test) throws RemoteException;

  public Test getTest(String id) throws RemoteException;

  public List<Test> getAllTests() throws RemoteException;

  public List<Test> getAllPublishTests() throws RemoteException;

  public int deleteTest(String id) throws RemoteException;

  public int publishTest(String id) throws RemoteException;
}
