package com.interview.controller.linkedin;

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
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.Education;
import com.interview.framework.pojo.Interviewer;
import com.interview.framework.pojo.Position;
import com.interview.framework.pojo.Skill;
import com.interview.services.Services;

@Controller
public class LinkedInUserRegistrationController {


  @RequestMapping(value = "/linkedinuserregister.do", method = RequestMethod.POST)
  public ModelAndView linkedinRegistration(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    Interviewer interviewer = new Interviewer();
    try {
      List<Skill> skillList =
          Services.getInstance().getJSONUtilityService()
              .readValue(req.getParameter("skills"), new TypeReference<List<Skill>>() {});
      interviewer.setSkillList(skillList);
      if (skillList != null && skillList.size() > 0) {
        String[] skills = new String[skillList.size()];
        for (int i = 0; i < skillList.size(); i++) {
          skills[i] = skillList.get(i).getSkill();
        }
        interviewer.setSkills(skills);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      List<Education> educations =
          Services.getInstance().getJSONUtilityService()
              .readValue(req.getParameter("educations"), new TypeReference<List<Education>>() {});
      interviewer.setEducations(educations);
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      List<Position> positions =
          Services.getInstance().getJSONUtilityService()
              .readValue(req.getParameter("positions"), new TypeReference<List<Position>>() {});
      interviewer.setPositions(positions);
      if (positions != null && positions.size() > 0) {
        String[] companies = new String[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
          companies[i] = positions.get(i).getCompanyName();
        }
        interviewer.setCompanies(companies);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    interviewer.setBalance(0);
    interviewer.setCountry(req.getParameter("country"));
    interviewer.setCv(req.getParameter("cv"));
    interviewer.setEmail(req.getParameter("useremail"));
    interviewer.setId(req.getParameter("useremail"));
    interviewer.setType(req.getParameter("type"));
    interviewer.setUsername(req.getParameter("username"));
    interviewer.setIndustries(null != req.getParameter("industry") ? new String[] {req
        .getParameter("industry")} : new String[] {});

    interviewer.setProfilePic(req.getParameter("profilepic"));

    if (null != req.getParameter("type") && "INTERVIEWER".equals(req.getParameter("type"))) {
      interviewer.setRate(new Integer(req.getParameter("rate")));
    }

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("user", interviewer);
    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.LINKEDIN_USER_REG);

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }
    
 
}
