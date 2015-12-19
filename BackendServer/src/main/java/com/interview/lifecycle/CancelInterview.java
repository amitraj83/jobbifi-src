package com.interview.lifecycle;

import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;

public class CancelInterview {

  public int cancelInterview(ObjectId _id) throws RemoteException {

    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(_id);
    if (interview.getStatus() == INTERVIEW_STATUS.PENDING) {
      DataStoreRegistry.getInstance().getInterviewDataStore().updateInterviewStatus(_id,
          INTERVIEW_STATUS.CANCELLED);
      return RETURN_VALUES.CANCEL_INTERVIEW_SUCCESS;
    } else
      return RETURN_VALUES.CANCEL_INTERVIEW_FAIL_INVALID_STATE;
  }

}
