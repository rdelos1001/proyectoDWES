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
		
		if(!repository.findByProductosAndPedido(producto,pedido).isEmpty()) {
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

		if(prodRepository.findById(idProd).isPresent()) {
			Productos producto=prodRepository.findById(idProd).get();
			ArrayList<PedidosProductos> pedProdproductos= repository.findByProductosAndPedido(producto, pedido);
			return pedProdproductos.get(0);
		}
		return null;
	}



	@Override
	public void actualizarPedidoProducto(PedidosProductos pedProd) {
		try {
			if(repository.findById(pedProd.getCodPedProd()).isPresent()) {
				if(pedProd.getUnidades()>0) {
					PedidosProductos pedProdGuardar =repository.findById(pedProd.getCodPedProd()).get();
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



	@Override
	public void quitarPedidoProducto(PedidosProductos pedProd) {
		repository.delete(pedProd);
	}
	
	
	
}
