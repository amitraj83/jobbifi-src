package com.interview.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.services.Services;


@Controller
public class RateController extends BaseController {

  @RequestMapping(value = "/rate.do", method = RequestMethod.GET)
  public ModelAndView rateUser(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("1", req.getParameter("1"));
    reqMap.put("2", req.getParameter("2"));
    reqMap.put("3", req.getParameter("3"));
    reqMap.put("4", req.getParameter("4"));
    reqMap.put("msg", req.getParameter("msg"));
    reqMap.put("iid", req.getParameter("iid"));
    reqMap.put("rateFor", req.getParameter("rateFor"));    
    reqMap.put("ratedBy", getLoginUser());
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.RATING_SUB_REQ.RATE_USER);
    
    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.RATING);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/ratingallowed.do", method = RequestMethod.GET)
  public ModelAndView checkingRatingAllowed(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    String user = getLoginUser();
    String iid = req.getParameter("iid");
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.RATING.RATEDBY, user);
    reqMap.put(DATASTORES.RATING.IID, iid);
    reqMap.put("rateFor", req.getParameter("rateFor"));

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.CHECK_RATING_ALLOWED);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }
  
  
  	@RequestMapping(value="/userrating.do", method=RequestMethod.GET)
  	public ModelAndView getUserRating(@RequestParam("username") String username){
  	
  		Map<Object, Object> reqMap = new HashMap<Object, Object>();
  	    reqMap.put(USER.USERNAME, username);  	    
  	    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.RATING_SUB_REQ.GET_USER_RATING);  	    
  	    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
  	            	.handleRequest(reqMap, REQUEST_TYPES.RATING);
  	    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
  	        .getJSONStringOfMap(resMap));
  	}
  
}
