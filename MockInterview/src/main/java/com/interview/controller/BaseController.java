package com.interview.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import com.interview.beans.MockUser ;

public abstract class BaseController {
	
	private Logger logger = Logger.getLogger(BaseController.class);
	
	/**
	 *  Get Current login user name. 
	 * */
	public String getLoginUser(){		
		if(!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getName())){	
			logger.info(" Principal : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			MockUser mockUser = (MockUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(null != mockUser){
				return mockUser.getUsername();
			}
		}
		return null;
	}
	
	public String getUserRole(){
		if(!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getName())){	
			logger.info(" Principal : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			MockUser mockUser = (MockUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(null != mockUser){
				return mockUser.getUserRole();
			}
		}
		return null;
	}
	public boolean isInterviwer(){
		if(!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getName())){
			MockUser mockUser = (MockUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
			if(null != mockUser && mockUser.getUserRole().equals("INTERVIEWER")){
				return true;
			}
		}
		return false;
	}
}