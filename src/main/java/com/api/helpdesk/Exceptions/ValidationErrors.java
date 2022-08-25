package com.api.helpdesk.Exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors extends StandardError{

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errorValidations = new ArrayList<>();

    public ValidationErrors() {
    }

    public ValidationErrors(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrorValidations() {
        return errorValidations;
    }

    public void addErrors(String fieldName, String message) {
        this.errorValidations.add(new FieldMessage(fieldName, message));
    }


    
}
