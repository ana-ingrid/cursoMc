package com.ingridsantos.cursomc.model;

import com.ingridsantos.cursomc.enums.EstadoPagamento;

import java.util.Date;
import java.util.Objects;

public class PagamentoBoleto extends Pagamento {

    private Date dataVencimento;
    private Date dataPagamento;

    public PagamentoBoleto(){

    }

    public PagamentoBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "PagamentoBoleto{" +
                "dataVencimento=" + dataVencimento +
                ", dataPagamento=" + dataPagamento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PagamentoBoleto that = (PagamentoBoleto) o;
        return Objects.equals(dataVencimento, that.dataVencimento) && Objects.equals(dataPagamento, that.dataPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataVencimento, dataPagamento);
    }
}
