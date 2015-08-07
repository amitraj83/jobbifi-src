package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Skill;

public interface ISkillDataStore extends Remote {

  String NAME = "skill";

  public ObjectId insert(Skill skill) throws RemoteException;

  public Skill getSkill(ObjectId _id) throws RemoteException;

  public void deleteSkill(ObjectId id) throws RemoteException;

}
