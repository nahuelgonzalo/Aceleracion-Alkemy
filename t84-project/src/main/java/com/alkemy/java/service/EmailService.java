package com.alkemy.java.service;

import java.util.List;

import com.alkemy.java.dto.UserDTO;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.objects.Attachments;

public interface EmailService {
	Response sendText(String to, String subject, String body, List<Attachments> attachments);
	Response sendHTML(String to, String subject, String body, List<Attachments> attachments);
	Response sendTemplateBienvenidaHTML(UserDTO user);
}
