package com.interview.services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.interview.framework.util.ConversionService;
import com.interview.framework.util.DecryptionService;
import com.interview.framework.util.EncryptionService;
import com.interview.framework.util.JSONUtilityService;
import com.interview.request.handlers.RequestHandler;

@Service
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

  // public BackendServerRMIClient getBackendServer(){
  // return (BackendServerRMIClient)myContext.getBean("backendServerRMIClient");
  // }

  public FileUtilities getFileUtilities() {
    return (FileUtilities) myContext.getBean("fileutilities");
  }

  // public BaseDataStore getBaseDataStore(){
  // return (BaseDataStore)myContext.getBean("baseDataStore");
  // }

  // public UploadedFileDataStore getUploadedFileDataStore(){
  // return (UploadedFileDataStore)myContext.getBean("uploadedfileDS");
  // }

  public JSONUtilityService getJSONUtilityService() {
    return (JSONUtilityService) myContext.getBean("jsontilityService");
  }

  public EncryptionService getEncryptionService() {
    return (EncryptionService) myContext.getBean("encryptionService");
  }

  public DecryptionService getDecryptionService() {
    return (DecryptionService) myContext.getBean("decryptionService");
  }

  public ConversionService getConversionService() {
    return (ConversionService) myContext.getBean("conversionService");
  }

  // public InterviewDataStore getInterviewDataStore(){
  // return (InterviewDataStore)myContext.getBean("interviewstore");
  // }

  // public BidStore getBidStore(){
  // return (BidStore)myContext.getBean("bidstore");
  // }


}
