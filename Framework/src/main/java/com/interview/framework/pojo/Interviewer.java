package com.interview.framework.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Interviewer implements Serializable {

  private static final long serialVersionUID = 1L;

  String id;
  String username;
  String password;
  String password2;
  String email;
  String[] companies;
  int rate;
  String type;
  float balance;
  String country;
  String cv;
  String profilePic;
  String chatPass;
  String[] skills;
  private List<Skill> skillList = new ArrayList<Skill>();
  private List<Education> educations = new ArrayList<Education>();
  private List<Position> positions = new ArrayList<Position>();
  private String[] industries;
  private String userSocialNetwork;

  public String getChatPass() {
    return chatPass;
  }

  public void setChatPass(String chatPass) {
    this.chatPass = chatPass;
  }

  public String getUserSocialNetwork() {
    return userSocialNetwork;
  }

  public void setUserSocialNetwork(String userSocialNetwork) {
    this.userSocialNetwork = userSocialNetwork;
  }

  public String[] getIndustries() {
    return industries;
  }

  public void setIndustries(String[] industries) {
    this.industries = industries;
  }

  public void addSkillToList(Skill skill) {
    if (skill != null)
      skillList.add(skill);
  }

  public void addPosition(Position position) {
    if (position != null)
      positions.add(position);
  }

  public List<Position> getPositions() {
    return this.positions;
  }

  public void setPositions(List<Position> positions) {
    this.positions = positions;
  }

  public void addEducation(Education education) {
    if (education != null)
      educations.add(education);
  }

  public List<Education> getEducations() {
    return this.educations;
  }

  public void setEducations(List<Education> educations) {
    this.educations = educations;
  }

  public String getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String[] getCompanies() {
    return companies;
  }

  public void setCompanies(String[] companies) {
    this.companies = companies;
  }

  public int getRate() {
    return rate;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCv() {
    return cv;
  }

  public void setCv(String cv) {
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

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String[] getSkills() {
    return skills;
  }

  public void setSkills(String[] skills) {
    this.skills = skills;
  }

  public void setSkillList(List<Skill> skillList) {
    this.skillList = skillList;
  }

  public List<Skill> getSkillList() {
    return skillList;
  }

  public static interface SOCIALNETWORKS {
    String DIRECT = "DIRECT";
    String LINKEDIN = "LINKEDIN";
    String GOOGLE = "GOOGLE";
  }
}
