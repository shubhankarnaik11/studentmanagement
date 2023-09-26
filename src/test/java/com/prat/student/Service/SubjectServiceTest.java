package com.prat.student.Service;

import com.prat.student.Repository.SubjectRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {
    @Mock
    SubjectRepository subjectRepo;

    @InjectMocks
    SubjectService subjectService;


}
