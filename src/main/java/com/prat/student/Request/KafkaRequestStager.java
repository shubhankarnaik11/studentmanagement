package com.prat.student.Request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.model.StudentRequest;
import com.prat.student.utils.converters.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.prat.student.config.EntityType;
@Component
public class KafkaRequestStager implements RequestStager {


    @Autowired
    Converters converter;

    @Override
    public void prepareDataAndForwardToRequestHandler(String request) {
        Message data = converter.convertJSONToObject(request);
        data.setData(convertDataFieldBasedOnEntity(data));

    }

    @Override
    public Object convertDataFieldBasedOnEntity(Message data) {


        switch (data.getEntity()) {
            case  EntityType.STUDENT -> {
                StudentRequest studentRequest = converter.convertKafkaDataToStudentRequest(data.getData());
                return studentRequest;
            }
        }

        return null;
    }



}
