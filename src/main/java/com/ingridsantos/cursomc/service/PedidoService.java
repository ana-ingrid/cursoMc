package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.enums.EstadoPagamento;
import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;
import com.ingridsantos.cursomc.model.ItemPedido;
import com.ingridsantos.cursomc.model.PagamentoBoleto;
import com.ingridsantos.cursomc.model.Pedido;
import com.ingridsantos.cursomc.repository.ItemPedidoRepository;
import com.ingridsantos.cursomc.repository.PagamentoRepository;
import com.ingridsantos.cursomc.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;
    private BoletoService boletoService;
    private PagamentoRepository pagamentoRepository;
    private ProdutoService produtoService;
    private ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         BoletoService boletoService,
                         PagamentoRepository pagamentoRepository,
                         ItemPedidoRepository itemPedidoRepository,
                         ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.boletoService = boletoService;
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.produtoService = produtoService;
    }

    public Pedido consultaPedidoId(Integer id) {
        return pedidoRepository.findById(id).orElseThrow
                (() -> new ObjetoNaoEncontradoException("Pedido não encontrado"));
    }

    @Transactional
    public  Pedido salvaPedido(Pedido obj){
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoBoleto){
            PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
            boletoService.preencherPagamentoBoleto(pagto, obj.getInstante());
        }
        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()){
            ip.setDesconto(0.00);
            ip.setPreco(produtoService.consultaProdutoId(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }
}
