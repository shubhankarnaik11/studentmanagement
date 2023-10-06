package com.prat.student.exception;

import com.prat.student.response.ResponseDataObject;
import com.prat.student.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ResponseDataObject> exception(StudentNotFoundException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_FOUND, null ,"Subject Created Successfully", false)
        );
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<ResponseDataObject> exception(SubjectNotFoundException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_FOUND, null ,"Subject not found", false)
        );
    }

    @ExceptionHandler(GradeNotFoundException.class)
    public ResponseEntity<ResponseDataObject> exception(GradeNotFoundException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_FOUND, null ,"Grade not found", false)
        );
    }

    @ExceptionHandler(MarkNotFoundException.class)
    public ResponseEntity<ResponseDataObject> exception(MarkNotFoundException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_FOUND, null ,"No marks found", false)
        );
    }

    @ExceptionHandler(InvalidMarkException.class)
    public ResponseEntity<ResponseDataObject> exception(InvalidMarkException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,"Invalid Mark, should be between 0 and 100", false)
        );
    }

    @ExceptionHandler(SubjectAlreadyExistsException.class)
    public ResponseEntity<ResponseDataObject> exception(SubjectAlreadyExistsException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,"Subject Already Exists", false)
        );
    }

    @ExceptionHandler(MaxAttemptExceededException.class)
    public ResponseEntity<ResponseDataObject> exception(MaxAttemptExceededException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,"Max Attempt Exceeded for the Subject", false)
        );
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ResponseDataObject> exception(InvalidInputException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,"Invalid Input : \n" + exception.getErrorMessage(), false)
        );
    }

}
