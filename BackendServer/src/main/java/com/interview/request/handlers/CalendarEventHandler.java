package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.pojo.CalendarEvent;
import com.interview.rmi.DataStoreRegistry;

@Service
public class CalendarEventHandler extends RequestHandler {
  public CalendarEventHandler() {
    addHandler(this, REQUEST_TYPES.CALENDAR_EVENTS);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> res = new HashMap<String, Object>();
    String sub_req = data.get(REQUEST_TYPES.SUB_REQ).toString();
    try {
      if (sub_req.equals(REQUEST_TYPES.CALENDAR_SUB_REQ.SAVE_EVENT)) {
        Date start = new Date((Long) data.get(DATASTORES.CALENDAR_EVENT.START_TIME));
        Date end = new Date((Long) data.get(DATASTORES.CALENDAR_EVENT.END_TIME));
        CalendarEvent event = new CalendarEvent();
        Calendar calendarA = Calendar.getInstance();
        calendarA.setTime(start);
        event.setDayofyear(calendarA.get(Calendar.DAY_OF_YEAR));
        event.setEndtime(end.getTime());
        String eventTypeSymbol = data.get(DATASTORES.CALENDAR_EVENT.EVENT_TYPE).toString();
        if (eventTypeSymbol.equals("A"))
          event.setEventtype(DATASTORES.CALENDAR_EVENT.EVENT_TYPES.AVAILABLE);
        else if (eventTypeSymbol.equals("B"))
          event.setEventtype(DATASTORES.CALENDAR_EVENT.EVENT_TYPES.INTERVIEW);
        int recurDays = (Integer) data.get(DATASTORES.CALENDAR_EVENT.RECUR_DAYS);
        if (recurDays == 0) {
          event.setIsrecursive(false);
          event.setRecurdays(0);
        } else {
          event.setIsrecursive(true);
          event.setRecurdays(recurDays);
        }
        event.setStarttime(start.getTime());
        event.setTitle(data.get(DATASTORES.CALENDAR_EVENT.TITLE).toString());
        event.setUserid(data.get(DATASTORES.CALENDAR_EVENT.USERID).toString());
        event.setYear(calendarA.get(Calendar.YEAR));
        try {
          String eventId = DataStoreRegistry.getInstance().getCalendarEventStore().saveEvent(event);
          if (eventId != null) {
            res.put("status", "1");
            res.put("eventId", eventId);
          } else
            res.put("status", "0");
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      } else if (sub_req.equals(REQUEST_TYPES.CALENDAR_SUB_REQ.GET_EVENTS)) {
        long start = (Long) data.get(DATASTORES.CALENDAR_EVENT.START_TIME);
        long end = (Long) data.get(DATASTORES.CALENDAR_EVENT.END_TIME);
        String userid = data.get(DATASTORES.CALENDAR_EVENT.USERID).toString();
        try {
          List<CalendarEvent> events =
              DataStoreRegistry.getInstance().getCalendarEventStore().getEvents(userid, start, end);
          res.put("status", "1");
          res.put("list", events);
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      } else if (sub_req.equals(REQUEST_TYPES.CALENDAR_SUB_REQ.DELETE_EVENT)) {
        String _id = data.get(DATASTORES.CALENDAR_EVENT.ID).toString();
        String userid = data.get(DATASTORES.CALENDAR_EVENT.USERID).toString();
        CalendarEvent event = DataStoreRegistry.getInstance().getCalendarEventStore().getEvent(_id);
        if (event.getUserid().equals(userid)) {
          boolean deleted =
              DataStoreRegistry.getInstance().getCalendarEventStore().deleteEvent(_id);
          if (deleted)
            res.put("status", "1");
          else
            res.put("status", "3");
        } else
          res.put("status", "2");
      } else if (sub_req.equals(REQUEST_TYPES.CALENDAR_SUB_REQ.UPDATE_EVENT)) {
        String userid = data.get(DATASTORES.CALENDAR_EVENT.USERID).toString();
        String _id = data.get(DATASTORES.CALENDAR_EVENT.ID).toString();
        CalendarEvent event = DataStoreRegistry.getInstance().getCalendarEventStore().getEvent(_id);
        if (event.getUserid().equals(userid)) {
          int recdays = new Integer(data.get(DATASTORES.CALENDAR_EVENT.RECUR_DAYS).toString());
          Map<String, Object> changes = new HashMap<String, Object>();
          changes.put(DATASTORES.CALENDAR_EVENT.TITLE,
              (String) data.get(DATASTORES.CALENDAR_EVENT.TITLE));
          changes.put(DATASTORES.CALENDAR_EVENT.START_TIME,
              new Long(data.get(DATASTORES.CALENDAR_EVENT.START_TIME).toString()));
          changes.put(DATASTORES.CALENDAR_EVENT.END_TIME,
              new Long(data.get(DATASTORES.CALENDAR_EVENT.END_TIME).toString()));
          changes.put(DATASTORES.CALENDAR_EVENT.RECUR_DAYS, recdays);
          changes.put(DATASTORES.CALENDAR_EVENT.IS_RECURSIVE, recdays == 0 ? false : true);
          changes.put(DATASTORES.CALENDAR_EVENT.EVENT_TYPE,
              (String) data.get(DATASTORES.CALENDAR_EVENT.EVENT_TYPE));
          boolean result =
              DataStoreRegistry.getInstance().getCalendarEventStore().updateEvent(_id, changes);
          if (result) {
            res.put("status", "1");
          } else
            res.put("status", "2");
        } else {
          res.put("status", "3");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return res;
  }
}
