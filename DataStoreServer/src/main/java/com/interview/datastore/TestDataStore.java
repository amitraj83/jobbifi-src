package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Test;
import com.interview.framework.rmi.common.ITestDataStore;
import com.interview.services.Services;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class TestDataStore extends UnicastRemoteObject implements ITestDataStore {

  private static final long serialVersionUID = 1L;

  protected TestDataStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  @Override
  public List<Test> getAllTests() throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.TEST.DBCollection);
    List<Test> list = new ArrayList<Test>();
    DBObject query = new BasicDBObject();
    DBCursor cursor =
        collection.find(query).sort(new BasicDBObject(DATASTORES.TEST.CREATED_DATE, -1));
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      if (row != null) {
        Test test = new Test();
        test.setId(row.get("_id").toString());
        test.setTitle(row.get(DATASTORES.TEST.TITLE).toString());
        test.setDescription(row.get(DATASTORES.TEST.DESCRIPTION).toString());
        test.setDifficultyLevel(
            Integer.parseInt(row.get(DATASTORES.TEST.DIFFICULTY_LEVEL).toString()));
        test.setDuration(Integer.parseInt(row.get(DATASTORES.TEST.DURATION).toString()));
        test.setNoOfQuestions(
            Integer.parseInt(row.get(DATASTORES.TEST.NO_OF_QUESTIONS).toString()));
        test.setCreatedDate(Long.parseLong(row.get(DATASTORES.TEST.CREATED_DATE).toString()));
        test.setPublish(Boolean.parseBoolean(row.get(DATASTORES.TEST.PUBLISH).toString()));
        List<String> skills = new ArrayList<String>();
        BasicDBList listDBSkills = (BasicDBList) row.get(DATASTORES.TEST.SKILLS);
        Iterator<Object> it = listDBSkills.iterator();
        while (it.hasNext()) {
          skills.add(it.next().toString());
        }
        test.setSkills(skills);
        list.add(test);
      }
    }
    return list;
  }

  @Override
  public List<Test> getAllPublishTests() throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.TEST.DBCollection);
    List<Test> list = new ArrayList<Test>();
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.TEST.PUBLISH, true);
    DBCursor cursor =
        collection.find(query).sort(new BasicDBObject(DATASTORES.TEST.CREATED_DATE, -1));
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      if (row != null) {
        Test test = new Test();
        test.setId(row.get("_id").toString());
        test.setTitle(row.get(DATASTORES.TEST.TITLE).toString());
        test.setDescription(row.get(DATASTORES.TEST.DESCRIPTION).toString());
        test.setDifficultyLevel(
            Integer.parseInt(row.get(DATASTORES.TEST.DIFFICULTY_LEVEL).toString()));
        test.setDuration(Integer.parseInt(row.get(DATASTORES.TEST.DURATION).toString()));
        test.setNoOfQuestions(
            Integer.parseInt(row.get(DATASTORES.TEST.NO_OF_QUESTIONS).toString()));
        test.setCreatedDate(Long.parseLong(row.get(DATASTORES.TEST.CREATED_DATE).toString()));
        test.setPublish(Boolean.parseBoolean(row.get(DATASTORES.TEST.PUBLISH).toString()));
        List<String> skills = new ArrayList<String>();
        BasicDBList listDBSkills = (BasicDBList) row.get(DATASTORES.TEST.SKILLS);
        Iterator<Object> it = listDBSkills.iterator();
        while (it.hasNext()) {
          skills.add(it.next().toString());
        }
        test.setSkills(skills);
        list.add(test);
      }
    }
    return list;
  }

  @Override
  public ObjectId saveTest(Test test) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.TEST.DBCollection);
    DBObject dbObject = new BasicDBObject();
    ObjectId _id = new ObjectId();
    dbObject.put("_id", _id);
    dbObject.put(DATASTORES.TEST.TITLE, test.getTitle());
    dbObject.put(DATASTORES.TEST.DESCRIPTION, test.getDescription());
    dbObject.put(DATASTORES.TEST.SKILLS, test.getSkills());
    dbObject.put(DATASTORES.TEST.DIFFICULTY_LEVEL, test.getDifficultyLevel());
    dbObject.put(DATASTORES.TEST.CREATED_DATE, test.getCreatedDate());
    dbObject.put(DATASTORES.TEST.DURATION, test.getDuration());
    dbObject.put(DATASTORES.TEST.NO_OF_QUESTIONS, test.getNoOfQuestions());
    dbObject.put(DATASTORES.TEST.PUBLISH, test.isPublish());
    CommandResult cr = collection.save(dbObject).getCachedLastError();
    if (cr.ok()) {
      return _id;
    } else {
      return null;
    }
  }

  public Test getTest(String id) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.TEST.DBCollection);
    DBObject query = new BasicDBObject();
    query.put("_id", new ObjectId(id));
    DBObject row = collection.findOne(query);
    if (row != null) {
      Test test = new Test();
      test.setId(row.get("_id").toString());
      test.setTitle(row.get(DATASTORES.TEST.TITLE).toString());
      test.setDescription(row.get(DATASTORES.TEST.DESCRIPTION).toString());
      test.setDifficultyLevel(
          Integer.parseInt(row.get(DATASTORES.TEST.DIFFICULTY_LEVEL).toString()));
      test.setDuration(Integer.parseInt(row.get(DATASTORES.TEST.DURATION).toString()));
      test.setNoOfQuestions(Integer.parseInt(row.get(DATASTORES.TEST.NO_OF_QUESTIONS).toString()));
      test.setCreatedDate(Long.parseLong(row.get(DATASTORES.TEST.CREATED_DATE).toString()));
      test.setPublish(Boolean.parseBoolean(row.get(DATASTORES.TEST.PUBLISH).toString()));
      List<String> skills = new ArrayList<String>();
      BasicDBList listDBSkills = (BasicDBList) row.get(DATASTORES.TEST.SKILLS);
      Iterator<Object> it = listDBSkills.iterator();
      while (it.hasNext()) {
        skills.add(it.next().toString());
      }
      test.setSkills(skills);
      return test;
    } else {
      return null;
    }
  }

  @Override
  public int deleteTest(String id) throws RemoteException {
    DBCollection dbCollection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.TEST.DBCollection);
    DBObject query = new BasicDBObject();
    query.put("_id", new ObjectId(id));
    CommandResult cr = dbCollection.remove(query).getCachedLastError();
    if (cr.ok()) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public int publishTest(String id) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.TEST.DBCollection);
    DBObject query = new BasicDBObject();
    query.put("_id", new ObjectId(id));
    BasicDBObject updateDoc =
        new BasicDBObject("$set", new BasicDBObject(DATASTORES.TEST.PUBLISH, true));
    CommandResult cr = collection.update(query, updateDoc).getCachedLastError();
    if (cr.ok()) {
      return 1;
    } else {
      return 0;
    }
  }
}
