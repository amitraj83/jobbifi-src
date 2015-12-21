package com.interview.framework.pojo;

import java.io.Serializable;

public class InterviewSchedule implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -5447401239388808059L;
  String _id;
  String iid;
  long date1;
  long date2;
  long date3;
  boolean oth_opted;
  boolean oth_option1;
  boolean oth_option2;
  boolean oth_option3;
  int final_option;
  long finaldate;
  long time;

  public String get_id() {
    return _id;
  }

  public void set_id(String id) {
    _id = id;
  }

  public String getIid() {
    return iid;
  }

  public void setIid(String iid) {
    this.iid = iid;
  }

  public long getDate1() {
    return date1;
  }

  public void setDate1(long date1) {
    this.date1 = date1;
  }

  public long getDate2() {
    return date2;
  }

  public void setDate2(long date2) {
    this.date2 = date2;
  }

  public long getDate3() {
    return date3;
  }

  public void setDate3(long date3) {
    this.date3 = date3;
  }

  public boolean getOth_opted() {
    return oth_opted;
  }

  public void setOth_opted(boolean othOpted) {
    oth_opted = othOpted;
  }

  public boolean getOth_option1() {
    return oth_option1;
  }

  public void setOth_option1(boolean othOption1) {
    oth_option1 = othOption1;
  }

  public boolean getOth_option2() {
    return oth_option2;
  }

  public void setOth_option2(boolean othOption2) {
    oth_option2 = othOption2;
  }

  public boolean getOth_option3() {
    return oth_option3;
  }

  public void setOth_option3(boolean othOption3) {
    oth_option3 = othOption3;
  }

  public long getFinaldate() {
    return finaldate;
  }

  public void setFinaldate(long finaldate) {
    this.finaldate = finaldate;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public int getFinal_option() {
    return final_option;
  }

  public void setFinal_option(int finalOption) {
    final_option = finalOption;
  }



}
