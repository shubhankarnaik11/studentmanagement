package com.prat.student.exception;

public class SubjectNotFoundException extends StudentManagementException{
    private String message;
    public SubjectNotFoundException(String message) {
        update(SubjectNotFoundException.class.getName(), message);
    }
}
