package com.interview.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.services.Services;
import com.interview.util.Util;

@Controller
public class PostInterviewController extends BaseController {
	
  private Logger logger = Logger.getLogger(PostInterviewController.class);

  @RequestMapping(value="/publishinterview.do", method=RequestMethod.GET)
  public String getPublishInterview(){
	  return "publishinterview";
  }
	
  @RequestMapping(value={"/myinterview.do"}, method=RequestMethod.GET)
  public String getAllInterview(@RequestParam(value="status", required=false, defaultValue="ALL") String status, Model map){
	  
	  logger.info("Status : " + status);
	  map.addAttribute("status", status);
	  
	  if(isInterviwer()){
		  return "myInterviewInterviewer";
	  } else {  
		  return "myInterview";
	  }
  }
  
  @RequestMapping(value="/dispute.do", method=RequestMethod.GET)
  public String getDispute(Model map){
	  Map<String, Object> resMap=new HashMap<String, Object>() ;
	  resMap.put("userRole", getUserRole());
	  resMap.put("loginuser", getLoginUser());
	  map.addAllAttributes(resMap);
	  return "dispute";
  }
	
  @RequestMapping(value="/interviewdetail.do", method=RequestMethod.GET)
  public String getEscrow(HttpServletRequest req,Model map){
	  
	  Map<Object, Object> reqMap = new HashMap<Object, Object>();
	  reqMap.put("_id", req.getParameter("iid"));
	  reqMap.put(VARIABLES.IID, req.getParameter("iid"));
	    
	  String username = getLoginUser();
	  reqMap.put(REQUEST_TYPES.SUB_REQ, "ESCROWLIST");
	  reqMap.put(USER.USERNAME, username);
	  reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.USER_REQ_SUB_REQ.USER_BALANCE);
	  
	  Map<String, Object> resMap  =
		        Services.getInstance().getRequestHandlerService()
		            .handleRequest(reqMap, REQUEST_TYPES.INTERVIEW_DETAILS);
		
	  map.addAllAttributes(resMap);		
	  resMap = Services.getInstance().getRequestHandlerService()
                .handleRequest(reqMap, REQUEST_TYPES.USER_REQ);
	  
