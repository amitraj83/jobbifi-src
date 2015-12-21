package com.interview.lifecycle;

import java.rmi.RemoteException;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class ReleaseFunds {
  public int releaseFunds(ObjectId iid, double amount) throws RemoteException {
    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.IN_PROGRESS
        || interview.getStatus() == INTERVIEW_STATUS.ESCROW_DEPOSITED) {
      return Services.getInstance().getEscrowReleaseService().escrowRelease(interview, amount);
    } else
      return RETURN_VALUES.RELEASE_FUNDS_FAIL;
  }

  public int releaseFundsInDispute(ObjectId iid, double amount) throws RemoteException {
    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.DISPUTE) {
      return Services.getInstance().getEscrowReleaseService().escrowRelease(interview, amount);
    } else
      return RETURN_VALUES.RELEASE_FUNDS_FAIL;
  }

  public int revertFundsInDispute(ObjectId iid, double amount) throws RemoteException {
    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.DISPUTE) {
      return Services.getInstance().getEscrowRevertService().revertEscrow(interview, amount);
    }
    return -1;
  }
}
