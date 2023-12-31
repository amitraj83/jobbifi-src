package com.interview.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.services.Services;

@Controller
public class CandidateSearchController extends BaseController {
  private static final int NO_OF_RESULTS = 10;

  @RequestMapping(value = "/searchcandidates.do", method = RequestMethod.GET)
  public String getCandidateSearch() {
    return "candidateSearch";
  }

  @RequestMapping(value = "/searchcandidates.do", method = RequestMethod.POST)
  public ModelAndView searchCandidate(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {
    Map<Object, Object> requestMap = new HashMap<Object, Object>();
    requestMap.put("searchkey", req.getParameter("searchkey"));
    requestMap.put("start", Integer.parseInt(req.getParameter("start")));
    requestMap.put("rows", NO_OF_RESULTS);
    Map<String, Object> responseMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(requestMap, REQUEST_TYPES.SEARCH_INTERVIEWEE);
    Map<String, Object> data = (Map<String, Object>) responseMap.get("JSON_DOC_LIST");
    if (data.values() != null && data.values().size() > 0) {
      Iterator<String> it = data.keySet().iterator();
      Map<Object, Object> usersList = new HashMap<Object, Object>();
      while (it.hasNext()) {
        String key = it.next();
        Map<String, Object> interviewer = (Map<String, Object>) data.get(key);
        if (interviewer.get("username") != null)
          usersList.put(interviewer.get("username").toString(), "");
      }
      /* get additional data for the user */
      Map<String, Object> additionalMap = Services.getInstance().getRequestHandlerService()
          .handleRequest(usersList, REQUEST_TYPES.SEARCH_ADDITIONAL_DATA);
      it = data.keySet().iterator();
      while (it.hasNext()) {
        String key = it.next();
        Map<String, Object> interviewer = (Map<String, Object>) data.get(key);
        // Check the case for the user exist in solr but not in the database
        // This is not case as we do not have a delete functionality in the application
        if (interviewer.get("username") != null
            && ((Map<String, Object>) additionalMap.get(interviewer.get("username"))) != null) {
          ((Map<String, Object>) additionalMap.get(interviewer.get("username"))).put("online", "0");
          interviewer.put("additional", additionalMap.get(interviewer.get("username")));
        }
      }
    }
    return new ModelAndView("searchResult", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(responseMap));
  }
}
