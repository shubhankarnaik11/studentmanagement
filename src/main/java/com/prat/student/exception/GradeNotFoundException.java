package com.prat.student.exception;

public class GradeNotFoundException extends StudentManagementException{
    public GradeNotFoundException(String message) {
        update(GradeNotFoundException.class.getName(), message);
    }

}
