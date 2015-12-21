package com.interview.rmi;


import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class RmiDataStoreServer {

  private Registry registry = null;

  public Registry getRegistry() {
    return registry;
  }

  public void setRegistry(Registry registry) {
    this.registry = registry;
  }

  private String host;

  public RmiDataStoreServer(String host, int port) throws RemoteException {
    this.host = host;
    System.out.println("Datastore Server Running... at =" + host + ",port=" + port);
    // try{
    // registry = LocateRegistry.createRegistry(port);
    // //registry.rebind(host, this);
    // }
    // catch(RemoteException e){
    // System.out.println("remote exception"+ e);
    // }
  }

  public void bind(String name, Remote remote) {
    try {
      System.out.println(name + " - " + remote.toString());
      this.registry.bind(name, remote);
    } catch (AccessException e) {
      e.printStackTrace();
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (AlreadyBoundException e) {
      e.printStackTrace();
    }
  }


}
