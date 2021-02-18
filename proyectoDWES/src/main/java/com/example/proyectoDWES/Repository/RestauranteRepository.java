package com.example.proyectoDWES.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.proyectoDWES.Entity.Restaurante;


/**
 * Repositorio JPA de Restaurante
 * @author Ra&uacute;l De los santos Cabrera
 * @see Restaurante
 * @see CrudRepository
 * */
public interface RestauranteRepository extends CrudRepository<Restaurante, Integer>{

	/**
	 * M&eacute;todo para encontrar el restaurante que tengan el correo y clave recibidas
	 * @param correo - Correo del restaurante que se desea buscar
	 * @param clave - Clave del restaurante que se desea buscar
	 * @return Restaurante encontrado
	 * */
	public Restaurante findByCorreoAndClave(String correo,String clave);

	/**
	 * M&eacute;todo para encontrar el restaurante que tengan el correo recibido
	 * @param correo - Correo del restaurante que se desea buscar
	 * @return Restaurante encontrado
	 * */
	public Restaurante findByCorreo(String correo);

}
