package com.ingridsantos.cursomc.resources;

import com.ingridsantos.cursomc.model.Cliente;
import com.ingridsantos.cursomc.repository.ClienteRepository;
import com.ingridsantos.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    protected ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer id){
        return ResponseEntity.status(200).body(clienteService.buscarCliente(id));
    }

}
