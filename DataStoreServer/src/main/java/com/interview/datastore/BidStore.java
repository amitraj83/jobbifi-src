package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.BID_STATUS;
import com.interview.framework.DATASTORES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.rmi.common.IBidStore;
import com.interview.helper.MongoDataHelper;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class BidStore extends UnicastRemoteObject implements IBidStore {

	private static final long serialVersionUID = 1L;

	protected BidStore() throws RemoteException {
		Services.getInstance().getRMIServer().bind(NAME, this);
	}

  public ObjectId saveBid(Bid bid) throws RemoteException {

    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);

    DBObject dbObject = new BasicDBObject();
    ObjectId _id = new ObjectId();
    dbObject.put("_id", _id);
    dbObject.put(VARIABLES.Bid.BIDDER, bid.getBidder());
    dbObject.put(VARIABLES.Bid.INTERVIEW_ID, bid.getIid());
    dbObject.put(VARIABLES.Bid.MSG, bid.getMsg());
    dbObject.put(VARIABLES.Bid.PRICE, bid.getPrice());
    dbObject.put(VARIABLES.Bid.DATE, bid.getDate());
    dbObject.put(VARIABLES.Bid.STATUS, BID_STATUS.PENDING);
    dbObject.put(VARIABLES.Bid.BID_FID, bid.getAttachmentID());
    WriteResult wr = collection.save(dbObject);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok()) {

      return _id;
    } else
      return null;
  }

  public boolean deleteBid(ObjectId _id) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
    DBObject doc = new BasicDBObject("_id", _id);
    WriteResult wr = collection.remove(doc);
    CommandResult cr = wr.getCachedLastError();
    return cr.ok();

  }

  public List<Bid> getBidsIPosted(String username) throws RemoteException {
    List<Bid> list = new ArrayList<Bid>();
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
    DBObject query = new BasicDBObject();
    query.put(VARIABLES.Bid.BIDDER, username);

    DBCursor cursor = collection.find(query);
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      Bid bid = new Bid();
      bid.setId(row.get("_id").toString());
      bid.setBidder((String) row.get(VARIABLES.Bid.BIDDER));
      bid.setIid(row.get(VARIABLES.Bid.INTERVIEW_ID).toString());
      bid.setMsg((String) row.get(VARIABLES.Bid.MSG));
      bid.setPrice((String) row.get(VARIABLES.Bid.PRICE));
      bid.setStatus(new Integer(row.get(VARIABLES.Bid.STATUS).toString()));
      bid.setDate((Long) row.get(VARIABLES.Bid.DATE));
      list.add(bid);
    }
    return list;
  }

  public List<Bid> getBidsIPosted(String username,int pageNum) throws RemoteException {
	    List<Bid> list = new ArrayList<Bid>();
	    DBCollection collection =
	        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
	    DBObject query = new BasicDBObject();
	    query.put(VARIABLES.Bid.BIDDER, username);
	        int documentsToSkip = pageNum > 0 ? (pageNum - 1)
	    			* (VARIABLES.TRNSACTION_PAGE_SIZE) : 0;
	    DBCursor cursor = collection.find(query).skip(documentsToSkip).limit(VARIABLES.TRNSACTION_PAGE_SIZE).sort(new BasicDBObject(DATASTORES.INTERVIEW.DATE, -1));
	    while (cursor.hasNext()) {
	      DBObject row = cursor.next();
	      Bid bid = new Bid();
	      bid.setId(row.get("_id").toString());
	      bid.setBidder((String) row.get(VARIABLES.Bid.BIDDER));
	      bid.setIid(row.get(VARIABLES.Bid.INTERVIEW_ID).toString());
	      bid.setMsg((String) row.get(VARIABLES.Bid.MSG));
	      bid.setPrice((String) row.get(VARIABLES.Bid.PRICE));
	      bid.setStatus(new Integer(row.get(VARIABLES.Bid.STATUS).toString()));
	      bid.setDate((Long) row.get(VARIABLES.Bid.DATE));

	      list.add(bid);

	    }
	    return list;
  }
  
  public int getBidsIPostedCount(String username) throws RemoteException {
	    List<Bid> list = new ArrayList<Bid>();
	    DBCollection collection =
	        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
	    DBObject query = new BasicDBObject();
	    query.put(VARIABLES.Bid.BIDDER, username);
	    int  cursor = collection.find(query).count();
	   

	    return cursor;
	  }
  
  public List<Bid> getBidsReceivedForInterview(String _id) throws RemoteException {
    List<Bid> list = new ArrayList<Bid>();

    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
    DBObject query = new BasicDBObject();
    query.put(VARIABLES.Bid.INTERVIEW_ID, _id);

    DBCursor cursor = collection.find(query);
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      Bid bid = new Bid();
      bid.setId(row.get("_id").toString());
      bid.setBidder((String) row.get(VARIABLES.Bid.BIDDER));
      bid.setIid(row.get(VARIABLES.Bid.INTERVIEW_ID).toString());
      bid.setMsg((String) row.get(VARIABLES.Bid.MSG));
      bid.setPrice((String) row.get(VARIABLES.Bid.PRICE));
      bid.setStatus(new Integer(row.get(VARIABLES.Bid.STATUS).toString()));
      bid.setDate((Long) row.get(VARIABLES.Bid.DATE));
      if (row.get(VARIABLES.Bid.BID_FID) != null)
        bid.setAttachmentID(row.get(VARIABLES.Bid.BID_FID).toString());
      else
        bid.setAttachmentID("");
      list.add(bid);

    }

    return list;
  }
  
  @Override
  public List<String> getBidderForInterview(String _id) throws RemoteException {
	    List<String> list = new ArrayList<String>();

	    DBCollection collection =
	        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
	    DBObject query = new BasicDBObject();
	    query.put(VARIABLES.Bid.INTERVIEW_ID, _id);

	    DBCursor cursor = collection.find(query);
	    while (cursor.hasNext()) {
	      DBObject row = cursor.next();
	      list.add((String) row.get(VARIABLES.Bid.BIDDER));
	    }
	    return list;
	  }

  public void updateBidStatus(ObjectId bid, int bidStatus) {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
    DBObject query = new BasicDBObject();
    query.put("_id", bid);
    BasicDBObject updateDoc =
        new BasicDBObject("$set", new BasicDBObject(VARIABLES.Bid.STATUS, bidStatus));

    collection.update(query, updateDoc);
  }

  public List<String> getAllBidIDsForInterview(ObjectId iid) {
    return getAllBidIDsForInterview(iid.toString());
  }

  public List<String> getAllBidIDsForInterview(String iid) {
    List<String> bids = new ArrayList<String>();
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
    DBObject query = new BasicDBObject();
    query.put(VARIABLES.Bid.INTERVIEW_ID, iid);

    DBCursor cursor = collection.find(query);
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      bids.add(row.get("_id").toString());
    }

    return bids;
  }



  public ObjectId getInterviewId(ObjectId _id) throws RemoteException {

    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
    DBObject query = new BasicDBObject();
    query.put("_id", _id);

    DBObject row = collection.findOne(query);
    return new ObjectId(row.get(VARIABLES.Bid.INTERVIEW_ID).toString());
  }

  public boolean deleteAssociatedBidsOfInterview(ObjectId iid) {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
    List<String> bids = getAllBidIDsForInterview(iid);
    boolean success = true;
    for (String bid_id : bids) {
      DBObject query = new BasicDBObject("_id", new ObjectId(bid_id));
      WriteResult wr = collection.remove(query);
      CommandResult cr = wr.getCachedLastError();
      success = success & cr.ok();
    }

    return success;
  }

  public Bid getBid(ObjectId bidId) {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
    DBObject query = new BasicDBObject();
    query.put("_id", bidId);

    DBObject row = collection.findOne(query);
    Bid bid = new Bid();
    bid.setId(row.get("_id").toString());
    bid.setBidder((String) row.get(VARIABLES.Bid.BIDDER));
    bid.setIid(row.get(VARIABLES.Bid.INTERVIEW_ID).toString());
    bid.setMsg((String) row.get(VARIABLES.Bid.MSG));
    bid.setPrice((String) row.get(VARIABLES.Bid.PRICE));
    bid.setStatus(new Integer(row.get(VARIABLES.Bid.STATUS).toString()));
    bid.setDate((Long) row.get(VARIABLES.Bid.DATE));

    return bid;
  }

