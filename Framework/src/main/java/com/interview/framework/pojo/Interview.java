package com.interview.framework.pojo;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class Interview implements Serializable {

  private static final long serialVersionUID = 1L;

  @Field("id")
  private String id;
  @Field("title")
  private String title;
  @Field("interviewee")
  private String interviewee;
  private String interviewer;
  @Field("skills")
  private List<String> skills;
  @Field("description")
  private String description;
  @Field("dt")
  private long dt;
  @Field("status")
  private int status;
  @Field
  private String doctype;

  private String industry;
  private String experience;
  private String budget;
  private int bidcount;
  private String price;

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public int getBidcount() {
    return bidcount;
  }

  public void setBidcount(int bidcount) {
    this.bidcount = bidcount;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public String getExperience() {
    return experience;
  }

  public void setExperience(String experience) {
    this.experience = experience;
  }

  public String getBudget() {
    return budget;
  }

  public void setBudget(String budget) {
    this.budget = budget;
  }

  private double eb;
  private String file;

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    if (file != null)
      this.file = file;
    else
      this.file = "";
  }

  public double getEb() {
    return eb;
  }

  public void setEb(double eb) {
    this.eb = eb;
  }

  @Transient
  public String getDoctype() {
    return doctype;
  }

  @Transient
  public void setDoctype(String doctype) {
    this.doctype = doctype;
  }

  @Transient
  public String getId() {
    return id;
  }

  @Transient
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

  public String getInterviewer() {
    return interviewer;
  }

  public void setInterviewer(String interviewer) {
    this.interviewer = interviewer;
  }

  public List<String> getSkills() {
    return skills;
  }

  @Field("skills")
  public void setSkills(List<String> skills) {
    this.skills = skills;
  }

  public int getStatus() {
    return status;
  }

  @Transient
  public long getDt() {
    return dt;
  }

  @Field("dt")
  public void setDt(long dt) {
    this.dt = dt;
  }

  @Field("status")
  public void setStatus(int status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  /**
   * Get the status string from the status of interview
   */
  public String getStatusString() {
    if (this.getStatus() == 0) {
      return "OPEN";
    } else if (this.getStatus() == 1) {
      return "IN PROGRESS";
    } else if (this.getStatus() == 2) {
      return "IN PROGRESS";
    } else if (this.getStatus() == 3) {
      return "IN PROGRESS";
    } else if (this.getStatus() == 4) {
      return "APPROVAL PENDING";
    } else if (this.getStatus() == 5) {
      return "COMPLETED";
    } else if (this.getStatus() == 6) {
      return "CANCELLED";
    } else if (this.getStatus() == 7) {
      return "REPOSTED";
    } else if (this.getStatus() == 8) {
      return "USER RATED";
    } else if (this.getStatus() == 9) {
      return "DISPUTE";
    }
    return "OPEN";

  }

  @Field("description")
  public void setDescription(String description) {
    this.description = description;
  }
}
