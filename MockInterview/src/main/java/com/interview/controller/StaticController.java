package com.interview.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StaticController {
	
	 @RequestMapping(value="/aboutus.do", method=RequestMethod.GET)
	  public String getAboutUS(){
		  return "aboutus";
	  }
	 
	 @RequestMapping(value="/ourteam.do", method=RequestMethod.GET)
	  public String getOurTeam(){
		  return "ourteam";
	  }
	 
	 @RequestMapping(value="/faq.do", method=RequestMethod.GET)
	  public String getFaq(){
		  return "faq";
	  }
	 
	 @RequestMapping(value="/support.do", method=RequestMethod.GET)
	  public String getSupport(){
		  return "support";
	  }
	 
	 @RequestMapping(value="/contactus.do", method=RequestMethod.GET)
	  public String getContactUs(){
		  return "contactus";
	  }
	 
	 @RequestMapping(value="/searchcandidate.do", method=RequestMethod.GET)
	  public String getSearchCandidate(){
		  return "searchCandidate";
	  }
	 
	 @RequestMapping(value="/providecareerservices.do", method=RequestMethod.GET)
	  public String getProvideCareerServices(){
		  return "provideCareerServices";
	  }
	 
	 @RequestMapping(value="/earnmoney.do", method=RequestMethod.GET)
	  public String getEarnMoney(){
		  return "earnMoney";
	  }
	 
	 @RequestMapping(value="/becomemember.do", method=RequestMethod.GET)
	  public String getBecomeMember(){
		  return "becomeMember";
	  }
	 
	 @RequestMapping(value="/creerservices.do", method=RequestMethod.GET)
	  public String getCareerServices(){
		  return "careerServices";
	  }
	 
	 @RequestMapping(value="/readingmaterial.do", method=RequestMethod.GET)
	  public String getReadingMaterial(){
		  return "readingMaterial";
	  }
	
}
