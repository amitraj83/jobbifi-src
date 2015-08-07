package com.interview.framework;

public interface FIRST_REQUESTS {

  String DBCOLLECTION = "requests";
  String INTERVIEWER = "interviewer";
  String INTERVIEWEE = "interviewee";
  String DATE_AND_TIME = "dt";
  String ID = "_id";
  String STATUS = "status";
  String UPDATE_DATE = "update_dt";
  String USER_ONLINE = "online";

  enum STATUSES {
    PENDING, ACCEPTED, REJECTED
  }

}
