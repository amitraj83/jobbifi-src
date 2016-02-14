package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.DataStoreRegistry;

public class SearchAdvisors extends RequestHandler {

	public SearchAdvisors() {
		addHandler(this, "FIND_ADVISORS");
	}

	@Override
	public Map<String, Object> handleRequest(Map<Object, Object> data) {
		Map<String, Object> response = new HashMap<String, Object>();
		try{  
			String searchKey = String.valueOf(data.get("searchkey"));
			if(searchKey == null || searchKey.trim().length() == 0)
				return response;
			else{
				//Map<String, Object> searchAdvisors(String key) 
				response = DataStoreRegistry.getInstance().getInterviewerDataStore().searchAdvisors(searchKey);

			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return response;
	}

}
