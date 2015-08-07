package com.interview.transaction;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.math.*;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;


import com.interview.framework.DATASTORES;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.CompanyAccount;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.Transaction;
import com.interview.framework.pojo.UserTransaction;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service("escrowrevertT")
public class EscrowRevertService  {
	private Logger log = Logger.getLogger(EscrowRevertService.class);
  public int revertEscrow(Interview interview, double amount) {
    int stage = 0;
    String cAccountId = null;
    double eb = interview.getEb();
    Double prevBalance = null;
    Double newBalance = null;
    Transaction transaction = null;
    String userTransactionId = null;
    try {

      if (eb >= amount) {
        double newEB = eb - amount;
        Map<String, Object> userInfo =
            DataStoreRegistry.getInstance().getInterviewerDataStore()
                .getUserInfo(interview.getInterviewee());
        prevBalance = new Double(userInfo.get(USER.BALANCE).toString());
        log.info("AMOUNT"+amount);
        log.info("prevBalance"+prevBalance);
        log.info("ESCROW_REVERT_FEE"+VARIABLES.ESCROW_REVERT_FEE );
        stage = 1;

        DataStoreRegistry.getInstance().getInterviewDataStore()
            .withdrawFromEscrow(new ObjectId(interview.getId()), amount);

        stage = 2;

        double fee = (amount * VARIABLES.ESCROW_REVERT_FEE / 100);
        double net = amount - fee;

        newBalance =
            DataStoreRegistry.getInstance().getInterviewerDataStore()
                .updateBalance(interview.getInterviewee(), net, VARIABLES.ADD);
        log.info("new Balance"+newBalance );
        log.info("Difference"+(newBalance - prevBalance));
        log.info("NET"+net);
        double interviewerbalance=(double)Math.round((newBalance - prevBalance) * 100) / 100;
        if (interviewerbalance == net) {
        	log.info("Enterred in the stage 3");
          stage = 3;

          CompanyAccount cAccount = new CompanyAccount();
          cAccount.setAmount(fee);
          cAccount.setDebitOrCredit(DATASTORES.COMPANY_ACCOUNT.TTYPE.DEBIT);
          cAccount.setInitiator(interview.getInterviewer());
          cAccount.setInterviewId(interview.getId());
          cAccount.setPurpose(DATASTORES.COMPANY_ACCOUNT.PURPOSE_TYPE.ESCROW_REVERT);
          cAccount.setStatus(DATASTORES.COMPANY_ACCOUNT.TSTATUS.DONE);
          cAccount.setTime(new Date().getTime());

          cAccountId =
              DataStoreRegistry.getInstance().getCompanyAccountStore().insertTransaction(cAccount);

          stage = 4;
          log.info("FEES"+fee);
          log.info("NET"+net);
          transaction =
              Services
                  .getInstance()
                  .getTransactionHistoryService()
                  .logTransaction(new Date().getTime(), DATASTORES.TRANSACTION.TTYPE.DEBIT,
                      "ESCROW", interview.getInterviewee(), DATASTORES.TRANSACTION.TSTATUS.DONE,
                      "Reverted funds in dispute", amount, fee, net, newBalance);

          stage = 5;

          UserTransaction userTransaction = new UserTransaction();
          userTransaction.setArtifact(DATASTORES.USER_TRANSACTION.ARTIFACT_TYPE.INTERVIEW);
          userTransaction.setArtifactid(interview.getId());
          userTransaction
              .setPurpose(DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE_TYPE.ESCROW_REVERT);
          userTransaction.setTid(transaction.getId());
          userTransaction.setTime(new Date().getTime());
          userTransaction.setUsername(interview.getInterviewee());

          userTransactionId =
              DataStoreRegistry.getInstance().getUserTransactionStore().save(userTransaction);

          return RETURN_VALUES.REVERT_FUNDS_SUCCESS;
        } else
          stage = 22;
      }
    } catch (Exception e) {
      if (stage == 1) {
        revertStage1(interview, eb);
      } else if (stage == 2 || stage == 22) {
        revertStage2(interview, prevBalance, newBalance);
        revertStage1(interview, eb);
      } else if (stage == 3) {
        revertStage3(cAccountId);
        revertStage2(interview, prevBalance, newBalance);
        revertStage1(interview, eb);
      } else if (stage == 4) {
        revertStage4(transaction);
        revertStage3(cAccountId);
        revertStage2(interview, prevBalance, newBalance);
        revertStage1(interview, eb);
      } else if (stage == 5) {
        revertStage5(userTransactionId);
        revertStage4(transaction);
        revertStage3(cAccountId);
        revertStage2(interview, prevBalance, newBalance);
        revertStage1(interview, eb);
      }

    }
    return RETURN_VALUES.REVERT_FUNDS_FAILURE;
  }

  private void revertStage5(String userTransactionId) {
    if (userTransactionId != null) {
      try {
        DataStoreRegistry.getInstance().getUserTransactionStore()
            .deleteUserTransaction(userTransactionId);
      } catch (RemoteException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  private void revertStage4(Transaction transaction) {
    if (transaction != null) {
      try {
        DataStoreRegistry.getInstance().getTransactionStore()
            .deleteTransaction(transaction.getId());
      } catch (RemoteException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  private void revertStage3(String cAccountId) {
    if (cAccountId != null) {
      try {
        DataStoreRegistry.getInstance().getCompanyAccountStore().deleteTransaction(cAccountId);
      } catch (RemoteException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
  }

  private void revertStage2(Interview interview, Double prevBalance, Double newBalance) {
    if (prevBalance != null) {
      if (prevBalance != newBalance) {
        try {
          DataStoreRegistry.getInstance().getInterviewerDataStore()
              .setBalance(interview.getInterviewee(), prevBalance);
        } catch (RemoteException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }
  }

  private void revertStage1(Interview interview, double eb) {
    Map<String, Object> changes = new HashMap<String, Object>();
    changes.put(DATASTORES.INTERVIEW.ESCROW_BALANCE, eb);
    try {
      DataStoreRegistry.getInstance().getInterviewDataStore()
          .updateInterview(new ObjectId(interview.getId()), changes);
    } catch (RemoteException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }
}
