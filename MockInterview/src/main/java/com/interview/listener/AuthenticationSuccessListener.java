package com.interview.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationSuccessListener implements
    ApplicationListener<AuthenticationSuccessEvent> {

  @Override
  public void onApplicationEvent(AuthenticationSuccessEvent authenticationEvent) {

    UserDetails userDetails = (UserDetails) authenticationEvent.getAuthentication().getPrincipal();
    System.out.println(userDetails.getUsername() + " is successfully logged in");

  }

}
