package com.interview.request.handlers;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
public class SaveBidFileHandler extends RequestHandler {

  @Autowired
  private Properties myProps;


  public SaveBidFileHandler() {
    addHandler(this, REQUEST_TYPES.FILESERVER_SAVE_BID_FILE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> resMap = new HashMap<String, Object>();

    String owner = data.get(USER.USERNAME).toString();
    ObjectId _id = new ObjectId();
    String extension = data.get(DATASTORES.UPLOAD_FILE.EXTENSION).toString();
    String fileNameWithExtension = _id + "." + extension;
    byte[] is = (byte[]) data.get("IS");
    int status =
        Services.getInstance().getFileUtilities()
            .copyFile(owner, is, myProps.getProperty("bidDocDir"), fileNameWithExtension);
    if (status == 1) {
      UploadedFile file = new UploadedFile();
      // file.setArtifactid(data.get(DATASTORES.UPLOAD_FILE.ARTIFACT_ID).toString());
      file.setClasstype(data.get(DATASTORES.UPLOAD_FILE.CLASSIFICATION).toString());
      file.setExtension(data.get(DATASTORES.UPLOAD_FILE.EXTENSION).toString());
      file.setFileName(fileNameWithExtension);
      file.setID(_id.toString());
      file.setMimeType(data.get(DATASTORES.UPLOAD_FILE.MIMETYPE).toString());
      file.setOriginalFileName(data.get(DATASTORES.UPLOAD_FILE.ORIGINAL_FN).toString());
      file.setOwner(owner);
      file.setPath_on_Disk(owner + File.separator + _id + "." + extension);
      file.setSize(new Long(data.get(DATASTORES.UPLOAD_FILE.SIZE).toString()));
      file.setThumbnailURL("");
      file.setTime(new Date().getTime());
      file.setURL("aauth/bidfiles/bidfiledownload.do?fileid=" + _id);

      ObjectId oid = null;
      try {
        oid = DataStoreRegistry.getInstance().getUploadedFileDataStore().saveUploadedFile(file);
      } catch (RemoteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      if (oid != null) {
        resMap.put(DATASTORES.UPLOAD_FILE.URL, file.getURL());
        resMap.put(DATASTORES.UPLOAD_FILE.ID, _id.toString());
      }
    }
    return resMap;
  }


}
