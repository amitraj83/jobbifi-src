package com.interview.rmi.common;
import java.rmi.*;
import java.util.Map;


public interface ReceiveMessageInterface extends Remote{
	Map<String, Object> executeRequest(Map<Object, Object> req, String reqType) throws RemoteException;
}