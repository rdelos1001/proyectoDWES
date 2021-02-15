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
	public Productos actualizarExistencias(int codProd, double cantidad){
		Productos prod =null;
		try {
			if(repository.findById(codProd).isPresent()) {
				prod =repository.findById(codProd).get();
				prod.setStock(cantidad);
				repository.save(prod);			
			}else {
				throw  new Exception("Código de producto incorrecto");
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return prod;
	}
}
