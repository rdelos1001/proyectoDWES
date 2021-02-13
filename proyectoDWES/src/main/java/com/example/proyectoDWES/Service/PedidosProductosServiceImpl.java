package com.example.proyectoDWES.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectoDWES.Entity.Pedidos;
import com.example.proyectoDWES.Entity.PedidosProductos;
import com.example.proyectoDWES.Entity.Productos;
import com.example.proyectoDWES.Repository.PedidosProductosRepository;
import com.example.proyectoDWES.Repository.ProductosRepository;

@Service
public class PedidosProductosServiceImpl implements PedidosProductosService {

	@Autowired
	PedidosProductosRepository repository;
	
	
	@Autowired
	ProductosRepository prodRepository;
	
	@Override
	public void anadirProducto(Pedidos pedido, Productos producto,double cantidad) {
		
		if(repository.findByProductosAndPedido(producto,pedido).size()>=1) {
			cantidad+=repository.findByProductosAndPedido(producto,pedido).get(0).getUnidades();
		}
			
		//cantidad=repository.findByProductos(producto).getUnidades()+cantidad;
		repository.save(new PedidosProductos(pedido,producto,cantidad));
	}

	
	
	@Override
	public double getCantidad(Pedidos pedido, Productos producto) {
		double cantidad=0;
		for(PedidosProductos pedProd:pedido.getPedidosProductos()) {
			if(pedProd.getProductos().getNombre().equals(producto.getNombre())) {
				cantidad=pedProd.getUnidades();
			}
		}
		
		return cantidad;
	}

	@Override
	public PedidosProductos getPedprodPorIdProd(int idProd,Pedidos pedido) {

		Productos producto=prodRepository.findById(idProd).get();
		ArrayList<PedidosProductos> pedProdproductos= repository.findByProductosAndPedido(producto, pedido);
		
		return pedProdproductos.get(0);
	}



	@Override
	public void actualizarProducto(PedidosProductos pedProd) {
		repository.save(pedProd);
	}



	@Override
	public void quitarPedidoProducto(PedidosProductos pedProd) {
		repository.delete(pedProd);
	}
	
	
	
}
