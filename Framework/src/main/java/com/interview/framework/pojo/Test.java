package com.interview.framework.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Test class represent the test(exam).
 */
public class Test implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;
  private String title;
  private String description;
  private int difficultyLevel;
  private List<String> skills;
  private long createdDate;
  private int duration;
  private int noOfQuestions;
  private boolean publish;

  public Test() {}

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getSkills() {
    return skills;
  }

  public void setSkills(List<String> skills) {
    this.skills = skills;
  }

  public long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public int getDifficultyLevel() {
    return difficultyLevel;
  }

  public void setDifficultyLevel(int difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getNoOfQuestions() {
    return noOfQuestions;
  }

  public void setNoOfQuestions(int noOfQuestions) {
    this.noOfQuestions = noOfQuestions;
  }

  public boolean isPublish() {
    return publish;
  }

  public void setPublish(boolean publish) {
    this.publish = publish;
  }

  @Override
  public String toString() {
    return "Test [id=" + id + ", title=" + title + ", description=" + description
        + ", difficultyLevel=" + difficultyLevel + ", skills=" + skills + ", createdDate="
        + createdDate + "]";
  }
}
