package com.interview.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.interview.services.Services;

public class HttpSessionListenerImpl implements HttpSessionListener {

  @Override
  public void sessionCreated(HttpSessionEvent sessionEvent) {
    System.out.println("Session Created: " + sessionEvent.getSession().getId());

  }

  @Override
  public void sessionDestroyed(HttpSessionEvent sessionEvent) {
    String sessionId = sessionEvent.getSession().getId();
    System.out.println("Session Destroyed:" + sessionId);
    Services.getInstance().getUserSessionManager().UnregisterUserSession(sessionId);
    // sessionEvent.getSession().putValue("sessionLoggedIn", "false");
    // System.out.println("Session Destroyed: "+sessionId+
    // " Value: "+sessionEvent.getSession().getValue("sessionLoggedIn"));
    // sessionEvent.getSession().invalidate();

  }



}
