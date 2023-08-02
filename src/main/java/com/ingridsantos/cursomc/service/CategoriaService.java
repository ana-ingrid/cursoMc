package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria ConsultaCategoriaPorId(Integer id) {
       Optional<Categoria> ObjCategoria = repository.findById(id);
        return ObjCategoria.orElse(null);
    }

}
