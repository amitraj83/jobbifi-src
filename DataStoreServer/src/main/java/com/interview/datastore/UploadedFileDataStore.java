package com.interview.datastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.DATASTORES;
import com.interview.framework.pojo.UploadedFile;
import com.interview.framework.rmi.common.IUploadedFileDataStore;
import com.interview.services.Services;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

// @Service("")
public class UploadedFileDataStore extends UnicastRemoteObject implements IUploadedFileDataStore {

  /**
   * 
   */
  private static final long serialVersionUID = 4788714828024643867L;

  protected UploadedFileDataStore() throws RemoteException {
    Services.getInstance().getRMIServer().bind(NAME, this);
  }

  /*
   * public UploadedFile getUploadedFile(ObjectId _id) throws RemoteException{ DBCollection
   * collection =
   * Services.getInstance().getBaseDataStore().db.getCollection(DATASTORES.UPLOAD_FILE.COLLECTION);
   * 
   * DBObject query = new BasicDBObject(DATASTORES.UPLOAD_FILE.ID, _id); DBCursor cursor =
   * collection.find(query); if(cursor.hasNext()){ DBObject row = cursor.next();
   * 
   * UploadedFile file = new UploadedFile();
   * file.setExtension(row.get(DATASTORES.UPLOAD_FILE.EXTENSION).toString());
   * file.setFileName(row.get(DATASTORES.UPLOAD_FILE.FILENAME).toString());
   * file.setID(row.get(DATASTORES.UPLOAD_FILE.ID).toString());
   * file.setMimeType(row.get(DATASTORES.UPLOAD_FILE.MIMETYPE).toString());
   * file.setOwner(row.get(DATASTORES.UPLOAD_FILE.OWNER).toString());
   * file.setPath_on_Disk(row.get(DATASTORES.UPLOAD_FILE.PATH_ON_DISK).toString()); file.setSize(new
   * Long(row.get(DATASTORES.UPLOAD_FILE.SIZE).toString()));
   * file.setThumbnailURL(row.get(DATASTORES.UPLOAD_FILE.THUMBNAIL_URL).toString());
   * file.setTime(new Long(row.get(DATASTORES.UPLOAD_FILE.TIME).toString()));
   * file.setURL(row.get(DATASTORES.UPLOAD_FILE.URL).toString());
   * file.setOriginalFileName(row.get(DATASTORES.UPLOAD_FILE.ORIGINAL_FN).toString()); return file;
   * } return null; }
   */
  public UploadedFile getUploadedFile(ObjectId _id) {
    DBCollection collection = Services.getInstance().getBaseDataStore().db
        .getCollection(DATASTORES.UPLOAD_FILE.COLLECTION);

    DBObject query = new BasicDBObject(DATASTORES.UPLOAD_FILE.ID, _id);
    DBCursor cursor = collection.find(query);
    if (cursor.hasNext()) {
      DBObject row = cursor.next();

      UploadedFile file = new UploadedFile();
      file.setExtension(row.get(DATASTORES.UPLOAD_FILE.EXTENSION).toString());
      file.setFileName(row.get(DATASTORES.UPLOAD_FILE.FILENAME).toString());
      file.setID(row.get(DATASTORES.UPLOAD_FILE.ID).toString());
      file.setMimeType(row.get(DATASTORES.UPLOAD_FILE.MIMETYPE).toString());
      file.setOwner(row.get(DATASTORES.UPLOAD_FILE.OWNER).toString());
      file.setPath_on_Disk(row.get(DATASTORES.UPLOAD_FILE.PATH_ON_DISK).toString());
      file.setSize(new Long(row.get(DATASTORES.UPLOAD_FILE.SIZE).toString()));
      file.setThumbnailURL(row.get(DATASTORES.UPLOAD_FILE.THUMBNAIL_URL).toString());
      file.setTime(new Long(row.get(DATASTORES.UPLOAD_FILE.TIME).toString()));
      file.setURL(row.get(DATASTORES.UPLOAD_FILE.URL).toString());
      file.setOriginalFileName(row.get(DATASTORES.UPLOAD_FILE.ORIGINAL_FN).toString());
      if (row.get(DATASTORES.UPLOAD_FILE.ARTIFACT_ID) != null)
        file.setArtifactid(row.get(DATASTORES.UPLOAD_FILE.ARTIFACT_ID).toString());
      else
        file.setArtifactid("");
      file.setClasstype(row.get(DATASTORES.UPLOAD_FILE.CLASSIFICATION).toString());
      return file;
    } else
      return null;
  }


