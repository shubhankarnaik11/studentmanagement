package com.prat.student.Service;

import com.prat.student.Entity.Subject;
import com.prat.student.Model.SubjectRequest;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    public abstract void createSubject(SubjectRequest subject);
    public abstract List<Subject> getAllSubjects();
    public abstract Optional<Subject> getSubjectById(Integer subjectId);

}
