package com.prat.student.service;

import com.prat.student.entity.Student;
import com.prat.student.model.StudentRequest;

import java.util.HashMap;
import java.util.List;

public interface StudentService {

    public abstract List<Student> getAllStudents();
    public abstract Student getStudentById(Integer studentId);
    public abstract Student createStudent(StudentRequest newStudent);
    public abstract Integer deleteStudent(Integer studentId);
    public abstract Student updateStudent(StudentRequest updatedStudent, Integer studentId);
    public abstract boolean updateStudentMark(Integer studentId, HashMap<String, Float> subjectMark);

}
