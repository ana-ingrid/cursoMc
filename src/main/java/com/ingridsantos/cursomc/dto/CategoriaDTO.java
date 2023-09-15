package com.ingridsantos.cursomc.dto;

import com.ingridsantos.cursomc.model.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;


public class CategoriaDTO {

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 50, message = "Preencha o campo corretamente")
    private String nome;

    public CategoriaDTO(){

    }

    public CategoriaDTO(Categoria obj){
        this.nome = obj.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
