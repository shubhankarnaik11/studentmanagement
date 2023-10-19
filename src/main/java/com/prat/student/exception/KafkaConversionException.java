package com.prat.student.exception;



public class KafkaConversionException extends RuntimeException {
    private String message;
    public KafkaConversionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}

