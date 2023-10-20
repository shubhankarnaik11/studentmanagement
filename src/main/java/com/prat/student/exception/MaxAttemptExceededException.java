package com.prat.student.exception;

public class MaxAttemptExceededException extends StudentManagementException{
    public MaxAttemptExceededException(String message) {
        update(MaxAttemptExceededException.class.getName(),message);
    }


}
