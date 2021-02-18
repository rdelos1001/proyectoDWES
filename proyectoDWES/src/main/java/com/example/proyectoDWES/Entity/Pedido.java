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


/**
 * Clase de los Pedido la cual se conecta con la tabla pedidos de la base de datos
 * @author Ra&uacute;l De los santos Cabrera
 * @since 1.0.0
 * */
@Entity(name="pedidos")
public class Pedido {

	/**
	 * Indentificador de pedido de tipo int
	 * */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int CodPed;
	
	/**
	 * Fecha del pedido de tipo Date
	 * @see Date
	 * */
	@Column
	private Date fecha;
	
	/**
	 * Propiedad de enviado del pedido de tipo boolean
	 * <ul>
	 * 	<li>true - El pedido se ha enviado</li>
	 *  <li>false - El pedido no se ha enviado</li>
	 * </ul>
	 * */
	@Column
	private boolean enviado;
	
	/**
	 * Importe total del pedido
	 * */
	@Column
	private double importe;
	
	
	/**
	 * Restaurante al cual se le ha asignado el pedido
	 * @see Restaurante
	 * */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Restaurante")
	private Restaurante restaurante;
	
	/**
	 * Lista de pedido producto de este pedido
	 * @see PedidosProducto
	 * */
    @OneToMany(mappedBy = "pedido")
    private List<PedidosProducto> pedidosProductos;

    /**
     * Constructor del pedido sin ning&uacute;n par&aacute;metro
     * */
	public Pedido() {
		super();
	}
	
	
	/**
	 * Contructor del Pedido el cual recibe todos los parametros excepto el identificador ya que este se a&ntilde;adir&aacute; a la base de datos de forma autom&aacute;tica
	 * @param fecha - Fecha del pedido
	 * @param enviado - <ul><li>true - el pedido se ha enviado</li><li>false - el pedido no se ha enviado</li></ul>
	 * @param importe - Importe total del pedido
	 * @param restaurante - Restaurante del pedido
	 * @param pedidosProductos - Lista de pedidosProductos de este pedido
	 * @see Restaurante
	 * @see PedidosProducto
	 * */
	public Pedido(Date fecha, boolean enviado, double importe, Restaurante restaurante,
			List<PedidosProducto> pedidosProductos) {
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

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public List<PedidosProducto> getPedidosProductos() {
		return pedidosProductos;
	}

	public void setPedidosProductos(List<PedidosProducto> pedidosProductos) {
		this.pedidosProductos = pedidosProductos;
	}

	/**
	 * M&eacute;todo to string auto generado de Pedido que muestra todos los campos 
	 * */
	@Override
	public String toString() {
		return "Pedidos [CodPed=" + CodPed + ", fecha=" + fecha + ", enviado=" + enviado + ", importe=" + importe
				+ ", restaurante=" + restaurante + ", pedidosProductos=" + pedidosProductos + "]";
	}
    
    
}
