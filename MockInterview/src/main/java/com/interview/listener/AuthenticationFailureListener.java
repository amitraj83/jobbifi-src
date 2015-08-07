package com.interview.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;

public class AuthenticationFailureListener implements
    ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  @Override
  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

    System.out.println(event.getAuthentication().getPrincipal() + " entered bad credentials");

  }


}
