package com.prat.student.Request;

import com.prat.student.model.GradeRequest;
import com.prat.student.model.StudentRequest;
import com.prat.student.model.SubjectRequest;
import com.prat.student.utils.converters.Converters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.prat.student.config.EntityType;

import java.util.HashMap;

@Component
@Slf4j
public class KafkaRequestStager implements RequestStager {


    @Autowired
    Converters converter;

    @Autowired
    RequestHandler requestHandler;

    @Override
    public void prepareDataAndForwardToRequestHandler(String request) {
        Message message = converter.convertJSONToObject(request);
        message.setRequestBody(convertDataFieldBasedOnEntity(message));
        log.info("sending to requesthandler");
        requestHandler.requestHandler(message);
    }

    @Override
    public Object convertDataFieldBasedOnEntity(Message message) {

        switch (message.getEntity()) {
            case  EntityType.STUDENT -> {
                if (message.requestType.equals("post") || message.requestType.equals("put")) {

                    StudentRequest studentRequest = converter.convertKafkaDataToStudentRequest(message.getRequestBody());
                    return studentRequest;
                }
                else if(message.requestType.equals("get") || message.requestType.equals("delete")){
                    return message.pathVariable;
                }
                else if(message.requestType.equals("getAll")){
                    return null;
                }
                else if(message.requestType.equals("updateMarks")){
                    return message.requestBody;
                }
            }
            case  EntityType.GRADE -> {
                if (message.requestType.equals("post") || message.requestType.equals("put")) {
                    GradeRequest gradeRequest = converter.convertKafkaDataToGradeRequest(message.getRequestBody());
                    return gradeRequest;
                }
                else if(message.requestType.equals("get") || message.requestType.equals("topper-list")
                || message.requestType.equals("promote") || message.requestType.equals("student-list")){
                    return message.pathVariable;
                }
                else if(message.requestType.equals("getAll")){
                    return null;
                }

            }
            case  EntityType.SUBJECT -> {
                if (message.requestType.equals("post")) {
                    SubjectRequest subjectRequest = converter.convertKafkaDataToSubjectRequest(message.getRequestBody());
                    return subjectRequest;
                }
                else if(message.requestType.equals("get") || message.requestType.equals("delete")){
                    return message.pathVariable;
                }
                else if(message.requestType.equals("getAll")){
                    return null;
                }

            }

        }
        return null;
    }



}
