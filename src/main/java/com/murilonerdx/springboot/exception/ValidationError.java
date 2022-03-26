package com.murilonerdx.springboot.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message) {
        super(timestamp, status, error, message);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldMessage> errors) {
        this.errors = errors;
    }
}
