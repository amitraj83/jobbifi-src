package com.interview.transaction;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.RETURN_VALUES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.CompanyAccount;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.Ticket;
import com.interview.framework.pojo.Transaction;
import com.interview.framework.pojo.UserTransaction;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service("escrowreleaseT")
public class EscrowReleaseService {

  public int escrowRelease(Interview interview, double amount) {
    int stage = 0;
    double eb = interview.getEb();
    Double interviewerPrevBalance = null;
    String cAccountId = null;
    Transaction transactionStage4 = null;
    Transaction transactionStage6 = null;
    UserTransaction userTransactionStage5 = null;
    UserTransaction userTransactionStage7 = null;
    int interviewPrevStatus = interview.getStatus();
    try {
      if (eb >= amount) {

        // Here we should use the transaction service.
        // This code is temporary

        Map<String, Object> userInfo = DataStoreRegistry.getInstance().getInterviewerDataStore()
            .getUserInfo(interview.getInterviewee());
        double balance = new Double(userInfo.get(USER.BALANCE).toString());

        interviewerPrevBalance =
            new Double(DataStoreRegistry.getInstance().getInterviewerDataStore()
                .getUserInfo(interview.getInterviewer()).get(USER.BALANCE).toString());


        stage = 1;

        DataStoreRegistry.getInstance().getInterviewDataStore()
            .withdrawFromEscrow(new ObjectId(interview.getId()), amount);

        stage = 2;

        double fee = (amount * VARIABLES.FEE_PERCENTAGE / 100);

        double net = amount - fee;

        double newBalance = DataStoreRegistry.getInstance().getInterviewerDataStore()
            .updateBalance(interview.getInterviewer(), net, VARIABLES.ADD);
        DecimalFormat df = new DecimalFormat("000.00");
        newBalance = new Double(df.format(newBalance));
        interviewerPrevBalance = new Double(df.format(interviewerPrevBalance));
        net = new Double(df.format(net));
        double diff = newBalance - interviewerPrevBalance;
        diff = new Double(df.format(diff));

        if (diff == net) {

          stage = 3;

          CompanyAccount cAccount = new CompanyAccount();
          cAccount.setAmount(fee);
          cAccount.setDebitOrCredit(DATASTORES.COMPANY_ACCOUNT.TTYPE.DEBIT);
          cAccount.setInitiator(interview.getInterviewee());
          cAccount.setInterviewId(interview.getId());
          cAccount.setPurpose(DATASTORES.COMPANY_ACCOUNT.PURPOSE_TYPE.ESCROW_RELEASE);
          cAccount.setStatus(DATASTORES.COMPANY_ACCOUNT.TSTATUS.DONE);
          cAccount.setTime(new Date().getTime());

          cAccountId =
              DataStoreRegistry.getInstance().getCompanyAccountStore().insertTransaction(cAccount);


          stage = 4;

          transactionStage4 = Services.getInstance().getTransactionHistoryService().logTransaction(
              new Date().getTime(), DATASTORES.TRANSACTION.TTYPE.CREDIT, "ESCROW",
              interview.getInterviewer(), DATASTORES.TRANSACTION.TSTATUS.DONE, "Interview Payment",
              amount, 0, amount, balance);


          stage = 5;

          userTransactionStage5 = new UserTransaction();
          userTransactionStage5.setArtifact(DATASTORES.USER_TRANSACTION.ARTIFACT_TYPE.INTERVIEW);
          userTransactionStage5.setArtifactid(interview.getId());
          userTransactionStage5
              .setPurpose(DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE_TYPE.ESCROW_RELEASE);
          userTransactionStage5.setTid(transactionStage4.getId());
          userTransactionStage5.setTime(new Date().getTime());
          userTransactionStage5.setUsername(interview.getInterviewee());
          String ut_id =
              DataStoreRegistry.getInstance().getUserTransactionStore().save(userTransactionStage5);
          userTransactionStage5.set_id(ut_id);

          stage = 6;

          transactionStage6 = Services.getInstance().getTransactionHistoryService().logTransaction(
              new Date().getTime(), DATASTORES.TRANSACTION.TTYPE.DEBIT, interview.getInterviewee(),
              interview.getInterviewer(), DATASTORES.TRANSACTION.TSTATUS.DONE, "Interview Payment",
              amount, fee, net, newBalance);

          stage = 7;

          userTransactionStage7 = new UserTransaction();
          userTransactionStage7.setArtifact(DATASTORES.USER_TRANSACTION.ARTIFACT_TYPE.INTERVIEW);
          userTransactionStage7.setArtifactid(interview.getId());
          userTransactionStage7
              .setPurpose(DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE_TYPE.ACCOUNT_DEPOSIT);
          userTransactionStage7.setTid(transactionStage6.getId());
          userTransactionStage7.setTime(new Date().getTime());
          userTransactionStage7.setUsername(interview.getInterviewer());
          DataStoreRegistry.getInstance().getUserTransactionStore().save(userTransactionStage7);



          stage = 8;

          /*
           * if(interviewPrevStatus == INTERVIEW_STATUS.DISPUTE)
           * DataStoreRegistry.getInstance().getInterviewDataStore() .updateInterviewStatus(new
           * ObjectId( interview.getId()), INTERVIEW_STATUS.FULLY_PAID); else
           * DataStoreRegistry.getInstance().getInterviewDataStore() .updateInterviewStatus(new
           * ObjectId( interview.getId()), INTERVIEW_STATUS.FULLY_PAID);
           */



          return RETURN_VALUES.RELEASE_FUNDS_SUCCESS;
        } else
          stage = 22;
      } else
        return RETURN_VALUES.INSUFFICIENT_BALANCE;

    } catch (Exception ex) {
      if (stage == 0)
        return RETURN_VALUES.RELEASE_FUNDS_FAIL;
      else if (stage == 1) {
        revertStage1(interview, eb);
      } else if (stage == 2 || stage == 22) {
        revertStage2(interview, interviewerPrevBalance);
        revertStage1(interview, eb);
      } else if (stage == 3) {
        revertStage3(cAccountId);
        revertStage2(interview, interviewerPrevBalance);
        revertStage1(interview, eb);
      } else if (stage == 4) {
        revertStage4(transactionStage4);
        revertStage3(cAccountId);
        revertStage2(interview, interviewerPrevBalance);
        revertStage1(interview, eb);
      } else if (stage == 5) {
        revertStage5(userTransactionStage5);
        revertStage4(transactionStage4);
        revertStage3(cAccountId);
        revertStage2(interview, interviewerPrevBalance);
        revertStage1(interview, eb);
      } else if (stage == 6) {
        revertStage4(transactionStage6);
        revertStage5(userTransactionStage5);
        revertStage4(transactionStage4);
        revertStage3(cAccountId);
        revertStage2(interview, interviewerPrevBalance);
        revertStage1(interview, eb);
      } else if (stage == 7) {
        revertStage5(userTransactionStage7);
        revertStage4(transactionStage6);
        revertStage5(userTransactionStage5);
        revertStage4(transactionStage4);
        revertStage3(cAccountId);
        revertStage2(interview, interviewerPrevBalance);
        revertStage1(interview, eb);
      } else if (stage == 8) {
        revertState8(interview, interviewPrevStatus);
        revertStage5(userTransactionStage7);
        revertStage4(transactionStage6);
        revertStage5(userTransactionStage5);
        revertStage4(transactionStage4);
        revertStage3(cAccountId);
        revertStage2(interview, interviewerPrevBalance);
        revertStage1(interview, eb);
      }

    }

    return RETURN_VALUES.RELEASE_FUNDS_FAIL;
  }

