package com.luanreis.cursosb.services;

import com.luanreis.cursosb.domain.Categoria;
import com.luanreis.cursosb.repositories.CategoriaRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id){
        Optional<Categoria> obj=repo.findById(id);
        return obj.orElse(null);
    }

}

