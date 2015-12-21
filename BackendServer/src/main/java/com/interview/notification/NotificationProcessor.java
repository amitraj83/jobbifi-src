package com.interview.notification;

import java.util.HashMap;
import java.util.Map;

public abstract class NotificationProcessor {

  private static Map<String, Object> map = new HashMap<String, Object>();

  public void register(String TYPE, NotificationProcessor notification) {
    map.put(TYPE, notification);
  }

  public abstract void process(Object document);

  public static NotificationProcessor getInstance(String TYPE) {
    return (NotificationProcessor) map.get(TYPE);
  }

  // public abstract Map<Object, Object> createContentMap(DBObject row);

}
