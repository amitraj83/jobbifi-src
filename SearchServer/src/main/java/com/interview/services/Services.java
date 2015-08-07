package com.interview.services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.interview.framework.util.JSONUtilityService;
import com.interview.request.handlers.DeleteInterviewHandler;
import com.interview.request.handlers.InterviewerSearchHandler;
import com.interview.solr.SolrService;

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

  public SolrService getSolrService() {
    return (SolrService) myContext.getBean("solrservice");
  }

  public static Services getInstance() {
    return instance;
  }

  public InterviewerSearchHandler getInterviewerSearchHandler() {
    return (InterviewerSearchHandler) myContext.getBean("interviewerSearchHandler");
  }

  public DeleteInterviewHandler getDeleteInterviewHandler() {
    return (DeleteInterviewHandler) myContext.getBean("deleteInterviewHandler");
  }

  public JSONUtilityService getJSONUtilityService() {
    return (JSONUtilityService) myContext.getBean("jsontilityService");
  }
}
