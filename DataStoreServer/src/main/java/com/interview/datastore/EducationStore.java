package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Education;
import com.interview.framework.rmi.common.IEducationStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class EducationStore extends UnicastRemoteObject implements IEducationStore {

  /**
   * 
   */
  private static final long serialVersionUID = -8975036524312082663L;

  protected EducationStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  @Override
  public Education getEducation(ObjectId id) throws RemoteException {

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject query = new BasicDBObject();
    query.put("_id", id);
    Education education = new Education();
    DBObject row = collection.findOne(query);
    if (row != null) {
      education.set_id(row.get("_id").toString());
      education.setDegree(row.get(DATASTORES.EDUCATION.DEGREE).toString());
      education.setEndYear(new Integer(row.get(DATASTORES.EDUCATION.ENDYEAR).toString()));
      education.setFieldOfStudy(row.get(DATASTORES.EDUCATION.FIELDOFSTUDY).toString());
      education.setSchoolname(row.get(DATASTORES.EDUCATION.SCHOOLNAME).toString());
      education.setStartYear(new Integer(row.get(DATASTORES.EDUCATION.STARTYEAR).toString()));
      return education;
    } else
      return null;
  }

  @Override
  public ObjectId insert(Education education) throws RemoteException {

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject dbObject = new BasicDBObject();
    ObjectId _id = new ObjectId();
    dbObject.put("_id", _id);
    dbObject.put(DATASTORES.EDUCATION.DEGREE, education.getDegree());
    dbObject.put(DATASTORES.EDUCATION.ENDYEAR, education.getEndYear());
    dbObject.put(DATASTORES.EDUCATION.FIELDOFSTUDY, education.getFieldOfStudy());
    dbObject.put(DATASTORES.EDUCATION.SCHOOLNAME, education.getSchoolname());
    dbObject.put(DATASTORES.EDUCATION.STARTYEAR, education.getStartYear());

    CommandResult cr = collection.save(dbObject).getCachedLastError();
    if (cr.ok())
      return _id;
    else
      return null;
  }

  public void deleteEducation(ObjectId id) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    BasicDBObject query = new BasicDBObject();
    query.put("_id", id);
    collection.remove(query);
  }

}
