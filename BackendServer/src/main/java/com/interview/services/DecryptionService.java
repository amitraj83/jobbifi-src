package com.interview.services;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service("decryptionService")
public class DecryptionService {

  static String IV = "AAAAAAAAAAAAAZAA";

  /*
   * public String decrypt(String encryptedData, String secret){ try{ MessageDigest digest2 =
   * MessageDigest.getInstance("SHA-256"); digest2.update(secret.getBytes()); byte[] key2 = new
   * byte[16]; System.arraycopy(digest2.digest(), 0, key2, 0, key2.length); SecretKeySpec keySpec2 =
   * new SecretKeySpec(key2, "AES");
   * 
   * IVSpecService ivSpecService = Services.getInstance().getIVSpecService(); // IVSpecService
   * ivSpecService = new IVSpecService();
   * 
   * ivSpecService.getCipher().init(Cipher.DECRYPT_MODE, keySpec2, ivSpecService.getIVSpec());
   * byte[] decrypted = ivSpecService.getCipher().doFinal(encryptedData.getBytes()); return new
   * String(decrypted, "UTF-8"); } catch(Exception e){ e.printStackTrace(); return ""; }
   * 
   * }
   */
  public String decrypt(byte[] cipherText, String encryptionKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    digest.update(encryptionKey.getBytes());
    byte[] skey = new byte[16];
    System.arraycopy(digest.digest(), 0, skey, 0, skey.length);

    SecretKeySpec key = new SecretKeySpec(skey, "AES");
    cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
    return new String(cipher.doFinal(cipherText), "UTF-8");
  }

}
