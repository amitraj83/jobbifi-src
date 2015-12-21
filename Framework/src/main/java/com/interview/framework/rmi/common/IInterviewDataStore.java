package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;

public interface IInterviewDataStore extends Remote {

  String NAME = "interview";

  public ObjectId saveInterview(Interview interview) throws RemoteException;

  public List<Interview> getInterviews(String user, String role, int pagenum, int status)
      throws RemoteException;

  public List<Interview> getInterviews(String user, String role) throws RemoteException;

  public List<Interview> getDisputableList(String user, String role) throws RemoteException;

  public List<Interview> getInterviewsWhereIBid(String user) throws RemoteException;

  public Interview getInterview(String _id) throws RemoteException;

  public Bid getAcceptedBidForInterview(ObjectId iid) throws RemoteException;

  public Interview getInterview(ObjectId _id) throws RemoteException;

  public void updateInterviewStatus(ObjectId _id, int inprogress) throws RemoteException;

  public void depositEscrowBalance(ObjectId _id, double amount) throws RemoteException;

  public void withdrawFromEscrow(ObjectId _id, double amount) throws RemoteException;

  public boolean deleteInterivew(ObjectId iid) throws RemoteException;

  public void updateInterview(ObjectId id, Map<String, Object> changes) throws RemoteException;

  public Map<String, String> getInterviewsStatuses(Collection<String> iids) throws RemoteException;

  public Map<String, String> getInterviewsFiles(Collection<String> iids) throws RemoteException;

  public int getTotalinterviewcount(String user, String role, int status) throws RemoteException;

  public List<Interview> getInterviewsWhereIBid(String user, int pageNumber, int status)
      throws RemoteException;

  public Interview getInterview(String _id, int pageNumber, int status) throws RemoteException;

  public List<Interview> getAwardedInterview(String user, int pageNum) throws RemoteException;

  public int getTotalAwardedInterviewCount(String User) throws RemoteException;
}
