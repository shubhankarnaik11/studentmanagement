package com.prat.student.service;

import com.prat.student.dto.SubjectDto;
import com.prat.student.entity.Subject;
import com.prat.student.exception.SubjectAlreadyExistsException;
import com.prat.student.exception.SubjectNotFoundException;
import com.prat.student.model.SubjectRequest;
import com.prat.student.repository.SubjectRepository;
import com.prat.student.serviceimpl.SubjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        assertThrows(SubjectNotFoundException.class, ()-> subjectService.getSubjectById(1));

    }

    @Test
    public void createSubjectThrowsSubjectAlreadyExists(){
        when(subjectRepo.findBySubjectName("math")).thenReturn(s1);
        assertThrows(SubjectAlreadyExistsException.class, ()-> subjectService.createSubject(new SubjectDto("math", 100, 35,  3)));


    }

    @Test
    public void createSubjectSubject(){

        when(subjectRepo.findBySubjectName("math")).thenReturn(null);

        SubjectDto subjectRequest = new SubjectDto("math", 100, 35, 3);
        Subject s6 = subjectService.createSubject(subjectRequest);
        assertNotNull(s6);
    }
}
