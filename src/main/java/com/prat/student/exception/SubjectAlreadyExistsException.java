package com.prat.student.exception;

import lombok.Getter;

@Getter
public class SubjectAlreadyExistsException extends RuntimeException {
    private String errorMessage;
    public SubjectAlreadyExistsException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}