package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.UploadedFile;
import com.interview.framework.rmi.common.IInterviewDataStore;
import com.interview.rmi.DataStoreRegistry;

public class GetInterviewHandler extends RequestHandler {

  private static final Logger logger = Logger.getLogger(GetInterviewHandler.class);

  public GetInterviewHandler() {
    addHandler(this, REQUEST_TYPES.GET_INTERVIEW);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    try {
      String user = (String) data.get(USER.USERNAME);
      String userRole = (String) data.get(USER.ROLE);
      String SUB_REQ = "";
      if (data.get(REQUEST_TYPES.SUB_REQ) != null)
        SUB_REQ = data.get(REQUEST_TYPES.SUB_REQ).toString();
      IInterviewDataStore iStore = DataStoreRegistry.getInstance().getInterviewDataStore();
      if (SUB_REQ != null
          && SUB_REQ.equals(REQUEST_TYPES.GET_INTERVIEW_SUB_REQ.GET_DISPUTABLE_INTERVIEW)) {
        resMap.put("disputable_list", iStore.getDisputableList(user, userRole));

      } else if (SUB_REQ != null
          && SUB_REQ.equals(REQUEST_TYPES.GET_INTERVIEW_SUB_REQ.GET_INTERVIEW_LIST)) {
        Map<String, Map<String, String>> fileMap = new HashMap<String, Map<String, String>>();
        int pageNumber = new Integer(data.get("pagenum").toString());
        int status = getStatus((String) data.get("status"));
        List<Interview> list =
            iStore.getInterviews(user, DATASTORES.INTERVIEW.INTERVIEWEE, pageNumber, status);
        if (list != null) {
          Iterator it = list.iterator();
          while (it.hasNext()) {
            Interview interview = (Interview) it.next();
            logger.info("Interview ID : " + interview.getId());
            interview.setBidcount(
                DataStoreRegistry.getInstance().getBidStore().getBidCount(interview.getId()));
            logger.info("Bid Count : " + interview.getBidcount());
          }
        }

        resMap.put(VARIABLES.MY_INTERVIEW_AS_INTERVIEWEE, list);
        resMap.put("MY_INTERVIEW_AS_INTERVIEWEE_COUNT",
            DataStoreRegistry.getInstance().getInterviewDataStore().getTotalinterviewcount(user,
                DATASTORES.INTERVIEW.INTERVIEWEE, status));
        populateFileMap(fileMap, list);


        list = iStore.getInterviews(user, DATASTORES.INTERVIEW.INTERVIEWER);
        resMap.put(VARIABLES.MY_INTERVIEW_AS_INTERVIEWER, list);
        populateFileMap(fileMap, list);

        list = iStore.getInterviewsWhereIBid(user);
        if (list != null) {
          Iterator it = list.iterator();
          while (it.hasNext()) {
            Interview interview = (Interview) it.next();
            interview.setPrice(DataStoreRegistry.getInstance().getBidStore()
                .getAcceptedBidForInterview(interview.getId()).getPrice());

          }
        }
        resMap.put(VARIABLES.INTERVIEWS_WHERE_I_BID, list);
        populateFileMap(fileMap, list);

        resMap.put("FILEMAP", fileMap);
      }

      else {
        Map<String, Map<String, String>> fileMap = new HashMap<String, Map<String, String>>();
        List<Interview> list = iStore.getInterviews(user, DATASTORES.INTERVIEW.INTERVIEWEE);
        if (list != null) {
          Iterator it = list.iterator();
          while (it.hasNext()) {
            Interview interview = (Interview) it.next();
            logger.info("Interview ID : " + interview.getId());
            interview.setBidcount(
                DataStoreRegistry.getInstance().getBidStore().getBidCount(interview.getId()));
            logger.info("Bid Count : " + interview.getBidcount());
          }
        }

        resMap.put(VARIABLES.MY_INTERVIEW_AS_INTERVIEWEE, list);
        populateFileMap(fileMap, list);


        list = iStore.getInterviews(user, DATASTORES.INTERVIEW.INTERVIEWER);
        resMap.put(VARIABLES.MY_INTERVIEW_AS_INTERVIEWER, list);
        populateFileMap(fileMap, list);

        list = iStore.getInterviewsWhereIBid(user);
        if (list != null) {
          Iterator it = list.iterator();
          while (it.hasNext()) {
            Interview interview = (Interview) it.next();
            interview.setPrice(DataStoreRegistry.getInstance().getBidStore()
                .getAcceptedBidForInterview(interview.getId()).getPrice());

          }
        }
        resMap.put(VARIABLES.INTERVIEWS_WHERE_I_BID, list);
        populateFileMap(fileMap, list);

        resMap.put("FILEMAP", fileMap);
      }
    } catch (RemoteException e) {

      e.printStackTrace();
    }
    return resMap;
  }


  private void populateFileMap(Map<String, Map<String, String>> fileMap, List<Interview> list) {
    try {
      for (Interview interview : list) {
        if (interview.getFile() != null && !interview.getFile().trim().equals("")) {
          ObjectId fileID = new ObjectId(interview.getFile());
          UploadedFile uploadedFile =
              DataStoreRegistry.getInstance().getUploadedFileDataStore().getUploadedFile(fileID);
          if (uploadedFile != null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN, uploadedFile.getOriginalFileName());
            map.put(DATASTORES.UPLOAD_FILE.URL, uploadedFile.getURL());
            fileMap.put(interview.getId(), map);
          }
        }
      }
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public int getStatus(String status) {
    if (status.equals("OPEN")) {
      return 0;
    } else if (status.equals("IN PROGRESS")) {
      return 1;
    }

    else if (status.equals("APPROVAL PENDING")) {
      return 4;
    } else if (status.equals("COMPLETED")) {
      return 5;
    } else if (status.equals("CANCELLED")) {
      return 6;
    } else if (status.equals("REPOSTED")) {
      return 7;
    } else if (status.equals("USER RATED")) {
      return 8;
    } else if (status.equals("DISPUTE")) {
      return 9;
    }
    return 10;

  }
}
