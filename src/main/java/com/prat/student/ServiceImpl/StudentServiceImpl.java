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

    @Transactional
    private boolean setStudentFinalMarks(Integer studentId){

        List <Subject> failedSubjects = new LinkedList<>();

        Student student =  findStudentByStudentId(studentId);

        Grade grade = student.getCurrentGrade();

        List<Subject> subjects = grade.getSubjects();

        List<Mark> studentMarks = markRepo.findByStudent(student.getStudentId());

        if(studentMarks.isEmpty()) return false;


        for(Subject subject: subjects){

            List<Mark> subjectMarks =  studentMarks.stream().filter(s -> s.getSubject().equals(subject)).toList();

            if(subjectMarks.isEmpty()) {
                failedSubjects.add(subject);
                continue;
            };

            LinkedList<Mark> studentMarksList = new LinkedList<>(subjectMarks); ///doubt

            Mark selectedAttemptMark = getMaxMark(studentMarksList);

            if(selectedAttemptMark.getMark() < subject.getPassMark()){
                failedSubjects.add(subject);
            }
            System.out.println(studentId);
//            System.out.println(subject.getSubjectId());System.out.println(selectedAttemptMark.getMarkId());
//

            //markRepo.deleteOtherAttempts(studentId, subject.getSubjectId(), selectedAttemptMark.getMarkId());

        }
        return failedSubjects.isEmpty();
    }

    @Override
    public HashMap<String, Object> promoteStudent(Integer studentId){
        Student student = findStudentByStudentId(studentId);
        boolean pass = setStudentFinalMarks(studentId);
        HashMap<String, Object> retObject = new HashMap<>();

        if(pass){
            Grade currentGrade = student.getCurrentGrade();
            Grade newGrade = gradeRepo.findByGradeNo(currentGrade.getGradeNo()+1);
            student.setCurrentGrade(newGrade);
            student.getPreviousGrades().add(currentGrade);

            retObject.put("Student", student);
            retObject.put("Promoted", true);
            return retObject;
        }

        retObject.put("Student", student);
        retObject.put("Promoted", false);
        return retObject;
    }


}
