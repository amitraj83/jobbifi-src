package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Job;
import com.interview.framework.rmi.common.IJobStore;
import com.interview.services.Services;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class JobStore extends UnicastRemoteObject implements IJobStore {

  private static final long serialVersionUID = 1L;

  protected JobStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  @Override
  public Job getJob(String _id) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.JOB.DBCollection);
    DBObject query = new BasicDBObject();
    query.put("_id", new ObjectId(_id));
    DBObject row = collection.findOne(query);
    Job job = null;
    if (row != null) {
      job = new Job();
      job.setTitle(row.get(DATASTORES.JOB.TITLE).toString());
      job.setInterviewer(row.get(DATASTORES.JOB.INTERVIEWER).toString());
      job.setDescription(row.get(DATASTORES.JOB.DESCRIPTION).toString());
      job.setDt(new Date((Long) row.get(DATASTORES.JOB.DATE)).getTime());
      job.setStatus(new Integer(row.get(DATASTORES.JOB.STATUS).toString()));
      job.setCompanyName(row.get(DATASTORES.JOB.COMPANY_NAME).toString());
      job.setApplyUrl(row.get(DATASTORES.JOB.APPLY_URL).toString());
      job.setIndustry(row.get(DATASTORES.JOB.INDUSTRY).toString());
      job.setFile(row.get(DATASTORES.JOB.FILE).toString());
      if (row.get(DATASTORES.JOB.SALARY) != null) {
        job.setSalary(row.get(DATASTORES.JOB.SALARY).toString());
      }
      
      if (row.get(DATASTORES.JOB.LOCATION) != null) {
    	  job.setLocation(row.get(DATASTORES.JOB.LOCATION).toString());
      }
      if (row.get(DATASTORES.JOB.EXPERIENCE) != null) {
    	  job.setExperience(row.get(DATASTORES.JOB.EXPERIENCE).toString());
      }

      List<String> skills = new ArrayList<String>();
      BasicDBList listDBSkills = (BasicDBList) row.get(DATASTORES.JOB.SKILLS);
      Iterator<Object> it = listDBSkills.iterator();
      while (it.hasNext()) {
        skills.add(it.next().toString());
      }
      job.setSkills(skills);
      job.setId(row.get("_id").toString());
    }
    return job;
  }

  @Override
  public ObjectId saveJob(Job job) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.JOB.DBCollection);
    DBObject dbObject = new BasicDBObject();
    ObjectId _id = new ObjectId();
    dbObject.put("_id", _id);
    dbObject.put(DATASTORES.JOB.TITLE, job.getTitle());
    dbObject.put(DATASTORES.JOB.INTERVIEWER, job.getInterviewer());
    dbObject.put(DATASTORES.JOB.SKILLS, job.getSkills());
    dbObject.put(DATASTORES.JOB.DESCRIPTION, job.getDescription());
    dbObject.put(DATASTORES.JOB.DATE, job.getDt());
    dbObject.put(DATASTORES.JOB.STATUS, job.getStatus());
    dbObject.put(DATASTORES.JOB.SALARY, job.getSalary());
    dbObject.put(DATASTORES.JOB.COMPANY_NAME, job.getCompanyName());
    dbObject.put(DATASTORES.JOB.APPLY_URL, job.getApplyUrl());
    dbObject.put(DATASTORES.JOB.INDUSTRY, job.getIndustry());
    dbObject.put(DATASTORES.JOB.FILE, job.getFile());
    dbObject.put(DATASTORES.JOB.EXPERIENCE, job.getExperience());
    dbObject.put(DATASTORES.JOB.LOCATION, job.getLocation());
    WriteResult wr = collection.save(dbObject);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok()) {
      return _id;
    } else {
      return null;
    }
  }

  @Override
  public List<Job> getJobsOffered(String interviewer) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.JOB.DBCollection);
    List<Job> list = new ArrayList<Job>();
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.JOB.INTERVIEWER, interviewer);
    DBCursor cursor = collection.find(query).sort(new BasicDBObject(DATASTORES.JOB.DATE, -1));
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      Job job = new Job();
      job.setDescription(row.get(DATASTORES.JOB.DESCRIPTION).toString());
      job.setInterviewer(row.get(DATASTORES.JOB.INTERVIEWER).toString());


      List<String> skills = new ArrayList<String>();
      BasicDBList listDBSkills = (BasicDBList) row.get(DATASTORES.JOB.SKILLS);
      Iterator<Object> it = listDBSkills.iterator();
      while (it.hasNext()) {
        skills.add(it.next().toString());
      }
      job.setSkills(skills);
      job.setStatus(new Integer(row.get(DATASTORES.JOB.STATUS).toString()));
      job.setTitle(row.get(DATASTORES.JOB.TITLE).toString());
      job.setCompanyName(row.get(DATASTORES.JOB.COMPANY_NAME).toString());

      if (row.get(DATASTORES.JOB.APPLY_URL) != null) {
        job.setApplyUrl(row.get(DATASTORES.JOB.APPLY_URL).toString());
      }
      job.setDt(new Date((Long) row.get(DATASTORES.JOB.DATE)).getTime());
      job.setId(row.get("_id").toString());

      if (row.get(DATASTORES.JOB.FILE) != null) {
        job.setFile(row.get(DATASTORES.JOB.FILE).toString());
      }

      if (row.get(DATASTORES.JOB.SALARY) != null) {
        job.setSalary(row.get(DATASTORES.JOB.SALARY).toString());
      }

      if (row.get(DATASTORES.JOB.INDUSTRY) != null) {
        job.setIndustry(row.get(DATASTORES.JOB.INDUSTRY).toString());
      }
      
      if (row.get(DATASTORES.JOB.LOCATION) != null) {
    	  job.setLocation(row.get(DATASTORES.JOB.LOCATION).toString());
      }
      if (row.get(DATASTORES.JOB.EXPERIENCE) != null) {
    	  job.setExperience(row.get(DATASTORES.JOB.EXPERIENCE).toString());
      }
      
      list.add(job);
    }
    return list;
  }
}
