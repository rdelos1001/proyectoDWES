package com.example.proyectoDWES.Service;

import java.util.ArrayList;

import com.example.proyectoDWES.Entity.Pedidos;
import com.example.proyectoDWES.Entity.Productos;
import com.example.proyectoDWES.Entity.Restaurantes;

public interface PedidosService {
	public ArrayList<Productos> getProductos(Pedidos pedido);

	public Pedidos getCarrito(Restaurantes restaurante);
	public void actualizarPedido(Pedidos pedido);
}
