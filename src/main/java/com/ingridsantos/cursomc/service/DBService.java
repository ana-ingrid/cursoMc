package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.enums.EstadoPagamento;
import com.ingridsantos.cursomc.enums.TipoCliente;
import com.ingridsantos.cursomc.model.*;
import com.ingridsantos.cursomc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
@Service
public class DBService {
    @Autowired
    protected CategoriaRepository categoriaRepository;
    @Autowired
    protected ProdutoRepository produtoRepository;
    @Autowired
    protected CidadeRepository cidadeRepository;
    @Autowired
    protected EstadoRepository estadoRepository;
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected PagamentoRepository pagamentoRepository;
    @Autowired
    protected PedidoRepository pedidoRepository;
    @Autowired
    protected EnderecoRepository enderecoRepository;
    @Autowired
    protected ItemPedidoRepository itemPedidoRepository;

    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    public void instantiateTestDatabase() throws ParseException {

//		produto e categoria
        Categoria cat1 = new Categoria(null,"Informática");
        Categoria cat2 = new Categoria(null,"Escritório");
        Categoria cat3 = new Categoria(null,"Jardinagem");
        Categoria cat4 = new Categoria(null,"Elétrica");
        Categoria cat5 = new Categoria(null,"Engenharia");
        Categoria cat6 = new Categoria(null,"Contadores");
        Categoria cat7 = new Categoria(null,"Perfumaria");


        Produto p1 = new Produto(null,"impressora", 2000.00);
        Produto p2 = new Produto(null,"cadeira", 1000.00);
        Produto p3 = new Produto(null,"computador", 2000.00);
        Produto p4 = new Produto(null,"mesa", 900.00);
        Produto p5 = new Produto(null,"caixa de som", 500.00);
        Produto p6 = new Produto(null,"webcam", 200.00);
        Produto p7 = new Produto(null,"scanner", 600.00);
        Produto p8 = new Produto(null,"monitor", 800.00);
        Produto p9 = new Produto(null,"organizadores", 230.00);

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));
        p4.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p5.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p5.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p6.getCategorias().addAll(Arrays.asList(cat2));
        p7.getCategorias().addAll(Arrays.asList(cat2));
        p8.getCategorias().addAll(Arrays.asList(cat1));
        p9.getCategorias().addAll(Arrays.asList(cat1, cat2));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));

//		estados e cidades
        Estado e1 = new Estado(null,"Minas Gerais");
        Estado e2 = new Estado(null,"São Paulo");

        Cidade c1 = new Cidade(null,"Uberlândia",e1);
        Cidade c2 = new Cidade(null,"Juiz de Fora", e1);
        Cidade c3 = new Cidade(null, "Campinas", e2);

        e1.getCidades().addAll(Arrays.asList(c1, c2));
        e2.getCidades().addAll(Arrays.asList(c3));


        estadoRepository.saveAll(Arrays.asList(e1, e2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
//      Cliente e endereço

        Cliente cl1 = new Cliente(null,"Maria Silva", "ingridsantoscosta2003@gmail.com", "22123343245", TipoCliente.PESSOAFISICA, bCryptPasswordEncoder.encode("123"));

        cl1.getTelefones().addAll(Arrays.asList("27688493", "974516398"));

        Endereco en1 = new Endereco(null,"Rua flores", 300, "Apto 302", "Jardim", "20342675", cl1, c1);
        Endereco en2 = new Endereco(null,"Rua Norte", 12, "Casa 1", "Santana", "20345645", cl1, c2);

        cl1.getEnderecos().addAll(Arrays.asList(en1, en2));

        clienteRepository.saveAll(Arrays.asList(cl1));
        enderecoRepository.saveAll(Arrays.asList(en1, en2));

//        Pedido e pagamento

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null,sdf.parse("30/07/2023 10:32"), cl1, en1);
        Pedido ped2 = new Pedido(null,sdf.parse("10/08/2023 14:10"), cl1, en1);

        Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pag1);

        Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("24/08/2023 23:59"), null);
        ped2.setPagamento(pag2);

        cl1.getPedidos().addAll(Arrays.asList(ped1,ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
        pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));

//        Item pedido

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00,1, p1.getPreco());
        ItemPedido ip2 = new ItemPedido(ped1, p2, 0.00, 1, p2.getPreco());
        ItemPedido ip3 = new ItemPedido(ped2, p3, 0.00, 1, p3.getPreco());

        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip2));
        p3.getItens().addAll(Arrays.asList(ip3));

        itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
    }
}
