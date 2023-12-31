package com.prat.student.exception;

import lombok.Getter;

@Getter
public class InvalidMarkException extends RuntimeException {
    private String errorMessage;

    public InvalidMarkException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}