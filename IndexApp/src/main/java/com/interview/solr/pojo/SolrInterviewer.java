package com.interview.solr.pojo;

import org.apache.solr.client.solrj.beans.Field;

public class SolrInterviewer {


  @Field
  private String id;
  @Field("username")
  private String username;
  @Field("skills")
  private String[] skills;
  @Field("companies")
  private String[] companies;
  @Field("doctype")
  private String docType;
  @Field("country")
  private String country;
  @Field("rate")
  private int rate;
  @Field("cv")
  private String cv;
  @Field("industries")
  private String[] industries;
  @Field("degree")
  private String[] degrees;
  @Field("fieldofstudy")
  private String[] fieldOfStudies;
  @Field("school")
  private String[] schoolnames;
  @Field("work")
  private String[] descriptions;
  @Field("position")
  private String[] titles;



  public String getUsername() {
    return username;
  }

  @Field("username")
  public void setUsername(String username) {
    this.username = username;
  }

  public String[] getDegrees() {
    return degrees;
  }

  @Field("degree")
  public void setDegrees(String[] degrees) {
    this.degrees = degrees;
  }

  public String[] getFieldOfStudies() {
    return fieldOfStudies;
  }

  @Field("fieldofstudy")
  public void setFieldOfStudies(String[] fieldOfStudies) {
    this.fieldOfStudies = fieldOfStudies;
  }

  public String[] getSchoolnames() {
    return schoolnames;
  }

  @Field("school")
  public void setSchoolnames(String[] schoolnames) {
    this.schoolnames = schoolnames;
  }

  public String[] getDescriptions() {
    return descriptions;
  }

  @Field("work")
  public void setDescriptions(String[] descriptions) {
    this.descriptions = descriptions;
  }

  public String[] getTitles() {
    return titles;
  }

  @Field("position")
  public void setTitles(String[] titles) {
    this.titles = titles;
  }

  public String getId() {
    return id;
  }

  @Field
  public void setId(String id) {
    this.id = id;
  }

  public String[] getSkills() {
    return skills;
  }

  @Field("skills")
  public void setSkills(String[] skills) {
    this.skills = skills;
  }

  public String[] getCompanies() {
    return companies;
  }

  @Field("companies")
  public void setCompanies(String[] companies) {
    this.companies = companies;
  }

  public String getDocType() {
    return docType;
  }

  @Field("doctype")
  public void setDocType(String docType) {
    this.docType = docType;
  }

  public String getCountry() {
    return country;
  }

  @Field("country")
  public void setCountry(String country) {
    this.country = country;
  }

  public String getCv() {
    return cv;
  }

  @Field("cv")
  public void setCv(String cv) {
    this.cv = cv;
  }

  public String[] getIndustries() {
    return industries;
  }

  @Field("industries")
  public void setIndustries(String[] industries) {
    this.industries = industries;
  }

  public int getRate() {
    return rate;
  }

  @Field("rate")
  public void setRate(int rate) {
    this.rate = rate;
  }



}
