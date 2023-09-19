package com.ingridsantos.cursomc.resources.utils;

import java.util.ArrayList;
import java.util.List;

public class URL {

    public static List<Integer> decodeIntList(String s ){
        String[] vet = s.split(",");
        List<Integer> lista = new ArrayList<>();
        for (int i=0; i< vet.length; i++){
            lista.add(Integer.parseInt(vet[i]));
        }
        return lista;
//        Mesma ação, simplificada
//        return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
}
