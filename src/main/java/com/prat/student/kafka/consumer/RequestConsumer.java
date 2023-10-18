package com.prat.student.kafka.consumer;

import com.prat.student.Request.KafkaRequestStager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RequestConsumer {

    @Autowired
    KafkaRequestStager krh;

    @KafkaListener(topics = "Request", groupId="001")
    public void requestConsumer(String data){
        try{
            krh.prepareDataAndForwardToRequestHandler(data);
        } catch (Exception e){
            System.out.println("Catching exception");
        }
    }
}
