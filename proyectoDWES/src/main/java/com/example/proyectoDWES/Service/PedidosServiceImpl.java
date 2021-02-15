package com.example.proyectoDWES.Service;



import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectoDWES.Entity.Pedidos;
import com.example.proyectoDWES.Entity.PedidosProductos;
import com.example.proyectoDWES.Entity.Productos;
import com.example.proyectoDWES.Entity.Restaurantes;
import com.example.proyectoDWES.Repository.PedidosRepository;

@Service
public class PedidosServiceImpl implements PedidosService {
	
	
	@Autowired
	PedidosRepository repository;

	@Override
	public ArrayList<Productos> getProductos(Pedidos pedido) {
		ArrayList<Productos>productos=new ArrayList<Productos>();
		for(PedidosProductos producto:pedido.getPedidosProductos()) {
			productos.add(producto.getProductos());
		}
		return productos;
	}

	

	@Override
	public Pedidos getCarrito(Restaurantes restaurante) {
		Pedidos carrito=null;
		if(repository.findAllByRestauranteAndEnviado(restaurante, false).isEmpty()) {
			Pedidos nuevoCarrito = new Pedidos(new Date(),false,0,null,new ArrayList<PedidosProductos>());
			repository.save(nuevoCarrito);
			nuevoCarrito.setRestaurante(restaurante);
			repository.save(nuevoCarrito);
		}
		carrito=repository.findAllByRestauranteAndEnviado(restaurante, false).get(0);
		
		return carrito;
	}

	@Override
	public void actualizarPedido(Pedidos pedido) {
		try {
			if(repository.findById(pedido.getCodPed()).isPresent()) {
				Pedidos pedidoGuardar=repository.findById(pedido.getCodPed()).get();
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
	
}
