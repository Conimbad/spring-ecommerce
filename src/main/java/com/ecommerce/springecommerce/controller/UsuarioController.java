package com.ecommerce.springecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.springecommerce.model.Usuario;
import com.ecommerce.springecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;



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

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {

        logger.info("Accesos: {}", usuario);

        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
        // logger.info("Usuario de Base de Datos: {}", user.get());

        if(user.isPresent()) {
            session.setAttribute("idUsuario", user.get().getId());

            if(user.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }else {
                return "redirect:/";
            }
        }else {
            logger.info("El usuario no existe");
        }

        return "redirect:/";
    }

}
