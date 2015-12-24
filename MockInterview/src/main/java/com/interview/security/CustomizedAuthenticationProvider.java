package com.interview.security;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.interview.beans.MockUser;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.services.Services;

public class CustomizedAuthenticationProvider implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String userEmailId)
      throws UsernameNotFoundException, DataAccessException {

    Map<String, Object> userCredentials = Services.getInstance().getUserDataService()
        .getUserData(userEmailId, REQUEST_TYPES.USER_CREDENTIALS);

    UserDetails user = new MockUser(userCredentials.get(USER.USERNAME).toString(),
        userCredentials.get(USER.PASSWORD).toString(), (String) userCredentials.get(USER.TYPE),
        (String) userCredentials.get(USER.USERNAME));
    return user;
  }
}
