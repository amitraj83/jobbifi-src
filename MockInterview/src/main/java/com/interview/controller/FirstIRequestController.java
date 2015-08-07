package com.interview.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.FIRST_REQUESTS;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.UPDATE_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.services.Services;

@Controller
public class FirstIRequestController extends BaseController {



  @RequestMapping(value = "/firstirequest.do", method = RequestMethod.POST)
  public ModelAndView processRequest(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(VARIABLES.INTERVIEWEE, req.getParameter("interviewee"));
    reqMap.put(VARIABLES.INTERVIEWER, req.getParameter("interviewer"));
    Map<String, Object> responseMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.FIRST_REQUEST);

    return new ModelAndView("firstrequest", "message", Services.getInstance()
        .getJSONUtilityService().getJSONStringOfMap(responseMap));
  }

  @RequestMapping(value = "/acceptrequest.do", method = RequestMethod.GET)
  public ModelAndView acceptRequest(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
    System.out.println("Accept Request");
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(FIRST_REQUESTS.ID, req.getParameter("rid"));
    reqMap.put(FIRST_REQUESTS.STATUS, FIRST_REQUESTS.STATUSES.ACCEPTED);
    Map<String, Object> responseMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.PROCESS_FIRST_REQUEST);
    String author =getLoginUser();
    List<String> recipients = new ArrayList<String>();
    recipients.add((String) responseMap.get(FIRST_REQUESTS.INTERVIEWEE));    
    return new ModelAndView("firstrequest", "message", Services.getInstance()
        .getJSONUtilityService().getJSONStringOfMap(responseMap));
  }


  @RequestMapping(value = "/rejectrequest.do", method = RequestMethod.GET)
  public ModelAndView rejectRequest(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
    System.out.println("Reject Request");
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(FIRST_REQUESTS.ID, req.getParameter("rid"));
    reqMap.put(FIRST_REQUESTS.STATUS, FIRST_REQUESTS.STATUSES.REJECTED);
    Map<String, Object> responseMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.PROCESS_FIRST_REQUEST);
    String author =getLoginUser();
    List<String> recipients = new ArrayList<String>();
    recipients.add((String) responseMap.get(FIRST_REQUESTS.INTERVIEWEE));

    return new ModelAndView("firstrequest", "message", Services.getInstance()
        .getJSONUtilityService().getJSONStringOfMap(responseMap));
  }



}
