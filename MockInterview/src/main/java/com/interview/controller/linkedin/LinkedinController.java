package com.interview.controller.linkedin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import com.interview.framework.REQUEST_TYPES;
import com.interview.framework.USER;
import com.interview.framework.pojo.LinkedInUserDetails;
import com.interview.services.Services;

@Controller
public class LinkedinController {

  @Autowired
  private Properties myProps;

  private final Logger logger = Logger.getLogger(LinkedinController.class);

  private static final String PROFILE_PIC_DIRECTORY = "profilepic";

  @RequestMapping(value = "/linkedinshowloginscreen.do", method = RequestMethod.GET)
  public String linkedinshowloginscreen(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {

    final LinkedInOAuthService oauthService =
        LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(
            myProps.getProperty("apikey"), myProps.getProperty("apisecret"));

    String callbackUrl = myProps.getProperty("linkedincallbackurl");
    LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken(callbackUrl);
    req.getSession().setAttribute("requestToken", requestToken);
    String authUrl = requestToken.getAuthorizationUrl();
    req.getSession().setAttribute("requestToken", requestToken);
    System.out.println("Auth URL:"+authUrl);

    return "redirect:" + authUrl;
  }

  @RequestMapping(value = "/linkedinfetchdetails.do", method = RequestMethod.GET)
  public String linkedinfetchdetails(ModelMap model, HttpServletRequest req,
      HttpServletResponse res) {
    final LinkedInOAuthService oauthService =
        LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(
            myProps.getProperty("apikey"), myProps.getProperty("apisecret"));

    LinkedInRequestToken requestToken =
        (LinkedInRequestToken) req.getSession().getAttribute("requestToken");
    String oauthVerifier = req.getParameter("oauth_verifier");
    LinkedInAccessToken accessToken = oauthService.getOAuthAccessToken(requestToken, oauthVerifier);
    final LinkedInApiClientFactory factory = LinkedInApiClientFactory
        .newInstance(myProps.getProperty("apikey"), myProps.getProperty("apisecret"));

    final LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);

    Person profile = client.getProfileForCurrentUser(EnumSet.allOf(ProfileField.class));
    LinkedInUserDetails userDetails = Services.getInstance().getLinkedInProfile2UserDetailsService()
        .convertLinkedInProfile2UserDetails(profile);

    System.out.println("Email Address: " + profile.getEmailAddress() + profile.toString());
    System.out.println("Pic : " + profile.getPictureUrl());
    System.out.println(" User Details : " + userDetails.toString());

    if (profile.getPictureUrl() != null) {
      String picurl = getLinkedinProfilePic(profile.getPictureUrl(), req);
      userDetails.setPictureUrl(picurl);
    }

    Map<Object, Object> reqMap = new HashMap<Object, Object>();
    reqMap.put(USER.EMAIL, profile.getEmailAddress());
    reqMap.put(REQUEST_TYPES.SUB_REQ, REQUEST_TYPES.USER_REQ_SUB_REQ.IS_USER_REGISTERED);
    Map<String, Object> resMap = Services.getInstance().getRequestHandlerService()
        .handleRequest(reqMap, REQUEST_TYPES.USER_REQ);

    // This should be uncommented so that linkedin api can login
    // get data and then logout leaving no scope for hacking.
    /*
     * final LinkedInOAuthService oAuthService = LinkedInOAuthServiceFactory.getInstance()
     * .createLinkedInOAuthService( client.getApiConsumer().getConsumerKey(),
     * client.getApiConsumer().getConsumerSecret());
     * oauthService.invalidateAccessToken(accessToken);
     */

    boolean userExist = new Boolean(resMap.get("exist").toString());
    if (userExist) {
      Collection<GrantedAuthorityImpl> authorities = new HashSet<GrantedAuthorityImpl>();
      authorities.add(new GrantedAuthorityImpl("ROLE_USER"));

      Authentication authentication =
          new UsernamePasswordAuthenticationToken(profile.getEmailAddress(), null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      return "redirect:" + myProps.getProperty("homeurl") + "/dashboard.html";

    } else {
      HttpSession session = req.getSession();
      String indetails =
          Services.getInstance().getJSONUtilityService().convertObjectToJSON(userDetails);
      session.setAttribute("IN_REG", indetails);
      session.setAttribute("IN_USERNAME", resMap.get(USER.USERNAME).toString().toUpperCase());
      return "forward:/linkedinregistration.jsp";
    }
  }

  private String getLinkedinProfilePic(String pictureUrl, HttpServletRequest request) {
    HttpClient httpclient = new DefaultHttpClient();
    HttpGet httpget = new HttpGet(pictureUrl);
    HttpResponse response;
    try {
      response = httpclient.execute(httpget);
      HttpEntity entity = response.getEntity();
      if (entity != null) {
        InputStream inputStream = entity.getContent();
        File myfile = null;
        String secToken = "";
        String uuid = "";
        String extension = "jpg";
        SecureRandom random = new SecureRandom();
        secToken = new BigInteger(130, random).toString(32);
        uuid = UUID.randomUUID().toString();

        String path = myProps.getProperty("profilepicpath") + "" + secToken;
        File profilePicDir = new File(path);
        if (!profilePicDir.exists()) {
          if (!profilePicDir.mkdir()) {
            logger.info(
                "Unable to create the directory. Check the permissions. Path : " + profilePicDir);
          }
        }
        myfile = new File(profilePicDir + File.separator + uuid + "." + extension);
        try {
          if (!myfile.exists())
            myfile.createNewFile();
        } catch (IOException e) {
          e.printStackTrace();
        }

        IOUtils.copy(inputStream, new FileOutputStream(myfile));
        String profilePicUrl = request.getContextPath() + "/" + PROFILE_PIC_DIRECTORY + "/"
            + secToken + "/" + uuid + "." + extension;
        return profilePicUrl;
      }
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
