package com.interview.request.handlers;

import java.util.Map;

import com.interview.services.Services;

public class InterviewerSearchHandler {

  public Map<String, Object> search(String key) {
    // public String search(String key){
    return Services.getInstance().getSolrService().search(key, 0, 50);
    // return new HashMap<String, Object>();
  }

}
