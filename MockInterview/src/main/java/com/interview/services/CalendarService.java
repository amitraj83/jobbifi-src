package com.interview.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;



@Service("calendarService")
public class CalendarService {

  public Map<String, Object> addCalendar(long CalendarStartTime, long CalendarEndTime,
      String CalendarTitle, boolean isAllDayEvent) {
    Map<String, Object> res = new HashMap<String, Object>();



    return res;
  }

  public Map<String, Object> listCalendar(long showDate, String viewType) {
    Map<String, Object> res = new HashMap<String, Object>();



    return res;
  }

  public Map<String, Object> updateCalendar(String calendarId, long CalendarStartTime,
      long CalendarEndTime) {
    Map<String, Object> res = new HashMap<String, Object>();



    return res;
  }

  public Map<String, Object> removeCalendar(String calendarId) {
    Map<String, Object> res = new HashMap<String, Object>();



    return res;
  }

  public Map<String, Object> updateDetailedCalendar(String id, long st, long et, String subject,
      boolean isAllDayEvent, String description, String location, String colorValue,
      String timeZone) {
    Map<String, Object> res = new HashMap<String, Object>();



    return res;

  }


}
