package com.interview.util.conversion;

import com.interview.framework.MongoService;
import com.mongodb.DBCollection;

public class MongoDBService {

	private static MongoDBService instance = null;
	private MongoService mongoService;
	private static String host;
	private static int port;

	public static void init(String h, int p) {
		host = h;
		port = p;
	}

	private MongoDBService(String host, int port) {
		this.mongoService = new MongoService(host, port);
	}

	public static MongoDBService getInstance() {
		if (instance == null)
			instance = new MongoDBService(host, port);
		return instance;
	}

	public DBCollection getInterviewerCollection() {
		return mongoService.getDataBase("interviewbackend").getCollection(
				"interviewer");
	}

	public DBCollection getEducationCollection() {
		return mongoService.getDataBase("interviewbackend").getCollection(
				"education");
	}

	public DBCollection getPositionCollection() {
		return mongoService.getDataBase("interviewbackend").getCollection(
				"position");
	}

	public DBCollection getInterviewCollection() {
		return mongoService.getDataBase("interviewbackend").getCollection(
				"interview");
	}

	public DBCollection getJobCollection() {
		return mongoService.getDataBase("interviewbackend")
				.getCollection("job");
	}

	public DBCollection getSkillsCollection() {
		return mongoService.getDataBase("interviewbackend").getCollection(
				"skill");
	}

}
