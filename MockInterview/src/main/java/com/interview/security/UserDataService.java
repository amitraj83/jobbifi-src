package com.interview.security;

import java.util.HashMap;
import java.util.Map;

import com.interview.framework.USER;
import com.interview.services.Services;

public class UserDataService {

  public Map<String, Object> getUserData(String userEmailId, String reqType) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(USER.EMAIL, userEmailId);
    return Services.getInstance().getRequestHandlerService().handleRequest(reqMap, reqType);
  }
}
