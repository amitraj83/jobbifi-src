package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.CalendarEvent;
import com.interview.framework.rmi.common.ICalendarEvent;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class CalendarEventStore extends UnicastRemoteObject implements ICalendarEvent {

  /**
   * 
   */
  private static final long serialVersionUID = 366566999448877807L;

  protected CalendarEventStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  @Override
  public CalendarEvent getEvent(String id) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put(DATASTORES.CALENDAR_EVENT.ID, new ObjectId(id));

    DBCursor cursor = collection.find(query);
    if (cursor.hasNext()) {
      DBObject row = cursor.next();
      CalendarEvent event = new CalendarEvent();
      event.set_id(id);
      if (row.get(DATASTORES.CALENDAR_EVENT.DAY_OF_YEAR) != null)
        event.setDayofyear(new Integer(row.get(DATASTORES.CALENDAR_EVENT.DAY_OF_YEAR).toString()));
      if (row.get(DATASTORES.CALENDAR_EVENT.END_TIME) != null)
        event.setEndtime(new Long(row.get(DATASTORES.CALENDAR_EVENT.END_TIME).toString()));
      if (row.get(DATASTORES.CALENDAR_EVENT.EVENT_TYPE) != null)
        event.setEventtype(row.get(DATASTORES.CALENDAR_EVENT.EVENT_TYPE).toString());
      if (row.get(DATASTORES.CALENDAR_EVENT.IS_RECURSIVE) != null)
        event.setIsrecursive(
            new Boolean(row.get(DATASTORES.CALENDAR_EVENT.IS_RECURSIVE).toString()));
      if (row.get(DATASTORES.CALENDAR_EVENT.RECUR_DAYS) != null)
        event.setRecurdays(new Integer(row.get(DATASTORES.CALENDAR_EVENT.RECUR_DAYS).toString()));
      if (row.get(DATASTORES.CALENDAR_EVENT.START_TIME) != null)
        event.setStarttime(new Long(row.get(DATASTORES.CALENDAR_EVENT.START_TIME).toString()));
      if (row.get(DATASTORES.CALENDAR_EVENT.TITLE) != null)
        event.setTitle(row.get(DATASTORES.CALENDAR_EVENT.TITLE).toString());
      if (row.get(DATASTORES.CALENDAR_EVENT.USERID) != null)
        event.setUserid(row.get(DATASTORES.CALENDAR_EVENT.USERID).toString());
      if (row.get(DATASTORES.CALENDAR_EVENT.YEAR) != null)
        event.setYear(new Integer(row.get(DATASTORES.CALENDAR_EVENT.YEAR).toString()));
      event.setTime(new Long(row.get(DATASTORES.CALENDAR_EVENT.TIME).toString()));
      return event;
    }

    return null;
  }

  @Override
  public String saveEvent(CalendarEvent event) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);
    ObjectId _id = new ObjectId();
    DBObject doc = new BasicDBObject();
    doc.put(DATASTORES.CALENDAR_EVENT.ID, _id);
    doc.put(DATASTORES.CALENDAR_EVENT.USERID, event.getUserid());
    doc.put(DATASTORES.CALENDAR_EVENT.TITLE, event.getTitle());
    doc.put(DATASTORES.CALENDAR_EVENT.YEAR, event.getYear());
    doc.put(DATASTORES.CALENDAR_EVENT.DAY_OF_YEAR, event.getDayofyear());
    doc.put(DATASTORES.CALENDAR_EVENT.START_TIME, event.getStarttime());
    doc.put(DATASTORES.CALENDAR_EVENT.END_TIME, event.getEndtime());
    doc.put(DATASTORES.CALENDAR_EVENT.EVENT_TYPE, event.getEventtype());
    doc.put(DATASTORES.CALENDAR_EVENT.IS_RECURSIVE, event.isIsrecursive());
    doc.put(DATASTORES.CALENDAR_EVENT.RECUR_DAYS, event.getRecurdays());
    doc.put(DATASTORES.CALENDAR_EVENT.TIME, new Date().getTime());

    CommandResult cr = collection.save(doc).getCachedLastError();
    if (cr.ok())
      return _id.toString();
    else
      return null;
  }

  @Override
  public List<CalendarEvent> getEvents(String userid, long start, long end) throws RemoteException {

    List<CalendarEvent> eventslist = new ArrayList<CalendarEvent>();

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    BasicDBObject query = new BasicDBObject(DATASTORES.CALENDAR_EVENT.USERID, userid);

    query.put(DATASTORES.TRANSACTION.TIME,
        BasicDBObjectBuilder.start("$gte", start).add("$lte", end).get());

    DBCursor cursor =
        collection.find(query).sort(new BasicDBObject(DATASTORES.CALENDAR_EVENT.TIME, -1));
    while (cursor.hasNext()) {
      DBObject row = cursor.next();

      CalendarEvent event = new CalendarEvent();
      event.set_id(row.get(DATASTORES.CALENDAR_EVENT.ID).toString());
      event.setDayofyear((Integer) row.get(DATASTORES.CALENDAR_EVENT.DAY_OF_YEAR));
      event.setEndtime((Long) row.get(DATASTORES.CALENDAR_EVENT.END_TIME));
      event.setEventtype(row.get(DATASTORES.CALENDAR_EVENT.EVENT_TYPE).toString());
      event.setIsrecursive((Boolean) row.get(DATASTORES.CALENDAR_EVENT.IS_RECURSIVE));
      event.setRecurdays((Integer) row.get(DATASTORES.CALENDAR_EVENT.RECUR_DAYS));
      event.setStarttime((Long) row.get(DATASTORES.CALENDAR_EVENT.START_TIME));
      event.setTime((Long) row.get(DATASTORES.CALENDAR_EVENT.TIME));
      event.setTitle(row.get(DATASTORES.CALENDAR_EVENT.TITLE).toString());
      event.setUserid(row.get(DATASTORES.CALENDAR_EVENT.USERID).toString());
      event.setYear((Integer) row.get(DATASTORES.CALENDAR_EVENT.YEAR));
      eventslist.add(event);
    }
    return eventslist;
  }

  @Override
  public boolean deleteEvent(String id) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put(DATASTORES.CALENDAR_EVENT.ID, new ObjectId(id));
    CommandResult cr = collection.remove(query).getCachedLastError();
    return cr.ok();
  }

  @Override
  public boolean updateEvent(String id, Map<String, Object> changes) throws RemoteException {

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject("_id", new ObjectId(id));
    BasicDBObject updateDoc = new BasicDBObject();
    Iterator<String> it = changes.keySet().iterator();
    while (it.hasNext()) {
      String key = it.next();
      if (key.equals(DATASTORES.CALENDAR_EVENT.END_TIME))
        updateDoc.append(DATASTORES.CALENDAR_EVENT.END_TIME,
            changes.get(DATASTORES.CALENDAR_EVENT.END_TIME));
      if (key.equals(DATASTORES.CALENDAR_EVENT.IS_RECURSIVE))
        updateDoc.append(DATASTORES.CALENDAR_EVENT.IS_RECURSIVE,
            changes.get(DATASTORES.CALENDAR_EVENT.IS_RECURSIVE));
      if (key.equals(DATASTORES.CALENDAR_EVENT.RECUR_DAYS))
        updateDoc.append(DATASTORES.CALENDAR_EVENT.RECUR_DAYS,
            changes.get(DATASTORES.CALENDAR_EVENT.RECUR_DAYS));
      if (key.equals(DATASTORES.CALENDAR_EVENT.START_TIME))
        updateDoc.append(DATASTORES.CALENDAR_EVENT.START_TIME,
            changes.get(DATASTORES.CALENDAR_EVENT.START_TIME));
      if (key.equals(DATASTORES.CALENDAR_EVENT.TITLE))
        updateDoc.append(DATASTORES.CALENDAR_EVENT.TITLE,
            changes.get(DATASTORES.CALENDAR_EVENT.TITLE));
    }
    updateDoc.append(DATASTORES.CALENDAR_EVENT.TIME, new Date().getTime());
    BasicDBObject update = new BasicDBObject().append("$set", updateDoc);
    CommandResult cr = collection.update(query, update).getCachedLastError();
    return cr.ok();
  }



}
