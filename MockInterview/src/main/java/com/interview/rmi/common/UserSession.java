package com.interview.rmi.common;

import org.json.JSONException;
import org.json.JSONObject;


public class UserSession {


  private String id;

  private JSONObject json = new JSONObject();

  private String username;


  private String password;

  private String password2;


  private String email;


  private String skills;


  private String companies;


  private String rate;


  private String balance;


  private String country;


  private String cv;

  private String socialNetwork;



  public String getSocialNetwork() {
    return socialNetwork;
  }


  public void setSocialNetwork(String socialNetwork) {
    this.socialNetwork = socialNetwork;
  }


  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    try {
      json.put("username", username);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    this.username = username;
  }

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    try {
      json.put("email", email);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    this.email = email;
  }

  public String getSkills() {
    return skills;
  }


  public void setSkills(String skills) {
    try {
      json.put("skills", skills);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    this.skills = skills;
  }

  public String getCompanies() {
    return companies;
  }


  public void setCompanies(String companies) {
    try {
      json.put("companies", companies);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    this.companies = companies;
  }

  public String getRate() {
    return rate;
  }


  public void setRate(String rate) {
    try {
      json.put("rate", rate);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    this.rate = rate;
  }

  public String getCountry() {
    return country;
  }


  public void setCountry(String country) {
    try {
      json.put("country", country);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    this.country = country;
  }

  public String getCv() {
    return cv;
  }


  public void setCv(String cv) {
    try {
      json.put("cv", cv);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    this.cv = cv;
  }

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword2() {
    return password2;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }

  public String getBalance() {
    return balance;
  }


  public void setBalance(String balance) {
    try {
      json.put("balance", balance);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    this.balance = balance;
  }

  public JSONObject getJSON() {
    return this.json;
  }

}
