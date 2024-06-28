package com.ecommerce.springecommerce.service;


import java.util.List;

import com.ecommerce.springecommerce.model.Orden;
import com.ecommerce.springecommerce.model.Usuario;

public interface IOrdenService {

    List<Orden> findAll();

    Orden save (Orden orden);
    String generarNumeroOrden();

    List<Orden> findByUsuario (Usuario usuario);
}
