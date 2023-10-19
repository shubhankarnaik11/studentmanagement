package com.prat.student.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.kafka.model.KafkaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void produceResponseMessage(KafkaResponse response) {
        String topicName = "Response";
        kafkaTemplate.send(topicName, response.toString());
    }

    public void produceErrorMessage(KafkaResponse response) {
        String topicName = "Error";
        kafkaTemplate.send(topicName, response.toString());
    }

}
