package com.ingridsantos.cursomc.util;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private String fieldname;
    private String message;

    public FieldMessage(String fieldname, String message) {
        this.fieldname = fieldname;
        this.message = message;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
