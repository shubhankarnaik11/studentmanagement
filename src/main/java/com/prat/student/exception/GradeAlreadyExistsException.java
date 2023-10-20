package com.prat.student.exception;

public class GradeAlreadyExistsException extends StudentManagementException{
    public GradeAlreadyExistsException(String message) {
        update(GradeAlreadyExistsException.class.getName(), message);
    }
}
