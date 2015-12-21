package com.interview.framework.pojo;

import java.io.Serializable;
import java.util.Map;

public class Notification implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8926940152515277831L;
  private String notificationId;
  // private ObjectId interviewId;
  private String createdBy;
  private String type;
  private Map<Object, Object> content;
  private String recepientUser;
  private long entryDate;


  public String getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(String notificationId) {
    this.notificationId = notificationId;
  }

  /*
   * public ObjectId getInterviewId() { return interviewId; } public void setInterviewId(ObjectId
   * interviewId) { this.interviewId = interviewId; }
   */
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRecepientUser() {
    return recepientUser;
  }

  public void setRecepientUser(String recepientUser) {
    this.recepientUser = recepientUser;
  }

  public long getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(long entryDate) {
    this.entryDate = entryDate;
  }

  public Map<Object, Object> getContent() {
    return content;
  }

  public void setContent(Map<Object, Object> content) {
    this.content = content;
  }



}
