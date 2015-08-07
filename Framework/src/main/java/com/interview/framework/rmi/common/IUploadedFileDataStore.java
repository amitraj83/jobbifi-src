package com.interview.framework.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

import org.bson.types.ObjectId;

import com.interview.framework.pojo.UploadedFile;

public interface IUploadedFileDataStore extends Remote {

  String NAME = "uploadfile";

  public UploadedFile getUploadedFile(ObjectId _id) throws RemoteException;

  public String getURL(ObjectId _id) throws RemoteException;

  public ObjectId saveUploadedFile(UploadedFile file) throws RemoteException;

  public void update(ObjectId _id, Map<String, Object> changes) throws RemoteException;

  public int updateArtifactId(ObjectId _id, String artifactId) throws RemoteException;
}
