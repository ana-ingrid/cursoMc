package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;
import com.ingridsantos.cursomc.model.Cliente;
import com.ingridsantos.cursomc.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente consultaClienteId(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente não encontrado"));
    }



}
