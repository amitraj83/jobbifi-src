package com.interview.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Message;
import com.interview.services.Services;

@Controller
public class MessageController extends BaseController {

  @RequestMapping(value = "/message.do", method = RequestMethod.GET)
  public String getmessage() {
    return "message";
  }

  @RequestMapping(value = "/newmessage.do", method = RequestMethod.GET)
  public String getNewmessage() {
    return "newmessage";
  }

  @RequestMapping(value = "/jobmessage.do", method = RequestMethod.POST)
  public ModelAndView jobMessage(HttpServletRequest req) {
    try {
      Map<Object, Object> reqMap = new HashMap<Object, Object>();
      Message msg = new Message();
      msg.setFrom(getLoginUser());
      msg.setTo(req.getParameter("to"));
      msg.setRefId(req.getParameter("jobid"));
      msg.setMessage(req.getParameter("message"));
      msg.setCreationDate(new Date().getTime());
      msg.setLastReplyToDate(new Date().getTime());
      msg.setRefEntity(VARIABLES.MESSAGE.REFENTITY);
      msg.setStatus(DATASTORES.MESSAGE.MESSAGE_STATUS.UNREAD);
      msg.setParentMessageId(req.getParameter("parentMessageId"));
      msg.setTitle(req.getParameter("jobtitle"));
      msg.setType(req.getParameter("type"));

      reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.MESSAGE_SUB_REQ.JOB_MESSAGE);
      reqMap.put("Message", msg);
      Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
          .handleRequest(reqMap, REQUEST_TYPES.MESSAGE);
      return new ModelAndView("response", "message",
          Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));

    } catch (Exception e) {
      e.printStackTrace();
      return new ModelAndView("response", "message", "error");
    }
  }

  @RequestMapping(value = "/getjobmessage.do", method = RequestMethod.GET)
  public ModelAndView getJobMessage(HttpServletRequest req) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    Message msg = new Message();
    msg.setId(req.getParameter("iid"));
    msg.setFrom(getLoginUser());
    msg.setTo(req.getParameter("messageto"));
    msg.setRefEntity(VARIABLES.MESSAGE.REFENTITY);
    reqMap.put("Message", msg);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.MESSAGE_SUB_REQ.GET_JOB_MESSAGE);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.MESSAGE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/getmessage.do", method = RequestMethod.GET)
  public ModelAndView getMessage(HttpServletRequest req) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    Message msg = new Message();
    msg.setFrom(getLoginUser());
    msg.setType(DATASTORES.MESSAGE.MESSAGE_TYPE.ORIGINAL);
    reqMap.put("pagenum", req.getParameter("pagenum"));
    reqMap.put("Message", msg);

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.MESSAGE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/getmessagebetween.do", method = RequestMethod.GET)
  public ModelAndView getMessageBetween(HttpServletRequest req) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("user1", req.getParameter("user"));
    reqMap.put("user2", getLoginUser());
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.MESSAGE_SUB_REQ.GET_MESSAGE_BETWEEN);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.MESSAGE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/getsubmessage.do", method = RequestMethod.GET)
  public ModelAndView getSubMessage(HttpServletRequest req) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    Message msg = new Message();
    msg.setParentMessageId(req.getParameter("parentMessageId"));
    reqMap.put("Message", msg);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.MESSAGE_SUB_REQ.GET_SUB_MESSAGE);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.MESSAGE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/sendmessage.do", method = RequestMethod.GET)
  public ModelAndView getSendMessage(HttpServletRequest req) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    Message msg = new Message();
    if (getLoginUser().equals(req.getParameter("to"))) {
      msg.setFrom(getLoginUser());
      msg.setTo(req.getParameter("from"));
    } else {
      msg.setFrom(getLoginUser());
      msg.setTo(req.getParameter("to"));
    }

    msg.setRefId(req.getParameter("jobid"));
    msg.setMessage(req.getParameter("message"));
    msg.setCreationDate(new Date().getTime());
    msg.setLastReplyToDate(new Date().getTime());
    msg.setRefEntity(req.getParameter("refentity"));
    msg.setStatus(DATASTORES.MESSAGE.MESSAGE_STATUS.UNREAD);
    msg.setParentMessageId(req.getParameter("parentMessageId"));
    msg.setTitle(req.getParameter("jobtitle"));
    msg.setType(req.getParameter("type"));

    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.MESSAGE_SUB_REQ.JOB_MESSAGE);
    reqMap.put("Message", msg);

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.MESSAGE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/sendnewmessage.do", method = RequestMethod.POST)
  public ModelAndView getSendNewMessage(HttpServletRequest req) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    Message msg = new Message();

    msg.setFrom(getLoginUser());
    msg.setTo(req.getParameter("to"));
    msg.setMessage(req.getParameter("message"));
    msg.setCreationDate(new Date().getTime());
    msg.setLastReplyToDate(new Date().getTime());
    msg.setStatus(DATASTORES.MESSAGE.MESSAGE_STATUS.UNREAD);
    msg.setParentMessageId(req.getParameter("parentMessageId"));

    msg.setRefId(req.getParameter("jobid"));
    msg.setRefEntity(req.getParameter("refentity"));
    msg.setTitle(req.getParameter("jobtitle"));

    msg.setType(DATASTORES.MESSAGE.MESSAGE_TYPE.ORIGINAL);

    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.MESSAGE_SUB_REQ.JOB_MESSAGE);
    reqMap.put("Message", msg);

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.MESSAGE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/getnewmessagecount.do", method = RequestMethod.GET)
  public ModelAndView getNewMessageCount() {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(USER.USERNAME, getLoginUser());
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.MESSAGE_SUB_REQ.GET_NEW_MESSAGE_COUNT);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.MESSAGE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/changestatus.do", method = RequestMethod.GET)
  public ModelAndView changeStatus(HttpServletRequest req) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(VARIABLES.MESSAGE.ID, req.getParameter("id"));
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.MESSAGE_SUB_REQ.CHANGE_MESSAGE_STATUS);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.MESSAGE);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }
}
