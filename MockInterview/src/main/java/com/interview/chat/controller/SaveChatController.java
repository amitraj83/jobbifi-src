package com.interview.chat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.CHAT;
import com.interview.framework.REQUEST_TYPES;
import com.interview.services.Services;

@Controller
public class SaveChatController {

	  @RequestMapping(value = "/savechatmessage.do", method = RequestMethod.GET)
	  public ModelAndView saveChatHistory(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
	
	    String from = req.getParameter("from");
	    String to = req.getParameter("to");
	    String time = req.getParameter("time");
	    String message = req.getParameter("message");
	    String type = req.getParameter("type");    
	
	    Map<Object, Object> reqMap = new HashMap<Object, Object>();
	    reqMap.put(CHAT.RID, from + to);
	    reqMap.put(CHAT.FROM, from);
	    reqMap.put(CHAT.MESSAGE, message);
	    reqMap.put(CHAT.TIME, time);
	    reqMap.put(CHAT.TO, to);
	    reqMap.put(CHAT.TYPE, type);
	    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.SAVE_CHAT_MESSAGE);
	    
	    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
	        .handleRequest(reqMap, REQUEST_TYPES.SAVE_CHAT_MESSAGES);    
	    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
	            .getJSONStringOfMap(resMap));
	  }	  
	  
	  @RequestMapping(value = "/savequickchat.do", method = RequestMethod.GET)
	  public ModelAndView saveQuickChat(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
	
	    String from = req.getParameter("from");
	    String to = req.getParameter("to");
	    String time = req.getParameter("time");
	    String message = req.getParameter("message");
	    String type = req.getParameter("type");
	
	    Map<Object, Object> reqMap = new HashMap<Object, Object>();
	    reqMap.put(CHAT.RID, from + to);
	    reqMap.put(CHAT.FROM, from);
	    reqMap.put(CHAT.MESSAGE, message);
	    reqMap.put(CHAT.TIME, time);
	    reqMap.put(CHAT.TO, to);
	    reqMap.put(CHAT.TYPE, type);
	    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.SAVE_QUICK_CHAT_MESSAGE);
	    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
	        .handleRequest(reqMap, REQUEST_TYPES.SAVE_CHAT_MESSAGES); 
	    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
	                .getJSONStringOfMap(resMap));  
	  }  
}