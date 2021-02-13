package com.example.proyectoDWES.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.proyectoDWES.Entity.Categorias;

public interface CategoriasRepository extends CrudRepository<Categorias, Integer>{
	public Categorias findByNombre(String Nombre);
	
	//@Query("");
}
