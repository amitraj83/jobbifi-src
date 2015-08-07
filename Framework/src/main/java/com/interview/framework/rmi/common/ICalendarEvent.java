package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.interview.framework.pojo.CalendarEvent;

public interface ICalendarEvent extends Remote {

  String NAME = "calendarevent";

  public String saveEvent(CalendarEvent event) throws RemoteException;

  public CalendarEvent getEvent(String _id) throws RemoteException;

  public List<CalendarEvent> getEvents(String userid, long start, long end) throws RemoteException;

  public boolean deleteEvent(String _id) throws RemoteException;

  public boolean updateEvent(String _id, Map<String, Object> changes) throws RemoteException;
}
