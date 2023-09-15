package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.dto.ClienteDTO;
import com.ingridsantos.cursomc.dto.SalvaClienteDTO;
import com.ingridsantos.cursomc.enums.TipoCliente;
import com.ingridsantos.cursomc.exceptions.IntegridadeDataException;
import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;
import com.ingridsantos.cursomc.model.Cidade;
import com.ingridsantos.cursomc.model.Cliente;
import com.ingridsantos.cursomc.model.Endereco;
import com.ingridsantos.cursomc.repository.CidadeRepository;
import com.ingridsantos.cursomc.repository.ClienteRepository;
import com.ingridsantos.cursomc.repository.EnderecoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private EnderecoRepository enderecoRepository;

    private CidadeRepository cidadeRepository;
    public ClienteService(ClienteRepository clienteRepository,EnderecoRepository enderecoRepository, CidadeRepository cidadeRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.cidadeRepository = cidadeRepository;
    }

    public Cliente consultaClienteId(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Cliente não encontrado"));
    }
    @Transactional
    public Cliente salvaCliente(Cliente obj){
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        cidadeRepository.save(obj.getEnderecos().get(0).getCidade());
        return obj;
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
            throw new IntegridadeDataException("Não é possível excluir um cliente pois há pedidos relacionadas");
        }
    }
    public List<Cliente> consultaClientes() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> consultaPaginada(Integer pagina, Integer linhasPagina, String tipo, String ordem){
        PageRequest pageRequest = PageRequest.of(pagina, linhasPagina, Sort.Direction.valueOf(tipo), ordem);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente  conversaoDTO(ClienteDTO objDto){
        return new Cliente(objDto.getNome(),objDto.getEmail(), null, null );
    }

    public Cliente conversaoDTO(SalvaClienteDTO objDto) {
        Cliente cli = new Cliente(objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!=null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3()!=null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }
    private void atualizaDados(Cliente newObj, Cliente obj){
        obj.setNome(newObj.getNome());
        obj.setEmail(newObj.getEmail());
    }
}
