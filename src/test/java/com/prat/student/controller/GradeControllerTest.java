package com.prat.student.controller;

import com.prat.student.entity.Grade;
import com.prat.student.entity.Student;
import com.prat.student.model.GradeRequest;
import com.prat.student.serviceimpl.GradeServiceImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GradeControllerTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private GradeServiceImpl gradeService;

    Integer gradeNo = 10;
    Grade grade = new Grade(gradeNo);
    List<String> subjectList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    Student student1 = new Student("Name1",1,"Address1",9087654321L,"FatherName","MotherName",grade);
    Student student2 = new Student("Name2",2,"Address2",90876543281L,"Father_Name","Mother_Name",grade);

    List<String> newSubjectList = new ArrayList<>();
    GradeRequest gradeRequest = new GradeRequest();
    List<HashMap<String, Object>> promotedList = new ArrayList<>();
    HashMap<String, Object> studentPromotionList = new HashMap<>();
    @BeforeTestClass
    void before(){
        subjectList.add("math");
        subjectList.add("science");
        newSubjectList.add("english");
        gradeRequest.setGradeNo(gradeNo);
        gradeRequest.setSubjects(subjectList);
    }

    @Test
    public void getGradesTest() throws Exception{
        mock.perform(get("/grades/get-all-grades")).andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));

    }

    @Test
    public void getGradeByGradeNoTest() throws Exception{
        when(gradeService.getGradeByGradeNo(10)).thenReturn(grade);
        mock.perform(get("/grades/get-grade-by-grade-no/"+gradeNo)).andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void createStudentTest() throws Exception{
        String content = new ObjectMapper().writeValueAsString(gradeRequest);
        when(gradeService.createGrade(gradeRequest)).thenReturn(grade);
        mock.perform(post("/grades/create-grade").contentType("application/json").content(content)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void addSubjectsToGradeTest() throws Exception{
        String content = new ObjectMapper().writeValueAsString(newSubjectList);
        when(gradeService.addSubjectsToGrade(gradeNo,subjectList)).thenReturn(grade);
        mock.perform(put("/grades/add-subjects-to-grade/"+gradeNo).contentType("application/json").content(content)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void getGradeStudentsTest() throws Exception{
        when(gradeService.getGradeStudents(gradeNo)).thenReturn(studentList);
        mock.perform(get("/grades/get-grade-students/"+gradeNo)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void promoteGradeTest() throws Exception{
        when(gradeService.promoteAllStudentsByGrade(gradeNo)).thenReturn(promotedList);
        mock.perform(post("/grades/promote/"+gradeNo)).andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void getToppersListTest() throws Exception{
        when(gradeService.promoteAllStudentsByGrade(gradeNo)).thenReturn(promotedList);
        mock.perform(get("/grades/get-toppers-list/"+gradeNo).param("n", "3")).andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));
    }
}
