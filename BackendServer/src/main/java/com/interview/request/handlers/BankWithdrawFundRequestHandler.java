package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.BankWithdrawFundRequest;
import com.interview.rmi.DataStoreRegistry;

@Service
public class BankWithdrawFundRequestHandler extends RequestHandler {

	private static final Logger logger = Logger.getLogger(BankWithdrawFundRequestHandler.class);
	
	public BankWithdrawFundRequestHandler(){
		addHandler(this, REQUEST_TYPES.BANK_WITHDRAW_FUND_REQUEST);
	}
	
	@Override
	public Map<String, Object> handleRequest(Map<Object, Object> data) {
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		String subReq = (String) data.get(REQUEST_TYPES.SUB_REQ);		
		
		if(null != subReq && REQUEST_TYPES.BANK_WITHDRAW_FUND_REQUEST_SUB_REQ.SAVE_REQUEST.equals(subReq)){		
			BankWithdrawFundRequest fundRequest = (BankWithdrawFundRequest) data.get("fundRequest");
			try {
				fundRequest.setStatus(DATASTORES.BANK_WITHDRAW_FUND_REQUEST.status.PENDING);
				fundRequest.setRequestDate(new Date().getTime());
				ObjectId id = DataStoreRegistry.getInstance().getBankWithdrawFundRequestStore().saveBankWithdrawFundRequest(fundRequest);
				if(null != id){
					resMap.put("status", 1);
				} else {
					resMap.put("status", -1);
				}
			} catch (RemoteException e) {
				logger.error("Exception : " + e.getMessage(), e);
				resMap.put("status", -1);
			}
		}
		return resMap;
	}
}