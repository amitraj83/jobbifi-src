package com.interview.services;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.proto.Mailer.Attribute;
import com.interview.proto.Mailer.AttributeType;
import com.interview.proto.Mailer.Email;
import com.interview.proto.Mailer.EmailType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service("emailservice")
public class EmailService {

  @Autowired
  Properties myProps;

  private static final Logger logger = Logger.getLogger(EmailService.class);

  /**
   * Sends an email of type `emailType` with variables `params` to `recipient`.
   * 
   * @param emailType Type of email. See mailer.proto for types.
   * @param params Map of variables and values to be substituted in email templates.
   * @param recipient Email ID of recipient
   */
  public void sendMail(EmailType emailType, Map<AttributeType, String> params, String recipient) {

    ConnectionFactory factory = new ConnectionFactory();
    Connection connection;
    factory.setHost(myProps.getProperty("mail.channel.host"));
    String queue = myProps.getProperty("mail.channel.queue");

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
      logger.error("", e);
    }
  }
}
