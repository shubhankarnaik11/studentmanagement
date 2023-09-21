package com.prat.student.ServiceImpl;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Mark;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import com.prat.student.Exception.*;
import com.prat.student.Model.StudentRequest;
import com.prat.student.Repository.GradeRepository;
import com.prat.student.Repository.MarkRepository;
import com.prat.student.Repository.StudentRepository;
import com.prat.student.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.lang.Math;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    GradeRepository gradeRepo;
    @Autowired
    private MarkRepository markRepo;

    private Student findStudentByStudentId(Integer studentId){
        Student student = studentRepo.findByStudentId(studentId);
        if(student == null) throw new StudentNotFoundException();
        return student;
    }

    @Override
    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(Integer studentId){
        return findStudentByStudentId(studentId);
    }

    @Override
    public void createStudent(StudentRequest newStudent){

        Student student = new Student(newStudent.getStudentName(),newStudent.getRollNo(),
                newStudent.getAddress(), newStudent.getContactNumber(), newStudent.getFatherName(),
                newStudent.getMotherName(), gradeRepo.findById(newStudent.getGradeNo()).orElseThrow(GradeNotFoundException::new));
        studentRepo.save(student);
    }

    @Override
    public void deleteStudent(Integer studentId){
        findStudentByStudentId(studentId);
        studentRepo.deleteById(studentId);
    }

    @Override
    public void updateStudent(StudentRequest updatedStudent) {
        Student student = new Student(updatedStudent.getStudentName(), updatedStudent.getRollNo(),
                updatedStudent.getAddress(), updatedStudent.getContactNumber(), updatedStudent.getFatherName(),
                updatedStudent.getMotherName(), gradeRepo.findById(updatedStudent.getGradeNo()).orElseThrow(GradeNotFoundException::new));
        studentRepo.save(student);
    }

    @Override
    public void updateStudentMark(Integer studentId, HashMap<String, Float> subjectMark){

        Student student = findStudentByStudentId(studentId);

        Grade grade = student.getCurrentGrade();
        List<Subject> sub = grade.getSubjects();
        List<Mark> markList = new ArrayList<>();

        for(Subject s : sub){

            List<Mark> mark = markRepo.findBySubjectAndStudentAndGrade(s,student,grade);

            if(subjectMark.containsKey(s.getSubjectName())){
                int attemptNo = Math.max(mark.size()+1, 1);
                Float singleMark = subjectMark.get(s.getSubjectName());
                if ( attemptNo > s.getMaxAttempt()) throw new MaxAttemptExceededException();
                if( singleMark<0 || singleMark>s.getMaxMark()) throw new InvalidMarkException();
                Mark newMark = new Mark(singleMark,student,s, grade, attemptNo);
                markList.add(newMark);
                subjectMark.remove(s.getSubjectName());
            }
        }

        if(!subjectMark.isEmpty()) throw new SubjectNotFoundException();

        markRepo.saveAll(markList);
    }

}
