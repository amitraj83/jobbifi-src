package com.interview.request.handlers;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.UploadedFile;
import com.interview.rmi.DataStoreRegistry;
import com.interview.services.Services;

@Service
public class JobApplicationFileHandler extends RequestHandler {

  @Autowired
  private Properties myProps;

  private static final Logger logger = Logger.getLogger(JobApplicationFileHandler.class);

  public JobApplicationFileHandler() {
    addHandler(this, REQUEST_TYPES.FILESERVER_JOB_APPLICATION_FILE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {

    Map<String, Object> resMap = new HashMap<String, Object>();
    String subReq = (String) data.get(REQUEST_TYPES.SUB_REQ);

    if (null != subReq && REQUEST_TYPES.FILESERVER_SAVE_JOB_APPLICATION_FILE.equals(subReq)) {

      byte[] is = ((String) data.get("IS")).getBytes();
      String extension = data.get("EXT").toString();
      logger.info("Uploading job doc for user : " + data.get(USER.USERNAME));

      if (data.get(USER.USERNAME) != null) {
        String username = data.get(USER.USERNAME).toString();
        String jobId = (String) data.get(DATASTORES.JOB_APPLICATION.JOB_ID);
        String id = new ObjectId().toString();
        String fileNameWithExtension = id + "." + extension;
        Services.getInstance().getFileUtilities().copyFile(username, is,
            myProps.getProperty("jobApplicationDocDir"), fileNameWithExtension);

        UploadedFile uploadFileDAO = new UploadedFile();
        uploadFileDAO.setID(id);
        uploadFileDAO.setClasstype(DATASTORES.UPLOAD_FILE.CLASS_TYPE.JOB_APPLICATION_DOCUMENT);
        uploadFileDAO.setExtension(extension);
        uploadFileDAO.setFileName(id + "." + extension);
        uploadFileDAO.setMimeType(data.get(DATASTORES.UPLOAD_FILE.MIMETYPE).toString());
        uploadFileDAO.setOwner(username);
        uploadFileDAO.setPath_on_Disk(username + File.separator + id + "." + extension);
        uploadFileDAO.setSize(new Long(data.get(DATASTORES.UPLOAD_FILE.SIZE).toString()));
        uploadFileDAO.setThumbnailURL("");
        uploadFileDAO.setTime(new Date().getTime());
        uploadFileDAO.setURL("aauth/jobapplication/filedownload.do?fileid=" + id);
        uploadFileDAO.setOriginalFileName(data.get(DATASTORES.UPLOAD_FILE.ORIGINAL_FN).toString());
        uploadFileDAO.setArtifactid(jobId);
        ObjectId _id = null;
        try {
          _id = DataStoreRegistry.getInstance().getUploadedFileDataStore()
              .saveUploadedFile(uploadFileDAO);
        } catch (RemoteException e) {
          e.printStackTrace();
        }

        if (_id != null) {
          resMap.put(DATASTORES.UPLOAD_FILE.URL, uploadFileDAO.getURL());
          resMap.put(DATASTORES.UPLOAD_FILE.ID, _id.toString());
        }

      }
    }
    return resMap;
  }
}
