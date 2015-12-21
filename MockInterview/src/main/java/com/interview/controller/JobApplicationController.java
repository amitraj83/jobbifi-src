package com.interview.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.JobApplication;
import com.interview.services.Services;

@Controller
public class JobApplicationController extends BaseController {

  @RequestMapping(value = "/jobapplication/save.do")
  public ModelAndView saveJobApplication(HttpServletRequest request) {

    JobApplication jobApplication = new JobApplication();
    jobApplication.setCoverLetter(request.getParameter("coverLetter"));
    jobApplication.setCvFileId(request.getParameter("cvFileId"));
    jobApplication.setJobId(request.getParameter("jobId"));
    jobApplication.setApplicantId(getLoginUser());
    jobApplication.setDt(new Date().getTime());
    jobApplication.setStatus(DATASTORES.JOB_APPLICATION.Status.APPLIED);

    Map<Object, Object> req = new HashMap<Object, Object>();
    req.put("jobApplication", jobApplication);
    req.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.JOB_APPLICATION_SUB_REQ.SAVE_APPLICATION);

    Map<String, Object> res = Services.getInstance().getRequestHandlerService().handleRequest(req,
        REQUEST_TYPES.JOB_APPLICATION_REQ);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(res));
  }

  @RequestMapping(value = "/jobapplications.do", method = RequestMethod.GET)
  public String getJobApplications(HttpServletRequest request) {
    return "jobapplications";
  }

  @RequestMapping(value = "/jobapplication/updatestatus.do", method = RequestMethod.POST)
  public ModelAndView updateApplicationStatus(@RequestParam("id") String id,
      @RequestParam("status") String status) {

    Map<Object, Object> req = new HashMap<Object, Object>();
    req.put(DATASTORES.JOB_APPLICATION.ID, id);
    req.put(DATASTORES.JOB_APPLICATION.STATUS, status);
    req.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.JOB_APPLICATION_SUB_REQ.UPDATE_APPLICATION_STATUS);
    Map<String, Object> res = Services.getInstance().getRequestHandlerService().handleRequest(req,
        REQUEST_TYPES.JOB_APPLICATION_REQ);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(res));
  }

}
