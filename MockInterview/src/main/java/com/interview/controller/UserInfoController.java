package com.interview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.rmi.common.UserSession;
import com.interview.services.Services;


@Controller
public class UserInfoController extends BaseController {


  @RequestMapping(value = "/getuserinfo.do", method = RequestMethod.POST)
  public ModelAndView processRequest(ModelMap model, HttpServletRequest req, HttpServletResponse res) {


    UserSession us =
        Services.getInstance().getUserSessionManager().getUserSession(req.getSession().getId());
    // Map<String, String> responseMap =
    // Services.getInstance().getRequestHandlerService().handleRequest(req.getParameter("regDetails"),
    // REQUEST_TYPES.INTERVIEWER_REGISTRATION);
    return new ModelAndView("userinfo", "message", us.getJSON().toString());
  }
}
