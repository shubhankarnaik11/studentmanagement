package com.prat.student.service;

import com.prat.student.dto.SubjectDto;
import com.prat.student.entity.Subject;

import java.util.List;

public interface SubjectService {
    public abstract Subject createSubject(SubjectDto subject);
    public abstract List<Subject> getAllSubjects();
    public abstract Subject getSubjectById(Integer subjectId);

}
