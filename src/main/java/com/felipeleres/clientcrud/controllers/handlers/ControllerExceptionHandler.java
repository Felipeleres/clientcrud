package com.felipeleres.clientcrud.controllers.handlers;

import com.felipeleres.clientcrud.dto.CustomError;
import com.felipeleres.clientcrud.dto.ValidationError;
import com.felipeleres.clientcrud.services.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error =  new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> MethodArgumentNotValid (MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status =  HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError(Instant.now(),status.value(),"Dados inválidos",request.getRequestURI());
        for(FieldError x : e.getBindingResult().getFieldErrors()){
            error.addErrors(x.getField(),x.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }

}
