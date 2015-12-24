package com.interview.lifecycle;

import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;

public class CompleteInterview {

  public int completeInterview(ObjectId iid) throws RemoteException {

    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.PENDING
        || interview.getStatus() == INTERVIEW_STATUS.FULLY_PAID) {
      DataStoreRegistry.getInstance().getInterviewDataStore().updateInterviewStatus(iid,
          INTERVIEW_STATUS.COMPLETED);
      return RETURN_VALUES.COMPLETE_INTERVIEW_SUCCESS;
    }
    /*
     * else if(interview.getStatus() == INTERVIEW_STATUS.COMPLETE_APPROVAL_PENDING){
     * Services.getInstance().getInterviewDataStore ().updateInterviewStatus(iid,
     * INTERVIEW_STATUS.COMPLETED); return RETURN_VALUES.COMPLETE_INTERVIEW_SUCCESS; }
     */
    else
      return RETURN_VALUES.COMPLETE_INTERVIEW_FAIL;
  }

}
