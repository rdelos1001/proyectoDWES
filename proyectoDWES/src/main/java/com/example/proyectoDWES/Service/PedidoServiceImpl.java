package com.example.proyectoDWES.Service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectoDWES.Entity.Pedido;
import com.example.proyectoDWES.Entity.PedidosProducto;
import com.example.proyectoDWES.Entity.Producto;
import com.example.proyectoDWES.Entity.Restaurante;
import com.example.proyectoDWES.Repository.PedidoRepository;

/**
 * Implementaci&oacute;n de la interfaz PedidoService gestionar los pedidos
 * @see PedidoService
 * @author Raúl De los santos Cabrera
 * @since 1.0.0
 * */
@Service
public class PedidoServiceImpl implements PedidoService {
	
	/**
	 * Repositorio de pedidoProducto para acceder a la base de datos pedidoProducto
	 * @see PedidoRepository
	 * */
	@Autowired
	PedidoRepository repository;

	/**
	 * M&eacute;todo para obtener los productos un pedido recibido
	 * @param pedido - Del cual se desea buscar la lista de productos
	 * @return productos - Lista de productos del pedido
	 * */
	@Override
	public ArrayList<Producto> getProductos(Pedido pedido) {
		ArrayList<Producto>productos=new ArrayList<Producto>();
		for(PedidosProducto producto:pedido.getPedidosProductos()) {
			productos.add(producto.getProductos());
		}
		return productos;
	}

	
	/**
	 * M&eacute;todo para obtener el carrito de un restaurante
	 * @param restaurante - Del cual se desea buscar el carrito
	 * @return carrito - Pedido correspondiente al carrito del restaurante
	 * */
	@Override
	public Pedido getCarrito(Restaurante restaurante) {
		Pedido carrito=null;
		if(repository.findAllByRestauranteAndEnviado(restaurante, false).isEmpty()) {
			Pedido nuevoCarrito = new Pedido(new Date(),false,0,null,new ArrayList<PedidosProducto>());
			repository.save(nuevoCarrito);
			nuevoCarrito.setRestaurante(restaurante);
			repository.save(nuevoCarrito);
		}
		carrito=repository.findAllByRestauranteAndEnviado(restaurante, false).get(0);
		
		return carrito;
	}

	/**
	 * M&eacute;todo para actualizar el Pedido recibido
	 * @param pedido - Pedido recibido que se desea actualizar
	 * @see Pedido
	 *  */
	@Override
	public void actualizarPedido(Pedido pedido) {
		try {
			if(repository.findById(pedido.getCodPed()).isPresent()) {
				Pedido pedidoGuardar=repository.findById(pedido.getCodPed()).get();
				pedidoGuardar.setEnviado(pedido.isEnviado());
				pedidoGuardar.setFecha(pedido.getFecha());
				pedidoGuardar.setImporte(pedido.getImporte());
				pedidoGuardar.setPedidosProductos(pedido.getPedidosProductos());
				pedidoGuardar.setRestaurante(pedido.getRestaurante());
				repository.save(pedidoGuardar);
			}else {
				throw new Exception("Código de pedido incorrecto");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M&eacute;todo para recuperar todos los pedidos seg&uacute;n su restaurante y si est&aacute; enviado o no
	 * @param restauranteLogeado - Restaurante seg&uacute;n el cual se desea buscar
	 * @param b - valor de enviado true: s&iacute; false: no
	 * @return La lista de todos los pedidos encontrados
	 * @see ArrayList
	 * @see Pedido
	 *  */
	@Override
	public ArrayList<Pedido> findAllByRestauranteAndEnviado(Restaurante restauranteLogeado, boolean b) {
		return this.repository.findAllByRestauranteAndEnviado(restauranteLogeado, b);
	}

	/**
	 * M&eacute;todo para guardar un pedido en la base de datos
	 * @param pedido - pedido a guardar
	 * @see Pedido
	 *  */
	@Override
	public void save(Pedido pedido) {
		this.repository.save(pedido);
	}	
	
}
