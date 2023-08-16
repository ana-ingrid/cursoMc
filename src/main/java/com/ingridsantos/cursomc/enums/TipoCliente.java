package com.ingridsantos.cursomc.enums;

import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica"),
    ;

    private int codigo;
    private String descricao;

    TipoCliente(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static TipoCliente toEnum(Integer codigo){
        if (codigo == null){
            return null;
        }

        for (TipoCliente x : TipoCliente.values()){
            if (codigo.equals(x.getCodigo())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id Inválido");
    }

}
