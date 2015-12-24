package com.interview.framework.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class BankWithdrawFundRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;
  private String username;
  private String accountName;
  private String accountNumber;
  private String accountType;
  private String ifscCode;
  private String bankName;
  private String bankBranch;
  private String bankCity;
  private long requestDate;
  private long processedDate;
  private String status;
  private BigDecimal amount;

  public BankWithdrawFundRequest() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getIfscCode() {
    return ifscCode;
  }

  public void setIfscCode(String ifscCode) {
    this.ifscCode = ifscCode;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBankBranch() {
    return bankBranch;
  }

  public void setBankBranch(String bankBranch) {
    this.bankBranch = bankBranch;
  }

  public String getBankCity() {
    return bankCity;
  }

  public void setBankCity(String bankCity) {
    this.bankCity = bankCity;
  }

  public long getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(long requestDate) {
    this.requestDate = requestDate;
  }

  public long getProcessedDate() {
    return processedDate;
  }

  public void setProcessedDate(long processedDate) {
    this.processedDate = processedDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
