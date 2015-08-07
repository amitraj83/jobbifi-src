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

@Controller
public class RegistrationController {

  @Autowired
  private Properties myProps;

  private static final Logger logger = Logger.getLogger(RegistrationController.class);
  
  @RequestMapping(value = "/register.do", method = RequestMethod.POST)
  public ModelAndView processRequest(ModelMap model, HttpServletRequest req, HttpServletResponse res) {


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
    interviewer.setIndustries(null != req.getParameter("industry") ? new String[] {req
        .getParameter("industry")} : new String[] {});
    interviewer.setSkills(null != req.getParameter("skills") ? req.getParameter("skills")
        .split(",") : new String[] {});
    interviewer.setType(req.getParameter("type"));
    interviewer.setUsername(req.getParameter("username"));

    if (req.getParameter("type").equals("INTERVIEWER")) {
      interviewer.setRate(null != req.getParameter("rate") ? new Integer(req.getParameter("rate"))
          : 0);
    }

    String fileExtension = null;
    SecureRandom random = null;
    String secToken = null;
    String dir_uuid = null;

    if (null != req.getParameter(USER.PROFILE_PIC)
        && !"".equals(req.getParameter(USER.PROFILE_PIC))) {
      fileExtension =
          req.getParameter(USER.PROFILE_PIC).substring(
              req.getParameter(USER.PROFILE_PIC).lastIndexOf(".") + 1,
              req.getParameter(USER.PROFILE_PIC).length());
      random = new SecureRandom();
      secToken = new BigInteger(130, random).toString(32);
      dir_uuid = UUID.randomUUID().toString();
      interviewer.setProfilePic("media/" + dir_uuid + "/" + req.getParameter(USER.USERNAME) + "/"
          + secToken + "." + fileExtension);
      // requestMap.put(USER.PROFILE_PIC,"media/"+dir_uuid+"/"+req.getParameter(USER.USERNAME)+"/"+secToken+"."+fileExtension);
    } else {
      interviewer.setProfilePic("");
    }
    
 // TODO: need to to empty should have the client side validation
    interviewer.setProfilePic("resources/images/face.png");     
    
    Map<Object, Object> requestMap = new HashMap<Object, Object>();
    requestMap.put("user", interviewer);
    Map<String, Object> responseMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(requestMap, REQUEST_TYPES.INTERVIEWER_REGISTRATION);

    if (null != req.getParameter(USER.PROFILE_PIC)
        && !"".equals(req.getParameter(USER.PROFILE_PIC))
        && responseMap.get("response").toString().equals("1")) {
      try {
        File userDir =
            new File(myProps.getProperty("mediapath") + File.separator + dir_uuid + File.separator
                + req.getParameter(USER.USERNAME));
        if (!userDir.exists()){
          userDir.mkdirs();
        }

        InputStream is =
            new FileInputStream(new File(myProps.getProperty("homedir") + File.separator
                + req.getParameter(USER.PROFILE_PIC)));
        File targetFile =
            new File(userDir.getAbsolutePath() + File.separator + secToken + "." + fileExtension);
        if (!targetFile.exists())
          targetFile.createNewFile();
        FileOutputStream outputpic = new FileOutputStream(targetFile);
        IOUtils.copy(is, outputpic);
        outputpic.flush();

      } catch (Exception e) {
    	  logger.error("Exception occured : ",e);        
      }
    }

    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(responseMap));
  }
}