  private void revertState8(Interview interview, int interviewPrevStatus) {
    try {
      DataStoreRegistry.getInstance().getInterviewDataStore()
          .updateInterviewStatus(new ObjectId(interview.getId()), interviewPrevStatus);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void revertStage5(UserTransaction userTransactionStage5) {
    try {
      if (userTransactionStage5 != null && userTransactionStage5.get_id() != null) {
        boolean deleted = DataStoreRegistry.getInstance().getUserTransactionStore()
            .deleteUserTransaction(userTransactionStage5.get_id());
        if (!deleted)
          raiseUserTransactionDeleteTicket(userTransactionStage5.get_id());
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void raiseUserTransactionDeleteTicket(String getId) {
    Ticket ticket = new Ticket();
    ticket.setCreatedBy("SYSTEM");

    String description = "Please delete an entry in usertransaction collection\r\n"
        + "Following are the details\r\n" + "_id : " + getId + "\r\n" + "";
    ticket.setDescription(description);
    ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.TRANSACTION_DELETE);
    ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
    ticket.setTime(new Date().getTime());
    try {
      DataStoreRegistry.getInstance().getTicketStore().save(ticket);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void revertStage4(Transaction transactionStage4) {
    try {
      if (transactionStage4 != null) {
        boolean deleted = DataStoreRegistry.getInstance().getTransactionStore()
            .deleteTransaction(transactionStage4.getId());
        if (!deleted)
          raiseTransactionDeleteTicket(transactionStage4);
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void raiseTransactionDeleteTicket(Transaction transactionStage4) {
    Ticket ticket = new Ticket();
    ticket.setCreatedBy("SYSTEM");

    String description = "Please delete an entry in transaction collection\r\n"
        + "Following are the details\r\n" + "_id : " + transactionStage4.getId() + "\r\n" + "";
    ticket.setDescription(description);
    ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.TRANSACTION_DELETE);
    ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
    ticket.setTime(new Date().getTime());
    try {
      DataStoreRegistry.getInstance().getTicketStore().save(ticket);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void revertStage3(String cAccountId) {
    if (cAccountId != null) {
      try {
        boolean deleted =
            DataStoreRegistry.getInstance().getCompanyAccountStore().deleteTransaction(cAccountId);
        if (!deleted) {
          raiseTicketToDelteCompanyTransaction(cAccountId);
        }
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }

  private void raiseTicketToDelteCompanyTransaction(String cAccountId) {
    Ticket ticket = new Ticket();
    ticket.setCreatedBy("SYSTEM");

    String description = "Please delete an entry in Companyaccount collection\r\n"
        + "Following are the details\r\n" + "_id : " + cAccountId + "\r\n" + "";
    ticket.setDescription(description);
    ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.COMPANY_ACCOUNT_ENTRY);
    ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
    ticket.setTime(new Date().getTime());
    try {
      DataStoreRegistry.getInstance().getTicketStore().save(ticket);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void revertStage2(Interview interview, Double interviewerPrevBalance) {
    if (interviewerPrevBalance != null) {
      try {
        DataStoreRegistry.getInstance().getInterviewerDataStore()
            .setBalance(interview.getInterviewer(), interviewerPrevBalance);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }

  private void revertStage1(Interview interview, double eb) {
    Map<String, Object> changes = new HashMap<String, Object>();
    changes.put(DATASTORES.INTERVIEW.ESCROW_BALANCE, eb);
    try {
      DataStoreRegistry.getInstance().getInterviewDataStore()
          .updateInterview(new ObjectId(interview.getId()), changes);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

}
