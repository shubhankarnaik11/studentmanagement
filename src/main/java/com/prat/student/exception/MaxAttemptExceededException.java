package com.prat.student.exception;


public class MaxAttemptExceededException extends RuntimeException {
    private String message;
    public MaxAttemptExceededException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

}
