package com.interview.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service("fileutilities")
public class FileUtilities {



  public int copyFile(String username, byte[] is, String rootpath, String fileNameWithExtension) {
    try {

      String userDirPath = rootpath + username;
      File userDir = new File(userDirPath);
      if (!userDir.exists())
        userDir.mkdirs();

      File userFile = new File(userDirPath + File.separator + fileNameWithExtension);
      if (!userFile.exists())
        try {
          userFile.createNewFile();
        } catch (IOException e) {
          e.printStackTrace();
        }

      FileOutputStream fos = new FileOutputStream(userFile);
      fos.write(is);
      fos.close();
      return 1;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public void copyFile(InputStream is, File outputFile) {
    try {
      OutputStream os = new FileOutputStream(outputFile);
      copyFile(is, os);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void copyFile(InputStream is, OutputStream os) {
    try {
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
