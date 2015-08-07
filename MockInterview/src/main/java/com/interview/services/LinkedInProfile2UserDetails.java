package com.interview.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.code.linkedinapi.schema.Certification;
import com.google.code.linkedinapi.schema.Certifications;
import com.google.code.linkedinapi.schema.Education;
import com.google.code.linkedinapi.schema.Educations;
import com.google.code.linkedinapi.schema.Industry;
import com.google.code.linkedinapi.schema.Language;
import com.google.code.linkedinapi.schema.Person;
import com.google.code.linkedinapi.schema.Position;
import com.google.code.linkedinapi.schema.Skill;
import com.google.code.linkedinapi.schema.Skills;
import com.interview.framework.pojo.LinkedInUserDetails;

@Service("InProfile2UserDetails")
public class LinkedInProfile2UserDetails {
  private String BLANK = "";

  public LinkedInUserDetails convertLinkedInProfile2UserDetails(Person profile) {

    LinkedInUserDetails user = new LinkedInUserDetails();
    user.setEmailAddress(profile.getEmailAddress());
    user.setProfileURL(profile.getPublicProfileUrl());
    user.setPictureUrl(profile.getPictureUrl());    
    Certifications cfs = profile.getCertifications();
    if (cfs != null) {
      List<Certification> certifications = cfs.getCertificationList();
      if (certifications != null && certifications.size() > 0) {
        for (Certification certification : certifications) {
          com.interview.framework.pojo.LinkedInUserDetails.Certification myCertfication =
              new com.interview.framework.pojo.LinkedInUserDetails.Certification();
          if (certification.getAuthority() != null
              && certification.getAuthority().getName() != null)
            myCertfication.setAuthority(certification.getAuthority().getName());
          else
            myCertfication.setAuthority(BLANK);

          if (certification.getName() != null)
            myCertfication.setName(certification.getName());
          else
            myCertfication.setName(BLANK);

          if (certification.getNumber() != null)
            myCertfication.setNumber(certification.getNumber());
          else
            myCertfication.setNumber(BLANK);

          user.addCertification(myCertfication);
        }
      }
    }

    Educations edus = profile.getEducations();
    if (edus != null) {
      List<Education> educations = edus.getEducationList();
      if (educations != null && educations.size() > 0) {
        for (Education education : educations) {
          com.interview.framework.pojo.LinkedInUserDetails.Education myEducation =
              new com.interview.framework.pojo.LinkedInUserDetails.Education();

          if (education.getDegree() != null)
            myEducation.setDegree(education.getDegree());
          else
            myEducation.setDegree(BLANK);

          if (education.getFieldOfStudy() != null)
            myEducation.setFieldOfStudy(education.getFieldOfStudy());
          else
            myEducation.setFieldOfStudy(BLANK);

          if (education.getSchoolName() != null)
            myEducation.setSchoolname(education.getSchoolName());
          else
            myEducation.setSchoolname(BLANK);

          user.addEducation(myEducation);
        }
      }
    }

    if (profile.getLanguages() != null) {
      List<Language> languages = profile.getLanguages().getLanguageList();
      if (languages != null && languages.size() > 0) {
        for (Language language : languages) {
          com.interview.framework.pojo.LinkedInUserDetails.Language myLanguage =
              new com.interview.framework.pojo.LinkedInUserDetails.Language();

          if (language.getLanguage() != null && language.getLanguage().getName() != null)
            myLanguage.setLangName(language.getLanguage().getName());
          else
            myLanguage.setLangName(BLANK);

          if (language.getProficiency() != null && language.getProficiency().getName() != null)
            myLanguage.setProficiency(language.getProficiency().getName());
          else
            myLanguage.setLangName(BLANK);


          user.addLanguage(myLanguage);
        }
      }
    }

    if (profile.getPositions() != null) {
      List<Position> positions = profile.getPositions().getPositionList();
      if (positions != null && positions.size() > 0) {
        for (Position position : positions) {
          com.interview.framework.pojo.LinkedInUserDetails.Position myPosition =
              new com.interview.framework.pojo.LinkedInUserDetails.Position();

          if (position.getCompany() != null && position.getCompany().getName() != null)
            myPosition.setCompanyName(position.getCompany().getName());
          else
            myPosition.setCompanyName(BLANK);

          if (position.getIndustries() != null) {
            List<Industry> posIndustries = position.getIndustries().getIndustryList();
            for (Industry industry : posIndustries) {
              if (industry != null && industry.getName() != null) {
                myPosition.addIndustryName(industry.getName());
              } else
                myPosition.addIndustryName(BLANK);
            }
          }

          if (position.getStartDate() != null && position.getStartDate().getYear() != null)
            myPosition.setStartYear(position.getStartDate().getYear().intValue());

          if (position.getEndDate() != null && position.getEndDate().getYear() != null)
            myPosition.setEndYear(position.getEndDate().getYear().intValue());

          if (position.getDescription() != null)
            myPosition.setDescription(position.getDescription());
          else
            myPosition.setDescription(BLANK);

          if (position.getExperienceLevel() != null
              && position.getExperienceLevel().getName() != null)
            myPosition.setExpLevel(position.getExperienceLevel().getName());
          else
            myPosition.setExpLevel(BLANK);

          user.addPosition(myPosition);
        }
      }
    }
    if (profile.getSkills() != null) {
      Skills skills = profile.getSkills();
      List<Skill> skillsList = skills.getSkillList();
      if (skillsList != null && skillsList.size() > 0) {
        for (Skill skill : skillsList) {
          com.interview.framework.pojo.LinkedInUserDetails.Skill mySkill =
              new com.interview.framework.pojo.LinkedInUserDetails.Skill();
          if (skill.getSkill() != null && skill.getSkill().getName() != null)
            mySkill.setSkillname(skill.getSkill().getName());
          else
            continue;
          if (skill.getYears() != null && skill.getYears().getName() != null)
            mySkill.setExpYear(skill.getYears().getName());
          else
            mySkill.setExpYear(BLANK);
          if (skill.getProficiency() != null && skill.getProficiency().getLevel() != null
              && skill.getProficiency().getLevel().name() != null)
            mySkill.setProficiency(skill.getProficiency().getLevel().name());
          else
            mySkill.setProficiency(BLANK);
          user.addSkill(mySkill);
        }
      }
    }
    if (profile.getLocation() != null && profile.getLocation().getName() != null)
      user.setLocation(profile.getLocation().getName());
    else
      user.setLocation(BLANK);
    if (profile.getIndustry() != null)
      user.setIndustry(profile.getIndustry());
    else
      user.setIndustry(BLANK);
    if (profile.getSpecialties() != null)
      user.setSpecialities(profile.getSpecialties());
    else
      user.setSpecialities(BLANK);
    if (profile.getSummary() != null)
      user.setSummary(profile.getSummary());
    else
      user.setSummary(BLANK);

    if (profile.getPictureUrl() != null)
      user.setProfileURL(profile.getPictureUrl());

    if (profile.getLocation() != null && profile.getLocation().getCountry() != null) {
      user.setCountry(profile.getLocation().getCountry().getCode());
    } else {
      user.setCountry(BLANK);
    }
    return user;
  }
}