package com.example.proyectoDWES.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.proyectoDWES.Entity.Categoria;

/**
 * Repositorio JPA de Categoria
 * @author Ra&uacute;l De los santos Cabrera
 * @see Categoria
 * @see CrudRepository
 * */
public interface CategoriaRepository extends CrudRepository<Categoria, Integer>{
	
	/**
	 * M&eacute;todo para encontrar una Categoria seg&uacute;n su nombre
	 * @param Nombre - Nombre de la categoria que se desea buscar
	 * @return La Categoria de encontrada
	 * */
	public Categoria findByNombre(String Nombre);
}
