package com.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController extends BaseController {

  @RequestMapping(value = "/admin.do", method = RequestMethod.GET)
  public String getadmin() {
    return "admin";
  }
}
