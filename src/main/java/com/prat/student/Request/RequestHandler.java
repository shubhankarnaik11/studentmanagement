package com.prat.student.Request;

import com.prat.student.entity.Grade;
import com.prat.student.entity.Student;
import com.prat.student.entity.Subject;
import com.prat.student.model.GradeRequest;
import com.prat.student.model.StudentRequest;
import com.prat.student.model.SubjectRequest;
import com.prat.student.service.GradeService;
import com.prat.student.service.StudentService;
import com.prat.student.service.SubjectService;
import com.prat.student.serviceimpl.GradeServiceImpl;
import com.prat.student.serviceimpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class RequestHandler implements StudentService, GradeService, SubjectService {

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    GradeServiceImpl gradeService;

    @Autowired
    SubjectService subjectService;


    @Override
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentService.getStudentById(studentId);
    }

    @Override
    public Student createStudent(StudentRequest newStudent) {
        return studentService.createStudent(newStudent);
    }

    @Override
    public Integer deleteStudent(Integer studentId) {
        return studentService.deleteStudent(studentId);
    }

    @Override
    public Student updateStudent(StudentRequest updatedStudent, Integer studentId) {
        return studentService.updateStudent(updatedStudent, studentId);
    }

    @Override
    public boolean updateStudentMark(Integer studentId, HashMap<String, Float> subjectMark) {
        return studentService.updateStudentMark(studentId, subjectMark);
    }

    @Override
    public List<Grade> getGrades() {
        return null;
    }

    @Override
    public Grade getGradeByGradeNo(Integer gradeNo) {
        return null;
    }

    @Override
    public Grade createGrade(GradeRequest grade) {
        return null;
    }

    @Override
    public Grade addSubjectsToGrade(Integer grade, List<String> subjects) {
        return null;
    }

    @Override
    public List<HashMap<String, Object>> promoteAllStudentsByGrade(Integer gradeNo) {
        return null;
    }

    @Override
    public List<Student> getGradeStudents(Integer gradeNo) {
        return null;
    }

    @Override
    public List<HashMap<String, Object>> getNToppers(Integer gradeNo, Integer N) {
        return null;
    }

    @Override
    public Subject createSubject(SubjectRequest subject) throws Exception {
        return null;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return null;
    }

    @Override
    public Subject getSubjectById(Integer subjectId) {
        return null;
    }
}
