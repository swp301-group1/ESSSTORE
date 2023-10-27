package com.shopping.esoshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailService {
	 @Autowired
	 private JavaMailSender javaMailSender;
	 
	 public boolean sendEmail(String to, String sub, String text) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(to);
			msg.setSubject(sub);
			msg.setText(text);
			javaMailSender.send(msg);
			return true; 
		} catch (Exception e) {
			e.printStackTrace(); 
			return false; 
		}
	}
}
