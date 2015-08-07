package com.interview.framework.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class EncryptionService {

  private IVSpecService ivSpecService;

  public EncryptionService(IVSpecService ivSpecService) {
    this.ivSpecService = ivSpecService;
  }

  public String encrypt(String data, String secret) {
    // http://stackoverflow.com/questions/5520640/encrypting-and-decrypting-using-java
    // hash keyString with SHA-256 and crop the output to 128-bit for key
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.update(secret.getBytes());
      byte[] key = new byte[16];
      System.arraycopy(digest.digest(), 0, key, 0, key.length);
      SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
      // IVSpecService ivSpecService = Services.getInstance().getIVSpecService();
      // encrypt
      this.ivSpecService.getCipher().init(Cipher.ENCRYPT_MODE, keySpec, ivSpecService.getIVSpec());
      byte[] encrypted = ivSpecService.getCipher().doFinal(data.getBytes("UTF-8"));
      return new String(encrypted);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
