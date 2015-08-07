package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.Question;
import com.interview.rmi.DataStoreRegistry;

@Service
public class QuestionHandler extends RequestHandler  {

	public QuestionHandler(){
		addHandler(this, REQUEST_TYPES.QUESTION_REQ);
	}
	
	private static final Logger logger = Logger.getLogger(QuestionHandler.class);
	
	@Override
	public Map<String, Object> handleRequest(Map<Object, Object> data) {

		Map<String, Object> resMap = new HashMap<String, Object>();		
		String subReq = (String) data.get(REQUEST_TYPES.SUB_REQ);
		
		if(null != subReq && REQUEST_TYPES.QUESTION_SUB_REQ.SAVE_QUESTION.equals(subReq)){			
			try {
				Question question = (Question) data.get("question");
				ObjectId id = DataStoreRegistry.getInstance().getQuestionStore().saveQuestion(question);
				if(null != id){
					resMap.put("result", 1);
				} else {
					resMap.put("result", -1);
				}
			} catch (RemoteException e) {				
				logger.error("Exception occured. OP: SAVE QUESTION", e);
				resMap.put("result", -1);
			}
		} else if(null != subReq && REQUEST_TYPES.QUESTION_SUB_REQ.GET_QUESTIONS_BY_TEST_ID.equals(subReq)){
			try {
				String testId = (String) data.get(DATASTORES.QUESTION.TEST_ID); 
				int pageSize = (Integer)data.get(DATASTORES.PAGE_SIZE);
				int pageNo = (Integer) data.get(DATASTORES.PAGE_NO);
				List<Question> list = DataStoreRegistry.getInstance().getQuestionStore().getQuestionsByTestId(testId, pageSize, pageNo);
				long count =  DataStoreRegistry.getInstance().getQuestionStore().getQuestionsCountForTest(testId);
				resMap.put("result", list);
				resMap.put("count", count);
			} catch (Exception e) {				
				logger.error("Exception occured. OP: GET_ALL_QUESTIONS_BY_TEST_ID", e);
				resMap.put("result", null);
			}
		} else if(null != subReq && REQUEST_TYPES.QUESTION_SUB_REQ.DELETE_QUESTION.equals(subReq)){			
			try {
				String questionID = (String) data.get(DATASTORES.QUESTION.ID);
				int result = DataStoreRegistry.getInstance().getQuestionStore().deleteQuestion(questionID);				
				resMap.put("result", result);
				
			} catch (RemoteException e) {				
				logger.error("Exception occured. OP: DELETE_QUESTION", e);
				resMap.put("result", -1);
			}
		} else if(null != subReq && REQUEST_TYPES.QUESTION_SUB_REQ.GET_QUESTIONS_FOR_TEST.equals(subReq)){
			try {
				String testId = (String) data.get(DATASTORES.QUESTION.TEST_ID); 				
				List<Question> list = DataStoreRegistry.getInstance().getQuestionStore().getQuestionsForTest(testId);				
				resMap.put("result", list);
				
			} catch (Exception e) {				
				logger.error("Exception occured. OP: GET_QUESTIONS_FOR_TEST", e);
				resMap.put("result", null);
			}
		} else if(null != subReq && REQUEST_TYPES.QUESTION_SUB_REQ.GET_QUESTIONS_COUNT_BY_TEST_ID.equals(subReq)){
			try {
				String testId = (String) data.get(DATASTORES.QUESTION.TEST_ID); 				
				long count =  DataStoreRegistry.getInstance().getQuestionStore().getQuestionsCountForTest(testId);							
				resMap.put("result", count);
				
			} catch (Exception e) {				
				logger.error("Exception occured. OP: GET_QUESTIONS_FOR_TEST", e);
				resMap.put("result", null);
			}
		} 
		return resMap;
	}
}