package com.interview.lifecycle;

import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;

public class InterviewDispute {

  public int initiateDispute(ObjectId iid, ObjectId bid_id) throws RemoteException {

    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.IN_PROGRESS) {

      DataStoreRegistry.getInstance().getInterviewDataStore().updateInterviewStatus(iid,
          INTERVIEW_STATUS.DISPUTE);

      return RETURN_VALUES.DISPUTE_START_SUCCESS;
    } else
      return RETURN_VALUES.DISPUTE_START_FAIL;

  }

}
