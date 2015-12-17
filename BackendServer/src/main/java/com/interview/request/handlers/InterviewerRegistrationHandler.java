package com.interview.request.handlers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.Interviewer;
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service
public class InterviewerRegistrationHandler extends RequestHandler {
  private static final Logger logger = Logger.getLogger(InterviewerRegistrationHandler.class);

  public InterviewerRegistrationHandler() {
    addHandler(this, REQUEST_TYPES.INTERVIEWER_REGISTRATION);
  }

  @Override
  public Map<String, Object> handleRequest(final Map<Object, Object> reqMap) {
    Map<String, Object> map = new HashMap<String, Object>();
    int success_status = -1;
    try {
      final Interviewer interviewer = (Interviewer) reqMap.get("user");
      interviewer.setUserSocialNetwork(Interviewer.SOCIALNETWORKS.DIRECT);
      interviewer.setChatPass(Services.getInstance().getPasswordGenerator().generatePassword());
      success_status =
          DataStoreRegistry.getInstance().getInterviewerDataStore().insertInterviewer(interviewer);

      if (success_status == 2) {
        map.put("response", "2");
        return map;
      } else {
        Map<AttributeType, String> params = new HashMap<AttributeType, String>();
        params.put(AttributeType.USER_NAME, interviewer.getUsername());
        params.put(AttributeType.USER_TYPE, "INTERVIEWER");
        Services.getInstance().getEmailService().sendMail(Mailer.EmailType.NEW_REGISTRATION, params,
            interviewer.getEmail());
        // Services.getInstance().getEmailService().sendMailChannelOnEvent("1", param, recList,
        // "mail.resetpasswordlink.subject");
      }
    } catch (Exception e) {
      logger.error("Exception : INTERVIEWER REGISTRATION : ", e);
      map.put("response", "-1");
      return map;
    }
    map.put("response", success_status);
    return map;
  }
}
