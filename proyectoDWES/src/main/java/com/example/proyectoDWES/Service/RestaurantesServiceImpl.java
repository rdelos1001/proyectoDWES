package com.example.proyectoDWES.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectoDWES.Entity.Restaurantes;
import com.example.proyectoDWES.Repository.RestaurantesRepository;

@Service
public class RestaurantesServiceImpl implements RestaurantesService {
	
	
	@Autowired
	RestaurantesRepository repository;
	
	public Restaurantes getRestauranteByCorreoAndClave(String correo,String clave) {
		
		return repository.findByCorreoAndClave(correo, clave);
	}
	
	public boolean logear(Restaurantes restaurante) {		
		if(repository.findByCorreoAndClave(restaurante.getCorreo(),restaurante.getClave())!=null) {
			return true;
		}
		return false;
	}
}
