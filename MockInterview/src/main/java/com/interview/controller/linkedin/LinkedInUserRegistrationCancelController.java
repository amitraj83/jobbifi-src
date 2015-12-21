package com.interview.controller.linkedin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LinkedInUserRegistrationCancelController {

  @RequestMapping(value = "/linkedinuserregistercancel.do", method = RequestMethod.POST)
  public ModelAndView linkedinRegistrationCancel(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {
    req.getSession().invalidate();
    return new ModelAndView("response", "message", "DONE");
  }
}
