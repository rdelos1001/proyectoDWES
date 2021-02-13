package com.example.proyectoDWES.Entity;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Pedidos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int CodPed;
	@Column
	private Date fecha;
	@Column
	private boolean enviado;
	
	@Column
	private double importe;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Restaurante")
	private Restaurantes restaurante;
	
    @OneToMany(mappedBy = "pedido")
    private List<PedidosProductos> pedidosProductos;

    
	public Pedidos() {
		super();
	}
	
	

	public Pedidos(Date fecha, boolean enviado, double importe, Restaurantes restaurante,
			List<PedidosProductos> pedidosProductos) {
		this.fecha = fecha;
		this.enviado = enviado;
		this.importe = importe;
		this.restaurante = restaurante;
		this.pedidosProductos = pedidosProductos;
	}



	public int getCodPed() {
		return CodPed;
	}

	public void setCodPed(int codPed) {
		CodPed = codPed;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isEnviado() {
		return enviado;
	}

	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Restaurantes getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurantes restaurante) {
		this.restaurante = restaurante;
	}

	public List<PedidosProductos> getPedidosProductos() {
		return pedidosProductos;
	}

	public void setPedidosProductos(List<PedidosProductos> pedidosProductos) {
		this.pedidosProductos = pedidosProductos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedidos other = (Pedidos) obj;
		if (CodPed != other.CodPed)
			return false;
		if (enviado != other.enviado)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (Double.doubleToLongBits(importe) != Double.doubleToLongBits(other.importe))
			return false;
		if (pedidosProductos == null) {
			if (other.pedidosProductos != null)
				return false;
		} else if (!pedidosProductos.equals(other.pedidosProductos))
			return false;
		if (restaurante == null) {
			if (other.restaurante != null)
				return false;
		} else if (!restaurante.equals(other.restaurante))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedidos [CodPed=" + CodPed + ", fecha=" + fecha + ", enviado=" + enviado + ", importe=" + importe
				+ ", restaurante=" + restaurante + ", pedidosProductos=" + pedidosProductos + "]";
	}
    
    
}
