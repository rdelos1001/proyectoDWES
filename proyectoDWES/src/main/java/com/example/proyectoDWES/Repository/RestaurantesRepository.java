package com.example.proyectoDWES.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.proyectoDWES.Entity.Restaurantes;

public interface RestaurantesRepository extends CrudRepository<Restaurantes, Integer>{

	public Restaurantes findByCorreoAndClave(String correo,String clave);

	public Restaurantes findByCorreo(String correo);

}
