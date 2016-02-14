package com.interview.helper;

import java.net.UnknownHostException;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Test {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		DB db = mongo.getDB("interviewbackend");
		DBCollection table = db.getCollection("interviewer");
		
		DBCursor cursor = db.getCollection("interviewer").find(new BasicDBObject("skills", new BasicDBObject("$regex", "jav")
        																					.append("$options", "i")));
		
		AggregationOutput output = table.aggregate(
				new BasicDBObject("$match", new BasicDBObject("skills","java")), 
				new BasicDBObject("$sort", new BasicDBObject("rating", 1)));
		
		for (DBObject dbObject : output.results()) {
			System.out.println(dbObject.toMap().toString());
		}
		
		
//		while(cursor.hasNext()){
//			DBObject row = cursor.next();
//			System.out.println(row.toMap().toString());
//		}

	}

}
