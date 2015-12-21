package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.types.ObjectId;

import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
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
    try {

      DataStoreRegistry.getInstance().getInterviewDataStore()
          .updateInterview(new ObjectId((String) data.get(VARIABLES.IID)), resMap);
      resultMap = new HashMap<String, Object>();
      resultMap.put("message", "Success");
      resultMap.put("code", "0");

      Map<AttributeType, String> params = new HashMap<AttributeType, String>();
      params.put(AttributeType.INTERVIEW_URL, (String) data.get("baseURL")
          + "/interviewdetail.do?iid=" + (String) data.get(VARIABLES.IID));
      List<String> users = DataStoreRegistry.getInstance().getBidStore()
          .getBidderForInterview((String) data.get(VARIABLES.IID));
      if (users != null) {
        Map<String, String> getEmailListFromUsersList = DataStoreRegistry.getInstance()
            .getInterviewerDataStore().getEmailListFromUsersList(users);
        for (Entry<String, String> obj : getEmailListFromUsersList.entrySet()) {
          Services.getInstance().getEmailService().sendMail(Mailer.EmailType.MOCK_INTERVIEW_UPDATED,
              params, obj.getValue());
        }
        // Services.getInstance().getEmailService().sendMailChannelOnEvent("50", param, receivers,
        // "Recent Changes to Mock Interview ! "
        // + (String) data.get(VARIABLES.POST_INTERVIEW.TITLE));
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return resultMap;
  }

}
