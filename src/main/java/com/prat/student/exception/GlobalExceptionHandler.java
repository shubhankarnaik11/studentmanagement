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

    @ExceptionHandler(StudentManagementException.class)
    public ResponseEntity<ResponseDataObject> exception(StudentManagementException exception) {
        return ResponseObject.getResponseObject(
                new ResponseDataObject(HttpStatus.NOT_FOUND, null ,exception.getMessage(), false)
        );
    }

}
