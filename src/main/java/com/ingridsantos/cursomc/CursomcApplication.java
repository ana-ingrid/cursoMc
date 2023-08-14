package com.ingridsantos.cursomc;

import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.model.Cidade;
import com.ingridsantos.cursomc.model.Estado;
import com.ingridsantos.cursomc.model.Produto;
import com.ingridsantos.cursomc.repository.CategoriaRepository;
import com.ingridsantos.cursomc.repository.CidadeRepository;
import com.ingridsantos.cursomc.repository.EstadoRepository;
import com.ingridsantos.cursomc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		produto e categoria
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null ,"Escritório");

		Produto p1 = new Produto( null, "impressora", 2000.00);
		Produto p2 = new Produto( null, "cadeira", 1000.00);
		Produto p3 = new Produto( null, "computador", 2000.00);

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

//		estados e cidades
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", e1);
		Cidade c2 = new Cidade(null, "Juiz de Fora",e1);
		Cidade c3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(c1,c2));
		e2.getCidades().addAll(Arrays.asList(c3));


		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));

	}
}
