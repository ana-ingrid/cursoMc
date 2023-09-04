package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;
import com.ingridsantos.cursomc.model.Pedido;
import com.ingridsantos.cursomc.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository repository) {
        this.pedidoRepository = repository;
    }

    public Pedido consultaPedidoId(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Pedido não encontrado"));
    }

}
