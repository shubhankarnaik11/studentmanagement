package com.prat.student.exception;


public class InvalidPassMarkException extends RuntimeException {
    private String message;
    public InvalidPassMarkException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}
