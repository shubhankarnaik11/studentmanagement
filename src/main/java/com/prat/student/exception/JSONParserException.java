package com.prat.student.exception;



public class JSONParserException extends RuntimeException {
    private String message;
    public JSONParserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}