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


@Controller
public class InterviewCompleteController {


  @RequestMapping(value = "/completeinterview.do", method = RequestMethod.GET)
  public ModelAndView completeInterview(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    String iid = req.getParameter("iid");

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("_id", iid);

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.COMPLETE_INTERVIEW);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }


}
