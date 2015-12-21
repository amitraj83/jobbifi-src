package com.interview.rmi;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;



public class RMIRegistry {

  private Registry registry;

  public Registry getRegistry() {
    return registry;
  }

  public void setRegistry(Registry registry) {
    this.registry = registry;
  }

  public RMIRegistry(int port) {
    // try {
    // this.registry = LocateRegistry.createRegistry(port);
    // } catch (RemoteException e) {
    // e.printStackTrace();
    // }
  }

  public void bind(String name, Remote remote) {
    try {
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
