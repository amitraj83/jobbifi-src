package com.interview.test.lifecycle;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.interview.proto.Mailer;
import com.interview.proto.Mailer.AttributeType;
import com.interview.services.Services;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmailTest {

  @Test
  public void testing() {
    System.out.println("Testing in another");
    Map<AttributeType, String> data = new HashMap<AttributeType, String>();
    data.put(AttributeType.SUPPORT_REQUEST_EMAIL, "deepcyan@gmail.com");
    Services.getInstance().getEmailService().sendMail(Mailer.EmailType.NEW_REGISTRATION_INTERVIEWER,
        data, "deepcyan@gmail.com");
  }
}
