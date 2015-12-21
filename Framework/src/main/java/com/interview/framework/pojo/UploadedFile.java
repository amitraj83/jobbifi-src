package com.interview.framework.pojo;

import java.io.Serializable;

public class UploadedFile implements Serializable {


  /**
   * 
   */
  private static final long serialVersionUID = -155771705415443875L;
  String ID;
  String fileName;
  String path_on_Disk;
  String extension;
  String mimeType;
  String URL;
  long size;
  String owner;
  long time;
  String thumbnailURL;
  String classtype;
  String artifactid;
  String originalFileName;

  public String getOriginalFileName() {
    return originalFileName;
  }

  public void setOriginalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
  }

  public String getClasstype() {
    return classtype;
  }

  public void setClasstype(String classtype) {
    this.classtype = classtype;
  }

  public String getArtifactid() {
    return artifactid;
  }

  public void setArtifactid(String artifactid) {
    this.artifactid = artifactid;
  }

  public String getID() {
    return ID;
  }

  public void setID(String iD) {
    ID = iD;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getPath_on_Disk() {
    return path_on_Disk;
  }

  public void setPath_on_Disk(String pathOnDisk) {
    path_on_Disk = pathOnDisk;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getURL() {
    return URL;
  }

  public void setURL(String uRL) {
    URL = uRL;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getThumbnailURL() {
    return thumbnailURL;
  }

  public void setThumbnailURL(String thumbnailURL) {
    this.thumbnailURL = thumbnailURL;
  }



}
