package com.felipeleres.clientcrud.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {

    private List<BeanError> errors = new ArrayList<>();

    public ValidationError(Instant timeStamp, Integer status, String error, String path) {
        super(timeStamp, status, error, path);
    }

    public List<BeanError> getErrors (){
        return errors;
    }

    public void addErrors(String fieldname, String message){
        errors.add(new BeanError(fieldname,message));
    }
}
