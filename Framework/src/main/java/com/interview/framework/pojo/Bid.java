package com.interview.framework.pojo;

import java.io.Serializable;

public class Bid implements Serializable {

  private static final long serialVersionUID = 1L;
  private String id;
  private String bidder;
  private String iid;
  private String msg;
  private String price;
  private long date;
  private int status;
  private String attachmentID;
  private int reputation;

  public int getReputation() {
    return reputation;
  }

  public void setReputation(int reputation) {
    this.reputation = reputation;
  }

  public String getStatusString() {
    if (this.getStatus() == 0) {
      return "OPEN";
    } else if (this.getStatus() == 1) {
      return "IN PROGRESS";
    } else if (this.getStatus() == 2) {
      return "IN PROGRESS";
    } else if (this.getStatus() == 3) {
      return "IN PROGRESS";
    } else if (this.getStatus() == 4) {
      return "APPROVAL PENDING";
    } else if (this.getStatus() == 5) {
      return "COMPLETED";
    } else if (this.getStatus() == 6) {
      return "CANCELLED";
    } else if (this.getStatus() == 7) {
      return "REPOSTED";
    } else if (this.getStatus() == 8) {
      return "USER RATED";
    } else if (this.getStatus() == 9) {
      return "DISPUTE";
    }
    return "OPEN";
  }

  public String getAttachmentID() {
    return attachmentID;
  }

  public void setAttachmentID(String attachmentID) {
    this.attachmentID = attachmentID;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getBidder() {
    return bidder;
  }

  public void setBidder(String bidder) {
    this.bidder = bidder;
  }

  public String getIid() {
    return iid;
  }

  public void setIid(String iid) {
    this.iid = iid;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public long getDate() {
    return date;
  }

  public void setDate(long date) {
    this.date = date;
  }


}
