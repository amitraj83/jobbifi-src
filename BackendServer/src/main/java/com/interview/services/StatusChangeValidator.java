package com.interview.services;

import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.BID_STATUS;
import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;

public class StatusChangeValidator {

  public boolean isChangeValid(String type, ObjectId _id, int status) {

    if (type.equals(VARIABLES.STATUS_CHANGE_TYPE.BID)) {

    } else if (type.equals(VARIABLES.STATUS_CHANGE_TYPE.INTERVIEW)) {

    }

    return true;
  }

  private boolean isValidChangeForBid(ObjectId _id, int status) throws RemoteException {

    if (status == BID_STATUS.ACCEPT) {
      ObjectId iid = DataStoreRegistry.getInstance().getBidStore().getInterviewId(_id);
      Interview i =
          DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid.toString());
      if (i.getStatus() == INTERVIEW_STATUS.IN_PROGRESS) {
        return false;
      } else
        return true;
    } else
      return true;
  }

  private boolean isValidChangeForInterview(ObjectId _id, int status) {

    return true;
  }

}
