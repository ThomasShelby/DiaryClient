package com.softserve.tc.diaryclient.service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.softserve.tc.diaryclient.controller.FollowerController;
import com.softserve.tc.diaryclient.log.Log;

import javax.mail.internet.InternetAddress;

public class MailSender implements Runnable{

	private final String userName = "DiaryLV159Java@gmail.com";
	private final String password = "Lv159Java";
	private String subject;
	private String text;
	private String toEmail;
	public static MailSender mailSender = null;
	private static Properties props = new Properties();
	private static Logger logger = Log.init(MailSender.class.toString());
	
	private MailSender() {
	}
	
	public void setParameters(String subject, String text, String toEmail) {
        this.subject = subject;
        this.text = text;
        this.toEmail = toEmail;
        
 
    }
	public static MailSender getInstance() {
		if (mailSender == null) {
			mailSender = new MailSender();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
		}
		return mailSender;
	}

	public synchronized void send() {
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			// from whom
			message.setFrom(new InternetAddress(userName));
			// whom
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			// title
			message.setSubject(subject);
			// text message
			message.setText(text);
			logger.info("Send e-mail to " + toEmail);
			// sending message
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

    public void run() {
       this.send();
        
    }

}
