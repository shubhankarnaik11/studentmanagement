package com.prat.student.exception;

import com.prat.student.response.ResponseDataObject;
import com.prat.student.response.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {


    private boolean isController(StackTraceElement sourceClassElement){

        try {

            Class<?> sourceClass = Class.forName(sourceClassElement.getClassName());
            Class<?> destinationClass = Class.forName("com.prat.student.controller.AbstractController");
            return sourceClass.getSuperclass() == destinationClass;

        } catch (ClassNotFoundException ignored) {
        }

        return false;
    }

    private ResponseEntity<ResponseDataObject> getErrorObject(Exception exception, String errorMessage){

        for(StackTraceElement errorClass : exception.getStackTrace()){
                if(isController(errorClass)){
                    return ResponseObject.getResponseObject(
                            new ResponseDataObject(HttpStatus.NOT_FOUND, null, errorMessage, false)
                    );
                }
        }
        return null;
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ResponseDataObject> exception(StudentNotFoundException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_FOUND, null ,exception.getErrorMessage(), false)
        );
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<ResponseDataObject> exception(SubjectNotFoundException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_FOUND, null ,exception.getErrorMessage(), false)
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
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null , exception.getErrorMessage(), false)
        );
    }

    @ExceptionHandler(SubjectAlreadyExistsException.class)
    public ResponseEntity<ResponseDataObject> exception(SubjectAlreadyExistsException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,exception.getErrorMessage(), false)
        );
    }

    @ExceptionHandler(MaxAttemptExceededException.class)
    public ResponseEntity<ResponseDataObject> exception(MaxAttemptExceededException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,exception.getErrorMessage(), false)
        );
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ResponseDataObject> exception(InvalidInputException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,"Invalid Input : \n" + exception.getErrorMessage(), false)
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
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,exception.getErrorMessage(), false)
        );
    }

    @ExceptionHandler(GradeAlreadyExistsException.class)
    public ResponseEntity<ResponseDataObject> exception(GradeAlreadyExistsException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,exception.getErrorMessage(), false)
        );
    }

    @ExceptionHandler(JSONParserException.class)
    public ResponseEntity<ResponseDataObject> exception(JSONParserException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_ACCEPTABLE, null ,exception.getErrorMessage(), false)
        );
    }
}
