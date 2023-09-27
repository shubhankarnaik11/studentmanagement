package com.prat.student.Service;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Student;
import com.prat.student.Model.GradeRequest;

import java.util.HashMap;
import java.util.List;

public interface GradeService {

    public abstract List<Grade> getGrades();
    public abstract Grade getGradeByGradeNo(Integer gradeNo);
    public abstract void createGrade(GradeRequest grade);
    public abstract void addSubjectsToGrade(Integer grade, List<String> subjects);
    public abstract List<HashMap<String, Object>> promoteAllStudentsByGrade(Integer gradeNo);
    //public abstract List<> getTopNStudents(Integer gradeNo, Integer n);

    public abstract List<Student> getGradeStudents(Integer gradeNo);

//    public abstract List<HashMap<Student, Float>> getNToppers(Integer gradeNo);

    public abstract List<HashMap<String, Object>> getNToppers(Integer gradeNo, Integer N);

}
