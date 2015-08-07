package com.interview.framework.pojo;

import java.io.Serializable;

public class Ticket implements Serializable {

  private String _id;
  private String servicetype;
  private String description;
  private String createdBy;
  private String agent;
  private String status;
  private long time;

  public String get_id() {
    return _id;
  }

  public void set_id(String id) {
    _id = id;
  }

  public String getServicetype() {
    return servicetype;
  }

  public void setServicetype(String servicetype) {
    this.servicetype = servicetype;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getAgent() {
    return agent;
  }

  public void setAgent(String agent) {
    this.agent = agent;
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

}
