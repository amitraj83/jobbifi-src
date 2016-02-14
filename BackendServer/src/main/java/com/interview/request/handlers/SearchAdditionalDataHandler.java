package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.DataStoreRegistry;

public class SearchAdditionalDataHandler extends RequestHandler {

  public SearchAdditionalDataHandler() {
    addHandler(this, REQUEST_TYPES.SEARCH_ADDITIONAL_DATA);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> users) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
    	
    String SUB_REQ = String.valueOf(users.get(REQUEST_TYPES.SUB_REQ));
    
    if(SUB_REQ.equals("FIND_ADVISOR")){
    	String searchKey = String.valueOf(users.get("searchkey"));
		if(searchKey == null || searchKey.trim().length() == 0)
			return resMap;
		else{
			resMap = DataStoreRegistry.getInstance().getInterviewerDataStore().searchAdvisors(searchKey);
		}
    }
    else{
    	resMap = DataStoreRegistry.getInstance().getInterviewerDataStore().getAdditionalData(users);
    }
    
      

    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return resMap;
  }
}
