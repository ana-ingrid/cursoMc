package com.ingridsantos.cursomc.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer statusHttp, String msg, Long timeStamp) {
        super(statusHttp, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName,message));
    }
}
