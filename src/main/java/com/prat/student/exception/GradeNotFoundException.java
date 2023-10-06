package com.prat.student.exception;

import lombok.Getter;

@Getter
public class GradeNotFoundException extends RuntimeException {
    private String errorMessage;
    public GradeNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
