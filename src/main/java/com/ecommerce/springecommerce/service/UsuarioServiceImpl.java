package com.ecommerce.springecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.springecommerce.model.Usuario;
import com.ecommerce.springecommerce.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

  @Autowired
  private IUsuarioRepository usuarioRepository;

  @Override
  public Optional<Usuario> findById(Integer id) {
    // TODO Auto-generated method stub
    return usuarioRepository.findById(id);
  }

  @Override
  public Usuario guardar(Usuario usuario) {
    // TODO Auto-generated method stub
    return usuarioRepository.save(usuario);
  }

  @Override
  public Optional<Usuario> findByEmail(String email) {
    // TODO Auto-generated method stub
    return usuarioRepository.findByEmail(email);
  }

}
