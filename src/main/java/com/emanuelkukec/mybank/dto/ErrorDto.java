package com.emanuelkukec.mybank.dto;

import java.util.List;

public class ErrorDto {
    private List<String> errors;

    private String message;

    public ErrorDto(String message, List<String> errors) {
        this.errors = errors;
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
