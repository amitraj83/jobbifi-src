package com.interview.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("fileutilities")
public class FileUtilities {

  public boolean copyMultipartFile(MultipartFile srcFile, File destFile) {
    InputStream is = null;
    OutputStream os = null;
    try {
      is = srcFile.getInputStream();
      os = new FileOutputStream(destFile);
      IOUtils.copy(is, os);
      os.flush();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (os != null) {
        try {
          // outputStream.flush();
          os.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }
  }


  public void copyFile(File srcFile, File destFile) {
    InputStream is = null;
    OutputStream os = null;
    try {
      is = new FileInputStream(srcFile);
      os = new FileOutputStream(destFile);
      IOUtils.copy(is, os);
      os.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (os != null) {
        try {
          // outputStream.flush();
          os.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

      }
    }
  }

}
