package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Job;

public interface IJobStore extends Remote {

  String NAME = "jobstore";

  public ObjectId saveJob(Job job) throws RemoteException;

  public Job getJob(String _id) throws RemoteException;

  public List<Job> getJobsOffered(String interviewer) throws RemoteException;

  public void updateJob(String id, Job job) throws RemoteException;

}
