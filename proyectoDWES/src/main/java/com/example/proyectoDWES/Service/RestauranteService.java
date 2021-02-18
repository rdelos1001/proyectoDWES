package com.example.proyectoDWES.Service;

import com.example.proyectoDWES.Entity.Restaurante;

/**
 * Interfaz del servicio encargado del control a la base de datos de Restaurante
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * @see Restaurante
 * */
public interface RestauranteService {
	
	/**
	 * M&eacute;todo para logear un restaurante
	 * @param restaurante - Restaurante que se est&aacute; intentando logear
	 * @return <ul>
	 *  <li>true - El restaurante se ha logeado</li>
	 * 	<li>false - El restaurante no se ha logeado</li>
	 * </ul>
	 * */
	public boolean logear(Restaurante restaurante);

	/**
	 * M&eacute;todo para recuperar el restaurante en funci&oacute;n del correo y la clave
	 * @param correo Correo del restaurante que se desea buscar
	 * @param clave Clave del restaurante que se desea buscar
	 * @return El restaurante que encontrado
	 * @see Restaurante
	 * */
	public Restaurante getRestauranteByCorreoAndClave(String correo, String clave);
}
