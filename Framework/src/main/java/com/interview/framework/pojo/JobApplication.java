package com.interview.framework.pojo;

import java.io.Serializable;

public class JobApplication implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;

  private String jobId;

  private String coverLetter;

  private String cvFileId;

  private long dt;

  private String status;

  private UploadedFile uploadedFile;

  private String applicantId;

  private int reviewCount;

  private double rating;

  private String profilePic;

  public JobApplication() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getJobId() {
    return jobId;
  }

  public void setJobId(String jobId) {
    this.jobId = jobId;
  }

  public String getCoverLetter() {
    return coverLetter;
  }

  public void setCoverLetter(String coverLetter) {
    this.coverLetter = coverLetter;
  }

  public String getCvFileId() {
    return cvFileId;
  }

  public void setCvFileId(String cvFileId) {
    this.cvFileId = cvFileId;
  }

  public long getDt() {
    return dt;
  }

  public void setDt(long dt) {
    this.dt = dt;
  }

  public String getApplicantId() {
    return applicantId;
  }

  public void setApplicantId(String applicantId) {
    this.applicantId = applicantId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public UploadedFile getUploadedFile() {
    return uploadedFile;
  }

  public void setUploadedFile(UploadedFile uploadedFile) {
    this.uploadedFile = uploadedFile;
  }

  public int getReviewCount() {
    return reviewCount;
  }

  public void setReviewCount(int reviewCount) {
    this.reviewCount = reviewCount;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public String getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }

  @Override
  public String toString() {
    return "JobApplication [id=" + id + ", jobId=" + jobId + ", coverLetter=" + coverLetter
        + ", cvFileId=" + cvFileId + ", dt=" + dt + ", applicantId=" + applicantId + ", status="
        + status + "]";
  }
}
