package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Escrow;

public interface IEscrowDataStore extends Remote {

  String NAME = "escrow";

  public ObjectId createEscrowEntry(Escrow escrow) throws RemoteException;

  public List<Escrow> getEscrowEntry(String iid) throws RemoteException;

  public boolean releaseStatusUpdate(String escrowId) throws RemoteException;

  public double getReleasedEscrowForInterview(String iid) throws RemoteException;
  // public boolean updateReleaseStatus(String escrowId);

}
