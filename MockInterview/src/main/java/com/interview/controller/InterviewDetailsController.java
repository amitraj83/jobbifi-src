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
import com.interview.framework.pojo.Bid;
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
    map.addAttribute("iid", iid);
    if (null != getLoginUser()) {
    	Map<Object, Object> req = new HashMap<Object, Object>();
        req.put(VARIABLES.Bid.BIDDER, getLoginUser());
        req.put(VARIABLES.Bid.INTERVIEW_ID, iid);
        req.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.BID_SUB_REQ.GET_BID_BY_BIDDER_AND_INTERVIEW);

        Map<String, Object> resMap1 = Services.getInstance().getRequestHandlerService()
            .handleRequest(req, REQUEST_TYPES.BID_REQ);
        Object obj = resMap1.get("bid");
        if (null != obj) {
        	map.addAttribute("isBidPlaced", "Y");
        }else{
        	map.addAttribute("isBidPlaced", "N");
        }
      }

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
