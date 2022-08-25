package com.api.helpdesk.ResourcesControlles.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExceptions.class)
    public ResponseEntity<StandardError> ObjectNotFoundExceptions(ObjectNotFoundExceptions ex,
     HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Object Not Found",
         ex.getMessage(), request.getRequestURI());

         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

        //tratamento de exceção DataIntegrityViolationExceptions
    @ExceptionHandler(DataIntegrityViolationExceptions.class)
    public ResponseEntity<StandardError> dataIntegrityViolationExceptions(DataIntegrityViolationExceptions ex,
     HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Violação de Dados Duplicados",
         ex.getMessage(), request.getRequestURI());

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException ex,
     HttpServletRequest request){

        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos",
         ex.getMessage(), request.getRequestURI());
        
        for(FieldError x : ex.getBindingResult().getFieldErrors()){
            errors.addErrors(x.getField(), x.getDefaultMessage());
        }

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
