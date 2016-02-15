package com.interview.helper;



import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Education;
import com.interview.framework.pojo.Position;
import com.interview.framework.pojo.Skill;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;


public class Test {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		
		MongoClient mongo = new MongoClient( "jobbifi.com" , 27017 );
		DB db = mongo.getDB("interviewbackend");
		DBCollection collection = db.getCollection("interviewer");

		final DBObject textSearchCommand = new BasicDBObject();
	    textSearchCommand.put("text", "interviewer");
	    textSearchCommand.put("search", "java business");
	    final CommandResult commandResult = db.command(textSearchCommand);
	    
	    BasicDBList results = (BasicDBList)commandResult.get("results");

	    for (Iterator<Object> it = results.iterator();it.hasNext();)
	    {
	    	Map<String, Object> responseMap = new HashMap<String, Object>();
	        BasicDBObject res  = (BasicDBObject) it.next();
	        BasicDBObject obj = (BasicDBObject)res.get("obj");
	        String score = String.valueOf(res.get("score"));
	        System.out.println(score);
	        System.out.println(obj.toMap().toString());
	        
	        Map<String, Object> response = new HashMap<String, Object>();
			
			response.put("_id", String.valueOf(obj.get("_id")));
			response.put(USER.USERNAME, (String) obj.get(USER.USERNAME));
			response.put(USER.RATE, obj.get(USER.RATE).toString());
			response.put(USER.COUNTRY, (String) obj.get(USER.COUNTRY));
			response.put(USER.COMPANIES, obj.get(USER.COMPANIES).toString());
			response.put(USER.RATING, obj.get(USER.RATING).toString());
			response.put(USER.CV, obj.get(USER.CV).toString());
			response.put(USER.PROFILE_PIC, obj.get(USER.PROFILE_PIC).toString());
			
			responseMap.put(String.valueOf(obj.get(USER.USERNAME)), response);
			System.out.println("Print : "+responseMap.toString());
	        
	    }
		System.out.println("Done");
//		AggregationOutput output = collection.aggregate(
//				new BasicDBObject("$match", new BasicDBObject("skills","java").append("$options", "i")), 
//				new BasicDBObject("$sort", new BasicDBObject("rating", 1)));
//		
//		
//		
//		for (DBObject row : output.results()) {
//			System.out.println(row.toMap().toString());
//		}
		mongo.close();
	}

}