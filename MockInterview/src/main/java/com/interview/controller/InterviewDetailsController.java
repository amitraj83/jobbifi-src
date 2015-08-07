package com.interview.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.services.Services;

@Controller
public class InterviewDetailsController {

  @RequestMapping(value = "/interview/{iid}", method = RequestMethod.GET)
  public ModelAndView getInterviewDetails(@PathVariable String iid) {


    Map<Object, Object> req = new HashMap<Object, Object>();
    req.put(VARIABLES.IID, iid);

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(req, REQUEST_TYPES.INTERVIEW_DETAILS);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));


  }
}