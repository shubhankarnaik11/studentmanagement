package com.prat.student.exception;

import lombok.Getter;


@Getter
public class JSONParserException extends RuntimeException {
    private String errorMessage;
    public JSONParserException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}