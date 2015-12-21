package com.interview.transaction;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.CompanyAccount;
import com.interview.framework.pojo.Escrow;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.Ticket;
import com.interview.framework.pojo.Transaction;
import com.interview.framework.pojo.UserTransaction;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service("escrowdepositT")
public class EscrowDepositService {
  public int deposit(Interview interview, double amount) {
    int stage = 0;
    if (interview != null && amount > 0) {
      Double prevBalance = null;
      Double prevEscrowBalance = null;
      CompanyAccount cAccount = null;
      String cAccountId = null;
      Transaction transaction = null;
      UserTransaction userTransaction = null;
      int interviewPrevStatus = -1;
      String userTransactionId = null;
      try {
        Map<String, Object> userInfo = DataStoreRegistry.getInstance().getInterviewerDataStore()
            .getUserInfo(interview.getInterviewee());
        prevBalance = new Double(userInfo.get(USER.BALANCE).toString());
        prevEscrowBalance = interview.getEb();
        if (prevBalance != null) {
          stage = 1;
          double newBalance = DataStoreRegistry.getInstance().getInterviewerDataStore()
              .updateBalance(interview.getInterviewee(), amount, VARIABLES.SUB);
          if (prevBalance - newBalance == amount) {
            stage = 2;
            DataStoreRegistry.getInstance().getInterviewDataStore()
                .depositEscrowBalance(new ObjectId(interview.getId()), amount);
            double newEB = DataStoreRegistry.getInstance().getInterviewDataStore()
                .getInterview(interview.getId()).getEb();
            if (newEB - prevEscrowBalance == amount) {
              Escrow escrow = new Escrow();
              escrow.setAmount(amount);
              escrow.setDate(new Date().getTime());
              escrow.setIid(interview.getId());
              escrow.setStatus(0);
              SecureRandom random = new SecureRandom();
              escrow.setVisibleId(new BigInteger(40, random).toString(32).toUpperCase());
              DataStoreRegistry.getInstance().getEscrowDataStore().createEscrowEntry(escrow);
              stage = 3;
              stage = 4;
              transaction = Services.getInstance().getTransactionHistoryService().logTransaction(
                  new Date().getTime(), DATASTORES.TRANSACTION.TTYPE.CREDIT,
                  interview.getInterviewee(), "ESCROW", DATASTORES.TRANSACTION.TSTATUS.DONE,
                  "Escrow deposit", amount, 0, amount, newBalance);
              stage = 5;
              userTransaction = new UserTransaction();
              userTransaction.setArtifact(DATASTORES.USER_TRANSACTION.ARTIFACT_TYPE.INTERVIEW);
              userTransaction.setArtifactid(interview.getId());
              userTransaction
                  .setPurpose(DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE_TYPE.ESCROW_DEPOSIT);
              userTransaction.setTid(transaction.getId());
              userTransaction.setTime(new Date().getTime());
              userTransaction.setUsername(interview.getInterviewee());
              userTransactionId =
                  DataStoreRegistry.getInstance().getUserTransactionStore().save(userTransaction);
              stage = 6;
              interviewPrevStatus = interview.getStatus();
              DataStoreRegistry.getInstance().getInterviewDataStore().updateInterviewStatus(
                  new ObjectId(interview.getId()), INTERVIEW_STATUS.ESCROW_DEPOSITED);
              return 1;
            } else
              stage = 12;
          } else
            stage = 11;
        }
      } catch (Exception e) {
        if (stage == 11 || stage == 1) {
          revertStage1(interview, prevBalance);
        } else if (stage == 2 || stage == 12) {
          revertStage2(interview, prevEscrowBalance);
          revertStage1(interview, prevBalance);
        } else if (stage == 3) {
          revertStage3(cAccountId, cAccount);
          revertStage2(interview, prevEscrowBalance);
          revertStage1(interview, prevBalance);
        } else if (stage == 4) {
          revertStage4(transaction);
          revertStage3(cAccountId, cAccount);
          revertStage2(interview, prevEscrowBalance);
          revertStage1(interview, prevBalance);
        } else if (stage == 5) {
          revertStage5(userTransactionId);
          revertStage4(transaction);
          revertStage3(cAccountId, cAccount);
          revertStage2(interview, prevEscrowBalance);
          revertStage1(interview, prevBalance);
        } else if (stage == 6) {
          try {
            int istatus = DataStoreRegistry.getInstance().getInterviewDataStore()
                .getInterview(interview.getId()).getStatus();
            if (istatus != INTERVIEW_STATUS.ESCROW_DEPOSITED) {
              revertStage6(interview, interviewPrevStatus);
              revertStage5(userTransactionId);
              revertStage4(transaction);
              revertStage3(cAccountId, cAccount);
              revertStage2(interview, prevEscrowBalance);
              revertStage1(interview, prevBalance);
            }
          } catch (RemoteException e1) {
            e1.printStackTrace();
          }
        }
        e.printStackTrace();
      }
    }
    return 0;
  }

