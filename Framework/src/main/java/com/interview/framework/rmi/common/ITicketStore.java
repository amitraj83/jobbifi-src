package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.interview.framework.pojo.Ticket;

public interface ITicketStore extends Remote {

  String NAME = "tickets";

  public String save(Ticket ticket) throws RemoteException;

}
