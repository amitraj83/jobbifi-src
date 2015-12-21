package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.FIRST_REQUESTS;

public interface IRequestDataStore extends Remote {

  String NAME = "requests";

  public Map<String, Object> registerRequest(String interviewerUsername, String intervieweeUsername)
      throws RemoteException;

  public Map<String, Object> retrieveAllFirstRequestsForUsername(String username)
      throws RemoteException;

  public int updateStatus(ObjectId id, FIRST_REQUESTS.STATUSES status) throws RemoteException;

  public int updateStatus(String id, FIRST_REQUESTS.STATUSES status) throws RemoteException;

  public List<String> getAcceptedFriends(String user) throws RemoteException;

  public Map<String, String> getUsersOfRequestId(String rid) throws RemoteException;

}
