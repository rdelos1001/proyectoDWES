package com.example.proyectoDWES.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.proyectoDWES.Entity.Pedidos;
import com.example.proyectoDWES.Entity.Restaurantes;

public interface PedidosRepository extends CrudRepository<Pedidos, Integer>{

	public ArrayList<Pedidos> findAllByRestaurante(Restaurantes restaurante);

	public ArrayList<Pedidos> findAllByRestauranteAndEnviado(Restaurantes restauranteLogeado,boolean enviado);
}
