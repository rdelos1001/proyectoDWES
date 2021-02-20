package com.example.proyectoDWES.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.proyectoDWES.Entity.Categoria;
import com.example.proyectoDWES.Entity.Pedido;
import com.example.proyectoDWES.Entity.PedidosProducto;
import com.example.proyectoDWES.Entity.Producto;
import com.example.proyectoDWES.Entity.Restaurante;
import com.example.proyectoDWES.Service.CategoriaService;
import com.example.proyectoDWES.Service.MailService;
import com.example.proyectoDWES.Service.PedidoProductoService;
import com.example.proyectoDWES.Service.PedidoService;
import com.example.proyectoDWES.Service.ProductoService;
import com.example.proyectoDWES.Service.RestauranteService;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

/**
 * @author Ra&uacute;l De los santos Cabrera
 * @version 1.0.0
 * */
@Controller
public class ApplicationController {

	/**
	 * Restaurante actualmente logeado
	 * */
	private Restaurante restauranteLogeado=null;
	
	/**
	 * Mensajes de error
	 * */
	private ArrayList<String> errores=new ArrayList<String>();
	
	
	/**
	 * Servicio para enviar el email de compra
	 * */
	@Autowired
	private MailService mailService;
	
	
	/**
	 * Servicio para gestionar los restaurantes
	 * */
	@Autowired
	private RestauranteService restauranteservice;
	
	@Autowired
	private CategoriaService catService;
	
		
	/**
	 * Servicio para gestionar los pedidos
	 * */	
	@Autowired
	private PedidoService pedidosservice;
	
	
	/**
	 * Servicio para gestionar la tabla pedidosProductos
	 * */
	@Autowired
	private PedidoProductoService pedidosProductosService;
	
