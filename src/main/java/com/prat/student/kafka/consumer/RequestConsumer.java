package com.prat.student.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.Request.RequestHandler;
import com.prat.student.kafka.model.KafkaRequest;
import com.prat.student.consts.RequestTypes;
import com.prat.student.kafka.model.KafkaResponse;
import com.prat.student.kafka.producer.Producer;
import com.prat.student.model.GradeRequest;
import com.prat.student.model.StudentRequest;
import com.prat.student.model.SubjectListRequest;
import com.prat.student.model.SubjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                case RequestTypes.STUDENT_GET_ALL -> {
                    res = requestHandler.getAllStudents();
                }
                case RequestTypes.STUDENT_MARK -> {
                    res = requestHandler.updateStudentMark(Integer.parseInt(msg.getEntityId()),objectMapper.convertValue(msg.getData(), HashMap.class));
                }
                case RequestTypes.SUBJECT_ADD -> {
                    res = requestHandler.createSubject(objectMapper.convertValue(msg.getData(), SubjectRequest.class));
                }
                case RequestTypes.SUBJECT_GET -> {
                    res = requestHandler.getSubjectById(Integer.parseInt(msg.getEntityId()));
                }
                case RequestTypes.SUBJECT_GET_ALL -> {
                    res = requestHandler.getAllSubjects();
                }
                case RequestTypes.GRADE_GET_ALL -> {
                    res = requestHandler.getGrades();
                }
                case RequestTypes.GRADE_GET -> {
                    res = requestHandler.getGradeByGradeNo(Integer.parseInt(msg.getEntityId()));
                }
                case RequestTypes.GRADE_ADD -> {
                    res = requestHandler.createGrade(objectMapper.convertValue(msg.getData(), GradeRequest.class));
                }
                case RequestTypes.GRADE_ADD_SUBJECTS -> {
                    res = requestHandler.addSubjectsToGrade(Integer.parseInt(msg.getEntityId()),objectMapper.convertValue(msg.getData(), List.class));
                }
                case RequestTypes.GRADE_PROMOTE -> {
                    res = requestHandler.promoteAllStudentsByGrade(Integer.parseInt(msg.getEntityId()));
                }
                case RequestTypes.GRADE_STUDENTS -> {
                    res = requestHandler.getGradeStudents(Integer.parseInt(msg.getEntityId()));
                }
                case RequestTypes.GRADE_TOPPERS -> {
                    res = requestHandler.getNToppers(objectMapper.convertValue(msg.getData(), Integer.class),objectMapper.convertValue(msg.getData(), Integer.class));
                }
            }
            log.debug("response {}",objectMapper.writeValueAsString(res));
            kafkaResponse.setData(objectMapper.writeValueAsString(res));
            System.out.println(kafkaResponse.getData());
            producer.produceResponseMessage(kafkaResponse);
        }catch (RuntimeException e){

            kafkaResponse.setStatus("error");
            kafkaResponse.getErrors().put("error while processing request", e.getMessage());
            producer.produceErrorMessage(kafkaResponse);

        }catch (Exception e){
            kafkaResponse.setStatus("error");
            System.out.println(e);
            kafkaResponse.getErrors().put("error while processing request", e.getMessage());
            producer.produceErrorMessage(kafkaResponse);
        }
    }
}
