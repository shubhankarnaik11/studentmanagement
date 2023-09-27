package com.prat.student.Service;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import com.prat.student.Exception.GradeNotFoundException;
import com.prat.student.Exception.SubjectAlreadyExistsException;
import com.prat.student.Exception.SubjectNotFoundException;
import com.prat.student.Model.GradeRequest;
import com.prat.student.Model.SubjectRequest;
import com.prat.student.Repository.GradeRepository;
import com.prat.student.Repository.SubjectRepository;
import com.prat.student.ServiceImpl.GradeServiceImpl;
import com.prat.student.ServiceImpl.SubjectServiceImpl;
import io.swagger.v3.oas.models.links.Link;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {

    @Mock
    GradeRepository gradeRepo;

    @Mock
    SubjectRepository subjectRepo;

    @InjectMocks
    GradeServiceImpl gradeService;

    @InjectMocks
    SubjectServiceImpl subjectService;

    Integer gradeNo = 10;
    @Mock
    Grade grade = new Grade(gradeNo);
    List<Subject> subjectList = new ArrayList<>();
    Subject s1 = new Subject("math",100f, 35f,3);
    Subject s2 = new Subject("science",100f, 35f,3);



    @BeforeTestClass
    void before(){
        subjectList.add(s1);
        subjectList.add(s2);
        grade.setSubjects(subjectList);
    }
    @Test
    public void getGradeTest(){
        List<Grade> grade = new ArrayList<>();
        grade.add(new Grade(1));
        grade.add(new Grade(2));
        when(gradeRepo.findAll()).thenReturn(grade);

        List<Grade> expectedGradeList = gradeService.getGrades();
        assertEquals(grade, expectedGradeList);

    }

    @Test
    public void getGradeByGradeNoTest(){

        when(gradeRepo.findByGradeNo(gradeNo)).thenReturn(grade);
        Grade expectedGrade = gradeService.getGradeByGradeNo(gradeNo);
        assertEquals(grade,expectedGrade);
    }

    @Test
    public void getGradeStudentsTest(){

        Student student1 = new Student("Name1",1,"Address1",9087654321L,"FatherName","MotherName",grade);
        Student student2 = new Student("Name2",2,"Address2",90876543281L,"Father_Name","Mother_Name",grade);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        when(gradeRepo.findByGradeNo(gradeNo)).thenReturn(grade);
        when(grade.getStudent()).thenReturn(studentList);
        assertEquals(gradeService.getGradeStudents(gradeNo),studentList);

    }

    @Test
    public void createGradeTestThrowsSubjectNotFoundError(){
        when(subjectRepo.findBySubjectName("english")).thenReturn(null);
        when(subjectRepo.findBySubjectName("math")).thenReturn(s1);
        when(subjectRepo.findBySubjectName("science")).thenReturn(s2);

        List<String> subjects = new LinkedList<>();
        subjects.add("math");
        subjects.add("science");
        subjects.add("english");

        GradeRequest grade = new GradeRequest(1, subjects);

        assertThrows(SubjectNotFoundException.class, ()-> gradeService.createGrade(grade));
    }

    @Test
    public void createGradeReturnsGradeWithSubjects(){
        when(subjectRepo.findBySubjectName("english")).thenReturn(s1);
        when(subjectRepo.findBySubjectName("math")).thenReturn(s1);
        when(subjectRepo.findBySubjectName("science")).thenReturn(s2);

        List<String> subjects = new LinkedList<>();
        subjects.add("math");
        subjects.add("science");
        subjects.add("english");

        GradeRequest grade = new GradeRequest(1, subjects);
        Grade newGrade = gradeService.createGrade(grade);

        assertNotNull(newGrade);
        assertFalse(newGrade.getSubjects().isEmpty());
    }

    @Test
    public void createGradeReturnsGradeWithoutSubjects(){

        List<String> subjects = new LinkedList<>();

        GradeRequest grade = new GradeRequest(1, subjects);
        Grade newGrade = gradeService.createGrade(grade);

        assertNotNull(newGrade);
        assertTrue(newGrade.getSubjects().isEmpty());
    }

    @Test
    public void addSubjectsToGradeThrowsGradeNotFoundError(){
        when(gradeRepo.findByGradeNo(10)).thenReturn(null);
        assertThrows(GradeNotFoundException.class, ()-> gradeService.addSubjectsToGrade(10, new LinkedList<>()));
    }

    @Test
    public void addSubjectsToGradeThrowsSubjectNotFoundException(){



        when(gradeRepo.findByGradeNo(10)).thenReturn(grade);



        when(subjectRepo.findBySubjectName("math")).thenReturn(s1);
        when(subjectRepo.findBySubjectName("science")).thenReturn(s2);
        when(subjectRepo.findBySubjectName("english")).thenReturn(null);

        List<String> subjects =  new LinkedList<>();
        subjects.add("math");
        subjects.add("science");
        subjects.add("english");
        assertThrows(SubjectNotFoundException.class , ()-> gradeService.addSubjectsToGrade(10, subjects));
    }

    @Test
    public void addSubjectsToGradeThrowsSubjectAlreadyExistsException(){

        Grade newGrade = new Grade(10);
        Subject newS1 = new Subject("math",100f, 35f,3);

        List<Subject> newSubjectList = new LinkedList<Subject>();
        newSubjectList.add(newS1);
        newGrade.setSubjects(newSubjectList);

        when(gradeRepo.findByGradeNo(10)).thenReturn(newGrade);
        when(subjectRepo.findBySubjectName("math")).thenReturn(s1);

        List<String> subjects =  new LinkedList<>();
        subjects.add("math");

        assertThrows(SubjectAlreadyExistsException.class , ()-> gradeService.addSubjectsToGrade(10, subjects));
    }

    @Test
    public void addSubjectToGradeReturnsGrade(){

        Grade existingGrade = new Grade(10);
        Subject existingS1 = new Subject("math",100f, 35f,3);

        List<Subject> existingSubjectList = new LinkedList<Subject>();
        existingSubjectList.add(existingS1);
        existingGrade.setSubjects(existingSubjectList);

        when(gradeRepo.findByGradeNo(10)).thenReturn(existingGrade);
        when(subjectRepo.findBySubjectName("science")).thenReturn(s2);


        List<String> subjects =  new LinkedList<>();
        subjects.add("science");

        Grade existingGradeWithAddedSubject = gradeService.addSubjectsToGrade(10, subjects);

        assertNotNull(existingGradeWithAddedSubject);
    }

}
