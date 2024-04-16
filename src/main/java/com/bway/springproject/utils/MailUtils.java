package com.bway.springproject.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailUtils {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String toEmail, String subject, String message) {

		SimpleMailMessage mail = new SimpleMailMessage();

		mail.setTo(toEmail);
		mail.setSubject(subject);
		mail.setText("<h1>" + message + "</h1>");

		javaMailSender.send(mail);

	}

	public void sendEmailWithAttachment(String toEmail, String subject, String message,String path) {
		MimeMessage msg = javaMailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true);

			helper.setTo(toEmail);
			helper.setSubject(subject);

			// default = text/plain
			// helper.setText("Check attachment for image!");

			// true = text/html
			helper.setText("<h1>Check attachment for image!</h1>", true);

			// hard coded a file path
			// FileSystemResource file = new FileSystemResource(new
			// File("path/android.png"));

			//helper.addAttachment("my_photo.png", new ClassPathResource("/image/1.jpeg"));
			helper.addAttachment("hello", new File(path));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(msg);

	}
}
