package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Transaction;
import com.interview.framework.rmi.common.ITransactionStore;
import com.interview.services.Services;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class TransactionStore extends UnicastRemoteObject implements ITransactionStore {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger(TransactionStore.class);
	DBCollection COLLECTION = Services.getInstance().getBaseDataStore().db
			.getCollection(DATASTORES.TRANSACTION.DBCollection);

	protected TransactionStore() throws RemoteException {
		Services.getInstance().getRMIServer().bind(NAME, this);
	}

	public ObjectId saveTrasaction(Transaction trans) throws RemoteException {
		DBObject dbObject = new BasicDBObject();
		ObjectId _id = new ObjectId();
		dbObject.put("_id", _id);
		dbObject.put(DATASTORES.TRANSACTION.TIME, trans.getTime());
		dbObject.put(DATASTORES.TRANSACTION.TYPE, trans.getType());
		dbObject.put(DATASTORES.TRANSACTION.OWNER, trans.getOwner());
		dbObject.put(DATASTORES.TRANSACTION.OTHERPARTY, trans.getOtherParty());
		dbObject.put(DATASTORES.TRANSACTION.STATUS, trans.getStatus());
		dbObject.put(DATASTORES.TRANSACTION.DETAILS, trans.getDetails());
		dbObject.put(DATASTORES.TRANSACTION.GROSS, trans.getGross());
		dbObject.put(DATASTORES.TRANSACTION.FEE, trans.getFee());
		dbObject.put(DATASTORES.TRANSACTION.NETAMOUNT, trans.getNetamount());
		dbObject.put(DATASTORES.TRANSACTION.BALANCE, trans.getBalance());

		WriteResult wr = COLLECTION.save(dbObject);
		CommandResult cr = wr.getCachedLastError();
		if (cr.ok())
			return _id;
		else
			return null;
	}

	public Transaction getTransaction(ObjectId tid) throws RemoteException {
		DBObject query = new BasicDBObject();
		query.put("_id", tid);
		DBObject row = COLLECTION.findOne(query);
		Transaction transaction = new Transaction();
		transaction.setBalance(new Double(row.get(
				DATASTORES.TRANSACTION.BALANCE).toString()));
		transaction.setDetails(row.get(DATASTORES.TRANSACTION.DETAILS)
				.toString());
		transaction.setFee(new Double(row.get(DATASTORES.TRANSACTION.FEE)
				.toString()));
		transaction.setGross(new Double(row.get(DATASTORES.TRANSACTION.GROSS)
				.toString()));
		transaction.setId(row.get("_id").toString());
		transaction.setNetamount(new Double(row.get(
				DATASTORES.TRANSACTION.NETAMOUNT).toString()));
		transaction.setOtherParty(row.get(DATASTORES.TRANSACTION.OTHERPARTY)
				.toString());
		transaction.setOwner(row.get(DATASTORES.TRANSACTION.OWNER).toString());
		transaction
				.setStatus(row.get(DATASTORES.TRANSACTION.STATUS).toString());
		transaction.setTime(new Long(row.get(DATASTORES.TRANSACTION.TIME)
				.toString()));
		transaction.setType(row.get(DATASTORES.TRANSACTION.TYPE).toString());
		return transaction;

	}

	public List<Transaction> getTransactionsList(String user, long fromDate,
			long toDate, int pageNum) throws RemoteException {
		List<Transaction> listOfTrans = new ArrayList<Transaction>();

		BasicDBList list = new BasicDBList();
		List<String> tids = Services.getInstance()
				.getUserTransactionDataStore().getTransactionIDs(user);
		if (tids.size() > 0) {
			BasicDBList orList = new BasicDBList();

			BasicDBObject query = new BasicDBObject("$or", orList);

			for (String tid : tids) {
				DBObject query2 = new BasicDBObject("_id", new ObjectId(tid));
				orList.add(query2);
			}
			// DBObject query = new BasicDBObject();//.append("_id",new
			// BasicDBObject("$in", tids));
			// query.put("$in", list);
			// DBObject query = new
			// BasicDBObject().append(DATASTORES.TRANSACTION.OWNER,user);
			query.put(
					DATASTORES.TRANSACTION.TIME,
					BasicDBObjectBuilder.start("$gte", fromDate)
							.add("$lte", toDate).get());

			int documentsToSkip = pageNum > 0 ? (pageNum - 1)
					* (VARIABLES.TRNSACTION_PAGE_SIZE) : 0;

			DBCursor cursor = COLLECTION.find(query).skip(documentsToSkip)
					.limit(VARIABLES.TRNSACTION_PAGE_SIZE)
					.sort(new BasicDBObject(DATASTORES.TRANSACTION.TIME, -1));
			while (cursor.hasNext()) {
				DBObject row = cursor.next();
				Transaction trans = Services.getInstance()
						.getJSONUtilityService()
						.convertValue(row, Transaction.class);
				listOfTrans.add(trans);
			}
		}
		return listOfTrans;
	}

	@Override
	public int getTotalRecord(String user, long fromDate,
			long toDate ) throws RemoteException{
		List<Transaction> listOfTrans = new ArrayList<Transaction>();

		BasicDBList list = new BasicDBList();
		List<String> tids = Services.getInstance()
				.getUserTransactionDataStore().getTransactionIDs(user);
		if (tids.size() > 0) {
			BasicDBList orList = new BasicDBList();

			BasicDBObject query = new BasicDBObject("$or", orList);

			for (String tid : tids) {
				DBObject query2 = new BasicDBObject("_id", new ObjectId(tid));
				orList.add(query2);
			}
			// DBObject query = new BasicDBObject();//.append("_id",new
			// BasicDBObject("$in", tids));
			// query.put("$in", list);
			// DBObject query = new
			// BasicDBObject().append(DATASTORES.TRANSACTION.OWNER,user);
			query.put(
					DATASTORES.TRANSACTION.TIME,
					BasicDBObjectBuilder.start("$gte", fromDate)
							.add("$lte", toDate).get());

			
			int cursor = COLLECTION.find(query).count();
			return cursor;
		}
		else
		return 0;
		
	}
	
	@Override
	public boolean deleteTransaction(String tid) throws RemoteException {
		ObjectId _id = new ObjectId(tid);

		DBObject query = new BasicDBObject();
		query.put("_id", _id);

		DBObject row = COLLECTION.findOne(query);

		// BasicDBObject updateDoc = new BasicDBObject("$set", new
		// BasicDBObject(DATASTORES.TRANSACTION.STATUS,
		// DATASTORES.TRANSACTION.TSTATUS.CANCELLED));
		// CommandResult cr = COLLECTION.update(query,
		// updateDoc).getCachedLastError();
		CommandResult cr = COLLECTION.remove(row).getCachedLastError();
		return cr.ok();
	}

	public int updateTransaction(String transactionid, double balance, String details)
			throws RemoteException {

		DBCollection collection = Services.getInstance().getBaseDataStore().db
				.getCollection(DATASTORES.TRANSACTION.DBCollection);
		DBObject query = new BasicDBObject();
		query.put(DATASTORES.TRANSACTION.ID, new ObjectId(transactionid));

		DBObject row = collection.findOne(query);
		Double amount = new Double(row.get(DATASTORES.TRANSACTION.NETAMOUNT).toString());
		balance = amount + Double.parseDouble(row.get(DATASTORES.TRANSACTION.BALANCE).toString());


		DBObject newDoc = new BasicDBObject()
			.append(DATASTORES.TRANSACTION.BALANCE, balance)
			.append(DATASTORES.TRANSACTION.STATUS, DATASTORES.TRANSACTION.TSTATUS.DONE)
			.append(DATASTORES.TRANSACTION.DETAILS, details);
				
		DBObject updateDoc = new BasicDBObject("$set", newDoc);
		collection.updateMulti(query, updateDoc);
		return 0;
	}
	
	public boolean updateTransactionDetails(String transactionid, String details) throws RemoteException {
		DBCollection collection = Services.getInstance().getBaseDataStore().db
				.getCollection(DATASTORES.TRANSACTION.DBCollection);
		DBObject query = new BasicDBObject();
		query.put(DATASTORES.TRANSACTION.ID, new ObjectId(transactionid));

		DBObject newDoc = new BasicDBObject()
			.append(DATASTORES.TRANSACTION.STATUS, DATASTORES.TRANSACTION.TSTATUS.CANCELLED)
			.append(DATASTORES.TRANSACTION.DETAILS, details);
				
		DBObject updateDoc = new BasicDBObject("$set", newDoc);
		collection.updateMulti(query, updateDoc);
		return true;
	}
	
}
