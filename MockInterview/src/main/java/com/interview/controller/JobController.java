package com.interview.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.JobApplication;
import com.interview.framework.pojo.UploadedFile;
import com.interview.services.Services;

/**
 *  This class handles the JOB specific public request.
 * */
@Controller
public class JobController extends BaseController{
	
	@RequestMapping(value="/jobs.do", method=RequestMethod.GET)
	public String getjob(){
		return "job";
	}
	
	@RequestMapping(value="/jobdetail.do", method=RequestMethod.GET)
	public String getJob(@RequestParam("jid") String jid, Model map){
		
		Map<Object, Object> req = new HashMap<Object, Object>();
		String loginUser = getLoginUser();
		JobApplication jobApplication = null;
		UploadedFile uploadedFile = null;
		if(null != loginUser){
			req.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.JOB_APPLICATION_SUB_REQ.GET_APPLICATION_BY_JOB_AND_USER);
			req.put(USER.USERNAME, loginUser);
			req.put("jobId", jid);
			Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
					.handleRequest(req, REQUEST_TYPES.JOB_APPLICATION_REQ);
			if(resMap.get("jobApplication") != null) {
				jobApplication = (JobApplication) resMap.get("jobApplication");
				uploadedFile = (UploadedFile)resMap.get("uploadedFile");
			}
		}
		
		req = new HashMap<Object, Object>();
		req.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.GET_JOB);
		req.put(DATASTORES.JOB.ID, jid);
		Map<String, Object> res = Services.getInstance().getRequestHandlerService().handleRequest(req, REQUEST_TYPES.JOB); 
		map.addAttribute("job", res.get("job"));
		map.addAttribute("jobid", jid);
		map.addAttribute("rating", res.get("rating"));
		map.addAttribute("username", getLoginUser());
		map.addAttribute("profilepic", res.get("profilepic"));
		map.addAttribute("jobApplication", jobApplication);
		map.addAttribute("uploadedFile", uploadedFile);
		return "jobdetail";
	}
}