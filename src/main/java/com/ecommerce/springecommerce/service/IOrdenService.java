package com.ecommerce.springecommerce.service;


import java.util.List;
import java.util.Optional;

import com.ecommerce.springecommerce.model.Orden;
import com.ecommerce.springecommerce.model.Usuario;

public interface IOrdenService {

    List<Orden> findAll();

    Optional<Orden> findById (Integer id);

    Orden save (Orden orden);
    String generarNumeroOrden();

    List<Orden> findByUsuario (Usuario usuario);
}
