package com.interview.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.interview.framework.FIRST_REQUESTS;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.REQUEST_TYPES.CHAT_HISTORY_SUB_REQ;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;

@Service("prepareLoginData")
public class PrepareLoginData {
  public Map<String, Object> prepareLoginData(HttpServletRequest req) {
    HttpSession session = req.getSession();
    String userEmailId = SecurityContextHolder.getContext().getAuthentication().getName();
    Map<String, Object> resmap = new HashMap<String, Object>();

    Map<String, Object> userInfo = getUserInformation(userEmailId);

    Map<Object, Object> userNameReqMap = userNameReqMap(userInfo);

    Map<String, Object> allFirstRequest = Services.getInstance().getRequestHandlerService()
        .handleRequest(userNameReqMap, REQUEST_TYPES.RETRIEVE_FIRST_REQUEST);

    populateSession(session, userInfo);

    ObjectMapper mapper = new ObjectMapper();
    resmap.put("user_info", (userInfo));
    Map<String, Object> receivedMap =
        (Map<String, Object>) allFirstRequest.get(VARIABLES.REQ_RECEIVED);
    Iterator<String> it = receivedMap.keySet().iterator();
    while (it.hasNext()) {
      Map<String, String> dataMap = (Map<String, String>) receivedMap.get(it.next());
      if (dataMap.get(FIRST_REQUESTS.STATUS).equals("1")) {
        if (Services.getInstance().getUserSessionManager()
            .isUserOnline(dataMap.get(FIRST_REQUESTS.INTERVIEWEE)))
          dataMap.put(FIRST_REQUESTS.USER_ONLINE, "1");
      }
    }
    resmap.put(VARIABLES.REQ_RECEIVED, (allFirstRequest.get(VARIABLES.REQ_RECEIVED)));
    Map<String, Object> sentMap = (Map<String, Object>) allFirstRequest.get(VARIABLES.REQ_SENT);
    it = sentMap.keySet().iterator();
    while (it.hasNext()) {
      Map<String, String> dataMap = (Map<String, String>) sentMap.get(it.next());
      if (dataMap.get(FIRST_REQUESTS.STATUS).equals("1")) {
        if (Services.getInstance().getUserSessionManager()
            .isUserOnline(dataMap.get(FIRST_REQUESTS.INTERVIEWER)))
          dataMap.put(FIRST_REQUESTS.USER_ONLINE, "1");
      }
    }
    resmap.put(VARIABLES.REQ_SENT, (allFirstRequest.get(VARIABLES.REQ_SENT)));

    Map<String, Object> interviewMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(userNameReqMap, REQUEST_TYPES.GET_INTERVIEW);
    resmap.put(VARIABLES.MY_INTERVIEW, (interviewMap));

    Map<String, Object> bidMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(userNameReqMap, REQUEST_TYPES.GET_MY_BIDS);
    resmap.put(VARIABLES.MY_BIDS, (bidMap));

    Map<String, Object> availableUsers = Services.getInstance().getRequestHandlerService()
        .handleRequest(userNameReqMap, REQUEST_TYPES.AVAILABLE_USERS);
    Iterator<String> itUsers = availableUsers.keySet().iterator();
    while (itUsers.hasNext()) {
      String availableUser = itUsers.next();
      if (Services.getInstance().getUserSessionManager().isUserOnline(availableUser))
        availableUsers.put(availableUser, "1");
    }
    resmap.put(VARIABLES.AVAILABLE_USERS, (availableUsers));
    Map<String, Object> disputes = Services.getInstance().getRequestHandlerService()
        .handleRequest(userNameReqMap, REQUEST_TYPES.DISPUTES_RETRIEVE_ALL);
    resmap.put(VARIABLES.DISPUTES, (disputes));

    userNameReqMap.put(REQUEST_TYPES.SUB_REQ, CHAT_HISTORY_SUB_REQ.GET_OFFLINE_CHAT_COUNT);
    Map<String, Object> offlineChatCount = Services.getInstance().getRequestHandlerService()
        .handleRequest(userNameReqMap, REQUEST_TYPES.CHAT_HISTORY);
    resmap.put(VARIABLES.OFFLINE_CHAT_COUNT, offlineChatCount);
    return resmap;
  }

