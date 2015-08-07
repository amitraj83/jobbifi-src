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
public class TransactionHistoryController extends BaseController {
	
	@RequestMapping(value="/finance.do", method=RequestMethod.GET)
	  public String getTransactionHistory(){
		  return "finance";
	  }

  @RequestMapping(value = "/transhistory.do", method = RequestMethod.POST)
  public ModelAndView getTransactionHistory(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    String user = getLoginUser();

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(USER.USERNAME, user);
    reqMap.put(VARIABLES.FROM_DATE, req.getParameter("fromDate"));
    reqMap.put(VARIABLES.TO_DATE, req.getParameter("toDate"));
    reqMap.put("pagenum", req.getParameter("pagenum"));
    Map<String, Object> resmap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.TRANSACTION_HISTORY);
    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resmap));
  }

}
