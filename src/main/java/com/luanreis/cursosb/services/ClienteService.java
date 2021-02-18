package com.luanreis.cursosb.services;

import com.luanreis.cursosb.domain.Cliente;
import com.luanreis.cursosb.dto.ClienteDTO;
import com.luanreis.cursosb.repositories.ClienteRepository;
import com.luanreis.cursosb.services.exceptions.DataIntegrityException;
import com.luanreis.cursosb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado,id: " + id +"Tipo"+Cliente.class));
    }

    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj,obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try {
            repo.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas.");
        }
    }

    public List<Cliente> findAll(){
        return repo.findAll();

    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);


    }

    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null , null);
    }

    private void updateData(Cliente newObj,Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

}

