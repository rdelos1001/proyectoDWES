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


@Entity
public class Productos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codProd;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;
	
	@Column
	private double peso;
	
	@Column
	private double precio;
	
	@Column
	private double stock;
	
	@ManyToOne
	@JoinColumn(name="categoria")
	private Categorias categoria;
	
    @OneToMany(mappedBy = "productos")
    Set<PedidosProductos> pedidosProductos;

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

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	public Set<PedidosProductos> getPedidosProductos() {
		return pedidosProductos;
	}

	public void setPedidosProductos(Set<PedidosProductos> pedidosProductos) {
		this.pedidosProductos = pedidosProductos;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	
	
	@Override
	public String toString() {
		return "Productos [codProd=" + codProd + ", nombre=" + nombre + ", descripcion=" + descripcion + ", peso="
				+ peso + ", precio=" + precio + ", stock=" + stock + ", categoria=" + categoria + ", pedidosProductos="
				+ pedidosProductos + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Productos other = (Productos) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codProd != other.codProd)
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (pedidosProductos == null) {
			if (other.pedidosProductos != null)
				return false;
		} else if (!pedidosProductos.equals(other.pedidosProductos))
			return false;
		if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}
    
    
    
    
}
