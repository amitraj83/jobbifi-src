package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.JobApplication;

public interface IJobApplicationStore extends Remote {
	  
	String NAME = "jobapplicationstore";
	  
	public ObjectId saveJobApplication(JobApplication jobApplication) throws RemoteException;

	public JobApplication getJobApplication(String _id) throws RemoteException;

	public List<JobApplication> getJobApplicationsByJobId(String jobId) throws RemoteException;
	
	public List<JobApplication> getJobApplicationsByJobIdAndUserId(String jobId, String userId) throws RemoteException;

}
