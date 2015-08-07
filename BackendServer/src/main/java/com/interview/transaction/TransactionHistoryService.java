package com.interview.transaction;

import java.rmi.RemoteException;
import org.bson.types.ObjectId;
import com.interview.framework.pojo.Transaction;
import com.interview.rmi.DataStoreRegistry;

public class TransactionHistoryService {

	public Transaction logTransaction(long time, String type, String owner,
			String otherParty, String status, String details,
			double amountSent, double fee, double netAmount, double balance)
			throws RemoteException {

		Transaction trans = new Transaction();
		trans.setDetails(details);
		trans.setFee(fee);
		trans.setGross(amountSent);
		trans.setNetamount(netAmount);
		trans.setOwner(owner);
		trans.setOtherParty(otherParty);
		trans.setStatus(status);
		trans.setTime(time);
		trans.setType(type);
		trans.setBalance(balance);

		ObjectId tid = DataStoreRegistry.getInstance().getTransactionStore()
				.saveTrasaction(trans);
		trans.setId(tid.toString());
		return trans;
	}

	public Transaction updateTransaction(String transactionid,long time, String type, String owner,
			String otherParty, String status, String details,
			double amountSent, double fee, double netAmount, double balance)
			throws RemoteException {
		

		Transaction trans = new Transaction();
		trans.setDetails(details);
		trans.setFee(fee);
		trans.setGross(amountSent);
		trans.setNetamount(netAmount);
		trans.setOwner(owner);
		trans.setOtherParty(otherParty);
		trans.setStatus(status);
		trans.setTime(time);
		trans.setType(type);
		trans.setBalance(balance);

		int result = DataStoreRegistry.getInstance().getTransactionStore()
					.updateTransaction(transactionid,balance, details);
		trans.setId(transactionid);
		return trans;
	}
}