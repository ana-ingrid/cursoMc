package com.ingridsantos.cursomc.dto;

import com.ingridsantos.cursomc.model.Categoria;
import com.ingridsantos.cursomc.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO {

    private Integer id = null;
    private String nome;
    private Double preco;

    public ProdutoDTO() {
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
