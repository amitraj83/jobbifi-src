package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Bid;

public interface IBidStore extends Remote {

  String NAME = "bid";

  public ObjectId saveBid(Bid bid) throws RemoteException;

  public boolean deleteBid(ObjectId _id) throws RemoteException;

  public List<Bid> getBidsIPosted(String username) throws RemoteException;

  public List<Bid> getBidsReceivedForInterview(String _id) throws RemoteException;

  public void updateBidStatus(ObjectId bid, int bidStatus) throws RemoteException;

  public List<String> getAllBidIDsForInterview(ObjectId iid) throws RemoteException;

  public List<String> getAllBidIDsForInterview(String iid) throws RemoteException;

  public ObjectId getInterviewId(ObjectId _id) throws RemoteException;

  public boolean deleteAssociatedBidsOfInterview(ObjectId iid) throws RemoteException;

  public Bid getBid(ObjectId bidId) throws RemoteException;

  public Bid getAcceptedBidForInterview(String iid) throws RemoteException;

  public int getBidCount(String iid) throws RemoteException;

  public Bid getBidByBidderAndInterview(String bidder, String iid) throws RemoteException;
  
  public List<Bid> getBidsIPosted(String username,int pageNumber) throws RemoteException;
  
  public int getBidsIPostedCount(String username) throws RemoteException;
 
  public List<Bid> getBidsForInterviewsByUser(String username, Collection<String> iids) throws RemoteException;
  
}
