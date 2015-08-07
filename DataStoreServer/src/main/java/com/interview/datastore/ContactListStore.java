package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;



import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.ContactList;
import com.interview.framework.rmi.common.IContactListStore;
import com.interview.services.Services;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class ContactListStore extends UnicastRemoteObject implements IContactListStore{
	
	private static final long serialVersionUID = 1L;

	  public ContactListStore() throws RemoteException {
	    Services.getInstance().getRMIServer().bind(NAME, this);
	  }
	public void saveContact(ContactList contactlist) throws RemoteException{
		DBCollection collection =
		        Services.getInstance().getBaseDataStore().db
		            .getCollection(DATASTORES.CONTACT_LIST.COLLECTION);
		DBObject query1,query2,query3,query4;
		query1= new BasicDBObject(DATASTORES.CONTACT_LIST.USER, contactlist.getUser());	
		query2= new BasicDBObject(DATASTORES.CONTACT_LIST.CONTACT, contactlist.getUser());
		query3= new BasicDBObject(DATASTORES.CONTACT_LIST.USER, contactlist.getContact());	
		query4= new BasicDBObject(DATASTORES.CONTACT_LIST.CONTACT, contactlist.getContact());
		BasicDBList orList = new BasicDBList();
		  orList.add(query1);
		  orList.add(query2);
		  BasicDBObject finalQuery1 = new BasicDBObject("$or", orList);	
		  BasicDBList orList1 = new BasicDBList();
		  orList1.add(query3);
		  orList1.add(query4);
		  BasicDBObject finalQuery2 = new BasicDBObject("$or", orList1);
		BasicDBList andList =new BasicDBList();
		andList.add(finalQuery2);
		andList.add(finalQuery1);
		BasicDBObject finalQuery = new BasicDBObject("$and", andList);
		  int cursor = collection.find(finalQuery).count();
		  if(cursor ==0){
			  DBObject dbObject = new BasicDBObject();
			  dbObject.put(DATASTORES.CONTACT_LIST.USER, contactlist.getUser());
				 dbObject.put(DATASTORES.CONTACT_LIST.CONTACT,contactlist.getContact());
				 WriteResult wr = collection.save(dbObject);
		  }
	}
	public List<ContactList> getContactList(String user) throws RemoteException{
		DBCollection collection =
		        Services.getInstance().getBaseDataStore().db
		            .getCollection(DATASTORES.CONTACT_LIST.COLLECTION);
		DBObject query1,query2;
		List<ContactList>  list=new ArrayList<ContactList>();
		  query1= new BasicDBObject(DATASTORES.CONTACT_LIST.USER, user);	
		  query2= new BasicDBObject(DATASTORES.CONTACT_LIST.CONTACT, user);
		  BasicDBList orList = new BasicDBList();
		  orList.add(query1);
		  orList.add(query2);
		  BasicDBObject finalQuery = new BasicDBObject("$or", orList);	
		  DBCursor cursor = collection.find(finalQuery);
		  while(cursor.hasNext()) {
			  ContactList contactlist=new ContactList();
			  DBObject row = cursor.next();
			  if(row.get(DATASTORES.CONTACT_LIST.USER).toString().equals(user))
				  contactlist.setContact(row.get(DATASTORES.CONTACT_LIST.CONTACT).toString());
			  else
				  contactlist.setContact(row.get(DATASTORES.CONTACT_LIST.USER).toString());
			  list.add(contactlist);
		  }
		
		return list;
		
	}

}
