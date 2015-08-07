package com.interview.framework.pojo;

import java.io.Serializable;

public class Escrow implements Serializable {

  public String id;
  public String visibleId;
  public double amount;
  public int status;
  public long date;
  public String iid;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getVisibleId() {
    return visibleId;
  }

  public void setVisibleId(String visibleId) {
    this.visibleId = visibleId;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }

  public String getIid() {
    return iid;
  }

  public void setIid(String iid) {
    this.iid = iid;
  }



}