public int getBidCount(String iid) throws RemoteException {	
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
    DBObject query = new BasicDBObject();
    query.put(VARIABLES.Bid.INTERVIEW_ID, iid);
    return collection.find(query).count();    
}

  @Override
  public Bid getAcceptedBidForInterview(String iid) throws RemoteException {

    DBCollection collection =
        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
    DBObject query = new BasicDBObject();
    query.put(VARIABLES.Bid.INTERVIEW_ID, iid);

    DBObject row = collection.findOne(query);
    Bid bid = new Bid();
    bid.setId(row.get("_id").toString());
    bid.setBidder((String) row.get(VARIABLES.Bid.BIDDER));
    bid.setIid(row.get(VARIABLES.Bid.INTERVIEW_ID).toString());
    bid.setMsg((String) row.get(VARIABLES.Bid.MSG));
    bid.setPrice((String) row.get(VARIABLES.Bid.PRICE));
    bid.setStatus(new Integer(row.get(VARIABLES.Bid.STATUS).toString()));
    bid.setDate((Long) row.get(VARIABLES.Bid.DATE));
    return bid;
  }

	@Override
	public Bid getBidByBidderAndInterview(String bidder, String iid)
			throws RemoteException {
		
		Bid bid = null;
		DBCollection collection =
	        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
	    DBObject query = new BasicDBObject();
	    query.put(VARIABLES.Bid.BIDDER, bidder);
	    query.put(VARIABLES.Bid.INTERVIEW_ID, iid);
	    DBObject row = collection.findOne(query);
	    if(null != row){	    		    
	    	bid = new Bid();
	    	bid.setId(row.get("_id").toString());
	    	bid.setBidder((String) row.get(VARIABLES.Bid.BIDDER));
	    	bid.setIid(row.get(VARIABLES.Bid.INTERVIEW_ID).toString());
	    	bid.setMsg((String) row.get(VARIABLES.Bid.MSG));
	    	bid.setPrice((String) row.get(VARIABLES.Bid.PRICE));
	    	bid.setStatus(new Integer(row.get(VARIABLES.Bid.STATUS).toString()));
	    	bid.setDate((Long) row.get(VARIABLES.Bid.DATE));
	    }
	    return bid;
	}

	@Override
	public List<Bid> getBidsForInterviewsByUser(String username, Collection<String> iids) 
			throws RemoteException {
		
		List<Bid> result = new ArrayList<Bid>();
	    DBCollection collection = Services.getInstance().getBaseDataStore().db
	            .getCollection(DATASTORES.BID.DBCollection);
	    ArrayList<String> vals = new ArrayList<String>();
	    for (String iid : iids) {
	      vals.add(iid);
	    }   
	    	    
	    ArrayList andList = new ArrayList();	    
	    andList.add(new BasicDBObject(VARIABLES.Bid.INTERVIEW_ID, new BasicDBObject("$in", vals)));
	    andList.add(new BasicDBObject(VARIABLES.Bid.BIDDER, username));
	    
	    DBObject query  = new BasicDBObject("$and", andList);
	    DBCursor cursor = collection.find(query);
	    while (cursor.hasNext()) {
	    	DBObject row = cursor.next();            
	      	Bid bid = new Bid();
	      	bid.setId(row.get("_id").toString());
	      	bid.setBidder(MongoDataHelper.getStringValue(row, VARIABLES.Bid.BIDDER));
	      	bid.setPrice(MongoDataHelper.getStringValue(row, VARIABLES.Bid.PRICE));
	      	bid.setMsg(MongoDataHelper.getStringValue(row, VARIABLES.Bid.MSG));
	      	bid.setIid(MongoDataHelper.getStringValue(row, VARIABLES.Bid.INTERVIEW_ID));
	      	bid.setAttachmentID(MongoDataHelper.getStringValue(row, VARIABLES.Bid.BID_FID));
	      	bid.setDate(MongoDataHelper.getLong(row, VARIABLES.Bid.DATE));
	      	bid.setStatus(MongoDataHelper.getInt(row, VARIABLES.Bid.STATUS));
	      	result.add(bid);	      	
	    }
	    return result;
	}

	@Override
	public int getMaxBidOfInterview(String iid) throws RemoteException {
		int price = 0;
		DBCollection collection =
		        Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.BID.DBCollection);
		    DBObject query = new BasicDBObject();
		    query.put(VARIABLES.Bid.INTERVIEW_ID, iid);
		    DBCursor cursor = collection.find(query).sort(new BasicDBObject(VARIABLES.Bid.PRICE, -1)).limit(1);
		    while (cursor.hasNext()) {
		        price = (int) cursor.next().get("price");
		    }
		return price;
	}
}
