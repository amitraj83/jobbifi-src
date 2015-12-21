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
import com.interview.services.Services;

@Controller
public class CalendarController extends BaseController {

  @RequestMapping(value = "/schedulecalendar.do", method = RequestMethod.GET)
  public ModelAndView datafeed(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

    String title = req.getParameter("title");
    long start = new Long(req.getParameter("start"));
    long end = new Long(req.getParameter("end"));
    int recurdays = new Integer(req.getParameter("recur"));

    String userid = getLoginUser();


    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.CALENDAR_EVENT.TITLE, title);
    reqMap.put(DATASTORES.CALENDAR_EVENT.START_TIME, start);
    reqMap.put(DATASTORES.CALENDAR_EVENT.END_TIME, end);
    reqMap.put(DATASTORES.CALENDAR_EVENT.RECUR_DAYS, recurdays);
    reqMap.put(DATASTORES.CALENDAR_EVENT.USERID, userid);
    reqMap.put(DATASTORES.CALENDAR_EVENT.EVENT_TYPE, req.getParameter("eventtype"));
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.CALENDAR_SUB_REQ.SAVE_EVENT);


    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.CALENDAR_EVENTS);

    System.out.println("Title:" + title);
    System.out.println("Start:" + new Date(start));
    System.out.println("End:" + new Date(end));
    System.out.println("REcuring days : " + recurdays);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }



  @RequestMapping(value = "/getcalendarevents.do", method = RequestMethod.GET)
  public ModelAndView getevents(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

    long start = new Long(req.getParameter("start"));
    long end = new Long(req.getParameter("end"));

    String userid = getLoginUser();

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.CALENDAR_EVENT.START_TIME, start);
    reqMap.put(DATASTORES.CALENDAR_EVENT.END_TIME, end);
    reqMap.put(DATASTORES.CALENDAR_EVENT.USERID, userid);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.CALENDAR_SUB_REQ.GET_EVENTS);

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.CALENDAR_EVENTS);


    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }


  @RequestMapping(value = "/deletecalendarevent.do", method = RequestMethod.GET)
  public ModelAndView deleteEvent(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

    String eventId = req.getParameter("eventid");
    String user = req.getParameter("user");
    String userid = getLoginUser();
    Map<String, Object> resMap = new HashMap<String, Object>();
    if (user.equals(userid)) {
      Map<Object, Object> reqMap = new HashMap<Object, Object>();
      reqMap.put(DATASTORES.CALENDAR_EVENT.ID, eventId);
      reqMap.put(DATASTORES.CALENDAR_EVENT.USERID, userid);
      reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.CALENDAR_SUB_REQ.DELETE_EVENT);
      resMap = Services.getInstance().getRequestHandlerService().handleRequest(reqMap,
          REQUEST_TYPES.CALENDAR_EVENTS);
    } else {
      resMap.put("status", "2");
    }
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }


  @RequestMapping(value = "/updatecalendar.do", method = RequestMethod.GET)
  public ModelAndView updateCalendarEvent(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {
    String userid = getLoginUser();

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.CALENDAR_EVENT.ID, req.getParameter("eventid"));
    reqMap.put(DATASTORES.CALENDAR_EVENT.TITLE, req.getParameter("title"));
    reqMap.put(DATASTORES.CALENDAR_EVENT.START_TIME, req.getParameter("start"));
    reqMap.put(DATASTORES.CALENDAR_EVENT.END_TIME, req.getParameter("end"));
    reqMap.put(DATASTORES.CALENDAR_EVENT.RECUR_DAYS, req.getParameter("recur"));
    reqMap.put(DATASTORES.CALENDAR_EVENT.EVENT_TYPE, req.getParameter("eventtype"));
    reqMap.put(DATASTORES.CALENDAR_EVENT.USERID, userid);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.CALENDAR_SUB_REQ.UPDATE_EVENT);


    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.CALENDAR_EVENTS);


    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }



}
