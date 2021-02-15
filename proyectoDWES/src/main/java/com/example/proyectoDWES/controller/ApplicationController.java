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

import com.example.proyectoDWES.Entity.Categorias;
import com.example.proyectoDWES.Entity.Pedidos;
import com.example.proyectoDWES.Entity.PedidosProductos;
import com.example.proyectoDWES.Entity.Productos;
import com.example.proyectoDWES.Entity.Restaurantes;
import com.example.proyectoDWES.Repository.CategoriasRepository;
import com.example.proyectoDWES.Repository.PedidosProductosRepository;
import com.example.proyectoDWES.Repository.PedidosRepository;
import com.example.proyectoDWES.Repository.ProductosRepository;
import com.example.proyectoDWES.Service.MailService;
import com.example.proyectoDWES.Service.PedidosProductosService;
import com.example.proyectoDWES.Service.PedidosService;
import com.example.proyectoDWES.Service.ProductosService;
import com.example.proyectoDWES.Service.RestaurantesService;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Controller
public class ApplicationController {

	private Restaurantes restauranteLogeado=null;
	private String error="";
	
	@Autowired
	private PedidosProductosRepository pedProdrepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private RestaurantesService restauranteservice;
	
	@Autowired
	private CategoriasRepository categoriasrepository;
	
	@Autowired
	private ProductosRepository productosrepository;
		
	@Autowired
	private PedidosService pedidosservice;
	
	@Autowired
	private PedidosRepository pedidosrepository;
	
	@Autowired
	private PedidosProductosService pedidosProductosService;
	
