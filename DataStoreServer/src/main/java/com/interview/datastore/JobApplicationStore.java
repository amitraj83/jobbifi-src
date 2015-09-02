package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.JobApplication;
import com.interview.framework.rmi.common.IJobApplicationStore;
import com.interview.helper.MongoDataHelper;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class JobApplicationStore extends UnicastRemoteObject implements IJobApplicationStore {

	private static final long serialVersionUID = 1L;

	protected JobApplicationStore() throws RemoteException {
		Services.getInstance().getRMIServer().bind(NAME, this);
	}

	@Override
	public ObjectId saveJobApplication(JobApplication jobApplication)
			throws RemoteException {
		 DBCollection collection =
			        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.JOB_APPLICATION.DBCollection);
	    DBObject dbObject = new BasicDBObject();
	    ObjectId _id = new ObjectId();
	    dbObject.put(DATASTORES.JOB_APPLICATION.ID, _id);
	    dbObject.put(DATASTORES.JOB_APPLICATION.COVER_LETTER, jobApplication.getCoverLetter());
	    dbObject.put(DATASTORES.JOB_APPLICATION.CV_FILE_ID, jobApplication.getCvFileId());
	    dbObject.put(DATASTORES.JOB_APPLICATION.DATE, jobApplication.getDt());
	    dbObject.put(DATASTORES.JOB_APPLICATION.JOB_ID, jobApplication.getJobId());
	    dbObject.put(DATASTORES.JOB_APPLICATION.APPLICANT_ID, jobApplication.getApplicantId());
	    dbObject.put(DATASTORES.JOB_APPLICATION.STATUS, jobApplication.getStatus());
	    WriteResult wr = collection.save(dbObject);
	    CommandResult cr = wr.getCachedLastError();
	    if (cr.ok()) {
	      return _id;
	    } else {
	      return null;
	    }
	}

	@Override
	public JobApplication getJobApplication(String _id) throws RemoteException {
		  DBCollection collection =
		        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.JOB_APPLICATION.DBCollection);
		  DBObject query = new BasicDBObject();
		  query.put(DATASTORES.JOB_APPLICATION.ID, new ObjectId(_id));
		  DBObject row = collection.findOne(query);
		  JobApplication jobApplication = null;
		  if (row != null) {
		      jobApplication = new JobApplication();
		      jobApplication.setId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.ID));
		      jobApplication.setCoverLetter(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.COVER_LETTER));
		      jobApplication.setCvFileId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.CV_FILE_ID));
		      jobApplication.setJobId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.JOB_ID));
		      jobApplication.setApplicantId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.APPLICANT_ID));
		      jobApplication.setDt(MongoDataHelper.getLong(row, DATASTORES.JOB_APPLICATION.DATE));
		      jobApplication.setStatus(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.STATUS));
		  }
		return jobApplication;
	}

	@Override
	public List<JobApplication> getJobApplicationsByJobId(String jobId)
			throws RemoteException {
		
		DBCollection collection = Services.getInstance().getBaseDataStore().db.
				getCollection(DATASTORES.JOB_APPLICATION.DBCollection);
		List<JobApplication> jobApplications = new ArrayList<JobApplication>();	
		  DBObject query = new BasicDBObject();
		  query.put(DATASTORES.JOB_APPLICATION.JOB_ID, jobId);
		  DBCursor cursor = collection.find(query);
		  while (cursor.hasNext()) {
			  DBObject row = cursor.next();
			  JobApplication jobApplication = new JobApplication();
		      jobApplication.setId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.ID));
		      jobApplication.setCoverLetter(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.COVER_LETTER));
		      jobApplication.setCvFileId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.CV_FILE_ID));
		      jobApplication.setJobId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.JOB_ID));
		      jobApplication.setApplicantId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.APPLICANT_ID));
		      jobApplication.setDt(MongoDataHelper.getLong(row, DATASTORES.JOB_APPLICATION.DATE));
		      jobApplication.setStatus(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.STATUS));
		      jobApplications.add(jobApplication);
		  }
		return jobApplications;
	}

	@Override
	public List<JobApplication> getJobApplicationsByJobIdAndUserId(String jobId,
			String userId) throws RemoteException {
		DBCollection collection = Services.getInstance().getBaseDataStore().db.
				getCollection(DATASTORES.JOB_APPLICATION.DBCollection);
		List<JobApplication> jobApplications = new ArrayList<JobApplication>();	
		  DBObject query = new BasicDBObject();
		  query.put(DATASTORES.JOB_APPLICATION.JOB_ID, jobId);
		  query.put(DATASTORES.JOB_APPLICATION.APPLICANT_ID, userId);
		  DBCursor cursor = collection.find(query);
		  while (cursor.hasNext()) {
			  DBObject row = cursor.next();
			  JobApplication jobApplication = new JobApplication();
		      jobApplication.setId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.ID));
		      jobApplication.setCoverLetter(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.COVER_LETTER));
		      jobApplication.setCvFileId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.CV_FILE_ID));
		      jobApplication.setJobId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.JOB_ID));
		      jobApplication.setApplicantId(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.APPLICANT_ID));
		      jobApplication.setDt(MongoDataHelper.getLong(row, DATASTORES.JOB_APPLICATION.DATE));
		      jobApplication.setStatus(MongoDataHelper.getStringValue(row, DATASTORES.JOB_APPLICATION.STATUS));
		      jobApplications.add(jobApplication);
		  }
		return jobApplications;
	}	
}