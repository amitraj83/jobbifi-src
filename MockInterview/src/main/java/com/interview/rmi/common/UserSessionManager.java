package com.interview.rmi.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.interview.framework.USER;

public class UserSessionManager {

  private Map<String, UserSession> sessionRegistry = new ConcurrentHashMap<String, UserSession>();
  private Map<String, Integer> onlineStatus = new ConcurrentHashMap<String, Integer>();

  public void registerUserSession(String sessionId, UserSession userSession) {
    sessionRegistry.put(sessionId, userSession);
    onlineStatus.put(userSession.getUsername(), 1);
  }

  public void registerUserSession(String sessionId, Map<String, Object> userDetails) {
    UserSession us = new UserSession();
    if (userDetails.get(USER.BALANCE) != null)
      us.setBalance(userDetails.get(USER.BALANCE).toString());
    if (userDetails.get(USER.COMPANIES) != null)
      us.setCompanies(userDetails.get(USER.COMPANIES).toString());
    if (userDetails.get(USER.COUNTRY) != null)
      us.setCountry(userDetails.get(USER.COUNTRY).toString());
    if (userDetails.get(USER.CV) != null)
      us.setCv(userDetails.get(USER.CV).toString());
    if (userDetails.get(USER.EMAIL) != null)
      us.setEmail(userDetails.get(USER.EMAIL).toString());
    if (userDetails.get(USER.EMAIL) != null)
      us.setId(userDetails.get(USER.EMAIL).toString());
    if (userDetails.get(USER.RATE) != null)
      us.setRate(userDetails.get(USER.RATE).toString());
    if (userDetails.get(USER.SKILLS) != null)
      us.setSkills(userDetails.get(USER.SKILLS).toString());
    if (userDetails.get(USER.USERNAME) != null)
      us.setUsername(userDetails.get(USER.USERNAME).toString());
    if (userDetails.get(USER.SOCIAL_NETWORK) != null)
      us.setSocialNetwork(userDetails.get(USER.SOCIAL_NETWORK).toString());


    sessionRegistry.put(sessionId, us);
    onlineStatus.put(userDetails.get(USER.USERNAME).toString(), 1);
  }

  public UserSession getUserSession(String sessionId) {
    return sessionRegistry.get(sessionId);
  }

  public void UnregisterUserSession(String sessionId) {
    if (sessionId != null) {
      String userName =
          getUserSession(sessionId) != null ? getUserSession(sessionId).getUsername() : null;
      if (userName != null && onlineStatus.get(userName) != null) {
        onlineStatus.remove(userName);
      }
      if (sessionRegistry.get(sessionId) != null)
        sessionRegistry.remove(sessionId);
    }
  }

  public boolean isUserOnline(String username) {
    if (onlineStatus.containsKey(username)) {
      if (onlineStatus.get(username) == 1)
        return true;
      else
        return false;
    } else
      return false;
  }

  public List<String> getOnlineUsers() {
    List<String> list = new ArrayList<String>();
    Iterator<Entry<String, UserSession>> it = sessionRegistry.entrySet().iterator();
    while (it.hasNext()) {
      Entry<String, UserSession> entry = it.next();
      UserSession userSession = entry.getValue();
      list.add(userSession.getUsername());
    }
    return list;
  }

}
