package com.interview.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.services.Services;

@Controller
public class ContactListController extends BaseController {
  @RequestMapping(value = "/addcontactlist.do", method = RequestMethod.POST)
  public ModelAndView addContactList(HttpServletRequest req) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("from", getLoginUser());
    reqMap.put("to", req.getParameter("to"));
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.SAVE_CONTACT_LIST);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.CONTACT_LIST);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));

  }

  @RequestMapping(value = "/getcontactlist.do", method = RequestMethod.GET)
  public ModelAndView getContactList(HttpServletRequest req) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("user", getLoginUser());
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.CONTACT_LIST);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));

  }
}
