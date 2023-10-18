package com.prat.student.kafka.producer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@SuppressWarnings("FieldCanBeLocal")
//@Component
//public class ResponseProducer {
//    private final String topicName = "Response";
//
//    @Autowired
//    KafkaTemplate<String, Object> kafkaTemplate;
//
//
//    public void sendMsg(Object object) {
//        kafkaTemplate.send(topicName, object);
//    }
//}
