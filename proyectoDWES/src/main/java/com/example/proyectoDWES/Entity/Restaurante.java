package com.example.proyectoDWES.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase de los Restaurantes la cual se conecta con la tabla restaurantes de la base de datos
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
@Entity(name="restaurantes")
public class Restaurante {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="CodRes")
	private int codRes;
	
	@Column(name="Correo")
	private String correo;
	
	@Column(name="Clave")
	private String clave;
	
	@Column(name="Pais")
	private String pais;
	
	@Column(name="CP")
	private int CP ;
	
	@Column(name="Ciudad")
	private String ciudad;
	
	@Column(name="Direccion")
	private String direccion;
	
	
	/**
     * Constructor del pedido sin ning&uacute;n par&aacute;metro
	 * */
	public Restaurante() {
		super();
	}
	
	/**
	 * Contructor del Restaurante el cual recibe todos los parametros
	 * @param codRes - Identificador de este restaurante
	 * @param correo - Correo electronico de este restaurante
	 * @param clave - Contraseña de este restaurante
	 * @param pais - Pais de este restaurante
	 * @param cP - C&oacute;digo postal de este restaurante
	 * @param ciudad - Ciudad de este restaurante
	 * @param direccion - Direcci&oacute;n de este restaurante
	 * */
	public Restaurante(int codRes, String correo, String clave, String pais, int cP, String ciudad, String direccion) {
		super();
		this.codRes = codRes;
		this.correo = correo;
		this.clave = clave;
		this.pais = pais;
		CP = cP;
		this.ciudad = ciudad;
		this.direccion = direccion;
	}
	public int getCodRes() {
		return codRes;
	}
	public void setCodRes(int codRes) {
		this.codRes = codRes;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getCP() {
		return CP;
	}
	public void setCP(int cP) {
		CP = cP;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public String toString() {
		return "Restaurantes [codRes=" + codRes + ", correo=" + correo + ", clave=" + clave + ", pais=" + pais + ", CP="
				+ CP + ", ciudad=" + ciudad + ", direccion=" + direccion + "]";
	}

}
