package com.interview.services;

import org.springframework.stereotype.Service;

@Service("passgenerator")
public class PasswordGenerator {

  public String generatePassword() {
    String password = "";
    String alphabet =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789<>?/;:/*-+.#$%^&!";
    int i = 0;
    int Alphalaenge = alphabet.length();

    while (i < 10) {
      int rand = (int) (Math.random() * Alphalaenge);
      password = password + (alphabet.charAt(rand));
      ++i;
    }
    return password;
  }

}
