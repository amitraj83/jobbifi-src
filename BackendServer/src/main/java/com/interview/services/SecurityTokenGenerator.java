package com.interview.services;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service("secTokenGen")
public class SecurityTokenGenerator {

  public String getToken() {
    String token = "";
    SecureRandom random = new SecureRandom();
    System.out.println(new BigInteger(130, random).toString(32));

    byte bytes[] = new byte[30];
    random.nextBytes(bytes);

    for (byte b : bytes) {
      token += (b);
    }
    return token.replaceAll("-", "");
  }

}
