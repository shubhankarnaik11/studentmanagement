package com.prat.student.Service;

import com.prat.student.Entity.Grade;
import com.prat.student.Model.GradeRequest;

import java.util.List;
import java.util.Optional;

public interface GradeService {

    public abstract List<Grade> getGrades();
    public abstract Optional<Grade> getGradeById(Integer gradeNo);
    public abstract void createGrade(GradeRequest grade);
    public abstract void addSubjectsToGrade(Integer grade, List<String> subjects);
}
