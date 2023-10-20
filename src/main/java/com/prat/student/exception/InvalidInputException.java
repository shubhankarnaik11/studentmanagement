package com.prat.student.exception;

public class InvalidInputException extends StudentManagementException{
    public InvalidInputException(String message) {
        update(InvalidInputException.class.getName(), message);
    }

}
