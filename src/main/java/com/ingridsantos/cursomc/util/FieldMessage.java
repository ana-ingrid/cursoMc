package com.ingridsantos.cursomc.util;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private String fieldName;
    private String message;

    public FieldMessage(String fieldname, String message) {
        this.fieldName = fieldname;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
