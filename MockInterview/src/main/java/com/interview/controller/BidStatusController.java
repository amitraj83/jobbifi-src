package com.interview.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.services.Services;

@Controller
public class BidStatusController extends BaseController {

  @RequestMapping(value = "/statuschange.do", method = RequestMethod.GET)
  public ModelAndView changeStatus(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {


    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(VARIABLES.Bid.INTERVIEW_ID, req.getParameter(VARIABLES.Bid.INTERVIEW_ID));
    reqMap.put(VARIABLES.Bid.BID_ID, req.getParameter("bid"));
    reqMap.put(VARIABLES.Bid.STATUS, req.getParameter(VARIABLES.Bid.STATUS));


    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.STATUS_CHANGE);

    Map<Object, Object> userName = new HashMap<Object, Object>();
    userName.put(USER.USERNAME, getLoginUser());


    Map<String, Object> interviewMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(userName, REQUEST_TYPES.GET_INTERVIEW);
    resMap.put(VARIABLES.MY_INTERVIEW,
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(interviewMap));

    Map<String, Object> bidMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(userName, REQUEST_TYPES.GET_MY_BIDS);
    resMap.put(VARIABLES.MY_BIDS,
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(bidMap));

    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }


}
