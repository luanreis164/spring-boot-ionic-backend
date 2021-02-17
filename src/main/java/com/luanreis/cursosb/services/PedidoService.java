package com.luanreis.cursosb.services;

import com.luanreis.cursosb.domain.Cliente;
import com.luanreis.cursosb.domain.Pedido;
import com.luanreis.cursosb.repositories.ClienteRepository;
import com.luanreis.cursosb.repositories.PedidoRepository;
import com.luanreis.cursosb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }else {
        throw new ObjectNotFoundException();
        }
    }


}

