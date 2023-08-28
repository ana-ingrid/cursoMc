package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;
import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository repository) {
        this.categoriaRepository = repository;
    }

    public Categoria ConsultaCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
    }


}
