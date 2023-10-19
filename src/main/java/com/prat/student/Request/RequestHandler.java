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
import com.prat.student.serviceimpl.SubjectServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class RequestHandler implements StudentService, GradeService, SubjectService {

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    GradeServiceImpl gradeService;

    @Autowired
    SubjectServiceImpl subjectService;


    @Override
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Override
    public Student getStudentById(Integer studentId) {
        log.info("student record with id {} : {}",studentId, studentService.getStudentById(studentId).getStudentName());
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
        return gradeService.getGrades();
    }

    @Override
    public Grade getGradeByGradeNo(Integer gradeNo) {
        return gradeService.getGradeByGradeNo(gradeNo);
    }

    @Override
    public Grade createGrade(GradeRequest grade) {
        return gradeService.createGrade(grade);
    }

    @Override
    public Grade addSubjectsToGrade(Integer grade, List<String> subjects) {
        return gradeService.addSubjectsToGrade(grade, subjects);
    }

    @Override
    public List<HashMap<String, Object>> promoteAllStudentsByGrade(Integer gradeNo) {
        return gradeService.promoteAllStudentsByGrade(gradeNo);
    }

    @Override
    public List<Student> getGradeStudents(Integer gradeNo) {
        return gradeService.getGradeStudents(gradeNo);
    }

    @Override
    public List<HashMap<String, Object>> getNToppers(Integer gradeNo, Integer N) {
        return gradeService.getNToppers(gradeNo, N);
    }

    @Override
    public Subject createSubject(SubjectRequest subject) throws Exception {
        return subjectService.createSubject(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @Override
    public Subject getSubjectById(Integer subjectId) {
        return subjectService.getSubjectById(subjectId);
    }
}
