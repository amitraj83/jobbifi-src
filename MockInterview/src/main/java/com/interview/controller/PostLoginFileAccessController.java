package com.interview.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.interview.framework.DATASTORES;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.VARIABLES;
import com.interview.services.Services;

@Controller
public class PostLoginFileAccessController extends BaseController {

  private static final Logger logger = Logger.getLogger(PostLoginFileAccessController.class);
  
  @Autowired
  private Properties myProps;
  
  private static final String PROFILE_PIC_DIRECTORY = "profilepic";
	
  @RequestMapping(value = "/aauth/testfileserver.do", method = RequestMethod.GET)
  public ModelAndView testfileupload() {
    Services
        .getInstance()
        .getRequestHandlerService()
        .handleRequest(new HashMap<Object, Object>(),
            REQUEST_TYPES.FILESERVER_UPDATE_INTERVIEW_FILE);
    return new ModelAndView();
  }

  @RequestMapping(value = "/aauth/fileupload", method = RequestMethod.POST)
  public ModelAndView fileUpload(@RequestParam("file") MultipartFile file,
      HttpServletRequest request, HttpServletResponse response) {

    Map<String, Object> res = null;
    
    if (request.getParameter("type").equals("profilepicupdate")) {
      boolean fileWritten = true;
      File myfile = null;
      String secToken = "";
      String uuid = "";
      String extension = "";

      SecureRandom random = new SecureRandom();
      secToken = new BigInteger(130, random).toString(32);
      uuid = UUID.randomUUID().toString();
      extension = FilenameUtils.getExtension(file.getOriginalFilename());
      
      String path = myProps.getProperty("profilepicpath") + "" + secToken;            
      File profilePicDir = new File(path);
      if(!profilePicDir.exists()){
        if(!profilePicDir.mkdir()){
        	logger.info("Unable to create the directory. Check the permissions. Path : " + profilePicDir);
        }
      }
      
      myfile = new File(profilePicDir + File.separator + uuid + "." + extension);
      try {
        if (!myfile.exists())
          myfile.createNewFile();
      } catch (IOException e) {
    	  logger.error("Exception occured while creating new file.", e);
      }

      fileWritten = Services.getInstance().getFileUtilities().copyMultipartFile(file, myfile);
      if (fileWritten) {         	 
    	  String profilePicUrl = request.getContextPath() + File.separatorChar + PROFILE_PIC_DIRECTORY + File.separatorChar +
    			  secToken + File.separatorChar + uuid  + "." + extension;    	  
    	  Map<Object, Object> data = new HashMap<Object, Object>();
    	  data.put(USER.USERNAME, getLoginUser());    	  
    	  data.put(USER.PROFILE_PIC, profilePicUrl);
    	  data.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.UPDATE_USER_PROFILE_PIC);
    	  Services.getInstance().getRequestHandlerService().handleRequest(data, REQUEST_TYPES.UPDATE_USER_PROFILE);
    	  
        res = new HashMap<String, Object>();
        res.put("st", secToken);
        res.put("fn", uuid + "." + extension);
        res.put("mime", file.getContentType());
        res.put("path", profilePicUrl);
      } else {
        logger.info("[Post Login] Writing file failed: " + myfile.getAbsolutePath());
      }
      
    } else if (request.getParameter("type").equals("interviewdoc")) {           
      try {     
        Map<Object, Object> data = new HashMap<Object, Object>();
        String content = new String(file.getBytes()); 
        data.put("IS", content);       
        data.put("FN", file.getOriginalFilename());
        data.put("EXT", FilenameUtils.getExtension(file.getOriginalFilename()));
        data.put(DATASTORES.UPLOAD_FILE.SIZE, file.getSize());
        data.put(DATASTORES.UPLOAD_FILE.MIMETYPE, file.getContentType());
        data.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN, file.getOriginalFilename());
        
        String userName = getLoginUser();
        data.put(USER.USERNAME, userName);
        res = Services.getInstance().getRequestHandlerService()
                .handleRequest(data, REQUEST_TYPES.FILESERVER_SAVE_INTERVIEW_FILE);        
        logger.info("Resposne : " + res);
        
        res.put("originalfn", file.getOriginalFilename());
      } catch (IOException e) {
    	  logger.error("IO EXCEPTION : ", e);        
      }
      
    } else if (request.getParameter("type").equals("jobdoc")) {           
        try {     
            Map<Object, Object> data = new HashMap<Object, Object>();
            String content = new String(file.getBytes()); 
            data.put("IS", content);       
            data.put("FN", file.getOriginalFilename());
            data.put("EXT", FilenameUtils.getExtension(file.getOriginalFilename()));
            data.put(DATASTORES.UPLOAD_FILE.SIZE, file.getSize());
            data.put(DATASTORES.UPLOAD_FILE.MIMETYPE, file.getContentType());
            data.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN, file.getOriginalFilename());
            
            String userName = getLoginUser();
            data.put(USER.USERNAME, userName);            
            data.put(REQUEST_TYPES.SUB_REQ,REQUEST_TYPES.FILESERVER_SAVE_JOB_FILE);
            
            res = Services.getInstance().getRequestHandlerService()
                    .handleRequest(data, REQUEST_TYPES.FILESERVER_JOB_FILE);        
            logger.info("Resposne : " + res);
            
            res.put("originalfn", file.getOriginalFilename());
          } catch (IOException e) {
        	  logger.error("IO EXCEPTION : ", e);
          }
          
    } else if (request.getParameter("type").equals("chatdocument")) {
      String targetUser = request.getParameter("targetuser");
      try {

        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("IS", file.getBytes());
        data.put(DATASTORES.UPLOAD_FILE.FILENAME, file.getOriginalFilename());
        data.put(DATASTORES.UPLOAD_FILE.EXTENSION,
            FilenameUtils.getExtension(file.getOriginalFilename()));
        data.put(DATASTORES.UPLOAD_FILE.ARTIFACT_ID, targetUser);
        data.put(DATASTORES.UPLOAD_FILE.CLASSIFICATION,
            DATASTORES.UPLOAD_FILE.CLASS_TYPE.CHAT_DOCUMENT);
        data.put(DATASTORES.UPLOAD_FILE.SIZE, file.getSize());
        data.put(DATASTORES.UPLOAD_FILE.MIMETYPE, file.getContentType());
        data.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN, file.getOriginalFilename());
        String userName =getLoginUser();
        data.put(USER.USERNAME, userName);

        res =
            Services.getInstance().getRequestHandlerService()
                .handleRequest(data, REQUEST_TYPES.FILESERVER_SAVE_CHAT_FILE);
        res.put("originalfn", file.getOriginalFilename());
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (request.getParameter("type").equals("biddocupload")) {
      try {
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("IS", file.getBytes());
        data.put(DATASTORES.UPLOAD_FILE.FILENAME, file.getOriginalFilename());
        data.put(DATASTORES.UPLOAD_FILE.EXTENSION,
            FilenameUtils.getExtension(file.getOriginalFilename()));
        data.put(DATASTORES.UPLOAD_FILE.CLASSIFICATION,
            DATASTORES.UPLOAD_FILE.CLASS_TYPE.BID_DOCUMENT);
        data.put(DATASTORES.UPLOAD_FILE.SIZE, file.getSize());
        data.put(DATASTORES.UPLOAD_FILE.MIMETYPE, file.getContentType());
        data.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN, file.getOriginalFilename());
        String userName =getLoginUser();
        data.put(USER.USERNAME, userName);

        res =
            Services.getInstance().getRequestHandlerService()
                .handleRequest(data, REQUEST_TYPES.FILESERVER_SAVE_BID_FILE);
        res.put("originalfn", file.getOriginalFilename());
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (request.getParameter("type").equals("disputedocument")) {
      try {
        Map<Object, Object> data = new HashMap<Object, Object>();
        String content = new String(file.getBytes()); 
        data.put("IS", content);
        data.put(DATASTORES.UPLOAD_FILE.FILENAME, file.getOriginalFilename());
        data.put(DATASTORES.UPLOAD_FILE.EXTENSION,
            FilenameUtils.getExtension(file.getOriginalFilename()));
        data.put(DATASTORES.UPLOAD_FILE.CLASSIFICATION,
            DATASTORES.UPLOAD_FILE.CLASS_TYPE.DISPUTE_DOCUMENT);
        data.put(DATASTORES.UPLOAD_FILE.SIZE, file.getSize());
        data.put(DATASTORES.UPLOAD_FILE.MIMETYPE, file.getContentType());
        data.put(DATASTORES.UPLOAD_FILE.ORIGINAL_FN, file.getOriginalFilename());
        String userName =getLoginUser();
        data.put(USER.USERNAME, userName);

        res =
            Services.getInstance().getRequestHandlerService()
                .handleRequest(data, REQUEST_TYPES.FILESERVER_SAVE_DISPUTE_FILE);
        res.put("originalfn", file.getOriginalFilename());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return new ModelAndView("response", "message", Services.getInstance().getJSONUtilityService()
        .getJSONStringOfMap(res));

  }


  @RequestMapping(value = "aauth/disputefiles/disputefiledownload.do", method = RequestMethod.GET)
  public void disputeFileDownload(HttpServletRequest request, HttpServletResponse response) {
    downloadFile(request, response);
  }

  @RequestMapping(value = "aauth/interviewfiles/ifiledownload.do", method = RequestMethod.GET)
  public void fileDownload(HttpServletRequest request, HttpServletResponse response) {
    downloadFile(request, response);
  }

  @RequestMapping(value = "aauth/chatfiles/chatfiledownload.do", method = RequestMethod.GET)
  public void chatfileDownload(HttpServletRequest request, HttpServletResponse response) {
    downloadFile(request, response);
  }

  @RequestMapping(value = "aauth/bidfiles/bidfiledownload.do", method = RequestMethod.GET)
  public void bidfileDownload(HttpServletRequest request, HttpServletResponse response) {
    downloadFile(request, response);
  }

  private void downloadFile(HttpServletRequest request, HttpServletResponse response) {
    Map<Object, Object> reqmap = new HashMap<Object, Object>();
    reqmap.put(VARIABLES.ENCRYPTED_FILE_ID, request.getParameter("fileid"));
    reqmap.put(USER.USERNAME,getLoginUser());
    Map<String, Object> resMap =
        Services.getInstance().getRequestHandlerService()
            .handleRequest(reqmap, REQUEST_TYPES.FILESERVER_DOWNLOAD_INTERVIEW_FILE);
    try {
      String mime = resMap.get(DATASTORES.UPLOAD_FILE.MIMETYPE).toString();
      String originalFileName = resMap.get(DATASTORES.UPLOAD_FILE.ORIGINAL_FN).toString();
      byte[] data = ((String)resMap.get("BYTES")).getBytes();
      response.setContentType(mime);
      response.setContentLength(new String(data).length());
      response
          .setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\"");
      ServletOutputStream oos = response.getOutputStream();
      oos.write(data);
      response.flushBuffer();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
