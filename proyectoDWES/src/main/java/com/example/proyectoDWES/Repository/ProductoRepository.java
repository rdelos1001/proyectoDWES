package com.example.proyectoDWES.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.proyectoDWES.Entity.Categoria;
import com.example.proyectoDWES.Entity.Producto;

/**
 * Repositorio JPA de Producto
 * @author Ra&uacute;l De los santos Cabrera
 * @see Producto
 * @see CrudRepository
 * */
public interface ProductoRepository extends CrudRepository<Producto, Integer>{
	/**
	 * M&eacute;todo para encontrar una todos los Producto que tengan la Categoria recibido
	 * @param categoria - categoria de la que se quieren sacar los productos
	 * @see Categoria
	 * @return La lista de pedidos de encontrada
	 * */
	public ArrayList<Producto> findAllByCategoria(Categoria categoria);
}
