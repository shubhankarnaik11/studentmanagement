package com.prat.student.exception;

import lombok.Getter;


@Getter
public class KafkaConversionException extends RuntimeException {
    private String errorMessage;
    public KafkaConversionException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}

