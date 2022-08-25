package com.api.helpdesk.ResourcesControlles.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

public class ValidationError extends StandardError{

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errorsValidations = new ArrayList<>();

    public ValidationError() {
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrorsValidations() {
        return errorsValidations;
    }

    public void addErrors(String fieldName, String message) {
        this.errorsValidations.add(new FieldMessage(fieldName, message));
    }

    
    
}
