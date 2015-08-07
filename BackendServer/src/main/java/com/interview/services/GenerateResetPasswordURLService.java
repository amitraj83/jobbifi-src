package com.interview.services;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Properties;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.framework.VARIABLES;
import com.interview.framework.pojo.ResetPasswordEntity;
import com.interview.rmi.DataStoreRegistry;

@Service("generateresetpassURL")
public class GenerateResetPasswordURLService {

  @Autowired
  private Properties myProps;	
	
  public String generateURL(String username, String email) throws RemoteException {
    try {
      String secretKey = Services.getInstance().getSecurityTokenGenerator().getToken();

      byte[] encodedUserNamebytes =
          Services.getInstance().getEncryptionService().encrypt(username, secretKey);
      String encodedUserName =
          Services.getInstance().getConversionService().bytesToHex(encodedUserNamebytes);
      String securityToken = Services.getInstance().getSecurityTokenGenerator().getToken();

      ResetPasswordEntity entity = getResetPasswordEntity(username, secretKey, securityToken);
      ObjectId _id = DataStoreRegistry.getInstance().getResetPasswordDataStore().insert(entity);
      if (_id != null) {
        String authInstanceObjectId = _id.toString();
        String url = myProps.getProperty("mail.url.reset") + "?"
                + VARIABLES.RESET_PASS.AUTH_INSTANCE + "=" + authInstanceObjectId + "&"
                + VARIABLES.RESET_PASS.AUTH_ID + "=" + encodedUserName + "&"
                + VARIABLES.RESET_PASS.AUTH_TOKEN + "=" + securityToken;
        System.out.println("RESET PASS URL:" + url);
        return url;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private ResetPasswordEntity getResetPasswordEntity(String username, String secretKey,
      String securityToken) {
    ResetPasswordEntity entity = new ResetPasswordEntity();
    entity.setDt(new Date().getTime());
    entity.setExpired(false);
    entity.setIpAddress("localhost");
    entity.setSecretKey(secretKey);
    entity.setSectoken(securityToken);
    entity.setUsername(username);
    return entity;
  }
}