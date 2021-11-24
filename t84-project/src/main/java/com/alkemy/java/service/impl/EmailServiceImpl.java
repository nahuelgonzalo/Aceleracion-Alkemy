package com.alkemy.java.service.impl;

import com.alkemy.java.dto.UserDTO;
import com.alkemy.java.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private SendGrid sendGridClient;
    
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${from.email}")
    private String fromEmailAddress;
    
    @Value("classpath:/email/templates/logo.png")
	private Resource logoFile;

    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public Response sendText(String to, String subject, String body, List<Attachments> attachments) {
        return sendEmail(fromEmailAddress, to, subject, new Content("text/plain", body), attachments);
    }

    @Override
    public Response sendHTML(String to, String subject, String body, List<Attachments> attachments) {
        return sendEmail(fromEmailAddress, to, subject, new Content("text/html", body), attachments);
    }
    
    private Response sendEmail(String from, String to, String subject, Content content, List<Attachments> attachments) {
        Mail mail = new Mail(new Email(from), subject, new Email(to), content);
        if(attachments!= null)attachments.forEach(mail::addAttachments);
        Request request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sendGridClient.api(request);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        return response;
    }
    
    @Override
	public Response sendTemplateBienvenidaHTML(UserDTO user) {
		//Declaracion de variables
		String subject = "¡Te damos la bienvenida " + user.getName() + '!';
		String titulo = "¡Felicitaciones y bienvenido/a " + user.getName() + '!';
		String welcomeMsg = "Gracias por sumarte a participar. Contigo <strong>\"Somos Más\"</strong>.";
		List<String> contactos = new ArrayList<>();
		contactos.add("<strong>Mail:</strong> somosfundacionmas@gmail.com");
		contactos.add("<strong>Instagram:</strong> SomosMás");
		contactos.add("<strong>Facebook:</strong> Somos_Más");
		contactos.add("<strong>Teléfono de contacto:</strong> 1160112988");
		
		//Se setean las variables para Thymeleaf
		Context thymeleafContext = new Context();
	    thymeleafContext.setVariable("logo", "logo");
	    thymeleafContext.setVariable("titulo", titulo);
	    thymeleafContext.setVariable("texto", welcomeMsg);
	    thymeleafContext.setVariable("contactos",contactos.toArray());
	    
	    //Se genera el Body del email de bienvenida
	    String htmlBody = templateEngine.process("plantilla_email.html", thymeleafContext);
	    
	    //Se agrega el logo como archivo adjunto "inline" para que aparezca en el Body
	    List<Attachments> attachments = new ArrayList<>();
	    try {
			attachments.add(new Attachments
			        .Builder(logoFile.getFilename(), logoFile.getInputStream())
			        .withDisposition("inline")
			        .withContentId(logoFile.getFilename())
			        .withType("image/png")
			        .build());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	    
	    return sendHTML(user.getEmail(), subject, htmlBody, attachments);
	}
}