	@Autowired
	private ProductosService productosService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("restaurantLogin",new Restaurantes());
		return "index";
	}
	@GetMapping("/error")
	public String error() {
		System.out.println("-------------ERROR-------------");
		return "views/error404";
	}
	@GetMapping("/login")
	public String login()
	{
		return "redirect:/";
	}	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion()
	{
		this.restauranteLogeado=new Restaurantes();
		return "redirect:/";
	}
	@GetMapping("/categorias")
	public String categorias(ModelMap model) {
		if(this.restauranteLogeado==null) {
			return "redirect:/";
		}
		model=addAllDefaultAttributes(model);
		return "views/categorias";
	}
	
	@PostMapping("/categorias")
	public String categorias(@ModelAttribute("restaurantLogin")Restaurantes restaurante, ModelMap model) {
		if(restauranteservice.logear(restaurante)) {
			this.restauranteLogeado=restauranteservice.getRestauranteByCorreoAndClave(restaurante.getCorreo(),restaurante.getClave());
			model=addAllDefaultAttributes(model);
			return "views/categorias";
		}
		model.addAttribute("mensajeError","Usuario o contraseña incorrectos");
		return "index";
	}
	
	@GetMapping("/carrito")
	public String mostrarCarrito(ModelMap model) {
		if(this.restauranteLogeado==null) {
			return "redirect:/";
		}
		ArrayList<Productos>productos=new ArrayList<Productos>();
		HashMap<Productos,Double> prodCant= new HashMap<Productos,Double>();
		
		Pedidos carrito=pedidosservice.getCarrito(this.restauranteLogeado);

		productos= pedidosservice.getProductos(carrito);
		for (int i = 0; i < productos.size(); i++) {
			prodCant.put(carrito.getPedidosProductos().get(i).getProductos(), carrito.getPedidosProductos().get(i).getUnidades());		
		}
		
		ArrayList<Pedidos>pedidosEnv =pedidosrepository.findAllByRestauranteAndEnviado(this.restauranteLogeado,true);

		model.addAttribute("error",this.error);
		model.addAttribute("pedidos",pedidosEnv);
		model.addAttribute("productos",prodCant);
		model.addAttribute("restaurantLogin",this.restauranteLogeado);		
		return "views/carrito";
	}
	
	@GetMapping("/comprar")
	public String comprar() {
		Pedidos carrito = this.pedidosservice.getCarrito(this.restauranteLogeado);		
		Pedidos compra= new Pedidos(new Date(),true,0,carrito.getRestaurante(),new ArrayList<PedidosProductos>());
		System.out.println("Carrito: "+carrito.toString());
		
		for (PedidosProductos pedidoProducto : carrito.getPedidosProductos()) {
			
			System.out.println("Pedido producto actual"+pedidoProducto.toString());
	
			 //Si las unidades que desea comprar el usuario es mayor que el stock se reducen las cantidades que hay en el carrito
			if(pedidoProducto.getUnidades() > pedidoProducto.getProductos().getStock()) {
				if(pedidoProducto.getProductos().getStock()>0) {
					pedidoProducto.setUnidades(pedidoProducto.getUnidades()-pedidoProducto.getProductos().getStock());
					
					//El producto se mueve al pedido de compra que se va a realizar
					compra.getPedidosProductos().add(pedidoProducto);
					this.pedidosProductosService.actualizarPedidoProducto(pedidoProducto);
					this.productosService.actualizarExistencias(pedidoProducto.getProductos().getCodProd(), 0);								
				}else {
					this.error="No puedes comprar el producto "+pedidoProducto.getProductos().getNombre()+" por que no hay stock disponible";
				}
			//Si las unidades que desea comprar el usuario es menor que el stock se reducen las cantidades que hay en el carrito a 0 para luego eliminarlas
			}else {
				compra.getPedidosProductos().add(pedidoProducto);
				this.productosService.actualizarExistencias(pedidoProducto.getProductos().getCodProd(), pedidoProducto.getProductos().getStock()-pedidoProducto.getUnidades());
				this.pedProdrepository.delete(pedidoProducto);
			}
			
			
			
			System.out.println("-----FIN PEDIDO PRODUCTO ACTUAL-------");
		}
		this.pedidosrepository.save(compra);
		
		return "redirect:/carrito";
	}
	
	@GetMapping("/anadirProductos")
	public String anadirProducto(@Param("id")int id,@Param("cantidad")int cantidad, ModelMap model) {	
		if(cantidad>0) {
			
			Productos producto=productosrepository.findById(id).get();
			Pedidos carrito=pedidosservice.getCarrito(this.restauranteLogeado);
			
			pedidosProductosService.anadirProducto(carrito,producto,cantidad);
		}
		
		model=addAllDefaultAttributes(model);
		return "redirect:/categorias";
	}
	
	@GetMapping("/buscarProductos")
	public String buscarProductos(@ModelAttribute("nombre")String nombreCat, @ModelAttribute("restaurantLogin")Restaurantes restaurante,ModelMap model) {
		ArrayList<Productos> productos=null;
		Categorias categoria= categoriasrepository.findByNombre(nombreCat);

		model=addAllDefaultAttributes(model);

		if(!nombreCat.equals("todo")) {
			productos=  productosrepository.findAllByCategoria(categoria);			
			model.addAttribute("productos",productos);
		}
		model.addAttribute("categoriaSelected",nombreCat);
		return "views/categorias";
	}
	@PostMapping("/modificarCarrito")
	public String modificarCarrito(@ModelAttribute("productosCant")String prodCant,@ModelAttribute("idProductos")String idProductos){
		Pedidos carrito=pedidosservice.getCarrito(restauranteLogeado);
		String[]idsProd= idProductos.split(",");
		String[]prodsCant= prodCant.split(",");
		
		for(int i=0; i<idsProd.length;i++) {
			PedidosProductos pedProd=pedidosProductosService.getPedprodPorIdProd(Integer.parseInt(idsProd[i]), carrito);
			pedProd.setUnidades(Double.parseDouble(prodsCant[i]));
			if(Double.parseDouble(prodsCant[i])<=0) {
				pedidosProductosService.quitarPedidoProducto(pedProd);
			}else{
				pedidosProductosService.actualizarPedidoProducto(pedProd);				
			}
			
		}
		return "redirect:/categorias";
	}
		
	private void sendMail(Pedidos pedido) {
		String to ="rauldelossantoscabrera@iessoterohernandez.es";
		//to=this.restauranteLogeado.getCorreo();
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
	private ModelMap addAllDefaultAttributes(ModelMap model) {
		model.addAttribute("restaurantLogin",this.restauranteLogeado);
		model.addAttribute("categorias",categoriasrepository.findAll());
		model.addAttribute("productos",productosrepository.findAll());
		return model;
	}
		
}
