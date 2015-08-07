package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Question;

public interface IQuestionDataStore extends Remote {
	
	public static final String NAME = "questionStore";

	ObjectId saveQuestion(Question question) throws RemoteException;
	
	public List<Question> getQuestionsByTestId(String testId, int pageSize, int pageNo)
			throws RemoteException;
	
	public long getQuestionsCountForTest(String testId) throws RemoteException;
	
	public int deleteQuestion(String questionId) throws RemoteException;
	
	public List<Question> getQuestionsForTest(String testId) throws RemoteException;
}
