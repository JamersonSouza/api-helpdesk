package com.api.helpdesk.Exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DataIntegrityViolationExceptions extends DataIntegrityViolationException{

    public DataIntegrityViolationExceptions(String msg) {
        super(msg);
    }
    
}
