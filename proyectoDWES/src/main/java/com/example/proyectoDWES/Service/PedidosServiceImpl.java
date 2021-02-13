package com.example.proyectoDWES.Service;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectoDWES.Entity.Pedidos;
import com.example.proyectoDWES.Entity.PedidosProductos;
import com.example.proyectoDWES.Entity.Productos;
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
	public void anadirProducto(Pedidos pedidos, Productos producto) {
		
	}	
	
}
