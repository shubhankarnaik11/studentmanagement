package com.prat.student.Service;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import com.prat.student.Model.GradeRequest;
import com.prat.student.Repository.GradeRepository;
import com.prat.student.Repository.SubjectRepository;
import com.prat.student.ServiceImpl.GradeServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {

    @Mock
    GradeRepository gradeRepo;

    @Mock
    SubjectRepository subjectRepo;

    @InjectMocks
    GradeServiceImpl gradeService;

    Integer gradeNo =10;
    @Mock
    Grade grade = new Grade(gradeNo);
    List<Subject> subjectList = new ArrayList<>();
    Subject s1 = new Subject("math",100f, 35f,3);
    Subject s2 = new Subject("science",100f, 35f,3);

    @BeforeTestClass
    void before(){
        subjectList.add(s1);
        subjectList.add(s2);
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



//    @Test
//    public void createGradeTest(){
//        GradeRequest grade = new GradeRequest();
//        grade.setGradeNo(1);
//        List<String> subjects = new ArrayList<>();
//        subjects.add("math");
//        subjects.add("science");
//        grade.setSubjects(subjects);
//        when(subjectRepo.findBySubjectName("math")).thenReturn(s1);
//        when(subjectRepo.findBySubjectName("science")).thenReturn(s2);
//        List<Subject> subjectsList = new ArrayList<>();
//        subjectsList.add(s1);
//        subjectsList.add(s2);
//        Grade newGrade = new Grade(1);
//        newGrade.setSubjects(subjectsList);
//        doNothing().when(gradeRepo).save(any());
//
//        verify(gradeService,times(1)).createGrade(grade);
//    }



}
