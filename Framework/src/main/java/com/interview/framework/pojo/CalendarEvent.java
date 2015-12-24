package com.interview.framework.pojo;

import java.io.Serializable;

public class CalendarEvent implements Serializable {
  private static final long serialVersionUID = -4680405247816776335L;

  String _id;
  int dayofyear;
  long endtime;
  String eventtype;
  boolean isrecursive;
  int recurdays;
  long starttime;
  long time;
  String title;
  String userid;
  int year;

  public String get_id() {
    return _id;
  }

  public int getDayofyear() {
    return dayofyear;
  }

  public long getEndtime() {
    return endtime;
  }

  public String getEventtype() {
    return eventtype;
  }

  public int getRecurdays() {
    return recurdays;
  }

  public long getStarttime() {
    return starttime;
  }

  public long getTime() {
    return time;
  }

  public String getTitle() {
    return title;
  }

  public String getUserid() {
    return userid;
  }

  public int getYear() {
    return year;
  }

  public boolean isIsrecursive() {
    return isrecursive;
  }

  public void set_id(String id) {
    _id = id;
  }

  public void setDayofyear(int dayofyear) {
    this.dayofyear = dayofyear;
  }

  public void setEndtime(long endtime) {
    this.endtime = endtime;
  }

  public void setEventtype(String eventtype) {
    this.eventtype = eventtype;
  }

  public void setIsrecursive(boolean isrecursive) {
    this.isrecursive = isrecursive;
  }

  public void setRecurdays(int recurdays) {
    this.recurdays = recurdays;
  }

  public void setStarttime(long starttime) {
    this.starttime = starttime;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public void setYear(int year) {
    this.year = year;
  }
}
