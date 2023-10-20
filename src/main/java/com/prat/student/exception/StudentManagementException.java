package com.prat.student.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentManagementException extends RuntimeException{
    private String message;
    private String messageType;

    public void update(String type, String message){
        this.messageType = type;
        this.message = message;
    }

}
