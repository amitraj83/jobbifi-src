package com.interview.util;

import java.io.Serializable;
import java.util.List;

import com.interview.framework.pojo.Education;
import com.interview.framework.pojo.Position;

public class AdvisorSearchItem implements Serializable, Comparable<AdvisorSearchItem>{

	String aid;
	String username;
	String profilepic;
	String avgRating;
	List<String> skills;
	String ratePerHour;
	List<Education> educations;
	List<Position> positions ;
	String cv ;
	String country ;
	
	
	
	
	
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCv() {
		return cv;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}
	public String getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(String avgRating) {
		this.avgRating = avgRating;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public String getRatePerHour() {
		return ratePerHour;
	}
	public void setRatePerHour(String ratePerHour) {
		this.ratePerHour = ratePerHour;
	}
	public List<Education> getEducations() {
		return educations;
	}
	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}
	public List<Position> getPositions() {
		return positions;
	}
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	
	
	
	@Override
	public int compareTo(AdvisorSearchItem other) {
	
		if(Double.valueOf(this.avgRating) > Double.valueOf(other.avgRating))
			return -1;
		else if(Double.valueOf(this.avgRating) == Double.valueOf(other.avgRating))
			return 0;
		else
			return 1;
	}
	
	
	
	
	
	
	
}