  private void revertStage6(Interview interview, int interviewPrevStatus) {
    try {
      DataStoreRegistry.getInstance().getInterviewDataStore()
          .updateInterviewStatus(new ObjectId(interview.getId()), interviewPrevStatus);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void revertStage5(String userTransactionId) {
    if (userTransactionId != null) {
      try {
        DataStoreRegistry.getInstance().getUserTransactionStore()
            .deleteUserTransaction(userTransactionId);
      } catch (RemoteException e) {
        raiseUserTransactionTicket(userTransactionId);
        e.printStackTrace();
      }
    }
  }

  private void raiseUserTransactionTicket(String userTransactionId) {
    Ticket ticket = new Ticket();
    ticket.setCreatedBy("SYSTEM");
    String description =
        "Please delete an entry in UserTransaction collection\r\n" + "Following are the details\r\n"
            + "user transaction _id : " + userTransactionId + "\r\n" + "";
    ticket.setDescription(description);
    ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.TRANSACTION_STATUS);
    ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
    ticket.setTime(new Date().getTime());
    try {
      DataStoreRegistry.getInstance().getTicketStore().save(ticket);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void revertStage4(Transaction transaction) {
    if (transaction != null && transaction.getId() != null) {
      try {
        DataStoreRegistry.getInstance().getTransactionStore()
            .deleteTransaction(transaction.getId());
      } catch (RemoteException e) {
        raiseCancelTransactionTicket(transaction.getId());
        e.printStackTrace();
      }
    }
  }

  private void raiseCancelTransactionTicket(String id) {
    Ticket ticket = new Ticket();
    ticket.setCreatedBy("SYSTEM");
    String description = "Please delete a transaction in Transaction\r\n"
        + "Following are the details\r\n" + "Transaction ID : " + id + "\r\n" + "";
    ticket.setDescription(description);
    ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.TRANSACTION_STATUS);
    ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
    ticket.setTime(new Date().getTime());
    try {
      DataStoreRegistry.getInstance().getTicketStore().save(ticket);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void revertStage3(String cAccountId, CompanyAccount cAccount) {
    if (cAccountId != null) {
      try {
        DataStoreRegistry.getInstance().getCompanyAccountStore().cancelTransaction(cAccountId);
      } catch (RemoteException e) {
        // raise a ticket
        raiseCompanyAccountTicket(cAccount);
        e.printStackTrace();
      }
    }
  }

  private void raiseCompanyAccountTicket(CompanyAccount cAccount) {
    Ticket ticket = new Ticket();
    ticket.setCreatedBy("SYSTEM");
    String description =
        "Please mark the status a company account transaction in companyaccount as CANCELLED\r\n"
            + "Following are the details\r\n" + "Interview Id : " + cAccount.getInterviewId()
            + "\r\n" + "Amount : " + cAccount.getAmount() + "\r\n" + "DebitOrCredit : "
            + cAccount.getDebitOrCredit() + "\r\n" + "Initiator : " + cAccount.getInitiator()
            + "\r\n" + "Purpose : " + cAccount.getPurpose() + "\r\n" + "CURRENT Status : "
            + cAccount.getStatus() + "\r\n" + "Time : " + cAccount.getTime() + "\r\n" + "";
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

  private void revertStage2(Interview interview, Double prevEscrowBalance) {
    Double currEB = null;
    try {
      currEB = DataStoreRegistry.getInstance().getInterviewDataStore()
          .getInterview(interview.getId()).getEb();
      Map<String, Object> changes = new HashMap<String, Object>();
      changes.put(DATASTORES.INTERVIEW.ESCROW_BALANCE, prevEscrowBalance);
      DataStoreRegistry.getInstance().getInterviewDataStore()
          .updateInterview(new ObjectId(interview.getId()), changes);
    } catch (RemoteException e) {
      try {
        double afterRevertEB = DataStoreRegistry.getInstance().getInterviewDataStore()
            .getInterview(interview.getId()).getEb();
        if (afterRevertEB != prevEscrowBalance) {
          // raise a tickets
          double diff = afterRevertEB - prevEscrowBalance;
          raiseEBTicket(interview, diff);
        }
      } catch (RemoteException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
    }
  }

  private void revertStage1(Interview interview, double prevBalance) {
    Double currBal = null;
    Double afterRevertBal = null;
    try {
      currBal = new Double(DataStoreRegistry.getInstance().getInterviewerDataStore()
          .getUserInfo(interview.getInterviewee()).get(USER.BALANCE).toString());
      DataStoreRegistry.getInstance().getInterviewerDataStore()
          .setBalance(interview.getInterviewee(), prevBalance);
    } catch (RemoteException e) {
      if (currBal != null) {
        try {
          afterRevertBal = new Double(DataStoreRegistry.getInstance().getInterviewerDataStore()
              .getUserInfo(interview.getInterviewee()).get(USER.BALANCE).toString());
        } catch (RemoteException e1) {
          e1.printStackTrace();
        }
        if (afterRevertBal != prevBalance) {
          // raise a ticket
          double diff = afterRevertBal - prevBalance;
          setBalanceTicket(interview, diff);
        }
      }
      e.printStackTrace();
    }
  }

  private void setBalanceTicket(Interview interview, Double diff) {
    Ticket ticket = new Ticket();
    ticket.setCreatedBy("SYSTEM");
    String description =
        "Please update the balance of " + interview.getInterviewee() + " by  \"" + diff + "\"";
    ticket.setDescription(description);
    ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.UPDATE_BALANCE);
    ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
    ticket.setTime(new Date().getTime());
    try {
      DataStoreRegistry.getInstance().getTicketStore().save(ticket);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private void raiseEBTicket(Interview interview, double diff) {
    Ticket ticket = new Ticket();
    ticket.setCreatedBy("SYSTEM");
    String description =
        "Please update the escrow balance of interview title " + interview.getTitle()
            + " that has Object Id : " + interview.getId() + " by \"" + diff + "\"";
    ticket.setDescription(description);
    ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.UPDATE_BALANCE);
    ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
    ticket.setTime(new Date().getTime());
    try {
      DataStoreRegistry.getInstance().getTicketStore().save(ticket);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}
