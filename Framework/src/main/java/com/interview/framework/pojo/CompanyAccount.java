package com.interview.framework.pojo;

import java.io.Serializable;

public class CompanyAccount implements Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = -7317708828062662808L;
  private String _id;
  private String purpose;
  private String debitOrCredit;
  private String interviewId;
  private double amount;
  private String initiator;
  private String status;

  public String get_id() {
    return _id;
  }

  public void set_id(String id) {
    _id = id;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public String getDebitOrCredit() {
    return debitOrCredit;
  }

  public void setDebitOrCredit(String debitOrCredit) {
    this.debitOrCredit = debitOrCredit;
  }

  public String getInterviewId() {
    return interviewId;
  }

  public void setInterviewId(String interviewId) {
    this.interviewId = interviewId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getInitiator() {
    return initiator;
  }

  public void setInitiator(String initiator) {
    this.initiator = initiator;
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

  private long time;

}
