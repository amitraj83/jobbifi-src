package com.interview.framework.pojo;

import java.io.Serializable;

public class UserTransaction implements Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = 2391729710814528442L;
  String _id;
  String username;
  String tid;
  String purpose;
  String artifact;
  String artifactid;
  String thirdPartyTID = "";
  long time;

  public String getThirdPartyTID() {
    return thirdPartyTID;
  }

  public void setThirdPartyTID(String thirdPartyTID) {
    this.thirdPartyTID = thirdPartyTID;
  }

  public String get_id() {
    return _id;
  }

  public void set_id(String id) {
    _id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getTid() {
    return tid;
  }

  public void setTid(String tid) {
    this.tid = tid;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public String getArtifact() {
    return artifact;
  }

  public void setArtifact(String artifact) {
    this.artifact = artifact;
  }

  public String getArtifactid() {
    return artifactid;
  }

  public void setArtifactid(String artifactid) {
    this.artifactid = artifactid;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }
}
