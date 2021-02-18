package com.example.proyectoDWES.Service;

import com.example.proyectoDWES.Entity.Pedido;
import com.example.proyectoDWES.Entity.PedidosProducto;
import com.example.proyectoDWES.Entity.Producto;
/**
 * Interfaz del servicio encargado del control a la base de datos de PedidosProducto
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * @see PedidosProducto
 * */
public interface PedidoProductoService {

	/**
	 * M&eacute;todo para el añadir un producto a un pedido recibido
	 * @param pedidos - Pedido al que se a&ntilde;adir&aacute; el producto
	 * @param producto - Producto que se a&ntilde;adir&aacute; al pedido
	 * @param cantidad - Cantidad de producto que se a&ntilde;adir&aacute; al pedido
	 * */
	public void anadirProducto(Pedido pedidos, Producto producto, double cantidad);
	
	/**
	 * M&eacute;todo para obtener la cantidad de un producto en un pedido
	 * @see Pedido
	 * @see Producto
	 * @param pedido Pedido donde se busca la cantidad del producto
	 * @param producto Producto del cual se busca la cantidad
	 * @return cantidad del producto en el pedido
	 * */
	public double getCantidad(Pedido pedido, Producto producto);
	
	/**
	 * M&eacute;todo para el obtener el pedidoProducto de un id y pedido recibido
	 * @see Pedido
	 * @see Producto
	 * @param pedido Pedido donde se busca el PedidosProducto
	 * @param idProd Producto del cual se busca el PedidosProducto
	 * @return <ul>
	 * 	<li>PedidosProducto - Pedido producto que se est&aacute; buscando</li>
	 * <li>null - No se ha encontrado el PedidosProducto</li>
	 * </ul>
	 * */
	public PedidosProducto getPedprodPorIdProd(int idProd,Pedido pedido);
	
	/**
	 * M&eacute;todo para actualizar el pedidoProducto en la base de datos de un pedidoProducto recibido, donde si el PedidosProducto tiene una cantidad menor o igual a 0 la elimina 
	 *@param pedProd - Pedido que se desea actualizar
	 * */
	public void actualizarPedidoProducto(PedidosProducto pedProd);
	
	/**
	 * M&eacute;todo para el eliminar pedidoProducto de la base de datos
	 * @param pedProd - Producto que se desea eliminar
	 * */
	public void quitarPedidoProducto(PedidosProducto pedProd);
}
