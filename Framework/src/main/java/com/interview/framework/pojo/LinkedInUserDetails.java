package com.interview.framework.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LinkedInUserDetails implements Serializable {

  private static final long serialVersionUID = 1L;
  private List<Education> educations = new ArrayList<Education>();
  private List<Certification> certifications = new ArrayList<Certification>();
  private String industry;
  private List<Language> languages = new ArrayList<Language>();
  private String location; // It should be linkedin location name.
  private List<Position> positions = new ArrayList<Position>();
  private List<Skill> skills = new ArrayList<Skill>();
  private String specialities;
  private String summary;
  private String profileURL;
  private String emailAddress;
  private String country;
  private String pictureUrl;

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getProfileURL() {
    return profileURL;
  }

  public void setProfileURL(String profileURL) {
    this.profileURL = profileURL;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getSpecialities() {
    return specialities;
  }

  public void setSpecialities(String specialities) {
    this.specialities = specialities;
  }

  public void addSkill(Skill skill) {
    this.skills.add(skill);
  }

  public List<Skill> getSkillsList() {
    return this.skills;
  }

  public static class Skill implements Serializable {
    private static final long serialVersionUID = 1L;
    private String skillname;
    private String proficiency;
    private String expYear;

    public String getSkillname() {
      return skillname;
    }

    public void setSkillname(String skillname) {
      this.skillname = skillname;
    }

    public String getProficiency() {
      return proficiency;
    }

    public void setProficiency(String proficiency) {
      this.proficiency = proficiency;
    }

    public String getExpYear() {
      return expYear;
    }

    public void setExpYear(String expYear) {
      this.expYear = expYear;
    }

    @Override
    public String toString() {
      return "Skill [skillname=" + skillname + ", proficiency=" + proficiency + ", expYear="
          + expYear + "]";
    }
  }


  public void addPosition(Position position) {
    this.positions.add(position);
  }

  public List<Position> getPositionsList() {
    return this.positions;
  }

  public static class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    private String companyName;
    private String description;
    private String expLevel;
    private List<String> industryNames;
    private String skillsAndExperience;
    private String title;
    private int startYear;
    private int endYear;


    public int getStartYear() {
      return startYear;
    }

    public void setStartYear(int startYear) {
      this.startYear = startYear;
    }

    public int getEndYear() {
      return endYear;
    }

    public void setEndYear(int endYear) {
      this.endYear = endYear;
    }

    public String getCompanyName() {
      return companyName;
    }

    public void setCompanyName(String companyName) {
      this.companyName = companyName;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getExpLevel() {
      return expLevel;
    }

    public void setExpLevel(String expLevel) {
      this.expLevel = expLevel;
    }

    public List<String> getIndustryNames() {
      return industryNames;
    }

    public void addIndustryName(String industryName) {
      this.industryNames.add(industryName);
    }

    public String getSkillsAndExperience() {
      return skillsAndExperience;
    }

    public void setSkillsAndExperience(String skillsAndExperience) {
      this.skillsAndExperience = skillsAndExperience;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    @Override
    public String toString() {
      return "Position [companyName=" + companyName + ", description=" + description + ", expLevel="
          + expLevel + ", industryNames=" + industryNames + ", skillsAndExperience="
          + skillsAndExperience + ", title=" + title + ", startYear=" + startYear + ", endYear="
          + endYear + "]";
    }
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void addLanguage(Language language) {
    this.languages.add(language);
  }

  public List<Language> getLanguagesList() {
    return this.languages;
  }

  public static class Language implements Serializable {
    private static final long serialVersionUID = 1L;
    private String langName;
    private String proficiency;

    public String getLangName() {
      return langName;
    }

    public void setLangName(String langName) {
      this.langName = langName;
    }

    public String getProficiency() {
      return proficiency;
    }

    public void setProficiency(String proficiency) {
      this.proficiency = proficiency;
    }

    @Override
    public String toString() {
      return "Language [langName=" + langName + ", proficiency=" + proficiency + "]";
    }
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public void addCertification(Certification certification) {
    this.certifications.add(certification);
  }

  public List<Certification> getCertifications() {
    return this.certifications;
  }

  public static class Certification implements Serializable {
    private static final long serialVersionUID = 1L;
    private String authority;
    private String name;
    private String number;

    public String getAuthority() {
      return authority;
    }

    public void setAuthority(String authority) {
      this.authority = authority;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getNumber() {
      return number;
    }

    public void setNumber(String number) {
      this.number = number;
    }

    @Override
    public String toString() {
      return "Certification [authority=" + authority + ", name=" + name + ", number=" + number
          + "]";
    }
  }

  public void addEducation(Education education) {
    this.educations.add(education);
  }

  public List<Education> getEducationList() {
    return this.educations;
  }

  public static class Education implements Serializable {

    private static final long serialVersionUID = 1L;
    private String degree;
    private String fieldOfStudy;
    private String schoolname;
    private int startYear;
    private int endYear;

    public int getStartYear() {
      return startYear;
    }

    public void setStartYear(int startYear) {
      this.startYear = startYear;
    }

    public int getEndYear() {
      return endYear;
    }

    public void setEndYear(int endYear) {
      this.endYear = endYear;
    }

    public String getDegree() {
      return degree;
    }

    public void setDegree(String degree) {
      this.degree = degree;
    }

    public String getFieldOfStudy() {
      return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
      this.fieldOfStudy = fieldOfStudy;
    }

    public String getSchoolname() {
      return schoolname;
    }

    public void setSchoolname(String schoolname) {
      this.schoolname = schoolname;
    }

    @Override
    public String toString() {
      return "Education [degree=" + degree + ", fieldOfStudy=" + fieldOfStudy + ", schoolname="
          + schoolname + ", startYear=" + startYear + ", endYear=" + endYear + "]";
    }
  }


  @Override
  public String toString() {
    return "LinkedInUserDetails [educations=" + educations + ", certifications=" + certifications
        + ", industry=" + industry + ", languages=" + languages + ", location=" + location
        + ", positions=" + positions + ", skills=" + skills + ", specialities=" + specialities
        + ", summary=" + summary + ", profileURL=" + profileURL + ", emailAddress=" + emailAddress
        + ", country=" + country + ", pictureUrl=" + pictureUrl + "]";
  }

}
