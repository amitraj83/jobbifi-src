package com.interview.request.handlers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Message;
import com.interview.framework.pojo.Transactions;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class DepositFundsHandler extends RequestHandler {

	public DepositFundsHandler() {
		addHandler(this, REQUEST_TYPES.DEPOSIT_FUNDS);
	}
	
	private Logger log = Logger.getLogger(DepositFundsHandler.class);
	
	@Override
	public Map<String, Object> handleRequest(Map<Object, Object> data) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		String subReq = (String) data.get(REQUEST_TYPES.SUB_REQ);
		
		if (null != subReq
				&& REQUEST_TYPES.DEPOSIT_FUNDS_SUB_REQ.LOG_TRANSACTION
						.equals(subReq)) {
			try {
				Transactions transactions=new Transactions();
				transactions=(Transactions) (data.get("transaction"));
				String thirdPartyTID = new ObjectId().toString();
				
				String transactionId = Services.getInstance().getFundsDepositService()
						.logtransaction(transactions, thirdPartyTID);				
				res.put("transactionId", transactionId);
				
			} catch (Exception e) {
				log.error("Exception :", e);				
			}

		} else if(null != subReq
				&& REQUEST_TYPES.DEPOSIT_FUNDS_SUB_REQ.CANCEL_TRANSACTION
				.equals(subReq)){			
			Services.getInstance().getFundsDepositService().updateTransactionDetails(data.get("transactionid").toString(),
					"User cancelled the transaction.");
			
		} else if(null != subReq
				&& REQUEST_TYPES.DEPOSIT_FUNDS_SUB_REQ.WITHDRAW_TRANSACTION
				.equals(subReq)){
			try{
				 Transactions  transaction=new Transactions();
				 transaction = (Transactions)(data.get("transaction"));
				String thirdPartyTID = new ObjectId().toString();
				int result = Services.getInstance().getFundsDepositService().withdrawDeposit(transaction, thirdPartyTID);
				res.put("result", result);
				DataStoreRegistry.getInstance().getWithdrawfundStore().insertWithdrawFund(transaction);
			} catch(Exception e){
				log.error("Exception :",e);
			}
			
		} else {
			try {				
				String thirdPartyTID = new ObjectId().toString();				
				int status = Services.getInstance().getFundsDepositService()
						.funddeposit(data.get("transactionid").toString(), thirdPartyTID);
				res.put("status", status);
				
			} catch (Exception e) {
				log.error("Exception :", e);
			}
		}
		return res;
	}

}
