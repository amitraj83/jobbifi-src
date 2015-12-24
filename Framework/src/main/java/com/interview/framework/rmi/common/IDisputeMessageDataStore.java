package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.DisputeMessage;

public interface IDisputeMessageDataStore extends Remote {

  String NAME = "disputesmessage";

  public ObjectId insertMessage(DisputeMessage disputeMessage) throws RemoteException;

  public List<DisputeMessage> getMessages(String disputeId) throws RemoteException;
}
