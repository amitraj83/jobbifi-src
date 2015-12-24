package com.interview.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.services.Services;

@Controller
public class InterviewDetailsController extends BaseController {

  @RequestMapping(value = "/mockdetail.do", method = RequestMethod.GET)
  public String getInterviewDetails(@RequestParam String iid, Model map) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("_id", iid);
    reqMap.put(VARIABLES.IID, iid);


    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.INTERVIEW_DETAILS);
    map.addAllAttributes(resMap);


    String username = getLoginUser();
    if (username != null) {
      reqMap.put(REQUEST_TYPES.SUB_REQ, "ESCROWLIST");
      reqMap.put(USER.USERNAME, username);
      reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.USER_REQ_SUB_REQ.USER_BALANCE);

      resMap = Services.getInstance().getRequestHandlerService().handleRequest(reqMap,
          REQUEST_TYPES.USER_REQ);

      map.addAllAttributes(resMap);

    }
    return "mockDetail";


  }
}
