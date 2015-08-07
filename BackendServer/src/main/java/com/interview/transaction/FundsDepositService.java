package com.interview.transaction;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Ticket;
import com.interview.framework.pojo.Transaction;
import com.interview.framework.pojo.Transactions;
import com.interview.framework.pojo.UserTransaction;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service("fundsdeposit")
public class FundsDepositService {
	
	@Autowired
	private Properties myProps;
	
	private Logger log = Logger.getLogger(FundsDepositService.class);
	
	public int deposit(String user, double amount, String thirdPartyId) {
		int stage = 0;
	   try {
	      double prev_bal =
	          new Double(DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(user)
	              .get(USER.BALANCE).toString());
	
	      DataStoreRegistry.getInstance().getInterviewerDataStore()
	          .updateBalance(user, amount, VARIABLES.ADD);
	      stage = 1;
	
	      double bal =
	          new Double(DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(user)
	              .get(USER.BALANCE).toString());
	      if ((bal - prev_bal) == amount) {
	        stage = 2;
	        Transaction transaction =
	            Services
	                .getInstance()
	                .getTransactionHistoryService()
	                .logTransaction(new Date().getTime(), DATASTORES.TRANSACTION.TTYPE.DEBIT, user,
	                    user, DATASTORES.TRANSACTION.TSTATUS.DONE, "Funds deposited ", amount, 0,
	                    amount, bal);
	        stage = 3;
	
	        UserTransaction userTransaction = new UserTransaction();
	        userTransaction.setArtifact(DATASTORES.USER_TRANSACTION.ARTIFACT_TYPE.THIRD_PARTY_DEPOSIT);
	        userTransaction.setArtifactid(thirdPartyId);
	        userTransaction
	            .setPurpose(DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE_TYPE.ACCOUNT_DEPOSIT);
	        userTransaction.setTid(transaction.getId());
	        userTransaction.setTime(new Date().getTime());
	        userTransaction.setUsername(user);
	
	        stage = 4;
	        DataStoreRegistry.getInstance().getUserTransactionStore().save(userTransaction);
	
	        stage = 5;
	        Services
	            .getInstance()
	            .getNotificationService()
	            .processNotification(transaction,
	                VARIABLES.NOTIFICATION.TYPE.DEPOSIT_FUNDS_NOTIFICATION);
	      } else
	        stage = 6;
	
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (stage == 0) {
	        Ticket ticket = new Ticket();
	        ticket.setAgent("");
	        ticket.setCreatedBy(user);
	        String description =
	            "User balance update is failed. Please update the balance:\r\n" + "Username:" + user
	                + "\r\n" + "Amount: " + amount + "\r\n" + "Thirdparty ID: " + thirdPartyId;
	        ticket.setDescription(description);
	        ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.UPDATE_BALANCE);
	        ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
	        ticket.setTime(new Date().getTime());
	
	        try {
	          DataStoreRegistry.getInstance().getTicketStore().save(ticket);
	        } catch (RemoteException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      }
	      if (stage == 2 || stage == 3 || stage == 4) {
	        Ticket ticket = new Ticket();
	        ticket.setAgent("");
	        ticket.setCreatedBy(user);
	        String description =
	            "Transaction history missing :\r\n" + "DEBIT_OR_CREDIT:"
	                + DATASTORES.TRANSACTION.TTYPE.DEBIT + "\r\n" + "From: " + user + "\r\n" + "To: "
	                + user + "\r\n" + "Account Message:" + "Funds deposited \r\n" + "AmountSent: "
	                + amount + "\r\n" + "Fee: " + "0" + "\r\n" + "NetAmount: " + amount + "\r\n"
	                + "Thirdparty ID: " + thirdPartyId;
	        ticket.setDescription(description);
	        ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.TRANSACTION_HISTORY_MISSING);
	        ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
	        ticket.setTime(new Date().getTime());
	
	        try {
	          DataStoreRegistry.getInstance().getTicketStore().save(ticket);
	        } catch (RemoteException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      }
	      if (stage == 5)
	        return 1;
	    }
	    return 0;
  }

 
  public String logtransaction(Transactions transactions, String thirdPartyId) {
	  String transactionid="";
	  try {
	  double prev_bal =
	          new Double(DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(transactions.getOwner())
	              .get(USER.BALANCE).toString());
	 
	  
		Transaction transaction =
		            Services
		                .getInstance()
		                .getTransactionHistoryService()
		                .logTransaction(new Date().getTime(), DATASTORES.TRANSACTION.TTYPE.DEBIT, transactions.getOwner(),
		                    transactions.getOwner(), DATASTORES.TRANSACTION.TSTATUS.PENDING, "Paypal Initiated ", transactions.getAmount(), 0,
		                    transactions.getAmount(), prev_bal);
		
		  transactionid=transaction.getId();
		
	} catch (RemoteException e) {		
		e.printStackTrace();
	}
	  
	  return transactionid;
  }
  public int withdrawDeposit(Transactions transactions, String thirdPartyId){
	  String user= transactions.getOwner();
		double amount= new Double(transactions.getAmount());
	  String transactionid="";
	  double balance=0.0;
	  try{
		  double prev_bal =
		          new Double(DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(user)
		              .get(USER.BALANCE).toString());
		  if(prev_bal < amount)
			  return 0;
		  else{
		  balance=prev_bal - amount;
		  DataStoreRegistry.getInstance().getInterviewerDataStore().setBalance(user, balance);
		  Transaction transaction=   Services
	                    .getInstance()
	                    .getTransactionHistoryService()
	                    .logTransaction(new Date().getTime(), DATASTORES.TRANSACTION.TTYPE.DEBIT, user,
	                        user, DATASTORES.TRANSACTION.TSTATUS.PENDING, "Funds Withdrow from your account ", amount, 0, amount, balance);
	                
	                UserTransaction userTransaction = new UserTransaction();
			        userTransaction.setArtifact(DATASTORES.USER_TRANSACTION.ARTIFACT_TYPE.WITHDRAW);
			        userTransaction.setArtifactid(thirdPartyId);
			        userTransaction
			            .setPurpose(DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE_TYPE.ACCOUNT_DEPOSIT);
			        userTransaction.setTid(transaction.getId());
			        userTransaction.setTime(new Date().getTime());
			        userTransaction.setUsername(user);
		
			        DataStoreRegistry.getInstance().getUserTransactionStore().save(userTransaction);
	                
	                Map<String, Object> model = new HashMap<String, Object>(); 
	                model.put("user", user);
		  	        model.put("amount", amount);
	                Services.getInstance().getEmailService().sendMail(myProps.getProperty("mail.support.finanace.to"), 
		  	  	   			myProps.getProperty("mail.withdrawdeposit.subject"), "withdrawfund.ftl",model);
		  }
		  
	  }catch(Exception e){
		  e.printStackTrace();		  
	  }
	 return 1; 
  }
  public int funddeposit(String transactionid, String thirdPartyId) {
	  
	  String user="";
	  double amount=0.0;
	  int stage = 0;
	  
	   try {

		   Transaction transaction = 
		         (DataStoreRegistry.getInstance().getTransactionStore().getTransaction(new ObjectId(transactionid)));
		   
		   user = transaction.getOwner();
		   amount = transaction.getNetamount();
		   double prev_bal = transaction.getBalance();
		   
		   log.info("USER : " + user);
		   log.info("Amount :" + amount);
		   log.info("prev_bal : " + prev_bal);
		   
	       DataStoreRegistry.getInstance().getInterviewerDataStore().updateBalance(user, amount, VARIABLES.ADD);
	       stage = 1;

	       double bal = new Double(DataStoreRegistry.getInstance().getInterviewerDataStore().getUserInfo(user)
	    		   			.get(USER.BALANCE).toString());
	       
	       if((bal - prev_bal) == amount) {
		       stage = 2;
		        
		       transaction =
		                Services
		                    .getInstance()
		                    .getTransactionHistoryService()
		                    .updateTransaction(transactionid,new Date().getTime(), DATASTORES.TRANSACTION.TTYPE.CREDIT, user,
		                        user, DATASTORES.TRANSACTION.TSTATUS.DONE, "Funds deposited from paypay to your account", amount, 0, amount, bal);
		        stage = 3;
	
		        UserTransaction userTransaction = new UserTransaction();
		        userTransaction.setArtifact(DATASTORES.USER_TRANSACTION.ARTIFACT_TYPE.THIRD_PARTY_DEPOSIT);
		        userTransaction.setArtifactid(thirdPartyId);
		        userTransaction
		            .setPurpose(DATASTORES.USER_TRANSACTION.TRANSACTION_PURPOSE_TYPE.ACCOUNT_DEPOSIT);
		        userTransaction.setTid(transaction.getId());
		        userTransaction.setTime(new Date().getTime());
		        userTransaction.setUsername(user);
	
		        stage = 4;
		        DataStoreRegistry.getInstance().getUserTransactionStore().save(userTransaction);
	
		        stage = 5;
		        Services
		            .getInstance()
		            .getNotificationService()
		            .processNotification(transaction,
		                VARIABLES.NOTIFICATION.TYPE.DEPOSIT_FUNDS_NOTIFICATION);
	      } else
	        stage = 6;

	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      if (stage == 0) {
	        Ticket ticket = new Ticket();
	        ticket.setAgent("");
	        ticket.setCreatedBy(user);
	        String description =
	            "User balance update is failed. Please update the balance:\r\n" + "Username:" + user
	                + "\r\n" + "Amount: " + amount + "\r\n" + "Thirdparty ID: " + thirdPartyId;
	        ticket.setDescription(description);
	        ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.UPDATE_BALANCE);
	        ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
	        ticket.setTime(new Date().getTime());

	        try {
	          DataStoreRegistry.getInstance().getTicketStore().save(ticket);
	        } catch (RemoteException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      }
	      if (stage == 2 || stage == 3 || stage == 4) {
	        Ticket ticket = new Ticket();
	        ticket.setAgent("");
	        ticket.setCreatedBy(user);
	        String description =
	            "Transaction history missing :\r\n" + "DEBIT_OR_CREDIT:"
	                + DATASTORES.TRANSACTION.TTYPE.DEBIT + "\r\n" + "From: " + user + "\r\n" + "To: "
	                + user + "\r\n" + "Account Message:" + "Funds deposited \r\n" + "AmountSent: "
	                + amount + "\r\n" + "Fee: " + "0" + "\r\n" + "NetAmount: " + amount + "\r\n"
	                + "Thirdparty ID: " + thirdPartyId;
	        ticket.setDescription(description);
	        ticket.setServicetype(DATASTORES.TICKET.SERVICE_TYPES.TRANSACTION_HISTORY_MISSING);
	        ticket.setStatus(DATASTORES.TICKET.STATUS_TYPES.UNATTENDED);
	        ticket.setTime(new Date().getTime());

	        try {
	        	DataStoreRegistry.getInstance().getTicketStore().save(ticket);
	        } catch (RemoteException e) {
	          e.printStackTrace();
	        }
	      }
	      if (stage == 5)
	        return 1;
	    }
	    return 0;
	  }
  
  	public void updateTransactionDetails(String  transactionid, String details) {
	  try {
		  DataStoreRegistry.getInstance().getTransactionStore().updateTransactionDetails(transactionid, details);
	  } catch(Exception e) {
		  log.error("Exception :: ", e);
	  }	  
  	}
}
