package com.example.proyectoDWES.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectoDWES.Entity.Restaurante;
import com.example.proyectoDWES.Repository.RestauranteRepository;

/**
 * Implementaci&oacute;n de la interfaz RestauranteService gestionar los restaurantes
 * @see RestauranteService
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
@Service
public class RestauranteServiceImpl implements RestauranteService {
	
	/**
	 * Repositorio de Restaurante para acceder a la base de datos Restaurante
	 * @see RestauranteRepository
	 * */
	@Autowired
	RestauranteRepository repository;
	
	/**
	 * M&eacute;todo para recuperar el restaurante en funci&oacute;n del correo y la clave
	 * @param correo Correo del restaurante que se desea buscar
	 * @param clave Clave del restaurante que se desea buscar
	 * @return El restaurante que encontrado
	 * @see Restaurante
	 * */
	public Restaurante getRestauranteByCorreoAndClave(String correo,String clave) {
		return repository.findByCorreoAndClave(correo, clave);
	}
	
	/**
	 * M&eacute;todo para logear un restaurante
	 * @param restaurante - Restaurante que se est&aacute; intentando logear
	 * @return <ul>
	 *  <li>true - El restaurante se ha logeado</li>
	 * 	<li>false - El restaurante no se ha logeado</li>
	 * </ul>
	 * @see Restaurante
	 * */
	public boolean logear(Restaurante restaurante) {		
		if(repository.findByCorreoAndClave(restaurante.getCorreo(),restaurante.getClave())!=null) {
			return true;
		}
		return false;
	}
}
