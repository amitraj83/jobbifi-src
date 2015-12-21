package com.interview.lifecycle;

import java.rmi.RemoteException;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class EscrowDeposit {

  public int escrowDeposit(String username, ObjectId iid, double amount) throws RemoteException {

    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.IN_PROGRESS
        || interview.getStatus() == INTERVIEW_STATUS.ESCROW_DEPOSITED) {

      String interviewee = interview.getInterviewee();
      if (!interviewee.equals(username))
        return RETURN_VALUES.ESCROW_DEPOSIT_FAIL;

      Map<String, Object> userInfo =
          DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(interviewee);
      double balance = new Double(userInfo.get(USER.BALANCE).toString());

      if (balance > amount) {

        // Here we should use trasaction services
        // balance = balance - amount;
        int status = Services.getInstance().getEscrowDepositService().deposit(interview, amount);
        if (status == 1)
          return RETURN_VALUES.ESCROW_DEPOSIT_SUCCESS;
        else
          return RETURN_VALUES.ESCROW_DEPOSIT_FAIL;
      } else
        return RETURN_VALUES.INSUFFICIENT_BALANCE;

    } else
      return RETURN_VALUES.ESCROW_DEPOSIT_FAIL;
  }

}
