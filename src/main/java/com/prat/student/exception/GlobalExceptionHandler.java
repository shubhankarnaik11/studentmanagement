package com.prat.student.exception;

import com.prat.student.response.ResponseDataObject;
import com.prat.student.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ResponseDataObject> exception(StudentNotFoundException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_FOUND, null ,exception.getMessage(), false)
        );
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<ResponseDataObject> exception(SubjectNotFoundException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_FOUND, null ,exception.getMessage(), false)
        );
    }

    @ExceptionHandler(GradeNotFoundException.class)
    public ResponseEntity<ResponseDataObject> exception(GradeNotFoundException exception) {
//        switch (getErrorSource(exception)){
//
//        }
        return null;
    }

    @ExceptionHandler(InvalidMarkException.class)
    public ResponseEntity<ResponseDataObject> exception(InvalidMarkException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null , exception.getMessage(), false)
        );
    }

    @ExceptionHandler(SubjectAlreadyExistsException.class)
    public ResponseEntity<ResponseDataObject> exception(SubjectAlreadyExistsException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,exception.getMessage(), false)
        );
    }

    @ExceptionHandler(MaxAttemptExceededException.class)
    public ResponseEntity<ResponseDataObject> exception(MaxAttemptExceededException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,exception.getMessage(), false)
        );
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ResponseDataObject> exception(InvalidInputException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,"Invalid Input : \n" + exception.getMessage(), false)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseDataObject> exception(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null, result.getFieldError().getDefaultMessage() , false)
        );

    }

    @ExceptionHandler(InvalidPassMarkException.class)
    public ResponseEntity<ResponseDataObject> exception(InvalidPassMarkException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,exception.getMessage(), false)
        );
    }

    @ExceptionHandler(GradeAlreadyExistsException.class)
    public ResponseEntity<ResponseDataObject> exception(GradeAlreadyExistsException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,exception.getMessage(), false)
        );
    }

    @ExceptionHandler(JSONParserException.class)
    public ResponseEntity<ResponseDataObject> exception(JSONParserException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,exception.getMessage(), false)
        );
    }
}
