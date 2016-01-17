package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.DisposableBean;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class AccountActiveHandler extends RequestHandler implements DisposableBean {
	private static final Logger logger = Logger.getLogger(AccountActiveHandler.class);

	public AccountActiveHandler() {
		addHandler(this, REQUEST_TYPES.CHECK_USERACCOUNT_ACTIVE);
	}

	@Override
	public Map<String, Object> handleRequest(Map<Object, Object> data) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try{
			String userid = String.valueOf(data.get(USER.EMAIL));
			
			if(DataStoreRegistry.getInstance().getInterviewerDataStore().isUserEmailExist(userid)){
				boolean active = DataStoreRegistry.getInstance().getInterviewerDataStore().isUserAccountActive(userid);
				if(active)
					resMap.put("active", 1);
				else
					resMap.put("active", 2);
			}
			else{
				resMap.put("active", 3);	
			}
			
			

			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resMap;
	}

	public void destroy() {
		logger.debug("Destroying Bidhander Bean.......");
	}
}

