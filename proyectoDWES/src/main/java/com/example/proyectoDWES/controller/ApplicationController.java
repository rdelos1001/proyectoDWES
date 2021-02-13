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
import com.example.proyectoDWES.Repository.PedidosRepository;
import com.example.proyectoDWES.Repository.ProductosRepository;
import com.example.proyectoDWES.Service.PedidosProductosService;
import com.example.proyectoDWES.Service.PedidosService;
import com.example.proyectoDWES.Service.ProductosService;
import com.example.proyectoDWES.Service.RestaurantesService;
import com.example.proyectoDWES.Service.SendMailService;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Controller
public class ApplicationController {

	private Restaurantes restauranteLogeado=null;
	
	@Autowired
	private SendMailService sendMailService;
	
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
	
	@GetMapping("/login")
	public String login()
	{
		return "redirect:/";
	}
	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion()
	{
		this.restauranteLogeado=null;
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
		HashMap<Productos,Integer> prodCant= new HashMap<Productos,Integer>();
		
		ArrayList<Pedidos>pedidosNoEnv=pedidosrepository.findAllByRestauranteAndEnviado(this.restauranteLogeado,false);
		if(pedidosNoEnv.isEmpty()) {
			Pedidos nuevoCarrito = new Pedidos(new Date(),false,0,null,new ArrayList<PedidosProductos>());
			pedidosrepository.save(nuevoCarrito);
			nuevoCarrito.setRestaurante(this.restauranteLogeado);
			pedidosrepository.save(nuevoCarrito);
			pedidosNoEnv.add(nuevoCarrito);
		}
		productos= pedidosservice.getProductos(pedidosNoEnv.get(0));
		for (int i = 0; i < productos.size(); i++) {
			prodCant.put(pedidosNoEnv.get(0).getPedidosProductos().get(i).getProductos(), (int)pedidosNoEnv.get(0).getPedidosProductos().get(i).getUnidades());		
		}
		
		ArrayList<Pedidos>pedidosEnv =pedidosrepository.findAllByRestauranteAndEnviado(this.restauranteLogeado,true);

		model.addAttribute("pedidos",pedidosEnv);
		model.addAttribute("productos",prodCant);
		model.addAttribute("restaurantLogin",this.restauranteLogeado);		
		return "views/carrito";
	}
	
	@GetMapping("/comprar")
	public String comprar(@ModelAttribute("productos")String productosCant) {
		String aux[]=productosCant.split(";");
		double total=0;
		
		for (int i = 0; i < aux.length; i++) {
			String productos[]= aux[i].split(",");
			if(productos[0].equals("total")) {
				total=Double.parseDouble(productos[1]);
			}else {
				this.productosService.actualizarExistencias(Integer.parseInt(productos[0]), Integer.parseInt(productos[1]));				
			}
		}
		
		ArrayList<Pedidos>pedidosNoEnv=pedidosrepository.findAllByRestauranteAndEnviado(this.restauranteLogeado,false);
		pedidosNoEnv.get(0).setFecha(new Date());		
		pedidosNoEnv.get(0).setEnviado(true);
		pedidosNoEnv.get(0).setImporte(total);
		pedidosrepository.save(pedidosNoEnv.get(0));
		sendMail();
		return "redirect:/categorias";
	}
	
	@GetMapping("/anadirProductos")
	public String anadirProducto(@Param("id")int id,@Param("cantidad")int cantidad, ModelMap model) {	
		if(cantidad>0) {
			
			Productos producto=productosrepository.findById(id).get();
			ArrayList<Pedidos>pedidosNoEnv=pedidosrepository.findAllByRestauranteAndEnviado(this.restauranteLogeado,false);
			
			pedidosProductosService.anadirProducto(pedidosNoEnv.get(0),producto,cantidad);
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
		ArrayList<Pedidos>pedidosNoEnv=pedidosrepository.findAllByRestauranteAndEnviado(this.restauranteLogeado,false);

		String[]idsProd= idProductos.split(",");
		String[]prodsCant= prodCant.split(",");
		
		for(int i=0; i<idsProd.length;i++) {
			PedidosProductos pedProd=pedidosProductosService.getPedprodPorIdProd(Integer.parseInt(idsProd[i]), pedidosNoEnv.get(0));
			pedProd.setUnidades(Integer.parseInt(prodsCant[i]));
			if(Integer.parseInt(prodsCant[i])<=0) {
				pedidosProductosService.quitarPedidoProducto(pedProd);
			}else{
				pedidosProductosService.actualizarProducto(pedProd);				
			}
			
		}
		return "redirect:/categorias";
	}
		
	private void sendMail() {
		String to ="rauldelossantoscabrera@iessoterohernandez.es";
		//to=this.restauranteLogeado.getCorreo();
		String message="Se ha realizado la compra de ";
		sendMailService.sendMail("notificaciones.app.98@gmail.com", to, "Compra", message);
	}
	private ModelMap addAllDefaultAttributes(ModelMap model) {
		model.addAttribute("restaurantLogin",this.restauranteLogeado);
		model.addAttribute("categorias",categoriasrepository.findAll());
		model.addAttribute("productos",productosrepository.findAll());
		return model;
	}
}
