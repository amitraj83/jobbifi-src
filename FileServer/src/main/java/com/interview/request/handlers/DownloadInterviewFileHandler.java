package com.interview.request.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.Bid;
import com.interview.framework.pojo.Dispute;
import com.interview.framework.pojo.Interview;
import com.interview.framework.pojo.Job;
import com.interview.framework.pojo.UploadedFile;
import com.interview.rmi.DataStoreRegistry;

@Service
public class DownloadInterviewFileHandler extends RequestHandler {

  @Autowired
  private Properties myProps;


  public DownloadInterviewFileHandler() {
    addHandler(this, REQUEST_TYPES.FILESERVER_DOWNLOAD_INTERVIEW_FILE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();
    String fileID = data.get(VARIABLES.ENCRYPTED_FILE_ID).toString();
    UploadedFile uploadedFile = null;
    try {
      uploadedFile = DataStoreRegistry.getInstance().getUploadedFileDataStore()
          .getUploadedFile(new ObjectId(fileID));
    } catch (RemoteException e1) {
      e1.printStackTrace();
    }

    String accessgingUser = data.get(USER.USERNAME).toString();
    if (uploadedFile != null) {
      try {
        if (isUserAllowed(accessgingUser, uploadedFile)) {
          String filePath = "";
          if (uploadedFile.getClasstype()
              .equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.INTERVIEW_DOCUMENT)) {
            filePath = myProps.getProperty("interviewDocDir") + uploadedFile.getPath_on_Disk();

          } else if (uploadedFile.getClasstype()
              .equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.BID_DOCUMENT)) {
            filePath = myProps.getProperty("bidDocDir") + uploadedFile.getPath_on_Disk();

          } else if (uploadedFile.getClasstype()
              .equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.CHAT_DOCUMENT)) {
            filePath = myProps.getProperty("chatDocDir") + uploadedFile.getPath_on_Disk();

          } else if (uploadedFile.getClasstype()
              .equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.DISPUTE_DOCUMENT)) {
            filePath = myProps.getProperty("disputeDocDir") + uploadedFile.getPath_on_Disk();

          } else if (uploadedFile.getClasstype()
              .equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.JOB_DOCUMENT)) {
            filePath = myProps.getProperty("jobDocDir") + uploadedFile.getPath_on_Disk();
          } else if (uploadedFile.getClasstype()
              .equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.JOB_APPLICATION_DOCUMENT)) {
            filePath = myProps.getProperty("jobApplicationDocDir") + uploadedFile.getPath_on_Disk();
          }

          InputStream is = new FileInputStream(new File(filePath));
          resMap.put("BYTES", new String(IOUtils.toByteArray(is)));
          resMap.put(DATASTORES.UPLOAD_FILE.MIMETYPE, uploadedFile.getMimeType());
          resMap.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN, uploadedFile.getOriginalFileName());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return resMap;
  }

  private boolean isUserAllowed(String accessgingUser, UploadedFile uploadedFile) {

    if (uploadedFile.getClasstype().equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.INTERVIEW_DOCUMENT)) {
      return true;
    } else if (uploadedFile.getClasstype().equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.JOB_DOCUMENT)) {
      return true;
    } else if (uploadedFile.getClasstype()
        .equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.CHAT_DOCUMENT)) {
      if (accessgingUser.equals(uploadedFile.getArtifactid()))
        return true;
      else if (accessgingUser.equals(uploadedFile.getOwner()))
        return true;
    } else if (uploadedFile.getClasstype().equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.BID_DOCUMENT)) {
      if (accessgingUser.equals(uploadedFile.getOwner()))
        return true;

      Interview interview = null;
      try {
        Bid bid = DataStoreRegistry.getInstance().getBidStore()
            .getBid(new ObjectId(uploadedFile.getArtifactid()));

        interview = DataStoreRegistry.getInstance().getInterviewDataStore()
            .getInterview(new ObjectId(bid.getIid()));
      } catch (RemoteException e) {
        e.printStackTrace();
      }

      if (accessgingUser.equals(interview.getInterviewee()))
        return true;
    } else if (uploadedFile.getClasstype()
        .equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.DISPUTE_DOCUMENT)) {
      if (accessgingUser.equals(uploadedFile.getOwner()))
        return true;

      String artifactid = uploadedFile.getArtifactid();
      if (artifactid != null && !artifactid.trim().equals("")) {
        try {
          Dispute dispute = DataStoreRegistry.getInstance().getDisputeDataStore()
              .getDispute(new ObjectId(artifactid));
          String iid = dispute.getIID();
          Interview interview =
              DataStoreRegistry.getInstance().getInterviewDataStore().getInterview(iid);
          if (accessgingUser.equals(interview.getInterviewee())
              || accessgingUser.equals(interview.getInterviewer()))
            return true;
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }
    } else if (uploadedFile.getClasstype()
        .equals(DATASTORES.UPLOAD_FILE.CLASS_TYPE.JOB_APPLICATION_DOCUMENT)) {
      if (accessgingUser.equals(uploadedFile.getOwner())) {
        return true;
      } else {
        try {
          Job job =
              DataStoreRegistry.getInstance().getJobStore().getJob(uploadedFile.getArtifactid());
          if (job.getInterviewer().equals(accessgingUser)) {
            return true;
          }
        } catch (RemoteException e) {
          e.printStackTrace();
        }
        return false;
      }

    }
    return false;
  }

}
