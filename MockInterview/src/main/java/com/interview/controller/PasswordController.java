package com.interview.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.services.Services;

@Controller
public class PasswordController extends BaseController {
	Logger loger = Logger.getLogger(PasswordController.class);
	@RequestMapping(value="/changepassword.do", method=RequestMethod.GET) 
	public String changePassword(){
		return "changepassword";
	}
	
	@RequestMapping(value="/changepassword.do", method=RequestMethod.POST) 
	public ModelAndView processChangePassword(@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword){
				
		 Map<Object, Object> request = new HashMap<Object, Object>();
		 request.put(USER.USERNAME, getLoginUser());
		 request.put(USER.PASSWORD, currentPassword);
		 request.put(USER.NEW_PASSWORD, newPassword);
		 loger.info("CURRENTPASSWORD"+currentPassword);
		 loger.info("NEWPASSWORD"+newPassword);
		 Map<String, Object> responseMap = Services.getInstance().getRequestHandlerService()
				 .handleRequest(request, REQUEST_TYPES.PASSWORD);
		 return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
			        .getJSONStringOfMap(responseMap));
	}
}
