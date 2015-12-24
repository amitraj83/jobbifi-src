package com.interview.framework.pojo;

import java.io.Serializable;

public class ResetPasswordEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -6841298721897646552L;
  private String id;
  private String username;
  private String sectoken;
  private String secretKey;
  private long dt;
  private boolean expired;
  private String ipAddress;

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

  public String getSectoken() {
    return sectoken;
  }

  public void setSectoken(String sectoken) {
    this.sectoken = sectoken;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public long getDt() {
    return dt;
  }

  public void setDt(long dt) {
    this.dt = dt;
  }

  public boolean isExpired() {
    return expired;
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }


}
