package com.interview.datastore;

import java.net.UnknownHostException;
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

import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Education;
import com.interview.framework.pojo.Interviewer;
import com.interview.framework.pojo.Position;
import com.interview.framework.pojo.Rating;
import com.interview.framework.pojo.Skill;
import com.interview.framework.rmi.common.IInterviewerDataStore;
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
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class InterviewerDataStore extends UnicastRemoteObject implements IInterviewerDataStore {

	private static final long serialVersionUID = 1L;

	public InterviewerDataStore() throws RemoteException {
		Services.getInstance().getRMIServer().bind(NAME, this);
	}

	public void insertInterviewer(Map<String, String> interviewerMap) throws RemoteException {}

	public int insertInterviewer(Interviewer interviewer) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject dbObject = new BasicDBObject();
		dbObject.put(USER.USERNAME, interviewer.getUsername());
		dbObject.put(USER.EMAIL, interviewer.getId());
		dbObject.put(USER.PASSWORD, interviewer.getPassword());
		dbObject.put(USER.TYPE, interviewer.getType());
		dbObject.put(USER.COMPANIES, interviewer.getCompanies());
		dbObject.put(USER.SKILLS, interviewer.getSkills());
		dbObject.put(USER.COUNTRY, interviewer.getCountry());
		dbObject.put(USER.CV, interviewer.getCv());
		dbObject.put(USER.RATE, interviewer.getRate());
		dbObject.put(USER.BALANCE, interviewer.getBalance());
		dbObject.put(USER.RATING, 0);
		dbObject.put(USER.PROFILE_PIC, interviewer.getProfilePic());
		dbObject.put(USER.CHATPASS, interviewer.getChatPass());
		dbObject.put(USER.INDUSTRY, interviewer.getIndustries());
		dbObject.put(USER.EMAILHASH, interviewer.getEmailHash());
		dbObject.put(USER.ACTIVE, interviewer.getActive());

		List<Education> educations = interviewer.getEducations();
		String[] educationIds = new String[educations.size()];
		for (int i = 0; i < educations.size(); i++) {
			Education education = educations.get(i);
			ObjectId id = Services.getInstance().getEducationStore().insert(education);
			educationIds[i] = id.toString();
		}
		dbObject.put(USER.EDUCATIONS, educationIds);

		List<Position> positions = interviewer.getPositions();
		String[] posIds = new String[positions.size()];
		for (int i = 0; i < positions.size(); i++) {
			Position position = positions.get(i);
			ObjectId id = Services.getInstance().getPositionStore().insert(position);
			posIds[i] = id.toString();
		}
		dbObject.put(USER.POSITIONS, posIds);

		List<Skill> skills = interviewer.getSkillList();
		String[] skillIds = new String[skills.size()];
		for (int i = 0; i < skills.size(); i++) {
			Skill skill = skills.get(i);
			ObjectId id = Services.getInstance().getSkillDataStore().insert(skill);
			skillIds[i] = id.toString();
		}
		dbObject.put(USER.SKILL_IDS, skillIds);

		dbObject.put(USER.SOCIAL_NETWORK, interviewer.getUserSocialNetwork());
		dbObject.put(USER.TIME, new Date().getTime());

		int isExistUserNameOrEmail = isExistUserNameOrEmail(dbObject);
		if (isExistUserNameOrEmail == 3 || isExistUserNameOrEmail == 4
				|| isExistUserNameOrEmail == -1) {
			return isExistUserNameOrEmail;
		} else {
			collection.insert(dbObject);
			return 1;
		}
	}

	public boolean updateUserInfo(Interviewer interviewer) throws RemoteException {

		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query = new BasicDBObject(USER.USERNAME, interviewer.getUsername());

		DBObject obj = collection.findOne(query);
		if (obj != null) {
			List<Education> educations = getAllEducations(obj);
			for (Education education : educations) {
				Services.getInstance().getEducationStore()
				.deleteEducation(new ObjectId(education.get_id()));
			}

			List<Position> positions = getAllPositions(obj);
			for (Position position : positions) {
				Services.getInstance().getPositionStore().deletePosition(new ObjectId(position.get_id()));
			}

			List<Skill> skills = getAllSkills(obj);
			for (Skill skill : skills) {
				Services.getInstance().getSkillDataStore().deleteSkill(new ObjectId(skill.get_id()));
			}

		}

		// insert educations
		List<Education> educations = interviewer.getEducations();
		String[] educationIds = new String[educations.size()];
		for (int i = 0; i < educations.size(); i++) {
			Education education = educations.get(i);
			ObjectId id = Services.getInstance().getEducationStore().insert(education);
			educationIds[i] = id.toString();
		}

		List<Position> positions = interviewer.getPositions();
		String[] posIds = new String[positions.size()];
		for (int i = 0; i < positions.size(); i++) {
			Position position = positions.get(i);
			ObjectId id = Services.getInstance().getPositionStore().insert(position);
			posIds[i] = id.toString();
		}

		List<Skill> skills = interviewer.getSkillList();
		String[] skillsIds = new String[skills.size()];
		for (int i = 0; i < skills.size(); i++) {
			Skill skill = skills.get(i);
			ObjectId id = Services.getInstance().getSkillDataStore().insert(skill);
			skillsIds[i] = id.toString();
		}

		DBObject updateDoc = new BasicDBObject();// ("$set", new BasicDBObject(USER.PASSWORD, pass));
		DBObject fieldSet = new BasicDBObject();
		if (interviewer.getCompanies() != null)
			fieldSet.put(USER.COMPANIES, interviewer.getCompanies());
		if (interviewer.getSkills() != null)
			fieldSet.put(USER.SKILLS, interviewer.getSkills());
		if (interviewer.getCountry() != null)
			fieldSet.put(USER.COUNTRY, interviewer.getCountry());
		if (interviewer.getCv() != null)
			fieldSet.put(USER.CV, interviewer.getCv());
		if (interviewer.getRate() != -1)
			fieldSet.put(USER.RATE, interviewer.getRate());
		if (interviewer.getProfilePic() != null)
			fieldSet.put(USER.PROFILE_PIC, interviewer.getProfilePic());
		if (interviewer.getPhoneNumber() != null) {
			fieldSet.put(USER.PHONE_NUMBER, interviewer.getPhoneNumber());
		}

		fieldSet.put(USER.EDUCATIONS, educationIds);
		fieldSet.put(USER.POSITIONS, posIds);
		fieldSet.put(USER.SKILL_IDS, skillsIds);

		updateDoc.put("$set", fieldSet);
		WriteResult wr = collection.update(query, updateDoc);
		CommandResult cr = wr.getCachedLastError();
		return cr.ok();

	}

	public int insertInterviewer(String interviewerJSON) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject dbObject = (DBObject) JSON.parse(interviewerJSON);
		if (isExist(dbObject))
			return 2;
		else {
			collection.insert(dbObject);
			return 1;
		}
	}

	public boolean setPasswordForUserName(String username, String pass) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query = new BasicDBObject(USER.USERNAME, username);
		DBObject updateDoc = new BasicDBObject("$set", new BasicDBObject(USER.PASSWORD, pass));
		WriteResult wr = collection.update(query, updateDoc);
		CommandResult cr = wr.getCachedLastError();
		return cr.ok();
	}

	public boolean setActiveAfterEmailVrification(String emailHash) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query = new BasicDBObject(USER.EMAILHASH, emailHash);
		int count = collection.find(query).count();
		if (count == 1){
			DBObject updateDoc = new BasicDBObject("$set", new BasicDBObject(USER.ACTIVE, "1"));
			WriteResult wr = collection.update(query, updateDoc);
			CommandResult cr = wr.getCachedLastError();
			return cr.ok();
		}else{
			return false;
		}
	}

	public boolean isExist(DBObject data) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		if (!((String) data.get("username")).isEmpty()) {
			long countForEmail = collection.count(new BasicDBObject("id", data.get("id")));
			long countForUserName = collection.count(new BasicDBObject("username", data.get("username")));
			if (countForEmail > 0 || countForUserName > 0)
				return true;
			else
				return false;
		} else
			return false;
	}

	public int isExistUserNameOrEmail(DBObject data) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		if (!((String) data.get("username")).isEmpty()) {
			long countForEmail = collection.count(new BasicDBObject("id", data.get("id")));
			long countForUserName = collection.count(new BasicDBObject("username", data.get("username")));
			if (countForEmail > 0)
				return 3;
			else if (countForUserName > 0)
				return 4;
			else
				return 1;
		}
		return -1;
	}

	public String getType(String username) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query = new BasicDBObject(USER.USERNAME, username);
		DBCursor cursor = collection.find(query);
		String type = "";
		if (cursor.hasNext()) {
			DBObject row = cursor.next();
			type = row.get(USER.TYPE).toString();
		}
		return type;
	}

	public Map<String, Object> getUserInfo(String usernameOrUserEmail) throws RemoteException {

		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query1 = new BasicDBObject(USER.USERNAME, usernameOrUserEmail);
		DBObject query2 = new BasicDBObject(USER.EMAIL, usernameOrUserEmail);

		BasicDBList orList = new BasicDBList();
		orList.add(query1);
		orList.add(query2);
		BasicDBObject finalQuery = new BasicDBObject("$or", orList);

		Map<String, Object> responseMap = new HashMap<String, Object>();
		DBObject obj = collection.findOne(finalQuery);
		if (obj != null) {
			responseMap.put(USER.USERNAME, (String) obj.get(USER.USERNAME));
			responseMap.put(USER.SKILLS, obj.get(USER.SKILLS).toString());
			responseMap.put(USER.RATE, obj.get(USER.RATE).toString());
			responseMap.put(USER.COUNTRY, (String) obj.get(USER.COUNTRY));
			responseMap.put(USER.BALANCE, obj.get(USER.BALANCE).toString());
			responseMap.put(USER.COMPANIES, obj.get(USER.COMPANIES).toString());
			responseMap.put(USER.RATING, obj.get(USER.RATING).toString());
			responseMap.put(USER.CV, obj.get(USER.CV).toString());
			responseMap.put(USER.TYPE, obj.get(USER.TYPE).toString());
			responseMap.put(USER.EMAIL, obj.get(USER.EMAIL).toString());
			if (obj.get(USER.PHONE_NUMBER) != null)
				responseMap.put(USER.PHONE_NUMBER, obj.get(USER.PHONE_NUMBER));
			if (obj.get(USER.CHATPASS) != null)
				responseMap.put(USER.CHATPASS, obj.get(USER.CHATPASS).toString());
			responseMap.put(USER.PROFILE_PIC, obj.get(USER.PROFILE_PIC).toString());
			if (obj.get(USER.SOCIAL_NETWORK) != null)
				responseMap.put(USER.SOCIAL_NETWORK, obj.get(USER.SOCIAL_NETWORK).toString());
			else
				responseMap.put(USER.SOCIAL_NETWORK, Interviewer.SOCIALNETWORKS.DIRECT);

			List<Map<String, Object>> ratingmap = getAllReviews(obj);
			responseMap.put(VARIABLES.ALLREVIEWS, ratingmap);

			List<Education> educations = getAllEducations(obj);
			responseMap.put(USER.EDUCATIONS, educations);

			List<Position> positions = getAllPositions(obj);
			responseMap.put(USER.POSITIONS, positions);

			List<Skill> skills = getAllSkills(obj);
			responseMap.put(USER.SKILL_LIST, skills);
			
			responseMap.put(USER.EMAILHASH, String.valueOf(obj.get(USER.EMAILHASH)));
		}
		return responseMap;
	}

	public Map<String, Object> getUserCredentials(String emailID) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);

		BasicDBList orList = new BasicDBList();
		orList.add(new BasicDBObject(USER.EMAIL, emailID));
		orList.add(new BasicDBObject(USER.USERNAME, emailID));
		BasicDBObject clause = new BasicDBObject("$or", orList);

		Map<String, Object> responseMap = new HashMap<String, Object>();
		DBObject obj = collection.findOne(clause);
		responseMap.put(USER.USERNAME, (String) obj.get(USER.USERNAME));
		responseMap.put(USER.EMAIL, (String) obj.get(USER.EMAIL));
		responseMap.put(USER.PASSWORD, (String) obj.get(USER.PASSWORD));
		responseMap.put(USER.TYPE, (String) obj.get(USER.TYPE));
		return responseMap;
	}

	public double updateBalance(String interviewer, double amount, int OPTION) {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query = new BasicDBObject();
		query.put(USER.USERNAME, interviewer);

		DBObject row = collection.findOne(query);
		Double balance = new Double(row.get(USER.BALANCE).toString());
		if (OPTION == VARIABLES.ADD)
			balance = balance + amount;
		else if (OPTION == VARIABLES.SUB)
			balance = balance - amount;
		DBObject updateDoc = new BasicDBObject("$set", new BasicDBObject(USER.BALANCE, balance));
		collection.update(query, updateDoc);
		return balance;
	}

	public void setBalance(String interviewer, double amount) {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query = new BasicDBObject();
		query.put(USER.USERNAME, interviewer);

		DBObject row = collection.findOne(query);
		if (row.get(USER.USERNAME).toString().equals(interviewer)) {
			DBObject updateDoc = new BasicDBObject("$set", new BasicDBObject(USER.BALANCE, amount));
			collection.update(query, updateDoc);
		}
	}

	public void updateUserPaypaladdress(String username, String paypaladdress)
			throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query = new BasicDBObject();
		query.put(USER.USERNAME, username);
		DBObject updateDoc =
				new BasicDBObject("$set", new BasicDBObject(USER.PAYPAL_ADDRESS, paypaladdress));
		collection.update(query, updateDoc);
	}

	public void setRating(String user, double currentAvgRating) {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query = new BasicDBObject();
		query.put(USER.USERNAME, user);

		DBObject row = collection.findOne(query);
		if (row.get(USER.USERNAME).toString().equals(user)) {
			DBObject updateDoc = new BasicDBObject("$set", new BasicDBObject("rating", currentAvgRating));
			collection.update(query, updateDoc);
		}
	}

	
	public Map<String, Object> searchAdvisors(String searchKey) throws RemoteException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
	
		
		
