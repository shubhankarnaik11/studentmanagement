package com.prat.student.serviceimpl;

import com.prat.student.entity.Grade;
import com.prat.student.entity.Mark;
import com.prat.student.entity.Student;
import com.prat.student.entity.Subject;
import com.prat.student.exception.*;
import com.prat.student.model.StudentRequest;
import com.prat.student.repository.GradeRepository;
import com.prat.student.repository.MarkRepository;
import com.prat.student.repository.StudentRepository;
import com.prat.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.lang.Math;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    GradeRepository gradeRepo;
    @Autowired
    private MarkRepository markRepo;


    private Student findStudentByStudentId(Integer studentId){
        Student student = studentRepo.findByStudentId(studentId);
        if(student == null) throw new StudentNotFoundException("Student (ID : " + studentId +")");
        return student;
    }

    private Grade findByGradeNo(Integer gradeNo){
        Grade grade = gradeRepo.findByGradeNo(gradeNo);
        if(grade == null) throw new GradeNotFoundException("Grade " + gradeNo + " not found");
        return grade;
    }

    @Override
    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(Integer studentId){
        log.info("get student by id service");
        return findStudentByStudentId(studentId);
    }

    @Override
    public Student createStudent(StudentRequest newStudent){
        log.info("add student service");
        Student student = new Student(newStudent.getStudentName(),newStudent.getRollNo(),
                newStudent.getAddress(), newStudent.getContactNumber(), newStudent.getFatherName(),
                newStudent.getMotherName(), findByGradeNo(newStudent.getGradeNo()));
        studentRepo.save(student);

        return student;
    }

    @Override
    public Integer deleteStudent(Integer studentId){
        findStudentByStudentId(studentId);
        studentRepo.deleteById(studentId);
        return studentId;
    }

    @Override
    public Student updateStudent(StudentRequest updatedStudent, Integer studentId) {
        log.info("update student service");
        Student student = findStudentByStudentId(studentId);

        student.setStudentName(updatedStudent.getStudentName());
        student.setRollNo(updatedStudent.getRollNo());
        student.setAddress(updatedStudent.getAddress());
        student.setContactNumber(updatedStudent.getContactNumber());
        student.setFatherName(updatedStudent.getFatherName());
        student.setMotherName(updatedStudent.getMotherName());
        student.setCurrentGrade(findByGradeNo(updatedStudent.getGradeNo()));

        studentRepo.save(student);
        return student;
    }

    @Override
    public boolean updateStudentMark(Integer studentId, HashMap<String, Float> subjectMark){
        log.info("update mark service");
        Student student = findStudentByStudentId(studentId);

        Grade grade = student.getCurrentGrade();
        List<Subject> sub = grade.getSubjects();
        List<Mark> markList = new ArrayList<>();

        for(Subject s : sub){

            List<Mark> mark = markRepo.findBySubjectAndStudentAndGrade(s,student,grade);

            if(subjectMark.containsKey(s.getSubjectName())){
                int attemptNo = Math.max(mark.size()+1, 1);
                Float singleMark = Float.parseFloat(String.valueOf(subjectMark.get(s.getSubjectName())));
                if ( attemptNo > s.getMaxAttempt()) throw new MaxAttemptExceededException("Max Attempt Exceeded for subject " + s.getSubjectName());
                if( singleMark<0 || singleMark>s.getMaxMark()) throw new InvalidMarkException("Mark can be in the range [0,"+s.getMaxMark()+"] for " + s.getSubjectName());
                Mark newMark = new Mark(singleMark,student,s, grade, attemptNo);
                markList.add(newMark);
                subjectMark.remove(s.getSubjectName());
            }
        }

        if(!subjectMark.isEmpty()){
            String subjectName = subjectMark.entrySet().iterator().next().getKey();

            throw new SubjectNotFoundException("Subject "+ subjectName + " does not exists ");
        }

        markRepo.saveAll(markList);
        log.info("exiting update mark service");
        return true;
    }


}
