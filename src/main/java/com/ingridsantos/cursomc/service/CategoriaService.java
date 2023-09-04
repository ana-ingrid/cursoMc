package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;
import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository repository) {
        this.categoriaRepository = repository;
    }

    public Categoria ConsultaCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
    }

    public Categoria salvaCategoria(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }
}
