package com.interview.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Education;
import com.interview.framework.pojo.Position;
import com.interview.framework.pojo.Skill;
import com.interview.services.Services;
import com.interview.util.AdvisorSearchItem;

/***
 * 
 * The class handles the request for Searching the INTERVIEWER (Advisor) users.
 * 
 *
 */
@Controller
public class SearchController {

  private static final int NO_OF_RESULTS = 10;

  @RequestMapping(value = "/advisors.do", method = RequestMethod.GET)
  public String getadvisor() {
    return "advisor";
  }

  @RequestMapping(value = "/search.do", method = RequestMethod.POST)
  public ModelAndView processRequest(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    Map<Object, Object> requestMap = new HashMap<Object, Object>();
    requestMap.put("searchkey", req.getParameter("searchkey"));
    requestMap.put("start", Integer.parseInt(req.getParameter("start")));
    requestMap.put("rows", NO_OF_RESULTS);
    requestMap.put(REQUEST_TYPES.SUB_REQ, "FIND_ADVISOR");

    Map<String, Object> responseMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(requestMap, REQUEST_TYPES.SEARCH_ADDITIONAL_DATA);

    List<AdvisorSearchItem> items = new ArrayList<AdvisorSearchItem>();

    Iterator<String> it = responseMap.keySet().iterator();
    while(it.hasNext()){
    	String username = it.next();
    	Map<String, Object> interviewer = (Map<String, Object>)responseMap.get(username);
    	
    	AdvisorSearchItem item = new AdvisorSearchItem();
    	
    	item.setUsername(username);
        
    	Iterator<String> infoIterator = interviewer.keySet().iterator();
        while (infoIterator.hasNext()) {
          String dataname = infoIterator.next();
          Object value = interviewer.get(dataname);

          if (dataname.equalsIgnoreCase("_id"))
            item.setAid(String.valueOf(value));

          if (dataname.equalsIgnoreCase(USER.RATING))
                item.setAvgRating(String.valueOf(value));
          
          if(dataname.equalsIgnoreCase("score"))
        	  item.setScore(new Double(String.valueOf(value)));
          
          if(dataname.equalsIgnoreCase(USER.PROFILE_PIC))
            item.setProfilepic(String.valueOf(value));

          if(dataname.equalsIgnoreCase(USER.EDUCATIONS)){
            List<Education> educations = (List<Education>) value;
            item.setEducations(educations);
          }
          
          if(dataname.equalsIgnoreCase(USER.POSITIONS)){
        	  List<Position> positions = (List<Position>) value;
              item.setPositions(positions);
          }

          if (dataname.equalsIgnoreCase(USER.SKILLS)) {
//            String parsedSkill =
//                String.valueOf(value).substring(1, String.valueOf(value).length() - 1);
//            String[] tokenizedSkill = parsedSkill.split(",");
        	  List<Skill>  skills = (List<Skill> )value;
        	  List<String> skillsString = new ArrayList<String>();
            for (Skill skill : skills) {
              skillsString.add(skill.getSkill());
            }
            item.setSkills(skillsString);
          }

          if (dataname.equalsIgnoreCase(USER.COUNTRY))
            item.setCountry(String.valueOf(value));

//          if (dataname.equalsIgnoreCase("rate"))
//            item.setRatePerHour(String.valueOf(value));

          if (dataname.equalsIgnoreCase(USER.CV))
            item.setCv(String.valueOf(value));

        }
        items.add(item);
    }
 


    java.util.Collections.sort(items);
    Map<String, List<AdvisorSearchItem>> result = new HashMap<String, List<AdvisorSearchItem>>();
    result.put("data", items);
    String json = Services.getInstance().getJSONUtilityService().getJSONStringOfMap(result);
    return new ModelAndView("searchResult", "message", json);
  }

  private Map<Object, Object> getAdvisorsNames(Map<String, Object> data) {
    Map<Object, Object> usersList = new HashMap<Object, Object>();
    Iterator<String> it = data.keySet().iterator();
    while (it.hasNext()) {
      String key = it.next();
      Map<String, Object> interviewer = (Map<String, Object>) data.get(key);
      if (interviewer.get("username") != null)
        usersList.put(interviewer.get("username").toString(), "");
    }
    return usersList;
  }

  @RequestMapping(value = "/gettopadvisor.do", method = RequestMethod.POST)
  public ModelAndView getTopAdvisor(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    Map<Object, Object> requestMap = new HashMap<Object, Object>();
    requestMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.INTERVIEWER_SUB_REQ.GET_TOP_ADVISOR);
    requestMap.put("noOfResult", req.getParameter("noOfResult"));

    Map<String, Object> responseMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(requestMap, REQUEST_TYPES.INTERVIEWER);

    return new ModelAndView("searchResult", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(responseMap));
  }

}
