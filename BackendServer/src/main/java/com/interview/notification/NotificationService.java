package com.interview.notification;

import org.springframework.stereotype.Service;

@Service("notificationService")
public class NotificationService {

  public void processNotification(Object document, String TYPE) {
    NotificationProcessor notification = NotificationProcessor.getInstance(TYPE);
    notification.process(document);
  }

}