	  map.addAllAttributes(resMap);	  
	  resMap.put("iid", req.getParameter("iid"));
	  map.addAllAttributes(resMap);
	  return "interviewdetail";
  }
  
  @RequestMapping(value="/updateinterviewdetail.do", method=RequestMethod.GET)
  public String updateInterviewDetail(HttpServletRequest req,Model map){
	  
	  Map<Object, Object> reqMap = new HashMap<Object, Object>();
	  reqMap.put("_id", req.getParameter("iid"));
	  reqMap.put(VARIABLES.IID, req.getParameter("iid"));
	    
	  Map<String, Object> resMap  =
		        Services.getInstance().getRequestHandlerService()
		            .handleRequest(reqMap, REQUEST_TYPES.INTERVIEW_DETAILS);
		
	  resMap.put("iid", req.getParameter("iid"));
	  List<String> skills = (List<String>) resMap.get(VARIABLES.POST_INTERVIEW.SKILLS);
	  String skillsString ="";
	  for (String string : skills) {
		  skillsString+=string+",";
	  }
	  skillsString=skillsString.substring(0, skillsString.length()-1);
	  resMap.put(VARIABLES.POST_INTERVIEW.SKILLS,skillsString);
	  map.addAllAttributes(resMap);
	  return "updateinterviewdetail";
  }
  
  // TODO : change the link use above method
  @RequestMapping(value="/interviewdetail_Interviewer.do", method=RequestMethod.GET)
  public String getEscrow_Interviewer(HttpServletRequest req,Model map){
	  
	  Map<Object, Object> reqMap = new HashMap<Object, Object>();
	  reqMap.put("_id", req.getParameter("iid"));
	  reqMap.put(VARIABLES.IID, req.getParameter("iid"));
	    
	  String username = getLoginUser();
	  reqMap.put(REQUEST_TYPES.SUB_REQ, "ESCROWLIST");
	  reqMap.put(USER.USERNAME, username);
	  reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.USER_REQ_SUB_REQ.USER_BALANCE);
	  
	  Map<String, Object> resMap  =
		        Services.getInstance().getRequestHandlerService()
		            .handleRequest(reqMap, REQUEST_TYPES.INTERVIEW_DETAILS);
		
	  map.addAllAttributes(resMap);		
	  resMap =Services.getInstance().getRequestHandlerService()
                .handleRequest(reqMap, REQUEST_TYPES.USER_REQ);
	  
	  map.addAllAttributes(resMap);
	  
	  resMap.put("iid", req.getParameter("iid"));
	  resMap.put("budget", req.getParameter("budget"));
	  map.addAllAttributes(resMap);
	  return "interviewdetail_interviewer";
  }
  
  @RequestMapping(value="/allbid.do", method=RequestMethod.GET)
  public ModelAndView getAllBid(){
	  Map<Object, Object> reqMap = new HashMap<Object, Object>();
	  reqMap.put(USER.USERNAME,getLoginUser());	  
	  Map<String, Object> resMap =
		        Services.getInstance().getRequestHandlerService()
	            .handleRequest(reqMap, REQUEST_TYPES.GET_MY_BIDS);
	  return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
	            .getJSONStringOfMap(resMap));
  }
  
  @RequestMapping(value="/getbidlist.do", method=RequestMethod.GET)
  public ModelAndView getAllBidPlaced( HttpServletRequest req){
	  Map<Object, Object> reqMap = new HashMap<Object, Object>();
	  reqMap.put(USER.USERNAME,getLoginUser());	 
	  reqMap.put("pagenum", req.getParameter("pagenum"));
	  reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.GET_ALL_BIDS);
	  Map<String, Object> resMap =
		        Services.getInstance().getRequestHandlerService()
	            .handleRequest(reqMap, REQUEST_TYPES.GET_MY_BIDS);
	  return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
	            .getJSONStringOfMap(resMap));
  }
 
  @RequestMapping(value="/getawardedbidlist.do", method=RequestMethod.GET)
  public ModelAndView getAwardedBidPlaced( HttpServletRequest req){
	  Map<Object, Object> reqMap = new HashMap<Object, Object>();
	  reqMap.put(USER.USERNAME,getLoginUser());	 
	  reqMap.put("pagenum", req.getParameter("pagenum"));
	  reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.GET_AWARDED_BIDS);
	  Map<String, Object> resMap =
		        Services.getInstance().getRequestHandlerService()
	            .handleRequest(reqMap, REQUEST_TYPES.GET_MY_BIDS);
	  return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
	            .getJSONStringOfMap(resMap));
  }
  
  @RequestMapping(value="/allbidplaced.do", method=RequestMethod.GET)
  public String getBidPlaced(){
	 return "allbid";
  }
  
  @RequestMapping(value="/awardedbid.do", method=RequestMethod.GET)
  public String getBidAwarded(){
	 return "awardedInterview";
  }
  @RequestMapping(value="/getinterviewlist.do", method=RequestMethod.GET)
  public ModelAndView getInterviewDetails( HttpServletRequest req){
	
	  Map<Object, Object> reqMap = new HashMap<Object, Object>();
	  reqMap.put(USER.USERNAME,getLoginUser());	  
	  reqMap.put("pagenum", req.getParameter("pagenum"));
	  reqMap.put("status", req.getParameter("status"));
	  reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.GET_INTERVIEW_SUB_REQ.GET_INTERVIEW_LIST);
	  Map<String, Object> resMap =
		        Services.getInstance().getRequestHandlerService()
		            .handleRequest(reqMap, REQUEST_TYPES.GET_INTERVIEW);	  	
     return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
	            .getJSONStringOfMap(resMap));
  }
  
  @RequestMapping(value="/closeddisputelist.do",method=RequestMethod.GET)
  public ModelAndView getDisputablelist(){
	  Map<Object, Object> reqMap = new HashMap<Object, Object>();
	  reqMap.put(USER.USERNAME,getLoginUser());	 
	  reqMap.put(USER.ROLE, getUserRole());
	  reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.CLOSED_DISPUTE_LIST);
	  Map<String, Object> resMap =
		        Services.getInstance().getRequestHandlerService()
		            .handleRequest(reqMap, REQUEST_TYPES.DISPUTE);	  	
     return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
	            .getJSONStringOfMap(resMap));	  
  }
  
  @RequestMapping(value="/getdisputableList.do",method=RequestMethod.GET)
  public ModelAndView getClosedDisputeList(){
	  Map<Object, Object> reqMap = new HashMap<Object, Object>();
	  reqMap.put(USER.USERNAME,getLoginUser());	 
	  reqMap.put(USER.ROLE, getUserRole());
	  reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.GET_INTERVIEW_SUB_REQ.GET_DISPUTABLE_INTERVIEW);
	  Map<String, Object> resMap =
		        Services.getInstance().getRequestHandlerService()
		            .handleRequest(reqMap, REQUEST_TYPES.GET_INTERVIEW);	  	
     return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
	            .getJSONStringOfMap(resMap));	  
  }
  
  @RequestMapping(value = "/postinterview.do", method = RequestMethod.POST)
  public ModelAndView postInterview(ModelMap model, HttpServletRequest req) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(VARIABLES.POST_INTERVIEW.TITLE, req.getParameter(VARIABLES.POST_INTERVIEW.TITLE));
    reqMap.put(VARIABLES.POST_INTERVIEW.INTERVIEWER, "");
    reqMap.put(VARIABLES.POST_INTERVIEW.SKILLS, req.getParameter(VARIABLES.POST_INTERVIEW.SKILLS));
    reqMap.put(VARIABLES.POST_INTERVIEW.DESCRIPTION,
        req.getParameter(VARIABLES.POST_INTERVIEW.DESCRIPTION));
    reqMap.put(VARIABLES.POST_INTERVIEW.BUDGET, req.getParameter(VARIABLES.POST_INTERVIEW.BUDGET));
    reqMap.put(VARIABLES.POST_INTERVIEW.EXPERIENCE,
        req.getParameter(VARIABLES.POST_INTERVIEW.EXPERIENCE));
    reqMap.put(VARIABLES.POST_INTERVIEW.INDUSTRY,
        req.getParameter(VARIABLES.POST_INTERVIEW.INDUSTRY));

    String user =getLoginUser();
    reqMap.put(VARIABLES.POST_INTERVIEW.INTERVIEWEE, user);
    reqMap.put("baseURL", Util.getbBaseURLpath(req));
    if (req.getParameter(DATASTORES.INTERVIEW.FILE) != null)
      reqMap.put(DATASTORES.INTERVIEW.FILE, req.getParameter(DATASTORES.INTERVIEW.FILE));

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.POST_INTERVIEW);
    if (req.getParameter(DATASTORES.INTERVIEW.FILE) != null) {
      Map<Object, Object> reqMapFS = new HashMap<Object, Object>();
      reqMapFS.put(DATASTORES.UPLOAD_FILE.ID, req.getParameter(DATASTORES.INTERVIEW.FILE));
      reqMapFS.put(VARIABLES.IID, resMap.get(VARIABLES.IID).toString());
      Services.getInstance().getRequestHandlerService()
          .handleRequest(reqMapFS, REQUEST_TYPES.FILESERVER_UPDATE_INTERVIEW_FILE);
    }
    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }
  
  @RequestMapping(value = "/editinterview.do", method = RequestMethod.POST)
  public ModelAndView editInterview(ModelMap model, HttpServletRequest req) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(VARIABLES.POST_INTERVIEW.TITLE, req.getParameter(VARIABLES.POST_INTERVIEW.TITLE));
    reqMap.put(VARIABLES.POST_INTERVIEW.INTERVIEWER, "");
    reqMap.put(VARIABLES.POST_INTERVIEW.SKILLS, req.getParameter(VARIABLES.POST_INTERVIEW.SKILLS));
    reqMap.put(VARIABLES.POST_INTERVIEW.DESCRIPTION,
        req.getParameter(VARIABLES.POST_INTERVIEW.DESCRIPTION));
    reqMap.put(VARIABLES.POST_INTERVIEW.BUDGET, req.getParameter(VARIABLES.POST_INTERVIEW.BUDGET));
    reqMap.put(VARIABLES.POST_INTERVIEW.EXPERIENCE,
        req.getParameter(VARIABLES.POST_INTERVIEW.EXPERIENCE));
    reqMap.put(VARIABLES.POST_INTERVIEW.INDUSTRY,
        req.getParameter(VARIABLES.POST_INTERVIEW.INDUSTRY));
    reqMap.put(VARIABLES.IID, req.getParameter(VARIABLES.IID));
    String user =getLoginUser();
    reqMap.put(VARIABLES.POST_INTERVIEW.INTERVIEWEE, user);
    reqMap.put("baseURL", Util.getbBaseURLpath(req));
    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.UPDATE_INTERVIEW);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }


  @RequestMapping(value = "/saveinterviewschedule.do", method = RequestMethod.POST)
  public ModelAndView saveinterviewSchedule(ModelMap model, HttpServletRequest req) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    String user = getLoginUser();
    reqMap.put(USER.USERNAME, user);
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.DATE1, req.getParameter("date1"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.DATE2, req.getParameter("date2"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.DATE3, req.getParameter("date3"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.IID, req.getParameter("iid"));
    reqMap.put("sender", req.getParameter("sender"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTED, req.getParameter("oth_opted"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION1, req.getParameter("oth_option1"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION2, req.getParameter("oth_option2"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION3, req.getParameter("oth_option3"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.FINAL_DATE, req.getParameter("finaldate"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.FINAL_OPTION, req.getParameter("final_option"));

    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.INTERVIEW_SCHEDULE_SUB_REQ.SAVE_SCHEDULE);

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.INTERVIEW_SCHEDULE);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }


  @RequestMapping(value = "/intervieweeopted.do", method = RequestMethod.POST)
  public ModelAndView intervieweeOpted(ModelMap model, HttpServletRequest req) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    String user = getLoginUser();
    reqMap.put(USER.USERNAME, user);
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.IID, req.getParameter("iid"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTED, req.getParameter("oth_opted"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION1, req.getParameter("oth_option1"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION2, req.getParameter("oth_option2"));
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION3, req.getParameter("oth_option3"));

    reqMap.put(REQUEST_TYPES.SUB_REQ,
        REQUEST_TYPES.INTERVIEW_SCHEDULE_SUB_REQ.SAVE_INTERVIEWEE_OPTION);

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.INTERVIEW_SCHEDULE);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));

  }

  @RequestMapping(value = "/getinterviewschedule.do", method = RequestMethod.POST)
  public ModelAndView getinterviewSchedule(ModelMap model, HttpServletRequest req) {

    String iid = req.getParameter("iid");
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(DATASTORES.INTERVIEW_SCHEDULE.IID, iid);
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.INTERVIEW_SCHEDULE_SUB_REQ.GET_SCHEDULE);
    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.INTERVIEW_SCHEDULE);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));

  }
}
