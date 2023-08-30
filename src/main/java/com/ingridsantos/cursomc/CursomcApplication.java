package com.ingridsantos.cursomc;

import com.ingridsantos.cursomc.enums.EstadoPagamento;
import com.ingridsantos.cursomc.enums.TipoCliente;
import com.ingridsantos.cursomc.model.*;
import com.ingridsantos.cursomc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//		produto e categoria
        Categoria cat1 = new Categoria("Informática");
        Categoria cat2 = new Categoria("Escritório");

        Produto p1 = new Produto("impressora", 2000.00);
        Produto p2 = new Produto("cadeira", 1000.00);
        Produto p3 = new Produto("computador", 2000.00);

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

//		estados e cidades
        Estado e1 = new Estado("Minas Gerais");
        Estado e2 = new Estado("São Paulo");

        Cidade c1 = new Cidade("Uberlândia",e1);
        Cidade c2 = new Cidade("Juiz de Fora", e1);
        Cidade c3 = new Cidade("Campinas", e2);

        e1.getCidades().addAll(Arrays.asList(c1, c2));
        e2.getCidades().addAll(Arrays.asList(c3));


        estadoRepository.saveAll(Arrays.asList(e1, e2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
//      Cliente e endereço

        Cliente cl1 = new Cliente("Maria Silva", "maria@gmail.com", "22123343245", TipoCliente.PESSOAFISICA);

        cl1.getTelefones().addAll(Arrays.asList("27688493", "974516398"));

        Endereco en1 = new Endereco("Rua flores", 300, "Apto 302", "Jardim", "20342675", cl1, c1);
        Endereco en2 = new Endereco("Rua Norte", 12, "Casa 1", "Santana", "20345645", cl1, c2);

        cl1.getEnderecos().addAll(Arrays.asList(en1, en2));

        clienteRepository.saveAll(Arrays.asList(cl1));
        enderecoRepository.saveAll(Arrays.asList(en1, en2));

//        Pedido e pagamento

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(sdf.parse("30/07/2023 10:32"), cl1, en1);
        Pedido ped2 = new Pedido(sdf.parse("10/08/2023 14:10"), cl1, en1);

        Pagamento pag1 = new PagamentoCartao(EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pag1);

        Pagamento pag2 = new PagamentoBoleto(EstadoPagamento.PENDENTE, ped2, sdf.parse("24/08/2023 23:59"), null);
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
