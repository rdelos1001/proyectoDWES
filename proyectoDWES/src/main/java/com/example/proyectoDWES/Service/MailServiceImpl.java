package com.example.proyectoDWES.Service;


import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Implementaci&oacute;n de la interfaz MailService para enviar los emails
 * @see MailService
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
@Service
public class MailServiceImpl implements MailService{

	/**
	 * Implementaci&oacute;n de la interfez JavaMailSender necesario para enviar el correo
	 * @see JavaMailSender
	 * @see JavaMailSenderImpl
	 * */
    @Autowired
    private final JavaMailSender javaMailSender=new JavaMailSenderImpl();

    /**
	 * M&eacute;todo para el envio de correo
	 * @param remitente Correo desde donde se enviar&aacute;n los correos
	 * @param destinatario Correo hacia donde se enviar&aacute;n los correos
	 * @param asunto Asunto del email
	 * @param contenido en texto que se desea enviar
     * */
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
