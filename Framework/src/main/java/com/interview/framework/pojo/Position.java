package com.interview.framework.pojo;

import java.io.Serializable;

public class Position implements Serializable {

  private String _id;
  private String companyName;
  private String description;
  private String title;
  private int startYear;
  private int endYear;



  public String get_id() {
    return _id;
  }

  public void set_id(String id) {
    _id = id;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

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



}
