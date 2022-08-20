package com.api.helpdesk.Exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceControllersExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExceptions.class)
    public ResponseEntity<StandardError> objectNotFoundExceptions(ObjectNotFoundExceptions ex,
    HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Objeto não encontrado",
         ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationExceptions.class)
    public ResponseEntity<StandardError> dataIntegrityViolationExceptions(DataIntegrityViolationExceptions ex,
    HttpServletRequest request){

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de violação de dados",
         ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException metanotva, HttpServletRequest request){

            ValidationErrors errors = new ValidationErrors(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
             "Erro na validação de alguns campos", metanotva.getMessage(), request.getRequestURI());
             
             for(FieldError x : metanotva.getBindingResult().getFieldErrors()){
                errors.addErrors(x.getField(), x.getDefaultMessage());
             }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    
    
}
