package com.example.proyectoDWES.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Clase de los pedidos productos la cual se conecta con la tabla pedidos_productos de la base de datos
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
@Entity(name="pedidos_productos")
public class PedidosProducto implements Serializable{

	private static final long serialVersionUID = 5506852841205641322L;

	/**
	 * M&eacute;todo toString manipulado para mostrar todos los atributos pero los campos de pedido y producto muestra solo el identificador.
	 * */
	@Override
	public String toString() {
		return "PedidosProductos [CodPedProd=" + CodPedProd + ", pedido=" + pedido.getCodPed() + ", productos=" + productos.getCodProd()
				+ ", unidades=" + unidades + "]";
	}

	/**
	 * Identificador del pedido producto de tipo integer
	 * */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int CodPedProd;

	/**
	 * Pedido al que pertenece el pedido producto de tipo Pedido
	 * @see Pedido
	 * */
    @ManyToOne
    @JoinColumn(name = "CodPed")
    private Pedido pedido;
    

    /**
     * Producto al que pertenece el pedido producto de tipo Producto
     * @see Producto
     * */
    @ManyToOne
    @JoinColumn(name = "CodProd")
    private Producto productos;

    /**
     * Unidades que del producto que estan en el pedido de tipo double
     * */
    @Column(name="unidades")
    private double unidades;

    public PedidosProducto() {
    	
    }
    
    /**
	 * Contructor del Pedido el cual recibe todos los parametros excepto el identificador ya que este se añadir&aacute; a la base de datos de forma autom&aacute;tica
     * @param pedido - Pedido de este pedidoProducto
     * @param productos - Producto de este pedidoProducto
     * @param unidades - unidades del producto que se añaden al pedido
     * @see Pedido
     * @see Producto
     * */
	public PedidosProducto(Pedido pedido, Producto productos, double unidades) {
		this.pedido = pedido;
		this.productos = productos;
		this.unidades = unidades;
	}

	public int getCodPedProd() {
		return CodPedProd;
	}

	public void setCodPedProd(int codPedProd) {
		CodPedProd = codPedProd;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProductos() {
		return productos;
	}

	public void setProductos(Producto productos) {
		this.productos = productos;
	}

	public double getUnidades() {
		return unidades;
	}

	public void setUnidades(double unidades) {
		this.unidades = unidades;
	}


    
}
