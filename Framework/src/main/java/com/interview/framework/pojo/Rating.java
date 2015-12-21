package com.interview.framework.pojo;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Rating implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 414133378822810156L;
  private String id;
  private String username;
  private String ratedBy;
  private int rate1;
  private int rate2;
  private int rate3;
  private int rate4;
  private double average;
  private String message;
  private ObjectId iid;
  private long time;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRatedBy() {
    return ratedBy;
  }

  public void setRatedBy(String ratedBy) {
    this.ratedBy = ratedBy;
  }

  public int getRate1() {
    return rate1;
  }

  public void setRate1(int rate1) {
    this.rate1 = rate1;
  }

  public int getRate2() {
    return rate2;
  }

  public void setRate2(int rate2) {
    this.rate2 = rate2;
  }

  public int getRate3() {
    return rate3;
  }

  public void setRate3(int rate3) {
    this.rate3 = rate3;
  }

  public int getRate4() {
    return rate4;
  }

  public void setRate4(int rate4) {
    this.rate4 = rate4;
  }

  public double getAverage() {
    return average;
  }

  public void setAverage(double average) {
    this.average = average;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ObjectId getIid() {
    return iid;
  }

  public void setIid(ObjectId iid) {
    this.iid = iid;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }



}
