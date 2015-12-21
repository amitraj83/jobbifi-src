package com.interview.services;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.interview.datastore.BaseDataStore;
import com.interview.datastore.BidStore;
import com.interview.datastore.ChatStore;
import com.interview.datastore.EducationStore;
import com.interview.datastore.InterviewDataStore;
import com.interview.datastore.InterviewerDataStore;
import com.interview.datastore.JobStore;
import com.interview.datastore.NotificationStore;
import com.interview.datastore.PositionStore;
import com.interview.datastore.RatingStore;
import com.interview.datastore.RequestsDataStore;
import com.interview.datastore.ResetPasswordDataStore;
import com.interview.datastore.SkillDataStore;
import com.interview.datastore.SkillsDataStore;
import com.interview.datastore.TransactionStore;
import com.interview.datastore.UploadedFileDataStore;
import com.interview.datastore.UserTransactionDataStore;
import com.interview.framework.MongoService;
import com.interview.framework.util.JSONUtilityService;

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

  public com.interview.rmi.RmiDataStoreServer getRMIServer() {
    return (com.interview.rmi.RmiDataStoreServer) myContext.getBean("rmiserver");
  }

  public static Services getInstance() {
    return instance;
  }

  public MongoService getMongoService() {
    return (MongoService) myContext.getBean("mongoService");
  }

  public BaseDataStore getBaseDataStore() {
    return (BaseDataStore) myContext.getBean("baseDataStore");
  }

  public InterviewerDataStore getInterviewerDataStore() {
    return (InterviewerDataStore) myContext.getBean("interviewerDataStore");
  }

  public ThreadPoolTaskExecutor getThreadPoolService() {
    return (ThreadPoolTaskExecutor) myContext.getBean("taskExecutor");
  }


  public RequestsDataStore getRequestsDataStore() {
    return (RequestsDataStore) myContext.getBean("requestsDataStore");
  }

  public ChatStore getChatStore() {
    return (ChatStore) myContext.getBean("chatStore");
  }

  public InterviewDataStore getInterviewDataStore() {
    return (InterviewDataStore) myContext.getBean("interviewDataStore");
  }

  public JSONUtilityService getJSONUtilityService() {
    return (JSONUtilityService) myContext.getBean("jsontilityService");
  }

  public BidStore getBidStore() {
    return (BidStore) myContext.getBean("bidStore");
  }

  public RatingStore getRatingStore() {
    return (RatingStore) myContext.getBean("ratingStore");
  }

  public NotificationStore getNotificationStore() {
    return (NotificationStore) myContext.getBean("notificationStore");
  }

  public ResetPasswordDataStore getResetPasswordDataStore() {
    return (ResetPasswordDataStore) myContext.getBean("resetPassStore");
  }

  public SkillsDataStore getSkillsDataStore() {
    return (SkillsDataStore) myContext.getBean("skillsdatastore");
  }

  public UploadedFileDataStore getUploadedFileDataStore() {
    return (UploadedFileDataStore) myContext.getBean("uploadedfileDS");
  }

  public TransactionStore getTransactionStore() {
    return (TransactionStore) myContext.getBean("transactionStore");
  }

  public CompanyServices getCompanyServices() {
    return (CompanyServices) myContext.getBean("companyService");
  }

  public UserTransactionDataStore getUserTransactionDataStore() {
    return (UserTransactionDataStore) myContext.getBean("userTransactionDataStore");
  }

  public PositionStore getPositionStore() {
    return (PositionStore) myContext.getBean("positionStore");
  }

  public EducationStore getEducationStore() {
    return (EducationStore) myContext.getBean("educationStore");
  }

  public SkillDataStore getSkillDataStore() {
    return (SkillDataStore) myContext.getBean("skilldatastore");
  }

  public JobStore getJobStore() {
    return (JobStore) myContext.getBean("jobstore");
  }

}
