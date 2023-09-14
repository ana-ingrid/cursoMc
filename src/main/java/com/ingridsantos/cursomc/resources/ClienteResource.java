package com.ingridsantos.cursomc.resources;

import com.ingridsantos.cursomc.dto.ClienteDTO;
import com.ingridsantos.cursomc.model.Cliente;
import com.ingridsantos.cursomc.model.Cliente;
import com.ingridsantos.cursomc.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    protected ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> consultaClienteId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(clienteService.consultaClienteId(id));
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public ResponseEntity<Cliente> alteraCliente(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
        Cliente obj = clienteService.conversaoDTO(objDto);
        obj.setId(id);
        obj = clienteService.alteraCliente(obj,id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletaCliente(@PathVariable Integer id){
        clienteService.deletaCliente(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<ClienteDTO>> ConsultaClientes() {
        List<Cliente> lista = clienteService.consultaClientes();
        List<ClienteDTO> listaDTO = lista.stream().map(obj ->  new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }

    @RequestMapping(value ="/paginada", method = RequestMethod.GET)
    ResponseEntity<Page<ClienteDTO>> consultaPaginada(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                                                        @RequestParam(value = "linhasPagina", defaultValue = "24") Integer linhasPagina,
                                                        @RequestParam(value = "tipo", defaultValue = "DESC") String tipo,
                                                        @RequestParam(value = "ordem", defaultValue = "nome") String ordem
    ){
        Page<Cliente> objs = clienteService.consultaPaginada(pagina, linhasPagina, tipo, ordem);
        Page<ClienteDTO> listaDTO = objs.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listaDTO);
    }



}
