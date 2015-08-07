package com.interview.framework.pojo;

import java.io.Serializable;

public class CalendarEvent implements Serializable {


  String _id;
  String userid;
  long starttime;
  long endtime;
  int dayofyear;
  int year;
  String eventtype;
  boolean isrecursive;
  int recurdays;
  String title;
  long time;

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String id) {
    _id = id;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public long getStarttime() {
    return starttime;
  }

  public void setStarttime(long starttime) {
    this.starttime = starttime;
  }

  public long getEndtime() {
    return endtime;
  }

  public void setEndtime(long endtime) {
    this.endtime = endtime;
  }

  public int getDayofyear() {
    return dayofyear;
  }

  public void setDayofyear(int dayofyear) {
    this.dayofyear = dayofyear;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getEventtype() {
    return eventtype;
  }

  public void setEventtype(String eventtype) {
    this.eventtype = eventtype;
  }

  public boolean isIsrecursive() {
    return isrecursive;
  }

  public void setIsrecursive(boolean isrecursive) {
    this.isrecursive = isrecursive;
  }

  public int getRecurdays() {
    return recurdays;
  }

  public void setRecurdays(int recurdays) {
    this.recurdays = recurdays;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
