package com.interview.controller;

import java.util.Date;
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
public class DisputeController extends BaseController {

  @RequestMapping(value = "/createdispute.do", method = RequestMethod.POST)
  public ModelAndView createDispute(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    String iid = req.getParameter("iid");
    String fid = req.getParameter("fileid");
    String msg = req.getParameter("msg");

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(USER.USERNAME, getLoginUser());
    reqMap.put(DATASTORES.DISPUTE_MESSAGE.FID, fid);
    reqMap.put(DATASTORES.DISPUTE.IID, iid);
    reqMap.put(DATASTORES.DISPUTE_MESSAGE.MESSAGE, msg);
    reqMap.put(DATASTORES.DISPUTE.STATUS, DATASTORES.DISPUTE.STATUS_TYPE.OPEN);
    reqMap.put(DATASTORES.DISPUTE.DISPUTE_AMOUNT, req.getParameter("amount"));
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.CREATE_DISPUTE);

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.DISPUTE);

    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));

  }

  @RequestMapping(value = "/retrievedispute.do", method = RequestMethod.GET)
  public ModelAndView retrieveDispute(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    String did = req.getParameter("did");

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.DISPUTE.ID, did);
    String user = getLoginUser();
    reqMap.put(USER.USERNAME, user);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.RETRIEVE_DISPUTE);

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.DISPUTE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));


  }

  @RequestMapping(value = "/getDisputeList.do", method = RequestMethod.GET)
  public ModelAndView getDisputeList() {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(USER.USERNAME, getLoginUser());
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.GET_DISPUTE_LIST);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.DISPUTE);
    resMap.put("userRole", getUserRole());
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/senddisputemessage.do", method = RequestMethod.GET)
  public ModelAndView sendDisputeMessage(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    String did = req.getParameter("did");
    String fid = req.getParameter("fid");
    String msg = req.getParameter("msg");
    String user = getLoginUser();

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.DISPUTE_MESSAGE.DISPUTEID, did);
    reqMap.put(DATASTORES.DISPUTE_MESSAGE.FID, fid);
    reqMap.put(DATASTORES.DISPUTE_MESSAGE.MESSAGE, msg);
    reqMap.put(DATASTORES.DISPUTE_MESSAGE.MESSAGEBY, user);
    reqMap.put(DATASTORES.DISPUTE_MESSAGE.TIME, new Date().getTime());
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.SEND_DISPUTE_MSG);

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.DISPUTE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));


  }



  @RequestMapping(value = "/interviewerclosingdispute.do", method = RequestMethod.GET)
  public ModelAndView interviewerclosingdispute(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    String did = req.getParameter("did");
    String user = getLoginUser();

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.DISPUTE.ID, did);
    reqMap.put(DATASTORES.DISPUTE.CLOSEDBY, user);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.DISPUTE_CLOSED_BY_INTERVIEWER);

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.DISPUTE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));


  }

  @RequestMapping(value = "/intervieweeclosingdispute.do", method = RequestMethod.GET)
  public ModelAndView intervieweeclosingdispute(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    String did = req.getParameter("did");
    String user = getLoginUser();

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.DISPUTE.ID, did);
    reqMap.put(DATASTORES.DISPUTE.CLOSEDBY, user);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.DISPUTE_CLOSED_BY_INTERVIEWEE);

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.DISPUTE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));


  }

}
