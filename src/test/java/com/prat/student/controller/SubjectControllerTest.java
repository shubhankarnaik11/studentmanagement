package com.prat.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prat.student.entity.Subject;
import com.prat.student.model.SubjectRequest;
import com.prat.student.serviceimpl.SubjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SubjectControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    SubjectServiceImpl subjectService;

    List<Subject> subjectList = new ArrayList<>();
    Subject s1 = new Subject("math",100f, 35f,3);
    Subject s2 = new Subject("science",100f, 35f,3);
    SubjectRequest subjectRequest = new SubjectRequest("sciences",100f,35f,3);

    //List<SubjectRequest> subjectReqList = new ArrayList<>();
    @BeforeTestClass
    void before(){
        subjectList.add(s1);
        subjectList.add(s2);
        //subjectReqList.add(subjectRequest);
    }

    @Test
    public void getAllSubjectsTest() throws Exception{
        when(subjectService.getAllSubjects()).thenReturn(subjectList);
        mock.perform(get("/subjects/get-all-subjects")).andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void createSubjectTest() throws Exception{
        String content = new ObjectMapper().writeValueAsString(subjectRequest);
        when(subjectService.createSubject(subjectRequest)).thenReturn(s2);
        mock.perform(post("/subjects/create-subject").contentType("application/json").content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.msg").value("Subject Created Successfully"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void getSubjectTest() throws Exception{
        when(subjectService.getSubjectById(1)).thenReturn(s1);
        mock.perform(get("/subjects/get-subject/"+1)).andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Successful"))
                .andExpect(jsonPath("$.success").value(true));
    }
}
