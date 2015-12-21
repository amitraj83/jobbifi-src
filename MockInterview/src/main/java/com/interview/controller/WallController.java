package com.interview.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.services.Services;


@Controller
public class WallController extends BaseController {

  @RequestMapping(value = "/wall.do", method = RequestMethod.GET)
  public ModelAndView getWall(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

    String user = getLoginUser();
    Map<Object, Object> userName = new HashMap<Object, Object>();
    userName.put(USER.USERNAME, user);

    Map<String, Object> wallMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(userName, REQUEST_TYPES.GET_WALL);

    String response = "";
    try {
      response = Services.getInstance().getJSONUtilityService().writeValueAsString(wallMap);
    } catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new ModelAndView("response", "message", response);
  }

}
