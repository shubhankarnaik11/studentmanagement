package com.prat.student.serviceimpl;

import com.prat.student.entity.Grade;
import com.prat.student.entity.Mark;
import com.prat.student.entity.Student;
import com.prat.student.entity.Subject;
import com.prat.student.exception.*;
import com.prat.student.model.GradeRequest;
import com.prat.student.repository.GradeRepository;
import com.prat.student.repository.MarkRepository;
import com.prat.student.repository.StudentRepository;
import com.prat.student.repository.SubjectRepository;
import com.prat.student.service.GradeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepo;
    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private MarkRepository markRepo;
    @Autowired
    private StudentRepository studentRepo;

    private Subject findBySubjectName(String subjectName){
        Subject s = subjectRepo.findBySubjectName(subjectName);
        if( s == null) throw new SubjectNotFoundException("Subject " + subjectName + " not found");
        return s;
    }

    private Grade findByGradeNo(Integer gradeNo){
        Grade grade = gradeRepo.findByGradeNo(gradeNo);
        if(grade == null) throw new GradeNotFoundException("Grade " + gradeNo + " not found");
        return grade;
    }

    private boolean isSubjectExistsInGrade(Subject subject, Grade grade){

        Optional<Subject> gradeSubject = grade.getSubjects().stream().filter( su -> Objects.equals(su.getSubjectName(), subject.getSubjectName())).findFirst();
        if(gradeSubject.isPresent()) throw new SubjectAlreadyExistsException(" Subject " + gradeSubject.get() + " already exists in this grade");
        return false;
    }

    @Override
    public List<Grade> getGrades(){
        return gradeRepo.findAll();
    }

    @Override
    public Grade getGradeByGradeNo(Integer gradeNo){
        return findByGradeNo(gradeNo);
    }

    @Override
    public Grade createGrade(GradeRequest grade){
        validateGrade(grade.getGradeNo());
        Grade newGrade = new Grade(grade.getGradeNo());
        List<String> subjects = grade.getSubjects();

        for(String sub : subjects){
            Subject s = findBySubjectName(sub);
            newGrade.getSubjects().add(s);
        }

        gradeRepo.save(newGrade);
        return newGrade;

    }


    @Override
    public Grade addSubjectsToGrade(Integer gradeNo, List<String> subjects){

        Grade grade = findByGradeNo(gradeNo);

        for(String sub : subjects){
            Subject s = findBySubjectName(sub);
            if(!isSubjectExistsInGrade(s, grade))
                grade.getSubjects().add(s);
        }
            gradeRepo.save(grade);
        return grade;
    }

    @Override
    public List<HashMap<String, Object>> promoteAllStudentsByGrade(Integer gradeNo){

        Grade grade = findByGradeNo(gradeNo);

        List<HashMap<String, Object>> promotedList = new ArrayList<>();

        for(Student student : grade.getStudent()){

            boolean pass = setStudentFinalMarks(student.getStudentId());
            HashMap<String, Object> retObject = new HashMap<>();

            if(pass){
                student.setCurrentGrade(gradeRepo.findByGradeNo(grade.getGradeNo()+1));
                student.getPreviousGrades().add(grade);
                studentRepo.save(student);
                retObject.put("Promoted", true);
            }
            else {
                retObject.put("Promoted", false);
            }
            retObject.put("Student", student);
            promotedList.add(retObject);

        }
        return promotedList;
    }

    @Transactional
    private boolean setStudentFinalMarks(Integer studentId){

        List <Subject> failedSubjects = new LinkedList<>();

        Student student =  findStudentByStudentId(studentId);

        Grade grade = student.getCurrentGrade();

        List<Subject> subjects = grade.getSubjects();

        List<Mark> studentMarks = markRepo.findByStudentAndAcademicYear(student, 2023);

        if(studentMarks.isEmpty()) return false;


        for(Subject subject: subjects){

            List<Mark> subjectMarks =  studentMarks.stream().filter(s -> s.getSubject().equals(subject)).toList();

            if(subjectMarks.isEmpty()) {
                failedSubjects.add(subject);
                continue;
            };

            LinkedList<Mark> studentMarksList = new LinkedList<>(subjectMarks); ///doubt

            Mark selectedAttemptMark = getMaxMark(studentMarksList);
            selectedAttemptMark.setIsSelectedMark(true);
            if(selectedAttemptMark.getMark() < subject.getPassMark()){
                failedSubjects.add(subject);
            }
            markRepo.save(selectedAttemptMark);

        }
        return failedSubjects.isEmpty();
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

    private Student findStudentByStudentId(Integer studentId){
        Student student = studentRepo.findByStudentId(studentId);
        if(student == null) throw new StudentNotFoundException("Student (ID :" + studentId + " )");
        return student;
    }

    @Override
    public List<Student> getGradeStudents(Integer gradeNo){
        return gradeRepo.findByGradeNo(gradeNo).getStudent();
    }



    @Override
    public List<HashMap<String, Object>> getNToppers(Integer gradeNo, Integer N){

        Grade grade = findByGradeNo(gradeNo);

        List<List<Number>> toppers = gradeRepo.getToppers(grade.getGradeNo(), 2023);

        List<HashMap <String, Object>> topperList = new LinkedList<HashMap <String, Object>>();

        int i = 1;
        for (List<Number> topper : toppers) {

            if(i>N) break;

            Student s = studentRepo.findByStudentId((Integer)topper.get(0));

            HashMap <String, Object> newTopper = new  HashMap <String, Object>();
            newTopper.put("Position", i);
            newTopper.put("Student", s.getStudentName());
            newTopper.put("Total Marks", topper.get(1));

            topperList.add(newTopper);
            i++;
        }


        return topperList;
    }

    private void validateGrade(Integer gradeNo) {
        if(gradeRepo.findByGradeNo(gradeNo) != null)
            throw new GradeAlreadyExistsException();
    }
}



