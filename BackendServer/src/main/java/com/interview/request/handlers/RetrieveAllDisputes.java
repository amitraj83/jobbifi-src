package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.Dispute;
import com.interview.framework.pojo.Interview;
import com.interview.rmi.DataStoreRegistry;

@Service
public class RetrieveAllDisputes extends RequestHandler {

  @Autowired
  private Properties myProps;

  public RetrieveAllDisputes() {
    addHandler(this, REQUEST_TYPES.DISPUTES_RETRIEVE_ALL);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    String username = data.get(USER.USERNAME).toString();

    try {
      List<Interview> list = DataStoreRegistry.getInstance().getInterviewDataStore()
          .getInterviews(username, DATASTORES.INTERVIEW.INTERVIEWEE);
      List<Interview> list2 = DataStoreRegistry.getInstance().getInterviewDataStore()
          .getInterviews(username, DATASTORES.INTERVIEW.INTERVIEWER);
      list.addAll(list2);

      Map<String, String> tempITitles = new HashMap<String, String>();
      List<String> disputeInterviewIds = new ArrayList<String>();
      for (Interview interview : list) {
        disputeInterviewIds.add(interview.getId());
        tempITitles.put(interview.getId(), interview.getTitle());
      }

      List<Dispute> disputes =
          DataStoreRegistry.getInstance().getDisputeStore().getAllDisputes(disputeInterviewIds);
      List<Map<String, String>> resdata = new ArrayList<Map<String, String>>();
      for (Dispute dispute : disputes) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ititle", tempITitles.get(dispute.getIID()));
        map.put("did", dispute.getId());
        map.put("dstatus", dispute.getStatus());
        map.put("vid", dispute.getVisibleID() == null ? "" : dispute.getVisibleID());
        map.put("dwith", dispute.getWith() == null ? "" : dispute.getWith());
        map.put("amount", dispute.getAmount() + "");
        map.put("result", dispute.getResult());
        map.put("closedtime", dispute.getTimeclosed() + "");
        map.put("createdby", dispute.getCreatedBy());

        resdata.add(map);
      }
      resMap.put("data", resdata);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return resMap;
  }

}
