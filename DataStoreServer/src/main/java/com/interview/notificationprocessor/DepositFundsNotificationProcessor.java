package com.interview.notificationprocessor;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Transaction;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class DepositFundsNotificationProcessor extends NotificationProcessor {

  public DepositFundsNotificationProcessor() {
    register(VARIABLES.NOTIFICATION.TYPE.DEPOSIT_FUNDS_NOTIFICATION, this);
  }


  @Override
  public Map<Object, Object> createContentMap(DBObject row) {

    Map<Object, Object> contentMap = new HashMap<Object, Object>();
    try {
      BasicDBObject basicDBObject =
          (BasicDBObject) row.get(VARIABLES.NOTIFICATION.DATASTORE.CONTENT);
      ObjectId tid = new ObjectId(basicDBObject.get(DATASTORES.TRANSACTION.TID).toString());
      Transaction transaction = Services.getInstance().getTransactionStore().getTransaction(tid);
      // contentMap.put(DATASTORES.TRANSACTION.OWNER, transaction.getOwner());
      contentMap.put(DATASTORES.TRANSACTION.NETAMOUNT, transaction.getNetamount());
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return contentMap;
  }
  /*
   * @Override public void process(Object document) { try{ Transaction transaction =
   * (Transaction)document;
   * 
   * 
   * Notification notification = new Notification(); Map<Object, Object> cMap = new HashMap<Object,
   * Object>(); cMap.put(DATASTORES.TRANSACTION.TID, transaction.getId());
   * notification.setContent(cMap); notification.setCreatedBy(transaction.getOwner());
   * notification.setEntryDate(transaction.getTime());
   * notification.setRecepientUser(transaction.getOwner());
   * notification.setType(VARIABLES.NOTIFICATION.TYPE.DEPOSIT_FUNDS_NOTIFICATION);
   * Services.getInstance().getNotificationStore().save(notification); } catch (Exception e) { //
   * TODO Auto-generated catch block e.printStackTrace(); } }
   */
}
