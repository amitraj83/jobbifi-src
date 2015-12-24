package com.interview.request.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;

@Service
public class UpdateInterviewFileHandler extends RequestHandler {

  public UpdateInterviewFileHandler() {
    addHandler(this, REQUEST_TYPES.FILESERVER_UPDATE_INTERVIEW_FILE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    System.out.println("File Server Testing succeeded");
    Map<String, Object> res = new HashMap<String, Object>();
    // String docId = data.get(DATASTORES.UPLOAD_FILE.ID).toString();
    // String iid = data.get(VARIABLES.IID).toString();
    //
    // Map<String, Object> changes = new HashMap<String, Object>();
    // changes.put(DATASTORES.UPLOAD_FILE.ARTIFACT_ID, new ObjectId(iid));
    //
    // try {
    // DataStoreRegistry.getInstance().getUploadedFileDataStore().update(new ObjectId(docId),
    // changes);
    // } catch (RemoteException e) {
    // e.printStackTrace();
    // }
    return res;
  }

}
