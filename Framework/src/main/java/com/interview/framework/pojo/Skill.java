package com.interview.framework.pojo;

import java.io.Serializable;

public class Skill implements Serializable {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  private String _id;

  private String skill;

  private int skillYear;

  public String get_id() {
    return _id;
  }

  public void set_id(String id) {
    _id = id;
  }

  public String getSkill() {
    return skill;
  }

  public void setSkill(String skill) {
    this.skill = skill;
  }

  public int getSkillYear() {
    return skillYear;
  }

  public void setSkillYear(int skillYear) {
    this.skillYear = skillYear;
  }
}
