package com.prat.student.Controller;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Student;
import com.prat.student.Model.GradeRequest;
import com.prat.student.Model.StudentRequest;
import com.prat.student.ServiceImpl.GradeServiceImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
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

    List<String> newSubjectList = new ArrayList<>();
    GradeRequest gradeRequest = new GradeRequest();
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
        mock.perform(get("/grades/get-all-grades")).andExpect(status().isOk());

    }

    @Test
    public void getGradeByGradeNoTest() throws Exception{
        when(gradeService.getGradeByGradeNo(10)).thenReturn(grade);
        MvcResult mvcResult = mock.perform(get("/grades/get-grade-by-grade-no/"+gradeNo)).andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void createStudentTest() throws Exception{
        String content = new ObjectMapper().writeValueAsString(gradeRequest);
        when(gradeService.createGrade(gradeRequest)).thenReturn(grade);
        MvcResult mvcResult = mock.perform(post("/grades/create-grade").contentType("application/json").content(content)).andReturn();
        assertEquals(201,mvcResult.getResponse().getStatus());
    }

    @Test
    public void addSubjectsToGradeTest() throws Exception{
        String content = new ObjectMapper().writeValueAsString(newSubjectList);
        when(gradeService.addSubjectsToGrade(gradeNo,subjectList)).thenReturn(grade);
        MvcResult mvcResult = mock.perform(post("/grades/add-subjects-to-grade/"+gradeNo).contentType("application/json").content(content)).andReturn();
        assertEquals(201,mvcResult.getResponse().getStatus());
    }
}
