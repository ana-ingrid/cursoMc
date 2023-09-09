package com.ingridsantos.cursomc.exceptions;

public class IntegridadeDataException extends RuntimeException{

    public IntegridadeDataException(String msg){
        super(msg);
    }

    public IntegridadeDataException(String msg, Throwable cause){
        super(msg, cause);
    }

}
