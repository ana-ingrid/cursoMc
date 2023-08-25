package com.ingridsantos.cursomc.model;

import com.ingridsantos.cursomc.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;
@Entity
public class PagamentoCartao extends Pagamento {

    private Integer numeroParcelas;

    public PagamentoCartao(){

    }

    public PagamentoCartao(EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
        super(estado, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    @Override
    public String toString() {
        return "PagamentoCartao{" +
                "numeroParcelas=" + numeroParcelas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagamentoCartao that = (PagamentoCartao) o;
        return Objects.equals(numeroParcelas, that.numeroParcelas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroParcelas);
    }
}
