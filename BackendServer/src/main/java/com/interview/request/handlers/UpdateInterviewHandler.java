package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.INTERVIEW_STATUS;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class UpdateInterviewHandler extends RequestHandler {

  public UpdateInterviewHandler() {
    addHandler(this, REQUEST_TYPES.UPDATE_INTERVIEW);
  }

  @Override
  public Map<String, Object> handleRequest(final Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    Map<String, Object> resultMap = null;
    resMap.put(VARIABLES.POST_INTERVIEW.TITLE, (String) data.get(VARIABLES.POST_INTERVIEW.TITLE));
    // interview.setInterviewee((String) data.get(VARIABLES.POST_INTERVIEW.INTERVIEWEE));
    // interview.setInterviewer((String) data.get(VARIABLES.POST_INTERVIEW.INTERVIEWER));

    resMap.put(VARIABLES.POST_INTERVIEW.BUDGET, (String) data.get(VARIABLES.POST_INTERVIEW.BUDGET));
    resMap.put(VARIABLES.POST_INTERVIEW.EXPERIENCE,
        (String) data.get(VARIABLES.POST_INTERVIEW.EXPERIENCE));
    resMap.put(VARIABLES.POST_INTERVIEW.INDUSTRY,
        (String) data.get(VARIABLES.POST_INTERVIEW.INDUSTRY));

    List<String> skillsList = new ArrayList<String>();
    String skillsString = (String) data.get(VARIABLES.POST_INTERVIEW.SKILLS);
    String[] skillsArray = skillsString.split(",");
    for (String skill : skillsArray) {
      skillsList.add(skill);
    }
    resMap.put(VARIABLES.POST_INTERVIEW.SKILLS, skillsList);
    resMap.put(VARIABLES.POST_INTERVIEW.DESCRIPTION,
        (String) data.get(VARIABLES.POST_INTERVIEW.DESCRIPTION));
    // interview.setDt(new Date().getTime());
    // interview.setStatus(INTERVIEW_STATUS.PENDING);

    try {
      // final ObjectId id =
      // DataStoreRegistry.getInstance().getInterviewDataStore().saveInterview(interview);

      DataStoreRegistry.getInstance().getInterviewDataStore()
          .updateInterview(new ObjectId((String) data.get(VARIABLES.IID)), resMap);
      resultMap = new HashMap<String, Object>();
      resultMap.put("message", "Success");

      resultMap.put("code", "0");

    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return resultMap;
  }

}
