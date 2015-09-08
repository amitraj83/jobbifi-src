package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.JobApplication;
import com.interview.framework.pojo.UploadedFile;
import com.interview.rmi.DataStoreRegistry;

@Service
public class JobApplicationHandler extends RequestHandler {

	public JobApplicationHandler(){
		addHandler(this, REQUEST_TYPES.JOB_APPLICATION_REQ);
	}

	private static final Logger logger = Logger.getLogger(JobApplicationHandler.class); 
	
	@Override
	public Map<String, Object> handleRequest(Map<Object, Object> data) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
	    String SUB_REQ = data.get(REQUEST_TYPES.SUB_REQ).toString();
	    if (null != SUB_REQ && REQUEST_TYPES.JOB_APPLICATION_SUB_REQ.SAVE_APPLICATION.equals(SUB_REQ)) {
	      JobApplication jobApplication = (JobApplication) data.get("jobApplication");
	      try {
	    	  DataStoreRegistry.getInstance().getJobApplicationStore().saveJobApplication(jobApplication);
	    	  resMap.put("status", 1);
	      } catch (RemoteException e) {
	    	  logger.error("Exception thrown while saving the Job Application : ", e);
	    	  resMap.put("status", -1);
	      }
	    } else if (null != SUB_REQ && REQUEST_TYPES.JOB_APPLICATION_SUB_REQ.GET_APPLICATION_BY_JOB_AND_USER.equals(SUB_REQ)) {
	    	String userId = data.get(USER.USERNAME).toString();
	    	String jobId = data.get("jobId").toString();
	    	JobApplication jobApplication = null;
	    	 try {
	    		 List<JobApplication> jobApplications = DataStoreRegistry.getInstance().getJobApplicationStore()
	    				 .getJobApplicationsByJobIdAndUserId(jobId, userId);
	    		 if(jobApplications.size() > 0) {
	    			 UploadedFile uploadedFile = DataStoreRegistry.getInstance().getUploadedFileDataStore().getUploadedFile(new ObjectId(jobApplications.get(0).getJobId())); 
	    			 resMap.put("jobApplication", jobApplications.get(0));
	    			 resMap.put("uploadedFile", uploadedFile);
	    		 } else {
	    			 resMap.put("jobApplication", null);
	    		 }
			} catch (RemoteException e) {
				logger.error("Exception thrown while getting the Job Application : ", e);
				resMap.put("jobApplication", jobApplication);
			}	    	
	    } else if (null != SUB_REQ && REQUEST_TYPES.JOB_APPLICATION_SUB_REQ.UPDATE_APPLICATION_STATUS.equals(SUB_REQ)) {
	    	String id = (String) data.get(DATASTORES.JOB_APPLICATION.ID);
	    	String status = (String) data.get(DATASTORES.JOB_APPLICATION.STATUS);
	    	try {
				boolean result = DataStoreRegistry.getInstance().getJobApplicationStore().updateStatus(id, status);
				if(result){
					resMap.put("status", 1);
				}else {
					resMap.put("status", -1);
				}
			} catch (RemoteException e) {
				logger.error("Exception thrown while updating Job Application Status : ", e);
				resMap.put("status", -1);
			}	    	
	    }
	    return resMap;
	}
}
