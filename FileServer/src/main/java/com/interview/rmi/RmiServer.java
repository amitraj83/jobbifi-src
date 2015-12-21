package com.interview.rmi;


import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import com.interview.request.handlers.RequestHandler;
import com.interview.rmi.common.ReceiveMessageInterface;
import com.interview.services.Services;

public class RmiServer extends java.rmi.server.UnicastRemoteObject
    implements ReceiveMessageInterface {

  /**
   * 
   */
  private static final long serialVersionUID = -7930656332830183067L;



  public RmiServer(String host, int port) throws RemoteException {
    // String name = GlobalProperties.properties.getProperty("rmiServerName");
    // int port = Integer.parseInt(GlobalProperties.properties.getProperty("rmiport"));
    String address = "";
    try {
      address = (InetAddress.getLocalHost()).toString();
    } catch (Exception e) {
      System.out.println("can't get inet address.");
    }
    System.out.println("FileServer Server Running... at =" + address + ",port=" + port);
    try {
      Registry registry = LocateRegistry.createRegistry(port);
      registry.rebind(host, this);
    } catch (RemoteException e) {
      System.out.println("remote exception" + e);
    }
  }



  public Map<String, Object> executeRequest(Map<Object, Object> req, String reqType)
      throws RemoteException {
    Map<String, String> map = new HashMap<String, String>();
    System.out.println("REceived at File server : " + req);
    RequestHandler handler = Services.getInstance().getRequestHandler(reqType);
    return handler.handleRequest(req);

  }

}
