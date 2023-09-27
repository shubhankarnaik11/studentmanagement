package com.prat.student.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Student;
import com.prat.student.Model.StudentRequest;
import com.prat.student.Service.StudentService;
import com.prat.student.ServiceImpl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
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
        mock.perform(get("/student/get-all-students")).andExpect(status().isOk());
    }

    @Test
    public void getStudentByIdTest() throws Exception{
        when(studentService.getStudentById(1)).thenReturn(student1);
        MvcResult mvcResult = mock.perform(get("/student/get-student/"+1)).andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void createStudentTest() throws Exception{
        String content = new ObjectMapper().writeValueAsString(studentReq);
        when(studentService.createStudent(studentReq)).thenReturn(student1);
        MvcResult mvcResult = mock.perform(post("/student/create-student").contentType("application/json").content(content)).andReturn();
        assertEquals(201,mvcResult.getResponse().getStatus());
    }

    @Test
    public void deleteStudentTest() throws Exception{
        when(studentService.deleteStudent(1)).thenReturn(1);
        MvcResult mvcResult = mock.perform(delete("/student/delete-student/"+2)).andReturn();
        assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void updateStudentTest() throws Exception{
        when(studentService.updateStudent(studentReq)).thenReturn(student1);
        String content = new ObjectMapper().writeValueAsString(studentReq);
        MvcResult mvcResult = mock.perform(put("/student/update-student").contentType("application/json").content(content)).andReturn();

        assertEquals(200,mvcResult.getResponse().getStatus());
    }

    @Test
    public void updateStudentMarkTest() throws Exception{
        HashMap<String, Float> subjectMark = new HashMap<>();
        subjectMark.put("math",90f);
        when(studentService.updateStudentMark(1,subjectMark)).thenReturn(true);
        String content = new ObjectMapper().writeValueAsString(subjectMark);
        MvcResult mvcResult = mock.perform(put("/student/update-student-marks/"+studentId).contentType("application/json").content(content)).andReturn();

        assertEquals(200,mvcResult.getResponse().getStatus());
    }


}
