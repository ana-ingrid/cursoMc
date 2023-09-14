package com.ingridsantos.cursomc.resources;

import com.ingridsantos.cursomc.dto.CategoriaDTO;
import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.service.CategoriaService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
        return ResponseEntity.status(200).body(categoriaService.consultaCategoriaId(id));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvaCategoria(@Valid @RequestBody CategoriaDTO objDto){
         Categoria obj = categoriaService.conversaoDTO(objDto);
         obj = categoriaService.salvaCategoria(obj);
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objDto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public ResponseEntity<Categoria> alteraCategoria(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id){
         Categoria obj = categoriaService.conversaoDTO(objDto);
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
    ResponseEntity<List<CategoriaDTO>> ConsultaCategorias() {
        List<Categoria> lista = categoriaService.consultaCategorias();
        List<CategoriaDTO> listaDTO = lista.stream().map(obj ->  new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }

    @RequestMapping(value ="/paginada", method = RequestMethod.GET)
    ResponseEntity<Page<CategoriaDTO>> consultaPaginada( @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                                                         @RequestParam(value = "linhasPagina", defaultValue = "24") Integer linhasPagina,
                                                         @RequestParam(value = "tipo", defaultValue = "DESC") String tipo,
                                                         @RequestParam(value = "ordem", defaultValue = "nome") String ordem
                                                         ){
         Page<Categoria> objs = categoriaService.consultaPaginada(pagina, linhasPagina, tipo, ordem);
         Page<CategoriaDTO> listaDTO = objs.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listaDTO);
    }

}
