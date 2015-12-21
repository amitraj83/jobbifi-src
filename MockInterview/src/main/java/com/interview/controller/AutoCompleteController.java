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
public class AutoCompleteController {

  @RequestMapping(value = "/pauth/relatedskills.do", method = RequestMethod.GET)
  public ModelAndView getRelatedSkills(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    String searchTerm = req.getParameter("terms");
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("term", searchTerm);
    reqMap.put("type", "SKILLS");
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.AUTO_COMPLETE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

}
