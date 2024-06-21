package com.ecommerce.springecommerce.service;

import java.util.Optional;

import com.ecommerce.springecommerce.model.Usuario;

public interface IUsuarioService {
  Optional<Usuario> findById(Integer id);
  Usuario guardar(Usuario usuario);
}
