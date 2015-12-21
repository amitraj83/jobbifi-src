package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Position;

public interface IPositionStore extends Remote {

  String NAME = "position";

  public ObjectId insert(Position position) throws RemoteException;

  public Position getPosition(ObjectId _id) throws RemoteException;

  public void deletePosition(ObjectId id) throws RemoteException;

}
