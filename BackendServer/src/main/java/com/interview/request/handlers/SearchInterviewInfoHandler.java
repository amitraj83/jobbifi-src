package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;

import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.UploadedFile;
import com.interview.rmi.DataStoreRegistry;

@Service
public class SearchInterviewInfoHandler extends RequestHandler {

  private Logger logger = Logger.getLogger(SearchAdditionalDataHandler.class);

  public SearchInterviewInfoHandler() {
    addHandler(this, REQUEST_TYPES.SEARCH_INTERVIEW_INFO);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();


    String subReq = (String) data.get(REQUEST_TYPES.SUB_REQ);
    if (null != subReq
        && REQUEST_TYPES.SEARCH_INTERVIEW_SUB_INFO.SEARCH_INTERVIEW_SUB_INFO.equals(subReq)) {
      String id = data.get("ID").toString();
      try {

        Interview userinfo =
            DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(id);
        resMap.put("budget", userinfo.getBudget());
        resMap.put("description", userinfo.getDescription());
        resMap.put("experience", userinfo.getExperience());
        resMap.put("dated", userinfo.getDt());
        resMap.put("title", userinfo.getTitle());
        resMap.put("industry", userinfo.getIndustry());
        resMap.put("interviewee", userinfo.getInterviewee());
        resMap.put("skills", userinfo.getSkills());
        resMap.put("iid", id);
        resMap.put("status", userinfo.getStatus());
        resMap.put("statusString", userinfo.getStatusString());

        Map<String, Object> idata = DataStoreRegistry.getInstance().getInterviewerDataStore()
            .getUserInfo(userinfo.getInterviewee());
        resMap.put("profilepic", idata.get(USER.PROFILE_PIC));

      } catch (Exception e) {
        logger.error("Exception : ", e);
      }

    } else {

      String username =
          (data.get(USER.USERNAME) != null) ? data.get(USER.USERNAME).toString() : null;
      Set<String> iids = (Set<String>) data.get("iids");
      Set<String> usernames = (Set<String>) data.get("usernames");

      try {

        Map<String, String> userPicMap =
            DataStoreRegistry.getInstance().getInterviewerDataStore().getPics(usernames);
        resMap.put("pics", userPicMap);

        Map<String, String> iidFileMap =
            DataStoreRegistry.getInstance().getInterviewDataStore().getInterviewsFiles(iids);
        Iterator<String> it = iidFileMap.keySet().iterator();
        Map<String, Object> iidFiles = new HashMap<String, Object>();
        while (it.hasNext()) {
          String key = it.next();
          String value = iidFileMap.get(key);
          if (null != value && !"".equals(value)) {
            UploadedFile uploadedFile = DataStoreRegistry.getInstance().getUploadedFileDataStore()
                .getUploadedFile(new ObjectId(value));
            Map<String, Object> uploadedFileMap = new HashMap<String, Object>();
            if (null != uploadedFile) {
              uploadedFileMap.put(DATASTORES.UPLOAD_FILE.URL, uploadedFile.getURL());
              uploadedFileMap.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN,
                  uploadedFile.getOriginalFileName());
            } else {
              uploadedFileMap.put(DATASTORES.UPLOAD_FILE.URL, null);
              uploadedFileMap.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN, null);
            }
            iidFiles.put(key, uploadedFileMap);
          }
        }
        resMap.put("iidFiles", iidFiles);

        Map<String, Object> iidBids = new HashMap<String, Object>();
        if (null != username) {
          List<Bid> bids = DataStoreRegistry.getInstance().getBidStore()
              .getBidsForInterviewsByUser(username, iids);
          for (Bid bid : bids) {
            iidBids.put(bid.getIid(), bid);
          }
        }
        resMap.put("iidBids", iidBids);

      } catch (RemoteException e) {
        logger.error("Remote Exception : ", e);
      }
    }
    return resMap;
  }

}
