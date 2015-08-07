package com.interview.request.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.interview.framework.REQUEST_TYPES;

@Service
public class UploadHandler extends RequestHandler {

  public UploadHandler() {
    addHandler(this, REQUEST_TYPES.FILE_UP_DOWN);
  }

  @Override
  public Map<String, Object> handleRequest(Map<Object, Object> data) {
    Map<String, Object> res = new HashMap<String, Object>();
    /*
     * if(data.get("SUB_REQ").toString().equals(REQUEST_TYPES.SAVE_UPLOAD_INFO )) { UploadedFile
     * file = new UploadedFile(); file.setExtension(data.get
     * (DATASTORES.UPLOAD_FILE.EXTENSION).toString()); file.setFileName(data.
     * get(DATASTORES.UPLOAD_FILE.FILENAME).toString()); file.setMimeType(data
     * .get(DATASTORES.UPLOAD_FILE.MIMETYPE).toString());
     * file.setOwner(data.get(DATASTORES.UPLOAD_FILE.OWNER).toString()); file
     * .setPath_on_Disk(data.get(DATASTORES.UPLOAD_FILE.PATH_ON_DISK).toString ()); file.setSize(new
     * Long(data.get(DATASTORES.UPLOAD_FILE.SIZE).toString())); file.setTime(new
     * Long(data.get(DATASTORES.UPLOAD_FILE.TIME).toString()));
     * 
     * ObjectId id = Services.getInstance().getUploadedFileDataStore().saveUploadedFile (file);
     * if(id == null){ res.put("status", 0); } else{ res.put("status", 1); res.put("iod",
     * id.toString()); res.put("fn", file.getFileName());
     * 
     * } } else if(data.get("SUB_REQ").toString().equals(REQUEST_TYPES.GET_UPLOAD_INFO )){ ObjectId
     * id = new ObjectId( data.get("oid").toString()); UploadedFile file =
     * Services.getInstance().getUploadedFileDataStore(). getUploadedFile(id);
     * res.put(DATASTORES.UPLOAD_FILE.EXTENSION, file.getExtension());
     * res.put(DATASTORES.UPLOAD_FILE.FILENAME, file.getFileName());
     * res.put(DATASTORES.UPLOAD_FILE.MIMETYPE, file.getMimeType());
     * res.put(DATASTORES.UPLOAD_FILE.ID, file.getID()); res.put(DATASTORES.UPLOAD_FILE.OWNER,
     * file.getOwner()); res.put(DATASTORES.UPLOAD_FILE.PATH_ON_DISK, file.getPath_on_Disk());
     * res.put(DATASTORES.UPLOAD_FILE.SIZE, file.getSize()); res.put(DATASTORES.UPLOAD_FILE.TIME,
     * file.getTime()); res.put(DATASTORES.UPLOAD_FILE.THUMBNAIL_URL, file.getThumbnailURL());
     * res.put(DATASTORES.UPLOAD_FILE.URL, file.getURL());
     * 
     * 
     * }
     */
    return res;
  }

}
