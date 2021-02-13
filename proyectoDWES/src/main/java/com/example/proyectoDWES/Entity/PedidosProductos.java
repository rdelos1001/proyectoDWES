package com.example.proyectoDWES.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PedidosProductos implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5506852841205641322L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int CodPedProd;

    @ManyToOne
    @JoinColumn(name = "CodPed")
    private Pedidos pedido;

    @ManyToOne
    @JoinColumn(name = "CodProd")
    private Productos productos;

    @Column(name="unidades")
    private double unidades;

    
    
    public PedidosProductos() {
    	
    }
    
	public PedidosProductos(Pedidos pedido, Productos productos, double unidades) {
		this.pedido = pedido;
		this.productos = productos;
		this.unidades = unidades;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidosProductos other = (PedidosProductos) obj;
		if (CodPedProd != other.CodPedProd)
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (productos == null) {
			if (other.productos != null)
				return false;
		} else if (!productos.equals(other.productos))
			return false;
		if (Double.doubleToLongBits(unidades) != Double.doubleToLongBits(other.unidades))
			return false;
		return true;
	}

	public int getCodPedProd() {
		return CodPedProd;
	}

	public void setCodPedProd(int codPedProd) {
		CodPedProd = codPedProd;
	}

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}

	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	public double getUnidades() {
		return unidades;
	}

	public void setUnidades(double unidades) {
		this.unidades = unidades;
	}


    
}
