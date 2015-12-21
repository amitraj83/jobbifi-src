package com.interview.framework.pojo;

import java.io.Serializable;

public class Message implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;
  private String message;

  private String from;

  private String to;

  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  private String parentMessageId;

  public String getParentMessageId() {
    return parentMessageId;
  }

  public void setParentMessageId(String parentMessageId) {
    this.parentMessageId = parentMessageId;
  }

  /**
   * ORIGINAL/REPLY Message
   */
  private String type;

  /**
   * The reference to the object associated with the message. Eg. JOB, INTERVIEW(MOCK),
   * INTERVIWER(ADVISOR) Default null
   */
  private String refEntity;

  /**
   * JOB-ID, INTERVIEW-ID, INTERVIWER-ID Default null
   */
  private String refId;

  private long creationDate;

  // if the message type is the original message then update
  // the last reply to date
  // default to creationDate
  private long lastReplyToDate;

  // READ / UNREAD
  private String status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRefEntity() {
    return refEntity;
  }

  public void setRefEntity(String refEntity) {
    this.refEntity = refEntity;
  }

  public String getRefId() {
    return refId;
  }

  public void setRefId(String refId) {
    this.refId = refId;
  }

  public long getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(long creationDate) {
    this.creationDate = creationDate;
  }

  public long getLastReplyToDate() {
    return lastReplyToDate;
  }

  public void setLastReplyToDate(long lastReplyToDate) {
    this.lastReplyToDate = lastReplyToDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public Message() {}
}
