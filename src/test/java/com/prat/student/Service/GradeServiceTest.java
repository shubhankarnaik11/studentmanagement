package com.prat.student.Service;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Subject;
import com.prat.student.Repository.GradeRepository;
import com.prat.student.Repository.SubjectRepository;
import com.prat.student.ServiceImpl.GradeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {

    @Mock
    GradeRepository gradeRepo;

//    @Mock
//    SubjectRepository subjectRepo;

    @InjectMocks
    GradeServiceImpl gradeService;

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

        List<Grade> expectedGradeListGradeList = gradeService.getGrades();
        assertEquals(grade, expectedGradeListGradeList);

    }

    @Test
    public void getGradeByIdTest(){
        Integer gradeNo =10;
        Grade grade = new Grade(1);
        when(gradeRepo.findByGradeNo(gradeNo)).thenReturn(grade);
        Grade expectedGrade = gradeService.getGradeByGradeNo(gradeNo);
        assertEquals(grade,expectedGrade);
    }





}
