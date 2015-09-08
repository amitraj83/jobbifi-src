package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.DataStoreRegistry;

@Service
public class InterviewerHandler extends RequestHandler {

	private static final Logger logger = Logger.getLogger(InterviewerHandler.class);
	
	public InterviewerHandler(){
		addHandler(this, REQUEST_TYPES.INTERVIEWER);
	}
	
	@Override
	public Map<String, Object> handleRequest(Map<Object, Object> data) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String subReq = (String) data.get(REQUEST_TYPES.SUB_REQ);
		if(null != subReq && (REQUEST_TYPES.INTERVIEWER_SUB_REQ.GET_TOP_ADVISOR).equals(subReq)){
			int noOfResult = Integer.parseInt((String) data.get("noOfResult"));			
			try {
				List<Object> advisorList = DataStoreRegistry.getInstance().getInterviewerDataStore().getTopAdvisorList(noOfResult);
				map.put("advisorList", advisorList);
			} catch (RemoteException e) {
				logger.error("Exception : ", e);				
			}
		}		
		return map;
	}
}
