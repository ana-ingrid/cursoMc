package com.ingridsantos.cursomc.resources;

import com.ingridsantos.cursomc.dto.CategoriaDTO;
import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    private CategoriaService categoriaService;

     public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = "/{id}")
     ResponseEntity<Categoria> ConsultaCategoriaPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(categoriaService.consultaCategoriaPorId(id));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvaCategoria(@RequestBody Categoria obj){
        obj = categoriaService.salvaCategoria(obj);
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public ResponseEntity<Void> alteraCategoria(@RequestBody Categoria obj, @PathVariable Integer id){
         obj.setId(id);
         obj = categoriaService.alteraCategoria(obj,id);
         return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletaCategoria(@PathVariable Integer id){
        categoriaService.deletaCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<CategoriaDTO>> ConsultaPaginada() {
        List<Categoria> lista = categoriaService.consultaPaginada();
        List<CategoriaDTO> listaDTO = lista.stream().map(obj ->  new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }



}
