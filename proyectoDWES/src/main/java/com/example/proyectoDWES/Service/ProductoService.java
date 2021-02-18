package com.example.proyectoDWES.Service;

import java.util.ArrayList;

import com.example.proyectoDWES.Entity.Categoria;
import com.example.proyectoDWES.Entity.Producto;

/**
 * Interfaz del servicio encargado del control a la base de datos de Producto
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * @see Producto
 * */
public interface ProductoService {

	/**
	 * M&eacute;todo para actualizar el stock de un producto
	 * @see Producto
	 * @param codProd - id del producto que se desea actualizar sus existencias
	 * @param cantidad - nueva cantidad del producto
	 * @return El producto con la nueva cantidad asignada
	 * */
	public Producto actualizarExistencias(int codProd, double cantidad);
	
	/**
	 * M&eacute;todo para buscar un producto seg&uacute;n su id
	 * @see Producto
	 * @see ArrayList
	 * @param id - id del producto a buscar
	 * @return El producto encontrado
	 * */
	public Producto findById(int id);
	
	/**
	 * M&eacute;todo para recuperar todos los Producto seg&uacute;n su Categoria
	 * @param cat - Categoria seg&uacute;n el cual se desea buscar
	 * @return La lista de Productos encontrados
	 * @see ArrayList
	 * @see Producto
	 *  */
	public ArrayList<Producto> findAllByCategoria(Categoria cat);
	
	/**
	 * M&eacute;todo para recuperar todos los Productos
	 * @see ArrayList
	 * @see Producto
	 * @return Todos los productos de la base de datos
	 *  */
	public Iterable<Producto> findAll();
	
}
