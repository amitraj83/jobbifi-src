package com.interview.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Education;
import com.interview.framework.pojo.Position;
import com.interview.framework.pojo.Skill;
import com.interview.services.Services;

@Controller
public class ProfileController extends BaseController {

  @RequestMapping(value = "/profile.do", method = RequestMethod.GET)
  public String getProfile() {
    return "profile";
  }

  @RequestMapping(value = "/profilesetting.do", method = RequestMethod.GET)
  public String getEditProfile() {
    return "profileSetting";
  }

  @RequestMapping(value = "/getProfileDetails.do", method = RequestMethod.GET)
  public ModelAndView getUserProfileDetails(@RequestParam("username") String username) {

    Map<Object, Object> request = new HashMap<Object, Object>();
    request.put(USER.USERNAME, username);
    request.put(REQUEST_TYPES.SUB_REQ, "USER_EXTERNAL_INFO");

    Map<String, Object> resmap = Services.getInstance().getRequestHandlerService()
        .handleRequest(request, REQUEST_TYPES.USER_INFO);
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resmap));
  }

  /**
   * Public user profile page available for the requested user.
   */
  @RequestMapping(value = "/userprofile.do", method = RequestMethod.GET)
  public ModelAndView getUserProfile(@RequestParam("name") String username) {
    ModelAndView mav = new ModelAndView("userProfile");
    mav.addObject("name", username);
    return mav;
  }

  @RequestMapping(value = "/userexternaldata.do", method = RequestMethod.GET)
  public ModelAndView getProfile(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(USER.USERNAME, req.getParameter("username"));
    reqMap.put(REQUEST_TYPES.SUB_REQ, "USER_EXTERNAL_INFO");
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.USER_INFO);
    resMap.put("isonline",
        Services.getInstance().getUserSessionManager().isUserOnline(req.getParameter("username")));
    return new ModelAndView("userinfo", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/userpicurl.do", method = RequestMethod.GET)
  public ModelAndView getUserpicurl(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(USER.USERNAME, req.getParameter("username"));
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.USER_INFO);
    String picURL = resMap.get(USER.PROFILE_PIC).toString();
    return new ModelAndView("response", "message", picURL);
  }

  @RequestMapping(value = "/usersdataforinbox.do", method = RequestMethod.GET)
  public ModelAndView getMultipleuserspicurll(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    String userslist = req.getParameter("userslist");
    String users[] = userslist.split("[$$]");
    Map<String, Object> statusMap = new HashMap<String, Object>();
    for (String user : users) {
      if (!user.trim().equals(""))
        statusMap.put(user, Services.getInstance().getUserSessionManager().isUserOnline(user));
    }

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("userslist", users);
    reqMap.put(REQUEST_TYPES.SUB_REQ, "USERS_PICS");

    Map<String, Object> picmap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.USER_INFO);
    Map<String, Object> resMap = new HashMap<String, Object>();
    resMap.put("statusmap", statusMap);
    resMap.put("picmap", picmap);

    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }

  @RequestMapping(value = "/isuseronline.do", method = RequestMethod.GET)
  public ModelAndView isuseronline(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {
    boolean isONline =
        Services.getInstance().getUserSessionManager().isUserOnline(req.getParameter("username"));

    return new ModelAndView("response", "message", isONline);
  }

  @RequestMapping(value = "/updateprofile.do", method = RequestMethod.GET)
  public ModelAndView updateProfile(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    boolean needToUpdateProfilePic = false;
    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    Map<Object, Object> reqMap2 = new HashMap<Object, Object>();

    if(req.getParameter("byadmin") != null && Boolean.parseBoolean(req.getParameter("byadmin")) == true)
    	reqMap2.put(USER.USERNAME, req.getParameter("targetuser"));
    else
    	reqMap2.put(USER.USERNAME, getLoginUser());
    
//    reqMap2.put(USER.USERNAME, getLoginUser());
    Map<String, Object> userInfo = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap2, REQUEST_TYPES.USER_INFO);
    String existingProfilepic = userInfo.get(USER.PROFILE_PIC).toString();
    if (!existingProfilepic.equals(req.getParameter("profilepic")))
      needToUpdateProfilePic = true;
    else
      needToUpdateProfilePic = false;

    reqMap.put(USER.RATE, req.getParameter("rate"));
    reqMap.put(USER.PHONE_NUMBER, req.getParameter("phonenumber"));
    reqMap.put(USER.CV, req.getParameter("cv"));
    reqMap.put(USER.COUNTRY, req.getParameter("country"));
    
    if(req.getParameter("byadmin") != null && Boolean.parseBoolean(req.getParameter("byadmin")) == true)
    	reqMap.put(USER.USERNAME, req.getParameter("targetuser"));
    else
    	reqMap.put(USER.USERNAME, getLoginUser());
    
    
    try {
      List<Skill> skillList = Services.getInstance().getJSONUtilityService()
          .readValue(req.getParameter("skills"), new TypeReference<List<Skill>>() {});
      reqMap.put(USER.SKILL_LIST, skillList);

      if (skillList != null && skillList.size() > 0) {
        String[] skills = new String[skillList.size()];
        for (int i = 0; i < skillList.size(); i++) {
          skills[i] = skillList.get(i).getSkill();

        }
        reqMap.put(USER.SKILLS, skills);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      List<Education> educations = Services.getInstance().getJSONUtilityService()
          .readValue(req.getParameter("educations"), new TypeReference<List<Education>>() {});
      reqMap.put(USER.EDUCATIONS, educations);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      List<Position> positions = Services.getInstance().getJSONUtilityService()
          .readValue(req.getParameter("positions"), new TypeReference<List<Position>>() {});
      reqMap.put(USER.POSITIONS, positions);

      if (positions != null && positions.size() > 0) {
        String[] companies = new String[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
          companies[i] = positions.get(i).getCompanyName();
        }
        reqMap.put(USER.COMPANIES, companies);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.UPDATE_USER_PROFILE);

    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(resMap));
  }
}
