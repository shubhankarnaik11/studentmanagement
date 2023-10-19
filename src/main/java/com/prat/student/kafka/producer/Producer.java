package com.prat.student.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void produceResponseMessage(Object object) {
        String topicName = "Response";
        kafkaTemplate.send(topicName, object);
    }

    public void produceErrorMessage(Object object) {
        String topicName = "Error";
        kafkaTemplate.send(topicName, object);
    }


}
