package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Question;
import com.interview.framework.rmi.common.IQuestionDataStore;
import com.interview.services.Services;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class QuestionDataStore extends UnicastRemoteObject implements IQuestionDataStore {

	private static final long serialVersionUID = 1L;

	protected QuestionDataStore() throws RemoteException {
	    Services.getInstance().getRMIServer().bind(NAME, this);
	}
	
	@Override
	public ObjectId saveQuestion(Question question) throws RemoteException {
		
		DBCollection dbCollection = Services.getInstance().getBaseDataStore().db
				.getCollection(DATASTORES.QUESTION.DBCollection);
		DBObject dbObject = new BasicDBObject();
	    ObjectId _id = new ObjectId();
	    dbObject.put(DATASTORES.QUESTION.ID, _id);
		dbObject.put(DATASTORES.QUESTION.QUESTION, question.getQuestion());
		dbObject.put(DATASTORES.QUESTION.QUESTION_TYPE, question.getQuestionType());
		dbObject.put(DATASTORES.QUESTION.ANSWER_1, question.getAnswer1());
		dbObject.put(DATASTORES.QUESTION.ANSWER_2, question.getAnswer2());
		dbObject.put(DATASTORES.QUESTION.ANSWER_3, question.getAnswer3());
		dbObject.put(DATASTORES.QUESTION.ANSWER_4, question.getAnswer4());
		dbObject.put(DATASTORES.QUESTION.TEST_ID, question.getTestId());
		dbObject.put(DATASTORES.QUESTION.CORRECT_ANSWERS, question.getCorrectAnswers());
		CommandResult cr = dbCollection.save(dbObject).getCachedLastError();
	    if (cr.ok()){
	      return _id;
	    } else {	      
	    	return null;
	    }
	}
	
	@Override
	public List<Question> getQuestionsByTestId(String testId, int pageSize, int pageNo)
			throws RemoteException {
		DBCollection dbCollection = Services.getInstance().getBaseDataStore().db
				.getCollection(DATASTORES.QUESTION.DBCollection);
		List<Question> list = new ArrayList<Question>();
	    DBObject query = new BasicDBObject();
	    query.put(DATASTORES.QUESTION.TEST_ID, testId);	    
	    DBCursor cursor = dbCollection.find(query).skip(pageSize *(pageNo-1))
	    		.sort(new BasicDBObject("_id", -1)).limit(pageSize);
	    while (cursor.hasNext()) {
	        DBObject row = cursor.next();
	        Question question = new Question();
	        question.setId(row.get("_id").toString());  
	        question.setQuestion(row.get(DATASTORES.QUESTION.QUESTION).toString());
	        question.setQuestionType(row.get(DATASTORES.QUESTION.QUESTION_TYPE).toString());
	        question.setAnswer1(row.get(DATASTORES.QUESTION.ANSWER_1).toString());
	        question.setAnswer2(row.get(DATASTORES.QUESTION.ANSWER_2).toString());
	        question.setAnswer3(row.get(DATASTORES.QUESTION.ANSWER_3).toString());
	        question.setAnswer4(row.get(DATASTORES.QUESTION.ANSWER_4).toString());
	        question.setTestId(row.get(DATASTORES.QUESTION.TEST_ID).toString());
	        	        
	        BasicDBList listDBSkills = (BasicDBList) row.get(DATASTORES.QUESTION.CORRECT_ANSWERS);
	        String correctAnswers[] = new String[listDBSkills.size()]; 
	        Iterator<Object> it = listDBSkills.iterator();
	        int i = 0;
	        while (it.hasNext()) {
	           correctAnswers[i] = (String) it.next();
	        	i++;
	        }
	        question.setCorrectAnswers(correctAnswers);
	        list.add(question);
	    }
	    return list;
	}
	
	@Override
	public long getQuestionsCountForTest(String testId) throws RemoteException {
		DBCollection dbCollection = Services.getInstance().getBaseDataStore().db
				.getCollection(DATASTORES.QUESTION.DBCollection);		
	    DBObject query = new BasicDBObject();
	    query.put(DATASTORES.QUESTION.TEST_ID, testId);
	    return dbCollection.count(query);
	}
	
	@Override
	public int deleteQuestion(String questionId) throws RemoteException {
		DBCollection dbCollection = Services.getInstance().getBaseDataStore().db
				.getCollection(DATASTORES.QUESTION.DBCollection);		
	    DBObject query = new BasicDBObject();
	    query.put(DATASTORES.QUESTION.ID, new ObjectId(questionId));
	    CommandResult cr = dbCollection.remove(query).getCachedLastError();
	    if (cr.ok()){
	    	return 1;
	    } else {	      
	    	return 0;
	    }
	}
	
	@Override
	public List<Question> getQuestionsForTest(String testId) throws RemoteException {
		DBCollection dbCollection = Services.getInstance().getBaseDataStore().db
				.getCollection(DATASTORES.QUESTION.DBCollection);
		List<Question> list = new ArrayList<Question>();
	    DBObject query = new BasicDBObject();
	    query.put(DATASTORES.QUESTION.TEST_ID, testId);	    
	    DBCursor cursor = dbCollection.find(query);
	    while (cursor.hasNext()) {
	        DBObject row = cursor.next();
	        Question question = new Question();
	        question.setId(row.get("_id").toString());  
	        question.setQuestion(row.get(DATASTORES.QUESTION.QUESTION).toString());
	        question.setQuestionType(row.get(DATASTORES.QUESTION.QUESTION_TYPE).toString());
	        question.setAnswer1(row.get(DATASTORES.QUESTION.ANSWER_1).toString());
	        question.setAnswer2(row.get(DATASTORES.QUESTION.ANSWER_2).toString());
	        question.setAnswer3(row.get(DATASTORES.QUESTION.ANSWER_3).toString());
	        question.setAnswer4(row.get(DATASTORES.QUESTION.ANSWER_4).toString());
	        question.setTestId(row.get(DATASTORES.QUESTION.TEST_ID).toString());
	        	        
	        BasicDBList listDBSkills = (BasicDBList) row.get(DATASTORES.QUESTION.CORRECT_ANSWERS);
	        String correctAnswers[] = new String[listDBSkills.size()]; 
	        Iterator<Object> it = listDBSkills.iterator();
	        int i = 0;
	        while (it.hasNext()) {
	           correctAnswers[i] = (String) it.next();
	        	i++;
	        }
	        question.setCorrectAnswers(correctAnswers);
	        list.add(question);
	    }
	    return list;
	}
}