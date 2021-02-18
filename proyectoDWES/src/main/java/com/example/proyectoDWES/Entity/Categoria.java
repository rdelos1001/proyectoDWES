package com.example.proyectoDWES.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase de las Categorias la cual se conecta con la tabla categorias de la base de datos
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
@Entity(name="categorias")
public class Categoria {

	/**
	 * Identificador de la categoria de tipo int
	 * */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codCat;
	
	
	/**
	 * Nombre de la categoria de tipo String
	 * */
	@Column
	private String nombre;
	
	/**
	 * Descripci&oacute;n de la categoria de tipo String
	 * */
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

	/**
	 * M&eacute;todo to string auto generado de Categoria que muestra todos los campos 
	 * */
	@Override
	public String toString() {
		return "Categorias [codCat=" + codCat + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}
