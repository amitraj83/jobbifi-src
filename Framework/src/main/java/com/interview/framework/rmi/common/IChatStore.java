package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.interview.framework.chat.ChatMessage;

public interface IChatStore extends Remote {

  String NAME = "chatstore";

  public int saveChatMessage(ChatMessage message) throws RemoteException;

  public List<ChatMessage> getChatHistory(String user, String otheruser, int max)
      throws RemoteException;

  public long getOfflineCount(String username) throws RemoteException;
}
