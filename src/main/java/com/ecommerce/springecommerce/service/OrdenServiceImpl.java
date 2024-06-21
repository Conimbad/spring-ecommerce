package com.ecommerce.springecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.springecommerce.model.Orden;
import com.ecommerce.springecommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImpl implements IOrdenService{

    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        // TODO Auto-generated method stub
        return ordenRepository.findAll();
    }

    public String generarNumeroOrden() {

        int numero = 0;
        String numeroConcatenado = "";

        List<Orden> ordenes = findAll();
        List<Integer> numeros = new ArrayList<>();

        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
        if (ordenes.isEmpty()) {
            numero = 1;
        }else {
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero < 10) {
            numeroConcatenado = "000000000" + String.valueOf(numero);
        }else if (numero < 100) {
            numeroConcatenado = "00000000" + String.valueOf(numero);
        }else if (numero < 1000) {
            numeroConcatenado = "0000000" + String.valueOf(numero);
        }else if (numero < 10000) {
            numeroConcatenado = "000000" + String.valueOf(numero);
        }else if (numero < 100000) {
            numeroConcatenado = "00000" + String.valueOf(numero);
        }else if (numero < 1000000) {
            numeroConcatenado = "0000" + String.valueOf(numero);
        }else if (numero < 10000000) {
            numeroConcatenado = "000" + String.valueOf(numero);
        }else if (numero < 100000000) {
            numeroConcatenado = "00" + String.valueOf(numero);
        }else if (numero < 1000000000) {
            numeroConcatenado = "0" + String.valueOf(numero);
        }
        return numeroConcatenado;
    }
}
