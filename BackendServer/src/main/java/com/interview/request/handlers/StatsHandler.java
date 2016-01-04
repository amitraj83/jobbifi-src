package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;
import com.interview.rmi.DataStoreRegistry;

@Service
public class StatsHandler extends RequestHandler {

  public StatsHandler() {
    addHandler(this, REQUEST_TYPES.STATS);
  }


  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();

    try {
      long countJobs = DataStoreRegistry.getInstance().getJobStore().getJobsCount();

      long candidateCount =
          DataStoreRegistry.getInstance().getInterviewerDataStore().getCandidatesCount();

      long interviewerCount =
          DataStoreRegistry.getInstance().getInterviewerDataStore().getInterviewerCount();

      resMap.put("jobs", countJobs);
      resMap.put("candidates", candidateCount);
      resMap.put("employers", interviewerCount);


    } catch (RemoteException e) {
      e.printStackTrace();
    }


    return resMap;
  }



}
