package com.luanreis.cursosb.services;

import com.luanreis.cursosb.domain.Categoria;
import com.luanreis.cursosb.domain.Cliente;
import com.luanreis.cursosb.repositories.CategoriaRepository;
import com.luanreis.cursosb.repositories.ClienteRepository;
import com.luanreis.cursosb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }


}

