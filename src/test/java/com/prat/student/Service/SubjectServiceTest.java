package com.prat.student.Service;

import com.prat.student.Entity.Subject;
import com.prat.student.Exception.SubjectNotFoundException;
import com.prat.student.Repository.SubjectRepository;
import com.prat.student.ServiceImpl.SubjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {
    @Mock
    SubjectRepository subjectRepo;

    @InjectMocks
    SubjectServiceImpl subjectService;

    List<Subject> subjectList = new ArrayList<>();
    Subject s1 = new Subject("math",100f, 35f,3);
    Subject s2 = new Subject("science",100f, 35f,3);

    @BeforeTestClass
    void before(){
        subjectList.add(s1);
        subjectList.add(s2);
    }

    @Test
    public void getAllSubjectsTest(){
        when(subjectRepo.findAll()).thenReturn(subjectList);
        assertEquals(subjectService.getAllSubjects(),subjectList);
    }

    @Test
    public void getSubjectByIdTest(){
        when(subjectRepo.findBySubjectId(1)).thenReturn(s1);
        assertEquals(subjectService.getSubjectById(1),s1);
    }

    @Test()
    public void getSubjectByIdThrowsSubjectNotFoundException(){
        when(subjectRepo.findBySubjectId(1)).thenReturn(null);
        assertThrows(SubjectNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                subjectService.getSubjectById(1);
            }
        });

    }

    @Test
    public void createSubjectTest(){

    }


}