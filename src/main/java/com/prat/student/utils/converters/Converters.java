package com.prat.student.utils.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.Request.Message;
import com.prat.student.exception.JSONParserException;
import com.prat.student.model.GradeRequest;
import com.prat.student.model.StudentRequest;
import com.prat.student.model.SubjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
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
        log.info("converting");
        StudentRequest studentRequest = null;
        try{
            studentRequest = objectMapper.convertValue(data, StudentRequest.class);
        } catch (Exception e) {
            log.info("json parser");
            throw new JSONParserException("Not able to parse Data field to Student Data");
        }
        log.info("returning converted");
        return studentRequest;
    }
    public GradeRequest convertKafkaDataToGradeRequest(Object data){

        GradeRequest gradeRequest = null;
        try{
            gradeRequest = objectMapper.convertValue(data, GradeRequest.class);
        } catch (Exception e) {

            throw new JSONParserException("Not able to parse Data field to Grade Data");
        }
        return gradeRequest;
    }
    public SubjectRequest convertKafkaDataToSubjectRequest(Object data){

        SubjectRequest subjectRequest = null;
        try{
            subjectRequest = objectMapper.convertValue(data, SubjectRequest.class);
        } catch (Exception e) {

            throw new JSONParserException("Not able to parse Data field to Subject Data");
        }
        return subjectRequest;
    }

}
