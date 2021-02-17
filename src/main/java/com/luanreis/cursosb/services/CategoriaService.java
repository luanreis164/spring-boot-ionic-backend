package com.luanreis.cursosb.services;

import com.luanreis.cursosb.domain.Categoria;
import com.luanreis.cursosb.repositories.CategoriaRepository;
import com.luanreis.cursosb.services.exceptions.ObjectNotFoundException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }else{
            throw new ObjectNotFoundException();
        }
    }


    public Categoria insert(Categoria obj){
        obj.setId(null);
        return repo.save(obj);
    }



}

