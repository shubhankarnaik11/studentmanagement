package com.prat.student.exception;

import lombok.Getter;

@Getter
public class InvalidInputException extends RuntimeException {
    private String errorMessage;
    public InvalidInputException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
