package com.interview.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.USER;
import com.interview.services.Services;

@Controller
public class PreLoginFileAccessController {

  @Autowired
  private Properties myProps;

  private static final String PROFILE_PIC_DIRECTORY = "profilepic";

  @RequestMapping(value = "/pauth/fileupload", method = RequestMethod.POST)
  public ModelAndView fileUpload(@RequestParam("file") MultipartFile file,
      HttpServletRequest request, HttpServletResponse response) {

    boolean fileWritten = true;
    InputStream inputStream = null;
    OutputStream outputStream = null;
    File myfile = null;
    String secToken = "";
    String uuid = "";
    String extension = "";

    SecureRandom random = new SecureRandom();
    secToken = new BigInteger(130, random).toString(32);
    uuid = UUID.randomUUID().toString();
    extension = FilenameUtils.getExtension(file.getOriginalFilename());

    File profilePicDir = new File(myProps.getProperty("profilepicpath") + "" + secToken);
    if (!profilePicDir.exists())
      profilePicDir.mkdirs();

    myfile = new File(profilePicDir + File.separator + uuid + "." + extension);
    try {
      if (!myfile.exists())
        myfile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    fileWritten = Services.getInstance().getFileUtilities().copyMultipartFile(file, myfile);
    Map<String, Object> res = null;
    if (fileWritten) {
      System.out.println("[Pre Login]Writing file to disk...done " + myfile.getAbsolutePath());
      res = new HashMap<String, Object>();
      String profilePicUrl = request.getContextPath() + "/" + PROFILE_PIC_DIRECTORY + "/" + secToken
          + "/" + uuid + "." + extension;
      res.put("st", secToken);
      res.put("fn", uuid + "." + extension);
      res.put("mime", file.getContentType());
      res.put(USER.PROFILE_PIC, profilePicUrl);
    } else {
      System.out.println("[Pre Login]Writing file failed: " + myfile.getAbsolutePath());
    }
    return new ModelAndView("response", "message",
        Services.getInstance().getJSONUtilityService().getJSONStringOfMap(res));

  }

  @RequestMapping(value = "/pauth/filedownload/{file_name}", method = RequestMethod.GET)
  public void fileDownload(@PathVariable("file_name") String fn, HttpServletRequest request,
      HttpServletResponse response) {

    String type = request.getParameter("type");
    String mime = request.getParameter("mime");
    if (type.equals("profile")) {
      try {
        String dir = "C:\\Work\\upload\\profilepics";
        String fileName = dir + File.separator + fn;

        InputStream is = new FileInputStream(new File(fileName));
        response.setContentType(mime);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fn + "\"");
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
