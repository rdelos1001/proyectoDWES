package com.example.proyectoDWES.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.proyectoDWES.Entity.Categorias;
import com.example.proyectoDWES.Entity.Productos;

public interface ProductosRepository extends CrudRepository<Productos, Integer>{
	public ArrayList<Productos> findAllByCategoria(Categorias categoria);
}
