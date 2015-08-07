package com.interview.rmi;


import java.net.InetAddress;
import java.rmi.RemoteException;
import java.util.Map;

import com.interview.request.handlers.RequestHandler;
import com.interview.rmi.common.ReceiveMessageInterface;
import com.interview.services.Services;

public class RmiServer extends java.rmi.server.UnicastRemoteObject implements
    ReceiveMessageInterface {

  private static final long serialVersionUID = 8579173378023710633L;

  public RmiServer(String host) throws RemoteException {
    String address = "";
    try {
      address = (InetAddress.getLocalHost()).toString();
    } catch (Exception e) {
      System.out.println("can't get inet address.");
    }
    System.out.println("Backend Server Running... at =" + address);
    Services.getInstance().getRMIRegistry().bind(host, this);
  }

  public Map<String, Object> executeRequest(Map<Object, Object> req, String reqType)
      throws RemoteException {
    System.out.println("REceived at server : " + req + " reqType:" + reqType);

    RequestHandler handler = Services.getInstance().getRequestHandler(reqType);
    return handler.handleRequest(req);

  }

}
