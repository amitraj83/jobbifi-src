package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Education;

public interface IEducationStore extends Remote {

  String NAME = "education";

  public ObjectId insert(Education education) throws RemoteException;

  public Education getEducation(ObjectId _id) throws RemoteException;

  public void deleteEducation(ObjectId id) throws RemoteException;
}
