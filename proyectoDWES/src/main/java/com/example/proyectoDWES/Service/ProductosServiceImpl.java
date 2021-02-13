package com.example.proyectoDWES.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectoDWES.Entity.Productos;
import com.example.proyectoDWES.Repository.ProductosRepository;

@Service
public class ProductosServiceImpl implements ProductosService {

	@Autowired
	ProductosRepository repository;
	
	@Override
	public Productos actualizarExistencias(int codProd, int cantidad) {
		Productos prod =repository.findById(codProd).get();
		prod.setStock(prod.getStock()-cantidad);
		repository.save(prod);
		return prod;
	}
}