  public String getURL(ObjectId _id) throws RemoteException {
    DBCollection collection = Services.getInstance().getBaseDataStore().db
        .getCollection(DATASTORES.UPLOAD_FILE.COLLECTION);

    DBObject query = new BasicDBObject(DATASTORES.UPLOAD_FILE.ID, _id);
    DBCursor cursor = collection.find(query);
    if (cursor.hasNext()) {
      DBObject row = cursor.next();
      return row.get(DATASTORES.UPLOAD_FILE.URL).toString();
    }
    return "";
  }

  public void update(ObjectId _id, Map<String, Object> changes) {
    DBCollection collection = Services.getInstance().getBaseDataStore().db
        .getCollection(DATASTORES.UPLOAD_FILE.COLLECTION);
    DBObject query = new BasicDBObject();
    query.put(DATASTORES.UPLOAD_FILE.ID, _id);

    BasicDBObject updateDoc = new BasicDBObject();
    Iterator<String> it = changes.keySet().iterator();
    while (it.hasNext()) {
      String key = it.next();
      if (key.equals(DATASTORES.UPLOAD_FILE.ARTIFACT_ID))
        updateDoc.append(DATASTORES.UPLOAD_FILE.ARTIFACT_ID,
            changes.get(DATASTORES.UPLOAD_FILE.ARTIFACT_ID));
    }
    BasicDBObject update = new BasicDBObject().append("$set", updateDoc);
    collection.update(query, update);
  }

  public ObjectId saveUploadedFile(UploadedFile file) {
    DBCollection collection = Services.getInstance().getBaseDataStore().db
        .getCollection(DATASTORES.UPLOAD_FILE.COLLECTION);



    DBObject doc = new BasicDBObject();
    doc.put(DATASTORES.UPLOAD_FILE.ID, new ObjectId(file.getID()));
    doc.put(DATASTORES.UPLOAD_FILE.OWNER, file.getOwner());
    doc.put(DATASTORES.UPLOAD_FILE.FILENAME, file.getFileName());
    doc.put(DATASTORES.UPLOAD_FILE.EXTENSION, file.getExtension());
    doc.put(DATASTORES.UPLOAD_FILE.MIMETYPE, file.getMimeType());
    doc.put(DATASTORES.UPLOAD_FILE.PATH_ON_DISK, file.getPath_on_Disk());
    doc.put(DATASTORES.UPLOAD_FILE.URL, file.getURL());
    doc.put(DATASTORES.UPLOAD_FILE.THUMBNAIL_URL, file.getThumbnailURL());
    doc.put(DATASTORES.UPLOAD_FILE.SIZE, file.getSize());
    doc.put(DATASTORES.UPLOAD_FILE.TIME, file.getTime());
    doc.put(DATASTORES.UPLOAD_FILE.CLASSIFICATION, file.getClasstype());
    doc.put(DATASTORES.UPLOAD_FILE.ARTIFACT_ID, file.getArtifactid());
    doc.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN, file.getOriginalFileName());

    WriteResult wr = collection.save(doc);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok())
      return new ObjectId(file.getID());
    else
      return null;
  }

  @Override
  public int updateArtifactId(ObjectId _id, String artifactId) throws RemoteException {

    DBCollection collection = Services.getInstance().getBaseDataStore().db.getCollection(NAME);

    DBObject query = new BasicDBObject();
    query.put(DATASTORES.UPLOAD_FILE.ID, _id);

    BasicDBObject updateDoc = new BasicDBObject();
    updateDoc.append(DATASTORES.UPLOAD_FILE.ARTIFACT_ID, artifactId);

    BasicDBObject update = new BasicDBObject().append("$set", updateDoc);
    WriteResult wr = collection.update(query, update);
    CommandResult cr = wr.getCachedLastError();
    if (cr.ok())
      return 1;
    else
      return -1;
  }
}
