package com.prat.student.Request;

import com.prat.student.config.EntityType;
import com.prat.student.entity.Grade;
import com.prat.student.entity.Student;
import com.prat.student.model.StudentRequest;
import com.prat.student.serviceimpl.GradeServiceImpl;
import com.prat.student.serviceimpl.StudentServiceImpl;
import com.prat.student.serviceimpl.SubjectServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class RequestHandler {

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    GradeServiceImpl gradeService;

    @Autowired
    SubjectServiceImpl subjectService;

    public void requestHandler(Message message){
        switch(message.getEntity()){
            case  EntityType.STUDENT -> {
                if (message.requestType.equals("post")) {
                    log.info("request handler create");
                    studentService.createStudent((StudentRequest) message.getRequestBody());
                }
                else if(message.requestType.equals("put")){
                    log.info("request handler update");
                    studentService.updateStudent((StudentRequest) message.getRequestBody(), message.pathVariable);
                }
                else if(message.requestType.equals("get") ){
                    log.info("request handler get");
                    Student student = studentService.getStudentById(message.pathVariable);
                    log.info(student.getStudentName());
                }
                else if(message.requestType.equals("delete")){
                    log.info("request handler delete");
                    Integer studentId = studentService.deleteStudent(message.pathVariable);
                    log.info(""+studentId);
                }
                else if(message.requestType.equals("getAll")){
                    log.info("request handler get all students");
                    List<Student> studentList = studentService.getAllStudents();
                    log.info(studentList.get(1).getStudentName());
                }
                else if(message.requestType.equals("updateMarks")){
                    log.info("request Handler update mark");
                    boolean success = studentService.updateStudentMark(message.pathVariable, (HashMap<String, Float>) message.requestBody);
                    log.info(success+"");
                }
            }
            case  EntityType.GRADE -> {
                if (message.requestType.equals("post") || message.requestType.equals("put")) {

                }
                else if(message.requestType.equals("get") || message.requestType.equals("topper-list")
                        || message.requestType.equals("promote") || message.requestType.equals("student-list")){

                }
                else if(message.requestType.equals("getAll")){
                    log.info("request handler get all grade");
                    List<Grade> grades = gradeService.getGrades();
                    log.info(grades.get(0).getGradeNo() +"");
                }

            }
            case  EntityType.SUBJECT -> {
                if (message.requestType.equals("post")) {

                } else if (message.requestType.equals("get") || message.requestType.equals("delete")) {

                } else if (message.requestType.equals("getAll")) {

                }
            }
        }
    }

}
