package com.ingridsantos.cursomc.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/{cpf}")
    public String listar() {
        return "Rest está funcionando";
    }

}
