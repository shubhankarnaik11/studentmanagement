package com.prat.student.ServiceImpl;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Mark;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import com.prat.student.Exception.GradeNotFoundException;
import com.prat.student.Exception.InvalidMarkException;
import com.prat.student.Exception.StudentNotFoundException;
import com.prat.student.Exception.SubjectNotFoundException;
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
        Student student = studentRepo.findByStudentId(studentId);
        if(student == null) throw new StudentNotFoundException();
        Grade grade = student.getGradeNo();
        List<Subject> sub = grade.getSubjects();
        for(Subject s : sub){
            Mark mark = markRepo.findBySubjectAndStudent(s,student);
            if(subjectMark.containsKey(s.getSubjectName())){
                Float singleMark = subjectMark.get(s.getSubjectName());
                if(singleMark<0 || singleMark>100) throw new InvalidMarkException();
                if(mark != null) {
                    mark.setMark(singleMark);
                }
                else{
                        mark = new Mark(singleMark,student,s);
                }
            }
            else throw new SubjectNotFoundException();
            markRepo.save(mark);
        }
    }

}
