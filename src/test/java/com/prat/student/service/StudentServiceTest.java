package com.prat.student.service;

import com.prat.student.entity.Grade;
import com.prat.student.entity.Mark;
import com.prat.student.entity.Student;
import com.prat.student.entity.Subject;
import com.prat.student.exception.GradeNotFoundException;
import com.prat.student.exception.InvalidMarkException;
import com.prat.student.exception.MaxAttemptExceededException;
import com.prat.student.model.StudentRequest;
import com.prat.student.repository.GradeRepository;
import com.prat.student.repository.MarkRepository;
import com.prat.student.repository.StudentRepository;
import com.prat.student.serviceimpl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.*;

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
    StudentRequest studentReq = new StudentRequest("Name1",1,"Address1","9087654321","FatherName","MotherName",9);
    Student student1 = new Student("Name1",1,"Address1","9087654321","FatherName","MotherName",grade);
    Student student2 = new Student("Name2",2,"Address2","90876543281","Father_Name","Mother_Name",grade);
    List<Student> studentList = new ArrayList<>();
    Subject s1 = new Subject("math",100, 35,3);
    Subject s2 = new Subject("science",100, 35,3);
    List<Subject> subjects = new ArrayList<>();
    @BeforeTestClass
    void before(){
        studentList.add(student1);
        studentList.add(student2);
        subjects.add(s1);
        grade.setSubjects(subjects);
        student1.setCurrentGrade(grade);
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
        when(studentRepo.findByStudentId(1)).thenReturn(student1);
        when(gradeRepo.findByGradeNo(9)).thenReturn(newGrade);
        student1.setStudentName(studentReq.getStudentName());
        student1.setRollNo(studentReq.getRollNo());
        student1.setAddress(studentReq.getAddress());
        student1.setFatherName(studentReq.getFatherName());
        student1.setMotherName(studentReq.getMotherName());
        student1.setCurrentGrade(newGrade);

        Student newStudent = studentService.updateStudent(studentReq,1);
        assertNotNull(newStudent);
    }

    @Test
    public void updateStudentThrowsGradeNotFoundExceptionTest(){
        when(gradeRepo.findByGradeNo(9)).thenReturn(null);
        assertThrows(GradeNotFoundException.class, ()-> studentService.createStudent(studentReq));
    }

    @Test
    public void updateStudentMarkTest(){
        subjects.add(s1);
        newGrade.setSubjects(subjects);
        student1.setCurrentGrade(newGrade);
        List<Mark> markList = new ArrayList<>();
        Mark m1 = new Mark(95f,student1,s1,newGrade,1);
        markList.add(m1);

        HashMap<String, Float> subjectMark = new HashMap<>();
        subjectMark.put("math",90f);
        when(studentRepo.findByStudentId(1)).thenReturn(student1);

        assertTrue(studentService.updateStudentMark(1, subjectMark));
        assertEquals(1, markList.size());
        Mark newMark = markList.get(0);
        assertEquals(95.0f, newMark.getMark());
        assertEquals(student1, newMark.getStudent());
        assertEquals(s1, newMark.getSubject());
        assertEquals(newGrade, newMark.getGrade());
        assertEquals(1, newMark.getAttempt());
    }

    @Test
    public void updateStudentMarkMaxAttemptExceededTest(){
        subjects.add(s1);
        newGrade.setSubjects(subjects);
        student1.setCurrentGrade(newGrade);
        List<Mark> existingMarks = Arrays.asList(
                new Mark(90.0f, student1, s1, newGrade, 1),
                new Mark(85.0f, student1, s1, newGrade, 2),
                new Mark(85.0f, student1, s1, newGrade, 3)

        );
        when(markRepo.findBySubjectAndStudentAndGrade(s1, student1, newGrade)).thenReturn(existingMarks);
        when(studentRepo.findByStudentId(1)).thenReturn(student1);
        HashMap<String, Float> subjectMark = new HashMap<>();
        subjectMark.put("math", 95.0f);

        assertThrows(MaxAttemptExceededException.class, () -> studentService.updateStudentMark(1, subjectMark));

    }

    @Test
    public void updateStudentMarkInvalidMarkExceptionTest(){
        subjects.add(s1);
        newGrade.setSubjects(subjects);
        student1.setCurrentGrade(newGrade);
        List<Mark> existingMarks = Arrays.asList(
                new Mark(90.0f, student1, s1, newGrade, 1),
                new Mark(85.0f, student1, s1, newGrade, 2)

        );
        when(markRepo.findBySubjectAndStudentAndGrade(s1, student1, newGrade)).thenReturn(existingMarks);
        when(studentRepo.findByStudentId(1)).thenReturn(student1);
        HashMap<String, Float> subjectMark = new HashMap<>();
        subjectMark.put("math", -95.0f);

        assertThrows(InvalidMarkException.class, () -> studentService.updateStudentMark(1, subjectMark));

    }

}
