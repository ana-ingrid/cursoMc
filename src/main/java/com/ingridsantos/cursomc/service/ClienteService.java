package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.dto.CategoriaDTO;
import com.ingridsantos.cursomc.dto.ClienteDTO;
import com.ingridsantos.cursomc.exceptions.IntegridadeDataException;
import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;
import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.model.Cliente;
import com.ingridsantos.cursomc.repository.ClienteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente consultaClienteId(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente não encontrado"));
    }

    public Cliente salvaCliente(Cliente obj){
        obj.setId(null);
        return clienteRepository.save(obj);
    }

    public Cliente alteraCliente(Cliente obj, Integer id) {
        Cliente cliente = consultaClienteId(id);
        atualizaDados(obj, cliente);
        return clienteRepository.save(cliente);
    }

    public void deletaCliente(Integer id) {
        consultaClienteId(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new IntegridadeDataException("Não é possível excluir um Cliente pois há entidades relacionadas");
        }
    }
    public List<Cliente> consultaClientes() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> consultaPaginada(Integer pagina, Integer linhasPagina, String tipo, String ordem){
        PageRequest pageRequest = PageRequest.of(pagina, linhasPagina, Sort.Direction.valueOf(tipo), ordem);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente conversaoDTO(ClienteDTO objDto){
        return new Cliente(objDto.getNome(),objDto.getEmail(), null, null );
    }

    private void atualizaDados(Cliente newObj, Cliente obj){
        obj.setNome(newObj.getNome());
        obj.setEmail(newObj.getEmail());
    }
}
