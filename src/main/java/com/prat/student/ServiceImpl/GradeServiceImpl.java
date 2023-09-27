package com.prat.student.ServiceImpl;

import com.prat.student.Entity.Grade;
import com.prat.student.Entity.Mark;
import com.prat.student.Entity.Student;
import com.prat.student.Entity.Subject;
import com.prat.student.Exception.GradeNotFoundException;
import com.prat.student.Exception.StudentNotFoundException;
import com.prat.student.Exception.SubjectAlreadyExistsException;
import com.prat.student.Exception.SubjectNotFoundException;
import com.prat.student.Model.GradeRequest;
import com.prat.student.Repository.GradeRepository;
import com.prat.student.Repository.MarkRepository;
import com.prat.student.Repository.StudentRepository;
import com.prat.student.Repository.SubjectRepository;
import com.prat.student.Service.GradeService;
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
        if( s == null) throw new SubjectNotFoundException();
        return s;
    }

    private Grade findByGradeNo(Integer gradeNo){
        Grade grade = gradeRepo.findByGradeNo(gradeNo);
        if(grade == null) throw new GradeNotFoundException();
        return grade;
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
    public void createGrade(GradeRequest grade){
        Grade newGrade = new Grade(grade.getGradeNo());
        List<String> subjectList = grade.getSubjects();

        for(String sub : subjectList){
            Subject s = findBySubjectName(sub);
            newGrade.getSubjects().add(subjectRepo.findBySubjectName(sub));
        }
        gradeRepo.save(newGrade);
    }



    @Override
    public void addSubjectsToGrade(Integer gradeNo, List<String> subjects){
        Grade grade = findByGradeNo(gradeNo);

        for(String sub : subjects){
            Subject s = findBySubjectName(sub);
            Optional<Subject> gradeSubject = grade.getSubjects().stream().filter( su -> su.equals(s)).findFirst();
            if(gradeSubject.isPresent()) throw new SubjectAlreadyExistsException();
            grade.getSubjects().add(s);
        }
        gradeRepo.save(grade);
    }

    @Override
    public List<HashMap<String, Object>> promoteAllStudentsByGrade(Integer gradeNo){
        Grade grade = findByGradeNo(gradeNo);
        List<HashMap<String, Object>> promotedList = new ArrayList<>();
        for(Student student : grade.getStudent()){

            boolean pass = setStudentFinalMarks(student.getStudentId());
            HashMap<String, Object> retObject = new HashMap<>();

            if(pass){
                Grade currentGrade = student.getCurrentGrade();
                Grade newGrade = gradeRepo.findByGradeNo(currentGrade.getGradeNo()+1);
                student.setCurrentGrade(newGrade);
                student.getPreviousGrades().add(currentGrade);
                studentRepo.save(student);

                retObject.put("Promoted", true);
                //return retObject;
            }
            else {
                retObject.put("Promoted", false);
            }
            retObject.put("Student", student);
            //return retObject;
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
            //System.out.println(studentId);
//            System.out.println(subject.getSubjectId());System.out.println(selectedAttemptMark.getMarkId());
//

            //markRepo.deleteOtherAttempts(studentId, subject.getSubjectId(), selectedAttemptMark.getMarkId());

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
        //return entityToDTOConversion(studentRepo.findByStudentId(studentId), StudentRequest.class);
        if(student == null) throw new StudentNotFoundException();
        return student;
    }

    @Override
    public List<Student> getGradeStudents(Integer gradeNo){
        return gradeRepo.findByGradeNo(gradeNo).getStudent();
    }

//    @Override
//    public List<HashMap<Student, Float>> getNToppers(Integer gradeNo){
//        Grade grade = findByGradeNo(gradeNo);
//
//        List<HashMap<Integer, Float>> toppers = gradeRepo.getToppers(grade.getGradeNo(), 2023);
//
//    }

    @Override
    public List<HashMap<String, Object>> getNToppers(Integer gradeNo, Integer N){

        Grade grade = findByGradeNo(gradeNo);
        List<List<Number>> toppers = gradeRepo.getToppers(grade.getGradeNo(), 2023);
        List<HashMap <String, Object>> topperList = new LinkedList<HashMap <String, Object>>();
        System.out.println(toppers.size());
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
}



