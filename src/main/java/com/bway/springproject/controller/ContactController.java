package com.bway.springproject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bway.springproject.utils.MailUtils;

@Controller
public class ContactController {
	@Autowired
	private MailUtils  mailUtil;
	
	@GetMapping("/contact")
	public String getContact() {
		
		return "ContactForm";
	}
	
	@PostMapping("/contact")
	public  String postContact(@RequestParam String toEmail,@RequestParam String subject,@RequestParam String message,@RequestParam MultipartFile file) {
		
		mailUtil.sendEmail(toEmail,subject,message);
		try {
			System.out.println("----------------"+file.getResource().getURI().getPath());
			//mailUtil.sendEmailWithAttachment(toEmail, subject, message, file.getResource());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "ContactForm";
	}

}
