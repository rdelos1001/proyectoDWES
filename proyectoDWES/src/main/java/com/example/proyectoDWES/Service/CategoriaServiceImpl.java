package com.example.proyectoDWES.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectoDWES.Entity.Categoria;
import com.example.proyectoDWES.Repository.CategoriaRepository;

/**
 * Implementaci&oacute;n de la interfaz CategoriaService gestionar las Categorias
 * @see PedidoProductoService
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
@Service
public class CategoriaServiceImpl implements CategoriaService {

	
	/**
	 * Repositorio de catRepository para acceder a la base de datos de catRepository
	 * @see CategoriaRepository
	 * */
	@Autowired
	CategoriaRepository repository;

	/**
	 * M&eacute;todo para obtener la categoria seg&uacute;n su nombre
	 * @see Categoria
	 * @param nombre - nombre del la categoria del cual se busca 
	 * @return Categoria buscada
	 * */
	@Override
	public Categoria findByNombre(String nombre) {
		return this.repository.findByNombre(nombre);
	}

	/**
	 * M&eacute;todo para obtener todas las categorias
	 * @see Categoria
	 * @return Categoria buscada
	 * */
	@Override
	public Iterable<Categoria> findAll() {
		return this.repository.findAll();
	}
	

	
	
}
