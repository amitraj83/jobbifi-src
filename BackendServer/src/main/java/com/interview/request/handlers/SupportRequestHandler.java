package com.interview.request.handlers;

import java.util.Map;

import org.apache.log4j.Logger;

import com.interview.framework.REQUEST_TYPES;

public class SupportRequestHandler extends RequestHandler {
  private Logger log = Logger.getLogger(UpdateProfileHandler.class);

  public SupportRequestHandler() {
    addHandler(this, REQUEST_TYPES.CREATE_SUPPORT_REQUEST);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    // TODO Auto-generated method stub
    return null;
  }
}
