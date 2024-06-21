package com.ecommerce.springecommerce.service;


import java.util.List;

import com.ecommerce.springecommerce.model.Orden;

public interface IOrdenService {

    List<Orden> findAll();

    Orden save (Orden orden);
}
