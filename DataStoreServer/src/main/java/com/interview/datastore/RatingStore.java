package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.Rating;
import com.interview.framework.rmi.common.IRatingStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class RatingStore extends UnicastRemoteObject implements IRatingStore {
  
	private static final long serialVersionUID = 1L;

	protected RatingStore() throws RemoteException {
		Services.getInstance().getRMIServer().bind(NAME, this);
   }

  @Override
  public ObjectId save(Rating r) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.RATING.DBCOllection);

    ObjectId rid = new ObjectId();
    DBObject data = new BasicDBObject();
    data.put("_id", rid);
    data.put(DATASTORES.RATING.USERNAME, r.getUsername());
    data.put(DATASTORES.RATING.RATEDBY, r.getRatedBy());
    data.put(DATASTORES.RATING.RATE1, r.getRate1());
    data.put(DATASTORES.RATING.RATE2, r.getRate2());
    data.put(DATASTORES.RATING.RATE3, r.getRate3());
    data.put(DATASTORES.RATING.RATE4, r.getRate4());
    data.put(DATASTORES.RATING.AVERAGE, r.getAverage());
    data.put(DATASTORES.RATING.MESSAGE, r.getMessage());
    data.put(DATASTORES.RATING.IID, r.getIid());
    data.put(DATASTORES.RATING.TIME, r.getTime());

    WriteResult wr = collection.save(data);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok()) {
      r.setId(rid.toString());
      return rid;
    } else
      return null;
  }

  public double getAvgRating(String user) {

    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.RATING.DBCOllection);
    DBObject query = new BasicDBObject(DATASTORES.RATING.USERNAME, user);
    DBCursor cursor = collection.find(query);
    double total = 0;
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      total += new Double(row.get(DATASTORES.RATING.AVERAGE).toString());
    }    
    
    double average = 0;
    if(cursor.count() != 0){
    	average = total / cursor.count();
    }
    return average;
  }

  public Rating getRating(ObjectId _id) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.RATING.DBCOllection);
    DBObject query = new BasicDBObject();
    query.put("_id", _id);

    DBObject row = collection.findOne(query);
    return getRating(row);
  }

  public boolean isRatingDone(String ratedByUser, ObjectId iid) {

    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.RATING.DBCOllection);
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.RATING.RATEDBY, ratedByUser);
    query.put("iid", iid);

    DBObject row = collection.findOne(query);
    if (row == null)
      return false;
    else if (row.get(DATASTORES.RATING.RATEDBY).toString().equals(ratedByUser))
      return true;
    else
      return false;
  }

  public Rating getRating(String ratedBy, ObjectId iid) throws RemoteException {

    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.RATING.DBCOllection);
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.RATING.RATEDBY, ratedBy);
    query.put("iid", iid);

    DBObject row = collection.findOne(query);
    if (row != null)
      return getRating(row);
    else
      return null;

  }

  private Rating getRating(DBObject row) throws RemoteException {
    Rating r = new Rating();
    r.setAverage(new Double(row.get(DATASTORES.RATING.AVERAGE).toString()));
    r.setIid(new ObjectId(row.get(DATASTORES.RATING.IID).toString()));
    r.setMessage(row.get(DATASTORES.RATING.MESSAGE).toString());
    r.setRate1(new Integer(row.get(DATASTORES.RATING.RATE1).toString()));
    r.setRate2(new Integer(row.get(DATASTORES.RATING.RATE2).toString()));
    r.setRate3(new Integer(row.get(DATASTORES.RATING.RATE3).toString()));
    r.setRate4(new Integer(row.get(DATASTORES.RATING.RATE4).toString()));
    r.setRatedBy(row.get(DATASTORES.RATING.RATEDBY).toString());
    r.setTime(new Long(row.get(DATASTORES.RATING.TIME).toString()));
    r.setUsername(row.get(DATASTORES.RATING.USERNAME).toString());
    r.setId(row.get("_id").toString());
    return r;
  }


  @Override
  public int getReviewsCount(String user) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put(DATASTORES.RATING.USERNAME, user);

    return collection.find(query).count();
  }

  @Override
  public List<Rating> getAllRatings(String user) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.RATING.DBCOllection);
    List<Rating> allRatings = new ArrayList<Rating>();
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.RATING.USERNAME, user);
    DBCursor cursor = collection.find(query);
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      allRatings.add(getRating(row));
    }
    return allRatings;
  }
}