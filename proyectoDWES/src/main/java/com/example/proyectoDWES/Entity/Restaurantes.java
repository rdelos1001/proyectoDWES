package com.example.proyectoDWES.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Restaurantes {

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
	
	
	
	public Restaurantes() {
		super();
	}
	public Restaurantes(int codRes, String correo, String clave, String pais, int cP, String ciudad, String direccion) {
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurantes other = (Restaurantes) obj;
		if (CP != other.CP)
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (codRes != other.codRes)
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Restaurantes [codRes=" + codRes + ", correo=" + correo + ", clave=" + clave + ", pais=" + pais + ", CP="
				+ CP + ", ciudad=" + ciudad + ", direccion=" + direccion + "]";
	}

}
