package com.prat.student.exception;


public class SubjectAlreadyExistsException extends RuntimeException {
    private String message;
    public SubjectAlreadyExistsException(String errorMessage) {
        this.message = errorMessage;
    }

    @Override
    public String getMessage(){
        return message;
    }
}