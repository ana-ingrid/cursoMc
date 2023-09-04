package com.ingridsantos.cursomc.resources;

import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    private CategoriaService categoriaService;

     public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = "/{id}")
     ResponseEntity<Categoria> ConsultaCategoriaPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(categoriaService.ConsultaCategoriaPorId(id));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Categoria> salvaCategoria(@RequestBody Categoria obj){
        obj = categoriaService.salvaCategoria(obj);
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }



}
