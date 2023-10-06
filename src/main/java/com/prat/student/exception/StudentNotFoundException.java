package com.prat.student.exception;

import lombok.Getter;

@Getter
public class StudentNotFoundException extends RuntimeException {
    private String errorMessage;
    public StudentNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}