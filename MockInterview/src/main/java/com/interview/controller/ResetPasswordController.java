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
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.services.Services;


@Controller
public class ResetPasswordController {


  @RequestMapping(value = "/resetpassword.do", method = RequestMethod.POST)
  public ModelAndView resetPassword(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(REQUEST_TYPES.SUB_REQ_RESET_PASSWORD, "3");
    reqMap.put(VARIABLES.RESET_PASS.AUTH_ID, req.getParameter("authid"));
    reqMap.put(VARIABLES.RESET_PASS.AUTH_INSTANCE, req.getParameter("authinstance"));
    reqMap.put(VARIABLES.RESET_PASS.AUTH_TOKEN, req.getParameter("authtoken"));
    reqMap.put(USER.PASSWORD, req.getParameter("password"));

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.RESET_PASSWORD);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/resetpasswordscreen.do", method = RequestMethod.GET)
  public String resetPasswordScreen(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(REQUEST_TYPES.SUB_REQ_RESET_PASSWORD, "2");
    reqMap.put(VARIABLES.RESET_PASS.AUTH_ID, req.getParameter("authid"));
    reqMap.put(VARIABLES.RESET_PASS.AUTH_INSTANCE, req.getParameter("authinstance"));
    reqMap.put(VARIABLES.RESET_PASS.AUTH_TOKEN, req.getParameter("authtoken"));

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.RESET_PASSWORD);
    int result = new Integer(resMap.get("response").toString());
    
    model.addAttribute("status", result);
    model.addAttribute("authid", req.getParameter("authid")); 
    model.addAttribute("authinstance", req.getParameter("authinstance")); 
    model.addAttribute("authtoken", req.getParameter("authtoken")); 
    return "forward:/resetpasswordscreen.jsp";
  }

  @RequestMapping(value = "/resetpasswordemail.do", method = RequestMethod.GET)
  public ModelAndView resetPasswordEmail(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
    
    String useremail = req.getParameter("useremail");
    Map<Object, Object> reqMap = new HashMap<Object, Object>();    
    reqMap.put(REQUEST_TYPES.SUB_REQ_RESET_PASSWORD, "1");
    reqMap.put(USER.EMAIL, useremail);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
    		.handleRequest(reqMap, REQUEST_TYPES.RESET_PASSWORD);
    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }
}