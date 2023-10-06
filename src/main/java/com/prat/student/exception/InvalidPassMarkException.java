package com.prat.student.exception;

import lombok.Getter;

@Getter
public class InvalidPassMarkException extends RuntimeException {
    private String errorMessage;
    public InvalidPassMarkException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
