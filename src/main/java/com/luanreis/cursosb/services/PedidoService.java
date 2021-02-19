package com.luanreis.cursosb.services;

import com.luanreis.cursosb.domain.Cliente;
import com.luanreis.cursosb.domain.ItemPedido;
import com.luanreis.cursosb.domain.PagamentoComBoleto;
import com.luanreis.cursosb.domain.Pedido;
import com.luanreis.cursosb.domain.enums.EstadoPagamento;
import com.luanreis.cursosb.repositories.ClienteRepository;
import com.luanreis.cursosb.repositories.ItemPedidoRepository;
import com.luanreis.cursosb.repositories.PagamentoRepository;
import com.luanreis.cursosb.repositories.PedidoRepository;
import com.luanreis.cursosb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado,id: " + id
                +"Tipo"+Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido obj){
        obj.setId(null);
        obj.setInstant(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto,obj.getInstant());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip :obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }

}

