package com.interview.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.services.Services;

@Controller
public class JobSearchController {

  private static final int NO_OF_RESULTS = 10;

  @RequestMapping(value = "/searchjobs.do", method = RequestMethod.GET)
  public ModelAndView searchJob(HttpServletRequest req, HttpServletResponse res) {

    Map<Object, Object> requestMap = new HashMap<Object, Object>();
    requestMap.put("searchkey", req.getParameter("searchkey"));
    requestMap.put("start", Integer.parseInt(req.getParameter("start")));
    requestMap.put("rows", Integer.parseInt(req.getParameter("rows")));
    // requestMap.put("rows", NO_OF_RESULTS);

    Map<String, Object> responseMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(requestMap, REQUEST_TYPES.SEARCH_JOBS);

    /** Get additional data */
    requestMap = new HashMap<Object, Object>();
    Map<String, Object> result = (Map<String, Object>) responseMap.get("JSON_DOC_LIST");
    requestMap.put("result", result);
    requestMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.SEARCH_JOB_INFO);
    result = Services.getInstance().getRequestHandlerService().handleRequest(requestMap,
        REQUEST_TYPES.JOB);
    responseMap.put("JSON_DOC_LIST", result.get("result"));

    return new ModelAndView("searchResult", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(responseMap));
  }
}
