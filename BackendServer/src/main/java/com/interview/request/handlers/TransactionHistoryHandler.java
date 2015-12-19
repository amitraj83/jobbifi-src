package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Transaction;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class TransactionHistoryHandler extends RequestHandler {

  public TransactionHistoryHandler() {
    addHandler(this, REQUEST_TYPES.TRANSACTION_HISTORY);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      String user = data.get(USER.USERNAME).toString();
      long fromDate = new Long(data.get(VARIABLES.FROM_DATE).toString());
      long toDate = new Long(data.get(VARIABLES.TO_DATE).toString());
      int pageNumber = new Integer(data.get("pagenum").toString());
      List<Transaction> list = DataStoreRegistry.getInstance().getTransactionStore()
          .getTransactionsList(user, fromDate, toDate, pageNumber);
      resMap.put("list", Services.getInstance().getJSONUtilityService().convertObjectToJSON(list));
      resMap.put("numberofrecords", DataStoreRegistry.getInstance().getTransactionStore()
          .getTotalRecord(user, fromDate, toDate));
      resMap.put("totalsize",
          DataStoreRegistry.getInstance().getUserTransactionStore().getTransactionIDs(user).size());

    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return resMap;
  }

}
