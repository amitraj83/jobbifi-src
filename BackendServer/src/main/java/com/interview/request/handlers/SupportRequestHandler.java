package com.interview.request.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.proto.Mailer.AttributeType;
import com.interview.proto.Mailer.EmailType;
import com.interview.services.Services;

public class SupportRequestHandler extends RequestHandler {
  public SupportRequestHandler() {
    addHandler(this, REQUEST_TYPES.CREATE_SUPPORT_REQUEST);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<AttributeType, String> emailData = new HashMap<AttributeType, String>();
    for (Entry<Object, Object> attribute : data.entrySet()) {
      if (attribute.getKey().equals(VARIABLES.SUPPORT_REQUEST_NAME)) {
        emailData.put(AttributeType.SUPPORT_REQUEST_NAME, (String) attribute.getValue());
      } else if (attribute.getKey().equals(VARIABLES.SUPPORT_REQUEST_MESSAGE)) {
        emailData.put(AttributeType.SUPPORT_REQUEST_MESSAGE, (String) attribute.getValue());
      } else if (attribute.getKey().equals(VARIABLES.SUPPORT_REQUEST_EMAIL)) {
        emailData.put(AttributeType.SUPPORT_REQUEST_EMAIL, (String) attribute.getValue());
      }
    }
    Services.getInstance().getEmailService().sendMail(EmailType.SUPPORT_REQUEST, emailData,
        VARIABLES.SUPPORT_EMAIL);
    HashMap<String, Object> responseMap = new HashMap<String, Object>();
    responseMap.put("status", "Support request created.");
    return responseMap;
  }
}
