package com.prat.student.exception;


public class GradeAlreadyExistsException extends RuntimeException{
    private String message;
    public GradeAlreadyExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
