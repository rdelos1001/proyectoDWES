package com.example.proyectoDWES.Service;

public interface MailService {

	public void send(String remitente, String destinatario,String asunto,String contenido) throws Exception;
}
