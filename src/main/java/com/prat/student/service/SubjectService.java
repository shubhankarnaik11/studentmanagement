package com.prat.student.service;

import com.prat.student.entity.Subject;
import com.prat.student.model.SubjectRequest;

import java.util.List;

public interface SubjectService {
    public abstract Subject createSubject(SubjectRequest subject);
    public abstract List<Subject> getAllSubjects();
    public abstract Subject getSubjectById(Integer subjectId);

}
