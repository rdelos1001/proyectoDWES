package com.example.proyectoDWES.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyectoDWES.Entity.Categoria;
import com.example.proyectoDWES.Entity.Producto;
import com.example.proyectoDWES.Repository.ProductoRepository;

/**
 * Implementaci&oacute;n de la interfaz ProductoService gestionar los productos
 * @see ProductoService
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
@Service
public class ProductoServiceImpl implements ProductoService {

	/**
	 * Repositorio de producto para acceder a la base de datos producto
	 * @see Producto
	 * */
	@Autowired
	ProductoRepository repository;
	
	/**
	 * M&eacute;todo para actualizar el stock de un producto
	 * @param codProd - Identificador del producto del que se desea actualizar el stock
	 * @param cantidad - Cantidad a la que se actualizar&aacute; el stock
	 * @return prod - El producto con la cantidad ya actualizada
	 * @see Producto
	 * */
	@Override
	public Producto actualizarExistencias(int codProd, double cantidad){
		Producto prod =null;
		try {
			if(repository.findById(codProd).isPresent()) {
				prod =repository.findById(codProd).get();
				prod.setStock(cantidad);
				repository.save(prod);			
			}else {
				throw  new Exception("Código de producto incorrecto");
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return prod;
	}

	/**
	 * M&eacute;todo para buscar un producto seg&uacute;n su id
	 * @see Producto
	 * @see ArrayList
	 * @param id - id del producto a buscar
	 * @return El producto encontrado
	 * */
	@Override
	public Producto findById(int id) {
		return repository.findById(id).get();
	}
	
	/**
	 * M&eacute;todo para recuperar todos los Producto seg&uacute;n su Categoria
	 * @param cat - Categoria seg&uacute;n el cual se desea buscar
	 * @see ArrayList
	 * @see Producto
	 *  */
	@Override
	public ArrayList<Producto> findAllByCategoria(Categoria cat) {
		return repository.findAllByCategoria(cat);
	}

	/**
	 * M&eacute;todo para recuperar todos los Productos
	 * @see ArrayList
	 * @see Producto
	 *  */
	@Override
	public Iterable<Producto> findAll() {
		return this.repository.findAll();
	}
}
