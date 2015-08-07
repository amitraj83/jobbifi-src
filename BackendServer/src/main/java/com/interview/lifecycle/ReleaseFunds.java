package com.interview.lifecycle;

import java.rmi.RemoteException;
import java.util.Properties;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class ReleaseFunds {

  @Autowired
  private Properties myProps;

  public int releaseFunds(ObjectId iid, double amount) throws RemoteException {
    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.IN_PROGRESS
        || interview.getStatus() == INTERVIEW_STATUS.ESCROW_DEPOSITED) {
      return Services.getInstance().getEscrowReleaseService().escrowRelease(interview, amount);// makeTransaction(interview,
      // amount);
    } else
      return RETURN_VALUES.RELEASE_FUNDS_FAIL;
  }

  public int releaseFundsInDispute(ObjectId iid, double amount) throws RemoteException {
    Interview interview = DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
    if (interview.getStatus() == INTERVIEW_STATUS.DISPUTE) {
      return Services.getInstance().getEscrowReleaseService().escrowRelease(interview, amount);// makeTransaction(interview,
      // amount);
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

  /*
   * private int makeTransaction(Interview interview, double amount){ try{ double eb =
   * interview.getEb(); if(eb >= amount){
   * 
   * //Here we should use the transaction service. //This code is temporary
   * 
   * double newEB = eb-amount;
   * 
   * Map<String, Object> userInfo = DataStoreRegistry.getInstance()
   * .getInterviewerDataStore().getUserInfo(interview.getInterviewee()); double balance = new
   * Double( userInfo.get(USER.BALANCE).toString());
   * 
   * DataStoreRegistry.getInstance().getInterviewDataStore().withdrawFromEscrow (new ObjectId(
   * interview.getId()), amount);
   * 
   * double fee = (amount * VARIABLES.FEE_PERCENTAGE/100);
   * 
   * double net = amount - fee; double newBalance = DataStoreRegistry.getInstance()
   * .getInterviewerDataStore() .updateBalance(interview.getInterviewer(), net, VARIABLES.ADD);
   * 
   * 
   * Transaction transaction = Services.getInstance().getTransactionHistoryService()
   * .logTransaction(new Date().getTime(), DATASTORES.TRANSACTION.TTYPE.CREDIT, "ESCROW",
   * interview.getInterviewer(), DATASTORES.TRANSACTION.TSTATUS.DONE, "Interview Payment", amount,
   * 0,amount, balance);
   * 
   * UserTransaction userTransaction = new UserTransaction(); userTransaction.setArtifact
   * (DATASTORES.USER_TRANSACTION.ARTIFACT_TYPE.INTERVIEW);
   * userTransaction.setArtifactid(interview.getId()); userTransaction.setPurpose
   * (DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE_TYPE.ESCROW_RELEASE);
   * userTransaction.setTid(transaction.getId()); userTransaction.setTime(new Date().getTime());
   * userTransaction.setUsername(interview.getInterviewee()); DataStoreRegistry
   * .getInstance().getUserTransactionStore().save(userTransaction);
   * 
   * 
   * transaction = Services.getInstance().getTransactionHistoryService() .logTransaction(new
   * Date().getTime(), DATASTORES.TRANSACTION.TTYPE.DEBIT, interview.getInterviewee(),
   * interview.getInterviewer(), DATASTORES.TRANSACTION.TSTATUS.DONE, "Interview Payment", amount,
   * fee,net, newBalance); userTransaction = new UserTransaction(); userTransaction
   * .setArtifact(DATASTORES.USER_TRANSACTION.ARTIFACT_TYPE.INTERVIEW);
   * userTransaction.setArtifactid(interview.getId()); userTransaction.setPurpose
   * (DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE_TYPE.ACCOUNT_DEPOSIT);
   * userTransaction.setTid(transaction.getId()); userTransaction.setTime(new Date().getTime());
   * userTransaction.setUsername(interview.getInterviewer()); DataStoreRegistry
   * .getInstance().getUserTransactionStore().save(userTransaction);
   * 
   * return 1; } else return 0; } catch(Exception ex){ return -1; } }
   */
}
