package com.interview.util;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service("ungenerator")
public class UserNameGenerator {

  public String getUserName() {
    String unique = UUID.randomUUID().toString();
    String name = unique.substring(30);
    Random r = new Random();
    char c1 = (char) (r.nextInt(26) + 'a');
    char c2 = (char) (r.nextInt(26) + 'a');
    char c3 = (char) (r.nextInt(26) + 'a');
    String username = c1 + "" + c2 + "" + c3 + name.substring(1);
    return username;
  }

}
