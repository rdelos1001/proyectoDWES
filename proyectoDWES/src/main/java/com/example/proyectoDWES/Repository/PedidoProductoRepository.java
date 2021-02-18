package com.example.proyectoDWES.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.proyectoDWES.Entity.Pedido;
import com.example.proyectoDWES.Entity.PedidosProducto;
import com.example.proyectoDWES.Entity.Producto;


/**
 * Repositorio JPA de PedidosProducto
 * @author Ra&uacute;l De los santos Cabrera
 * @see PedidosProducto
 * @see CrudRepository
 * */
public interface PedidoProductoRepository extends CrudRepository<PedidosProducto, Integer>{

	/**
	 * M&eacute;todo para encontrar una todos los PedidosProducto que tengan el producto y pedido recibido
	 * @param producto - Producto que se desea buscar
	 * @param pedido - Pedido que se desea buscar
	 * @see Producto
	 * @see Pedido
	 * @return La Categoria de encontrada
	 * */
	ArrayList<PedidosProducto> findByProductosAndPedido(Producto producto,Pedido pedido);
}
