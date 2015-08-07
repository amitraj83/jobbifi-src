package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.interview.framework.pojo.ContactList;

public interface IContactListStore extends Remote{
	String NAME = "contactFriendStore";
	
	public List<ContactList> getContactList(String user) throws RemoteException;
	
	public void saveContact(ContactList contactlist) throws RemoteException;
}
