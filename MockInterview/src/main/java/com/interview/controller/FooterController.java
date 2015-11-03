package com.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FooterController {
	
	@RequestMapping(value="/termsofservice.do", method=RequestMethod.GET)
	public String getTermsOfService(){
		return "termsofservice";
	}	
	
	@RequestMapping(value="/privacy.do", method=RequestMethod.GET)
	public String getPrivacy(){
		return "privacy";
	}
	
	@RequestMapping(value="/about.do", method=RequestMethod.GET)
	public String getAbout(){
		return "about";
	}
	
	@RequestMapping(value="/faqfooter.do", method=RequestMethod.GET)
	public String getFaqFooter(){
		return "faqfooter";
	}
	
	@RequestMapping(value="/career.do", method=RequestMethod.GET)
	public String getCareer(){
		return "career";
	}
	
	@RequestMapping(value="/contact.do", method=RequestMethod.GET)
	public String getContact(){
		return "contact";
	}
}