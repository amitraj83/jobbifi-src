package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.interview.framework.pojo.Interviewer;
import com.mongodb.DBObject;

public interface IInterviewerDataStore extends Remote {

  String NAME = "interviewer";

  public void insertInterviewer(Map<String, String> interviewerMap) throws RemoteException;

  public int insertInterviewer(Interviewer interviewer) throws RemoteException;

  public boolean updateUserInfo(Interviewer interviewer) throws RemoteException;

  public int insertInterviewer(String interviewerJSON) throws RemoteException;

  public boolean setPasswordForUserName(String username, String pass) throws RemoteException;

  public boolean isExist(DBObject data) throws RemoteException;

  public String getType(String username) throws RemoteException;

  public Map<String, Object> getUserInfo(String usernameOrUserEmail) throws RemoteException;

  public Map<String, Object> getUserExternalInfo(String username) throws RemoteException;

  public Map<String, Object> getUserCredentials(String emailID) throws RemoteException;

  public double updateBalance(String interviewer, double amount, int OPTION) throws RemoteException;

  public void setBalance(String interviewer, double amount) throws RemoteException;

  public void setRating(String user, double currentAvgRating) throws RemoteException;

  public Map<String, Object> getAdditionalData(Map<Object, Object> users) throws RemoteException;

  public List<String> getMatchingUsersList(List<String> skills, List<String> companies,
      String country) throws RemoteException;

  public String getUserEmail(String username) throws RemoteException;
  
  public Map<String,String> getEmailListFromUsersList(List<String> users) throws RemoteException;

  public boolean isUserEmailExist(String emailAddress) throws RemoteException;

  public boolean isUserNameExist(String username) throws RemoteException;

  public Map<String, String> getPics(Collection<String> usernames) throws RemoteException;

  public void updateProfilePic(String interviewer, String profilePic) throws RemoteException;
  
  public String getUserPassword(String username) throws RemoteException; 
  
  public void updateUserPaypaladdress(String username,String paypaladdress) throws RemoteException;
  
  public List<Object>  getTopAdvisorList(int noOfResult) throws RemoteException;
  
  
}
