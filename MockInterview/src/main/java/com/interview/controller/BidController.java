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
import com.interview.util.Util;

@Controller
public class BidController extends BaseController {

  @RequestMapping(value = "/makebid.do", method = RequestMethod.GET)
  public ModelAndView makeBid(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

    // TODO : allowed bid only if open or reposted

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(VARIABLES.Bid.BIDDER, getLoginUser());
    reqMap.put(VARIABLES.Bid.INTERVIEW_ID, req.getParameter(VARIABLES.Bid.INTERVIEW_ID));
    reqMap.put(VARIABLES.Bid.MSG, req.getParameter(VARIABLES.Bid.MSG));
    reqMap.put(VARIABLES.Bid.PRICE, req.getParameter(VARIABLES.Bid.PRICE));
    reqMap.put(VARIABLES.Bid.BID_FID, req.getParameter("bidfid"));
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.BID_SUB_REQ.MAKE_BID);
    reqMap.put("baseURL", Util.getbBaseURLpath(req));

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.BID_REQ);
    String bidId = resMap.get(VARIABLES.Bid.BID_ID).toString();

    if (!req.getParameter("bidfid").equals("")) {

      Map<Object, Object> fReqMap = new HashMap<Object, Object>();
      fReqMap.put(VARIABLES.Bid.BID_FID, req.getParameter("bidfid"));
      fReqMap.put(VARIABLES.Bid.BID_ID, bidId);
      Services.getInstance().getRequestHandlerService().handleRequest(fReqMap,
          REQUEST_TYPES.FILESERVER_UPDATE_BID_FILE);
    }

    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }
}
