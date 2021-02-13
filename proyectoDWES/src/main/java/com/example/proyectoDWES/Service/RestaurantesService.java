package com.example.proyectoDWES.Service;

import com.example.proyectoDWES.Entity.Restaurantes;

public interface RestaurantesService {
	
	public boolean logear(Restaurantes restaurante);

	public Restaurantes getRestauranteByCorreoAndClave(String correo, String clave);
}
