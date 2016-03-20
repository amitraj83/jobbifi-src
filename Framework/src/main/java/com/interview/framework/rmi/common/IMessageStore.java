package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.interview.framework.pojo.Message;

public interface IMessageStore extends Remote {

  String NAME = "messageStore";

  public void saveMessage(Message msg) throws RemoteException;

  public List<Message> getMessages(Message message) throws RemoteException;

  public List<Message> getMessage(Message message, int pageNum) throws RemoteException;

  public List<Message> getMessage1(Message message, int pageNum) throws RemoteException;

  public List<Message> getChatMessages(String user1, String user2) throws RemoteException;

  public List<Message> getMessage(String parentmessageid) throws RemoteException;

  public void updateOriginalMessage(Message msg) throws RemoteException;

  public int getMessageCount(Message message) throws RemoteException;

  public int getNewMessageCount(String username) throws RemoteException;

  public void changeMessageStatus(String id) throws RemoteException;
  
  public long getConsultationsCount() throws RemoteException;
}
