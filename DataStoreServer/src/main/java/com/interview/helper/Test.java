package com.interview.helper;



import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;

import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Education;
import com.interview.framework.pojo.Position;
import com.interview.framework.pojo.Skill;
import com.interview.services.Services;
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

		String searchKey = "apache";
		
		final DBObject textSearchCommand = new BasicDBObject();
	    textSearchCommand.put("text", "interviewer");
	    textSearchCommand.put("search", searchKey);
	    final CommandResult commandResult = db.command(textSearchCommand);
	    BasicDBList results = (BasicDBList)commandResult.get("results");

	    Map<String, Object> responseMap = new HashMap<String, Object>();
        
	    for (Iterator<Object> it = results.iterator();it.hasNext();)
	    {
	    	BasicDBObject res  = (BasicDBObject) it.next();
	        BasicDBObject obj = (BasicDBObject)res.get("obj");
	        String score = String.valueOf(res.get("score"));
//	        System.out.println(score);
//	        System.out.println(obj.toMap().toString());
	        
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
		    
	    }
		
	    System.out.println("Before:"+responseMap.keySet().size());
		
		
		
		final DBObject textPositionSearchCommand = new BasicDBObject();
	    textPositionSearchCommand.put("text", "position");
	    textPositionSearchCommand.put("search", searchKey);
	    final CommandResult positionCommandResult = db.command(textPositionSearchCommand);
	    BasicDBList positionResults = (BasicDBList)positionCommandResult.get("results");

	    List<String> positionIDs = new ArrayList<String>();
	    
	    for (Iterator<Object> it = positionResults.iterator();it.hasNext();)
	    {
	    	DBObject row = (DBObject) it.next();
	    	BasicDBObject obj = (BasicDBObject)row.get("obj");
	    	positionIDs.add(obj.get("_id").toString());
	    }
	    
	    BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> listQuery = new ArrayList<BasicDBObject>();
		listQuery.add(new BasicDBObject("type","INTERVIEWER"));
		listQuery.add(new BasicDBObject("positions", new BasicDBObject("$in", positionIDs)));
		andQuery.put("$and", listQuery);
	    
	    DBCursor cursor = collection.find(andQuery);
	    
	    while(cursor.hasNext()) {
			DBObject obj = cursor.next();
			String username = String.valueOf(obj.get(USER.USERNAME));
			if(!responseMap.containsKey(username)){
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
			}
		}
		
		
		System.out.println("After:"+responseMap.keySet().size());
		
		
		
		
		
		
		System.out.println("Done");

		mongo.close();
	}

}