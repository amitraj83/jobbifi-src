package com.interview.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Job;
import com.interview.services.Services;

@Controller
public class PostJobController extends BaseController{

	@RequestMapping(value="/postjob.do", method=RequestMethod.GET)
	  public String getdepositfunds(){
		  return "postjob";
	 }

  @RequestMapping(value = "/postjob.do", method = RequestMethod.POST)
  public ModelAndView postJob(ModelMap model, HttpServletRequest req) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    Job job = new Job();
    job.setTitle(req.getParameter(VARIABLES.POST_JOB.TITLE));
    job.setDescription(req.getParameter(VARIABLES.POST_JOB.DESCRIPTION));
    job.setSalary(req.getParameter(VARIABLES.POST_JOB.SALARY));
    job.setCompanyName(req.getParameter(VARIABLES.POST_JOB.COMPANY_NAME));
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
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.POST_JOB);
    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService().handleRequest(reqMap, REQUEST_TYPES.JOB);
    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/getJobsOffered.do", method = RequestMethod.GET)
  public ModelAndView getJobsOffered(ModelMap model, HttpServletRequest req) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    String user = getLoginUser();
    reqMap.put(DATASTORES.JOB.INTERVIEWER, user);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.GET_JOBS_OFFERED);
    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService().handleRequest(reqMap, REQUEST_TYPES.JOB);
    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }

}
