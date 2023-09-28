package com.prat.student.Service;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Mark;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import com.prat.student.Exception.GradeNotFoundException;
import com.prat.student.Model.StudentRequest;
import com.prat.student.Repository.GradeRepository;
import com.prat.student.Repository.MarkRepository;
import com.prat.student.Repository.StudentRepository;
import com.prat.student.ServiceImpl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepo;

    @Mock
    GradeRepository gradeRepo;

    @Mock
    MarkRepository markRepo;

    @InjectMocks
    StudentServiceImpl studentService;

    Integer gradeNo = 10;
    @Mock
    Grade grade = new Grade(gradeNo);

    Grade newGrade = new Grade(9);
    StudentRequest studentReq = new StudentRequest("Name1",1,"Address1",9087654321L,"FatherName","MotherName",9);
    Student student1 = new Student("Name1",1,"Address1",9087654321L,"FatherName","MotherName",grade);
    Student student2 = new Student("Name2",2,"Address2",90876543281L,"Father_Name","Mother_Name",grade);
    List<Student> studentList = new ArrayList<>();
    Subject s1 = new Subject("math",100f, 35f,3);
    Subject s2 = new Subject("science",100f, 35f,3);

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

    @Test
    public void createStudentTest(){
        when(gradeRepo.findByGradeNo(9)).thenReturn(newGrade);
        Student student = new Student(studentReq.getStudentName(),studentReq.getRollNo(),
                studentReq.getAddress(), studentReq.getContactNumber(), studentReq.getFatherName(),
                studentReq.getMotherName(), gradeRepo.findByGradeNo(studentReq.getGradeNo()));

        Student newStudent = studentService.createStudent(studentReq);
        assertNotNull(newStudent);
    }

    @Test
    public void createStudentThrowsGradeNotFoundException(){
        when(gradeRepo.findByGradeNo(9)).thenReturn(null);
        assertThrows(GradeNotFoundException.class, ()-> studentService.createStudent(studentReq));
    }

    @Test
    public void deleteStudentTest(){
        when(studentRepo.findByStudentId(1)).thenReturn(student1);
        assertEquals(studentService.deleteStudent(1),1);
    }

    @Test
    public void updateStudentTest(){
        when(gradeRepo.findByGradeNo(9)).thenReturn(newGrade);
        Student student = new Student(studentReq.getStudentName(),studentReq.getRollNo(),
                studentReq.getAddress(), studentReq.getContactNumber(), studentReq.getFatherName(),
                studentReq.getMotherName(), gradeRepo.findByGradeNo(studentReq.getGradeNo()));

        Student newStudent = studentService.updateStudent(studentReq);
        assertNotNull(newStudent);
    }

    @Test
    public void updateStudentThrowsGradeNotFoundExceptionTest(){
        when(gradeRepo.findByGradeNo(9)).thenReturn(null);
        assertThrows(GradeNotFoundException.class, ()-> studentService.createStudent(studentReq));
    }

    @Test
    public void updateStudentMarkTest(){
        List<Mark> markList = new ArrayList<>();
        Mark mark1 = new Mark(100f,student1,s1,newGrade,2);
        markList.add(mark1);
        HashMap<String, Float> subjectMark = new HashMap<>();

        when(studentRepo.findByStudentId(1)).thenReturn(student1);
        when(markRepo.findBySubjectAndStudentAndGrade(s1,student1,grade)).thenReturn(markList);
        assertTrue(studentService.updateStudentMark(1,subjectMark));
    }
}
