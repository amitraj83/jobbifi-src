package com.interview.framework;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.Rating;
import com.interview.framework.util.JSONUtilityService;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class Test {


  public static void main(String[] args) {

    MongoService ms = new MongoService("127.0.0.1", 27017);
    DB db = ms.getDataBase("interviewbackend");
    DBCollection collection = db.getCollection(DATASTORES.RATING.DBCOllection);


    JSONUtilityService jsonUtil = new JSONUtilityService();
    String json = "";
    Rating r = new Rating();
    r.setIid(new ObjectId());
    DBObject obj = new BasicDBObject();
    obj.put("iid", r.getIid());
    // json = jsonUtil.writeValueAsString(r);

    // DBObject dbo = (DBObject)JSON.parse(json);

    WriteResult wr = collection.save(obj);
    System.out.println("Done");
    /*
     * 
     * 
     * DBObject query = new BasicDBObject(); query.put(DATASTORES.RATING.RATEDBY, "amitraj");
     * query.put("iid", new ObjectId ("5229fc8490a26455113c0e2e"));
     * 
     * DBObject row = collection.findOne(query); System.out.println(row);
     */
  }
}
