package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Dispute;
import com.interview.framework.rmi.common.IDispute;
import com.interview.helper.MongoDataHelper;
import com.interview.services.Services;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class DisputeDataStore extends UnicastRemoteObject implements IDispute {

	private static final long serialVersionUID = 1L;

	protected DisputeDataStore() throws RemoteException {
		Services.getInstance().getRMIServer().bind(NAME, this);
	}	

  @Override
  public ObjectId createDispute(Dispute dispute) throws RemoteException {

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject dbObject = new BasicDBObject();
    ObjectId _id = new ObjectId();
    dbObject.put("_id", _id);
    dbObject.put(DATASTORES.DISPUTE.CREATEDBY, dispute.getCreatedBy());
    dbObject.put(DATASTORES.DISPUTE.IID, dispute.getIID());
    dbObject
        .put(DATASTORES.DISPUTE.INTERVIEW_ORIGINAL_STATUS, dispute.getInterviewOriginalStatus());
    dbObject.put(DATASTORES.DISPUTE.STATUS, dispute.getStatus());
    dbObject.put(DATASTORES.DISPUTE.TIME, dispute.getTime());
    dbObject.put(DATASTORES.DISPUTE.RESULT, DATASTORES.DISPUTE.RESULT_TYPE.PENDING);
    dbObject.put(DATASTORES.DISPUTE.DISPUTE_AMOUNT, dispute.getAmount());
    dbObject.put(DATASTORES.DISPUTE.VISIBLE_ID, dispute.getVisibleID());
    dbObject.put(DATASTORES.DISPUTE.WITH, dispute.getWith());
    dbObject.put(DATASTORES.DISPUTE.TIME_CLOSED, "0");

    WriteResult wr = collection.save(dbObject);
    CommandResult cr = wr.getLastError();
    if (cr.ok())
      return _id;
    else
      return null;
  }

  @Override
  public Dispute getDispute(String iid) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put(DATASTORES.DISPUTE.IID, iid);

    DBCursor cursor = collection.find(query);
    if (cursor.hasNext()) {
      DBObject row = cursor.next();
      Dispute dispute = new Dispute();
      dispute.setCreatedBy(row.get(DATASTORES.DISPUTE.CREATEDBY).toString());
      dispute.setId(row.get("_id").toString());
      dispute.setIID(row.get(DATASTORES.DISPUTE.IID).toString());
      dispute.setInterviewOriginalStatus(new Integer(row.get(
          DATASTORES.DISPUTE.INTERVIEW_ORIGINAL_STATUS).toString()));
      dispute.setStatus(row.get(DATASTORES.DISPUTE.STATUS).toString());
      dispute.setTime(new Long(row.get(DATASTORES.DISPUTE.TIME).toString()));
      dispute.setResult(row.get(DATASTORES.DISPUTE.RESULT).toString());
      dispute.setAmount(new Double(row.get(DATASTORES.DISPUTE.DISPUTE_AMOUNT).toString()));
      dispute.setClosedBy(MongoDataHelper.getStringValue(row, DATASTORES.DISPUTE.CLOSEDBY));
      if (row.get(DATASTORES.DISPUTE.VISIBLE_ID) != null)
        dispute.setVisibleID(row.get(DATASTORES.DISPUTE.VISIBLE_ID).toString());
      if (row.get(DATASTORES.DISPUTE.WITH) != null)
        dispute.setWith(row.get(DATASTORES.DISPUTE.WITH).toString());
      if (row.get(DATASTORES.DISPUTE.TIME_CLOSED) != null)
        dispute.setTimeclosed(new Long(row.get(DATASTORES.DISPUTE.TIME_CLOSED).toString()));

      return dispute;
    }
    return null;
  }

  @Override
  public Dispute getDispute(ObjectId _id) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put("_id", _id);

    DBCursor cursor = collection.find(query);
    if (cursor.hasNext()) {
      DBObject row = cursor.next();
      Dispute dispute = new Dispute();
      dispute.setCreatedBy(row.get(DATASTORES.DISPUTE.CREATEDBY).toString());
      dispute.setId(row.get("_id").toString());
      dispute.setIID(row.get(DATASTORES.DISPUTE.IID).toString());
      dispute.setInterviewOriginalStatus(new Integer(row.get(
          DATASTORES.DISPUTE.INTERVIEW_ORIGINAL_STATUS).toString()));
      dispute.setStatus(row.get(DATASTORES.DISPUTE.STATUS).toString());
      dispute.setTime(new Long(row.get(DATASTORES.DISPUTE.TIME).toString()));
      dispute.setResult(row.get(DATASTORES.DISPUTE.RESULT).toString());
      dispute.setAmount(new Double(row.get(DATASTORES.DISPUTE.DISPUTE_AMOUNT).toString()));
      dispute.setClosedBy(MongoDataHelper.getStringValue(row, DATASTORES.DISPUTE.CLOSEDBY));
      if (row.get(DATASTORES.DISPUTE.VISIBLE_ID) != null)
        dispute.setVisibleID(row.get(DATASTORES.DISPUTE.VISIBLE_ID).toString());
      if (row.get(DATASTORES.DISPUTE.WITH) != null)
        dispute.setWith(row.get(DATASTORES.DISPUTE.WITH).toString());
      if (row.get(DATASTORES.DISPUTE.TIME_CLOSED) != null)
        dispute.setTimeclosed(new Long(row.get(DATASTORES.DISPUTE.TIME_CLOSED).toString()));

      return dispute;
    }
    return null;
  }
  
  @Override
  public  List<Dispute>  getDisputelist(String userName) throws RemoteException {
	  
	  List<Dispute> disputes = new ArrayList<Dispute>();
	  
	  DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
	  DBObject query1 = new BasicDBObject(DATASTORES.DISPUTE.CREATEDBY, userName);
	  DBObject query2 = new BasicDBObject(DATASTORES.DISPUTE.WITH, userName);
	  DBObject query3 = new BasicDBObject(DATASTORES.DISPUTE.STATUS,  "OPEN");
	  
	  BasicDBList orList = new BasicDBList();
	  orList.add(query1);
	  orList.add(query2);
	  BasicDBObject orQuery = new BasicDBObject("$or", orList);	  
	  BasicDBList andList = new BasicDBList();
	  andList.add(orQuery);
	  andList.add(query3);
	  BasicDBObject finalQuery = new BasicDBObject("$and", andList);	  

	  DBCursor cursor = collection.find(finalQuery);
	    
	    while(cursor.hasNext()) {
	    	 DBObject row = cursor.next();
	    	 
	    	 Dispute dispute = new Dispute();
	    	 dispute.setCreatedBy(row.get(DATASTORES.DISPUTE.CREATEDBY).toString());
	         dispute.setId(row.get("_id").toString());
	         dispute.setIID(row.get(DATASTORES.DISPUTE.IID).toString());
	         dispute.setInterviewOriginalStatus(new Integer(row.get(
	             DATASTORES.DISPUTE.INTERVIEW_ORIGINAL_STATUS).toString()));
	         dispute.setStatus(row.get(DATASTORES.DISPUTE.STATUS).toString());
	         dispute.setTime(new Long(row.get(DATASTORES.DISPUTE.TIME).toString()));
	         dispute.setResult(row.get(DATASTORES.DISPUTE.RESULT).toString());
	         dispute.setAmount(new Double(row.get(DATASTORES.DISPUTE.DISPUTE_AMOUNT).toString()));
	         dispute.setClosedBy(MongoDataHelper.getStringValue(row, DATASTORES.DISPUTE.CLOSEDBY));
	         if (row.get(DATASTORES.DISPUTE.VISIBLE_ID) != null)
	           dispute.setVisibleID(row.get(DATASTORES.DISPUTE.VISIBLE_ID).toString());
	         
	         if (row.get(DATASTORES.DISPUTE.WITH) != null)
	           dispute.setWith(row.get(DATASTORES.DISPUTE.WITH).toString());
	         
	         if (row.get(DATASTORES.DISPUTE.TIME_CLOSED) != null)
	           dispute.setTimeclosed(new Long(row.get(DATASTORES.DISPUTE.TIME_CLOSED).toString()));
	         
	         disputes.add(dispute);	         
	    }
    return disputes;
  }
  
  
  @Override
  public  List<Dispute>  getClosedDisputelist(String userName) throws RemoteException {
	  
	  List<Dispute> disputes = new ArrayList<Dispute>();
	  
	  DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
	  DBObject query1 = new BasicDBObject(DATASTORES.DISPUTE.CREATEDBY, userName);
	  DBObject query2 = new BasicDBObject(DATASTORES.DISPUTE.WITH, userName);	
	  DBObject query3 = new BasicDBObject(DATASTORES.DISPUTE.STATUS, new BasicDBObject("$ne", "OPEN"));
	  
	  BasicDBList orList = new BasicDBList();
	  orList.add(query1);
	  orList.add(query2);
	  BasicDBObject orQuery = new BasicDBObject("$or", orList);	 
	  BasicDBList andList = new BasicDBList();
	  andList.add(orQuery);
	  andList.add(query3);
	  BasicDBObject finalQuery = new BasicDBObject("$and", andList);	 
	  DBCursor cursor = collection.find(finalQuery);
	    
	    while(cursor.hasNext()) {
	    	 DBObject row = cursor.next();
	    	 
	    	 Dispute dispute = new Dispute();
	    	 dispute.setCreatedBy(row.get(DATASTORES.DISPUTE.CREATEDBY).toString());
	         dispute.setId(row.get("_id").toString());
	         dispute.setIID(row.get(DATASTORES.DISPUTE.IID).toString());
	         dispute.setInterviewOriginalStatus(new Integer(row.get(
	             DATASTORES.DISPUTE.INTERVIEW_ORIGINAL_STATUS).toString()));
	         dispute.setStatus(row.get(DATASTORES.DISPUTE.STATUS).toString());
	         dispute.setTime(new Long(row.get(DATASTORES.DISPUTE.TIME).toString()));
	         dispute.setResult(row.get(DATASTORES.DISPUTE.RESULT).toString());
	         dispute.setAmount(new Double(row.get(DATASTORES.DISPUTE.DISPUTE_AMOUNT).toString()));
	         dispute.setClosedBy(MongoDataHelper.getStringValue(row, DATASTORES.DISPUTE.CLOSEDBY));
	         if (row.get(DATASTORES.DISPUTE.VISIBLE_ID) != null)
	           dispute.setVisibleID(row.get(DATASTORES.DISPUTE.VISIBLE_ID).toString());
	         
	         if (row.get(DATASTORES.DISPUTE.WITH) != null)
	           dispute.setWith(row.get(DATASTORES.DISPUTE.WITH).toString());
	         if (row.get(DATASTORES.DISPUTE.TIME_CLOSED) != null)
	           dispute.setTimeclosed(new Long(row.get(DATASTORES.DISPUTE.TIME_CLOSED).toString()));
	         
	         disputes.add(dispute);	         
	    }
    return disputes;
  }
  @Override
  public int closeDispute(ObjectId _id, String result) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject query = new BasicDBObject();
    query.put("_id", _id);
    DBObject updatedDoc =
        new BasicDBObject(DATASTORES.DISPUTE.STATUS, DATASTORES.DISPUTE.STATUS_TYPE.CLOSED);
    updatedDoc.put(DATASTORES.DISPUTE.RESULT, result);
    updatedDoc.put(DATASTORES.DISPUTE.TIME_CLOSED, new Date().getTime());
    updatedDoc.put(DATASTORES.DISPUTE.CLOSEDBY, new Date().getTime());
    BasicDBObject updateDoc = new BasicDBObject("$set", updatedDoc);

    WriteResult wr = collection.update(query, updateDoc);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok()) {
      return 1;
    } else
      return -1;
  }

  @Override
  public int closeDispute(ObjectId _id, String result, String closedBy) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    DBObject query = new BasicDBObject();
    query.put("_id", _id);
    DBObject updatedDoc =
        new BasicDBObject(DATASTORES.DISPUTE.STATUS, DATASTORES.DISPUTE.STATUS_TYPE.CLOSED);
    updatedDoc.put(DATASTORES.DISPUTE.RESULT, result);
    updatedDoc.put(DATASTORES.DISPUTE.TIME_CLOSED, new Date().getTime());
    updatedDoc.put(DATASTORES.DISPUTE.CLOSEDBY, closedBy);
    BasicDBObject updateDoc = new BasicDBObject("$set", updatedDoc);

    WriteResult wr = collection.update(query, updateDoc);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok()) {
      return 1;
    } else
      return -1;
  }
  @Override
  public List<Dispute> getAllDisputes(List<String> iids) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    List<Dispute> listOfDisputes = new ArrayList<Dispute>();


    BasicDBList list = new BasicDBList();
    if (iids.size() > 0) {
      BasicDBList orList = new BasicDBList();


      for (String iid : iids) {
        DBObject query2 = new BasicDBObject(DATASTORES.DISPUTE.IID, iid);
        orList.add(query2);
      }

      BasicDBObject query = new BasicDBObject("$or", orList);

      DBCursor cursor = collection.find(query).sort(new BasicDBObject(DATASTORES.DISPUTE.TIME, -1));
      while (cursor.hasNext()) {
        DBObject row = cursor.next();
        Dispute dispute = new Dispute();
        dispute.setCreatedBy(row.get(DATASTORES.DISPUTE.CREATEDBY).toString());
        dispute.setId(row.get("_id").toString());
        dispute.setIID(row.get(DATASTORES.DISPUTE.IID).toString());
        dispute.setInterviewOriginalStatus(new Integer(row.get(
            DATASTORES.DISPUTE.INTERVIEW_ORIGINAL_STATUS).toString()));
        dispute.setStatus(row.get(DATASTORES.DISPUTE.STATUS).toString());
        dispute.setTime(new Long(row.get(DATASTORES.DISPUTE.TIME).toString()));
        dispute.setResult(row.get(DATASTORES.DISPUTE.RESULT).toString());
        dispute.setAmount(new Double(row.get(DATASTORES.DISPUTE.DISPUTE_AMOUNT).toString()));
        if (row.get(DATASTORES.DISPUTE.VISIBLE_ID) != null)
          dispute.setVisibleID(row.get(DATASTORES.DISPUTE.VISIBLE_ID).toString());
        if (row.get(DATASTORES.DISPUTE.WITH) != null)
          dispute.setWith(row.get(DATASTORES.DISPUTE.WITH).toString());
        if (row.get(DATASTORES.DISPUTE.TIME_CLOSED) != null)
          dispute.setTimeclosed(new Long(row.get(DATASTORES.DISPUTE.TIME_CLOSED).toString()));

        listOfDisputes.add(dispute);
      }
    }
    return listOfDisputes;


  }


}
