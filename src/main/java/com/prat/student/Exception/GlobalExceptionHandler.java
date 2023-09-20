package com.prat.student.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> exception(StudentNotFoundException exception) {
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<Object> exception(SubjectNotFoundException exception) {
        return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GradeNotFoundException.class)
    public ResponseEntity<Object> exception(GradeNotFoundException exception) {
        return new ResponseEntity<>("Grade not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MarkNotFoundException.class)
    public ResponseEntity<Object> exception(MarkNotFoundException exception) {
        return new ResponseEntity<>("Mark Id not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidMarkException.class)
    public ResponseEntity<Object> exception(InvalidMarkException exception) {
        return new ResponseEntity<>("Invalid Mark, should be between 0 and 100", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubjectAlreadyExistsException.class)
    public ResponseEntity<Object> exception(SubjectAlreadyExistsException exception) {
        return new ResponseEntity<>("Subject Already Exists for the Grade", HttpStatus.NOT_FOUND);
    }

}
