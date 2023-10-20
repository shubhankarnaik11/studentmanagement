package com.prat.student.exception;

public class InvalidPassMarkException extends StudentManagementException{
    public InvalidPassMarkException(String message) {
        update(InvalidPassMarkException.class.getName(), message);
    }
}
