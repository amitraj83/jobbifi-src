package com.interview.services;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.session.SessionRegistryImpl;

import com.interview.framework.util.JSONUtilityService;
import com.interview.norbert.client.BackendClient;
import com.interview.norbert.client.FileServerClient;
import com.interview.norbert.client.SearchClient;
import com.interview.rmi.common.UserSessionManager;
import com.interview.security.UserDataService;

public class Services implements ApplicationContextAware {
  @Resource(name = "sessionRegistry")
  private SessionRegistryImpl sessionRegistry;
  private static ApplicationContext myContext;
  private static Services instance;

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    instance = this;
    this.myContext = context;
  }

  public static Services getInstance() {
    return instance;
  }

  public RequestHandlerService getRequestHandlerService() {
    return (RequestHandlerService) myContext.getBean("requestHandler");
  }

  public ShaPasswordEncoder getPasswordEncoder() {
    return (ShaPasswordEncoder) myContext.getBean("passwordEncoder");
  }

  public UserDataService getUserDataService() {
    return (UserDataService) myContext.getBean("userDataService");
  }

  public UserSessionManager getUserSessionManager() {
    return (UserSessionManager) myContext.getBean("userSessionManager");
  }

  public JSONUtilityService getJSONUtilityService() {
    return (JSONUtilityService) myContext.getBean("jsontilityService");
  }

  public SessionRegistryImpl getSessionRegistry() {
    return sessionRegistry;
  }

  public FileUtilities getFileUtilities() {
    return (FileUtilities) myContext.getBean("fileutilities");
  }

  public CalendarService getCalendarService() {
    return (CalendarService) myContext.getBean("calendarService");
  }

  public LinkedInProfile2UserDetails getLinkedInProfile2UserDetailsService() {
    return (LinkedInProfile2UserDetails) myContext.getBean("InProfile2UserDetails");
  }

  public PrepareLoginData getPrepareLoginDataService() {
    return (PrepareLoginData) myContext.getBean("prepareLoginData");
  }

  public BackendClient getBackendClient() {
    return (BackendClient) myContext.getBean("backendClient");
  }

  public FileServerClient getFileServerClient() {
    return (FileServerClient) myContext.getBean("fsclient");
  }

  public SearchClient getSearchServerClient() {
    return (SearchClient) myContext.getBean("searchclient");
  }
}
