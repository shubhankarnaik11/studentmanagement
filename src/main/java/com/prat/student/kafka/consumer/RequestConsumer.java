package com.prat.student.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.Request.RequestHandler;
import com.prat.student.kafka.model.KafkaRequest;
import com.prat.student.consts.RequestTypes;
import com.prat.student.kafka.model.KafkaResponse;
import com.prat.student.kafka.producer.Producer;
import com.prat.student.model.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RequestConsumer {

    @Autowired
    RequestHandler requestHandler;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Producer producer;

    @KafkaListener(topics = "Request", groupId="001")
    public void requestConsumer(String request){
        KafkaResponse kafkaResponse = new KafkaResponse();
        kafkaResponse.setStatus("success");
        try {
            KafkaRequest msg = objectMapper.readValue(request, KafkaRequest.class);
            kafkaResponse.setMessageId(msg.getMessageId());
            Object res = null;
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
            }
            kafkaResponse.setData(res);

        }catch (Exception e){
            kafkaResponse.setStatus("error");
            kafkaResponse.getErrors().put("error while processing request", e.getMessage());
            producer.produceErrorMessage(kafkaResponse);
        }
    }
}
