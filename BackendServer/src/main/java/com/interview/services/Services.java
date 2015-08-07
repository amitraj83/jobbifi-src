package com.interview.services;

import javax.management.remote.rmi.RMIServer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.interview.framework.util.JSONUtilityService;
import com.interview.lifecycle.BidAccept;
import com.interview.lifecycle.CancelInterview;
import com.interview.lifecycle.CompleteInterview;
import com.interview.lifecycle.DeleteInterview;
import com.interview.lifecycle.EditInterview;
import com.interview.lifecycle.EscrowDeposit;
import com.interview.lifecycle.InterviewDispute;
import com.interview.lifecycle.RatingService;
import com.interview.lifecycle.ReleaseFunds;
import com.interview.lifecycle.RepostInterview;
import com.interview.notification.NotificationService;
import com.interview.request.handlers.RequestHandler;
import com.interview.rmi.RMIRegistry;
import com.interview.transaction.EscrowDepositService;
import com.interview.transaction.EscrowReleaseService;
import com.interview.transaction.EscrowRevertService;
import com.interview.transaction.FundsDepositService;
import com.interview.transaction.TransactionHistoryService;
import com.interview.util.UserNameGenerator;

public class Services implements ApplicationContextAware {

  private static ApplicationContext myContext;
  private static Services instance = null;

  public Services() {
    instance = this;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.myContext = context;
  }

  public RequestHandler getRequestHandler(String reqType) {
    return RequestHandler.getHandlerForRequestType(reqType);
  }

  public static Services getInstance() {
    return instance;
  }

  public ThreadPoolTaskExecutor getThreadPoolService() {
    return (ThreadPoolTaskExecutor) myContext.getBean("taskExecutor");
  }

  public JSONUtilityService getJSONUtilityService() {
    return (JSONUtilityService) myContext.getBean("jsontilityService");
  }

  public StatusChangeValidator getStatusValidatorService() {
    return (StatusChangeValidator) myContext.getBean("statusChangeValidator");
  }

  public BidAccept getBidAcceptService() {
    return (BidAccept) myContext.getBean("bidAccept");
  }

  public CancelInterview getCancelInterviewService() {
    return (CancelInterview) myContext.getBean("CancelInterview");
  }

  public CompleteInterview getCompleteInterviewService() {
    return (CompleteInterview) myContext.getBean("CompleteInterview");
  }

  public DeleteInterview getDeleteInterviewService() {
    return (DeleteInterview) myContext.getBean("DeleteInterview");
  }

  public EditInterview getEditInterviewService() {
    return (EditInterview) myContext.getBean("EditInterview");
  }

  public EscrowDeposit getEscrowDeposit() {
    return (EscrowDeposit) myContext.getBean("EscrowDeposit");
  }

  public InterviewDispute getInterviewDisputeService() {
    return (InterviewDispute) myContext.getBean("InterviewDispute");
  }

  public ReleaseFunds getReleaseFundsService() {
    return (ReleaseFunds) myContext.getBean("ReleaseFunds");
  }

  public RepostInterview getRepostInterviewService() {
    return (RepostInterview) myContext.getBean("RepostInterview");
  }

  public RatingService getRatingService() {
    return (RatingService) myContext.getBean("rating");
  }

  public TransactionHistoryService getTransactionHistoryService() {
    return (TransactionHistoryService) myContext.getBean("transactionHistoryService");
  }

  public NotificationService getNotificationService() {
    return (NotificationService) myContext.getBean("notificationService");
  }

  public CompanyServices getCompanyServices() {
    return (CompanyServices) myContext.getBean("companyService");
  }

  public IVSpecService getIVSpecService() {
    return (IVSpecService) myContext.getBean("ivspec");
  }

  public EncryptionService getEncryptionService() {
    return (EncryptionService) myContext.getBean("encryptionService");
  }

  public DecryptionService getDecryptionService() {
    return (DecryptionService) myContext.getBean("decryptionService");
  }

  public ConversionService getConversionService() {
    return (ConversionService) myContext.getBean("conservice");
  }

  public SecurityTokenGenerator getSecurityTokenGenerator() {
    return (SecurityTokenGenerator) myContext.getBean("secTokenGen");
  }

  public GenerateResetPasswordURLService getGenerateResetPasswordURLService() {
    return (GenerateResetPasswordURLService) myContext.getBean("generateresetpassURL");
  }

  public EmailService getEmailService() {
    return (EmailService) myContext.getBean("emailservice");
  }

  public FundsDepositService getFundsDepositService() {
    return (FundsDepositService) myContext.getBean("fundsdeposit");
  }

  public EscrowDepositService getEscrowDepositService() {
    return (EscrowDepositService) myContext.getBean("escrowdepositT");
  }

  public EscrowRevertService getEscrowRevertService() {
    return (EscrowRevertService) myContext.getBean("escrowrevertT");
  }

  public EscrowReleaseService getEscrowReleaseService() {
    return (EscrowReleaseService) myContext.getBean("escrowreleaseT");
  }

  public UserNameGenerator getUserNameGeneratorService() {
    return (UserNameGenerator) myContext.getBean("ungenerator");
  }

  public PasswordGenerator getPasswordGenerator() {
    return (PasswordGenerator) myContext.getBean("passgenerator");
  }

  public RMIRegistry getRMIRegistry() {
    return (RMIRegistry) myContext.getBean("rmiregistry");
  }

  public RMIServer getRMIServer() {
    return (RMIServer) myContext.getBean("rmiserver");
  }
}
