package com.prat.student.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.prat.student.Request.RequestHandler;
import com.prat.student.entity.Student;
import com.prat.student.kafka.model.KafkaRequest;
import com.prat.student.consts.RequestTypes;
import com.prat.student.kafka.model.KafkaResponse;
import com.prat.student.kafka.producer.Producer;
import com.prat.student.model.StudentRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class RequestConsumer {

    @Autowired
    RequestHandler requestHandler;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Producer producer;

    @KafkaListener(topics = "Request", groupId="001")
    public void requestConsumer(String request){
        log.info("consumed");
        KafkaResponse kafkaResponse = new KafkaResponse();
        kafkaResponse.setStatus("success");
        try {
            KafkaRequest msg = objectMapper.readValue(request, KafkaRequest.class);
            kafkaResponse.setMessageId(msg.getMessageId());
            Object res = null;
            log.info("entering switch case");
            switch (msg.getType()) {

                case RequestTypes.STUDENT_ADD -> {
                    res = requestHandler.createStudent(objectMapper.convertValue(msg.getData(), StudentRequest.class));

                }
                case RequestTypes.STUDENT_MODIFY -> {
                    res = requestHandler.updateStudent(objectMapper.convertValue(msg.getData(), StudentRequest.class), Integer.parseInt(msg.getEntityId()));
                }
                case RequestTypes.STUDENT_DELETE -> {
                    res = requestHandler.deleteStudent(Integer.parseInt(msg.getEntityId()));
                }
                case RequestTypes.STUDENT_GET -> {
                    res = requestHandler.getStudentById(Integer.parseInt(msg.getEntityId()));
                }
            }
            log.debug("response {}",objectMapper.writeValueAsString(res));
            kafkaResponse.setData(objectMapper.writeValueAsString(res));
            producer.produceResponseMessage(kafkaResponse);
        }catch (Exception e){
            kafkaResponse.setStatus("error");
            kafkaResponse.getErrors().put("error while processing request", e.getMessage());
            producer.produceErrorMessage(kafkaResponse);
        }
    }
}
