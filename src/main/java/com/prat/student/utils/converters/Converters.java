package com.prat.student.utils.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.Request.Message;
import com.prat.student.exception.JSONParserException;
import com.prat.student.model.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Converters {

    @Autowired
    ObjectMapper objectMapper;
    public Message convertJSONToObject(String message){

        Message returnObject = null;

        try{
            returnObject = objectMapper.readValue(message, Message.class);
        } catch (Exception e) {
            throw new JSONParserException("Not able to parse Request JSON");
        }
        return returnObject;
    }

    public StudentRequest convertKafkaDataToStudentRequest(Object data){

        StudentRequest studentRequest = null;
        try{
            studentRequest = objectMapper.convertValue(data, StudentRequest.class);
        } catch (Exception e) {

            throw new JSONParserException("Not able to parse Data field to Student Data");
        }
        return studentRequest;
    }

}
