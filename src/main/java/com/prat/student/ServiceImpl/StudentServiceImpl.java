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

    @Override
    public List<Student> getAllStudents(){
        List<Student> studentList = studentRepo.findAll();
        return studentList;
    }

    @Override
    public Optional<Student> getStudentById(Integer studentId){

        if(studentRepo.findById(studentId).isEmpty()){
            throw new StudentNotFoundException();
        }

        return studentRepo.findById(studentId);
    }

    @Override
    public void createStudent(StudentRequest newStudent){
        Student student = new Student(newStudent.getStudentId(),newStudent.getStudentName(),newStudent.getRollNo(),
                newStudent.getAddress(), newStudent.getContactNumber(), newStudent.getFatherName(),
                newStudent.getMotherName(), gradeRepo.findById(newStudent.getGradeNo()).orElseThrow(GradeNotFoundException::new));
        studentRepo.save(student);
    }

    @Override
    public void deleteStudent(Integer studentId){
        if(studentRepo.findById(studentId).isEmpty()){
            throw new StudentNotFoundException();
        }
        studentRepo.deleteById(studentId);
    }

    @Override
    public void updateStudent(StudentRequest updatedStudent) {


        Student student = new Student(updatedStudent.getStudentId(), updatedStudent.getStudentName(), updatedStudent.getRollNo(),
                updatedStudent.getAddress(), updatedStudent.getContactNumber(), updatedStudent.getFatherName(),
                updatedStudent.getMotherName(), gradeRepo.findById(updatedStudent.getGradeNo()).orElseThrow(GradeNotFoundException::new));
        studentRepo.save(student);
    }

    @Override
    public void updateStudentMark(Integer studentId, HashMap<String, Float> subjectMark){
        //getGradeIdFromStudent
        //getMaxAttemptsFromSubjectDefn
        // getTotalNoOfAttempts from MArks table
        // if(Attemts+1 > maxAttempts) throw MAxAtteptExceededError
        // new MArks(marks.save(new Marks(studentId,subjectId, gradeId, Attempt+1)))





        Student student = studentRepo.findByStudentId(studentId);
        if(student == null) throw new StudentNotFoundException();

        Grade grade = student.getCurrentGrade();
        List<Subject> sub = grade.getSubjects();

        for(Subject s : sub){
            List<Mark> mark = markRepo.findBySubjectAndStudentAndGrade(s,student,grade);

            if(subjectMark.containsKey(s.getSubjectName())){

                int attemptNo = Math.max(mark.size(), 1);

                Float singleMark = subjectMark.get(s.getSubjectName());

                if ( attemptNo+1 > s.getMaxAttempt()) throw new MaxAttemptExceededException();

                if(singleMark<0 || singleMark>s.getMaxMark()) throw new InvalidMarkException();

                Mark newMark = new Mark(singleMark,student,s, grade, attemptNo+1);
                markRepo.save(newMark);
            }
            else throw new SubjectNotFoundException();


        }
    }

}