//		DB db = mongo.getDB("interviewbackend");
//		DBCollection collection = db.getCollection("interviewer");

		
		final DBObject textSearchCommand = new BasicDBObject();
	    textSearchCommand.put("text", USER.DBCollection);
	    textSearchCommand.put("search", searchKey);
	    final CommandResult commandResult = Services.getInstance().getBaseDataStore().db.command(textSearchCommand);
//	    final CommandResult commandResult = db.command(textSearchCommand);
	    
	    BasicDBList results = (BasicDBList)commandResult.get("results");

	    if(results  == null)
	    	return responseMap;
	    
	    for (Iterator<Object> it = results.iterator();it.hasNext();)
	    {
	    	BasicDBObject res  = (BasicDBObject) it.next();
	        BasicDBObject obj = (BasicDBObject)res.get("obj");
	        String score = String.valueOf(res.get("score"));
	        
	        Map<String, Object> response = new HashMap<String, Object>();
	        
			response.put("_id", String.valueOf(obj.get("_id")));
			response.put(USER.USERNAME, (String) obj.get(USER.USERNAME));
			response.put(USER.RATE, obj.get(USER.RATE).toString());
			response.put(USER.COUNTRY, (String) obj.get(USER.COUNTRY));
			response.put(USER.COMPANIES, obj.get(USER.COMPANIES).toString());
			response.put(USER.RATING, obj.get(USER.RATING).toString());
			response.put(USER.CV, obj.get(USER.CV).toString());
			response.put(USER.PROFILE_PIC, obj.get(USER.PROFILE_PIC).toString());
			response.put("score", score);
			
			List<Map<String, Object>> ratingmap = getAllReviews(obj);
			response.put(VARIABLES.ALLREVIEWS, ratingmap);

			List<Education> educations = getAllEducations(obj);
			response.put(USER.EDUCATIONS, educations);

			List<Position> positions = getAllPositions(obj);
			response.put(USER.POSITIONS, positions);

			List<Skill> skills = getAllSkills(obj);
			response.put(USER.SKILLS, skills);
			responseMap.put(String.valueOf(obj.get(USER.USERNAME)), response);
		}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseMap;
	}
	
	
	public Map<String, Object> getAdditionalData(Map<Object, Object> users) throws RemoteException {

		Map<String, Object> response = new HashMap<String, Object>();
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);

		BasicDBList orList = new BasicDBList();
		Iterator<Object> it = users.keySet().iterator();
		while (it.hasNext()) {
			String user = it.next().toString();
			DBObject query1 = new BasicDBObject(USER.USERNAME, user);
			orList.add(query1);
		}

		BasicDBObject clause = new BasicDBObject("$or", orList);
		DBCursor cursor = collection.find(clause);
		while (cursor.hasNext()) {
			DBObject row = cursor.next();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put(USER.RATING, row.get(USER.RATING));
			data.put(USER.PROFILE_PIC, row.get(USER.PROFILE_PIC).toString());
			List<Education> educations = getAllEducations(row);
			data.put(USER.EDUCATIONS, educations);

			List<Position> positions = getAllPositions(row);
			data.put(USER.POSITIONS, positions);
			response.put(row.get(USER.USERNAME).toString(), data);
		}
		return response;
	}

	public List<String> getMatchingUsersList(List<String> skills, List<String> companies,
			String country) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);

		List<String> usersList = new ArrayList<String>();
		BasicDBList orList = new BasicDBList();

		if (skills != null && skills.size() > 0) {
			DBObject skillsQuery = new BasicDBObject(USER.SKILLS, new BasicDBObject("$in", skills));
			orList.add(skillsQuery);
		}
		if (companies != null && companies.size() > 0) {
			DBObject companyQuery =
					new BasicDBObject(USER.COMPANIES, new BasicDBObject("$in", companies));
			orList.add(companyQuery);
		}
		if (country != null && country.trim().length() > 0) {
			DBObject countryQuery = new BasicDBObject(USER.COUNTRY, country);
			orList.add(countryQuery);
		}
		BasicDBObject finalQuery = new BasicDBObject("$or", orList);
		DBCursor cursor = collection.find(finalQuery);
		while (cursor.hasNext()) {
			DBObject row = cursor.next();
			usersList.add(row.get("username").toString());
		}

		return usersList;
	}

	@Override
	public Map<String, String> getEmailListFromUsersList(List<String> users) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);

		Map<String, String> userEmailMap = new HashMap<String, String>();
		BasicDBList userList = new BasicDBList();
		if (users.size() > 0) {
			DBObject usersQuery = new BasicDBObject(USER.USERNAME, new BasicDBObject("$in", users));
			userList.add(usersQuery);
		}
		DBCursor cursor = collection.find(userList);
		while (cursor.hasNext()) {
			DBObject row = cursor.next();
			userEmailMap.put(row.get("username").toString(), row.get("id").toString());
		}

		return userEmailMap;
	}


	@Override
	public String getUserEmail(String username) throws RemoteException {

		DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
		DBObject query = new BasicDBObject(USER.USERNAME, username);
		DBCursor cursor = collection.find(query);
		String email = "";
		if (cursor.hasNext()) {
			DBObject row = cursor.next();
			email = row.get(USER.EMAIL).toString();
		}
		return email;
	}

	@Override
	public boolean isUserEmailExist(String emailAddress) throws RemoteException {
		DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
		DBObject query = new BasicDBObject(USER.EMAIL, emailAddress);

		DBObject row = collection.findOne(query);
		if (row != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean isUserNameExist(String username) throws RemoteException {
		DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
		DBObject query = new BasicDBObject(USER.USERNAME, username);

		DBObject row = collection.findOne(query);
		if (row != null)
			return true;
		else
			return false;
	}

	@Override
	public Map<String, String> getPics(Collection<String> usernames) throws RemoteException {
		Map<String, String> result = new HashMap<String, String>();
		DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

		DBObject query = new BasicDBObject(USER.USERNAME, new BasicDBObject("$in", usernames));
		DBCursor cursor = collection.find(query);
		while (cursor.hasNext()) {
			DBObject row = cursor.next();
			result.put(row.get(USER.USERNAME).toString(), row.get(USER.PROFILE_PIC).toString());
		}
		return result;
	}

	@Override
	public Map<String, Object> getUserExternalInfo(String username) throws RemoteException {

		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query1 = new BasicDBObject(USER.USERNAME, username);
		DBObject query2 = new BasicDBObject(USER.EMAIL, username);

		BasicDBList orList = new BasicDBList();
		orList.add(query1);
		orList.add(query2);

		BasicDBObject finalQuery = new BasicDBObject("$or", orList);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		DBObject obj = collection.findOne(finalQuery);
		if (obj != null) {

			List<String> skills = new ArrayList<String>();
			BasicDBList skillsArray = (BasicDBList) obj.get(USER.SKILLS);
			Iterator<Object> itSkills = skillsArray.iterator();
			while (itSkills.hasNext()) {
				skills.add((String) itSkills.next());
			}
			responseMap.put(USER.SKILLS, skills);

			responseMap.put(USER.USERNAME, (String) obj.get(USER.USERNAME));
			responseMap.put(USER.RATE, obj.get(USER.RATE).toString());
			responseMap.put(USER.COUNTRY, (String) obj.get(USER.COUNTRY));
			responseMap.put(USER.COMPANIES, obj.get(USER.COMPANIES).toString());
			responseMap.put(USER.RATING, obj.get(USER.RATING).toString());
			responseMap.put(USER.CV, obj.get(USER.CV).toString());
			responseMap.put(USER.TYPE, obj.get(USER.TYPE).toString());
			responseMap.put(USER.BALANCE, obj.get(USER.BALANCE).toString());
			responseMap.put(USER.PROFILE_PIC, obj.get(USER.PROFILE_PIC).toString());
			if (obj.get(USER.PHONE_NUMBER) != null)
				responseMap.put(USER.PHONE_NUMBER, obj.get(USER.PHONE_NUMBER));

			List<Map<String, Object>> ratingmap = getAllReviews(obj);
			responseMap.put(VARIABLES.ALLREVIEWS, ratingmap);


			List<Education> educations = getAllEducations(obj);
			responseMap.put(USER.EDUCATIONS, educations);

			List<Position> positions = getAllPositions(obj);
			responseMap.put(USER.POSITIONS, positions);

			List<Skill> skillList = getAllSkills(obj);
			responseMap.put(USER.SKILL_LIST, skillList);
		}
		return responseMap;
	}

	private List<Position> getAllPositions(DBObject obj) throws RemoteException {
		BasicDBList positionIDs = (BasicDBList) obj.get(USER.POSITIONS);
		List<Position> positions = new ArrayList<Position>();
		Iterator<Object> it = positionIDs.iterator();
		while (it.hasNext()) {
			String positionID = (String) it.next();
			Position position =
					Services.getInstance().getPositionStore().getPosition(new ObjectId(positionID));
			positions.add(position);
		}
		return positions;
	}

	private List<Education> getAllEducations(DBObject obj) throws RemoteException {
		BasicDBList educationIDs = (BasicDBList) obj.get(USER.EDUCATIONS);
		List<Education> educations = new ArrayList<Education>();
		Iterator<Object> it = educationIDs.iterator();
		while (it.hasNext()) {
			String educationID = (String) it.next();
			Education education =
					Services.getInstance().getEducationStore().getEducation(new ObjectId(educationID));
			educations.add(education);
		}
		return educations;
	}

	private List<Skill> getAllSkills(DBObject obj) throws RemoteException {
		List<Skill> skills = new ArrayList<Skill>();
		BasicDBList skillIDs = (BasicDBList) obj.get(USER.SKILL_IDS);
		if (null != skillIDs) {
			Iterator<Object> it = skillIDs.iterator();
			while (it.hasNext()) {
				String skillID = (String) it.next();
				if (null != skillID) {
					Skill skill = Services.getInstance().getSkillDataStore().getSkill(new ObjectId(skillID));
					skills.add(skill);
				}
			}
		}
		return skills;
	}

	private List<Map<String, Object>> getAllReviews(DBObject obj) throws RemoteException {
		List<Rating> allRatings =
				Services.getInstance().getRatingStore().getAllRatings((String) obj.get(USER.USERNAME));
		List<Map<String, Object>> ratingmap = new ArrayList<Map<String, Object>>();
		for (Rating rating : allRatings) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("average", rating.getAverage() + "");
			map.put("message", rating.getMessage());
			map.put("ratedby", rating.getRatedBy());
			map.put("dt", rating.getTime() + "");
			map.put("ititle",
					Services.getInstance().getInterviewDataStore().getInterview(rating.getIid()).getTitle());

			ratingmap.add(map);
		}
		return ratingmap;
	}

	public void updateProfilePic(String interviewer, String profilePic) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query = new BasicDBObject();
		query.put(USER.USERNAME, interviewer);

		DBObject row = collection.findOne(query);
		if (row.get(USER.USERNAME).toString().equals(interviewer)) {
			DBObject updateDoc =
					new BasicDBObject("$set", new BasicDBObject(USER.PROFILE_PIC, profilePic));
			collection.update(query, updateDoc);
		}
	}

	@Override
	public String getUserPassword(String username) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);
		DBObject query = new BasicDBObject();
		query.put(USER.USERNAME, username);

		DBObject row = collection.findOne(query);
		return row.get(USER.PASSWORD).toString();
	}

	@Override
	public List<Object> getTopAdvisorList(int noOfResult) throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);

		List<Object> result = new ArrayList<Object>();
		DBCursor cursor = collection.find(new BasicDBObject(USER.TYPE, "INTERVIEWER"))
				.sort(new BasicDBObject(USER.RATING, -1)).limit(noOfResult);
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put(USER.USERNAME, (String) obj.get(USER.USERNAME));
			responseMap.put(USER.SKILLS, obj.get(USER.SKILLS).toString());
			responseMap.put(USER.RATE, obj.get(USER.RATE).toString());
			responseMap.put(USER.COUNTRY, (String) obj.get(USER.COUNTRY));
			responseMap.put(USER.BALANCE, obj.get(USER.BALANCE).toString());
			responseMap.put(USER.COMPANIES, obj.get(USER.COMPANIES).toString());
			responseMap.put(USER.RATING, obj.get(USER.RATING).toString());
			responseMap.put(USER.CV, obj.get(USER.CV).toString());
			responseMap.put(USER.TYPE, obj.get(USER.TYPE).toString());
			responseMap.put(USER.EMAIL, obj.get(USER.EMAIL).toString());
			if (obj.get(USER.CHATPASS) != null)
				responseMap.put(USER.CHATPASS, obj.get(USER.CHATPASS).toString());
			responseMap.put(USER.PROFILE_PIC, obj.get(USER.PROFILE_PIC).toString());
			if (obj.get(USER.SOCIAL_NETWORK) != null)
				responseMap.put(USER.SOCIAL_NETWORK, obj.get(USER.SOCIAL_NETWORK).toString());
			else
				responseMap.put(USER.SOCIAL_NETWORK, Interviewer.SOCIALNETWORKS.DIRECT);

			List<Map<String, Object>> ratingmap = getAllReviews(obj);
			responseMap.put(VARIABLES.ALLREVIEWS, ratingmap);

			List<Education> educations = getAllEducations(obj);
			responseMap.put(USER.EDUCATIONS, educations);

			List<Position> positions = getAllPositions(obj);
			responseMap.put(USER.POSITIONS, positions);

			List<Skill> skills = getAllSkills(obj);
			responseMap.put(USER.SKILL_LIST, skills);
			result.add(responseMap);
		}
		return result;
	}

	@Override
	public long getCandidatesCount() throws RemoteException {

		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);

		DBCursor cursor = collection.find(new BasicDBObject(USER.TYPE, "INTERVIEWEE"));
		return Long.valueOf(cursor.count());

	}

	@Override
	public long getInterviewerCount() throws RemoteException {
		DBCollection collection =
				Services.getInstance().getBaseDataStore().db.getCollection(USER.DBCollection);

		DBCursor cursor = collection.find(new BasicDBObject(USER.TYPE, "INTERVIEWER"));
		return Long.valueOf(cursor.count());
	}


	@Override
	public boolean isUserAccountActive(String userEmailId) throws RemoteException {
		DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
		DBObject query = new BasicDBObject(USER.EMAIL, userEmailId);

		DBObject row = collection.findOne(query);
		if(row != null){
			if(String.valueOf(row.get(USER.ACTIVE)).equals("1"))
				return true;
		}
		return false;
	}


}
