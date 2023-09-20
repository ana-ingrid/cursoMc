package com.ingridsantos.cursomc.resources;

import com.ingridsantos.cursomc.dto.ProdutoDTO;
import com.ingridsantos.cursomc.model.Produto;
import com.ingridsantos.cursomc.resources.utils.URL;
import com.ingridsantos.cursomc.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    private ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> consultaProdutoId( @PathVariable Integer id) {
        return ResponseEntity.status(200).body(produtoService.consultaProdutoId(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Page<ProdutoDTO>> consultaPaginada(@RequestParam(value = "nome") String nome,
                                                      @RequestParam(value = "categorias") String categorias,
                                                      @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
                                                      @RequestParam(value = "linhasPagina", defaultValue = "24") Integer linhasPagina,
                                                      @RequestParam(value = "tipo", defaultValue = "DESC") String tipo,
                                                      @RequestParam(value = "ordem", defaultValue = "nome") String ordem
    ){
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids= URL.decodeIntList(categorias);
        Page<Produto> objs = produtoService.consultaPaginada(nome, ids, pagina, linhasPagina, tipo, ordem);
        Page<ProdutoDTO> listaDTO = objs.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listaDTO);
    }

}
