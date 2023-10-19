package com.prat.student.exception;


public class InvalidInputException extends RuntimeException {
    private String message;
    public InvalidInputException(String errorMessage) {
        this.message = errorMessage;
    }

    @Override
    public String getMessage(){
        return message;
    }

}
