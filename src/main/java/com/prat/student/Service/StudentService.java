package com.prat.student.Service;

import com.prat.student.Entity.Student;
import com.prat.student.Model.StudentRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    public abstract List<Student> getAllStudents();
    public abstract Student getStudentById(Integer studentId);
    public abstract void createStudent(StudentRequest newStudent);
    public abstract void deleteStudent(Integer studentId);
    public abstract void updateStudent(StudentRequest updatedStudent);
    public abstract void updateStudentMark(Integer studentId, HashMap<String, Float> subjectMark);

}
