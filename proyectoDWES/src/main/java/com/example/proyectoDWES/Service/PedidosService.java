package com.example.proyectoDWES.Service;

import java.util.ArrayList;

import com.example.proyectoDWES.Entity.Pedidos;
import com.example.proyectoDWES.Entity.Productos;

public interface PedidosService {
	public ArrayList<Productos> getProductos(Pedidos pedido);

	public void anadirProducto(Pedidos pedidos, Productos producto);
}
