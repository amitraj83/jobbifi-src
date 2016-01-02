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

import com.interview.framework.REQUEST_TYPES;
import com.interview.services.Services;
import com.interview.util.AdvisorSearchItem;

@Controller
public class StatController {


  @RequestMapping(value = "/stats.do", method = RequestMethod.GET)
  public ModelAndView processRequest(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {


    Map<String, Object> result = Services.getInstance().getRequestHandlerService()
        .handleRequest(new HashMap<Object, Object>(), REQUEST_TYPES.STATS);


    String json = Services.getInstance().getJSONUtilityService().getJSONStringOfMap(result);
    return new ModelAndView("response", "message", json);
  }


}
