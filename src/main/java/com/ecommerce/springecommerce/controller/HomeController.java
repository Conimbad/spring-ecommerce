package com.ecommerce.springecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.springecommerce.model.DetalleOrden;
import com.ecommerce.springecommerce.model.Orden;
import com.ecommerce.springecommerce.model.Producto;
import com.ecommerce.springecommerce.service.ProductoService;

@Controller
@RequestMapping("/")
public class HomeController {

  private final Logger log = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  private ProductoService productoService;

  // Almacena los detalles de la orden
  List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

  // Datos de la orden
  Orden orden = new Orden();

  @GetMapping("")
  public String home (Model model) {

    model.addAttribute("productos", productoService.findAll());
    return "/usuario/home";
  }

  @GetMapping("producto_home/{id}")
  public String productoHome( @PathVariable Integer id, Model model) {

    Producto producto = new Producto();
    Optional<Producto> productoOptional = productoService.get(id);
    producto = productoOptional.get();

    model.addAttribute("producto", producto);

    log.info("Id de producto enviado como parámetro {}", id);
    return "/usuario/productohome";
  }

  @PostMapping("/cart")
  public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {

    DetalleOrden detalleOrden = new DetalleOrden();
    Producto producto = new Producto();
    double sumaTotal = 0;
    

    Optional<Producto> optionalProducto = productoService.get(id);
    log.info("Producto añadido: {}", optionalProducto.get());
    log.info("Cantidad: {}", cantidad);

    producto = optionalProducto.get();
    detalleOrden.setCantidad(cantidad);
    detalleOrden.setPrecio(producto.getPrecio());
    detalleOrden.setNombre(producto.getNombre());
    detalleOrden.setTotal(producto.getPrecio() * cantidad);
    detalleOrden.setProducto(producto);

    // Se valida que el producto se añada más de una vez
    Integer idProducto = producto.getId();
    boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

    if(!ingresado) {
      
      detalles.add(detalleOrden);
    }
                    
    // Sumar el total de los productos que añada el usuario al carrito
    sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

    orden.setTotal(sumaTotal);
    model.addAttribute("cart", detalles);
    model.addAttribute("orden", orden);



    return "usuario/carrito";
  }

  // Quitar producto del carrito
  @GetMapping("/delete/cart/{id}")
  public String borrarProductoCarrito(@PathVariable Integer id, Model model) {

    // Lista nueva de productos
    List<DetalleOrden> ordenNueva = new ArrayList<DetalleOrden>();

    for(DetalleOrden detalleOrden: detalles) {
      if(detalleOrden.getProducto().getId() != id) {
        ordenNueva.add(detalleOrden);
      }
    }

    // Nueva lista con los productos restantes
    detalles = ordenNueva;

    double sumaTotal = 0;

    sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

    orden.setTotal(sumaTotal);
    model.addAttribute("cart", detalles);
    model.addAttribute("orden", orden);

    return "usuario/carrito";
  }

  @GetMapping("/obtenerCarrito")
  public String obtenerCarrito (Model model) {

    model.addAttribute("cart", detalles);
    model.addAttribute("orden", orden);

    return "/usuario/carrito";
  }

  @GetMapping("/orden")
  public String orden () {
    
    return "/usuario/resumenorden";
  }
}
