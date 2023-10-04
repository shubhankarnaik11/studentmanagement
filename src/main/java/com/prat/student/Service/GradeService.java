package com.prat.student.Service;

import com.prat.student.entity.Grade;
import com.prat.student.entity.Student;
import com.prat.student.model.GradeRequest;

import java.util.HashMap;
import java.util.List;

public interface GradeService {

    public abstract List<Grade> getGrades();
    public abstract Grade getGradeByGradeNo(Integer gradeNo);
    public abstract Grade createGrade(GradeRequest grade);
    public abstract Grade addSubjectsToGrade(Integer grade, List<String> subjects);
    public abstract List<HashMap<String, Object>> promoteAllStudentsByGrade(Integer gradeNo);
    //public abstract List<> getTopNStudents(Integer gradeNo, Integer n);

    public abstract List<Student> getGradeStudents(Integer gradeNo);

//    public abstract List<HashMap<Student, Float>> getNToppers(Integer gradeNo);

    public abstract List<HashMap<String, Object>> getNToppers(Integer gradeNo, Integer N);

}
