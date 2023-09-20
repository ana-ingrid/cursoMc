package com.ingridsantos.cursomc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ingridsantos.cursomc.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

@Entity
@JsonTypeName("pagamentoBoleto")
public class PagamentoBoleto extends Pagamento {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataPagamento;

    public PagamentoBoleto(){

    }

    public PagamentoBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id,estado, pedido);
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
