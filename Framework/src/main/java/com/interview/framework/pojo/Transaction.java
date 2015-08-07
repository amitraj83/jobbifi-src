package com.interview.framework.pojo;

import java.io.Serializable;

public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
  private String id;
  private long time;
  private String type;
  private String owner;
  private String otherParty;
  private String status;
  private String details;
  private double gross;
  private double fee;
  private double netamount;
  private double balance;
  private double amount;
  public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

private String paypal_address;
  private long process_date;
  private long request_date;
  public String getPaypal_address() {
	return paypal_address;
}

public void setPaypal_address(String paypal_address) {
	this.paypal_address = paypal_address;
}

public long getProcess_date() {
	return process_date;
}

public void setProcess_date(long process_date) {
	this.process_date = process_date;
}

public long getRequest_date() {
	return request_date;
}

public void setRequest_date(long request_date) {
	this.request_date = request_date;
}

public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getOtherParty() {
    return otherParty;
  }

  public void setOtherParty(String otherParty) {
    this.otherParty = otherParty;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public double getGross() {
    return gross;
  }

  public void setGross(double gross) {
    this.gross = gross;
  }

  public double getFee() {
    return fee;
  }

  public void setFee(double fee) {
    this.fee = fee;
  }

  public double getNetamount() {
    return netamount;
  }

  public void setNetamount(double netamount) {
    this.netamount = netamount;
  }



}
