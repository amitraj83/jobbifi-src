package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Education;
import com.interview.framework.pojo.Interviewer;
import com.interview.framework.pojo.Position;
import com.interview.framework.pojo.Skill;
import com.interview.rmi.DataStoreRegistry;

@Service
public class UpdateProfileHandler extends RequestHandler {
  public UpdateProfileHandler() {
    addHandler(this, REQUEST_TYPES.UPDATE_USER_PROFILE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    if (null != data.get(REQUEST_TYPES.SUB_REQ)
        && REQUEST_TYPES.UPDATE_USER_PROFILE_PIC.equals(data.get(REQUEST_TYPES.SUB_REQ))) {
      try {
        DataStoreRegistry.getInstance().getInterviewerDataStore().updateProfilePic(
            data.get(USER.USERNAME).toString(), data.get(USER.PROFILE_PIC).toString());
        resMap.put("status", 1);
      } catch (Exception e) {
        e.printStackTrace();
        resMap.put("status", 0);
      }
    } else {
      try {
        Interviewer interviewer = new Interviewer();
        if (data.get(USER.USERNAME) != null) {
          interviewer.setUsername(data.get(USER.USERNAME).toString());
        } else {
          resMap.put("status", 0);
          return resMap;
        }
        if (data.get(USER.PROFILE_PIC) != null)
          interviewer.setProfilePic(data.get(USER.PROFILE_PIC).toString());
        if (data.get(USER.COMPANIES) != null)
          interviewer.setCompanies((String[]) data.get(USER.COMPANIES));
        if (data.get(USER.SKILLS) != null)
          interviewer.setSkills((String[]) data.get(USER.SKILLS));
        /*if (data.get(USER.RATE) != null) {
        	try{
	          Integer rate = NumberUtils.parseNumber((String) data.get(USER.RATE), Integer.class);
	          if (null != rate) {
	            interviewer.setRate(rate);
	          } else {
	            interviewer.setRate(0);
	          }
        	}
        	catch(Exception ex){
        		interviewer.setRate(0);
        		ex.printStackTrace();
        	}
        }
        if (data.get(USER.PHONE_NUMBER) != null)  {
        	try{
	          PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
	          String phoneNumberString = (String) data.get(USER.PHONE_NUMBER);
	          PhoneNumber phoneNumber = phoneUtil.parse(phoneNumberString, "IN");
	          if (phoneUtil.isValidNumber(phoneNumber)) {
	            interviewer.setPhoneNumber((Long) phoneNumber.getNationalNumber());
	          }
        	}
        	catch(Exception ex){
        		ex.printStackTrace();
        	}
        }*/
        if (data.get(USER.CV) != null)
          interviewer.setCv(data.get(USER.CV).toString());
        if (data.get(USER.COUNTRY) != null)
          interviewer.setCountry(data.get(USER.COUNTRY).toString());
        if (data.get(USER.EDUCATIONS) != null) {
          List<Education> educations = (List<Education>) data.get(USER.EDUCATIONS);
          interviewer.setEducations(educations);
        }
        if (data.get(USER.POSITIONS) != null) {
          List<Position> positions = (List<Position>) data.get(USER.POSITIONS);
          interviewer.setPositions(positions);
        }
        if (data.get(USER.SKILL_LIST) != null) {
          List<Skill> skillList = (List<Skill>) data.get(USER.SKILL_LIST);
          if (null != skillList)
            interviewer.setSkillList(skillList);
        }
        boolean status =
            DataStoreRegistry.getInstance().getInterviewerDataStore().updateUserInfo(interviewer);
        if (status) {
          resMap.put("status", 1);
        } else
          resMap.put("status", 0);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return resMap;
  }
}
