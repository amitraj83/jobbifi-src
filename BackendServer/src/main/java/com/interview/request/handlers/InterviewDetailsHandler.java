package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.UploadedFile;
import com.interview.rmi.DataStoreRegistry;

@Service
public class InterviewDetailsHandler extends RequestHandler {

  public InterviewDetailsHandler() {
    addHandler(this, REQUEST_TYPES.INTERVIEW_DETAILS);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      ObjectId iid = new ObjectId(data.get(VARIABLES.IID).toString());
      Interview interview =
          DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);

      resMap.put(DATASTORES.INTERVIEW.DATE, interview.getDt());
      resMap.put(DATASTORES.INTERVIEW.DESCRIPTION, interview.getDescription());
      resMap.put(DATASTORES.INTERVIEW.ESCROW_BALANCE, interview.getEb());
      resMap.put(DATASTORES.INTERVIEW.INTERVIEWEE, interview.getInterviewee());
      resMap.put(DATASTORES.INTERVIEW.INTERVIEWER, interview.getInterviewer());
      resMap.put(DATASTORES.INTERVIEW.SKILLS, interview.getSkills());
      resMap.put(DATASTORES.INTERVIEW.STATUS, interview.getStatus());
      resMap.put(DATASTORES.INTERVIEW.TITLE, interview.getTitle());
      resMap.put(DATASTORES.INTERVIEW.STATUS_STRING, interview.getStatusString());
      if (null != interview.getFile() && !interview.getFile().equals("")) {
        UploadedFile file = DataStoreRegistry.getInstance().getUploadedFileDataStore()
            .getUploadedFile(new ObjectId(interview.getFile()));
        if (file != null) {
          resMap.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN, file.getOriginalFileName());
          resMap.put(DATASTORES.UPLOAD_FILE.URL, file.getURL());
        }
      }


      if (interview.getBudget() != null)
        resMap.put(DATASTORES.INTERVIEW.BUDGET, interview.getBudget());
      if (interview.getExperience() != null)
        resMap.put(DATASTORES.INTERVIEW.EXPERIENCE, interview.getExperience());
      if (interview.getIndustry() != null)
        resMap.put(DATASTORES.INTERVIEW.INDUSTRY, interview.getIndustry());

    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return resMap;
  }

}
