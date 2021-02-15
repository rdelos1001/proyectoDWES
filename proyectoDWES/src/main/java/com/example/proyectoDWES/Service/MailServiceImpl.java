package com.example.proyectoDWES.Service;


import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class MailServiceImpl implements MailService{

	
    @Autowired
    private final JavaMailSender javaMailSender=new JavaMailSenderImpl();

    @Override
	public void send(String remitente, String destinatario,String asunto,String contenido) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom(remitente);
		mimeMessageHelper.setTo(destinatario);
		mimeMessageHelper.setSubject(asunto);
		mimeMessageHelper.setText(contenido);
		mimeMessageHelper.setSentDate(new Date());
		javaMailSender.send(mimeMessage);
    }
}
