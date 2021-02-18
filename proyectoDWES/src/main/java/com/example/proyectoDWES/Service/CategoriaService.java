package com.example.proyectoDWES.Service;


import com.example.proyectoDWES.Entity.Categoria;
/**
 * Interfaz del servicio encargado del control a la base de datos de Categoria
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * @see Categoria
 * */
public interface CategoriaService {

	/**
	 * M&eacute;todo para obtener la categoria seg&uacute;n su nombre
	 * @see Categoria
	 * @param nombre - nombre del la categoria del cual se busca 
	 * @return Categoria buscada
	 * */
	public Categoria findByNombre(String nombre);
	
	/**
	 * M&eacute;todo para obtener todas las categorias
	 * @see Categoria
	 * @return Categoria buscada
	 * */
	public Iterable<Categoria> findAll();
}
