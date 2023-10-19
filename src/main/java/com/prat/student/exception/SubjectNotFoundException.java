package com.prat.student.exception;


public class SubjectNotFoundException extends RuntimeException {
    private String message;
    public SubjectNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
