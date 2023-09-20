package com.ingridsantos.cursomc.service;

import com.ingridsantos.cursomc.model.PagamentoBoleto;
import com.ingridsantos.cursomc.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoBoleto(PagamentoBoleto pagamentoBoleto, Date instantePedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instantePedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagamentoBoleto.setDataVencimento(cal.getTime());
    }

}
