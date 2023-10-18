package com.prat.student.exception;
//
//import org.apache.kafka.clients.consumer.Consumer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.listener.CommonErrorHandler;
//import org.springframework.kafka.listener.MessageListenerContainer;
//import org.springframework.stereotype.Component;
//
////@Component
//public class CommonErrorHandlerSMT implements CommonErrorHandler {
//
////    @Override
////    @Bean
//    public void handleOtherException(Exception e, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener){
//        System.out.println(e.getMessage());
//        System.out.println(e.getClass());
//
//        System.out.println("---------------------\n\n\n");
//        consumer.close();
//    }
//}
