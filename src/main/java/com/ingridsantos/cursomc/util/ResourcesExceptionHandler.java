package com.ingridsantos.cursomc.util;

import com.ingridsantos.cursomc.exceptions.ObjetoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourcesExceptionHandler {

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjetoNaoEncontradoException e, HttpServletRequest request){
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }


}