  /*
   * public HttpServletRequest prepareLoginData(HttpServletRequest req) { HttpSession session =
   * req.getSession(); String userEmailId =
   * SecurityContextHolder.getContext().getAuthentication().getName();
   * 
   * Map<String, Object> userInfo = getUserInformation(userEmailId);
   * 
   * Map<Object, Object> userNameReqMap = userNameReqMap(userInfo);
   * 
   * Map<String, Object> allFirstRequest =
   * Services.getInstance().getRequestHandlerService().handleRequest(userNameReqMap,
   * REQUEST_TYPES.RETRIEVE_FIRST_REQUEST);
   * 
   * populateSession(session, userInfo);
   * 
   * ObjectMapper mapper = new ObjectMapper(); try { req.setAttribute("user_info",
   * mapper.writeValueAsString(userInfo)); Map<String, Object> receivedMap = (Map<String,
   * Object>)allFirstRequest.get(VARIABLES.REQ_RECEIVED); Iterator<String> it =
   * receivedMap.keySet().iterator(); while(it.hasNext()){ Map<String, String> dataMap =
   * (Map<String, String>)receivedMap.get(it.next());
   * if(dataMap.get(FIRST_REQUESTS.STATUS).equals("1")){
   * if(Services.getInstance().getUserSessionManager
   * ().isUserOnline(dataMap.get(FIRST_REQUESTS.INTERVIEWEE)))
   * dataMap.put(FIRST_REQUESTS.USER_ONLINE, "1"); } } req.setAttribute(VARIABLES.REQ_RECEIVED,
   * mapper.writeValueAsString(allFirstRequest.get(VARIABLES.REQ_RECEIVED))); Map<String, Object>
   * sentMap = (Map<String, Object>)allFirstRequest.get(VARIABLES.REQ_SENT); it =
   * sentMap.keySet().iterator(); while(it.hasNext()){ Map<String, String> dataMap = (Map<String,
   * String>)sentMap.get(it.next()); if(dataMap.get(FIRST_REQUESTS.STATUS).equals("1")){
   * if(Services.
   * getInstance().getUserSessionManager().isUserOnline(dataMap.get(FIRST_REQUESTS.INTERVIEWER)))
   * dataMap.put(FIRST_REQUESTS.USER_ONLINE, "1"); } } req.setAttribute(VARIABLES.REQ_SENT,
   * mapper.writeValueAsString(allFirstRequest.get(VARIABLES.REQ_SENT)));
   * 
   * Map<String, Object> interviewMap =
   * Services.getInstance().getRequestHandlerService().handleRequest(userNameReqMap,
   * REQUEST_TYPES.GET_INTERVIEW); req.setAttribute(VARIABLES.MY_INTERVIEW,
   * mapper.writeValueAsString(interviewMap));
   * 
   * Map<String, Object> bidMap =
   * Services.getInstance().getRequestHandlerService().handleRequest(userNameReqMap,
   * REQUEST_TYPES.GET_MY_BIDS); req.setAttribute(VARIABLES.MY_BIDS,
   * mapper.writeValueAsString(bidMap));
   * 
   * Map<String, Object> availableUsers =
   * Services.getInstance().getRequestHandlerService().handleRequest(userNameReqMap,
   * REQUEST_TYPES.AVAILABLE_USERS); Iterator<String> itUsers = availableUsers.keySet().iterator();
   * while(itUsers.hasNext()){ String availableUser = itUsers.next();
   * if(Services.getInstance().getUserSessionManager().isUserOnline(availableUser))
   * availableUsers.put(availableUser, "1"); } req.setAttribute(VARIABLES.AVAILABLE_USERS,
   * mapper.writeValueAsString(availableUsers)); Map<String, Object> disputes =
   * Services.getInstance().getRequestHandlerService().handleRequest(userNameReqMap,
   * REQUEST_TYPES.DISPUTES_RETRIEVE_ALL); req.setAttribute(VARIABLES.DISPUTES,
   * mapper.writeValueAsString(disputes)); } catch (JsonGenerationException e) {
   * e.printStackTrace(); } catch (JsonMappingException e) { e.printStackTrace(); } catch
   * (IOException e) { e.printStackTrace(); } //new
   * Update((String)userInfo.get("username"),UPDATE_TYPES.CHAT_ONLINE,
   * (String)userInfo.get("username"),null); return req; }
   */
  private void populateSession(HttpSession session, Map<String, Object> userInfo) {
    Services.getInstance().getUserSessionManager().registerUserSession(session.getId(), userInfo);
    session.setAttribute("sessionLoggedIn", "true");
    session.setAttribute("mysessionuser", userInfo.get("username"));
  }

  private Map<Object, Object> userNameReqMap(Map<String, Object> userInfo) {
    Map<Object, Object> userName = new HashMap<Object, Object>();
    userName.put(USER.USERNAME, userInfo.get(USER.USERNAME));
    return userName;
  }

  private Map<String, Object> getUserInformation(String userEmailId) {
    Map<String, Object> userInfo = Services.getInstance().getUserDataService()
        .getUserData(userEmailId, REQUEST_TYPES.USER_INFO);
    return userInfo;
  }
}
