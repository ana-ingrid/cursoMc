package com.ingridsantos.cursomc.resources;

import com.ingridsantos.cursomc.model.Pedido;
import com.ingridsantos.cursomc.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
