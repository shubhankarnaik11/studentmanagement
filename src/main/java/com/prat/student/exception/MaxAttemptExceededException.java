package com.prat.student.exception;

import lombok.Getter;

@Getter
public class MaxAttemptExceededException extends RuntimeException {
    private String errorMessage;
    public MaxAttemptExceededException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
