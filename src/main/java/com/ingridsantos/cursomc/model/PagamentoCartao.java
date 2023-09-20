package com.ingridsantos.cursomc.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ingridsantos.cursomc.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;
@Entity
@JsonTypeName("pagamentoCartao")
public class PagamentoCartao extends Pagamento {

    private Integer numeroParcelas;

    public PagamentoCartao() {

    }

    public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
        super(id, estado, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
}
