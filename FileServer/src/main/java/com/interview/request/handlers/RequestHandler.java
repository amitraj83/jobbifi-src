package com.interview.request.handlers;

import java.util.HashMap;
import java.util.Map;


public abstract class RequestHandler {


  private static Map<String, RequestHandler> handlers = new HashMap<String, RequestHandler>();

  public static void addHandler(RequestHandler handler, String type) {
    handlers.put(type, handler);
  }

  public static RequestHandler getHandlerForRequestType(String type) {
    return handlers.get(type);
  }

  public abstract Map<String, Object> handleRequest(Map<Object, Object> data);

}
