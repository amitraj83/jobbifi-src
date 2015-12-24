package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.UserTest;

public interface IUserTestStore extends Remote {

  public static final String NAME = "userTestStore";

  public ObjectId saveUserTest(UserTest userTest) throws RemoteException;

  public UserTest getUserTest(String id) throws RemoteException;

  public List<UserTest> getAllUserTests() throws RemoteException;

}
