package com.interview.request.handlers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.VARIABLES;
import com.interview.rmi.DataStoreRegistry;


@Service
public class UpdateBidFileHandler extends RequestHandler {


  public UpdateBidFileHandler() {
    addHandler(this, REQUEST_TYPES.FILESERVER_UPDATE_BID_FILE);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> res = new HashMap<String, Object>();

    String docId = data.get(VARIABLES.Bid.BID_FID).toString();
    String bidid = data.get(VARIABLES.Bid.BID_ID).toString();

    Map<String, Object> changes = new HashMap<String, Object>();
    changes.put(DATASTORES.UPLOAD_FILE.ARTIFACT_ID, new ObjectId(bidid));

    try {
      DataStoreRegistry.getInstance().getUploadedFileDataStore().update(new ObjectId(docId),
          changes);
    } catch (RemoteException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // res.put("status", 1);
    return res;
  }

}
