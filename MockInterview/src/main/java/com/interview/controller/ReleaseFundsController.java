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
import com.interview.framework.VARIABLES;
import com.interview.services.Services;

@Controller
public class ReleaseFundsController {


  @RequestMapping(value = "/releasefunds.do", method = RequestMethod.POST)
  public ModelAndView escrowDeposit(ModelMap model, HttpServletRequest req, HttpServletResponse res) {


    String iid = req.getParameter("iid");
    String interviewer = req.getParameter("interviewer");
    String amount = req.getParameter("amount");
    String escid = req.getParameter("escid");
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("iid", iid);
    reqMap.put(VARIABLES.AMOUNT, amount);
    reqMap.put(DATASTORES.ESCROW.OBJECT_ID, escid);

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.RELEASE_FUNDS);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }


}
