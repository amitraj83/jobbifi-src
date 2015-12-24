package com.interview.lifecycle;

import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.pojo.Interview;
import com.interview.framework.rmi.common.IInterviewDataStore;
import com.interview.rmi.DataStoreRegistry;

public class DeleteInterview {

  public int deleteInterview(ObjectId iid) throws RemoteException {
    IInterviewDataStore is = DataStoreRegistry.getInstance().getInterviewDataStore();
    Interview interview = is.getInterview(iid);
    if (interview.getStatus() != INTERVIEW_STATUS.ESCROW_DEPOSITED
        && interview.getStatus() != INTERVIEW_STATUS.DISPUTE) {

      boolean isDeleted = is.deleteInterivew(iid);
      if (isDeleted) {
        boolean isBidsDeleted =
            DataStoreRegistry.getInstance().getBidStore().deleteAssociatedBidsOfInterview(iid);
        if (isBidsDeleted)
          return RETURN_VALUES.INTERVIEW_DELETE_SUCCESS;
        else
          return RETURN_VALUES.INTERVIEW_DELETE_FAIL;
      } else
        return RETURN_VALUES.INTERVIEW_DELETE_FAIL;

    } else
      return RETURN_VALUES.INTERVIEW_DELETE_FAIL_INVALID_STATE;

  }

}
