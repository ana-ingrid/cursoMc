package com.ingridsantos.cursomc.resources;

import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    private CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> ConsultaCategoriaPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(categoriaService.ConsultaCategoriaPorId(id));
    }

}
