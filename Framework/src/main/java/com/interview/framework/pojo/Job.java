package com.interview.framework.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Job implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;

  private String title;

  private String interviewer;

  private List<String> skills;

  private String description;

  private String companyVideo;
  
  private String companyDescription;
  
  private long dt;

  private int status;

  private String salary;

  private String companyName;

  private String applyUrl;

  private String industry;

  private String file;

  private String experience;
  
  private String location;
  
  private List<JobApplication> jobApplications = new ArrayList<JobApplication>(0);
  
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getInterviewer() {
    return interviewer;
  }

  public void setInterviewer(String interviewer) {
    this.interviewer = interviewer;
  }

  public List<String> getSkills() {
    return skills;
  }

  public void setSkills(List<String> skills) {
    this.skills = skills;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getDt() {
    return dt;
  }

  public void setDt(long dt) {
    this.dt = dt;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getApplyUrl() {
    return applyUrl;
  }

  public void setApplyUrl(String applyUrl) {
    this.applyUrl = applyUrl;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

	public String getExperience() {
		return experience;
	}
	
	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<JobApplication> getJobApplications() {
		return jobApplications;
	}

	public void setJobApplications(List<JobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}

	public String getCompanyVideo() {
		return companyVideo;
	}

	public void setCompanyVideo(String companyVideo) {
		this.companyVideo = companyVideo;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	} 		
}
