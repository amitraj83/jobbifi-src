package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.InterviewSchedule;
import com.interview.framework.rmi.common.IInterviewScheduleStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class InterviewScheduleStore extends UnicastRemoteObject implements IInterviewScheduleStore {


  protected InterviewScheduleStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }


  @Override
  public String insertSchedule(InterviewSchedule schedule) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    ObjectId _id = new ObjectId();

    DBObject row = new BasicDBObject();
    row.put(DATASTORES.INTERVIEW_SCHEDULE.ID, _id);
    row.put(DATASTORES.INTERVIEW_SCHEDULE.DATE1, schedule.getDate1());
    row.put(DATASTORES.INTERVIEW_SCHEDULE.DATE2, schedule.getDate2());
    row.put(DATASTORES.INTERVIEW_SCHEDULE.DATE3, schedule.getDate3());
    row.put(DATASTORES.INTERVIEW_SCHEDULE.FINAL_DATE, schedule.getFinaldate());
    row.put(DATASTORES.INTERVIEW_SCHEDULE.IID, schedule.getIid());
    row.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTED, schedule.getOth_opted());
    row.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION1, schedule.getOth_option1());
    row.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION2, schedule.getOth_option2());
    row.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION3, schedule.getOth_option3());
    row.put(DATASTORES.INTERVIEW_SCHEDULE.FINAL_OPTION, schedule.getFinal_option());
    row.put(DATASTORES.INTERVIEW_SCHEDULE.TIME, schedule.getTime());

    CommandResult cr = collection.save(row).getCachedLastError();
    if (cr.ok())
      return _id.toString();
    else
      return null;
  }

  @Override
  public InterviewSchedule getSchedule(String iid) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put(DATASTORES.INTERVIEW_SCHEDULE.IID, iid);


    DBObject row = collection.findOne(query);
    if (row != null) {
      return wrapSchedule(row);
    }

    return null;
  }


  @Override
  public InterviewSchedule getSchedule(ObjectId scheduleId) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put(DATASTORES.INTERVIEW_SCHEDULE.ID, scheduleId);


    DBObject row = collection.findOne(query);
    if (row != null) {
      return wrapSchedule(row);
    }

    return null;
  }

  private InterviewSchedule wrapSchedule(DBObject row) {
    InterviewSchedule schedule = new InterviewSchedule();
    schedule.set_id(row.get(DATASTORES.INTERVIEW_SCHEDULE.ID).toString());
    schedule.setDate1(new Long(row.get(DATASTORES.INTERVIEW_SCHEDULE.DATE1).toString()));
    schedule.setDate2(new Long(row.get(DATASTORES.INTERVIEW_SCHEDULE.DATE2).toString()));
    schedule.setDate3(new Long(row.get(DATASTORES.INTERVIEW_SCHEDULE.DATE3).toString()));
    schedule.setFinaldate(new Long(row.get(DATASTORES.INTERVIEW_SCHEDULE.FINAL_DATE).toString()));
    schedule.setIid(row.get(DATASTORES.INTERVIEW_SCHEDULE.IID).toString());
    schedule
        .setOth_opted(new Boolean(row.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTED).toString()));
    schedule.setOth_option1(new Boolean(row.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION1)
        .toString()));
    schedule.setOth_option2(new Boolean(row.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION2)
        .toString()));
    schedule.setOth_option3(new Boolean(row.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION3)
        .toString()));
    schedule.setFinal_option(new Integer(row.get(DATASTORES.INTERVIEW_SCHEDULE.FINAL_OPTION)
        .toString()));
    schedule.setTime(new Long(row.get(DATASTORES.INTERVIEW_SCHEDULE.TIME).toString()));
    return schedule;
  }


  @Override
  public boolean updateSchedule(InterviewSchedule schedule) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put(DATASTORES.INTERVIEW_SCHEDULE.ID, new ObjectId(schedule.get_id()));

    DBObject foundRow = collection.findOne(query);
    if (foundRow != null) {
      DBObject oldRow = new BasicDBObject();
      oldRow.put("_id", new ObjectId(schedule.get_id()));
      BasicDBObject newRow = new BasicDBObject();
      newRow.append(DATASTORES.INTERVIEW_SCHEDULE.DATE1, schedule.getDate1());
      newRow.append(DATASTORES.INTERVIEW_SCHEDULE.DATE2, schedule.getDate2());
      newRow.append(DATASTORES.INTERVIEW_SCHEDULE.DATE3, schedule.getDate3());
      newRow.append(DATASTORES.INTERVIEW_SCHEDULE.FINAL_DATE, schedule.getFinaldate());
      newRow.append(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTED, schedule.getOth_opted());
      newRow.append(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION1, schedule.getOth_option1());
      newRow.append(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION2, schedule.getOth_option2());
      newRow.append(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION3, schedule.getOth_option3());
      newRow.append(DATASTORES.INTERVIEW_SCHEDULE.FINAL_OPTION, schedule.getFinal_option());
      newRow.append(DATASTORES.INTERVIEW_SCHEDULE.TIME, schedule.getTime());
      BasicDBObject update = new BasicDBObject().append("$set", newRow);
      CommandResult cr = collection.update(oldRow, update).getCachedLastError();
      DBObject foundRowtest = collection.findOne(query);
      return cr.ok();
    } else
      return false;

  }


}
