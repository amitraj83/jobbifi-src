package com.interview.services;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service("encryptionService")
public class EncryptionService {
  static String IV = "AAAAAAAAAAAAAZAA";

  /*
   * public String encrypt(String data, String secret){ //http://stackoverflow.
   * com/questions/5520640/encrypting-and-decrypting-using-java // hash keyString with SHA-256 and
   * crop the output to 128-bit for key try{ MessageDigest digest =
   * MessageDigest.getInstance("SHA-256"); digest.update(secret.getBytes()); byte[] key = new
   * byte[16]; System.arraycopy(digest.digest(), 0, key, 0, key.length); SecretKeySpec keySpec = new
   * SecretKeySpec(key, "AES"); IVSpecService ivSpecService =
   * Services.getInstance().getIVSpecService(); //IVSpecService ivSpecService = new IVSpecService();
   * // encrypt ivSpecService.getCipher().init(Cipher.ENCRYPT_MODE, keySpec,
   * ivSpecService.getIVSpec()); byte[] encrypted =
   * ivSpecService.getCipher().doFinal(data.getBytes("UTF-8")); return new String(encrypted); }
   * catch(Exception e){ e.printStackTrace(); return null; } }
   */
  public byte[] encrypt(String data, String secret) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.update(secret.getBytes());
    byte[] skey = new byte[16];
    System.arraycopy(digest.digest(), 0, skey, 0, skey.length);

    SecretKeySpec key = new SecretKeySpec(skey, "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
    return cipher.doFinal(data.getBytes("UTF-8"));
  }
}
