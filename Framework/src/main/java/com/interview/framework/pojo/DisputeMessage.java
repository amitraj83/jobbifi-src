package com.interview.framework.pojo;

import java.io.Serializable;

public class DisputeMessage implements Serializable {


  private String id;
  private String disputeId;
  private String messageBy;
  private String message;
  private String fid;
  private long time;

  public String getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDisputeId() {
    return disputeId;
  }

  public void setDisputeId(String disputeId) {
    this.disputeId = disputeId;
  }

  public String getMessageBy() {
    return messageBy;
  }

  public void setMessageBy(String messageBy) {
    this.messageBy = messageBy;
  }

  public String getFid() {
    return fid;
  }

  public void setFid(String fid) {
    this.fid = fid;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

}
