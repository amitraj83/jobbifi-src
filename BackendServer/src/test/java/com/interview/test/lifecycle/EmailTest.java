package com.interview.test.lifecycle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.interview.proto.Mailer;
import com.interview.proto.Mailer.Attribute;
import com.interview.proto.Mailer.AttributeType;
import com.interview.proto.Mailer.Email;
import com.interview.proto.Mailer.EmailType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration("classpath:applicationContext.xml")
public class EmailTest {

  // @Test
  public static void main(String[] args) {
    System.out.println("Testing in another");
    Map<AttributeType, String> data = new HashMap<AttributeType, String>();
    data.put(AttributeType.SUPPORT_REQUEST_EMAIL, "deepcyan@gmail.com");
    // Services.getInstance().getEmailService().sendMail(Mailer.EmailType.NEW_REGISTRATION_INTERVIEWER,
    // data, "deepcyan@gmail.com");
    sendMail(Mailer.EmailType.NEW_REGISTRATION_INTERVIEWER, data, "deepcyan@gmail.com");
  }

  private static void sendMail(EmailType emailType, Map<AttributeType, String> params,
      String recipient) {
    ConnectionFactory factory = new ConnectionFactory();
    Connection connection;
    factory.setHost("localhost");
    String queue = "hello";

    Email.Builder emailBuilder = Email.newBuilder();
    emailBuilder.setRecipient(recipient);
    emailBuilder.setType(emailType);
    for (Entry<AttributeType, String> param : params.entrySet()) {
      emailBuilder.addData(
          Attribute.newBuilder().setType(param.getKey()).setValue(param.getValue()).build());
    }

    try {
      byte[] message = emailBuilder.build().toByteArray();
      connection = factory.newConnection();
      Channel channel = connection.createChannel();
      channel.queueDeclare(queue, false, false, false, null);
      channel.basicPublish("", queue, null, message);
      channel.close();
      connection.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
