package com.interview.solr.pojo;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class SolrInterview {

  @Field("id")
  private String id;

  @Field("title")
  private String title;

  @Field("interviewee")
  private String interviewee;

  @Field("skills")
  private List<String> skills;

  @Field("description")
  private String description;

  @Field("dt")
  private long dt;

  @Field("doctype")
  private String doctype;

  @Field("status")
  private int status;

  public String getId() {
    return id;
  }

  @Field("id")
  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  @Field("title")
  public void setTitle(String title) {
    this.title = title;
  }

  public String getInterviewee() {
    return interviewee;
  }

  @Field("interviewee")
  public void setInterviewee(String interviewee) {
    this.interviewee = interviewee;
  }

  public List<String> getSkills() {
    return skills;
  }

  @Field("skills")
  public void setSkills(List<String> skills) {
    this.skills = skills;
  }

  public String getDescription() {
    return description;
  }

  @Field("description")
  public void setDescription(String description) {
    this.description = description;
  }

  public long getDt() {
    return dt;
  }

  @Field("dt")
  public void setDt(long dt) {
    this.dt = dt;
  }

  public String getDoctype() {
    return doctype;
  }

  @Field("doctype")
  public void setDoctype(String doctype) {
    this.doctype = doctype;
  }

  public int getStatus() {
    return status;
  }

  @Field("status")
  public void setStatus(int status) {
    this.status = status;
  }

}
