package com.example.proyectoDWES.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectoDWES.Entity.Pedido;
import com.example.proyectoDWES.Entity.PedidosProducto;
import com.example.proyectoDWES.Entity.Producto;
import com.example.proyectoDWES.Repository.PedidoProductoRepository;
import com.example.proyectoDWES.Repository.ProductoRepository;

/**
 * Implementaci&oacute;n de la interfaz PedidoProductoService gestionar los pedidosProductos
 * @see PedidoProductoService
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
@Service
public class PedidoProductoServiceImpl implements PedidoProductoService {

	/**
	 * Repositorio de pedidoProducto para acceder a la base de datos pedidoProducto
	 * @see PedidoProductoRepository
	 * */
	@Autowired
	PedidoProductoRepository repository;
	
	/**
	 * Repositorio de ProductoRepository para acceder a la base de datos de ProductoRepository
	 * @see ProductoRepository
	 * */
	@Autowired
	ProductoRepository prodRepository;
	
	/**
	 * M&eacute;todo para el añadir un producto a un pedido recibido
	 * @see Pedido
	 * @see Producto
	 * @param pedido Pedido donde se a&ntilde;ade el producto
	 * @param producto Producto que se a&ntilde;ade al pedido
	 * */
	@Override
	public void anadirProducto(Pedido pedido, Producto producto,double cantidad) {
		
		if(!repository.findByProductosAndPedido(producto,pedido).isEmpty()) {
			cantidad+=repository.findByProductosAndPedido(producto,pedido).get(0).getUnidades();
		}
			
		//cantidad=repository.findByProductos(producto).getUnidades()+cantidad;
		repository.save(new PedidosProducto(pedido,producto,cantidad));
	}

	
	/**
	 * M&eacute;todo para obtener la cantidad de un producto en un pedido
	 * @see Pedido
	 * @see Producto
	 * @param pedido Pedido donde se busca la cantidad del producto
	 * @param producto Producto del cual se busca la cantidad
	 * */
	@Override
	public double getCantidad(Pedido pedido, Producto producto) {
		double cantidad=0;
		for(PedidosProducto pedProd:pedido.getPedidosProductos()) {
			if(pedProd.getProductos().getNombre().equals(producto.getNombre())) {
				cantidad=pedProd.getUnidades();
			}
		}
		
		return cantidad;
	}

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
	@Override
	public PedidosProducto getPedprodPorIdProd(int idProd,Pedido pedido) {

		if(prodRepository.findById(idProd).isPresent()) {
			Producto producto=prodRepository.findById(idProd).get();
			ArrayList<PedidosProducto> pedProdproductos= repository.findByProductosAndPedido(producto, pedido);
			return pedProdproductos.get(0);
		}
		return null;
	}


	/**
	 * M&eacute;todo para actualizar el pedidoProducto en la base de datos de un pedidoProducto recibido, donde si el PedidosProducto tiene una cantidad menor o igual a 0 la elimina 
	 *@param pedProd - Pedido que se desea actualizar
	 * */
	@Override
	public void actualizarPedidoProducto(PedidosProducto pedProd) {
		try {
			if(repository.findById(pedProd.getCodPedProd()).isPresent()) {
				if(pedProd.getUnidades()>0) {
					PedidosProducto pedProdGuardar =repository.findById(pedProd.getCodPedProd()).get();
					pedProdGuardar.setPedido(pedProd.getPedido());
					pedProdGuardar.setUnidades(pedProd.getUnidades());
					pedProdGuardar.setProductos(pedProd.getProductos());
					repository.save(pedProdGuardar);					
				}else {
					repository.delete(repository.findById(pedProd.getCodPedProd()).get());
				}
			}else {
				throw new Exception("Pedido Producto no encontrado");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * M&eacute;todo para el eliminar pedidoProducto de la base de datos
	 * @param pedProd - Producto que se desea eliminar
	 * */
	@Override
	public void quitarPedidoProducto(PedidosProducto pedProd) {
		repository.delete(pedProd);
	}
	
	
	
}
