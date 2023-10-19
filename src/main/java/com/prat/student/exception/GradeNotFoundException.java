package com.prat.student.exception;


public class GradeNotFoundException extends RuntimeException {
    private String message;
    public GradeNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}
