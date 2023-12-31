package com.interview.request.handlers;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service
public class PasswordHandler extends RequestHandler {
  Logger log = Logger.getLogger(PasswordHandler.class);

  public PasswordHandler() {
    addHandler(this, REQUEST_TYPES.PASSWORD);
  }

  private static final Logger logger = Logger.getLogger(PasswordHandler.class);

  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> res = new HashMap<String, Object>();
    try {
      String username = data.get(USER.USERNAME).toString();
      String password = data.get(USER.PASSWORD).toString();
      String currentPassword =
          DataStoreRegistry.getInstance().getInterviewerDataStore().getUserPassword(username);

      if (password != null && currentPassword != null && password.equals(currentPassword)) {
        String newpassword = data.get(USER.NEW_PASSWORD).toString();

        boolean result = DataStoreRegistry.getInstance().getInterviewerDataStore()
            .setPasswordForUserName(username, newpassword);
        if (result) {
          res.put("RESULT", "SUCCESS");
          Map<AttributeType, String> params = new HashMap<AttributeType, String>();
          params.put(AttributeType.USER_NAME, username);
          String email =
              DataStoreRegistry.getInstance().getInterviewerDataStore().getUserEmail(username);
          Services.getInstance().getEmailService().sendMail(Mailer.EmailType.PASSWORD_UPDATED,
              params, email);
          // Services.getInstance().getEmailService().sendMailChannelOnEvent("3", params, resEmail,
          // "Your Jobbify Password has been changed!");
        } else {
          res.put("RESULT", "ERROR");
        }
      } else {
        res.put("RESULT", "ERROR");
        res.put("MSG", "Current password is wrong.");
      }
    } catch (Exception e) {
      logger.error("Exception", e);
    }
    return res;
  }
}
