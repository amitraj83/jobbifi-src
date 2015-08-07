package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.ContactList;
import com.interview.rmi.DataStoreRegistry;

public class ContactListHandler extends RequestHandler{
	
	public ContactListHandler() {
	    addHandler(this, REQUEST_TYPES.CONTACT_LIST);
	  }

	@Override
	public Map<String, Object> handleRequest(Map<Object, Object> data) {
		// TODO Auto-generated method stub
		 Map<String, Object> resMap = new HashMap<String, Object>();
		 String SUB_REQ = (data.get(REQUEST_TYPES.SUB_REQ) != null) ? 
				   (data.get(REQUEST_TYPES.SUB_REQ).toString()) : null;
				   if(SUB_REQ!=null && SUB_REQ.equals("SAVE_CONTACT_LIST")){
					   try {
						   ContactList contactlist=new ContactList();
						   contactlist.setUser( (String) data.get("from"));
						      contactlist.setContact((String) data.get("to"));						      
						       DataStoreRegistry.getInstance().getContactListStore().saveContact(contactlist);
						       resMap.put("listadded", "success");
						    } catch (RemoteException e) {
						      // TODO Auto-generated catch block
						      e.printStackTrace();
						    }
				   }
				   else{
						 try {
						      String author = (String) data.get("user");
				
						      List<ContactList> friends =
						          DataStoreRegistry.getInstance().getContactListStore().getContactList(author);
				
						      resMap.put("CONTACT_LIST", friends);
						    } catch (RemoteException e) {
						      // TODO Auto-generated catch block
						      e.printStackTrace();
						    }
				   	}
		    return resMap;
	}


}
