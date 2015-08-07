package com.interview.solr.pojo;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class SolrJob {

  @Field("id")
  private String id;
  @Field("title")
  private String title;
  @Field("interviewer")
  private String interviewer;
  @Field("skills")
  private List<String> skills;
  @Field("description")
  private String description;
  @Field("dt")
  private long dt;
  @Field("doctype")
  private String doctype;
  @Field("companyname")
  private String companyName;
  @Field("location")
  private String location;

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

  public String getInterviewer() {
    return interviewer;
  }

  @Field("interviewer")
  public void setInterviewer(String interviewer) {
    this.interviewer = interviewer;
  }

  public String getCompanyName() {
    return companyName;
  }

  @Field("companyname")
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

	public String getLocation() {
		return location;
	}
	
	@Field("location")
	public void setLocation(String location) {
		this.location = location;
	}
}
