package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.InterviewSchedule;
import com.interview.framework.rmi.common.IInterviewScheduleStore;
import com.interview.rmi.DataStoreRegistry;

@Service
public class InterviewScheduleHandler extends RequestHandler {

  public InterviewScheduleHandler() {
    addHandler(this, REQUEST_TYPES.INTERVIEW_SCHEDULE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> res = new HashMap<String, Object>();
    try {
      String SUB_REQ = data.get(REQUEST_TYPES.SUB_REQ).toString();
      if (SUB_REQ.equals(REQUEST_TYPES.INTERVIEW_SCHEDULE_SUB_REQ.SAVE_SCHEDULE)) {

        String iid = data.get(DATASTORES.INTERVIEW_SCHEDULE.IID).toString();

        IInterviewScheduleStore scheduleStore =
            DataStoreRegistry.getInstance().getInterviewScheduleStore();
        InterviewSchedule existingSchedule = scheduleStore.getSchedule(iid);

        String user = data.get(USER.USERNAME).toString();
        Interview interview =
            DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);

        if (interview.getInterviewer().equals(user)) {

          InterviewSchedule newschedule = new InterviewSchedule();
          long date1 = new Long(data.get(DATASTORES.INTERVIEW_SCHEDULE.DATE1).toString());
          long date2 = new Long(data.get(DATASTORES.INTERVIEW_SCHEDULE.DATE2).toString());
          long date3 = new Long(data.get(DATASTORES.INTERVIEW_SCHEDULE.DATE3).toString());

          newschedule.setDate1(date1);
          newschedule.setDate2(date2);
          newschedule.setDate3(date3);
          newschedule.setIid(iid);
          newschedule.setOth_opted(
              new Boolean(data.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTED).toString()));
          newschedule.setOth_option1(
              new Boolean(data.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION1).toString()));
          newschedule.setOth_option2(
              new Boolean(data.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION2).toString()));
          newschedule.setOth_option3(
              new Boolean(data.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION3).toString()));
          newschedule.setFinaldate(
              new Long(data.get(DATASTORES.INTERVIEW_SCHEDULE.FINAL_DATE).toString()));
          newschedule.setFinal_option(
              new Integer(data.get(DATASTORES.INTERVIEW_SCHEDULE.FINAL_OPTION).toString()));
          newschedule.setTime(new Date().getTime());

          if (existingSchedule == null) {
            String scheduleId = scheduleStore.insertSchedule(newschedule);
            if (scheduleId == null)
              res.put("status", "FAIL");
            else
              res.put("status", "SUCCESS");
          } else {
            newschedule.set_id(existingSchedule.get_id());
            if (scheduleStore.updateSchedule(newschedule))
              res.put("status", "SUCCESS");
            else
              res.put("status", "FAIL");
          }
        } else {
          res.put("status", "NOT_ALLOWED");
        }

      }

      else if (SUB_REQ.equals(REQUEST_TYPES.INTERVIEW_SCHEDULE_SUB_REQ.GET_SCHEDULE)) {
        String iid = data.get(DATASTORES.INTERVIEW_SCHEDULE.IID).toString();
        InterviewSchedule schedule =
            DataStoreRegistry.getInstance().getInterviewScheduleStore().getSchedule(iid);
        if (schedule == null) {
          res.put("status", "NULL");
        } else {
          res.put("status", "1");
          res.put(DATASTORES.INTERVIEW_SCHEDULE.ID, schedule.get_id());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.DATE1, schedule.getDate1());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.DATE2, schedule.getDate2());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.DATE3, schedule.getDate3());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.FINAL_DATE, schedule.getFinaldate());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.IID, schedule.getIid());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTED, schedule.getOth_opted());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION1, schedule.getOth_option1());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION2, schedule.getOth_option2());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION3, schedule.getOth_option3());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.TIME, schedule.getTime());
          res.put(DATASTORES.INTERVIEW_SCHEDULE.FINAL_OPTION, schedule.getFinal_option());

        }
      } else if (SUB_REQ.equals(REQUEST_TYPES.INTERVIEW_SCHEDULE_SUB_REQ.SAVE_INTERVIEWEE_OPTION)) {
        String iid = data.get(DATASTORES.INTERVIEW_SCHEDULE.IID).toString();

        IInterviewScheduleStore scheduleStore =
            DataStoreRegistry.getInstance().getInterviewScheduleStore();
        InterviewSchedule existingSchedule = scheduleStore.getSchedule(iid);

        String user = data.get(USER.USERNAME).toString();
        Interview interview =
            DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);

        if (interview.getInterviewee().equals(user)) {
          if (existingSchedule == null)
            res.put("status", "No schedule exist to update.");
          else {
            existingSchedule.setOth_opted(
                new Boolean(data.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTED).toString()));
            existingSchedule.setOth_option1(
                new Boolean(data.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION1).toString()));
            existingSchedule.setOth_option2(
                new Boolean(data.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION2).toString()));
            existingSchedule.setOth_option3(
                new Boolean(data.get(DATASTORES.INTERVIEW_SCHEDULE.OTHER_OPTION3).toString()));
            existingSchedule.setTime(new Date().getTime());

            if (scheduleStore.updateSchedule(existingSchedule))
              res.put("status", "Options saved");
            else
              res.put("status", "FAIL");

          }
        } else
          res.put("status", "NOT_ALLOWED");

      }

    } catch (RemoteException e) {
      e.printStackTrace();
    }

    return res;
  }

}
