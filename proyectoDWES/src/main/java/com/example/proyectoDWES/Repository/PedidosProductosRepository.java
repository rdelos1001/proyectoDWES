package com.example.proyectoDWES.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.proyectoDWES.Entity.Pedidos;
import com.example.proyectoDWES.Entity.PedidosProductos;
import com.example.proyectoDWES.Entity.Productos;

public interface PedidosProductosRepository extends CrudRepository<PedidosProductos, Integer>{

	ArrayList<PedidosProductos> findByProductosAndPedido(Productos producto,Pedidos pedido);
}
