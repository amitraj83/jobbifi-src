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
import com.interview.framework.VARIABLES;
import com.interview.services.Services;

@Controller
public class SupportController extends BaseController {
  @RequestMapping(value = "/support.do", method = RequestMethod.POST)
  public ModelAndView processRequest(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(VARIABLES.SUPPORT_REQUEST_NAME, req.getParameter("name"));
    reqMap.put(VARIABLES.SUPPORT_REQUEST_MESSAGE, req.getParameter("message"));
    reqMap.put(VARIABLES.SUPPORT_REQUEST_EMAIL, req.getParameter("email"));
    System.out.println("reqMap: " + reqMap.toString());
    Map<String, Object> responseMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.CREATE_SUPPORT_REQUEST);
    return new ModelAndView("createSupportRequest", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(responseMap));
  }
}
