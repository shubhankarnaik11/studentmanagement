package com.prat.student.exception;

public class SubjectAlreadyExistsException extends StudentManagementException{
    public SubjectAlreadyExistsException(String message) {
        update(SubjectAlreadyExistsException.class.getName(), message);
    }
}