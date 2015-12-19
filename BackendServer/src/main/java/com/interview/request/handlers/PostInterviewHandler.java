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
import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

public class PostInterviewHandler extends RequestHandler {

  public PostInterviewHandler() {
    addHandler(this, REQUEST_TYPES.POST_INTERVIEW);
  }

  @Override
  public Map<String, Object> handleRequest(final Map<Object, Object> data) {

    final Interview interview = new Interview();
    interview.setTitle((String) data.get(VARIABLES.POST_INTERVIEW.TITLE));
    interview.setInterviewee((String) data.get(VARIABLES.POST_INTERVIEW.INTERVIEWEE));
    interview.setInterviewer((String) data.get(VARIABLES.POST_INTERVIEW.INTERVIEWER));

    interview.setBudget((String) data.get(VARIABLES.POST_INTERVIEW.BUDGET));
    interview.setExperience((String) data.get(VARIABLES.POST_INTERVIEW.EXPERIENCE));
    interview.setIndustry((String) data.get(VARIABLES.POST_INTERVIEW.INDUSTRY));

    if (data.get(DATASTORES.INTERVIEW.FILE) != null)
      interview.setFile((String) data.get(DATASTORES.INTERVIEW.FILE));

    List<String> skillsList = new ArrayList<String>();
    String skillsString = (String) data.get(VARIABLES.POST_INTERVIEW.SKILLS);
    String[] skillsArray = skillsString.split(",");
    for (String skill : skillsArray) {
      skillsList.add(skill);
    }
    interview.setSkills(skillsList);
    interview.setDescription((String) data.get(VARIABLES.POST_INTERVIEW.DESCRIPTION));
    interview.setDt(new Date().getTime());
    interview.setStatus(INTERVIEW_STATUS.PENDING);

    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      final ObjectId id =
          DataStoreRegistry.getInstance().getInterviewDataStore().saveInterview(interview);

      interview.setId(id.toString());

      if (id != null) {
        resMap.put("iid", id.toString());
        Services.getInstance().getNotificationService().processNotification(interview,
            VARIABLES.NOTIFICATION.TYPE.NEW_INTERVIEW_NOTIFICATION);
        resMap.put("status", 1);
        Map<AttributeType, String> params = new HashMap<AttributeType, String>();
        params.put(AttributeType.USER_NAME,
            (String) data.get(VARIABLES.POST_INTERVIEW.INTERVIEWEE));
        params.put(AttributeType.INTERVIEW_TITLE,
            (String) data.get(VARIABLES.POST_INTERVIEW.TITLE));
        params.put(AttributeType.USER_SKILLS, skillsString);
        params.put(AttributeType.USER_EXPERIENCE,
            (String) data.get(VARIABLES.POST_INTERVIEW.EXPERIENCE));
        params.put(AttributeType.INTERVIEW_URL,
            (String) data.get("baseURL") + "/interviewdetail.do?iid=" + id.toString());
        List<String> receivers = DataStoreRegistry.getInstance().getInterviewerDataStore()
            .getMatchingUsersList(interview.getSkills(), null, null);
        // TODO: Pending
        // Need to identify that can we send the email multiple user with this functionality?
        // We also find the advisor according to skills
        for (String recipient : receivers) {
          Services.getInstance().getEmailService().sendMail(Mailer.EmailType.NEW_MOCK_INTERVIEW,
              params, recipient);
        }
        // Services.getInstance().getEmailService().sendMailChannelOnEvent("4", params, receivers,
        // "Your Jobbify Password has been changed!");

      } else
        resMap.put("status", -1);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return resMap;
  }

}
