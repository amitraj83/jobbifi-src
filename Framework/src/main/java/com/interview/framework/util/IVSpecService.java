package com.interview.framework.util;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;


public class IVSpecService implements Serializable {

  private IvParameterSpec ivSpec;
  private Cipher cipher;

  public IVSpecService() {
    // setup AES cipher in CBC mode with PKCS #5 padding

    try {
      cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

      // setup an IV (initialization vector) that should be
      // randomly generated for each input that's encrypted
      byte[] iv = new byte[cipher.getBlockSize()];
      new SecureRandom().nextBytes(iv);
      this.ivSpec = new IvParameterSpec(iv);

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    }

  }

  public Cipher getCipher() {
    return this.cipher;
  }

  public IvParameterSpec getIVSpec() {
    return this.ivSpec;
  }

}
