package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;
import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.model.Produto;
import com.ingridsantos.cursomc.repository.CategoriaRepository;
import com.ingridsantos.cursomc.repository.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;
    CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository repository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto consultaProdutoId(Integer id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Produto não encontrado"));
    }

    public Page<Produto> consultaPaginada(String nome, List<Integer> ids, Integer pagina, Integer linhasPagina, String tipo, String ordem){
        PageRequest pageRequest = PageRequest.of(pagina, linhasPagina, Sort.Direction.valueOf(tipo), ordem);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return produtoRepository.search(nome, categorias, pageRequest);
    }

}
