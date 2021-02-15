package com.example.proyectoDWES.Service;

import com.example.proyectoDWES.Entity.Pedidos;
import com.example.proyectoDWES.Entity.PedidosProductos;
import com.example.proyectoDWES.Entity.Productos;

public interface PedidosProductosService {

	public void anadirProducto(Pedidos pedidos, Productos producto, double cantidad);
	public double getCantidad(Pedidos pedido, Productos producto);
	public PedidosProductos getPedprodPorIdProd(int idProd,Pedidos pedido);
	public void actualizarPedidoProducto(PedidosProductos pedProd);
	public void quitarPedidoProducto(PedidosProductos pedProd);
}
