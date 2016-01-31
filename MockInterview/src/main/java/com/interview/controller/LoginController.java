package com.interview.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.services.Services;

@Controller
public class LoginController {

	@Autowired
	private Properties myProps;


	



	@RequestMapping(value = "/isuseraccountactive.do", method = RequestMethod.POST)
	public ModelAndView checkuserActive(HttpServletRequest req, HttpServletResponse res) {



		HashMap<Object, Object> reqMap = new HashMap<Object, Object>();
		reqMap.put(USER.EMAIL, String.valueOf(req.getParameter("emailid")));

		Map<String, Object> result = Services.getInstance().getRequestHandlerService()
				.handleRequest(reqMap, REQUEST_TYPES.CHECK_USERACCOUNT_ACTIVE);

		return new ModelAndView("response", "message",
				Services.getInstance().getJSONUtilityService().getJSONStringOfMap(result));
	}

	@RequestMapping(value = "/welcome.do", method = RequestMethod.GET)
	public ModelAndView printWelcome(ModelMap model, HttpServletRequest req,
			HttpServletResponse res) {

		ServletRequestAttributes attr =
				(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.setMaxInactiveInterval(365 * 60 * 60);

		Map<String, Object> resmap =
				Services.getInstance().getPrepareLoginDataService().prepareLoginData(req);

		return new ModelAndView("response", "message",
				Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resmap));
	}

	@RequestMapping(value = "/isloggedin.do", method = RequestMethod.GET)
	public ModelAndView checkLoggedIn(ModelMap model, HttpServletRequest req,
			HttpServletResponse res) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("response", "message", "LI");
		} else {
			return new ModelAndView("response", "message", "NL");
		}
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
		return "login";
	}

	@RequestMapping(value = "/loginsuccess.do", method = RequestMethod.GET)
	public ModelAndView loginsuccess(ModelMap model, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("RESULT", "SUCCESS");
		Collection<GrantedAuthority> gAuthorities = ((Authentication) principal).getAuthorities();
		for (GrantedAuthority g : gAuthorities) {
			if (g.getAuthority().equals("ROLE_ADMIN")) {
				resMap.put("REDIRECT", "admin.do");
			}
		}
		return new ModelAndView("response", "message",
				Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
	}

	@RequestMapping(value = "/loginfailed.do", method = RequestMethod.GET)
	public ModelAndView loginerror(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("RESULT", "ERROR");
		return new ModelAndView("response", "message",
				Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

		ServletRequestAttributes attr =
				(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		res.addCookie(new Cookie("sid", "null"));
		session.invalidate();
		return "redirect:" + myProps.getProperty("homeurl") + "/";

	}
}
