package com.prat.student.exception;

public class InvalidMarkException extends StudentManagementException{
    public InvalidMarkException(String message) {
        update(InvalidMarkException.class.getName(), message);
    }
}