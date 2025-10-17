package com.felipeleres.clientcrud.dto;

public class BeanError {

    private String fieldName;
    private String message;

    public BeanError(String fieldName,String message){
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
