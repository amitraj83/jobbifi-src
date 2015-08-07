package com.interview.framework.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class DecryptionService {

  private IVSpecService ivSpecService;

  public DecryptionService(IVSpecService ivSpecService) {
    this.ivSpecService = ivSpecService;
  }


  public String decrypt(String encryptedData, String secret) {
    try {
      MessageDigest digest2 = MessageDigest.getInstance("SHA-256");
      digest2.update(secret.getBytes());
      byte[] key2 = new byte[16];
      System.arraycopy(digest2.digest(), 0, key2, 0, key2.length);
      SecretKeySpec keySpec2 = new SecretKeySpec(key2, "AES");

      // IVSpecService ivSpecService = Services.getInstance().getIVSpecService();

      this.ivSpecService.getCipher().init(Cipher.DECRYPT_MODE, keySpec2, ivSpecService.getIVSpec());
      byte[] decrypted = ivSpecService.getCipher().doFinal(encryptedData.getBytes());
      return new String(decrypted, "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }

  }

  public static void main(String[] args) {
    ConversionService cs = new ConversionService();
    String secret = "936112467832161231129274664264010578764754573797257357633632";
    IVSpecService ivSpec = new IVSpecService();
    EncryptionService es = new EncryptionService(ivSpec);
    String encName = es.encrypt("526e898c90a2ccd4d7be1b35", secret);
    // String encodedUserName = "252F6DAB9A5A4C3F684BE6B167F292B4FC489EE1040247727F701D386643DDD5";
    String encodedUserName = cs.bytesToHex(encName.getBytes());
    System.out.println(encodedUserName);
    encodedUserName = new String(cs.hexStringToByteArray(encodedUserName));
    String dec = new DecryptionService(ivSpec).decrypt(encodedUserName, secret);
    System.out.println(dec);
  }

}
