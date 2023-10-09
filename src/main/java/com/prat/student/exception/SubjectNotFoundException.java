package com.prat.student.exception;

import lombok.Getter;

@Getter
public class SubjectNotFoundException extends RuntimeException {
    private String errorMessage;
    public SubjectNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
