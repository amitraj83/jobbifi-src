package com.interview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LiveConnectionController {



  @RequestMapping(value = "/live.do", method = RequestMethod.GET)
  public ModelAndView processRequest(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {
    return new ModelAndView("live", "message", null);
  }
}