	/**
	 * Servicio para gestionar la tabla Productos
	 * */
	@Autowired
	private ProductoService productosService;
	
	
	/**
	 * Mapeo para mostrar la vista del ra&iacute;z
	 * @param model Modelo recibido por par&aacute;metros
	 * @return index Devuelve la vista index
	 * */
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("restaurantLogin",new Restaurante());
		return "index";
	}

	/**
	 * Mapeo de '/login' la cual redireciona al raiz
	 * @return redirecciona a raiz*/
	@GetMapping("/login")
	public String login()
	{
		return "redirect:/";
	}	
	
	/**
	 * Mapeo de '/cerrarSesion' el cual deslogea al restaurante y redirecciona al inicio
	 * @return redirecciona a raiz*/
	@GetMapping("/cerrarSesion")
	public String cerrarSesion()
	{
		this.restauranteLogeado=null;
		return "redirect:/";
	}
	
	/**
	 * Mapeo de '/categorias' para mostrar la vista de todas las categorias mediante el m&eacute;todo GET. Requiere que el restaurante esté logeado si no lo est&aacute; redirecciona al inicio,
	 * adem&aacute;s añade todos los datos por defecto.
	 * @param model ModelMap que se mostrar&aacute; en la vista
	 * @return Devuelve la vista de ra&iacute;z o categorias dependiendo si el restaurante est&aacute; o no logeado
	 * */
	@GetMapping("/categorias")
	public String categorias(ModelMap model) {
		if(this.restauranteLogeado==null) {
			return "redirect:/";
		}
		this.errores.clear();
		model=addAllDefaultAttributes(model);
		return "views/categorias";
	}
	/**
	 * Mapeo de '/categorias' para mostrar la vista de todas las categorias mediante el m&eacute;todo POST.
	 * @param restaurante Es el restaurante que se est&aacute; intentando logear
	 * @param model Modelo que se mostrar&aacute; en la vista
	 * @return Devuelve la vista index
	 * */
	@PostMapping("/categorias")
	public String categorias(@ModelAttribute("restaurantLogin")Restaurante restaurante, ModelMap model) {
		if(restauranteservice.logear(restaurante)) {
			this.restauranteLogeado=restauranteservice.getRestauranteByCorreoAndClave(restaurante.getCorreo(),restaurante.getClave());
			model=addAllDefaultAttributes(model);
			System.out.println(pedidosservice.getCarrito(this.restauranteLogeado).toString() );
			this.errores.clear();
			return "views/categorias";
		}
		anadirError("Usuario o contrase&ntilde;a incorrectos");
		model.addAttribute("errores",this.errores);
		return "index";
	}
	
	/**
	 * Mapeo '/carrito' para mostrar el carrito con todos los productos y sus cantidades
	 * @param model recibe el modelo que se mostrar&aacute;
	 * @return Devuelve la vista del carrito
	 */
	@GetMapping("/carrito")
	public String mostrarCarrito(ModelMap model) {
		if(this.restauranteLogeado==null) {
			return "redirect:/";
		}
		ArrayList<Producto>productos=new ArrayList<Producto>();
		HashMap<Producto,Double> prodCant= new HashMap<Producto,Double>();
		
		Pedido carrito=pedidosservice.getCarrito(this.restauranteLogeado);

		productos= pedidosservice.getProductos(carrito);
		for (int i = 0; i < productos.size(); i++) {
			prodCant.put(carrito.getPedidosProductos().get(i).getProductos(), carrito.getPedidosProductos().get(i).getUnidades());		
		}
		
		ArrayList<Pedido>pedidosEnv =pedidosservice.findAllByRestauranteAndEnviado(this.restauranteLogeado,true);

		model.addAttribute("errores",this.errores);
		model.addAttribute("pedidos",pedidosEnv);
		model.addAttribute("productos",prodCant);
		model.addAttribute("restaurantLogin",this.restauranteLogeado);		
		return "views/carrito";
	}
	/**
	 * Mapeo de '/comprar' para que reduzca el stock de las unidades que se desean comprar en la medida de lo posible
	 * @return Redirecciona a '/carrito'
	 * */
	@GetMapping("/comprar")
	public String comprar() {
		
		Pedido carrito = this.pedidosservice.getCarrito(this.restauranteLogeado);		
		Pedido compra= new Pedido(new Date(),true,0,carrito.getRestaurante(),new ArrayList<PedidosProducto>());
		double importeTotal=0;

		
		for (PedidosProducto pedidoProducto : carrito.getPedidosProductos()) {
			
	
			 //Si las unidades que desea comprar el usuario es mayor que el stock se reducen las cantidades que hay en el carrito
			if(pedidoProducto.getUnidades() > pedidoProducto.getProductos().getStock()) {
				if(pedidoProducto.getProductos().getStock()>0) {
					pedidoProducto.setUnidades(pedidoProducto.getUnidades()-pedidoProducto.getProductos().getStock());
					
					//El producto se mueve al pedido de compra que se va a realizar
					compra.getPedidosProductos().add(pedidoProducto);
					this.pedidosProductosService.actualizarPedidoProducto(pedidoProducto);
					this.productosService.actualizarExistencias(pedidoProducto.getProductos().getCodProd(), 0);								
	
					compra.setImporte(importeTotal);
					this.pedidosservice.save(compra);
					sendMail(compra);
				}else {
					anadirError("No puedes comprar el producto "+pedidoProducto.getProductos().getNombre()+" por que no hay stock disponible");
				}
			//Si las unidades que desea comprar el usuario es menor que el stock se reducen las cantidades que hay en el carrito a 0 para luego eliminarlas
			}else {
				compra.getPedidosProductos().add(pedidoProducto);
				this.productosService.actualizarExistencias(pedidoProducto.getProductos().getCodProd(), pedidoProducto.getProductos().getStock()-pedidoProducto.getUnidades());
				this.pedidosProductosService.quitarPedidoProducto(pedidoProducto);
				
				
			}			
		}
		
		for(PedidosProducto pedProd: compra.getPedidosProductos()) {
			importeTotal+=pedProd.getUnidades()*pedProd.getProductos().getPrecio();
		}
		
		compra.setImporte(importeTotal);
		System.out.println("Compra.toString: "+compra.toString());
		this.pedidosservice.save(compra);
		sendMail(compra);
		return "redirect:/carrito";
	}
	
	/**
	 * Mapeo de '/anadirProductos' si la cantidad que se desea a&ntilde;adir es mayor a 0 se añade al carrito
	 * @param id Identificador del producto a añadir al carrito
	 * @param cantidad Cantidad del producto que se desea a&ntilde;adir al carrito
	 * @param model Modelo que se enviar&aacute; a la vista
	 * @return Redirecciona a la '/categorias'
	 * */
	@GetMapping("/anadirProductos")
	public String anadirProducto(@Param("id")int id,@Param("cantidad")int cantidad, ModelMap model) {	
		if(cantidad>0) {
			
			Producto producto=productosService.findById(id);
			Pedido carrito=pedidosservice.getCarrito(this.restauranteLogeado);
			
			pedidosProductosService.anadirProducto(carrito,producto,cantidad);
		}
		
		model=addAllDefaultAttributes(model);
		return "redirect:/categorias";
	}
	
	/**
	 * Mapeo de '/buscarProductos' muestra los productos en la vista de categorias en funci&oacute;n de la categoria elegida
	 * @param nombreCat Nombre de la categoria que se quiere buscar
	 * @param restaurante Restaurante logeado
	 * @param model Modelo de que llegar&aacute; a la vista´
	 * @return Devuelve la vista de categorias
	 * */
	@GetMapping("/buscarProductos")
	public String buscarProductos(@ModelAttribute("nombre")String nombreCat, @ModelAttribute("restaurantLogin")Restaurante restaurante,ModelMap model) {
		ArrayList<Producto> productos=null;
		Categoria categoria= this.catService.findByNombre(nombreCat);

		model=addAllDefaultAttributes(model);

		if(!nombreCat.equals("todo")) {
			productos=  productosService.findAllByCategoria(categoria);			
			model.addAttribute("productos",productos);
		}
		model.addAttribute("categoriaSelected",nombreCat);
		return "views/categorias";
	}
	
	/**
	 * Mapeo de '/modificarCarrito' para modificar la cantidad de un conjunto de productos que hay en el carrito
	 * @param prodCant nuevas cantidades de los productos
	 * @param idProductos Identificadores de los productos
	 * @return Redirecciona a '/categorias'
	 * */
	@PostMapping("/modificarCarrito")
	public String modificarCarrito(@ModelAttribute("productosCant")String prodCant,@ModelAttribute("idProductos")String idProductos){
		Pedido carrito=pedidosservice.getCarrito(restauranteLogeado);
		String[]idsProd= idProductos.split(",");
		String[]prodsCant= prodCant.split(",");
		
		for(int i=0; i<idsProd.length;i++) {
			PedidosProducto pedProd=pedidosProductosService.getPedprodPorIdProd(Integer.parseInt(idsProd[i]), carrito);
			pedProd.setUnidades(Double.parseDouble(prodsCant[i]));
			if(Double.parseDouble(prodsCant[i])<=0) {
				pedidosProductosService.quitarPedidoProducto(pedProd);
			}else{
				pedidosProductosService.actualizarPedidoProducto(pedProd);				
			}
			
		}
		return "redirect:/categorias";
	}
		
	/**
	 * Envia un correo al email del restaurante (modificado para que lo mande a mi correo corporativo del centro para comprobar que funciona)
	 * @param pedido Pedido que se enviar&aacute; por email
	 * */
	private void sendMail(Pedido pedido) {
		String to ="rauldelossantoscabrera@iessoterohernandez.es";
		// Para cambiar al correo del destinatario al de el restaurante solo habría que descomentar la siguiente línea
		// to=this.restauranteLogeado.getCorreo();
		String message="";
		message+="USUARIO:"+this.restauranteLogeado.getCorreo()+"\n";
		message="Se ha realizado la compra de \n";
		
		for (int i = 0; i < pedido.getPedidosProductos().size(); i++) {
			message+=pedido.getPedidosProductos().get(i).getUnidades()+" unidades de ";
			message+=pedido.getPedidosProductos().get(i).getProductos().getNombre()+"\n";
		}
		message+="IMPORTE TOTAL: "+pedido.getImporte()+"€";
		try {
			mailService.send("notificaciones.app.98@gmail.com", to, "PROYECTO DWES Compra", message);
			System.out.println("Enviado correo a "+to);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A&ntilde;ade al modelo los atributos de restauranteLogin, categorias, productos
	 * @param model Modelo al cual se le a&ntilde;aden los atributos
	 * @return model Modelo con los atributos ya a&ntilde;adidos
	 * */
	private ModelMap addAllDefaultAttributes(ModelMap model) {
		model.addAttribute("restaurantLogin",this.restauranteLogeado);
		model.addAttribute("categorias",this.catService.findAll());
		model.addAttribute("productos",productosService.findAll());
		return model;
	}
	
	/**
	 * A&ntilde;ade un error a la lista de errores si este no est&aacute; ya en la lista.
	 * @param error error que se desea a&ntilde;adir
	 * */
	private void anadirError(String error) {
		if(this.errores.size()<=0) {
			this.errores.add(error);
		}else {
			boolean check=false;
			for(String errorActual :this.errores) {
				if(errorActual.equals(error)) {
					check=true;
				}
			}
			if(!check) {
				this.errores.add(error);
			}			
		}
	}
}
