package com.interview.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.mail.smtp.SMTPSSLTransport;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service("emailservice")
public class EmailService {

	@Autowired
	Properties myProps;
	
	@Autowired
	private Configuration freemarkerConfiguration;
	
	private static final Logger logger = Logger.getLogger(EmailService.class);
	
	public int sendEmail(String from, String to, String subject, String fid, String message) {

		final String username = myProps.getProperty("mail.username");
		final String password = myProps.getProperty("mail.password");

		Properties props = new Properties();
		props.put("mail.smtp.host", myProps.getProperty("mail.smtp.host"));
		props.put("mail.smtp.socketFactory.port", myProps.getProperty("mail.smtp.socketFactory.port"));
		props.put("mail.smtp.socketFactory.class", myProps.getProperty("mail.smtp.socketFactory.class"));
		props.put("mail.smtp.auth", myProps.getProperty("mail.smtp.auth"));
		props.put("mail.smtp.port", myProps.getProperty("mail.smtp.port"));
		props.put("mail.transport.protocol", myProps.getProperty("mail.transport.protocol"));
		props.put("mail.debug", myProps.getProperty("mail.debug"));

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);	
	        }	
	    });

    try {
        Message message1 = new MimeMessage(session);
        message1.setFrom(new InternetAddress(from));
        message1.setRecipients(RecipientType.TO, InternetAddress.parse(to));
        message1.setSubject(subject);
        message1.setText(message);
        Transport t = session.getTransport("smtp");
        SMTPSSLTransport transport = (SMTPSSLTransport) session.getTransport("smtps");
        transport.connect(myProps.getProperty("mail.smtp.host"), Integer.parseInt(myProps.getProperty("mail.smtp.port")), username, password);        
        transport.send(message1);
        transport.close();        

    } catch (Exception e) {
    	e.printStackTrace();
        throw new RuntimeException(e);
    }    
    return 1;
  }
	
	/**
	 * 
	 * @param body
	 * @param recipient
	 * @param subject
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void sendMail(String recipient, String subject, String template, Map<String, Object> model) {
				
		String body = "";		
		try {
			body = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate(template,"UTF-8"), model);
			
			if(Boolean.parseBoolean(myProps.getProperty("mail.debug"))){
			
				final String username = myProps.getProperty("mail.username");
				final String password = myProps.getProperty("mail.password");
				Properties props = new Properties();
				props.put("mail.smtp.host", myProps.getProperty("mail.smtp.host"));
				props.put("mail.smtp.socketFactory.port", myProps.getProperty("mail.smtp.socketFactory.port"));
				props.put("mail.smtp.socketFactory.class", myProps.getProperty("mail.smtp.socketFactory.class"));
		        props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.auth", myProps.getProperty("mail.smtp.auth"));
				props.put("mail.smtp.port", myProps.getProperty("mail.smtp.port"));
				props.put("mail.transport.protocol", myProps.getProperty("mail.transport.protocol"));
				props.put("mail.debug", myProps.getProperty("mail.debug"));
	
				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			        protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(username, password);	
			        }	
			    });
				
				Message message1 = new MimeMessage(session);
		        message1.setFrom(new InternetAddress(myProps.getProperty("mail.from")));
		        message1.setRecipients(RecipientType.TO, InternetAddress.parse(recipient));
		        message1.setSubject(subject);
		        message1.setText(body);
		        Transport t = session.getTransport("smtp");
		        SMTPSSLTransport transport = (SMTPSSLTransport) session.getTransport("smtps");
		        transport.connect(myProps.getProperty("mail.smtp.host"), Integer.parseInt(myProps.getProperty("mail.smtp.port")), username, password);        
		        transport.send(message1);
		        transport.close();
		        
			} else {
				sendMailChannel(body, recipient, subject);
			}
			
		} catch (IOException e1) {
			logger.error("", e1);
		} catch (TemplateException e1) {
			logger.error("", e1);
		} catch (AddressException e) {
			logger.error("", e);
		} catch (MessagingException e) {
			logger.error("", e);
		}
	}		
	
	public void sendMailChannel(String body, String recipient, String subject){
		
		String queue = myProps.getProperty("mail.channel.queue");		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(myProps.getProperty("mail.channel.host"));
	    Connection connection;

	    try {
    		  Map<String, String> messageMap = new HashMap<String, String>();
		      messageMap.put("body", body);
		      messageMap.put("recipient", recipient);
		      messageMap.put("subject", subject);
		      String message = new ObjectMapper().writeValueAsString(messageMap);
		      logger.info(" Message :" + message);
	    	
		      connection = factory.newConnection();
      		  Channel channel = connection.createChannel();
	      	  channel.queueDeclare(queue, false, false, false, null);
	      	  channel.basicPublish("", queue, null, message.getBytes());
	      	  channel.close();
	      	  connection.close();
	      
	    } catch (IOException e) {
	    	logger.error("", e);	      
	    }
	}
	
	public void sendMailChannelOnEvent(String scenarioid,Map<String, String> param,String recipient, String subject){
		
		String queue = myProps.getProperty("mail.channel.queue");		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost(myProps.getProperty("mail.channel.host"));
	    Connection connection;

	    try {
    		  Map<String, Object> messageMap = new HashMap<String, Object>();
		      messageMap.put("body", param);
		      messageMap.put("scenarioid", scenarioid);
		      messageMap.put("recipient", recipient);
		      messageMap.put("subject", subject);
		      String message = new ObjectMapper().writeValueAsString(messageMap);
		      logger.info(" Message :" + message);
	    	
		      connection = factory.newConnection();
      		  Channel channel = connection.createChannel();
	      	  channel.queueDeclare(queue, false, false, false, null);
	      	  channel.basicPublish("", queue, null, message.getBytes());
	      	  channel.close();
	      	  connection.close();
	      
	    } catch (IOException e) {
	    	logger.error("", e);	      
	    }
	}
	
}