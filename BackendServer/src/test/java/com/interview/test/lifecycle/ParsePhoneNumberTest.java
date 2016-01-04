package com.interview.test.lifecycle;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration("classpath:applicationContext.xml")
public class ParsePhoneNumberTest {

  // @Test
  public static void main(String[] args) {
    parsePhone("09482566565");
  }

  private static void parsePhone(String data) {
    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    try {
      PhoneNumber phoneNumber = phoneUtil.parse(data, "IN");
      System.out.println(phoneNumber);
      System.out.println(phoneNumber.toString());
      System.out.println(phoneNumber.getNationalNumber());
    } catch (NumberParseException e) {
      System.err.println("NumberParseException was thrown: " + e.toString());
    }
  }
}
