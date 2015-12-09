package com.interview.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Job;
import com.interview.services.Services;

@Controller
public class EditJobController extends BaseController{

	@RequestMapping(value="/editjob.do", method=RequestMethod.GET)
	  public String getJob(@RequestParam("jid") String jid, Model map){
		Map<Object, Object> req = new HashMap<Object, Object>();
		req = new HashMap<Object, Object>();
		req.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.GET_JOB);
		req.put(DATASTORES.JOB.ID, jid);
		Map<String, Object> res = Services.getInstance().getRequestHandlerService().handleRequest(req, REQUEST_TYPES.JOB); 
		Job job = (Job)res.get("job");
		List<String> skills = (List<String>) job.getSkills();
		  String skillsString ="";
		  for (String string : skills) {
			  skillsString+=string+",";
		  }
		  skillsString=skillsString.substring(0, skillsString.length()-1);
		map.addAttribute("job", res.get("job"));
		map.addAttribute("jobid", jid);
		map.addAttribute("skills", skillsString);
		return "editjob";
	 }

  @RequestMapping(value = "/editjob.do", method = RequestMethod.POST)
  public ModelAndView postJob(ModelMap model, HttpServletRequest req) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    Job job = new Job();
    job.setId(req.getParameter("jid"));
    job.setTitle(req.getParameter(VARIABLES.POST_JOB.TITLE));
    job.setDescription(req.getParameter(VARIABLES.POST_JOB.DESCRIPTION));
    job.setSalary(req.getParameter(VARIABLES.POST_JOB.SALARY));
    job.setCompanyName(req.getParameter(VARIABLES.POST_JOB.COMPANY_NAME));
    job.setCompanyDescription(req.getParameter(VARIABLES.POST_JOB.COMPANY_DESCRIPTION));
    job.setCompanyVideo(req.getParameter(VARIABLES.POST_JOB.COMPANY_VIDEO));
    job.setApplyUrl(req.getParameter(VARIABLES.POST_JOB.APPLY_URL));
    job.setIndustry(req.getParameter(VARIABLES.POST_JOB.INDUSTRY));
    job.setExperience(req.getParameter(VARIABLES.POST_JOB.EXPERIENCE));
    job.setLocation(req.getParameter(VARIABLES.POST_JOB.LOCATION));
    String[] skills =
        null != req.getParameter(VARIABLES.POST_JOB.SKILLS) ? req.getParameter(
            VARIABLES.POST_JOB.SKILLS).split(",") : new String[] {};
    List<String> skillList = new ArrayList<String>();
    for (int i = 0; i < skills.length; i++) {
      skillList.add(skills[i]);
    }
    job.setSkills(skillList);
    job.setDt(new Date().getTime());
    String user =getLoginUser();
    job.setInterviewer(user);
    if (req.getParameter(DATASTORES.JOB.FILE) != null) {
      job.setFile(req.getParameter(DATASTORES.JOB.FILE));
    }

    reqMap.put("job", job);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.UPDATE_JOB);
    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService().handleRequest(reqMap, REQUEST_TYPES.JOB);
    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }
}
