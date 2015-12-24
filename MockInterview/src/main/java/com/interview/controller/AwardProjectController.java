package com.interview.controller;

import java.io.IOException;
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
import com.interview.util.Util;

@Controller
public class AwardProjectController extends BaseController {

  @RequestMapping(value = "/awardinterview.do", method = RequestMethod.GET)
  public ModelAndView awardProject(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("iid", req.getParameter("iid"));
    reqMap.put("bid", req.getParameter("bid"));
    reqMap.put("baseURL", Util.getbBaseURLpath(req));
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.AWARD_INTERVIEW);

    String user = getLoginUser();
    Map<Object, Object> userName = new HashMap<Object, Object>();
    userName.put(USER.USERNAME, user);

    Map<String, Object> bidMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(userName, REQUEST_TYPES.GET_MY_BIDS);

    // resMap.putAll(bidMap);
    try {
      resMap.put(VARIABLES.MY_BIDS,
          Services.getInstance().getJSONUtilityService().writeValueAsString(bidMap));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }
}
