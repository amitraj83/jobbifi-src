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
import com.interview.services.Services;
import com.interview.util.Util;

@Controller
public class DeleteInterviewController {


  @RequestMapping(value = "/deleteinterview.do", method = RequestMethod.GET)
  public ModelAndView deleteInterview(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {


    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("_id", req.getParameter("iid"));
    reqMap.put("baseURL", Util.getbBaseURLpath(req));

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.DELETE_INTERVIEW);
    // TODO need to ask that how to call this because it gives null pointer exception
    /*
     * if (new Integer(resMap.get("code").toString()) == 0) {
     * Services.getInstance().getRequestHandlerService() .handleRequest(reqMap,
     * REQUEST_TYPES.DELETE_INTERVIEW_SOLR); }
     */


    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

}
