package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Dispute;

public interface IDispute extends Remote {

  String NAME = "disputes";

  public ObjectId createDispute(Dispute dispute) throws RemoteException;

  public Dispute getDispute(String iid) throws RemoteException;

  public Dispute getDispute(ObjectId _id) throws RemoteException;

  public List<Dispute> getDisputelist(String userName) throws RemoteException;

  public int closeDispute(ObjectId _id, String result) throws RemoteException;

  public List<Dispute> getAllDisputes(List<String> iids) throws RemoteException;

  public List<Dispute> getClosedDisputelist(String userName) throws RemoteException;

  public int closeDispute(ObjectId _id, String result, String closedBy) throws RemoteException;

}
