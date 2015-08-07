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

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.services.Services;


@Controller
public class EscrowDepositController extends BaseController{

  @RequestMapping(value = "/escrowdeposit.do", method = RequestMethod.POST)
  public ModelAndView escrowDeposit(ModelMap model, HttpServletRequest req, HttpServletResponse res) {


    String iid = req.getParameter("iid");
    String interviewer = req.getParameter("interviewer");
    String amount = req.getParameter("amount");

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("_id", iid);
    reqMap.put(DATASTORES.INTERVIEW.ESCROW_BALANCE, amount);
    reqMap.put(REQUEST_TYPES.SUB_REQ, "DEPOSIT");
    String username = getLoginUser();
    reqMap.put(USER.USERNAME, username);

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.ESCROW_DEPOSIT);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/escrowlist.do", method = RequestMethod.POST)
  public ModelAndView escrowlist(ModelMap model, HttpServletRequest req, HttpServletResponse res) {


    String iid = req.getParameter("iid");

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("_id", iid);
    reqMap.put(REQUEST_TYPES.SUB_REQ, "ESCROWLIST");
    String username = getLoginUser();
    reqMap.put(USER.USERNAME, username);

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.ESCROW_DEPOSIT);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }



}
