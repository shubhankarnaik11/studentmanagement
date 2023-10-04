package com.prat.student.controller;

import com.prat.student.entity.Grade;
import com.prat.student.entity.Student;
import com.prat.student.model.StudentRequest;
import com.prat.student.serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.*;
import java.util.HashMap;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private StudentServiceImpl studentService;
    Integer studentId = 1;
    Grade grade = new Grade(9);
    Student student1 = new Student("Name1",1,"Address1",9087654321L,"FatherName","MotherName",grade);
    Student student2 = new Student("Name2",2,"Address2",90876543281L,"Father_Name","Mother_Name",grade);
    StudentRequest studentReq = new StudentRequest("Name1",1,"Address1",9087654321L,"FatherName","MotherName",9);

    @Test
    public void getAllStudentsTest() throws Exception{
        mock.perform(get("/student/get-all-students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void getStudentByIdTest() throws Exception{
        when(studentService.getStudentById(1)).thenReturn(student1);
        mock.perform(get("/student/get-student/"+1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));;

    }

    @Test
    public void createStudentTest() throws Exception{
        String content = new ObjectMapper().writeValueAsString(studentReq);
        when(studentService.createStudent(studentReq)).thenReturn(student1);
        mock.perform(post("/student/create-student").contentType("application/json").content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void deleteStudentTest() throws Exception{
        when(studentService.deleteStudent(2)).thenReturn(2);
        mock.perform(delete("/student/delete-student/"+2)).andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Deleted student with Id"+2))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void updateStudentTest() throws Exception{
        when(studentService.updateStudent(studentReq)).thenReturn(student1);
        String content = new ObjectMapper().writeValueAsString(studentReq);
        mock.perform(put("/student/update-student").contentType("application/json").content(content)).andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Student Updated Successfully"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void updateStudentMarkTest() throws Exception{
        HashMap<String, Float> subjectMark = new HashMap<>();
        subjectMark.put("math",90f);
        when(studentService.updateStudentMark(1,subjectMark)).thenReturn(true);
        String content = new ObjectMapper().writeValueAsString(subjectMark);
        mock.perform(put("/student/update-student-marks/"+studentId).contentType("application/json").content(content)).andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Student Marks Updated Successfully"))
                .andExpect(jsonPath("$.success").value(true));
    }


}
