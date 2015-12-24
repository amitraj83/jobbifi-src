package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Notification;

public interface INotificationStore extends Remote {

  String NAME = "notification";

  public ObjectId save(Notification notif) throws RemoteException;

  public List<Notification> getUnReadNotification(String user, int max) throws RemoteException;

  public List<Notification> getAllLatestNotification(String user, int max) throws RemoteException;

  public Map<String, List<Notification>> getNotifications(List<String> users)
      throws RemoteException;



}
