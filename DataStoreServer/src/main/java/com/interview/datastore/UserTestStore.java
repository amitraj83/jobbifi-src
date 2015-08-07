package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.UserTest;
import com.interview.framework.rmi.common.IUserTestStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserTestStore extends UnicastRemoteObject implements IUserTestStore {

	private static final long serialVersionUID = 1L;

	protected UserTestStore() throws RemoteException {
		 Services.getInstance().getRMIServer().bind(NAME, this);		
	}

	@Override
	public List<UserTest> getAllUserTests() throws RemoteException {
		DBCollection collection = Services.getInstance().getBaseDataStore().db
				 .getCollection(DATASTORES.USER_TEST.DBCollection);
	    List<UserTest> list = new ArrayList<UserTest>();
	    DBObject query = new BasicDBObject();	   
	    DBCursor cursor = collection.find(query);
	    while (cursor.hasNext()) {
	      DBObject row = cursor.next();
	      if(row != null) {
	    	  UserTest userTest = new UserTest();
	    	  userTest.setId(row.get(DATASTORES.USER_TEST.ID).toString());
	    	  userTest.setTestId(row.get(DATASTORES.USER_TEST.TEST_ID).toString());
	    	  userTest.setUserId(row.get(DATASTORES.USER_TEST.USER_ID).toString());
	    	  userTest.setDuration(Integer.parseInt(row.get(DATASTORES.USER_TEST.ID).toString()));
	    	  userTest.setStartTime(new Long(row.get(DATASTORES.USER_TEST.ID).toString()));
	    	  userTest.setEndTime(new Long(row.get(DATASTORES.USER_TEST.ID).toString()));
	    	  userTest.setMarksPerQuestion(new Double(row.get(DATASTORES.USER_TEST.ID).toString()));
	    	  userTest.setMarksObtained(new Double(row.get(DATASTORES.USER_TEST.ID).toString()));
		      list.add(userTest);
	      }
	    }
		return list;
	}

	@Override
	public UserTest getUserTest(String id) throws RemoteException {
		DBCollection collection = Services.getInstance().getBaseDataStore().db
		    		.getCollection(DATASTORES.USER_TEST.DBCollection);
	    DBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id));	    
	    DBObject row = collection.findOne(query);
	    if(row != null) {
    		  UserTest userTest = new UserTest();
	    	  userTest.setId(row.get(DATASTORES.USER_TEST.ID).toString());
	    	  userTest.setTestId(row.get(DATASTORES.USER_TEST.TEST_ID).toString());
	    	  userTest.setUserId(row.get(DATASTORES.USER_TEST.USER_ID).toString());
	    	  userTest.setDuration(Integer.parseInt(row.get(DATASTORES.USER_TEST.ID).toString()));
	    	  userTest.setStartTime(new Long(row.get(DATASTORES.USER_TEST.ID).toString()));
	    	  userTest.setEndTime(new Long(row.get(DATASTORES.USER_TEST.ID).toString()));
	    	  userTest.setMarksPerQuestion(new Double(row.get(DATASTORES.USER_TEST.ID).toString()));
	    	  userTest.setMarksObtained(new Double(row.get(DATASTORES.USER_TEST.ID).toString()));
		      return userTest;
	    } else {
	    	return null;
	    }
	}

	@Override
	public ObjectId saveUserTest(UserTest userTest) throws RemoteException {
		DBCollection collection = Services.getInstance().getBaseDataStore().db
				.getCollection(DATASTORES.USER_TEST.DBCollection);
	    DBObject dbObject = new BasicDBObject();
	    ObjectId _id = new ObjectId();
	    dbObject.put(DATASTORES.USER_TEST.ID, _id);
	    dbObject.put(DATASTORES.USER_TEST.USER_ID, userTest.getUserId());
	    dbObject.put(DATASTORES.USER_TEST.TEST_ID, userTest.getTestId());
	    dbObject.put(DATASTORES.USER_TEST.START_TIME, userTest.getStartTime());
	    dbObject.put(DATASTORES.USER_TEST.END_TIME, userTest.getEndTime());
	    dbObject.put(DATASTORES.USER_TEST.DURATION, userTest.getDuration());
	    dbObject.put(DATASTORES.USER_TEST.MARKS_PER_QUESTION, userTest.getMarksPerQuestion());
	    dbObject.put(DATASTORES.USER_TEST.MARKS_OBTAINED, userTest.getMarksObtained());	    
	    CommandResult cr = collection.save(dbObject).getCachedLastError();
	    if (cr.ok()) {
	      return _id;
	    } else {
	      return null;
	    }		
	}
}