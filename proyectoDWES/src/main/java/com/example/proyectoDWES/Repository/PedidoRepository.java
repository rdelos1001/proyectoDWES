package com.example.proyectoDWES.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.proyectoDWES.Entity.Pedido;
import com.example.proyectoDWES.Entity.Restaurante;


/**
 * Repositorio JPA de Pedido
 * @author Ra&uacute;l De los santos Cabrera
 * @see Pedido
 * @see CrudRepository
 * */
public interface PedidoRepository extends CrudRepository<Pedido, Integer>{

	/**
	 * M&eacute;todo para encontrar una todos los Pedido que tengan el restaurante recibido
	 * @param restaurante - restaurante que se desea buscar
	 * @see Restaurante
	 * @return La lista de pedidos de encontrada
	 * */
	public ArrayList<Pedido> findAllByRestaurante(Restaurante restaurante);

	/**
	 * M&eacute;todo para encontrar una todos los Pedido que tengan el restaurante recibido y el valor de enviado recibido
	 * @param restauranteLogeado - restaurante que se desea buscar
	 * @param enviado - valor de enviado (true o false)
	 * @see Restaurante
	 * @return La lista de pedidos de encontrada
	 * */
	public ArrayList<Pedido> findAllByRestauranteAndEnviado(Restaurante restauranteLogeado,boolean enviado);
}
