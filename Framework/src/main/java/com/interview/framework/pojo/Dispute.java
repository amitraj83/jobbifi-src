package com.interview.framework.pojo;

import java.io.Serializable;

public class Dispute implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 14196072739732478L;
  private String id;
  private String createdBy;
  private String IID;
  private int interviewOriginalStatus;
  private String status;
  private String result;
  private String visibleID;
  private String with;
  private double amount;
  private long time;
  private long timeclosed;
  private String title;
  private String closedBy;

  public String getClosedBy() {
    return closedBy;
  }

  public void setClosedBy(String closedBy) {
    this.closedBy = closedBy;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getVisibleID() {
    return visibleID;
  }

  public void setVisibleID(String visibleID) {
    this.visibleID = visibleID;
  }

  public long getTimeclosed() {
    return timeclosed;
  }

  public void setTimeclosed(long timeclosed) {
    this.timeclosed = timeclosed;
  }

  public String getWith() {
    return with;
  }

  public void setWith(String with) {
    this.with = with;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getIID() {
    return IID;
  }

  public void setIID(String iID) {
    IID = iID;
  }

  public int getInterviewOriginalStatus() {
    return interviewOriginalStatus;
  }

  public void setInterviewOriginalStatus(int interviewOriginalStatus) {
    this.interviewOriginalStatus = interviewOriginalStatus;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }


}
