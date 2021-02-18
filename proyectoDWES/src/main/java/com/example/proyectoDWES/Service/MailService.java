package com.example.proyectoDWES.Service;

/**
 * Interfaz del servicio encargado del envio del email
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
public interface MailService {

	/**
	 * M&eacute;todo para el envio de correo
	 * @param remitente - Correo de quien se manda el email
	 * @param destinatario - Correo a quien se manda el email
	 * @param asunto - Asunto del email
	 * @param contenido - Contenido del email
	 * @throws Exception Excepci&oacute;n lanzada cuando no se puede mandar el email
	 * @see MailServiceImpl
	 * */
	public void send(String remitente, String destinatario,String asunto,String contenido) throws Exception;
}
