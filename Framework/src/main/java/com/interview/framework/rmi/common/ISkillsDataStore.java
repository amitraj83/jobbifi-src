package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ISkillsDataStore extends Remote {

  String NAME = "skills";

  public List<String> getRelatedSkills(String searchterm) throws RemoteException;

}
