package com.interview.chat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.REQUEST_TYPES;
import com.interview.services.Services;


@Controller
public class ChatHistoryController {

  @RequestMapping(value = "/chathistory.do", method = RequestMethod.GET)
  public ModelAndView getChatHistory(ModelMap model, HttpServletRequest req, HttpServletResponse res) {

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put("otheruser", req.getParameter("otheruser"));
    reqMap.put("user", req.getParameter("user"));

    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqMap, REQUEST_TYPES.CHAT_HISTORY);

    /*
     * List<ChatMessage> list = (List<ChatMessage>)resMap.get("data"); JSONObject json = new
     * JSONObject(); Iterator<ChatMessage> it = list.iterator(); for(int i=0;i<list.size();i++){
     * ChatMessage cm = list.get(i); try { json.put(i+"",
     * Services.getInstance().getJSONUtilityService().writeValueAsString(cm)); } catch
     * (JsonGenerationException e) { e.printStackTrace(); } catch (JsonMappingException e) {
     * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } catch (JSONException e)
     * { e.printStackTrace(); } }
     */


    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(resMap));
  }

}
