package com.ingridsantos.cursomc.resources;

import com.ingridsantos.cursomc.dto.CategoriaDTO;
import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.model.Pedido;
import com.ingridsantos.cursomc.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    private PedidoService pedidoService;

    public PedidoResource(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> consultaPedidoId( @PathVariable Integer id) {
        return ResponseEntity.status(200).body(pedidoService.consultaPedidoId(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvaPedido(@Valid @RequestBody Pedido obj){
        obj = pedidoService.salvaPedido(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
