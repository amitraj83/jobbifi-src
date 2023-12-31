package com.interview.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Education;
import com.interview.framework.pojo.Interviewer;
import com.interview.framework.pojo.Position;
import com.interview.services.Services;
import com.interview.util.Util;

@Controller
public class RegistrationController {

  @Autowired
  private Properties myProps;

  private static final Logger logger = Logger.getLogger(RegistrationController.class);

  
  


	@RequestMapping(value = "/emailverification.do", method = RequestMethod.GET)
	public ModelAndView activateUser(HttpServletRequest req, HttpServletResponse res) {

		
		HashMap<Object, Object> reqMap = new HashMap<Object, Object>();
		reqMap.put("authid", String.valueOf(req.getParameter("authid")));
		reqMap.put("authtoken", String.valueOf(req.getParameter("authtoken")));
		reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.INTERVIEWER_REGISTRATION_SUB_REQ.EMAIL_VERIFICATION);
		
		Map<String, Object> result = Services.getInstance().getRequestHandlerService()
				.handleRequest(reqMap, REQUEST_TYPES.INTERVIEWER_REGISTRATION);

		String resp = String.valueOf(result.get("response"));
		if(resp.equals("1")){
			return new ModelAndView("response", "message","<html><center>Your account is active. Click <a href=\""+myProps.getProperty("domainurl")+"\">here</a> to login. </center></html>");	
		}
		else{
			return new ModelAndView("response", "message","<html><center>Your details are not valid. Make sure you clicked on the correct activation link. </center></html>");
		}
		
	}
  
  
  
  
  
  
  @RequestMapping(value = "/register.do", method = RequestMethod.POST)
  public ModelAndView processRequest(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {


    Interviewer interviewer = new Interviewer();
    if (null != req.getParameter("educationcount")
        && !"".equalsIgnoreCase(req.getParameter("educationcount"))) {
      for (int i = 0; i <= new Integer(req.getParameter("educationcount")); i++) {
        Education education = new Education();
        education.setDegree(req.getParameter("degree_" + i));
        education.setEndYear(new Integer(req.getParameter("endyear_" + i)));
        education.setFieldOfStudy(req.getParameter("fieldofstudy_" + i));
        education.setSchoolname(req.getParameter("school_" + i));
        education.setStartYear(new Integer(req.getParameter("startyear_" + i)));
        interviewer.addEducation(education);
      }
    }

    if (null != req.getParameter("positioncount")
        && !"".equalsIgnoreCase(req.getParameter("positioncount"))) {
      String[] companies = new String[new Integer(req.getParameter("positioncount")) + 1];
      for (int i = 0; i <= new Integer(req.getParameter("positioncount")); i++) {
        Position position = new Position();
        position.setCompanyName(req.getParameter("comp_" + i));
        position.setDescription(req.getParameter("desc_" + i));
        position.setEndYear(new Integer(req.getParameter("eyear_" + i)));
        position.setStartYear(new Integer(req.getParameter("syear_" + i)));
        position.setTitle(req.getParameter("title_" + i));
        interviewer.addPosition(position);
        companies[i] = req.getParameter("comp_" + i);
      }
      interviewer.setCompanies(companies);
    } else {
      interviewer.setCompanies(new String[] {});
    }

    interviewer.setPassword(req.getParameter("password"));
    interviewer.setBalance(0);
    interviewer.setCountry(null != req.getParameter("country") ? req.getParameter("country") : "");
    interviewer.setCv(null != req.getParameter("cv") ? req.getParameter("cv") : "");
    interviewer.setEmail(req.getParameter("useremail"));
    interviewer.setId(req.getParameter("useremail"));
    interviewer.setIndustries(null != req.getParameter("industry")
        ? new String[] {req.getParameter("industry")} : new String[] {});
    interviewer.setSkills(null != req.getParameter("skills") ? req.getParameter("skills").split(",")
        : new String[] {});
    interviewer.setType(req.getParameter("type"));
    String username = String.valueOf(req.getParameter("username")).replaceAll(" ", "");
    interviewer.setUsername(username);

    if (req.getParameter("type").equals("INTERVIEWER")) {
      interviewer
          .setRate(null != req.getParameter("rate") ? new Integer(req.getParameter("rate")) : 0);
    }

    String fileExtension = null;
    SecureRandom random = null;
    String secToken = null;
    String dir_uuid = null;

    

    // TODO: need to to empty should have the client side validation
    interviewer.setProfilePic("images/face.jpg");
    interviewer.setEmailHash(UUID.randomUUID().toString());
    interviewer.setActive("0");
    Map<Object, Object> requestMap = new HashMap<Object, Object>();
    requestMap.put("user", interviewer);
    requestMap.put("baseURL", Util.getbBaseURLpath(req));
    Map<String, Object> responseMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(requestMap, REQUEST_TYPES.INTERVIEWER_REGISTRATION);

    /*if (null != req.getParameter(USER.PROFILE_PIC) && !"".equals(req.getParameter(USER.PROFILE_PIC))
        && responseMap.get("response").toString().equals("1")) {
      try {
        File userDir = new File(myProps.getProperty("mediapath") + File.separator + dir_uuid
            + File.separator + req.getParameter(USER.USERNAME));
        if (!userDir.exists()) {
          userDir.mkdirs();
        }

        InputStream is = new FileInputStream(new File(
            myProps.getProperty("homedir") + File.separator + req.getParameter(USER.PROFILE_PIC)));
        File targetFile =
            new File(userDir.getAbsolutePath() + File.separator + secToken + "." + fileExtension);
        if (!targetFile.exists())
          targetFile.createNewFile();
        FileOutputStream outputpic = new FileOutputStream(targetFile);
        IOUtils.copy(is, outputpic);
        outputpic.flush();

      } catch (Exception e) {
        logger.error("Exception occured : ", e);
      }
    }*/

    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(responseMap));
  }
}
