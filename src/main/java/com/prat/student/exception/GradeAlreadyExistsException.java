package com.prat.student.exception;

import lombok.Getter;

@Getter
public class GradeAlreadyExistsException extends RuntimeException {
    private String errorMessage;
    public GradeAlreadyExistsException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
