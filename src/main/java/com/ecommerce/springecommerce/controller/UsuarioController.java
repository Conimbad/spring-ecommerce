package com.ecommerce.springecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.springecommerce.model.Usuario;
import com.ecommerce.springecommerce.service.IUsuarioService;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/registro")
    public String crear() {
        return "usuario/registro";
    }

    @PostMapping("/guardar")
    public String guardar(Usuario usuario) {
        logger.info("Usuario registro: {}", usuario);
        usuario.setTipo("USER");
        
        usuarioService.guardar(usuario);
        
        return "redirect:/";
    }

}
