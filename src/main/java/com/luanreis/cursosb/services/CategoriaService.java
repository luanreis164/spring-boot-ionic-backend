package com.luanreis.cursosb.services;

import com.luanreis.cursosb.domain.Categoria;
import com.luanreis.cursosb.domain.Cliente;
import com.luanreis.cursosb.dto.CategoriaDTO;
import com.luanreis.cursosb.repositories.CategoriaRepository;
import com.luanreis.cursosb.services.exceptions.DataIntegrityException;
import com.luanreis.cursosb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }else{
            throw new ObjectNotFoundException("Categoria não encontrada! ID: "+ id + ", Tipo:"  + Categoria.class);
        }
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return repo.save(obj);
    }


    public Categoria update(Categoria obj){
        Categoria newObj = find(obj.getId());
        updateData(newObj,obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir uma categoria com produtos.");
        }
    }

    public List<Categoria> findAll(){
        return repo.findAll();

    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);


    }

    public Categoria fromDTO(CategoriaDTO objDTO){
        return new Categoria(objDTO.getId(), objDTO.getNome());
    }

    public void updateData(Categoria newObj,Categoria obj){
        newObj.setNome(obj.getNome());

    }


}

