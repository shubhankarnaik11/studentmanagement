package com.prat.student.ServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.lang.Math;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    GradeRepository gradeRepo;
    @Autowired
    private MarkRepository markRepo;

    public static ObjectMapper objectMapper = new ObjectMapper();

    public <T> T entityToDTOConversion(Object entity, Class<T> T){
        return objectMapper.convertValue(entity, T);
    }

    private Student findStudentByStudentId(Integer studentId){
        Student student = studentRepo.findByStudentId(studentId);
        //return entityToDTOConversion(studentRepo.findByStudentId(studentId), StudentRequest.class);
        if(student == null) throw new StudentNotFoundException();
        return student;
    }

    private Mark getMaxMark(LinkedList <Mark> marks){

        Mark maxMark = marks.getFirst();
        for(Mark mark : marks){
            if(maxMark.getMark() < mark.getMark()){
                maxMark = mark;
            }
        }
        return maxMark;

    }

    private Grade findByGradeNo(Integer gradeNo){
        Grade grade = gradeRepo.findByGradeNo(gradeNo);
        if(grade == null) throw new GradeNotFoundException();
        return grade;
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
    public Student createStudent(StudentRequest newStudent){

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
    public Student updateStudent(StudentRequest updatedStudent) {
        Student student = new Student(updatedStudent.getStudentName(), updatedStudent.getRollNo(),
                updatedStudent.getAddress(), updatedStudent.getContactNumber(), updatedStudent.getFatherName(),
                updatedStudent.getMotherName(), findByGradeNo(updatedStudent.getGradeNo()));
        studentRepo.save(student);
        return student;
    }

    @Override
    public boolean updateStudentMark(Integer studentId, HashMap<String, Float> subjectMark){

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
        return true;
    }


}
