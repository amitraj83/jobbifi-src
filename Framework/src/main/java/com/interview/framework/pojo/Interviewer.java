package com.interview.framework.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Interviewer implements Serializable {

  public static interface SOCIALNETWORKS {
    String DIRECT = "DIRECT";
    String GOOGLE = "GOOGLE";
    String LINKEDIN = "LINKEDIN";
  }

  private static final long serialVersionUID = 1L;
  float balance;
  String chatPass;
  String[] companies;
  String country;
  String cv;
  private List<Education> educations = new ArrayList<Education>();
  String email;
  String id;
  private String[] industries;
  String password;
  String password2;
  Long phoneNumber;
  private List<Position> positions = new ArrayList<Position>();
  String profilePic;
  int rate;
  private List<Skill> skillList = new ArrayList<Skill>();
  String[] skills;
  String type;
  String username;

  private String userSocialNetwork;

  public void addEducation(Education education) {
    if (education != null)
      educations.add(education);
  }

  public void addPosition(Position position) {
    if (position != null)
      positions.add(position);
  }

  public void addSkillToList(Skill skill) {
    if (skill != null)
      skillList.add(skill);
  }

  public float getBalance() {
    return balance;
  }

  public String getChatPass() {
    return chatPass;
  }

  public String[] getCompanies() {
    return companies;
  }

  public String getCountry() {
    return country;
  }

  public String getCv() {
    return cv;
  }

  public List<Education> getEducations() {
    return this.educations;
  }

  public String getEmail() {
    return email;
  }

  public String getId() {
    return id;
  }

  public String[] getIndustries() {
    return industries;
  }

  public String getPassword() {
    return password;
  }

  public String getPassword2() {
    return password2;
  }

  public List<Position> getPositions() {
    return this.positions;
  }

  public String getProfilePic() {
    return profilePic;
  }

  public int getRate() {
    return rate;
  }

  public List<Skill> getSkillList() {
    return skillList;
  }

  public String[] getSkills() {
    return skills;
  }

  public String getType() {
    return type;
  }

  public String getUsername() {
    return username;
  }

  public String getUserSocialNetwork() {
    return userSocialNetwork;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  public void setChatPass(String chatPass) {
    this.chatPass = chatPass;
  }

  public void setCompanies(String[] companies) {
    this.companies = companies;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setCv(String cv) {
    this.cv = cv;
  }

  public void setEducations(List<Education> educations) {
    this.educations = educations;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setIndustries(String[] industries) {
    this.industries = industries;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }

  public void setPhoneNumber(Long l) {
    this.phoneNumber = l;
  }

  public void setPositions(List<Position> positions) {
    this.positions = positions;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }

  public void setSkillList(List<Skill> skillList) {
    this.skillList = skillList;
  }

  public void setSkills(String[] skills) {
    this.skills = skills;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setUserSocialNetwork(String userSocialNetwork) {
    this.userSocialNetwork = userSocialNetwork;
  }

  public Long getPhoneNumber() {
    return phoneNumber;
  }
}
