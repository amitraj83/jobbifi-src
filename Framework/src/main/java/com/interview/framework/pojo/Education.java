package com.interview.framework.pojo;

import java.io.Serializable;

public class Education implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8967039446384532599L;
  private String _id;
  private String degree;
  private String fieldOfStudy;
  private String schoolname;
  private int startYear;
  private int endYear;



  public String get_id() {
    return _id;
  }

  public void set_id(String id) {
    _id = id;
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
}
