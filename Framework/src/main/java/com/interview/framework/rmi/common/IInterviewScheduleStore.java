package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.InterviewSchedule;

public interface IInterviewScheduleStore extends Remote {

  String NAME = "interviewschedule";

  public String insertSchedule(InterviewSchedule schedule) throws RemoteException;

  public InterviewSchedule getSchedule(String iid) throws RemoteException;

  public InterviewSchedule getSchedule(ObjectId scheduleId) throws RemoteException;

  public boolean updateSchedule(InterviewSchedule schedule) throws RemoteException;

}
