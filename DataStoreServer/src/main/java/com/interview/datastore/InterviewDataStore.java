package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.framework.rmi.common.IInterviewDataStore;
import com.interview.helper.MongoDataHelper;
import com.interview.services.Services;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class InterviewDataStore extends UnicastRemoteObject implements IInterviewDataStore {

  private static final long serialVersionUID = 1L;

  public InterviewDataStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  public ObjectId saveInterview(Interview interview) throws RemoteException {

    DBCollection collection =
        Services.getInstance().getBaseDataStore().db
            .getCollection(DATASTORES.INTERVIEW.DBCollection);

    DBObject dbObject = new BasicDBObject();
    ObjectId _id = new ObjectId();
    dbObject.put("_id", _id);
    dbObject.put(DATASTORES.INTERVIEW.TITLE, interview.getTitle());
    dbObject.put(DATASTORES.INTERVIEW.INTERVIEWEE, interview.getInterviewee());
    dbObject.put(DATASTORES.INTERVIEW.INTERVIEWER, interview.getInterviewer());
    dbObject.put(DATASTORES.INTERVIEW.SKILLS, interview.getSkills());
    dbObject.put(DATASTORES.INTERVIEW.DESCRIPTION, interview.getDescription());
    dbObject.put(DATASTORES.INTERVIEW.DATE, interview.getDt());
    dbObject.put(DATASTORES.INTERVIEW.STATUS, interview.getStatus());
    dbObject.put(DATASTORES.INTERVIEW.ESCROW_BALANCE, new Double(0));
    dbObject.put(DATASTORES.INTERVIEW.FILE, interview.getFile());
    dbObject.put(DATASTORES.INTERVIEW.BUDGET, interview.getBudget());
    dbObject.put(DATASTORES.INTERVIEW.INDUSTRY, interview.getIndustry());
    dbObject.put(DATASTORES.INTERVIEW.EXPERIENCE, interview.getExperience());
    WriteResult wr = collection.save(dbObject);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok())
      return _id;
    else
      return null;
  }

  
  public List<Interview> getInterviews(String user, String role) throws RemoteException {

	    DBCollection collection =
	        Services.getInstance().getBaseDataStore().db
	            .getCollection(DATASTORES.INTERVIEW.DBCollection);
	    List<Interview> list = new ArrayList<Interview>();
	    DBObject query = new BasicDBObject();
	    if (role.equals(DATASTORES.INTERVIEW.INTERVIEWEE))
	      query.put(DATASTORES.INTERVIEW.INTERVIEWEE, user);
	    else if (role.equals(DATASTORES.INTERVIEW.INTERVIEWER))
	      query.put(DATASTORES.INTERVIEW.INTERVIEWER, user);
	    DBCursor cursor = collection.find(query).sort(new BasicDBObject(DATASTORES.INTERVIEW.DATE, -1));
	    while (cursor.hasNext()) {
	      DBObject row = cursor.next();
	      Interview interview = new Interview();
	      // interview = Services.getInstance().getJSONUtilityService().readValue(row.toString(),
	      // Interview.class);
	      interview.setDescription(row.get(DATASTORES.INTERVIEW.DESCRIPTION).toString());
	      interview.setEb(new Double(row.get(DATASTORES.INTERVIEW.ESCROW_BALANCE).toString()));
	      interview.setInterviewee(row.get(DATASTORES.INTERVIEW.INTERVIEWEE).toString());
	      if (row.get(DATASTORES.INTERVIEW.INTERVIEWER) != null)
	        interview.setInterviewer(row.get(DATASTORES.INTERVIEW.INTERVIEWER).toString());
	      else
	        interview.setInterviewer("");
	      List<String> skills = new ArrayList<String>();
	      skills.add(row.get(DATASTORES.INTERVIEW.SKILLS).toString());
	      interview.setSkills(skills);
	      interview.setStatus(new Integer(row.get(DATASTORES.INTERVIEW.STATUS).toString()));
	      interview.setTitle(row.get(DATASTORES.INTERVIEW.TITLE).toString());
	      interview.setDt(new Date((Long) row.get(DATASTORES.INTERVIEW.DATE)).getTime());
	      interview.setId(row.get("_id").toString());
	      if (row.get(DATASTORES.INTERVIEW.FILE) != null)
	        interview.setFile(row.get(DATASTORES.INTERVIEW.FILE).toString());

	      if (row.get(DATASTORES.INTERVIEW.BUDGET) != null)
	        interview.setBudget(row.get(DATASTORES.INTERVIEW.BUDGET).toString());

	      if (row.get(DATASTORES.INTERVIEW.EXPERIENCE) != null)
	        interview.setExperience(row.get(DATASTORES.INTERVIEW.EXPERIENCE).toString());

	      if (row.get(DATASTORES.INTERVIEW.INDUSTRY) != null)
	        interview.setIndustry(row.get(DATASTORES.INTERVIEW.INDUSTRY).toString());


	      list.add(interview);
	    }
	    return list;
	  
	  
  }
  public List<Interview> getInterviews(String user, String role,int pageNum,int status) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db
            .getCollection(DATASTORES.INTERVIEW.DBCollection);
    List<Interview> list = new ArrayList<Interview>();
    DBObject query = new BasicDBObject();
    if (role.equals(DATASTORES.INTERVIEW.INTERVIEWEE))
      query.put(DATASTORES.INTERVIEW.INTERVIEWEE, user);
    else if (role.equals(DATASTORES.INTERVIEW.INTERVIEWER))
      query.put(DATASTORES.INTERVIEW.INTERVIEWER, user);
    if(status!=10)
    query.put(DATASTORES.INTERVIEW.STATUS, status);
    int documentsToSkip = pageNum > 0 ? (pageNum - 1)
			* (VARIABLES.TRNSACTION_PAGE_SIZE) : 0;
    DBCursor cursor = collection.find(query).skip(documentsToSkip).limit(VARIABLES.TRNSACTION_PAGE_SIZE).sort(new BasicDBObject(DATASTORES.INTERVIEW.DATE, -1));
    while (cursor.hasNext()) {
      DBObject row = cursor.next();
      Interview interview = new Interview();
      // interview = Services.getInstance().getJSONUtilityService().readValue(row.toString(),
      // Interview.class);
      interview.setDescription(row.get(DATASTORES.INTERVIEW.DESCRIPTION).toString());
      interview.setEb(new Double(row.get(DATASTORES.INTERVIEW.ESCROW_BALANCE).toString()));
      interview.setInterviewee(row.get(DATASTORES.INTERVIEW.INTERVIEWEE).toString());
      if (row.get(DATASTORES.INTERVIEW.INTERVIEWER) != null)
        interview.setInterviewer(row.get(DATASTORES.INTERVIEW.INTERVIEWER).toString());
      else
        interview.setInterviewer("");
      List<String> skills = new ArrayList<String>();
      skills.add(row.get(DATASTORES.INTERVIEW.SKILLS).toString());
      interview.setSkills(skills);
      interview.setStatus(new Integer(row.get(DATASTORES.INTERVIEW.STATUS).toString()));
      interview.setTitle(row.get(DATASTORES.INTERVIEW.TITLE).toString());
      interview.setDt(new Date((Long) row.get(DATASTORES.INTERVIEW.DATE)).getTime());
      interview.setId(row.get("_id").toString());
      if (row.get(DATASTORES.INTERVIEW.FILE) != null)
        interview.setFile(row.get(DATASTORES.INTERVIEW.FILE).toString());

      if (row.get(DATASTORES.INTERVIEW.BUDGET) != null)
        interview.setBudget(row.get(DATASTORES.INTERVIEW.BUDGET).toString());

      if (row.get(DATASTORES.INTERVIEW.EXPERIENCE) != null)
        interview.setExperience(row.get(DATASTORES.INTERVIEW.EXPERIENCE).toString());

      if (row.get(DATASTORES.INTERVIEW.INDUSTRY) != null)
        interview.setIndustry(row.get(DATASTORES.INTERVIEW.INDUSTRY).toString());


      list.add(interview);
    }
    return list;
  }

  public int getTotalinterviewcount(String user, String role,int status) throws RemoteException {

	    DBCollection collection =
	        Services.getInstance().getBaseDataStore().db
	            .getCollection(DATASTORES.INTERVIEW.DBCollection);
	    List<Interview> list = new ArrayList<Interview>();
	    DBObject query = new BasicDBObject();
	    if (role.equals(DATASTORES.INTERVIEW.INTERVIEWEE))
	      query.put(DATASTORES.INTERVIEW.INTERVIEWEE, user);
	    else if (role.equals(DATASTORES.INTERVIEW.INTERVIEWER))
	      query.put(DATASTORES.INTERVIEW.INTERVIEWER, user);
	    if(status!=10)
	    query.put(DATASTORES.INTERVIEW.STATUS, status);
	    int cursor = collection.find(query).count();
	    return cursor;

  }
  
  public int getTotalAwardedInterviewCount(String User) throws RemoteException{
	  DBCollection collection =
		        Services.getInstance().getBaseDataStore().db
		            .getCollection(DATASTORES.INTERVIEW.DBCollection);

		    DBObject query = new BasicDBObject();
		    query.put(DATASTORES.INTERVIEW.INTERVIEWER, User);
		
				    int cursor = collection.find(query).count();
				 return cursor;
	  
  }
  public List<Interview> getDisputableList(String user,String userRole){
	  DBCollection collection =
		        Services.getInstance().getBaseDataStore().db
		            .getCollection(DATASTORES.INTERVIEW.DBCollection);
		    List<Interview> list = new ArrayList<Interview>();
		    DBObject query1,query2,query3;
		    if (userRole.equalsIgnoreCase(DATASTORES.INTERVIEW.INTERVIEWEE))
		     query1 = new BasicDBObject(DATASTORES.INTERVIEW.INTERVIEWEE, user);		   	    	
		    else
		     query1 = new BasicDBObject(DATASTORES.INTERVIEW.INTERVIEWER, user);
		     query2 = new BasicDBObject(DATASTORES.INTERVIEW.ESCROW_BALANCE,new BasicDBObject("$gt", 0));
		     query3 = new BasicDBObject(DATASTORES.INTERVIEW.STATUS,new BasicDBObject("$ne", 9));
		 BasicDBList andList = new BasicDBList();
		 andList.add(query1);
		 andList.add(query2);
		 andList.add(query3);
		 BasicDBObject finalQuery = new BasicDBObject("$and", andList);	    
		  DBCursor cursor = collection.find(finalQuery);
		  while(cursor.hasNext()) {
		    	 DBObject row = cursor.next();
		    	 Interview interview = new Interview();
		    	 interview.setId(row.get(DATASTORES.INTERVIEW.ID).toString());
		    	 interview.setTitle(row.get(DATASTORES.INTERVIEW.TITLE).toString());
		    	 interview.setEb(new Double(row.get(DATASTORES.INTERVIEW.ESCROW_BALANCE).toString()));
		    	 list.add(interview);
		  }
	 return list; 
  }

  public List<Interview> getInterviewsWhereIBid(String user) throws RemoteException {

    List<Interview> list = new ArrayList<Interview>();
    List<Bid> myBids = Services.getInstance().getBidStore().getBidsIPosted(user);
    for (Bid bid : myBids) {
      String iid = bid.getIid();
      Interview interview = getInterview(iid);
      if (interview != null)
        list.add(interview);
    }
    return list;
  }

  
  public List<Interview> getInterviewsWhereIBid(String user,int pageNumber,int status) throws RemoteException {

	    List<Interview> list = new ArrayList<Interview>();
	    List<Bid> myBids = Services.getInstance().getBidStore().getBidsIPosted(user);
	    for (Bid bid : myBids) {
	      String iid = bid.getIid();
	      Interview interview = getInterview(iid);
	      if (interview != null)
	        list.add(interview);
	    }
	    return list;
	  }
  public List<Interview> getAwardedInterview(String user, int pageNum) throws RemoteException{
	  DBCollection collection =
		        Services.getInstance().getBaseDataStore().db
		            .getCollection(DATASTORES.INTERVIEW.DBCollection);

		    DBObject query = new BasicDBObject();
		    query.put(DATASTORES.INTERVIEW.INTERVIEWER, user);
		    List<Interview> list = new ArrayList<Interview>();
		    int documentsToSkip = pageNum > 0 ? (pageNum - 1)
					* (VARIABLES.TRNSACTION_PAGE_SIZE) : 0;
				    DBCursor cursor = collection.find(query).skip(documentsToSkip).limit(VARIABLES.TRNSACTION_PAGE_SIZE).sort(new BasicDBObject(DATASTORES.INTERVIEW.DATE, -1));
				    while (cursor.hasNext()) {
				        DBObject row = cursor.next();
				        Interview interview = new Interview();
				        interview.setBudget(row.get(DATASTORES.INTERVIEW.BUDGET).toString());
				        interview.setTitle(row.get(DATASTORES.INTERVIEW.TITLE).toString());
				        interview.setStatus(new Integer(row.get(DATASTORES.INTERVIEW.STATUS).toString()));
				        interview.setId(row.get(DATASTORES.INTERVIEW.ID).toString());
				        list.add(interview);
				    }
		 
	return list;	  
  }
  public Interview getInterview(String _id,int pageNumber,int status) throws RemoteException{

	    DBCollection collection =
	        Services.getInstance().getBaseDataStore().db
	            .getCollection(DATASTORES.INTERVIEW.DBCollection);

	    DBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(_id));
	    DBObject row = collection.findOne(query);

	    Interview interview = null;
	    if (row != null) {

	      interview = new Interview();
	      // interview = Services.getInstance().getJSONUtilityService().readValue(row.toString(),
	      // Interview.class);
	      interview.setDescription(row.get(DATASTORES.INTERVIEW.DESCRIPTION).toString());
	      interview.setEb(new Double(row.get(DATASTORES.INTERVIEW.ESCROW_BALANCE).toString()));
	      interview.setInterviewee(row.get(DATASTORES.INTERVIEW.INTERVIEWEE).toString());
	      interview.setInterviewer(row.get(DATASTORES.INTERVIEW.INTERVIEWER).toString());
	      List<String> skills = new ArrayList<String>();
	      BasicDBList listDBSkills = (BasicDBList) row.get(DATASTORES.INTERVIEW.SKILLS);
	      Iterator<Object> it = listDBSkills.iterator();
	      while (it.hasNext()) {
	        skills.add(it.next().toString());
	      }
	      interview.setSkills(skills);
	      interview.setStatus(new Integer(row.get(DATASTORES.INTERVIEW.STATUS).toString()));
	      interview.setTitle(row.get(DATASTORES.INTERVIEW.TITLE).toString());
	      interview.setDt(new Date((Long) row.get(DATASTORES.INTERVIEW.DATE)).getTime());
	      interview.setId(row.get("_id").toString());
	      if (row.get(DATASTORES.INTERVIEW.FILE) != null)
	        interview.setFile(row.get(DATASTORES.INTERVIEW.FILE).toString());

	      if (row.get(DATASTORES.INTERVIEW.BUDGET) != null)
	        interview.setBudget(row.get(DATASTORES.INTERVIEW.BUDGET).toString());

	      if (row.get(DATASTORES.INTERVIEW.EXPERIENCE) != null)
	        interview.setExperience(row.get(DATASTORES.INTERVIEW.EXPERIENCE).toString());

	      if (row.get(DATASTORES.INTERVIEW.INDUSTRY) != null)
	        interview.setIndustry(row.get(DATASTORES.INTERVIEW.INDUSTRY).toString());


	    }
	    return interview;
	  
	  
  }
  public Interview getInterview(String _id) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db
            .getCollection(DATASTORES.INTERVIEW.DBCollection);

    DBObject query = new BasicDBObject();
    query.put("_id", new ObjectId(_id));
    DBObject row = collection.findOne(query);

    Interview interview = null;
    if (row != null) {

      interview = new Interview();
      // interview = Services.getInstance().getJSONUtilityService().readValue(row.toString(),
      // Interview.class);
      interview.setDescription(row.get(DATASTORES.INTERVIEW.DESCRIPTION).toString());
      interview.setEb(new Double(row.get(DATASTORES.INTERVIEW.ESCROW_BALANCE).toString()));
      interview.setInterviewee(row.get(DATASTORES.INTERVIEW.INTERVIEWEE).toString());
      interview.setInterviewer(row.get(DATASTORES.INTERVIEW.INTERVIEWER).toString());
      List<String> skills = new ArrayList<String>();
      BasicDBList listDBSkills = (BasicDBList) row.get(DATASTORES.INTERVIEW.SKILLS);
      Iterator<Object> it = listDBSkills.iterator();
      while (it.hasNext()) {
        skills.add(it.next().toString());
      }
      interview.setSkills(skills);
      interview.setStatus(new Integer(row.get(DATASTORES.INTERVIEW.STATUS).toString()));
      interview.setTitle(row.get(DATASTORES.INTERVIEW.TITLE).toString());
      interview.setDt(new Date((Long) row.get(DATASTORES.INTERVIEW.DATE)).getTime());
      interview.setId(row.get("_id").toString());
      if (row.get(DATASTORES.INTERVIEW.FILE) != null)
        interview.setFile(row.get(DATASTORES.INTERVIEW.FILE).toString());

      if (row.get(DATASTORES.INTERVIEW.BUDGET) != null)
        interview.setBudget(row.get(DATASTORES.INTERVIEW.BUDGET).toString());

      if (row.get(DATASTORES.INTERVIEW.EXPERIENCE) != null)
        interview.setExperience(row.get(DATASTORES.INTERVIEW.EXPERIENCE).toString());

      if (row.get(DATASTORES.INTERVIEW.INDUSTRY) != null)
        interview.setIndustry(row.get(DATASTORES.INTERVIEW.INDUSTRY).toString());


    }
    return interview;
  }


  public Bid getAcceptedBidForInterview(ObjectId iid) throws RemoteException {
    Interview interview = getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.PENDING)
      return null;

    String interviewer = interview.getInterviewer();
    List<Bid> bids =
        Services.getInstance().getBidStore().getBidsReceivedForInterview(iid.toString());
    for (Bid bid : bids) {
      if (bid.getBidder().equals(interviewer))
        return bid;
    }
    return null;
  }

  public Interview getInterview(ObjectId _id) throws RemoteException {
    return getInterview(_id.toString());
  }

  public void updateInterviewStatus(ObjectId _id, int inprogress) {

    DBCollection collection =
        Services.getInstance().getBaseDataStore().db
            .getCollection(DATASTORES.INTERVIEW.DBCollection);
    DBObject query = new BasicDBObject();
    query.put("_id", _id);
    BasicDBObject updateDoc =
        new BasicDBObject("$set", new BasicDBObject(DATASTORES.INTERVIEW.STATUS, inprogress));

    collection.update(query, updateDoc);
  }

  public void depositEscrowBalance(ObjectId _id, double amount) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db
            .getCollection(DATASTORES.INTERVIEW.DBCollection);
    DBObject query = new BasicDBObject();
    query.put("_id", _id);

    DBObject row = collection.findOne(query);
    double balance = new Double(row.get(DATASTORES.INTERVIEW.ESCROW_BALANCE).toString());
    balance = balance + amount;
    BasicDBObject updateDoc =
        new BasicDBObject("$set", new BasicDBObject(DATASTORES.INTERVIEW.ESCROW_BALANCE, balance));
    collection.update(query, updateDoc);
  }

  public void withdrawFromEscrow(ObjectId _id, double amount) throws RemoteException {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db
            .getCollection(DATASTORES.INTERVIEW.DBCollection);
    DBObject query = new BasicDBObject();
    query.put("_id", _id);

    DBObject row = collection.findOne(query);
    double balance = new Double(row.get(DATASTORES.INTERVIEW.ESCROW_BALANCE).toString());
    balance = balance - amount;
    BasicDBObject updateDoc =
        new BasicDBObject("$set", new BasicDBObject(DATASTORES.INTERVIEW.ESCROW_BALANCE, balance));
    collection.update(query, updateDoc);
  }

  public boolean deleteInterivew(ObjectId iid) {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db
            .getCollection(DATASTORES.INTERVIEW.DBCollection);
    DBObject doc = new BasicDBObject("_id", iid);
    WriteResult wr = collection.remove(doc);
    CommandResult cr = wr.getCachedLastError();
    return cr.ok();
  }

  public void updateInterview(ObjectId id, Map<String, Object> changes) {
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db
            .getCollection(DATASTORES.INTERVIEW.DBCollection);
    DBObject query = new BasicDBObject("_id", id);
    BasicDBObject updateDoc = new BasicDBObject();
    Iterator<String> it = changes.keySet().iterator();
    while (it.hasNext()) {
      String key = it.next();
      if (key.equals(DATASTORES.INTERVIEW.TITLE))
        updateDoc.append(DATASTORES.INTERVIEW.TITLE, changes.get(DATASTORES.INTERVIEW.TITLE));
      if (key.equals(DATASTORES.INTERVIEW.SKILLS))
        updateDoc.append(DATASTORES.INTERVIEW.SKILLS, changes.get(DATASTORES.INTERVIEW.SKILLS));
      if (key.equals(DATASTORES.INTERVIEW.DESCRIPTION))
        updateDoc.append(DATASTORES.INTERVIEW.DESCRIPTION,
            changes.get(DATASTORES.INTERVIEW.DESCRIPTION));
      if (key.equals(DATASTORES.INTERVIEW.STATUS))
        updateDoc.append(DATASTORES.INTERVIEW.STATUS, changes.get(DATASTORES.INTERVIEW.STATUS));
      if (key.equals(DATASTORES.INTERVIEW.INTERVIEWER))
        updateDoc.append(DATASTORES.INTERVIEW.INTERVIEWER,
            changes.get(DATASTORES.INTERVIEW.INTERVIEWER));
      if (key.equals(DATASTORES.INTERVIEW.ESCROW_BALANCE))
        updateDoc.append(DATASTORES.INTERVIEW.ESCROW_BALANCE,
            changes.get(DATASTORES.INTERVIEW.ESCROW_BALANCE));
      if (key.equals(DATASTORES.INTERVIEW.BUDGET))
          updateDoc.append(DATASTORES.INTERVIEW.BUDGET,
              changes.get(DATASTORES.INTERVIEW.BUDGET));
      if (key.equals(DATASTORES.INTERVIEW.INDUSTRY))
          updateDoc.append(DATASTORES.INTERVIEW.INDUSTRY,
              changes.get(DATASTORES.INTERVIEW.INDUSTRY));
      if (key.equals(DATASTORES.INTERVIEW.EXPERIENCE))
          updateDoc.append(DATASTORES.INTERVIEW.EXPERIENCE,
              changes.get(DATASTORES.INTERVIEW.EXPERIENCE));


    }
    updateDoc.append(DATASTORES.INTERVIEW.DATE, new Date().getTime());
    BasicDBObject update = new BasicDBObject().append("$set", updateDoc);
    collection.update(query, update);
  }

  @Override
  public Map<String, String> getInterviewsStatuses(Collection<String> iids) throws RemoteException {
    Map<String, String> result = new HashMap<String, String>();
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db
            .getCollection(DATASTORES.INTERVIEW.DBCollection);
    ArrayList<ObjectId> vals = new ArrayList<ObjectId>();
    for (String iid : iids) {
      vals.add(new ObjectId(iid));
    }   
    DBObject query = new BasicDBObject("_id", new BasicDBObject("$in", vals));
    DBCursor cursor = collection.find(query);
    while (cursor.hasNext()) {
      DBObject row = cursor.next();      
      result.put(row.get("_id").toString(), row.get(DATASTORES.INTERVIEW.STATUS).toString());
    }
    return result;
  }

  @Override
  public Map<String, String> getInterviewsFiles(Collection<String> iids) throws RemoteException {
    Map<String, String> result = new HashMap<String, String>();
    DBCollection collection =
        Services.getInstance().getBaseDataStore().db
            .getCollection(DATASTORES.INTERVIEW.DBCollection);
    ArrayList<ObjectId> vals = new ArrayList<ObjectId>();
    for (String iid : iids) {
      vals.add(new ObjectId(iid));
    }   
    DBObject query = new BasicDBObject("_id", new BasicDBObject("$in", vals));
    DBCursor cursor = collection.find(query);
    while (cursor.hasNext()) {
      DBObject row = cursor.next();            
      result.put(row.get("_id").toString(), MongoDataHelper.getStringValue(row, DATASTORES.INTERVIEW.FILE));
    }
    return result;
  }  
}
