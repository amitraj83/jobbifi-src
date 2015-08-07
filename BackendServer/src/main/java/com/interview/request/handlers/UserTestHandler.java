package com.interview.request.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.UserTest;
import com.interview.rmi.DataStoreRegistry;

@Service
public class UserTestHandler extends RequestHandler {
		
	private static final Logger logger = Logger.getLogger(UserTestHandler.class);
	
	public UserTestHandler(){
		addHandler(this, REQUEST_TYPES.USER_TEST_REQ);
	}
	
	@Override
	public Map<String, Object> handleRequest(Map<Object, Object> data){		
		
		Map<String, Object> resMap = new HashMap<String, Object>();		
		String subReq = (String) data.get(REQUEST_TYPES.SUB_REQ);
		
		if(null != subReq && REQUEST_TYPES.USER_TEST_SUB_REQ.SAVE_USER_TEST.equals(subReq)){
			try {
				UserTest userTest = (UserTest) data.get("usertest");
				ObjectId id = DataStoreRegistry.getInstance().getUserTestStore().saveUserTest(userTest);
				if(id != null){
					resMap.put("result", 1);
				} else {
					resMap.put("result", 0);
				}
			} catch (Exception e) {
				logger.error("Exception occured while saving the user test.", e);
				resMap.put("result", 0);
			}	
		} else if(null != subReq && REQUEST_TYPES.USER_TEST_SUB_REQ.GET_USER_TESTS.equals(subReq)){
			try {
				List<UserTest> testList = DataStoreRegistry.getInstance().getUserTestStore().getAllUserTests();
				resMap.put("result", testList);
			} catch (Exception e) {
				logger.error("Exception occured while get all the user test.", e);
				resMap.put("result", null);
			}
			
		} else if(null != subReq && REQUEST_TYPES.USER_TEST_SUB_REQ.GET_USER_TEST_BY_ID.equals(subReq)){
			String id = null;
			try {
				id = (String) data.get("id");
				UserTest userTest = DataStoreRegistry.getInstance().getUserTestStore().getUserTest(id);
				resMap.put("result", userTest);
			} catch (Exception e) {
				logger.error("Exception occured while getting test. User Test Id : " + id, e);
				resMap.put("result", null);
			}			
		}
		return resMap;
	}
}