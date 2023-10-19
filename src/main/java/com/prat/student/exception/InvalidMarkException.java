package com.prat.student.exception;


public class InvalidMarkException extends RuntimeException {
    private String message;

    public InvalidMarkException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}