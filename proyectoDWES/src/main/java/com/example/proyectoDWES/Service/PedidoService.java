package com.example.proyectoDWES.Service;

import java.util.ArrayList;

import com.example.proyectoDWES.Entity.Pedido;
import com.example.proyectoDWES.Entity.Producto;
import com.example.proyectoDWES.Entity.Restaurante;

/**
 * Interfaz del servicio encargado del control a la base de datos de los Pedidos
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * @see Pedido
 * */
public interface PedidoService {
	
	/**
	 * M&eacute;todo para obtener los productos un pedido recibido
	 * @param pedido - Del cual se desea buscar la lista de productos
	 * @return productos - Lista de productos del pedido
	 * */
	public ArrayList<Producto> getProductos(Pedido pedido);
	
	/**
	 * M&eacute;todo para obtener el carrito de un restaurante
	 * @param restaurante - Del cual se desea buscar el carrito
	 * @return carrito - Pedido correspondiente al carrito del restaurante
	 * */
	public Pedido getCarrito(Restaurante restaurante);
	
	/**
	 * M&eacute;todo para actualizar el Pedido recibido
	 * @param pedido - Pedido recibido que se desea actualizar
	 * @see Pedido
	 *  */
	public void actualizarPedido(Pedido pedido);

	/**
	 * M&eacute;todo para recuperar todos los pedidos seg&uacute;n su restaurante y si est&aacute; enviado o no
	 * @param restauranteLogeado - Restaurante seg&uacute;n el cual se desea buscar
	 * @param b - valor de enviado true: s&iacute; false: no
	 * @return La lista de todos los pedidos encontrados
	 * @see ArrayList
	 * @see Pedido
	 *  */
	public ArrayList<Pedido> findAllByRestauranteAndEnviado(Restaurante restauranteLogeado, boolean b);

	/**
	 * M&eacute;todo para guardar un pedido en la base de datos
	 * @param pedido - pedido a guardar
	 * @see Pedido
	 *  */
	public void save(Pedido pedido);
}
