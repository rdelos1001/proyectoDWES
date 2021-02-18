package com.example.proyectoDWES.Entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Clase de los Productos la cual se conecta con la tabla productos de la base de datos
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
@Entity(name="productos")
public class Producto {

	/**
	 * Identificador del producto de tipo int
	 * */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codProd;
	
	/**
	 * Nombre del producto de tipo String
	 * */
	@Column
	private String nombre;
	
	/**
	 * Descripci&oacute;n del producto de tipo String
	 * */
	@Column
	private String descripcion;
	
	/**
	 * Peso de una unidad del producto de tipo double
	 * */
	@Column
	private double peso;
	
	
	/**
	 * Precio de una unidad del producto de tipo double
	 * */
	@Column
	private double precio;
	
	/**
	 * Cantidad de unidades disponibles que hay en stock del producto de tipo double
	 * */
	@Column
	private double stock;
	
	/**
	 * Categoria a la que pertenece el producto de tipo Categoria
	 * @see Categoria
	 * */
	@ManyToOne
	@JoinColumn(name="categoria")
	private Categoria categoria;
	
	
	/**
	 * Lista de pedidos productos en los que aparece este producto
	 * @see PedidosProducto
	 * */
    @OneToMany(mappedBy = "productos")
    private Set<PedidosProducto> pedidosProductos;

	public int getCodProd() {
		return codProd;
	}

	public void setCodProd(int codProd) {
		this.codProd = codProd;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set<PedidosProducto> getPedidosProductos() {
		return pedidosProductos;
	}

	public void setPedidosProductos(Set<PedidosProducto> pedidosProductos) {
		this.pedidosProductos = pedidosProductos;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * M&eacute;todo toString que muestra todos los campos del Producto
	 * @see Categoria
	 * @see PedidosProducto
	 * */
	@Override
	public String toString() {
		return "Productos [codProd=" + codProd + ", nombre=" + nombre + ", descripcion=" + descripcion + ", peso="
				+ peso + ", precio=" + precio + ", stock=" + stock + ", categoria=" + categoria + ", pedidosProductos="
				+ pedidosProductos + "]";
	}   
}
