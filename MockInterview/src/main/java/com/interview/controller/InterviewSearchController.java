package com.interview.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.services.Services;

@Controller
public class InterviewSearchController extends BaseController {

  private static final int NO_OF_RESULTS = 10;

  @RequestMapping(value = "/mocks.do", method = RequestMethod.GET)
  public String getmocks() {
    return "mocks";
  }

  @RequestMapping(value = "/mock/details.do", method = RequestMethod.GET)
  public String getmockdetails(@RequestParam("mid") String mid, Model map) {

    Map<Object, Object> req = new HashMap<Object, Object>();
    req.put(REQUEST_TYPES.SUB_REQ,
        REQUEST_TYPES.SEARCH_INTERVIEW_SUB_INFO.SEARCH_INTERVIEW_SUB_INFO);
    req.put("ID", mid);

    Map<String, Object> res = Services.getInstance().getRequestHandlerService().handleRequest(req,
        REQUEST_TYPES.SEARCH_INTERVIEW_INFO);
    map.addAttribute("budget", res.get("budget"));
    map.addAttribute("description", res.get("description"));
    map.addAttribute("experience", res.get("experience"));
    map.addAttribute("dated", res.get("dated"));
    map.addAttribute("title", res.get("title"));
    map.addAttribute("industry", res.get("industry"));
    map.addAttribute("interviewee", res.get("interviewee"));
    map.addAttribute("skills", res.get("skills"));
    map.addAttribute("iid", res.get("iid"));
    map.addAttribute("status", res.get("status"));
    map.addAttribute("statusString", res.get("statusString"));
    map.addAttribute("profilepic", res.get("profilepic"));


    Bid bid = null;
    if (null != getLoginUser()) {
      req = new HashMap<Object, Object>();
      req.put(VARIABLES.Bid.BIDDER, getLoginUser());
      req.put(VARIABLES.Bid.INTERVIEW_ID, mid);
      req.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.BID_SUB_REQ.GET_BID_BY_BIDDER_AND_INTERVIEW);

      Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
          .handleRequest(req, REQUEST_TYPES.BID_REQ);
      Object obj = resMap.get("bid");
      if (null != obj) {
        bid = (Bid) obj;
      }
    }
    map.addAttribute("bid", bid);

    return "mockDetail";
  }

  @RequestMapping(value = "/interviewsearch.do", method = RequestMethod.GET)
  public ModelAndView searchInterviews(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    Map<Object, Object> requestMap = new HashMap<Object, Object>();
    requestMap.put("searchkey", req.getParameter("searchkey"));
    requestMap.put("start", Integer.parseInt(req.getParameter("start")));
    requestMap.put("rows", NO_OF_RESULTS);

    Map<String, Object> responseMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(requestMap, REQUEST_TYPES.SEARCH_INTERVIEWS);
    Set<String> IIDs = new HashSet<String>();
    Set<String> Interviewees = new HashSet<String>();

    Map<String, Object> result = (Map<String, Object>) responseMap.get("JSON_DOC_LIST");
    Collection<Object> maps = result.values();
    for (Object object : maps) {
      Map<String, Object> map = (Map<String, Object>) object;
      IIDs.add((String) map.get("id"));
      Interviewees.add((String) map.get("interviewee"));
    }

    requestMap = new HashMap<Object, Object>();
    requestMap.put("iids", IIDs);
    requestMap.put("usernames", Interviewees);
    requestMap.put(USER.USERNAME, getLoginUser());

    Map<String, Object> infoMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(requestMap, REQUEST_TYPES.SEARCH_INTERVIEW_INFO);
    responseMap.putAll(infoMap);
    return new ModelAndView("searchResult", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(responseMap));

  }
}
