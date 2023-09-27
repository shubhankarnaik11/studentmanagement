package com.prat.student.Service;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Student;
import com.prat.student.Repository.StudentRepository;
import com.prat.student.ServiceImpl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepo;

    @InjectMocks
    StudentServiceImpl studentService;

    @Mock
    Grade grade = new Grade(10);
    Student student1 = new Student("Name1",1,"Address1",9087654321L,"FatherName","MotherName",grade);
    Student student2 = new Student("Name2",2,"Address2",90876543281L,"Father_Name","Mother_Name",grade);
    List<Student> studentList = new ArrayList<>();

    @BeforeTestClass
    void before(){
        studentList.add(student1);
        studentList.add(student2);
    }

    @Test
    public void getAllStudentTest(){
        when(studentRepo.findAll()).thenReturn(studentList);
        assertEquals(studentService.getAllStudents(),studentList);
    }

    @Test
    public void getStudentByIdTest(){
        when(studentRepo.findByStudentId(1)).thenReturn(student1);
        assertEquals(studentService.getStudentById(1),student1);
    }
}
