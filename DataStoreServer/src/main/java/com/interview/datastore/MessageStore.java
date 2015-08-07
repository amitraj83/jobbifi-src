package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Message;
import com.interview.framework.rmi.common.IMessageStore;
import com.interview.services.Services;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class MessageStore extends UnicastRemoteObject implements IMessageStore {
	
	private static final long serialVersionUID = 1L;
	
	protected MessageStore() throws RemoteException {
	    Services.getInstance().getRMIServer().bind(NAME, this);
	}

	public void saveMessage(Message msg) throws RemoteException {
		 DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.MESSAGE.Collection);
		 DBObject dbObject = new BasicDBObject();
		 dbObject.put(DATASTORES.MESSAGE.REF_ID, msg.getRefId());
		 dbObject.put(DATASTORES.MESSAGE.MESSAGE, msg.getMessage());
		 dbObject.put(DATASTORES.MESSAGE.FROM, msg.getFrom());
		 dbObject.put(DATASTORES.MESSAGE.TO, msg.getTo());
		 dbObject.put(DATASTORES.MESSAGE.REFENTITY, msg.getRefEntity());
		 dbObject.put(DATASTORES.MESSAGE.STATUS, msg.getStatus());
		 dbObject.put(DATASTORES.MESSAGE.PARENTMESSAGEID, msg.getParentMessageId());
		 dbObject.put(DATASTORES.MESSAGE.TITLE, msg.getTitle());
		 dbObject.put(DATASTORES.MESSAGE.CREATIONDATE, msg.getCreationDate());
		 dbObject.put(DATASTORES.MESSAGE.LASTREPLYTODATE, msg.getLastReplyToDate());
		 dbObject.put(DATASTORES.MESSAGE.TYPE, msg.getType());
		 WriteResult wr = collection.save(dbObject);
	}
	
	public void updateOriginalMessage(Message msg) throws RemoteException{
		 DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.MESSAGE.Collection);
		 DBObject query = new BasicDBObject();
		 query.put("_id",new ObjectId(msg.getParentMessageId()));
		 BasicDBObject updateDoc =
		 new BasicDBObject("$set", new BasicDBObject(DATASTORES.MESSAGE.LASTREPLYTODATE,  msg.getCreationDate()));
		 collection.update(query, updateDoc);
	}
	
	public List<Message> getMessages(Message message) throws RemoteException{
		  List<Message> list = new ArrayList<Message>();
		  DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.MESSAGE.Collection);
		  DBObject query1,query2,query3,query4,query5,query6;
		  query1= new BasicDBObject(DATASTORES.MESSAGE.REF_ID, message.getId());	
		  query2= new BasicDBObject(DATASTORES.MESSAGE.FROM, message.getFrom());	
		  query3= new BasicDBObject(DATASTORES.MESSAGE.TO,message.getTo());
		  query4= new BasicDBObject(DATASTORES.MESSAGE.FROM, message.getTo());	
		  query5= new BasicDBObject(DATASTORES.MESSAGE.TO,message.getFrom());
		  query6= new BasicDBObject(DATASTORES.MESSAGE.REFENTITY,message.getRefEntity());
		  BasicDBList andList1 = new BasicDBList();
		  andList1.add(query1);
		  andList1.add(query2);
		  andList1.add(query3);
		  andList1.add(query6);
		  BasicDBObject andQuery1 = new BasicDBObject("$and", andList1);
		  BasicDBList andList2 = new BasicDBList();
		  andList2.add(query1);
		  andList2.add(query4);
		  andList2.add(query5);
		  andList2.add(query6);
		  BasicDBObject andQuery2 = new BasicDBObject("$and", andList2);
		  BasicDBList orList = new BasicDBList();
		  orList.add(andQuery1);
		  orList.add(andQuery2);
		  BasicDBObject finalQuery = new BasicDBObject("$or", orList);	
		  DBCursor cursor = collection.find(finalQuery);
		  while(cursor.hasNext()) {
			  DBObject row = cursor.next();
			  Message msg=new Message();
			  msg.setFrom(row.get(DATASTORES.MESSAGE.FROM).toString());
			  msg.setTo(row.get(DATASTORES.MESSAGE.TO).toString());
			  msg.setMessage(row.get(DATASTORES.MESSAGE.MESSAGE).toString());
			  msg.setCreationDate(new Date((Long) row.get(DATASTORES.MESSAGE.CREATIONDATE)).getTime());
			  msg.setStatus(row.get(DATASTORES.MESSAGE.STATUS).toString());
			  msg.setParentMessageId(row.get(DATASTORES.MESSAGE.PARENTMESSAGEID).toString());
			  msg.setId(row.get(DATASTORES.MESSAGE.ID).toString());
			  msg.setRefEntity(row.get(DATASTORES.MESSAGE.REFENTITY).toString());
			  msg.setRefId(row.get(DATASTORES.MESSAGE.REF_ID).toString());
			  msg.setLastReplyToDate(new Date((Long) row.get(DATASTORES.MESSAGE.LASTREPLYTODATE)).getTime());
			  msg.setTitle(row.get(DATASTORES.MESSAGE.TITLE).toString());
			  list.add(msg);
		  }
		return list;
	}
	
	public List<Message> getMessage(Message message, int pageNum) throws RemoteException{
		  List<Message> list = new ArrayList<Message>();
		  DBCollection collection = Services.getInstance().getBaseDataStore()
				  .db.getCollection(DATASTORES.MESSAGE.Collection);
		  		 
		  DBObject query1 = new BasicDBObject(DATASTORES.MESSAGE.FROM,message.getFrom());
		  DBObject query2 = new BasicDBObject(DATASTORES.MESSAGE.TO,message.getFrom());
		  DBObject query3 = new BasicDBObject(DATASTORES.MESSAGE.TYPE,message.getType());
		  
		  BasicDBList orList = new BasicDBList();
		  orList.add(query1);
		  orList.add(query2);
		  BasicDBObject orQuery = new BasicDBObject("$or", orList);	
		  
		  BasicDBList andList = new BasicDBList();
		  andList.add(orQuery);
		  andList.add(query3);
		  
		  BasicDBObject finalQuery = new BasicDBObject("$and", andList);	
		  int documentsToSkip = pageNum > 0 ? (pageNum - 1)
					* (VARIABLES.TRNSACTION_PAGE_SIZE) : 0;
		  DBCursor cursor = collection.find(finalQuery).skip(documentsToSkip)
				  .limit(VARIABLES.TRNSACTION_PAGE_SIZE).sort(new BasicDBObject(DATASTORES.MESSAGE.LASTREPLYTODATE, -1));
		  while(cursor.hasNext()) {
			  DBObject row = cursor.next();
			  Message msg=new Message(); 
			  msg.setFrom(row.get(DATASTORES.MESSAGE.FROM).toString());
			  msg.setTo(row.get(DATASTORES.MESSAGE.TO).toString());
			  msg.setMessage(row.get(DATASTORES.MESSAGE.MESSAGE).toString());
			  msg.setCreationDate(new Date((Long) row.get(DATASTORES.MESSAGE.CREATIONDATE)).getTime());
			  msg.setStatus(row.get(DATASTORES.MESSAGE.STATUS).toString());
			  msg.setParentMessageId(row.get(DATASTORES.MESSAGE.PARENTMESSAGEID).toString());
			  msg.setId(row.get(DATASTORES.MESSAGE.ID).toString());
			  msg.setRefEntity(row.get(DATASTORES.MESSAGE.REFENTITY).toString());
			  msg.setRefId(row.get(DATASTORES.MESSAGE.REF_ID).toString());
			  msg.setLastReplyToDate(new Date((Long) row.get(DATASTORES.MESSAGE.LASTREPLYTODATE)).getTime());
			  msg.setTitle(row.get(DATASTORES.MESSAGE.TITLE).toString());
			  list.add(msg);
		  }
		  return list;
	}
		  
	public List<Message> getMessage(String  parentmessageid) throws RemoteException{
		  List<Message> list = new ArrayList<Message>();
		  DBCollection collection = Services.getInstance().getBaseDataStore()
				  .db.getCollection(DATASTORES.MESSAGE.Collection);
		  
		  DBObject query1 = new BasicDBObject(DATASTORES.MESSAGE.PARENTMESSAGEID,parentmessageid);
		  DBObject query2 =  new BasicDBObject(DATASTORES.MESSAGE.ID,new ObjectId(parentmessageid));
		  
		  BasicDBList orList = new BasicDBList();
		  orList.add(query1);
		  orList.add(query2);
		  
		  BasicDBObject orQuery = new BasicDBObject("$or", orList);	
		  DBCursor cursor = collection.find(orQuery);
		  
		  while(cursor.hasNext()) {
			  DBObject row = cursor.next();
			  Message msg = new Message(); 
			  msg.setFrom(row.get(DATASTORES.MESSAGE.FROM).toString());
			  msg.setTo(row.get(DATASTORES.MESSAGE.TO).toString());
			  msg.setMessage(row.get(DATASTORES.MESSAGE.MESSAGE).toString());
			  msg.setCreationDate(new Date((Long) row.get(DATASTORES.MESSAGE.CREATIONDATE)).getTime());
			  msg.setStatus(row.get(DATASTORES.MESSAGE.STATUS).toString());
			  msg.setParentMessageId(row.get(DATASTORES.MESSAGE.PARENTMESSAGEID).toString());
			  msg.setId(row.get(DATASTORES.MESSAGE.ID).toString());
			  msg.setRefEntity(row.get(DATASTORES.MESSAGE.REFENTITY).toString());
			  msg.setRefId(row.get(DATASTORES.MESSAGE.REF_ID).toString());
			  msg.setLastReplyToDate(new Date((Long) row.get(DATASTORES.MESSAGE.LASTREPLYTODATE)).getTime());
			  msg.setTitle(row.get(DATASTORES.MESSAGE.TITLE).toString());
			  list.add(msg);
		  }
		  return list;
	}
	
	public int getMessageCount(Message message) throws RemoteException{
		  DBCollection collection = Services.getInstance().getBaseDataStore()
				  .db.getCollection(DATASTORES.MESSAGE.Collection);
		  		 
		  DBObject query1 = new BasicDBObject(DATASTORES.MESSAGE.FROM,message.getFrom());
		  DBObject query2 = new BasicDBObject(DATASTORES.MESSAGE.TO,message.getFrom());
		  DBObject query3 = new BasicDBObject(DATASTORES.MESSAGE.TYPE,message.getType());
		  
		  BasicDBList orList = new BasicDBList();
		  orList.add(query1);
		  orList.add(query2);
		  
		  BasicDBObject orQuery = new BasicDBObject("$or", orList);	
		  BasicDBList andList = new BasicDBList();
		  andList.add(orQuery);
		  andList.add(query3);
		  
		  BasicDBObject finalQuery = new BasicDBObject("$and", andList);			  
		  int cursor = collection.find(finalQuery).count();
		  return cursor;
	}

	@Override
	public int getNewMessageCount(String username) throws RemoteException {
		DBCollection collection = Services.getInstance().getBaseDataStore()
				  .db.getCollection(DATASTORES.MESSAGE.Collection);
		DBObject query = new BasicDBObject(DATASTORES.MESSAGE.TO, username);
		query.put(DATASTORES.MESSAGE.STATUS, DATASTORES.MESSAGE.MESSAGE_STATUS.UNREAD);
		int cursor = collection.find(query).count();
		return cursor;
	}
	
	@Override
	public void changeMessageStatus(String id) throws RemoteException {
		 DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.MESSAGE.Collection);
		 DBObject query = new BasicDBObject();
		 query.put("_id",new ObjectId(id));
		 BasicDBObject updateDoc =
		 new BasicDBObject("$set", new BasicDBObject(DATASTORES.MESSAGE.STATUS, DATASTORES.MESSAGE.MESSAGE_STATUS.READ));
		 collection.update(query, updateDoc);
	}
}
