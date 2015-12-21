package com.interview.request.handlers;

import java.util.Map;

import com.interview.services.Services;

public class InterviewsSearchHandler {


  public Map<String, Object> search(String key) {
    return Services.getInstance().getSolrService().searchInterviews(key, 0, 50);
  }
}
