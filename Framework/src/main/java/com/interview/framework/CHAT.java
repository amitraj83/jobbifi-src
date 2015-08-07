package com.interview.framework;

public interface CHAT {

  String RID = "rid";
  String FROM = "from";
  String TO = "to";
  String MESSAGE = "message";
  String TIME = "mt";
  String TYPE = "type";

  String SAVE_STATUS = "status";
  String COLLECTION_NAME = "chatstore";

  enum Type {
    OFFLINE, ONLINE
  }
}
