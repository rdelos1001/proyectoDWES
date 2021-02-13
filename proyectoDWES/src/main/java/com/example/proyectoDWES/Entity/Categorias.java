package com.example.proyectoDWES.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categorias {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codCat;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;

	public int getCodCat() {
		return codCat;
	}

	public void setCodCat(int codCat) {
		this.codCat = codCat;
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

	@Override
	public String toString() {
		return "Categorias [codCat=" + codCat + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}
