package com.interview.framework.chat;

import java.io.Serializable;
import java.util.Date;

public class ChatMessage implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -9019800160324751776L;
  private String rid;
  private String from;
  private String to;
  private String message;
  private Date time;
  private String type;

  public String getRid() {
    return rid;
  }

  public void setRid(String rid) {
    this.rid = rid;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
