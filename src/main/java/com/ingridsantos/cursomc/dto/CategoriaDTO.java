package com.ingridsantos.cursomc.dto;

import com.ingridsantos.cursomc.model.Categoria;

public class CategoriaDTO {

    private Integer id = null;
    private String nome;

    public CategoriaDTO(){

    }

    public CategoriaDTO(Categoria obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
